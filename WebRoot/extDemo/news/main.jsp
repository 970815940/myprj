<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="CSS/main.css" type="text/css" />
<title>新闻系统首页</title>
</head>

<body>
	<page>
    	<div id="header">
        	<div id="top_login">
           		<form id="myForm" action="login.jsp" method="post">
                	登录名<input type="text" class="login_input" name="name"/>
                	密码<input type="password" class="login_input"name="pwd"/>
                	<input class="login_sub" type="submit" value="登录" onclick="login()"/>
                    <a class="login_link" href="#"><img id="friend_logo" src="Images/friend_logo.gif"/></a>
                </form>
            </div>
            <div id="nav">
                <div id="logo"><img src="Images/logo.jpg"/></div>
                <div id="mainnav" style="width:790px">
                        <a href="#"><img src="Images/a_b01.gif"/></a>                       
                </div>
            </div>
        </div>
        
        <div id="container">
        	<div class="sidebar">
            	<h1><img src="Images/title_1.gif" /></h1>
                <div class="side_list">
                	<li><a href="#">重庆涉黑富豪黎强夫妇庭审答辩言辞相互矛盾</a></li>
                    <li><a href="#">发改委：4万亿投资计划不会挤占民间投资空间</a></li>
                    <li><a href="#">河南两个乡镇政绩报告内容完全一致引关注</a></li>
                    <li><a href="#">重庆警方否认被围殴致死吸毒者曾针刺民众</a></li>
                    <li><a href="#">人保部将首次就同工同酬做出规定</a></li>
                    <li><a href="#">国台办将授权福建等六省市部分赴台管理审批权</a></li>
                </div>
            
            	<h1><img src="Images/title_2.gif" /></h1>
                <div class="side_list">
                	<li><a href="#">重庆涉黑富豪黎强夫妇庭审答辩言辞相互矛盾</a></li>
                    <li><a href="#">发改委：4万亿投资计划不会挤占民间投资空间</a></li>
                    <li><a href="#">河南两个乡镇政绩报告内容完全一致引关注</a></li>
                    <li><a href="#">重庆警方否认被围殴致死吸毒者曾针</a></li>
                    <li><a href="#">人保部将首次就同工同酬做出规定</a></li>
                    <li><a href="#">国台办将授权福建等六省市部分赴台管理审批权</a></li>
                </div>
            
            	<h1><img src="Images/title_3.gif" /></h1>
                <div class="side_list">
                	<li><a href="#">重庆涉黑富豪黎强夫妇庭审答辩言辞相互矛盾</a></li>
                    <li><a href="#">发改委：4万亿投资计划不会挤占民间投资空间</a></li>
                    <li><a href="#">河南两个乡镇政绩报告内容完全一致引关注</a></li>
                    <li><a href="#">重庆警方否认被围殴致死吸毒者曾针刺民众</a></li>
                    <li><a href="#">人保部将首次就同工同酬做出规定</a></li>
                    <li><a href="#">国台办将授权福建等六省市部分赴台管理审批权</a></li>
                </div>
            </div>
            
            <div class="main">
            	<div class="class_type"><img src="Images/class_type.gif"/></div>
                <div class="content">
                	<div class="class_date">
                    	<div id="class_month">
                        	<a href="#">国内</a>
                            <a href="#">国际</a>
                            <a href="#">军事</a>
                            <a href="#">体育</a>
                            <a href="#">娱乐</a>
                            <a href="#">社会</a>
                            <a href="#">财经</a>
                            <a href="#">科技</a>
                            <a href="#">健康</a>
                            <a href="#">汽车</a>
                            <a href="#">教育</a>
                            <a href="#">房产</a>
                            <a href="#">家居</a>
                            <a href="#">旅游</a>
                            <a href="#">文化</a>
                            <a href="#">其他</a>
                        </div>
                        <div id="day"></div>
                    </div>
                    <div class="classlist">
                    	<li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space"></li>
                        <li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space"></li>
                        <li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space"></li>
                        <li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space"></li>
                        <li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space"></li>
                        <li><a href="#">测试了可以修改</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">测试后证明没有错误了吧</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">深足教练组：说我们买球是侮辱 朱广沪常暗中支招</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">省政府500万悬赏建业登顶 球员：遗憾主场放跑西安</a><span>2009-10-28 01:02:56.0</span></li>
                        <li><a href="#">洪元硕：北京人的脸就看你们了 最后一哆嗦看着办</a><span>2009-10-28 01:02:56.0</span></li>
                        <li class="space">&quot;</li>
                    </div>
                </div>
                <div class="picnews">
                	<li><img src="Images/Picture1.jpg"/></li>
                    <li><a href="#">幻想中穿越时空</a></li>
                    <li><img src="Images/Picture2.jpg"/></li>
                    <li><a href="#">国庆多变的发型</a></li>
                    <li><img src="Images/Picture3.jpg"/></li>
                    <li><a href="#">新技术照亮都市</a></li>
                    <li><img src="Images/Picture4.jpg"/></li>
                    <li><a href="#">群星闪耀红地毯</a></li>
                </div>
            </div>
        </div>
   		
        <div id="friend">
        	<div class="friend_t"><img src="Images/friend_ico.gif"/></div>
            <div class="friend_list">
            	<ul>
                	<li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                    <li><a href="#">中国政府网</a></li>
                </ul>
            </div>
        </div>
        
        <div id="footer">
        	<p>24小时客户服务热线：&nbsp;&nbsp;010-68988888&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">常见问题解答</a>&nbsp;&nbsp;&nbsp;&nbsp;新闻热线：&nbsp;&nbsp;010-62748888&nbsp;&nbsp;</p>
            <p>文明办网文明上网举报电话：&nbsp;010-627488888&nbsp;&nbsp;&nbsp;举报邮箱：&nbsp;<a href="#">jubao@jb-aptech.com.cn</a>&nbsp;&nbsp;</p>
            <p class="copyright">Copyright&nbsp;&copy;1999-2009 News China Gov,All Right Reserver<br/>新闻中国 版权所有</p>
        </div>
    </page>
    <% 
  			Cookie[] cookies = request.getCookies();	//获取cookie 
			if(cookies!=null){ 
				for(int i=0;i<cookies.length;i++){ 
					if(cookies[i].getName().equals("useName")){ 
							
					} 
				} 
			} 
	%>
	<script  type="text/javascript"  src=" jq/jquery-1.9.1.min.js " ></script>
	<script type="text/javascript"  src="jq.cookie/jquery.cookie.js"></script>
   <script language="javascript">
			var reg=/^\w+$/;
			
			$(document).ready(function($){
				
					alert("cookie名称"+$.cookie("useName"));
					if($.cookie(useName) != null && $.cookie(usePwd)!=null){
						$('input').first().value=$.cookie(useName);
						$('input').first().next().value=$.cookie(usePwd);
					}
					$(".login_sub").trigger("onclick");
				
			});
	
		document.forms[0].onsubmit=function login(){
			var nameObj=document.getElementsByName("name");
			var pwdObj=document.getElementsByName("pwd");
		
			if(reg.test(nameObj[0].value)==false && reg.test(pwdObj[0].value)==false){
				alert("请输入用户名和密码！");
				return false;
			}
			if(reg.test(nameObj[0].value)==false){
				alert("请输入用户名！");
				return false;
			}
			if(reg.test(pwdObj[0].value)==false){
				alert("请输入密码！");
				return false;
			}
			document.forms[0].submit();
	};
</script>
</body>
</html>


