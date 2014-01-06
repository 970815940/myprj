package com.test.exception;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class main  {
	public static void firstException() throws myException{
		throw  new myException("第一个异常产出了");
		
	}
	public static void twoException() throws myException{
		throw new myException("第二个一次产出了");
	}
	/*public static void main(String[] args) {
		try {
			firstException();
			twoException();
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
    public   static   void   changeStr(int   str){  

        str=312;
        System.out.println(str+"~");

}  

	public   static   void   main(String[]   args)   {  
	
	        int str=1;
	
	        changeStr(str);  
	
	        System.out.println(str);  
	
	}  


	
}
