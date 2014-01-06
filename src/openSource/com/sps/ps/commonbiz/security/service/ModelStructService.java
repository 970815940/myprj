package com.sps.ps.commonbiz.security.service;

import java.util.List;

import com.sps.ps.commonbiz.security.entity.ModelStruct;
import com.sps.ps.utils.entity.Tree;

public interface ModelStructService {
	public void addModelStruct(ModelStruct ms);
	public void deleteModelStruct(ModelStruct ms);
	public void updateModelStruct(ModelStruct ms);
	public ModelStruct getModelStructById(String id);
	public List getModelStructByList(String values[],int start,int size);
	public  String getInfo(Tree tree,String id);
}
