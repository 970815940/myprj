<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.sysway.syssill.utils.URLUtil"%>
<%@ taglib uri="/WEB-INF/runqianReport4.tld" prefix="report" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.runqian.report4.usermodel.Context"%>
<%@ page import="com.sysway.syssill.commonbiz.security.ctrl.LoginUserContext"%>
<%@ page import="com.sysway.syssill.commonbiz.security.entity.LoginUser"%>

<html>
<body topmargin=0 leftmargin=0 rightmargin=0 bottomMargin=0>

<%
	//request.setCharacterEncoding( "GBK" );
	//获取当前登录用户信息
	LoginUser user = (LoginUser)session.getAttribute(LoginUserContext.LOGINUSER_STORE_KEY);
	String report = request.getParameter( "raq" );
	String reportFileHome=Context.getInitCtx().getMainDir();
	StringBuffer param=new StringBuffer();
	
	//��֤������Ƶ�������
	int iTmp = 0;
	if( (iTmp = report.lastIndexOf(".raq")) <= 0 ){
		report = report + ".raq";
		iTmp = 0;
	}
	
	Enumeration paramNames = request.getParameterNames();
	if(paramNames!=null){
		while(paramNames.hasMoreElements()){
			String paramName = (String) paramNames.nextElement();
			String paramValue=request.getParameter(paramName);
			if(paramValue!=null){
				//�Ѳ���ƴ��name=value;name2=value2;.....����ʽ
				param.append(paramName).append("=").append(paramValue).append(";");
			}
		}
	}
		//参数中加入当前用户部门
	param.append("psnid").append("=").append(user.getUserGuid()).append(";");
	param.append("wrpdepid").append("=").append(user.getOrgDepId()).append(";");
	//���´����Ǽ�������Ƿ�����Ӧ�Ĳ���ģ��
	String paramFile = report.substring(0,iTmp)+"_arg.raq";
	File f=new File(application.getRealPath(reportFileHome+ File.separator +paramFile));

%>

<table id=rpt align=center><tr><td>
<%	//������ģ����ڣ�����ʾ����ģ��
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
			submit=""
			needPageMark="no"
			generateParamForm="no"
			params="<%=param.toString()%>"
			width="-1"
			exceptionPage="/report/reportJsp/myError2.jsp"
		/>
	</td></tr>
</table>

<script language="javascript">
		//隐藏授权信息
	var tbl = document.getElementById("table");
	var  a = document.getElementsByTagName("td");    
	for(var i=0;i<a.length;i++){
		a[i].onmouseover="";
	}
	
	var tb = document.getElementById("param_tbl");
	if(tb!=null)
		tb.style.display="none";
	function showParam(){
		if(tb!=null)
			return tb.style.display="";
	}
	//隐藏报表下方授权信息
	var divEles = document.getElementsByTagName("div");
	for(var i = 0;i<divEles.length;i++){
		if(divEles[i].firstChild && divEles[i].firstChild.nodeValue=="渝"){
			divEles[i].style.display="none";
		}
	}

</script>
</body>
</html>
