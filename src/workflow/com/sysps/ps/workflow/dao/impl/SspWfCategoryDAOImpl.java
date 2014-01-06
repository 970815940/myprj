/* SspWfCategoryDAOImpl.java 
 * Dec 18, 2013  11:22:33 AM
 * Copyright 2013. All rights reserved.
 * 
 *
 * taoxs
 */
package com.sysps.ps.workflow.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;
import com.sysps.ps.workflow.dao.SspWfCategoryIDAO;
import com.sysps.ps.workflow.entity.SspWfCategory;

/**
 *<p>SspWfCategoryDAOImpl</p>
 *<p></p> 
 *<p></p> 
 * @author<a href="taoxs@sysway.cn">taoxs</a>
 * @date Dec 18, 2013 11:22:33 AM
 * @version  
 */
public class SspWfCategoryDAOImpl extends GeneralHibernateDAOImpl implements SspWfCategoryIDAO {

	public void addSspWfCategory(SspWfCategory cate) {
		super.Save(cate);

	}

	public int countBySspWfCategory(String hql, String[] values) {
		int count=(int)super.countByHql(hql, values);
		return count;
	}

	public void deleteSspWfCategory(SspWfCategory cate) {
		super.Delete(cate);

	}

	public List getBySspWfCategory(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateSspWfCategory(SspWfCategory cate) {
		super.Update(cate);

	}

	public SspWfCategory getSspWfCategoryById(Serializable id) {
		
		return (SspWfCategory)super.get(SspWfCategory.class, id);
	}

}
    
