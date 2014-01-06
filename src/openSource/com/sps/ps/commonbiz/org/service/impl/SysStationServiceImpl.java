package com.sps.ps.commonbiz.org.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysStationIDAO;
import com.sps.ps.commonbiz.org.entity.SysStation;
import com.sps.ps.commonbiz.org.service.SysStationService;

public class SysStationServiceImpl implements SysStationService {
	private SysStationIDAO sysStationDAOImpl;

	/**
	 * @param sysStationDAOImpl the sysStationDAOImpl to set
	 */
	public void setSysStationDAOImpl(SysStationIDAO sysStationDAOImpl) {
		this.sysStationDAOImpl = sysStationDAOImpl;
	}


	public void addSysStation(SysStation user) {
		this.sysStationDAOImpl.addSysStation(user);
		
	}

	public int countSysStation(String[] columns,String[] ysf, String[] values) {
		String hql="select count(*) from SysStation where 1=1 ";
		hql+=buildHql(columns,ysf);
		int rows= this.sysStationDAOImpl.countSysStation(hql, values);
		return rows;
	}

	public void deleteSysStation(SysStation user) {
		this.sysStationDAOImpl.deleteSysStation(user);
		
	}

	public SysStation getSysStationById(String id) {
		return this.sysStationDAOImpl.getSysStationById(id);
	}

	public List getSysStationByList(String[] columns,String[] ysf,String[] values, int start,
			int size) {
		String hql="from SysStation where 1=1 ";
		hql+=buildHql(columns,ysf);
		hql+=" order by ssIndex";
		return this.sysStationDAOImpl.getSysStationByList(hql, values, start, size);
	}

	public void updateSysStation(SysStation user) {
		this.sysStationDAOImpl.updateSysStation(user);
		
	}
	private String buildHql(String[] columns,String []ysf){
		StringBuffer sb=new StringBuffer();
		if(columns==null)return"";
		for (int i = 0; i < columns.length; i++) {
			sb.append(" and "+columns[i]+" "+ysf[i]+"?");
		}
		
		return sb.toString();
	}



	
}
