<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<% String appRootPath=request.getContextPath(); %>
<html>
  <head>
    <title>个人软件作品 登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link  type="text/css" rel="stylesheet" href="<%=appRootPath %>/index/css/jquery-ui.css"/>
	<script type="text/javascript" src="<%=appRootPath%>/index/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=appRootPath%>/index/js/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=appRootPath%>/index/js/login.js"></script>
	
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(<%=appRootPath%>/skins/default/index/images/login_01.gif);
	overflow:hidden;
}
.STYLE3 {font-size: 12px; color: #FFFFFF; }
.STYLE4 {
	color: #FFFFFF;
	font-family: "方正大黑简体";
	font-size: 50px;
}
-->
</style>
<style type="text/css">
    #progressbar .ui-progressbar-value {    background-color: #ccc;  }  
</style>
<script type="text/javascript">
	var LOGIN_URL="<%=appRootPath%>/commonbiz/slalogin.action";
</script>	
  </head>
  <!--  
  <body>
		<form action="/SysPs/commonbiz/login.action" method="post">
	   		<table>
	   			<tr>
	   				<td>用户名</td>
	   				<td><input type="text" name="username"/></td>
	   			</tr>
	   			<tr>
	   				<td>密&nbsp;码</td>
	   				<td><input type="password" name="password"/></td>
	   			</tr>
	   			<tr>
	   				<td><input type="submit"  id="login-btn" value="登录"/></td>
	   			</tr>
	   		</table>		
		</form>
  </body>
  <td width="89.05%" height="22.14%"
  -->
 
<body>
<div id="progressbar"><div class="progress-label"></div></div>
<form id="login-form" action="<%=appRootPath%>/commonbiz/slalogin.action" method="post" >
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td background="<%=appRootPath%>/skins/default/index/images/login_03.gif">&nbsp;</td>
        <td width="876"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="299" valign="top" background="<%=appRootPath%>/skins/default/index/images/login_01.jpg">&nbsp;</td>
          </tr>
          <tr>
            <td height="54"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="394" height="69" background="<%=appRootPath%>/skins/default/index/images/login_02.jpg">&nbsp;</td>
                <td width="199" background="<%=appRootPath%>/skins/default/index/images/login_03.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="22%" height="22"><div align="center"><span class="STYLE3">用户名</span></div></td>
                    <td width="51%" height="22"><input  name="username" id="username" type="text"  style="width: 120px;height:20px;background-color:#032e49; color:#88b5d1; border:solid 1px #88b5d1;" /></td>
                    <td width="27%" height="22">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="22" valign="middle"><div align="center"><span class="STYLE3">密&nbsp; 码</span></div></td>
                    <td height="22" valign="bottom"><input  name="password" id="password" type="password"  style="width: 120px;height:20px;background-color:#032e49; color:#88b5d1; border:solid 1px #88b5d1;" /></td>
                    <td height="22" valign="bottom">&nbsp;</td>
                  </tr>

                  <tr>
                  <!--  
                    <td height="22" valign="middle" class="STYLE3"><div align="center">验证码</div></td>
                    <td height="22" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="36%" height="22"><input name="textfield22" type="text" size="5
						" style="height:20px;background-color:#032e49; color:#88b5d1; border:solid 1px #88b5d1;" /></td>
                        <td width="64%"><img src="<%=appRootPath%>/skins/default/index/images/yzm.gif" width="45" height="16"></td>
                      </tr>
                    </table></td>
                    -->
                    <td height="22" valign="middle">
                    <!-- 
                    <img src="<%=appRootPath%>/skins/default/index/images/dl.gif" width="39" height="17" />
                     -->
                    <input border="0" id="login-btn" type="button" value="登录" style="background:<%=appRootPath%>/skins/default/index/images/dl.gif;width:39px;height:20px;" />
                    </td>
                    <td height="22" valign="middle">
                    <!-- 
                    <img src="<%=appRootPath%>/skins/default/index/images/dl.gif" width="39" height="17" />
                     -->
                    <input border="0" id='reset-btn' type="button" value="重置" style="background:<%=appRootPath%>/skins/default/index/images/dl.gif;width:39px;height:20px;" />
                    </td>                    
                  </tr>
                   
                </table></td>
                <td width="283" background="<%=appRootPath%>/skins/default/index/images/login_04.jpg">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="225" background="<%=appRootPath%>/skins/default/index/images/login_05.jpg">&nbsp;</td>
          </tr>
        </table></td>
        <td background="<%=appRootPath%>/skins/default/index/images/login_03.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
