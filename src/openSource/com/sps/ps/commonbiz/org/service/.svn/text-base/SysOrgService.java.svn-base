package com.sps.ps.commonbiz.org.service;

import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysOrg;;

public interface SysOrgService {
	public void addSysOrg(SysOrg org);
	public void deleteSysOrg(SysOrg org);
	public void updateSysOrg(SysOrg org);
	public SysOrg getSysOrgById(String id);
	public List getSysOrgByList(String[] columns,String[] ysf,String[] values,int start,int size);
	public int countSysOrg(String[] columns,String[] ysf,String[] values);
	public List getSysForHql_In(String hql,String collection,String[] strs);
	/**
	 * 为部门分配岗位
	 * @param dptId  部门ID
	 * @param stationId 机构表--> 岗位ID ssn
	 */
	public void assignStation(String dptId,String stationId);
	/***
	 * 为岗位分配人员
	 * @param userIds 人员ID 
	 * @param stationId  岗位ID
	 */
	public void assignUser(String userIds,String stationId);
}
