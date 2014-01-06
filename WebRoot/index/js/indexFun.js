var USER_SYLOGIN_URL=Ext.appRootPath+"/commonbiz/slaexitUser.action";
var MODIFY_PAS_URL=Ext.appRootPath+"/commonbiz/slamodifyPassword.action";
//系统通用函数
/***********************提示消息 **********************************************************/
 var MSG_ICON_INFO = Ext.MessageBox.INFO; //普通消息图片类型
 var MSG_ICON_WARN = Ext.MessageBox.WARNING ; //警告图片类型
 var MSG_ICON_ERROR = Ext.MessageBox.ERROR; //错误图片类型
 var MSG_ICON_QUESTION = Ext.MessageBox.QUESTION ; //询问图片类型
 
 var MSG_BTN_OK = Ext.MessageBox.OK;
 var MSG_BTN_YESNO = Ext.MessageBox.YESNO;
 var MSG_BTN_OKCANCEL = Ext.MessageBox.OKCANCEL; 
 var MSG_BTN_YESNOCANCEL = Ext.MessageBox.YESNOCANCEL;
 //简单消息提示
 function _alertAll(message,title, btns, callback,icoType,mode) {
	 Ext.Msg.show({
	   title: title || "提示消息" ,
	   msg: (message==null || "" == message) ? "<div style='width:90px'></div>":message,
	   buttons: (btns || MSG_BTN_OK),
	   fn: function(btn,text){if(callback){callback(btn);}},
	   icon: (icoType || MSG_ICON_INFO),
	   modal: (mode == undefined || mode == null || mode == true)
	});
 } 
 //简单警告消息框
 function _info(message){
	_alertAll(message);
 }
 //错误提示框
 function _error(message){
 	_alertAll(message,"提示",null,null,MSG_ICON_ERROR);
 }
/*********************************end  提示消息*****************************************************/
function packURL(url,moduleGuid){
	var url=url;
	//if(moduleGuid==null||moduleGuid=="")return url;
	//url+="?SSP.Authorzation="+moduleGuid+"&"+new Date().getTime();
	return url;
}
//注销
function Exit(){
		Ext.Msg.confirm("提示","请确认退出登录吗?",function(b){
			if(b=='yes'){
					Ext.Ajax.request({
						url:USER_SYLOGIN_URL,
						//params:{orgGUID:ORGGUID},
						method:'POST',
						success:function(response ,options){
								var str=response.responseText;			
								var msg=Ext.decode(str);
								if(msg.success===true){
									window.location.reload();		
								}else{
									top._error(msg.msg);
								}
											
						},
						failure:function(response ,options){
							top._error("注销发生服务器内部错误");
						}
								
				});			
			}
		});
}
Ext.apply(Ext.form.VTypes, {
     repetition: function(val, field) {	
     	if(field.repetition){
     		var id=field.repetition.val1;
     		var value2=Ext.getCmp(id).getValue();
     		if(value2===val){
     			return true;
     		}else{
     			return false;
     		}
     	}else{
     		return false;
     	}
      	return true;
     },
     repetitionText: '2次新密码输入不一致'
 })
//修改密码
function modifyPas(){
	modifyPasForm=new Ext.form.FormPanel({
		width:300,
		height:180,
		frame:true,
		defaultType:'textfield',
		labelAlign:'right',
		items:[
			{fieldLabel:'旧密码',allowBlank :false,name:'password1',id:'password1',inputType:'password'},
			{fieldLabel:'新密码',allowBlank :false,name:'password2',id:'password2',inputType:'password'},
			{fieldLabel:'确认新密码',allowBlank :false,name:'password3',id:'password3',inputType:'password',vtype:'repetition', repetition: { val1: 'password2' } }
		]
	});
	new Ext.Window({
		title:'修改密码',
		modal:true,
		width:300,
		height:200,
		items:modifyPasForm,
		buttons:[
			{text:'确定',handler:function(){
					if(modifyPasForm.getForm().isValid()===false){
						top._error("请检测数据完整性");
						return;
					}
					modifyPasForm.getForm().submit({
						url:MODIFY_PAS_URL,
						method :'POST',
						scope:this,
						clientValidation:true,
						success :function(form ,action){
							//修改成功后 注销当前用户 并重新登录
							//。。。。
							var msg=action.result;
							if(msg.success===true){
								this.ownerCt.destroy();
								top._info("密码修改成功");
							}else{
								top._error(msg.msg);
							}
						},
						failure :function(form ,action ){
							var obj=action.result;
							try{
								if(obj.success===false){
									top._error(obj.msg);
								}else{
									top._error("服务器内部错误");
								}
							}catch (e){
								top._error("服务器返回数据错误");
							}	
						} 
					});	
			}
			},
			{text:'取消',handler:function(){
				this.ownerCt.destroy();
			}}
		]
	}).show();
}