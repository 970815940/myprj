package com.test.exception;

public class t {

	/**
	 *<p>Title: main</p>
	 *<p>Description: </p> 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//		int number=[]={1,3,4,5,};
		Object o=new Float(3.14F);
		Object[] oa=new Object[1];
		oa[0]=o;
		System.out.println(oa[0]);
		o=null;
		System.out.println(oa[0]);
	}

}
