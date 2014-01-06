package com.sps.ps.commonbiz.org.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysUserDAO;
import com.sps.ps.commonbiz.org.entity.SysUser;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class SysUserDAOImpl extends GeneralHibernateDAOImpl implements SysUserDAO {

	public void addSysUser(SysUser user) {
		super.Save(user);
		
	}

	public int countSysUser(String hql, String[] values) {
		
		return (int) super.countByHql(hql, values);
	}

	public void deleteSysUser(SysUser user) {
		super.Delete(user);
		
	}

	public SysUser getSysUserById(String id) {
		
		return (SysUser) super.findById(SysUser.class, id);
	}

	public List getSysUserByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateSysUser(SysUser user) {
		super.Update(user);
		
	}

}
