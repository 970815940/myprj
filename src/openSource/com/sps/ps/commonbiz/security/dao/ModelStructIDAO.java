package com.sps.ps.commonbiz.security.dao;

import java.util.List;

import com.sps.ps.commonbiz.security.entity.ModelStruct;

public interface ModelStructIDAO {
	public void addModelStruct(ModelStruct ms);
	public void deleteModelStruct(ModelStruct ms);
	public void updateModelStruct(ModelStruct ms);
	public ModelStruct getModelStructById(String id);
	public List getModelStructByList(String hql,String values[],int start,int size);
}
