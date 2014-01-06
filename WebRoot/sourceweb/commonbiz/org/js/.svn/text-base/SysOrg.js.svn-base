var ACTION_TREE_URL=Ext.appRootPath+"/commonbiz/sogetSynchronizedOrgTree.action";
var ACTION_ASSIGN_STATION_URL=Ext.appRootPath+"/commonbiz/soassignStation.action";
var ACTION_ASSIGN_USER_URL=Ext.appRootPath+"/commonbiz/soassignUser.action";
var leftTree=null;
var leftTreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_TREE_URL,
	baseParams :{},
    listeners: {
        	beforeload: function(loader, node, callback) {
        		loader.baseParams.porgid = node.id;
        	}
    }	
});
/***
 *岗位选择回调函数
 *@param records 选中的记录 
 */
function callStation(records){
	if(records==null||records.length==0){
		Ext.Msg.alert("系统提示","请选择岗位再点击确定");
		return ;
	}
	var stid=records[0].get('guid');//岗位ID
	var dptid=leftTree.getSelectionModel().getSelectedNode().id;//部门ID
	var dptPath=leftTree.getSelectionModel().getSelectedNode().getPath();
	Ext.Ajax.request({
		url:ACTION_ASSIGN_STATION_URL,
		method :'POST',
		params:{
			ssId:stid,
			dptId:dptid//部门ID
		},
		success:function(response ,options){
			var data = Ext.decode(response.responseText);
			var node=leftTree.getRootNode();
			node.reload(function(){
				leftTree.expandPath(dptPath);
			});
		},
		failure:function(response ,options){
			var data = Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","分配岗位失败,</br>"+data.msg);
		}
		
	});
}
/***
 *定义岗位选择窗口组件
 */
var stationWin=new Ext.ux.StationSelectWin({
	mulitiSelect: true,
	listeners:{
		sureButtonClicked:callStation
	}
});
/****
 *
 *人员选择回调函数
 *@param records 选中的记录 
 */
function CallUser(records){
	if(records==null||records.length==0){
		Ext.Msg.alert("系统提示","请选择人员后再点击确定");
		return ;
	}
	var users=new Array();
	for(var i=0;i<records.length;i++){
		users.push(records[i].get('guid'));
	}
	var dptid=leftTree.getSelectionModel().getSelectedNode().id;//部门ID
	var dptPath=leftTree.getSelectionModel().getSelectedNode().getPath();
	Ext.Ajax.request({
		url:ACTION_ASSIGN_USER_URL,
		method :'POST',
		params:{
			userIds:users.join(','),//多个用,分开，在后台需要用split(',')分割
			ssId:dptid//部门ID
		},
		success:function(response ,options){
			var data = Ext.decode(response.responseText);
			var node=leftTree.getRootNode();
			node.reload(function(){
				leftTree.expandPath(dptPath);
			});
		},
		failure:function(response ,options){
			var data = Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","分配岗位失败,</br>"+data.msg);
		}
		
	});	
}
/***
 *
 *定义人员选择窗口
 */
var userWin=new Ext.ux.UserSelectWin({
	mulitiSelect: true,
	listeners:{
		sureButtonClicked:CallUser
	}	
});
var leftTreeTool=[
	{
		text:'分配岗位',
		icon:Ext.appRootPath+"/skins/default/org/ssn.gif",
		handler:Allocation_station,
		iconCls:'-'
	
	},'-',
	{
		text:'分配人员',
		icon:Ext.appRootPath+"/skins/default/org/user.gif",
	    handler:Allocation_User,
	    iconCls:'-'
	}
]
/**
 *左边树形
 */
leftTree=new Ext.tree.TreePanel({
	tbar:leftTreeTool,
	region:'center',
	autoScroll:true,
	title:'&nbsp;组织机构维护',
	root:new Ext.tree.AsyncTreeNode({
		id:'-1',
		text:'组织机构管理'
	}),	
	loader :leftTreeLoader	
});

Ext.onReady(function(){
	new Ext.Viewport({
		layout:'border',
		items:leftTree
	});
	leftTree.getRootNode().expand();
});
/*******************方法定义区****************************/
/***
 *分配岗位
 */
function Allocation_station(){
	var node=leftTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择部门行政机构");
		return;
	}
	if(node.attributes.so.soOrgkind!='dpt'){
		Ext.Msg.alert("系统提示","该节点上不能添加岗位，岗位只能添加到部门机构");
		return;
	}
	stationWin.show();
}
/***
 *分配人员
 */
function Allocation_User(){
	var node=leftTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择岗位");
		return;
	}	
	if(node.attributes.so.soOrgkind!='ssn'){
		Ext.Msg.alert("系统提示","该节点上不能添加人员，岗位只能添加到岗位节点");
		return;
	}
	userWin.show();
}