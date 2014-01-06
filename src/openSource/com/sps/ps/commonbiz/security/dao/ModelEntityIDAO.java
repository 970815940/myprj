package com.sps.ps.commonbiz.security.dao;

import java.util.List;

import com.sps.ps.commonbiz.security.entity.ModelEntity;

public interface ModelEntityIDAO {
	public void addModelEntity(ModelEntity me);
	public void deleteModelEntity(ModelEntity me);
	public void updateModelEntity(ModelEntity me);
	public ModelEntity getModelEntityById(String id);
	public List getModelEntityByList(String hql,String values[],int start,int size);
	public int countModelEntityByList(String sql,String values[]);
	public void deleteModelEntitys(String hql,String values[]);
	public List getUnAuthorize(String hql,List lst);
}
