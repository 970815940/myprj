/* SspWfCategoryServiceImpl.java 
 * Dec 18, 2013  11:26:15 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.service.impl;

import java.io.Serializable;
import java.util.List;

import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.springframework.util.Assert;

import com.sps.ps.utils.StringUtil;
import com.sysps.ps.workflow.dao.SspWfCategoryIDAO;
import com.sysps.ps.workflow.entity.SspWfCategory;
import com.sysps.ps.workflow.service.SspWfCategoryService;

/**
 *<p>SspWfCategoryServiceImpl</p>
 *<p></p> 
 *<p></p> 
 * @author  <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 18, 2013 11:26:15 AM
 * @version  
 */
public class SspWfCategoryServiceImpl implements SspWfCategoryService {
	private SspWfCategoryIDAO categoryDAOImpl;
	private JbpmConfiguration jbpmconfig;
	
	/**
	 * @param jbpmconfig the jbpmconfig to set
	 */
	public void setJbpmconfig(JbpmConfiguration jbpmconfig) {
		this.jbpmconfig = jbpmconfig;
	}

	public void addSspWfCategory(SspWfCategory cate) {
		this.categoryDAOImpl.addSspWfCategory(cate);

	}

	public int countBySspWfCategory(SspWfCategory cate) {
		String hql="";
		String[] values=null;
		return this.categoryDAOImpl.countBySspWfCategory(hql, values);
	}

	public void deleteSspWfCategory(SspWfCategory cate) {
		this.categoryDAOImpl.deleteSspWfCategory(cate);

	}

	public List getBySspWfCategory(SspWfCategory cate, int start, int size) {
		String hql="from SspWfCategory where 1=1";
		String[] values=null;
		return this.categoryDAOImpl.getBySspWfCategory(hql, values, start, size);
	}

	public void updateSspWfCategory(SspWfCategory cate) {
		this.categoryDAOImpl.updateSspWfCategory(cate);

	}

	/**
	 * @param categoryDAOImpl the categoryDAOImpl to set
	 */
	public void setCategoryDAOImpl(SspWfCategoryIDAO categoryDAOImpl) {
		this.categoryDAOImpl = categoryDAOImpl;
	}

	public SspWfCategory getSspWfCategoryById(Serializable id) {
		
		return this.categoryDAOImpl.getSspWfCategoryById(id);
	}

	public List getwfTree(SspWfCategory cate) {
		String hql="from SspWfCategory where 1=1";
		hql+=" and parent="+(cate.getParent()==0?-1:cate.getParent())+"";
		return this.categoryDAOImpl.getBySspWfCategory(hql, null, -1, -1);
	}
	private String buildHql(SspWfCategory cate){
		StringBuffer hql=new StringBuffer();
		if(cate.getParent()==0){
			
		}
		hql.append(" parent="+(cate.getParent()==0?-1:cate.getParent())+"");
		return hql.toString();
	}

	public List getProcessDefinitionByName(String pdName) {
		JbpmContext context=null;
		List lst=null;
		try {
			Assert.isTrue(StringUtil.isEmpty(pdName),"流程定义名称不能为空!");
			context=jbpmconfig.getCurrentJbpmContext();
			if(context==null)context=jbpmconfig.createJbpmContext();
			lst=context.getGraphSession().findAllProcessDefinitionVersions(pdName);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(context!=null)context.close();
		}
		return lst;
	}

}
    
