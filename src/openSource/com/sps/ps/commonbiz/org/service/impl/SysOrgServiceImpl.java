package com.sps.ps.commonbiz.org.service.impl;

import java.util.List;


import com.sps.ps.commonbiz.org.dao.SysOrgIDAO;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.commonbiz.org.entity.SysStation;
import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.commonbiz.org.service.SysOrgService;
import com.sps.ps.commonbiz.org.service.SysStationService;
import com.sps.ps.commonbiz.org.service.SysUserService;
import com.sps.ps.commonbiz.pk.service.impl.GETKEY;
import com.sps.ps.utils.BuildHql;

public class SysOrgServiceImpl implements SysOrgService {
	private SysOrgIDAO sysOrgDAOImpl;
	private SysStationService sysStationServiceImpl;
	private SysUserService sysUserServiceImpl;

	/**
	 * @param sysUserServiceImpl the sysUserServiceImpl to set
	 */
	public void setSysUserServiceImpl(SysUserService sysUserServiceImpl) {
		this.sysUserServiceImpl = sysUserServiceImpl;
	}


	/**
	 * @param sysStationServiceImpl the sysStationServiceImpl to set
	 */
	public void setSysStationServiceImpl(SysStationService sysStationServiceImpl) {
		this.sysStationServiceImpl = sysStationServiceImpl;
	}


	/**
	 * @param sysOrgDAOImpl the sysOrgDAOImpl to set
	 */
	public void setsysOrgDAOImpl(SysOrgIDAO sysOrgDAOImpl) {
		this.sysOrgDAOImpl = sysOrgDAOImpl;
	}


	public void addSysOrg(SysOrg org) {
		this.sysOrgDAOImpl.addSysOrg(org);
		
	}

	public int countSysOrg(String[] columns,String[] ysf, String[] values) {
		String hql="select count(*) from SysOrg where 1=1";
		return this.sysOrgDAOImpl.countSysOrg(hql, values);
	}

	public void deleteSysOrg(SysOrg org) {
		this.sysOrgDAOImpl.deleteSysOrg(org);
		
	}

	public SysOrg getSysOrgById(String id) {
		return this.sysOrgDAOImpl.getSysOrgById(id);
	}

	public List getSysOrgByList(String[] columns,String[] ysf, String[] values, int start,
			int size) {
		String hql="from SysOrg where 1=1";
		hql+=BuildHql.createHql(columns, ysf);
		hql+=" order by soIndex";
		return this.sysOrgDAOImpl.getSysOrgByList(hql, values, start, size);
	}

	public void updateSysOrg(SysOrg org) {
		this.sysOrgDAOImpl.updateSysOrg(org);
		
	}


	public void assignStation(String dptId, String stationId) {
		if(stationId==null||stationId.equals("")){
			throw new RuntimeException("岗位为空,分配失败");
		}
		SysOrg org=getSysOrgById(dptId);
		SysStation ss_=this.sysStationServiceImpl.getSysStationById(stationId);
		SysOrg so=new SysOrg();
		so.setSoGuid(GETKEY.getKey("org"));
		so.setSoDptid(org.getSoDptid());
		so.setSoIndex(0);
		//so.setSoOrg("");
		so.setSoOrgdispalyname(ss_.getSsDispalyname());
		so.setSoOrgkind("ssn");//岗位
		so.setSoOrgpath(org.getSoOrgpath()+"/"+so.getSoGuid());
		so.setSoParentid(org.getSoGuid());
		so.setSoStation(stationId);
		this.sysOrgDAOImpl.addSysOrg(so);
		
		
	}


	public void assignUser(String userIds, String stationId) {
		if(userIds==null||userIds.equals("")){
			throw  new RuntimeException("人员ID为空,为岗位分配人员失败");
		}
		String[] user=userIds.split(",");
		SysOrg org=getSysOrgById(stationId);
		for (int i = 0; i < user.length; i++) {
			SysUser su=this.sysUserServiceImpl.getSysUserById(user[i]);
			SysOrg so=new SysOrg();
			so.setSoDptid(org.getSoDptid());
			so.setSoGuid(GETKEY.getKey("org"));
			so.setSoIndex(0);
			//so.setSoOrg(soOrg)
			so.setSoOrgdispalyname(su.getSuDisplayname());
			so.setSoOrgkind("user");
			so.setSoOrgpath(org.getSoOrgpath()+"/"+so.getSoGuid());
			so.setSoParentid(org.getSoGuid());
			so.setSoStation(org.getSoStation());
			so.setSoUser(user[i]);	
			this.sysOrgDAOImpl.addSysOrg(so);
		}
	}


	public List getSysForHql_In(String hql, String collection, String[] strs) {
		return this.sysOrgDAOImpl.getSysOrgByHqlForIn(hql, collection, strs);
	}


}
