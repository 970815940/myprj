<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'attr.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	${SSP_CONTENTHEADER }
	<script type="text/javascript" src="${SSP_CONTEXTPATH }/jslib/Plugins/upload_attachment.js"></script>
	<script type="text/javascript" src="${SSP_CONTEXTPATH}/extDemo/attachment/js/attr.js"></script>

  </head>
  
  <body>
	
  </body>
</html>