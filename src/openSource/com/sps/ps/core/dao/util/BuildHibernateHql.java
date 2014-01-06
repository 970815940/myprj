package com.sps.ps.core.dao.util;

import java.io.Serializable;
import com.sps.ps.commonbiz.pk.entity.Primarykey;

/**
 * 构建hql
 * @author taoxs
 *
 */
public class BuildHibernateHql {
	/**
	 * 空字符
	 */
	public static final String BLANK_SPAVE=" ";
	/**
	 * 根据指定属性,逻辑符号,值 构建hql
	 * @param calss_
	 * @param propertys
	 * @param logic
	 * @param values
	 * @return
	 */
	public static String buildQueryHql(Class class_,Serializable[] propertys,String[] logic, Serializable[] values){
		if(class_==null)    return	"";
		StringBuffer str=new StringBuffer("from"+BLANK_SPAVE+class_.getName()+BLANK_SPAVE+"where"+BLANK_SPAVE+"1=1");
		if(propertys==null) return str.toString();
		if(logic==null)		return str.toString();
		if(values==null)    return str.toString();
		
		for (int i = 0; i < propertys.length; i++) {
			str.append(BLANK_SPAVE+"and"+BLANK_SPAVE+propertys[i]+BLANK_SPAVE+logic[i]+BLANK_SPAVE+values[i]);
		}
		return str.toString();
	}
	public static String buildCountHql(Class class_,Serializable[] propertys,String[] logic, Serializable[] values){
		if(class_==null)    return	"";
		StringBuffer str=new StringBuffer("select count(*) from"+BLANK_SPAVE+class_.getName()+BLANK_SPAVE+"where"+BLANK_SPAVE+"1=1");
		if(propertys==null) return str.toString();
		if(logic==null)		return str.toString();
		if(values==null)    return str.toString();
		
		for (int i = 0; i < propertys.length; i++) {
			str.append(BLANK_SPAVE+"and"+BLANK_SPAVE+propertys[i]+BLANK_SPAVE+logic[i]+BLANK_SPAVE+values[i]);
		}
		return str.toString();		
	}
	
	public static void main(String[] args) {
		Serializable[] propertys=new Serializable[]{"name","age"};
		String[] logic=new String[]{"like",">"};
		String[] values=new String[]{"'%张'","20"};
		Primarykey p=new Primarykey();
		String str=buildCountHql(p.getClass(),propertys,logic,values);
		System.out.println(str);
	}
}
