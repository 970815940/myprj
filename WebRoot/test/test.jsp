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
	<script type="text/javascript" src="${SSP_CONTEXTPATH}/jslib/tools/GridWindow.js"></script>
 <script language="javascript">
Ext.onReady(function(){


	var win=new Ext.ux.GridWindow({
		url:Ext.appRootPath+"/commonbiz/ssgetSysStationByList.action",
		cm: [
			{header:'guid',dataIndex:'guid'},
			{header:'ssDispalyname',daatIndex:'ssDispalyname'},
			{header:'ssDemo',dataIndex:'ssDemo'}
		],
		 title: '选择人员',
		 width:300,
		 height:300
	});
	win.show();
	});
</script>



 
  </head>
  
  <body>
	<div id="ee"></div>
  </body>
</html>
