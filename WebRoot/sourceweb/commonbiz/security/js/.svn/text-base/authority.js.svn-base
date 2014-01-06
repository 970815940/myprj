/**
 *系统授权管理
 */
 var ACTION_LEFT_TREE_URL=Ext.appRootPath+"/commonbiz/sogetSynchronizedOrgTree.action";
 var ACTION_YES_TREE_URL=Ext.appRootPath+"/commonbiz/aragetAutorizationTree.action";//已授权树
 var ACTION_NO_TREE_URL=Ext.appRootPath+"/commonbiz/aragetUnAutorizationTree.action";//未授权树
 
 var ACTION_ADD_AUTHORIZACTION=Ext.appRootPath+"/commonbiz/araaddAutorization.action";//授权
 var ACTION_DEL_AUTHORIZACTION=Ext.appRootPath+"/commonbiz/aradelAutorization.action";//取消权限
 
var leftTree=null;//组织机构树
var centerTree=null;//已授权树
var rightTree=null;//未授权树
var leftTreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_LEFT_TREE_URL,
	baseParams :{},
    listeners: {
        	beforeload: function(loader, node, callback) {
        		loader.baseParams.porgid = node.id;
        	}
    }	
});
var centerTreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_YES_TREE_URL,
	baseParams :{}//,
   /* listeners: {
        	beforeload: function(loader, node, callback) {
        		loader.baseParams.porgid = node.id;
        	}
    }*/	
});
var rightTreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_NO_TREE_URL,
	baseParams :{},
    listeners: {
        	loadexception: function(loader, node, response) {
        		var obj=Ext.decode(response.responseText);
        		Ext.Msg.alert("系统提示","对不起,数据交互失败.<br/>");
        	}
    }	
});
var searchInput=new  Ext.form.TextField({
	
});
var searchBtn=new Ext.Button({
	text:'查询'
});
var leftTreeTool=['输入机构名称',searchInput,searchBtn]
leftTree=new Ext.tree.TreePanel({
	tbar:leftTreeTool,
	region:'west',
	width:370,
	autoScroll:true,
	title:'&nbsp;组织机构',
	root:new Ext.tree.AsyncTreeNode({
		id:'-1',
		text:'组织机构授权管理'
	}),	
	loader :leftTreeLoader	
});
leftTree.on('click',function(node){
	var orgGuid=node.id;
	reloadResourcesTree(orgGuid);

});
var centerTreeBtnExpand=new Ext.Button({
	text:'全部展开',
	handler:function(){centerTree.expandAll();}
});
var centerTreeBtnCollapse=new Ext.Button({
	text:'全部收起',
	handler:function(){
		centerTree.collapseAll();
	}
});
var centerRefresh=new Ext.Button({
	text:'刷新',
	handler:function(){
		var SelectNode=leftTree.getSelectionModel().getSelectedNode();
		if(SelectNode==null)throw "刷新已授权树发生异常";	
		var centerTreeRoot=centerTree.getRootNode();//写在里面保证不让2颗树同时加载 造成bug
		centerTreeLoader.baseParams.orgGUID=SelectNode.id;
		centerTreeLoader.load(centerTreeRoot,function(){
			centerTreeRoot.expand();
		});
	}
});
var centerTreeTool=[
	centerTreeBtnExpand,'-',centerTreeBtnCollapse,'-',centerRefresh
];

centerTree=new Ext.tree.TreePanel({
	tbar:centerTreeTool,
	region:'center',
	autoScroll:true,
	enableDD:true,
	ddGroup:'authority-ing-dd-group',
	dropConfig:{
		ddGroup:'authority-no-dd-group',
		appendOnly :true
	},		
	title:'&nbsp;已授权资源',
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'模块功能(已授权)',
		allowDrag :false//根节点不能拖拽		
	}),	
	loader :centerTreeLoader	
});
var rightTreeBtnExpand=new Ext.Button({
	text:'全部展开',
	handler:function(){
		rightTree.expandAll();
	}
});
var rightTreeBtnCollapse=new Ext.Button({
	text:'全部收起',
	handler:function(){
		rightTree.collapseAll();
	}
});
var rightRefresh=new Ext.Button({
	text:'刷新',
	handler:function(){
		var SelectNode=leftTree.getSelectionModel().getSelectedNode();
		if(SelectNode==null)throw "刷新已授权树发生异常";	
		var rightTreeRoot=rightTree.getRootNode();//写在里面保证不让2颗树同时加载 造成bug
		rightTreeLoader.baseParams.orgGUID=SelectNode.id;
		rightTreeLoader.load(rightTreeRoot,function(){
			rightTreeRoot.expand();
		});
	}
});
var rightTreeTool=[
	rightTreeBtnExpand,'-',rightTreeBtnCollapse,'-',rightRefresh
];
rightTree=new Ext.tree.TreePanel({
	tbar:rightTreeTool,
	region:'east',
	width:370,
	enableDD:true,
	ddGroup:'authority-no-dd-group',
	dropConfig:{
		ddGroup:'authority-ing-dd-group',
		appendOnly :true
	},	
	autoScroll:true,
	title:'&nbsp;未授权资源',
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'模块功能(未授权)',
		allowDrag :false//根节点不能拖拽
	}),	
	loader :rightTreeLoader	
});
centerTree.on('nodedrop',rightTocenter);
rightTree.on('nodedrop',centerToright);
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:[
			leftTree,
			centerTree,
			rightTree
		]
	});
	TreeRefresh(leftTree);//'/-1/DPT_20130054/DPT_20130055'
});


