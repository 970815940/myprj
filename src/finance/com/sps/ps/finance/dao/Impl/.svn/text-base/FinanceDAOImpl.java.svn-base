package com.sps.ps.finance.dao.Impl;

import java.util.List;

import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;
import com.sps.ps.finance.dao.FinanceIDAO;
import com.sps.ps.finance.entity.Finance;

public class FinanceDAOImpl extends GeneralHibernateDAOImpl implements
		FinanceIDAO {

	public void addFinance(Finance fn) {
		super.Save(fn);

	}

	public int countFinance(String hql, String[] values) {
		double rows=super.countByHql(hql, values);
		return (int)rows;
	}

	public void deleteFinance(Finance fn) {
		super.Delete(fn);

	}

	public Finance getFinanceById(String id) {
		Object obj=super.findById(Finance.class, id);
		return obj!=null?(Finance)obj:null;
	}

	public List getFinanceByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateFinance(Finance fn) {
		super.Update(fn);
	}

}
