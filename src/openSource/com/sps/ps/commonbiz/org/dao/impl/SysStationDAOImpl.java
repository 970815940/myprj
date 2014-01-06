package com.sps.ps.commonbiz.org.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.org.dao.SysStationIDAO;
import com.sps.ps.commonbiz.org.entity.SysStation;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class SysStationDAOImpl extends GeneralHibernateDAOImpl implements SysStationIDAO {
	
	public void addSysStation(SysStation station) {
		super.Save(station);
		
	}
	public int countSysStation(String hql, String[] values) {
		
		return (int) super.countByHql(hql, values);
	}

	public void deleteSysStation(SysStation station) {
		super.Delete(station);
		
	}

	public SysStation getSysStationById(String id) {
		
		return (SysStation) super.findById(SysStation.class, id);
	}

	public List getSysStationByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateSysStation(SysStation station) {
		super.Update(station);
		
	}



}
