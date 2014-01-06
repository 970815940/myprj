package com.sps.ps.commonbiz.basic.service;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Portlet;

public interface PortletService {
	public void savePortlet(Portlet por);
	public void deletePortlet(Portlet por);
	public void updatePortlet(Portlet por);
	public int countByPortlet(String[] values);
	public List getPortletByList(String[] columns,String[] ysf,String[] value,int start,int limit);
	public Portlet getPortletById(String id);
}
