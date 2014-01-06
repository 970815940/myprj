package com.sps.ps.commonbiz.basic.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.basic.dao.PortletIDAO;
import com.sps.ps.commonbiz.basic.entity.Portlet;
import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;
import com.sps.ps.commonbiz.basic.service.PortletService;
import com.sps.ps.commonbiz.basic.service.WorkspanceporletService;
import com.sps.ps.utils.BuildHql;

public class PortletServiceImpl implements PortletService {
	private PortletIDAO portletDAOImpl;
	private WorkspanceporletService workspanceportletServiceImpl;
	public int countByPortlet(String[] values) {
		StringBuffer hql=new StringBuffer("select count(*) from Portlet where 1=1");
		return this.portletDAOImpl.countByPortlet(hql.toString(), values);
	}

	public void deletePortlet(Portlet por) {
		//先删除已关联的用户工作空间
		List list=this.workspanceportletServiceImpl.getWorkspanceporletByList(new String[]{"portletid.id"}, new String[]{"="}, new String[]{por.getId()}, -1, -1);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Workspanceporlet ws=(Workspanceporlet)list.get(i);
				workspanceportletServiceImpl.deleteWorkspanceporlet(ws);
			}
		}
		
		this.portletDAOImpl.deletePortlet(por);

	}

	public Portlet getPortletById(String id) {
		
		return this.portletDAOImpl.getPortletById(id);
	}

	public List getPortletByList(String[] columns, String[] ysf,
			String[] value, int start, int limit) {
		StringBuffer hql=new StringBuffer("from Portlet where 1=1");
		hql.append(BuildHql.createHql(columns, ysf));
		return this.portletDAOImpl.getPortletByList(hql.toString(), value, start, limit);
	}

	public void savePortlet(Portlet por) {
		this.portletDAOImpl.savePortlet(por);

	}

	public void updatePortlet(Portlet por) {
		this.portletDAOImpl.updatePortlet(por);

	}

	public void setPortletDAOImpl(PortletIDAO portletDAOImpl) {
		this.portletDAOImpl = portletDAOImpl;
	}

	public void setWorkspanceportletServiceImpl(
			WorkspanceporletService workspanceportletServiceImpl) {
		this.workspanceportletServiceImpl = workspanceportletServiceImpl;
	}


}
