<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.URLDecoder"%>
<%@ taglib uri="/WEB-INF/configs/report/runqianReport4.tld" prefix="report" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body topmargin="0" leftmargin="0" rightmargin="0" bottomMargin="0">
<%
	//request.setCharacterEncoding( "UTF-8" );
	String report = request.getParameter( "raq" );
	String reportFileHome= Context.getInitCtx().getMainDir();
	StringBuffer param=new StringBuffer();
	//保证报表名称的完整性
	int iTmp = 0;
	if( (iTmp = report.lastIndexOf(".raq")) <= 0 ){
		report = report + ".raq";
		iTmp = 0;
	}
	
	Enumeration paramNames = request.getParameterNames();
	if(paramNames!=null){
		while(paramNames.hasMoreElements()){
			String paramName = (String) paramNames.nextElement();
			//request.setCharacterEncoding()		
			request.getCharacterEncoding();
			String paramValue=request.getParameter(paramName);
			if(paramValue!=null){
				//把参数拼成name=value;name2=value2;.....的形式
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}
%>
<jsp:include page="toolbar.jsp" flush="false" />
<table  align="center">
	<tr><td>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation="top"
			needImportExcel="yes"
			importExcelLabel="选择excel"
			params="<%=param.toString()%>"
			importExcelAppend="no"
			exceptionPage="/report/reportJsp/myError2.jsp"
		/>
	</td></tr>
</table>

<script language="javascript">
	//设置分页显示值
	document.getElementById( "t_page_span" ).innerHTML=report1_getTotalPage();
	document.getElementById( "c_page_span" ).innerHTML=report1_getCurrPage();


</script>

</body>
</html>
