package com.sps.ps.finance.service.impl;

import java.util.List;

import com.sps.ps.finance.dao.FinanceTypeIDAO;
import com.sps.ps.finance.entity.FinanceTypepath;
import com.sps.ps.finance.service.FinanceTypeService;
import com.sps.ps.utils.entity.Tree;

public class FinanceTypeServiceImpl implements FinanceTypeService {
	private FinanceTypeIDAO financeTypeDAOImpl;
	
	
	public int countFinanceType(String[] columns, String[] ysf, String[] values) {
		String hql="select count(*) from FinanceTypepath where 1=1";
		return this.financeTypeDAOImpl.countFinanceType(hql, values);
	}

	public void deleteFinanceType(FinanceTypepath ftp) {
		this.financeTypeDAOImpl.deleteFinanceType(ftp);
		
	}

	public FinanceTypepath getFinanceTypeById(String id) {
		
		return this.financeTypeDAOImpl.getFinanceTypeById(id);
	}

	public List getFinanceTypeByList(String[] columns, String[] ysf,
			String[] values, int start, int size) {
		String hql="from FinanceTypepath where 1=1";
		return this.financeTypeDAOImpl.getFinanceTypeByList(hql, values, start, size);
	}

	public void saveFinanceType(FinanceTypepath ftp) {
		this.financeTypeDAOImpl.saveFinanceType(ftp);
		
	}

	public void setFinanceTypeDAOImpl(FinanceTypeIDAO financeTypeDAOImpl) {
		this.financeTypeDAOImpl = financeTypeDAOImpl;
	}

	public void updateFinanceType(FinanceTypepath ftp) {
		this.financeTypeDAOImpl.updateFinanceType(ftp);
	}

	public String getFinanceTypeTree(String pid) {
		List lst=this.financeTypeDAOImpl.getFinanceTypeByList("from FinanceTypepath", null, -1, -1);
		Tree tree=new Tree();
		tree.setObjForm(lst, "ftId", "ftPid");
		String str=getInfo(tree,pid);
		return str;
	}
	private String getInfo(Tree tree,String pid){
		StringBuffer sb=new StringBuffer();
		List lst=tree.getChildrens(pid);
		if(lst!=null&&lst.size()!=0){
			sb.append("[");
			for (int i = 0; i <lst.size(); i++) {
				FinanceTypepath ftp=(FinanceTypepath)lst.get(i);
				boolean leaf=tree.hasChildren(ftp.getFtId());
				sb.append("{");
				sb.append("text:\""+ftp.getFtDisplayname()+"\",");
				sb.append("ftpId:\""+ftp.getFtId()+"\",");
				sb.append("id:\""+ftp.getFtId()+"\",");
				sb.append("ftpPid:\""+ftp.getFtPid()+"\",");
				sb.append("leaf:"+!leaf);
				if(leaf){
					sb.append(",children:"+getInfo(tree, ftp.getFtId()));
				}
				sb.append("}");
				if(lst.size()-1!=i){
					sb.append(",");
				}
			}
			
			sb.append("]");
		}
		return sb.toString();
	}

}
