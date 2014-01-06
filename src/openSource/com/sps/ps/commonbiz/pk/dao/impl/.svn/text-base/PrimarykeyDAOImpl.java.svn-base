package com.sps.ps.commonbiz.pk.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.pk.dao.PrimarykeyDAO;
import com.sps.ps.commonbiz.pk.entity.Primarykey;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class PrimarykeyDAOImpl extends GeneralHibernateDAOImpl implements PrimarykeyDAO {

	public void addPrimarykey(Primarykey pk) {
		super.Save(pk);
		
	}

	public int countPrimaryKey(String hql, String[] values) {
		double value=super.countByHql(hql, values);
		return (int)value ;

	}

	public void deletePrimaryKey(Primarykey pk) {
		super.Delete(pk);
		
	}

	public Primarykey getPrimaryKeyById(String id) {
		return (Primarykey) super.findById(Primarykey.class, id);
	}

	public List getPrimaryKeyByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updatePrimaryKey(Primarykey pk) {
		super.Update(pk);
		
	}

}
