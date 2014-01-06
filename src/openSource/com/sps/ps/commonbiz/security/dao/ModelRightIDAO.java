package com.sps.ps.commonbiz.security.dao;

import java.util.List;

import com.sps.ps.commonbiz.security.entity.ModelRight;

public interface ModelRightIDAO {
	public void addModelRight(ModelRight mr);
	public void updateModelRight(ModelRight mr);
	public void deleteModelRight(ModelRight mr);
	public ModelRight getModelRightById(String id);//String[] columns,String[] ysf,
	public List getModelRightByList(String hql,String[] values,int start,int limit);
	public int getModelRightBycount(String hql,String[] values);
	public List getMRFor_HQL_IN(String hql,String[] collections,Object[] values);
	public void deleteMRguidAndMeId(String hql,String[] meIds);
	/***
	 * 根据条件删除授权表数据
	 * @param hql
	 */
	public void deleteModelRightDels(String hql);
	
}
