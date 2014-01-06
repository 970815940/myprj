package com.test.exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class test {
 public static void main(String[] args) {
	 try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName("com.ibm.db2.jcc.DB2Driver");		
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "mr_tao", "taoxiaosong");
		Connection conn2=DriverManager.getConnection("jdbc:db2://localhost:50000/sysps", "db2admin", "db2admin");
		PreparedStatement pps=conn.prepareStatement("select * from mr_tao.iems_app_ie t where lx='支出' and (ms like '%清爽王fuj%') ");
		ResultSet rs=pps.executeQuery();
		Statement st=conn2.createStatement();
		int row=0;
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str=sf.format(new Date());
		System.err.println(str);
		/**/
		while(rs.next()){
			String lx=rs.getString(2);
			double je=rs.getDouble(3);//金额
			String ms=rs.getString(4);
			Date de=rs.getDate(5);
			//String sql="insert into PS.FINANCE values('"+UUID.randomUUID().toString()+"','/-1/FT_002/FT_004',"+je+","+new Date()+","+de+",'"+ms+"','吃饭','陶小松','USER201300010')";
			String sql="insert into PS.FINANCE values('"+UUID.randomUUID().toString()+"','/-1/FT_002/FT_051/FT_055',"+je+",'"+sf.format(new Date())+"','"+sf.format(de)+"','"+ms+"','旅游','陶小松','USER201300010')";
System.out.println(sql);
			row+=st.executeUpdate(sql);
		}
		System.out.println(row);
		
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
