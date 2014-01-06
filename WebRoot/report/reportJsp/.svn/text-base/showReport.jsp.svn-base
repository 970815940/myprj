<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>

<html>
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0>
<%
	//request.setCharacterEncoding( "GBK" );
	//获取当前登录用户信息
	String report = request.getParameter( "raq" );
	String reportFileHome=Context.getInitCtx().getMainDir();
	StringBuffer param=new StringBuffer();
	String isout="";
	if(null!=request.getParameter("isout"))
		isout = request.getParameter("isout");
	
	String newurl = "yes";
	if(isout.equals("yes"))
		newurl="/report/reportJsp/showReport.jsp?";
	
	
	int iTmp = 0;
	if( (iTmp = report.lastIndexOf(".raq")) <= 0 ){
		report = report + ".raq";
		iTmp = 0;
	}
	
	Enumeration paramNames = request.getParameterNames();
	if(paramNames!=null){
		while(paramNames.hasMoreElements()){
			String paramName = (String) paramNames.nextElement();
		}
	}

	//System.out.println(param);

	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));
	
%>
<jsp:include page="toolbar1.jsp" flush="false" />
<table id=rpt align=center><tr><td>
<%	//������ģ����ڣ�����ʾ����ģ��?
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

<table align=center>
	<tr><td>
		<report:html name="report1" reportFileName="<%=report%>"
			funcBarLocation="no"
			needImportExcel="yes"
			importExcelLabel="选择excel"
			needPageMark="yes"
			generateParamForm="no"
			backAndRefresh="<%=newurl %>"
			params="<%=param.toString()%>"
			width="-1"
			exceptionPage="/report/reportJsp/myError2.jsp"
		/>
	</td>
	</tr>
</table>

<script language="javascript">
	//隐藏报表下方授权信息
	var divEles = document.getElementsByTagName("div");
	for(var i = 0;i<divEles.length;i++){
		if(divEles[i].firstChild && divEles[i].firstChild.nodeValue=="�?){
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

