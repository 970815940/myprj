package com.sps.ps.commonbiz.security.service.impl;

import java.util.List;

import com.sps.ps.commonbiz.security.dao.ModelEntityIDAO;
import com.sps.ps.commonbiz.security.entity.ModelEntity;
import com.sps.ps.commonbiz.security.service.ModelEntityService;
import com.sps.ps.commonbiz.security.service.ModelRightService;

public class ModelEntityServiceImpl implements ModelEntityService {
	private ModelEntityIDAO modelEntityDAOImpl;
	private ModelRightService modelRightServiceImpl;//授权
	public void setModelRightServiceImpl(ModelRightService modelRightServiceImpl) {
		this.modelRightServiceImpl = modelRightServiceImpl;
	}

	public void addModelEntity(ModelEntity me) {
		this.modelEntityDAOImpl.addModelEntity(me);

		
	}

	public void deleteModelEntity(ModelEntity me) {
		//删除功能实体的同时删除授权相关
		String hql="delete from ModelRight where 1=1 and mrMeid='"+me.getMeId()+"'";
		this.modelRightServiceImpl.deleteModelRIghtByDels(hql);
		
		this.modelEntityDAOImpl.deleteModelEntity(me);
		
		
	}

	public ModelEntity getModelEntityById(String id) {
		return this.modelEntityDAOImpl.getModelEntityById(id);
	}

	public List getModelEntityByList(String[] columns,String[] ysf, String[] values, int start,
			int size) {
		StringBuffer hql=new StringBuffer("from ModelEntity where 1=1");
		if(columns!=null){
			for (int i = 0; i < columns.length; i++) {
				hql.append(" and "+columns[i]+" "+ysf[i]+" ?");
			}
		}
		return this.modelEntityDAOImpl.getModelEntityByList(hql.toString(), values, start, size);
	}

	public void updateModelEntity(ModelEntity me) {
		this.modelEntityDAOImpl.updateModelEntity(me);
		
	}


	/**
	 * @param modelEntityDAOImpl the modelEntityDAOImpl to set
	 */
	public void setModelEntityDAOImpl(ModelEntityIDAO modelEntityDAOImpl) {
		this.modelEntityDAOImpl = modelEntityDAOImpl;
	}

	public int countModelEntityByList(String[] columns,String[] ysf,String[] values) {
		StringBuffer hql=new StringBuffer("select count(*) from ModelEntity where 1=1");
		if(columns!=null){
			for (int i = 0; i < columns.length; i++) {
				hql.append(" and "+columns[i]+" "+ysf[i]+" ?");
			}
		}		
		return this.modelEntityDAOImpl.countModelEntityByList(hql.toString(), values);
	}

	public void deleteModelEntitys(String[] columns, String[] ysf,
			String[] values) {
		String hql="delete from ModelEntity where 1=1";
		hql+=buildHql(columns,ysf);
		this.modelEntityDAOImpl.deleteModelEntitys(hql, values);
	}
	private String buildHql(String[] columns,String[] ysf){
		StringBuffer sb=new StringBuffer();
		if(columns==null)return "";
		for (int i = 0; i < columns.length; i++) {
		 sb.append(" and "+columns[i]+" "+ysf[i]+" ?");	
		}
		return sb.toString();
	}
	public List getUnModelRight(String hql, List list) {
		
		return this.modelEntityDAOImpl.getUnAuthorize(hql, list);
	}	

}
