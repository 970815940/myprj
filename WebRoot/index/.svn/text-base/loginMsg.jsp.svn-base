<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>你没有登录,或者登录过期 请重新登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" target="text/css" charset="utf-8" href="<%=path %>/index/css/loginMsg.css"/>	
	<script type="text/javascript" src="<%=path%>/jslib/tools/jquery-1.4.js"></script>
	
	<script type="text/javascript">
		var rootPath="<%=path %>";
		var interVal;
		$(function(){
			$("#btn-ok").click(function(){
				window.top.location.replace(rootPath+"/index/login.jsp");
			});
			interVal=window.setInterval(toLoginPage,1000);
		});
		var si=9;
		function toLoginPage(){
			document.getElementById("span-num").innerHTML=si;
			si=si-1;
			if(si==0){
				window.clearInterval(interVal);
				window.top.location.replace(rootPath+"/index/login.jsp");
			}
		}
	</script>
  </head>
  <!-- -

   -->
  <body style="margin: 0px auto;">
   		<table id="table">
   			<tr>
   				<td width="20%">
   				</td>
   				<td valign="middle" align="center" width="60%">
   					 <div id="MsgDiv">你没有登录，或者登录过期 请重新登录，<span id="span-num">10</span>秒后将自动跳转登录页面！<br/>
   					 	 <input id="btn-ok" type="button" value="确定">
   					 </div> 
   				</td>
   				<td width="20%">
   				</td>
   			</tr>
   		</table>
  </body>
</html>
