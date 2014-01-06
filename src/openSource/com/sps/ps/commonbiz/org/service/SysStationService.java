package com.sps.ps.commonbiz.org.service;

import java.util.List;

import com.sps.ps.commonbiz.org.entity.SysStation;

public interface SysStationService {
	public void addSysStation(SysStation station);
	public void deleteSysStation(SysStation station);
	public void updateSysStation(SysStation station);
	public SysStation getSysStationById(String id);
	public List getSysStationByList(String[] columns,String[] ysf,String[] values,int start,int size);
	public int countSysStation(String[] columns,String[] ysf,String[] values);

}
