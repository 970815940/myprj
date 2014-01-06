/*
 * GeneralHibernateDAOImpl.java 1.0
 * 2013-8-13 21:31
 */
package com.sps.ps.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sps.ps.core.dao.GeneralSupportDAO;
import com.sps.ps.core.dao.util.BuildHibernateHql;
/**
 * <p>系统dao层实现类<p>
 * <p>更新情况：</p>
 * 2013-9-10 20：00 新增 方法executionHql_FOR_IN();为了使用from SysOrg where soGuid in (:gruids) order by soIndex样式的HQL语句
 *                         executionHQL_FOR_INs  处理多个参数
 * @author taoxs
 * @since 1.0
 *
 */
public abstract class GeneralHibernateDAOImpl extends HibernateTemplate implements
												GeneralSupportDAO{

	public List executionHQL_FOR_INs(final String hql,final String[] collections,
			final Object[] values) {
		return this.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Query query=session.createQuery(hql);
					if(collections!=null||collections.length!=0){
						return findByNamedParam(hql, collections, values);
					}
					return null;
			}
		});
	}

	public List executionHql_FOR_IN(final String hql ,final  String collection,final  String[] strs) {
		return this.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Query query=session.createQuery(hql);
					if(collection!=null||!collection.equals("")){
						query.setParameterList(collection, strs);
					}
					
					return query.list();
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.sps.ps.core.dao.GeneralSupportDAO#executionHql(java.lang.String, java.lang.String[])
	 */
	public int executionHql(final String hql,final  String[] values) {
		Object obj=  super.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				if(values!=null){
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				int rows=query.executeUpdate();
				return String.valueOf(rows);
			}
			
		});
		String rows=obj.toString();
		return Integer.parseInt(rows);
	}

	public void Delete(Object obj) {
		super.delete(obj);
		
	}

	public void Delete(Class class_, Serializable id) {
		Object obj=this.findById(class_, id);
		delete(obj);
	}

	public void Delete(Class class_, Serializable[] proerties, String[] logic,
			Serializable[] value) {
			
		
	}

	public void Update(Object obj) {
		super.update(obj);
		
	}

	public Serializable Save(Object obj) {
		return super.save(obj);
	}

	public void SaveOrUpdate(Object obj) {
		super.saveOrUpdate(obj);
		
	}

	public double countByHql(final String hql,final  String[] values) {
		 return  (Double) super.execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					double rowCount=0;
					Query query=session.createQuery(hql);
					if(values!=null){
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
					}
					Iterator it=query.iterate();
					while (it.hasNext()) {
						Object obj= it.next();
						if(obj instanceof Integer){
							rowCount=((Integer)obj).doubleValue();
						}else if(obj instanceof Double){
							rowCount=((Double)obj).doubleValue();
						}else if(obj instanceof Long){
							rowCount=((Long)obj).doubleValue();
						}
						break;
					}
					return rowCount;
				}
			});
	}

	public Double countByProperties(final Class class_,final Serializable[] propertys,
			final String[] logic,final Serializable[] values) {
		 return  (Double) super.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				double rowCount=0;
				String hql=BuildHibernateHql.buildCountHql(class_,propertys,logic,values);
				Query query=session.createQuery(hql);
				Iterator it=query.iterate();
				while (it.hasNext()) {
					Object obj= it.next();
					if(obj instanceof Integer){
						rowCount=((Integer)obj).doubleValue();
					}else if(obj instanceof Double){
						rowCount=((Double)obj).doubleValue();
					}else if(obj instanceof Long){
						rowCount=((Long)obj).doubleValue();
					}
					break;
				}
				return rowCount;
			}
		});
		
	}

	public List findByHql(final String hql,final String[] values,final int start,final int size) {
		return super.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				if(values!=null){
					for (int i = 0; i < values.length; i++) {
						query.setParameter(i, values[i]);
					}
				}
				if(start!=-1){
					query.setFirstResult(start);
					query.setMaxResults(size);
				}
				List list=query.list();
				return list;
			}
			
		});
	}

	public Object findById(Class class_, Serializable id) {
		Object obj=super.get(class_, id);
		return obj;
	}

	public List findByProperties(final Class class_,final Serializable[] propertys,
			final String[] logic,final Serializable[] values,final int start, final int size) {
		return super.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql=BuildHibernateHql.buildQueryHql(class_, propertys, logic, values);
				Query query=session.createQuery(hql);
				if(start!=-1){
					query.setFirstResult(start);
					query.setMaxResults(size);
				}
				List list=query.list();
				return list;
			}
		});
		
	}



}
