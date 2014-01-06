function isVaild(){
	var username=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(username==""){
		alert("用户名不能为空");
		return false;
	}
	if(password==""){
		alert("密码不能为空");
		return false;
	}
	return true;
}
function submit(){
	$.ajax({
		url:LOGIN_URL,
		dataType:'json',
		async:true,
		data :{username:$("#username").val(),
			password:$("#password").val()
		},
		success:function(data,stats){
			location.href="index.jsp";
		},
		error:function(data){
			
		}
	});
}
function  initLogin(){
	$("#login-btn").click(function(){
		if(isVaild()==false)return;
		
		document.getElementById("login-form").submit();
		showProcess();
		//submit();
		
	});
	$("#reset-btn").click(function(){
		document.getElementById("username").value="";
		document.getElementById("password").value="";	
	});
}
function listenerKey(){
	$(document).keydown(function(event){
	    var curKey = event.which; 
	    if(curKey==13){
	     	  $("#login-btn").click();
	     	
	    }
   });

	
}
var progressbar=null;
function showProcess(){
	progressbar = $( "#progressbar" );
	    var progressLabel = $( ".progress-label" );
		progressbar.progressbar({
			value: false,
			width:200,
			barImage:'/skins/default/index/progressbar.gif',
			change: function() {
				progressLabel.text("正在登录...请稍后"+progressbar.progressbar( "value" ) + "%" );
			},
			complete: function() {
				//progressLabel.text( "Complete!" );
			}
		});

	setTimeout( progress, 3000 );	
}
function setTititle(){

}
function progress() {
			var val = progressbar.progressbar( "value" ) || 0;

			progressbar.progressbar( "value", val + 1 );

			if ( val < 99 ) {
				setTimeout( progress, 100 );
			}
		
}
$(function(){
	initLogin();
	listenerKey();
});