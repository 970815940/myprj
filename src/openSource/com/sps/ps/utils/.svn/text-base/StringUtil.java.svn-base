/*
 * String.java 1.0
 * 2013-8-16
 * Copyrihgt 2013 ps, Inc. All rights reserved.
 */
package com.sps.ps.utils;

import java.util.Date;

/**
 * 提供字符串操作的工具类
 *@author peach_mr@sina.com
 *@since 1.0
 */
public final class StringUtil {
	/**
	 * 字符串填充方向,向右填充
	 */
	public final static int FILL_RIGTH = 1;
	
	/**
	 * 字符串填充方向,向左填充
	 */	
	public final static int FILL_LEFT = 2;
	public static java.lang.String tostring(Integer integer){
			if(integer==null)return "";
			return java.lang.String.valueOf(integer);
	}
	public static java.lang.String tostring(Double integer){
		if(integer==null)return "";
		return java.lang.String.valueOf(integer);
	}
	public static java.lang.String tostring(Float integer){
		if(integer==null)return "";
		return java.lang.String.valueOf(integer);
	}
	public static java.lang.String tostring(Date integer){
		if(integer==null)return "";
		return java.lang.String.valueOf(integer);
	}	
	/**
	 * 判断str是否为NULL或者为空  
	 * <p>如果返回值=true则不为空，false则为空</p>
	 * @param str
	 * @return  boolean
	 */
	public static boolean isEmpty(java.lang.String str){
		if(str==null||str.equals("")) return false;
		return true;
	}
	/**
	 * 截取字符串  <p>从start开始 len结束</p>
	 * @param str
	 * @param start
	 * @param len
	 * @return
	 */	
	public static java.lang.String substr(java.lang.String str,int start,int len){
		if(!isEmpty(str)) return "";
		java.lang.String  str_tmp=str.substring(start, len);
		return str_tmp;
	}
	/**
	 * 截取字符串  <p>从start开始 len结束</p>
	 * @param str
	 * @param start
	 * @return
	 */
	public static java.lang.String substr(java.lang.String str,int start){
		if(!isEmpty(str)) return "";
		String str_tmp=str.substring(start);
		return str_tmp;
	}
	/**
	 * 连接字符串   用separator拼接字符串
	 * @param str[]
	 * @param separator
	 * @return
	 */
	public static String join(String[] str,String separator){
		if(str==null) return "";
		StringBuffer sb_str=new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sb_str.append(str[i]);
			if(str.length-1!=i){
				sb_str.append(separator);
			}
		}
		return sb_str.toString();
	}
	/**
	 * 把数据连接成字符串
	 * @param str
	 * @return
	 */
	public static String join(String[] str){
		if(str==null) return "";
		 return join(str,"");
	}
	/**
	 * 拆分字符串 成数组 >>根据separator
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String[] split(String str,String separator){
		if(isEmpty(str)) return new String[]{};
		return str.split(separator);
	
	}
	public static String fillString(String src,char fillchar,int length,int description) {
		if(src == null) {
			return "";
		}
		if(length<0 ) {
			return src;
		}
		String tagert = src;
		switch(description) {
			case 1 :
			{
				int len = tagert.length();
				while(len++ < length) {
					tagert += fillchar;
				}
				break;
			}
			case 2 :
			{
				int len = tagert.length();
				while(len++ < length) {
					tagert = fillchar +  tagert;
				}
				break;
			}				
		}		
		return tagert;
	}	
	
	public static void main(String[] args) {
		String aa="{url}/jslib/aa.js";
		aa=aa.replaceAll("\\{url\\}", "http://127.0.0.1:8080/iems");
	
		System.out.println(aa);
	}

	
}
