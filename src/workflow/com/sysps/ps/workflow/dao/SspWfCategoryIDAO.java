/* SspWfCategoryIDAO.java 
 * Dec 18, 2013  11:15:50 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.dao;

import java.util.List;

import com.sysps.ps.workflow.entity.SspWfCategory;

/**
 *<p>SspWfCategoryIDAO</p>
 *<p></p> 
 *<p></p> 
 * @author<a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 18, 2013 11:15:50 AM
 * @version  
 */
public interface SspWfCategoryIDAO {
	public void addSspWfCategory(SspWfCategory cate);
	public void updateSspWfCategory(SspWfCategory cate);
	public int countBySspWfCategory(String hql,String[] values);
	public List getBySspWfCategory(String hql,String[] values,int start,int size);
	public void deleteSspWfCategory(SspWfCategory cate);
	public SspWfCategory getSspWfCategoryById(java.io.Serializable id);
}
    
