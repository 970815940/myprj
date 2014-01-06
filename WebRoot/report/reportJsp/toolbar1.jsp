<%@ page contentType="text/html;charset=GBK" %>
<%	String appmap = request.getContextPath();
	String printImage = "<img src='" + appmap + "/report/images/print.png' border=no >";
	String excelImage = "<img src='" + appmap + "/report/images/excel.png' border=no >";
	String submitImage = "<img src='" + appmap + "/report/images/save.png' border=no >";
	String inexcelImage = "<img src='" + appmap + "/report/images/import.png' border=no >";
	String appendRow = "<img src='" + appmap + "/report/images/add.png' border=no >";
	String deleteRow = "<img src='" + appmap + "/report/images/del.png' border=no >";
%>

<table id=titleTable width=100% cellspacing=0 cellpadding=0 border=0 ><tr>
	<td height="22" width=100% valign="middle"  style="font-size:13px" background="../images/tools-bg.gif">
		<table width="100%">
			<tr >
				<td align="right"  style="font-size:13px">
					<a href="#" Title="打印" onClick="report1_print();return false;"><%=printImage%></a>
					<a href="#" Title="导出excel" onClick="report1_saveAsExcel();return false;"><%=excelImage%></a>
					<a href="#" Title="导入excel" onClick="report1_importExcel();return false;"><%=inexcelImage%></a>
					<a href="#" Title="添加行" onClick="_appendRow( report1 )"><%=appendRow%></a>
					<a href="#" Title="删除行" onClick="_deleteRow( report1 )"><%=deleteRow%></a>
					<a href="#" Title="保存" onClick="_submitRowInput( report1 );return false;"><%=submitImage%></a>
			  	</td>
			</tr>
	  </table>
	</td>
</table>