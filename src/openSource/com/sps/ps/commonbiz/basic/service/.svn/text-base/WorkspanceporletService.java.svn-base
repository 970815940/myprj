package com.sps.ps.commonbiz.basic.service;

import java.util.List;

import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;
import com.sps.ps.core.web.ClientProxy;

public interface WorkspanceporletService {
	public void saveWorkspanceporlet(Workspanceporlet wspor);
	public void deleteWorkspanceporlet(Workspanceporlet wspor);
	public void updateWorkspanceporlet(Workspanceporlet wspor);
	public int countByWorkspanceporlet(String[] values);
	public List getWorkspanceporletByList(String[] columns,String[] ysf,String[] value,int start,int limit);
	public Workspanceporlet getWorkspanceporletById(String id);
	/**
	 * 根据指定用户获取 用户所指定的组件。
	 * @param UserId
	 * @return
	 */
	public List getUserForPortlet(String UserId);
	public List getPortletSetList(ClientProxy cp);
}
