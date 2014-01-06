var treepanel=null;
var mainpanel=null;
var formpanel=null;
var categroyForm=null;
var tbar=null;
var ACTION_URL=Ext.appRootPath+"/workflow/fduploadFlowDefinition.action";
var LOADER_URL=Ext.appRootPath+"/workflow/wcgetpdTree.action";
tbar=[
	{text:'新增分类',handler:editCategory},'-',
	{text:'修改分类',handler:editCategory},'-',
	{text:'删除分类',handler:deleteCategory},'-','&nbsp;&nbsp;',
	{text:'新建流程',handler:newFlow},'-',
	{text:'删除流程',handler:deleteProcessDefinition},'-'
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
			loader.baseParams.nodeName=encodeURIComponent(b.text);
		},
		'click':function(b){
			if(b.leaf===true){
				//getFlowImg
				var id=b.id.replace("p_","");
				//Ext.get("graphImg").dom.src=Ext.appRootPath+"/workflow/fdgetFlowImg.action?pdId="+id;
				var frame=document.getElementById("graphImg");
				frame.src=Ext.appRootPath+"/appweb/workflow/showGraph.jsp?id="+id;
			}
			
		}
	}	
});
//
formpanel=new Ext.form.FormPanel({
	region:'center',
	baseCls: 'x-plain',
	fileUpload:true,
	labelWidth:60,
	items:[
		{
			fieldLabel:'上传文件',
			xtype:'textfield',
			inputType:'file',
			name:'file',
			id:'file',
			allowBlank:false,
			anchor:'90%'
		}
	],
	html:''
	//html:'<iframe id="wfdesigner-container" width="100%" height="100%" frameborder="0" src="' + Ext.appRootPath + '/flexui/WorkFlow.html?contextPath='+Ext.appRootPath+'" ></iframe>'
});

mainpanel=new Ext.Panel({
	layout:'border',
	title:'流程设计',
	tbar:tbar,
	items:[	
               treepanel,
               {region:'center',
               	html:'<iframe id="graphImg" frameborder="0" height="100%" width="100%" marginheight="0" marginwidth="0" src="'+contextPath+'/appweb/workflow/showGraph.jsp"></iframe>'}
               
               // formpanel
	]
});
categroyForm=new Ext.form.FormPanel({
	frame:true,
	width:400,
	height:200,
	items:[
		{cls:'marign:100px',fieldLabel:'类别',xtype:'textfield'}
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
function newFlow(){
	new Ext.Window({
		items:formpanel,
		width:400,
		height:300,
		buttons:[
			{text:'确定',handler:function(){
				var basicForm=formpanel.getForm();
				basicForm.submit({
					url:ACTION_URL,
					params:{},
					scope:this,
					success:function(form ,action){
						top._alert("流程发布成功！");
					},
					failure:function(form,action){
						top._error("流程发布失败！");
					}
				});
			}},
			{text:'取消',handler:function(){
				this.ownerCt.hide();
			}}
		]
	}).show();
}
function deleteProcessDefinition(){
	var node=treepanel.getSelectionModel().getSelectedNode();
	if(node==null||node==""){
		top._error("请选择一个流程定义");
		return false;
	}	
	var id=	node.id.replace("p_","");
	Ext.Ajax.request({
		url:Ext.appRootPath+"/workflow/fddeleteFlowDefinition.action",
		params:{pdId:id},
		success:function(r,o){
			var json=Ext.decode(r.responseText);
			if(json.success===true){
				top._info("删除成功");
					var p2 =  node.parentNode;
					var pNode = p2 && p2.parentNode ? p2.parentNode : rootNode;				    	
						loader.load(pNode,function(){
							pNode.expand();
						});				
			}else{
				top._error("删除失败，"+json.msg);
			}
		
		},
		failure:function(r,o){
			top._error("与服务器交互失败");
		}
		
	});
}
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