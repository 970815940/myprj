package com.sps.ps.commonbiz.security.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.security.dao.ModelRightIDAO;
import com.sps.ps.commonbiz.security.entity.ModelRight;
import com.sps.ps.commonbiz.security.service.ModelRightService;
import com.sps.ps.utils.BuildHql;

public class ModelRightServiceImpl implements ModelRightService {
	private ModelRightIDAO modelRightDAOImpl;
	public void addModelRight(ModelRight mr) {
		this.modelRightDAOImpl.addModelRight(mr);
		
	}

	public void deleteModelRight(ModelRight mr) {
		this.modelRightDAOImpl.deleteModelRight(mr);
		
	}

	public ModelRight getModelRightById(String id) {
		
		return this.modelRightDAOImpl.getModelRightById(id);
	}

	public List getModelRightByList(String[] columns, String[] ysf,
			String[] values, int start, int limit) {
		StringBuffer hql=new StringBuffer("from ModelRight where 1=1 ");
		hql.append(BuildHql.createHql(columns, ysf));
		
		return this.modelRightDAOImpl.getModelRightByList(hql.toString(), values, start, limit);
	}

	public int getModelRightBycount( String[] columns, String[] ysf,
			String[] values) {
		String hql="";
		return this.modelRightDAOImpl.getModelRightBycount(hql, values);
	}

	public void updateModelRight(ModelRight mr) {
		this.modelRightDAOImpl.updateModelRight(mr);
		
	}

	public void setModelRightDAOImpl(ModelRightIDAO modelRightDAOImpl) {
		this.modelRightDAOImpl = modelRightDAOImpl;
	}

	public List getModelRightByGuid(String orgGuid) {
		String hql="from ModelRight where mrOrgguid=?";
		return this.modelRightDAOImpl.getModelRightByList(hql, new String[]{orgGuid}, -1, -1);
	}

	public List getModelRightByGuidAndType(String hql,String[] collications,Object[] values) {
		
		return this.modelRightDAOImpl.getMRFor_HQL_IN(hql, collications, values);
	}

	public void deleteModelRIghtByGuidAndModelEntityId(String hql,
			String[] meIds) {
		this.modelRightDAOImpl.deleteMRguidAndMeId(hql, meIds);
		
	}

	public void deleteModelRIghtByDels(String hql) {
		this.modelRightDAOImpl.deleteModelRightDels(hql);
		
	}



}
