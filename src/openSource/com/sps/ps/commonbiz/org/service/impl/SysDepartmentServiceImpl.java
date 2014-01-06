package com.sps.ps.commonbiz.org.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysDepartmentIDAO;
import com.sps.ps.commonbiz.org.entity.SysDepartment;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.service.SysDepartmentService;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.security.service.ModelRightService;

public class SysDepartmentServiceImpl implements SysDepartmentService{
	
	private SysDepartmentIDAO sysDepartmentDAOImpl;
	private SysOrgService sysOrgServiceImpl;
	private ModelRightService modelRightServiceImpl;//授权服务

	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}

	/**
	 * @param sysOrgServiceImpl the sysOrgServiceImpl to set
	 */
	public void setSysOrgServiceImpl(SysOrgService sysOrgServiceImpl) {
		this.sysOrgServiceImpl = sysOrgServiceImpl;
	}

	/**
	 * @param sysDepartmentDAOImpl the sysDepartmentDAOImpl to set
	 */
	public void setSysDepartmentDAOImpl(SysDepartmentIDAO sysDepartmentDAOImpl) {
		this.sysDepartmentDAOImpl = sysDepartmentDAOImpl;
	}
	/***
	 * 新增或者修改部门时.对sysorg里面的数据进行同步
	 * @param flag 1=新增 2=修改
	 */
	private void sysUpdateSysOrg(String flag,SysOrg so,SysDepartment sd){
		if(so==null){
			so=new SysOrg();//创建部门的同时 也要为sysorg创建一个相关记录	
			so.setSoGuid(sd.getGuid());
			so.setSoParentid(sd.getSdPanentid());
			so.setSoIndex(sd.getSdIndex());
			so.setSoDptid(sd.getSdId());
			so.setSoOrgkind(sd.getSdKind().equals("1")?"org":"dpt");
			so.setSoOrgdispalyname(sd.getSdDisplayname());
			so.setSoDptid(sd.getGuid());
		}
		if("-1".equals(sd.getSdPanentid())){
			so.setSoOrgpath(so.getSoGuid());
		}else{
			SysOrg sorg_tmp=this.sysOrgServiceImpl.getSysOrgById(so.getSoParentid());
			if(sorg_tmp==null){
				throw new RuntimeException("程序异常,对象不能为空");
			}
			so.setSoOrgpath(sorg_tmp.getSoOrgpath()+"/"+so.getSoGuid());
		}	
		if(flag.equals("1")){
			this.sysOrgServiceImpl.addSysOrg(so);
		}else{
			so.setSoOrgdispalyname(sd.getSdDisplayname());
			this.sysOrgServiceImpl.updateSysOrg(so);
		}
		
	}

	public void addSysDepartment(SysDepartment dpt) {
		this.sysDepartmentDAOImpl.addSysDepartment(dpt);
		SysOrg so=this.sysOrgServiceImpl.getSysOrgById(dpt.getGuid());
		
		sysUpdateSysOrg("1",so, dpt);//同步SYSORG表数据
		
	}

	public int countSysDepartment(String hql, String[] values) {
		return this.sysDepartmentDAOImpl.countSysDepartment(hql, values);
	}

	public void deleteSysDepartment(SysDepartment dpt) {
		//删除部门的同时 要删除SYSORG表相关数据
		this.sysDepartmentDAOImpl.deleteSysDepartment(dpt);
		SysOrg so=this.sysOrgServiceImpl.getSysOrgById(dpt.getGuid());
		//删除授权
		String hql="delete from ModelRight where 1=1 and mrOrgguid='"+so.getSoGuid()+"'";
		this.modelRightServiceImpl.deleteModelRIghtByDels(hql);
		//删除机构表
		this.sysOrgServiceImpl.deleteSysOrg(so);
		
		
	}

	public SysDepartment getSysDepartmentById(String id) {
		return this.sysDepartmentDAOImpl.getSysDepartmentById(id);
	}

	public List getSysDepartmentByList(String hql, String[] values, int start,
			int size) {
		return this.sysDepartmentDAOImpl.getSysDepartmentByList(hql, values, start, size);
	}

	public void updateSysDepartment(SysDepartment dpt) {
		this.sysDepartmentDAOImpl.updateSysDepartment(dpt);
		SysOrg so=this.sysOrgServiceImpl.getSysOrgById(dpt.getGuid());
		sysUpdateSysOrg("2",so, dpt);//同步SYSORG表数据
		
	}

}
