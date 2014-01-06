package com.sps.ps.commonbiz.org.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysDepartmentIDAO;
import com.sps.ps.commonbiz.org.entity.SysDepartment;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class SysDepartmentDAOImpl extends GeneralHibernateDAOImpl implements SysDepartmentIDAO {
	public void addSysDepartment(SysDepartment user) {
		super.Save(user);
		
	}

	public int countSysDepartment(String hql, String[] values) {
		
		return (int) super.countByHql(hql, values);
	}

	public void deleteSysDepartment(SysDepartment dpt) {
		super.Delete(dpt);
		
	}

	public SysDepartment getSysDepartmentById(String id) {
		
		return (SysDepartment) super.findById(SysDepartment.class, id);
	}

	public List getSysDepartmentByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateSysDepartment(SysDepartment dpt) {
		super.Update(dpt);
		
	}

}
