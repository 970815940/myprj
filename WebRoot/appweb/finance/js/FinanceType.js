// fields 
var ACTION_SAVE_URL=Ext.appRootPath+"/finance/ftpsaveFinanceType.action";
var ACTION_TREE_URL=Ext.appRootPath+"/finance/ftpgetFinanceTree.action";
var ACTION_BYID_URL=Ext.appRootPath+"/finance/ftpgetFinanceTypeById.action";
var ACTION_DELETE_URL=Ext.appRootPath+"/finance/ftpdeleteFinanceType.action";
var path=null;//节点打开的PATH
var TreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_TREE_URL,
	baseParams :{},
    listeners: {
        	loadexception: function(loader, node, response) {
        		var obj=Ext.decode(response.responseText);
        		Ext.Msg.alert("系统提示","对不起,数据交互失败.<br/>");
        	}
    }	
});
var tbar=[
	{text:'新增',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  iconCls:'--',
	  handler:addFT	 
	},
	{text:'编辑',
	 icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	 iconCls:'---',
	 handler:editFT	
	},
	{text:'删除',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  iconCls:'--',
	  handler:deleteFT	
	},
	{ text:'刷新',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	  iconCls:'--',
	  handler:function(){refreshTree(null)}
	},
	{
	 text:'获取PATH到黏贴板',
	 iconCls:'--',
	 icon:Ext.appRootPath+'/skins/default/grid-tbar/page_copy.png',
	 handler:copyPath
	}
]
var typeTree=new Ext.tree.TreePanel({
	title:'财务类型维护',
	region:'center',
	autoScroll:true,
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'财务类型'
	}),
	loader:TreeLoader,
	tbar:tbar
});
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:typeTree
	});
	refreshTree();
});
//*******************************************************************************
function addFT(){
	var node=typeTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个节点后，在新增");
		return;
	}
	path=node.getPath();
	CreateeditForm();
	Ext.getCmp("ftPid").setValue(node.id);
	

}
function editFT(){
	var node=typeTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个节点后，在点击编辑");
		return;
	}	
	if(node.id=='-1'){
		Ext.Msg.alert("系统提示","根节点不能编辑");
		return;
	}
	path=node.getPath();
	CreateeditForm();
	Ext.Ajax.request({
					  url:ACTION_BYID_URL,
					  params:{ftpId:node.id},
					  scope :this,
					  success :function(response ,options ){
					  		var data = Ext.decode(response.responseText).object;
							Ext.getCmp("ftDisplayname").setValue(data.ftDisplayname);
							Ext.getCmp("ftReadme").setValue(data.ftReadme);
							Ext.getCmp("ftId").setValue(data.ftId);
							Ext.getCmp("ftPid").setValue(data.ftPid);
					  },
					  failure :function(response ,options){
					  		var data = Ext.decode(response.responseText);
					  		Ext.Msg.alert("错误提示","获取数据失败<br/>"+data.msg);
					}
 });	
}
function deleteFT(){
	var node=typeTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个节点后，在点击删除");
		return;
	}
	if(!node.leaf){
		Ext.Msg.alert("系统提示","只能删除非空节点");
		return;
	}	
	path=node.parentNode.getPath();
	deleFT(node);
}
function refreshFT(){
}
function refreshTree(path){
	var root=typeTree.getRootNode();
	TreeLoader.baseParams.pid="-1";//
	TreeLoader.load(root,function(){
		if(path!=null&&path!=""){
			typeTree.expandPath(path);
		}else{
			root.expand();
		}
	});
}
function CreateeditForm(){
	editForm=new Ext.form.FormPanel({
		width:400,
		height:300,
		labelWidth:60,
		labelAlign:'right',
		frame:true,
		id:'from-financetype-editFrom',
		defaultType:'textfield',		
		items:[
				{
				fieldLabel:'类型名称',
				width:240,
				allowBlank:false,
				id:'ftDisplayname',
				name:'ftp.ftDisplayname'
				
				},
			   {
				  xtype:'textarea',
				  fieldLabel:'类型描述',
				  width:240,
				  height:100,
				  id:'ftReadme',
				  name:'ftp.ftReadme'
				},
				{xtype:'hidden',id:'ftId',name:'ftp.ftId'}	,
				{xtype:'hidden',id:'ftPid',name:'ftp.ftPid'}	
	]
	});
	new Ext.Window({
		title:'编辑财务类型详细',
		id:'win-financetype-formGrid',
		width:400,
		modal:true,
		height:300,
		items:editForm,
		buttons:[
			{text:'保存',handler:saveFT},
			{text:'取消',handler:function(){
				Ext.getCmp("win-financetype-formGrid").destroy();
			}}
		]
	}).show();;
}
function saveFT(){
	var form=Ext.getCmp("from-financetype-editFrom");
	if(!form)throw new Error("程序异常,表单不存在");
	var basicForm=form.getForm();
	basicForm.submit({
		url:ACTION_SAVE_URL ,
		scope :this,	
		clientValidation:true,
		success:function(form ,action){
			Ext.Msg.alert("系统提示","保存成功");
			refreshTree(path);
			Ext.getCmp("win-financetype-formGrid").destroy();
		},
		failure:function(form,action){
			Ext.Msg.alert("系统提示","保存失败");
		}
		
	});
}
function deleFT(node){
	Ext.Ajax.request({
		url:ACTION_DELETE_URL ,
		scope :this,	
		params:{ftpId:node.id},
		success:function(form ,action){
			refreshTree(path);
		},
		failure:function(form,action){
			Ext.Msg.alert("系统提示","保存失败");
		}	
	});
}
/***
 *复制path功能
 */
function copyPath(){
	var node=typeTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个节点后，在点击复制PATH");
		return;
	}
	var path=node.getPath();
	if(Ext.isIE){//IE
		window.clipboardData.setData( "Text" , path);
	}else if(navigator.userAgent.indexOf("Opera")!=-1){
			window.location=path; 
	}else if(window.netscape){
        try{ 
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
        } 
        catch(e){ 
            alert("您的firefox安全限制限制您进行剪贴板操作，请打开'about:config'将signed.applets.codebase_principal_support'设置为true'之后重试，相对路径为firefox根目录/greprefs/all.js"); 
            return false; 
        }
        var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard); 
        if(!clip)return; 
        var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable); 
        if(!trans)return; 
        trans.addDataFlavor('text/unicode'); 
        var str=new Object(); 
        var len=new Object(); 
        var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString); 
        var copytext=path;str.data=copytext; 
        trans.setTransferData("text/unicode",str,copytext.length*2); 
        var clipid=Components.interfaces.nsIClipboard; 
        if(!clip)return false; 
        clip.setData(trans,null,clipid.kGlobalClipboard); 	
	}
	
		
}