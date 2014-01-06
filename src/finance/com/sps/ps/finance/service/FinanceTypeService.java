package com.sps.ps.finance.service;

import java.util.List;

import com.sps.ps.finance.entity.FinanceTypepath;

public interface FinanceTypeService {
	public void saveFinanceType(FinanceTypepath ftp);
	public void deleteFinanceType(FinanceTypepath ftp);
	public FinanceTypepath getFinanceTypeById(String id);
	public List getFinanceTypeByList(String columns[],String[] ysf,String[] values,int start,int size);
	public int countFinanceType(String columns[],String[] ysf,String[] values);
	public void updateFinanceType(FinanceTypepath ftp);
	public String getFinanceTypeTree(String pid);
}
