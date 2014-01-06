package com.sps.ps.commonbiz.basic.dao;

import java.util.List;


import com.sps.ps.commonbiz.basic.entity.Workspanceporlet;

public interface WorkspanceporletIDAO {
	public void saveWorkspanceporlet(Workspanceporlet wspor);
	public void deleteWorkspanceporlet(Workspanceporlet wspor);
	public void updateWorkspanceporlet(Workspanceporlet wspor);
	public int countByWorkspanceporlet(String hql,String[] values);
	public List getWorkspanceporletByList(String hql,String[] value,int start,int limit);
	public Workspanceporlet getWorkspanceporletById(String id);
	
}
