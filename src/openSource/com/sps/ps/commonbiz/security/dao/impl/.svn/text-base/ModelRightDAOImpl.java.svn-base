package com.sps.ps.commonbiz.security.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sps.ps.commonbiz.security.dao.ModelRightIDAO;
import com.sps.ps.commonbiz.security.entity.ModelRight;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class ModelRightDAOImpl extends GeneralHibernateDAOImpl implements
							ModelRightIDAO{

	public void addModelRight(ModelRight mr) {
		super.Save(mr);
		
	}

	public void deleteModelRight(ModelRight mr) {
		super.Delete(mr);
		
	}

	public ModelRight getModelRightById(String id) {
		Object obj=super.findById(ModelRight.class, id);
		return obj==null?null:(ModelRight)obj;
	}

	public List getModelRightByList(String hql, 
			String[] values, int start, int limit) {
		
		return super.findByHql(hql, values, start, limit);
	}

	public int getModelRightBycount(String hql, 
			String[] values) {
		int count=(int) super.countByHql(hql, values);
		return count;
	}

	public void updateModelRight(ModelRight mr) {
		super.Update(mr);
		
	}

	public List getMRFor_HQL_IN(String hql, String[] collections,
			Object[] values) {
		
		return super.executionHQL_FOR_INs(hql, collections, values);
	}

	public void deleteMRguidAndMeId(final String hql, final String[] meIds) {
		super.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				if(meIds!=null&&meIds.length!=0){
					query.setParameterList("meIds", meIds);
				}
				int rows=query.executeUpdate();
				System.out.println("取消授权=====受影响的行数有:"+rows);
				return null;
			}
			
		});
		
	}
	/**
	 * 根据条件删除权限数据
	 * @param hql
	 */
	public void deleteModelRightDels(final String hql){
		super.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				int rows=query.executeUpdate();
				System.out.println(hql+"【"+rows+"】受影响");
				return rows;
			}
			
		});
	}



}
