package com.sps.ps.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * dao层接口
 *<p><b>注意 该接口目前只提供GURD操作</b></p>
 *@author taoxs
 *@since 1.0
 */
public interface GeneralSupportDAO {
	/**
	 * 不分页
	 */
	public final static int QUERY_ALL=-1;
	/**
	 * 持久化一个对象 并返回主键对象
	 * @param obj
	 * @return
	 */
	public Serializable Save(Object obj);
	/**
	 *新增或者修改对象  
	 *<b>如果该对象的主键有值,则执行update 否则执行insert</>
	 * @param obj
	 */
	public void SaveOrUpdate(Object obj);
	/**
	 * 删除一个已持久化对象
	 * @param obj
	 */
	public void Delete(Object obj);
	/**
	 * 根据ID删除指定已持久化对象
	 * @param class_
	 * @param id
	 */
	public void Delete(Class class_,Serializable id);
	/**
	 * 根据属性和属性值  同时删除多个-->指定实体对象
	 * @param class_
	 * @param proerties
	 * @param logic  逻辑运算符
	 * @param value
	 */
	public void Delete( final Class class_, final Serializable[] proerties,final String[] logic, final Serializable[] value);
	/**
	 * 修改一个已持久化对象
	 * @param obj
	 */
	public void Update(Object obj);
	/**
	 * 根据指定对象,指定ID获取对象
	 * @param class_
	 * @param id
	 * @return
	 */
	public Object findById(Class class_,Serializable id);
	/**
	 * <b>获取一页数据</b>
	 * <p>根据指定的实体类型,过滤属性,和过滤逻辑运算符来获取一页数据</p>
	 * @param class_
	 * @param propertys
	 * @param logic
	 * @param values
	 * @param start
	 * @param size
	 * @return
	 */
	public List  findByProperties( final Class class_, final Serializable[] propertys,final String[] logic, final Serializable[] values,int start,int size);
	/**
	 * 根据HQL获取一页数据
	 * @param hql
	 * @param values 过滤条件值
	 * @param start  开始数据行
	 * @param size   获取数据的行数
	 * @return
	 */
	public List  findByHql( final String hql, final String[] values, final int start, final int size);
	/**
	 * 统计数据的总行数
	 * <p></p>
	 * <b>根据指定的类型 ,指定的过滤条件</b>
	 * @param class_
	 * @param propertys
	 * @param logic
	 * @param values
	 * @return
	 */
	public Double countByProperties(final Class class_, final Serializable[] propertys, final String[] logic, final Serializable[] values);
	/**
	 * 统计数据的总行数
	 * <p/>
	 * <b>根据Hql语句过滤数据</b>
	 * @param hql
	 * @param values
	 * @return
	 */
	public double countByHql( final String hql,final String[] values);
	
	/***
	 * 执行HQL语句  不包括查询
	 * @param hql
	 * @param values
	 */
	public int executionHql(final String hql,final String[] values);
	/***
	 * 此方法专门用来处理这样的HQL语句
	 * from SysOrg where soGuid in (:gruids) order by soIndex
	 * @param strs
	 * @return
	 */
	public List executionHql_FOR_IN(String hql,final  String collection,final String[] strs);
	/**
	 * 用来处理这样的多个参数的hql语句
	 * from SysOrg where soGuid in (:gruids) and type =:type order by soIndex
	 * @param hql
	 * @param collections
	 * @param values
	 * @return
	 */
	public List executionHQL_FOR_INs(String hql,final String[] collections,final Object[] values);
}
