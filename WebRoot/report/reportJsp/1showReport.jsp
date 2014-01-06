<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="com.sysway.syssill.utils.URLUtil"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
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
			String paramValue=URLUtil.paramDecode(request.getParameter(paramName));
			if(paramValue!=null){
				//把参数拼成name=value;name2=value2;.....的形式
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}

	//以下代码是检测这个报表是否有相应的参数模板
	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));

%>
<jsp:include page="toolbar.jsp" flush="false" />
<table id=rpt  align="center"><tr><td>
<%	//如果参数模板存在，则显示参数模板
	if( f.exists() ) {
	%>
	<table id=param_tbl><tr><td>
		<report:param name="form1" paramFileName="<%=paramFile%>"
			needSubmit="no"
			params="<%=param.toString()%>"
			
		/>
	</td>
	<td><a href="javascript:_submit( form1 )"><img src="../images/query.jpg" border=no style="vertical-align:middle"></a></td>
	</tr></table>
	<% }
%>

<table>
	<tr><td>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation="top"
			needPageMark="yes"
			generateParamForm="no"
			pageMarkLabel=""
			firstPageLabel=""
			prevPageLabel=""
			nextPageLabel=""
			lastPageLabel=""
			params="<%=param.toString()%>"
			width="-1"
			needScroll="no"
			exceptionPage="report/reportJsp/myError2.jsp"
		/>
	</td></tr>
</table>

<script language="javascript">
	//设置分页显示值
	document.getElementById( "t_page_span" ).innerHTML=report1_getTotalPage();
	document.getElementById( "c_page_span" ).innerHTML=report1_getCurrPage();
	//隐藏授权信息
	var tbl = document.getElementById("table");
	var  a = document.getElementsByTagName("td");    
	for(var i=0;i<a.length;i++){
		a[i].onmouseover="";
	}
	//隐藏报表下方授权信息
	var divEles = document.getElementsByTagName("div");
	for(var i = 0;i<divEles.length;i++){
		if(divEles[i].firstChild && divEles[i].firstChild.nodeValue=="数"){
			divEles[i].style.display="none";
		}
	}
	
	//隐藏提示tip
	if(parent.qt){
		parent.hideTip();
	}
</script>

</body>
</html>
