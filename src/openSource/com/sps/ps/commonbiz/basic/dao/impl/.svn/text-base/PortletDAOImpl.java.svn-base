package com.sps.ps.commonbiz.basic.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.basic.dao.PortletIDAO;
import com.sps.ps.commonbiz.basic.entity.Portlet;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class PortletDAOImpl extends GeneralHibernateDAOImpl implements
		PortletIDAO {

	public int countByPortlet(String hql, String[] values) {
		double rows=super.countByHql(hql, values);
		return (int)rows;
	}

	public void deletePortlet(Portlet por) {
		super.Delete(por);

	}

	public Portlet getPortletById(String id) {
		Object obj=super.findById(Portlet.class, id);
		return obj==null?null:(Portlet)obj;
	}

	public List getPortletByList(String hql, String[] value, int start,
			int limit) {
		
		return super.findByHql(hql, value, start, limit);
	}

	public void savePortlet(Portlet por) {
		super.Save(por);

	}

	public void updatePortlet(Portlet por) {
		super.Update(por);

	}

}
