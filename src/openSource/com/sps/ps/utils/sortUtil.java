package com.sps.ps.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sps.ps.core.exception.SyspsException;
import com.sps.ps.utils.entity.entity;

/***
 * 
 *<p>ClassName: sortUtil</p>
 *<p>Description:排序帮助类 </p> 
 *<p>Company ：www.nice1234.com</p> 
 * @author   <a href="">taoxs</a>
 * @date Oct 14, 2013
 * @version  1.0
 */
public class sortUtil {
	/**
	 *<p>ClassName: Order_Type</p>
	 *<p>Description: 排序类型  枚举定义</p> 
	 *<p>Company ：www.nice1234.com</p> 
	 * @author   <a href="">taoxs</a>
	 * @date Oct 14, 2013
	 * @version  1.0
	 */
	public enum Order_Type{
		ORDER_DESC,ORDER_ASC
	}
	/**
	 * 倒序
	 */
	public static final String ORDER_DESC="desc";
	/**
	 * 顺序
	 */
	public static final String ORDER_ASC="asc";
	
	public final static String[] classType=new String[]{
			"java.lang.Integer",
			"java.lang.Float",
			"java.lang.String",
			"java.lang.Long",
			"java.lang.Double"
	};
	/**
	 *<p>Title: sort</p>
	 *<p>Description:对集合排序 </p> 
	 * @param lst  集合
	 * @param str  排序的字段
	 * @param order  asc,desc
	 */
	@SuppressWarnings("unchecked")
	public static void sort(List lst, final String str,final String order){
		if(lst==null)throw new NullPointerException("lst is not null！");
		//if(str==null)throw new NullPointerException(" str is not null!");
		//if(order==null||order.equals(""))order=sortUtil.ORDER_ASC;//默认为顺序
		java.util.Collections.sort(lst, new Comparator(){
			public int compare(Object o1, Object o2) {	
				try {
					Field f_o1=o1.getClass().getDeclaredField(str);
					f_o1.setAccessible(true);
					Field f_o2=o1.getClass().getDeclaredField(str);
					f_o2.setAccessible(true);
					Class type=f_o1.getType();
					String o1_value=f_o1.get(o1).toString();
					String o2_value=f_o1.get(o2).toString();
					if(type.getName().equals("java.lang.String")){
						int o1_len=o1_value.length();
						int o2_len=o2_value.length();
						int n=Math.min(o1_len, o2_len);
						char[] o1_arrc=o1_value.toCharArray();  
						char[] o2_arrc=o2_value.toCharArray();
						int index=0;
						while(n--!=0){
							char o1_c=o1_arrc[index];
							char o2_c=o2_arrc[index];
							if(o1_c!=o2_c){
								if(order!=null&&order.equals(sortUtil.ORDER_DESC)){
									return o2_c-o1_c;
								}else{
									return o1_c-o2_c;
								}
							}
							index++;
						}
						return o1_len-o2_len;
					}else if(type.getName().equals("java.lang.Integer")||type.getName().equals("java.lang.Double")
							|| type.getName().equals("java.lang.Float")|| type.getName().equals("java.lang.Long")||
							type.getName().equals("int") ||type.getName().equals("double") ||type.getName().equals("float")
							||type.getName().equals("long")
							){
						Double o1l=Double.parseDouble(o1_value);
						Double o2l=Double.parseDouble(o2_value);
						int b=0;
						if(order!=null&&order.equals(sortUtil.ORDER_DESC)){
							b=(int) (o2l-o1l);
						}else{
							b=(int) (o1l-o2l);
						}
						return b;
					}else{
						throw new RuntimeException("该字段的匹配类型不支持.请自定义扩展");
					}
				} catch (Exception e) {
					throw new RuntimeException(e.toString(),e);
				} 
			}
			
			
		});
	}
	/**
	 *<p>Title: sort</p>
	 *<p>Description:  对数组进行排序</p> 
	 * @param att
	 * @param qt
	 */
	@SuppressWarnings("unchecked")
	public static void sort(final Object[] arr ,final Order_Type qt){
		java.util.Arrays.sort(arr, new  Comparator(){
			List lst=Arrays.asList(classType);
			public int compare(Object o1, Object o2) {
				if(lst.contains(o1.getClass().getName())){
					String o1_value=o1.toString();
					String o2_value=o2.toString();
					if(o1.getClass().getName().equals("java.lang.String")){
						int o1_len=o1_value.length();
						int o2_len=o2_value.length();
						int n=Math.min(o1_len, o2_len);
						char[] o1_arrc=o1_value.toCharArray();  
						char[] o2_arrc=o2_value.toCharArray();
						int index=0;
						while(n--!=0){
							char o1_c=o1_arrc[index];
							char o2_c=o2_arrc[index];
							if(o1_c!=o2_c){
								if(qt!=null&&qt.equals(qt.ORDER_DESC)){
									return o2_c-o1_c;
								}else{
									return o1_c-o2_c;
								}
							}
							index++;
						}
						return o1_len-o2_len;
					}else{//数字
						double o1_v=Double.parseDouble(o1_value);
						double o2_v=Double.parseDouble(o2_value);
						if(qt!=null&&qt.equals(Order_Type.ORDER_DESC)){
							return (int)(o2_v-o1_v);
						}else{
							return (int)(o1_v-o2_v);
						}
					}
				}else{//暂未实现代码 。。。。。。。。。。。。。。。。
					
				}
				return 0;
			}
			
		});
	}
/*
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List l=new ArrayList();
		String[] arr=new String[10];
		for (int i = 0; i <10; i++) {
			user user=null;
			if(i%2==0){
				arr[i]="a"+i;
				user=new user("aName"+i,i);
			}else{
				arr[i]="b"+i;
				user=new user("bName"+i,i);
			}
			l.add(user);
			
		}
		//sortUtil.sort(l, "name", sortUtil.ORDER_DESC);
		sortUtil.sort(arr, Order_Type.ORDER_ASC);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	*/
	
}
