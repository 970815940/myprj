package com.sps.ps.commonbiz.security.dao.impl;

import java.util.List;

import com.sps.ps.commonbiz.security.dao.ModelStructIDAO;
import com.sps.ps.commonbiz.security.entity.ModelStruct;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class ModelStructDAOImpl extends GeneralHibernateDAOImpl implements ModelStructIDAO {

	public void addModelStruct(ModelStruct ms) {
		super.save(ms);
		
	}

	public void deleteModelStruct(ModelStruct ms) {
		super.Delete(ms);
		
	}

	public ModelStruct getModelStructById(String id) {
		Object obj=super.findById(ModelStruct.class, id);
		return (ModelStruct) obj;
	}

	public List getModelStructByList(String hql,String values[],int start, int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateModelStruct(ModelStruct ms) {
		super.Update(ms);	
	}

}
