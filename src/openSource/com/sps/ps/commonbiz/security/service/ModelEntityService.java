package com.sps.ps.commonbiz.security.service;

import java.util.List;

import com.sps.ps.commonbiz.security.entity.ModelEntity;

public interface ModelEntityService {
	public void addModelEntity(ModelEntity me);
	public void deleteModelEntity(ModelEntity me);
	public void updateModelEntity(ModelEntity me);
	public ModelEntity getModelEntityById(String id);
	public List getModelEntityByList(String[] columns,String[] ysf, String values[],int start,int size);
	public int countModelEntityByList(String[] columns,String[] ysf,String values[]);
	/**
	 * 根据HQL删除多个ModelEntity对象
	 * @param columns
	 * @param ysf
	 * @param values
	 */
	public void deleteModelEntitys(String[] columns,String[] ysf,String[] values);
	public List getUnModelRight(String hql,List list);
}
