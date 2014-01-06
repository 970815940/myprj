<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录处理页面</title>
</head>

<body onload="show()">

	<%
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		if(name.equals("jack")&& pwd.equals("123456")){
			Cookie useName=new Cookie("useName",name);
			Cookie usePwd=new Cookie("pwd",pwd);
			useName.setMaxAge(300);
			usePwd.setMaxAge(300);
	
			response.addCookie(useName);
			response.addCookie(usePwd);
			//System.out.print(useName.getName());
			//System.out.print(useName.getValue());
			//System.out.print(usePwd.getName());
			//System.out.print(usePwd.getValue());
			request.getRequestDispatcher("welcome.jsp").forward(request, response);	
			//response.sendRedirect("welcome.jsp");
		}
	%>
	
	<script language="javascript">
		var i=3;
		function show(){
			if(i==0){
				location.href="index.html";
			}
			document.getElementById("info").innerHTML=i+"秒后将跳转至登录页面";
			i--;
			setTimeout("show()",1000);
		}
	</script>
    	
    	  	
    	<div id="info"></div>
    	如果没有自动跳转，<a href="index.html">请点击此处</a>
</body>
</html>