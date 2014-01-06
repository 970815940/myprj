package com.sps.ps.finance.dao.Impl;

import java.util.List;

import com.ibm.db2.jcc.t4.ob;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;
import com.sps.ps.finance.dao.FinanceTypeIDAO;
import com.sps.ps.finance.entity.FinanceTypepath;

public class FinanceTypeDAOImpl extends GeneralHibernateDAOImpl implements
		FinanceTypeIDAO {

	public int countFinanceType(String hql, String[] values) {
		double rows=super.countByHql(hql, values);
		return (int)rows;
	}

	public void deleteFinanceType(FinanceTypepath ftp) {
		super.Delete(ftp);

	}

	public FinanceTypepath getFinanceTypeById(String id) {
		FinanceTypepath ftp=new FinanceTypepath();
		Object obj=super.findById(ftp.getClass(), id);
		return (FinanceTypepath)obj;
	}

	public List getFinanceTypeByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void saveFinanceType(FinanceTypepath ftp) {
		super.Save(ftp);

	}

	public void updateFinanceType(FinanceTypepath ftp) {
		super.Update(ftp);
		
	}

}
