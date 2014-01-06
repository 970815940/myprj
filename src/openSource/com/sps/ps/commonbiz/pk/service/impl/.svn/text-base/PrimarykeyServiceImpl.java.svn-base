package com.sps.ps.commonbiz.pk.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.pk.dao.PrimarykeyDAO;
import com.sps.ps.commonbiz.pk.entity.Primarykey;
import com.sps.ps.commonbiz.pk.service.PrimarykeyService;

public class PrimarykeyServiceImpl implements PrimarykeyService{
	private PrimarykeyDAO primarykeyDAOImpl;
	
	
	/**
	 * Spring 注入
	 * @param primarykeyDAOImpl the primarykeyDAOImpl to set
	 */
	public void setPrimarykeyDAOImpl(PrimarykeyDAO primarykeyDAOImpl) {
		this.primarykeyDAOImpl = primarykeyDAOImpl;
	}

	public void addPrimarykey(Primarykey pk) {
		this.primarykeyDAOImpl.addPrimarykey(pk);
		
	}

	public int countPrimaryKey( String[] values) {
		String hql="select count(*) from Primarykey";
		return this.primarykeyDAOImpl.countPrimaryKey(hql, values);
	}

	public void deletePrimaryKey(Primarykey pk) {
		this.primarykeyDAOImpl.deletePrimaryKey(pk);
		
	}

	public Primarykey getPrimaryKeyById(String id) {
		
		return this.primarykeyDAOImpl.getPrimaryKeyById(id);
	}

	public List getPrimaryKeyByList( String[] values, int start,
			int size) {
		String hql ="from Primarykey";
		return this.primarykeyDAOImpl.getPrimaryKeyByList(hql, values, start, size);
	}

	public void updatePrimaryKey(Primarykey pk) {
		this.primarykeyDAOImpl.updatePrimaryKey(pk);
		
	}

	public Primarykey getPrimaryKeyByCode(String code) {
		String hql ="from Primarykey where pkCode=?";
		List lst=this.primarykeyDAOImpl.getPrimaryKeyByList(hql, new String[]{code},-1,-1);
		return lst!=null&&lst.size()==1?(Primarykey)lst.get(0):null;
	}

	public int countByCode(String[] values) {
		String hql ="select count(*) from Primarykey where pkCode=?";
		return this.primarykeyDAOImpl.countPrimaryKey(hql, values);
	}

}
