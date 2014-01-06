var treepanel=null;
var mainpanel=null;
var graphpanel=null;
var categroyForm=null;
var tbar=null;
var LOADER_URL=Ext.appRootPath+"/workflow/wcgetpdTree.action";
tbar=[
	{text:'新增分类',handler:editCategory},'-',
	{text:'修改分类',handler:editCategory},'-',
	{text:'删除分类',handler:deleteCategory},'-','&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
	{text:'保存流程'},'-',
	{text:'新建流程'},'-',
	{text:'删除流程'},'-',
	{text:'发布流程'},'-',
	{text:'返&nbsp;&nbsp;回'}
];
//var root=new Ext.tree.TreeNode({
//	id:'-1',
//	text:'全部流程',
//	expanded :false
//});
var root=new  Ext.tree.AsyncTreeNode({
	id:'-1',
	text:'全部流程',
	expanded :false,
	type:'c'
});
var loader=new Ext.tree.TreeLoader({
	dataUrl: LOADER_URL,
	baseParams:{}
});
treepanel=new Ext.tree.TreePanel({
	region:'west',
	width:240,
	root:root,
	layoutConfig:{
	  animate:true
	},	
	loader:loader,
	listeners:{
		'beforeload':function(b){
			loader.baseParams.id=b.id;
			loader.baseParams.type=b.attributes.type;
		}
	}	
});
//
graphpanel=new Ext.Panel({
	region:'center',
	html:'<iframe id="wfdesigner-container" width="100%" height="100%" frameborder="0" src="' + Ext.appRootPath + '/flexui/WorkFlow.html?contextPath='+Ext.appRootPath+'" ></iframe>'
});

mainpanel=new Ext.Panel({
	layout:'border',
	title:'流程设计',
	tbar:tbar,
	items:[	
               treepanel,
			   graphpanel
	]
});
categroyForm=new Ext.form.FormPanel({
	frame:true,
	width:400,
	height:200,
	items:[
		{fieldLabel:'类别',xtype:'textfield'}
	]
});
Ext.onReady(function(){
	new Ext.Viewport({
		layout:'fit',
		items:[
			mainpanel
		]
	});
//	var root_=treepanel.getRootNode();
//	loader.baseParams.id=root_.id;
//	loader.load(root_,function(){
//		root_.expand();
//	});
});
/////////////////////方法定义区//////////////////////////////////////////////////////////////////
function editCategory(){
	var node=treepanel.getSelectionModel().getSelectedNode();
	if(node==null||node==""){
		top._error("请选择一个目录");
		return false;
	}
	if(node.attributes.type!="c"){
		top._error("请选择一个非流程定义节点");
		return false;		
	}
	new Ext.Window({
		title:"编辑分类",
		width:400,
		height:200,
		buttonAlign:'right',
		buttons:[
			{text:'确定'},
			{text:'取消'}
		],
		items:categroyForm
	}).show();
}
function deleteCategory(){
	
}