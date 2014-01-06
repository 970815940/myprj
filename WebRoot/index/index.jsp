<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${SSP_TITLE }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	${SSP_CONTENTHEADER}
	<link rel="stylesheet" type="text/css" href="${SSP_CONTEXTPATH }/index/css/index.css"/>
	<script type="text/javascript">	
		var ORGGUID='${SSP_LOGINUSER.guid }';var userName1='${SSP_LOGINUSER.userName }';
	
	</script>
	<script type="text/javascript" src="${SSP_CONTEXTPATH }/index/js/indexFun.js"></script>
	<script type="text/javascript" src="${SSP_CONTEXTPATH}/jslib/miframe.js"></script>
	<script type="text/javascript" src="${SSP_CONTEXTPATH}/index/js/index.js"></script>
  </head>
  
  <body>
  	
   		<div align="right" id="global-header" style="background-repeat: no-repeat;" >
   			<div id="InfoMsg" style="font-size: 13px;">
   				<div><span><a href="javascript:;;">用户名：</a></span><span style="color:#CC0;font-weight:border;" id="userInfo"></span></div>
   				<div><span><a href="javascript:Exit()">注销</a></span><span></span></div>
   				<div><span><a href="javascript:modifyPas()">修改密码</a></span><span></span></div>
   			</div>
   		
   		</div>
   		<div id="global-left"></div>
   		<div id="global-center"></div>
  </body>
</html>
