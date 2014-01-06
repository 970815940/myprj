package com.sps.ps.commonbiz.security.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.sps.ps.commonbiz.security.dao.ModelEntityIDAO;
import com.sps.ps.commonbiz.security.entity.ModelEntity;
import com.sps.ps.core.dao.impl.GeneralHibernateDAOImpl;

public class ModelEntityDAOImpl extends GeneralHibernateDAOImpl implements ModelEntityIDAO {

	public void addModelEntity(ModelEntity me) {
		super.Save(me);
		
	}

	public void deleteModelEntity(ModelEntity me) {
		super.Delete(me);
		
	}

	public ModelEntity getModelEntityById(String id) {
		Object obj=super.findById(ModelEntity.class, id);
		return (ModelEntity) obj;
	}

	public List getModelEntityByList(String hql, String[] values, int start,
			int size) {
		
		return super.findByHql(hql, values, start, size);
	}

	public void updateModelEntity(ModelEntity me) {
		super.Update(me);
		
	}

	public int countModelEntityByList(String hql, String[] values) {
		double rowcount=super.countByHql(hql, values);
		return (int) rowcount;
	}

	public void deleteModelEntitys(String hql, String[] values) {
		super.executionHql(hql, values);
		
	}
	public List getUnAuthorize(final String hql,final List lst) {
		return super.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				try {
					Query query=session.createQuery(hql);
					if(lst==null||lst.size()==0){
						
					}else{
						query.setParameterList("meIds", lst);
					}
					return query.list();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
		});
	}	

}
