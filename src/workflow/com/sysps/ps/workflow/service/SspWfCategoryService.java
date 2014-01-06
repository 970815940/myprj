/* SspWfCategoryService.java 
 * Dec 18, 2013  11:24:28 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.service;

import java.util.List;

import com.sysps.ps.workflow.entity.SspWfCategory;

/**
 *<p>SspWfCategoryService</p>
 *<p></p> 
 *<p></p> 
 * @author <a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 18, 2013 11:24:28 AM
 * @version  
 */
public interface SspWfCategoryService {
	public void addSspWfCategory(SspWfCategory cate);
	public void updateSspWfCategory(SspWfCategory cate);
	public int countBySspWfCategory(SspWfCategory cate);
	public List getBySspWfCategory(SspWfCategory cate,int start,int size);
	public void deleteSspWfCategory(SspWfCategory cate);
	public SspWfCategory getSspWfCategoryById(java.io.Serializable id);
	/**
	 * 获取流程设计 左边树形结构
	 *<p>getwfTree ()</p>
	 *<p>Description: </p> 
	 *@param cate
	 *@return
	 *@date Dec 24, 2013 4:31:52 PM
	 */
	public List getwfTree(SspWfCategory cate);
	
	public List getProcessDefinitionByName(String pdName);
}
    
