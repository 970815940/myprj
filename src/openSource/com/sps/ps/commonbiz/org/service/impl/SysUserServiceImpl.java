package com.sps.ps.commonbiz.org.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.sps.ps.commonbiz.org.dao.SysUserDAO;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.org.service.SysUserService;
import com.sps.ps.commonbiz.pk.action.PrimarykeyAction;
import com.sps.ps.commonbiz.security.service.ModelRightService;
import com.sps.ps.utils.BuildHql;

public class SysUserServiceImpl implements SysUserService{
	private SysUserDAO SysUserDAOImpl;
	private SysOrgService orgServiceImpl;
	private ModelRightService modelRightServiceImpl;
	private static Logger log = Logger.getLogger(SysUserServiceImpl.class);
	
	/** 
	 * Spring注入
	 * @param sysUserDAOImpl the sysUserDAOImpl to set
	 */
	public void setSysUserDAOImpl(SysUserDAO sysUserDAOImpl) {
		SysUserDAOImpl = sysUserDAOImpl;
	}

	public void addSysUser(SysUser user) {
		this.SysUserDAOImpl.addSysUser(user);
		
	}

	public int countSysUser(String[] columns,String[] ysf, String[] values) {
		String hql="select count(*) from SysUser where 1=1";
		hql+=BuildHql.createHql(columns, ysf);
		return this.SysUserDAOImpl.countSysUser(hql, values);
	}

	public void deleteSysUser(SysUser user) {
		//删除用户同时 要删除 SYSORG相关联的数据。
		//删除授权表相关联的数据
		List lst=this.orgServiceImpl.getSysOrgByList(new String[]{"soUser"}, new String[]{"="}, new String[]{user.getGuid()}, -1, -1);
		if(lst==null||lst.size()==0){//如果用户没有与机构表关联 那么不需删除机构表和授权表.  否则需要删除机构表数据和授权表数据
			this.SysUserDAOImpl.deleteSysUser(user);
		}else{
			for (int i = 0; i <lst.size(); i++) {
				SysOrg so_=(SysOrg)lst.get(i);
				//首先删除权限
				String hql="delete from ModelRight where 1=1 and mrOrgguid='"+so_.getSoGuid()+"'";
				this.modelRightServiceImpl.deleteModelRIghtByDels(hql);
				//然后删除机构表
				this.orgServiceImpl.deleteSysOrg(so_);
				//然后删除用户
				this.SysUserDAOImpl.deleteSysUser(user);
			}
		}
		
		
	}

	public SysUser getSysUserById(String id) {
		return this.SysUserDAOImpl.getSysUserById(id);
		 
	}

	public List getSysUserByList(String[] columns,String[] ysf, String[] values, int start,
			int size) {
		String hql="from SysUser where 1=1";
		hql+=BuildHql.createHql(columns, ysf);
		hql+=" order by suIndex";
		return this.SysUserDAOImpl.getSysUserByList(hql, values, start, size);
	}

	public void updateSysUser(SysUser user) {
		this.SysUserDAOImpl.updateSysUser(user);
		
	}

	public SysUser getSysUserByLoginId(String loginId) {
		String hql="from SysUser where suId=?";
		List<SysUser> lst=this.SysUserDAOImpl.getSysUserByList(hql, new String[]{loginId}, -1, -1);
		log.debug("用户ID对应的实体有------"+lst!=null?lst.size():0+"个");
		return lst!=null&&lst.size()==1?lst.get(0):null;
	}

	public void setOrgServiceImpl(SysOrgService orgServiceImpl) {
		this.orgServiceImpl = orgServiceImpl;
	}

	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}

}
