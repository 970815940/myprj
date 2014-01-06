package com.sps.ps.finance.service.impl;

import java.util.List;

import com.sps.ps.finance.dao.FinanceIDAO;
import com.sps.ps.finance.entity.Finance;
import com.sps.ps.finance.service.FinanceService;
import com.sps.ps.utils.BuildHql;

public class FinanceServiceImpl implements FinanceService {
	private FinanceIDAO financeDAOImpl;
	
	
	public void setFinanceDAOImpl(FinanceIDAO financeDAOImpl) {
		this.financeDAOImpl = financeDAOImpl;
	}

	public void addFinance(Finance fn) {
		financeDAOImpl.addFinance(fn);

	}

	public int countFinance(String[] columns,String[] ysf,String[] values) {
		String hql="select count(*) from Finance where 1=1";
		hql+=BuildHql.createHql(columns, ysf);
		return this.financeDAOImpl.countFinance(hql, values);
	}

	public void deleteFinance(Finance fn) {
		this.financeDAOImpl.deleteFinance(fn);

	}

	public Finance getFinanceById(String id) {
		
		return this.financeDAOImpl.getFinanceById(id);
	}

	public List getFinanceByList(String[] columns,String[] ysf,String[] values, int start, int size) {
		String hql="from Finance where 1=1";
		hql+=BuildHql.createHql(columns, ysf);
		hql+=" order by FApplytime desc";
		return this.financeDAOImpl.getFinanceByList(hql, values, start, size);
	}

	public void updateFinance(Finance fn) {
		this.financeDAOImpl.updateFinance(fn);

	}

}
