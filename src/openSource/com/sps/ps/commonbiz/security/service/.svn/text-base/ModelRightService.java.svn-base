package com.sps.ps.commonbiz.security.service;

import java.util.List;

import com.ibm.db2.jcc.t4.ob;
import com.sps.ps.commonbiz.security.entity.ModelRight;

public interface ModelRightService {
	public void addModelRight(ModelRight mr);
	public void updateModelRight(ModelRight mr);
	public void deleteModelRight(ModelRight mr);
	public ModelRight getModelRightById(String id);
	public List getModelRightByList(String[] columns,String[] ysf,String[] values,int start,int limit);
	public int getModelRightBycount(String[] columns,String[] ysf,String[] values);
	/***
	 * 根据GUID获取ModelRight
	 * @param orgGuid
	 * @return
	 */
	public List getModelRightByGuid(String orgGuid);
	/***
	 * 根据多个guids 和权限类别来获取权限模块
	 * @param guids
	 * @param type
	 * @return
	 */
	public List getModelRightByGuidAndType(String hql,String[] collications,Object[] values);
	/***
	 * 根据GUID  和 功能ID 来取消权限
	 * @param hql
	 */
	public void deleteModelRIghtByGuidAndModelEntityId(String hql,String[] meIds);
	/**
	 * 根据授权表 功能ID 删除授权数据行
	 * @param hql
	 */
	public void deleteModelRIghtByDels(String hql);
	
}
