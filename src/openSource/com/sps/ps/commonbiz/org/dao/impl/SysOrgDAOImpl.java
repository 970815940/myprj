package com.sps.ps.commonbiz.org.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysOrgIDAO;
import com.sps.ps.commonbiz.org.entity.SysOrg;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class SysOrgDAOImpl extends GeneralHibernateDAOImpl implements SysOrgIDAO {
	public void addSysOrg(SysOrg user) {
		super.Save(user);
		
	}

	public int countSysOrg(String hql, String[] values) {
		
		return (int) super.countByHql(hql, values);
	}

	public void deleteSysOrg(SysOrg user) {
		super.Delete(user);
		
	}

	public SysOrg getSysOrgById(String id) {
		
		return (SysOrg) super.findById(SysOrg.class, id);
	}

	public List getSysOrgByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateSysOrg(SysOrg org) {
		super.Update(org);
		
	}

	public List getSysOrgByHqlForIn(String hql, String collection, String[] str) {
		
		return super.executionHql_FOR_IN(hql, collection, str);
	}

}
