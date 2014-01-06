package com.sps.ps.commonbiz.basic.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.basic.dao.WorkspanceporletIDAO;
import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class WorkspanceporletDAOImpl extends GeneralHibernateDAOImpl implements
		WorkspanceporletIDAO {

	public int countByWorkspanceporlet(String hql, String[] values) {
		double rows=super.countByHql(hql, values);
		return (int)rows;
	}

	public void deleteWorkspanceporlet(Workspanceporlet wspor) {
		super.Delete(wspor);

	}

	public Workspanceporlet getWorkspanceporletById(String id) {
		Object obj=super.findById(Workspanceporlet.class, id);
		return obj==null?null:(Workspanceporlet)obj;
	}

	public List getWorkspanceporletByList(String hql, String[] value,
			int start, int limit) {
		
		return super.findByHql(hql, value, start, limit);
	}

	public void saveWorkspanceporlet(Workspanceporlet wspor) {
		super.Save(wspor);

	}

	public void updateWorkspanceporlet(Workspanceporlet wspor) {
		super.Update(wspor);

	}

}