/*****************************FUN start**************************************/
/***
 *从未授权目录移动节点到已授权目录  ---->新增权限
 */
function rightTocenter(dropEvent){
	var SelectNode=leftTree.getSelectionModel().getSelectedNode();
	if(SelectNode==null)throw "授权发生异常";
	var tree=dropEvent.tree;
	var node=dropEvent.dropNode;
	var paramsNode="";//参数中所传递的NODE  ID
	if(node.isLeaf()){
		paramsNode=node.id;
	}else{
		node.eachChild(function(n,b){
				if(n.isLeaf()){
					paramsNode += n.id+",";
				}
				return true;			
		});
	}
	addModelRight(SelectNode.id,paramsNode);
	
}
/****
 *为角色 新增权限
 */
function  addModelRight(guid,meIds){
	Ext.Ajax.request({
		url:ACTION_ADD_AUTHORIZACTION,
		params:{
			orgGUID:guid,
			meIds:meIds	
		},
		success:function(response ,options){
			var data = Ext.decode(response.responseText);
			if(data.success===true){
				reloadResourcesTree(guid);
			}
		},
		failure :function(response ,options){
			var data = Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","授权失败<br/>"+data!=null?data.msg:"");
		}
	});
}
/****
 *为角色 移除权限
 */
function  delModelRight(guid,meIds){
	Ext.Ajax.request({
		url:ACTION_DEL_AUTHORIZACTION,
		params:{
			orgGUID:guid,
			meIds:meIds	
		},
		success:function(response ,options){
			var data = Ext.decode(response.responseText);
			if(data.success===true){
				reloadResourcesTree(guid);
			}
		},
		failure :function(response ,options){
			var data = Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","取消权限失败<br/>"+data!=null?data.msg:"");
		}
	});
}
/****
 *
 *从已授权目录移动节点到未授权目录 --->取消权限
 */
function centerToright(dropEvent){
	var SelectNode=leftTree.getSelectionModel().getSelectedNode();
	if(SelectNode==null)throw "异常权限发生异常";
	var tree=dropEvent.tree;
	var node=dropEvent.dropNode;
	var paramsNode=null;//参数中所传递的NODE  ID
	if(node.isLeaf()){
		paramsNode=node.id;
	}else{
		node.eachChild(function(n,b){
				if(n.isLeaf()){
					paramsNode += n.id+",";
				}
				return true;			
		});
	}	
	delModelRight(SelectNode.id,paramsNode);
}
/****
 *刷新资源树
 */
function reloadResourcesTree(orgGuid){
	var rightTreeRoot=rightTree.getRootNode();
	rightTreeLoader.baseParams.orgGUID=orgGuid;
	rightTreeLoader.load(rightTreeRoot,function(){
		rightTreeRoot.expand();
		var centerTreeRoot=centerTree.getRootNode();//写在里面保证不让2颗树同时加载 造成bug
		centerTreeLoader.baseParams.orgGUID=orgGuid;
		centerTreeLoader.load(centerTreeRoot,function(){
			centerTreeRoot.expand();
		});
	});
}
/***
 *组织机构树刷新
 *@param  tree 需要操作的树
 *@param  path 默认打开的节点路径
 */
function TreeRefresh(tree,path){
	var RootNode=tree.getRootNode();
	var treeLoader=tree.getLoader();
	treeLoader.load(RootNode,function(){
		if(!path){
			RootNode.expand();
		}else{
			tree.expandPath(path);
		}
	});
}