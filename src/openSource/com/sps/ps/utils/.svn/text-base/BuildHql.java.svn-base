package com.sps.ps.utils;

public class BuildHql {
	/**
	 * 构建HQL 条件
	 * @param columns
	 * @param ysf
	 * @return
	 */
	public static String createHql(String[] columns,String []ysf){
		StringBuffer sb=new StringBuffer();
		if(columns==null)return"";
		for (int i = 0; i < columns.length; i++) {
			sb.append(" and "+columns[i]+" "+ysf[i]+"?");
		}
		
		return sb.toString();
	}
}
