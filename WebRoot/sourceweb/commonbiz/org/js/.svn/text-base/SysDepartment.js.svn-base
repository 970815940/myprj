
var dpt={};
dpt.ACTION_TREE_URL=Ext.appRootPath+"/commonbiz/sdgetDptTree.action";
dpt.ACTION_SAVE_URL=Ext.appRootPath+"/commonbiz/sdsaveSysDepartment.action";
dpt.ACTION_DEL_URL=Ext.appRootPath+"/commonbiz/sddeleteSysDepartment.action";
dpt.treeLoader=new Ext.tree.TreeLoader({
	dataUrl :dpt.ACTION_TREE_URL,
	baseParams :{}
});
dpt.tool=[
	{
		text:'新增',
		icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
		iconCls:'--',
		handler:addDpt
	}, 
	{
	    text:'删除',
	    icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	    iconCls:'--',
	    handler:delDpt
	
	},
	{
	    text:'刷新',
	    icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	    iconCls:'--',
	    handler:function(){
	    	reloadTree();
	    }
	}
]
dpt.left=new Ext.tree.TreePanel({
	region:'west',
	autoScroll:true,
	title:'&nbsp;组织结构',
	width:400,
	tbar:dpt.tool,
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'组织结构'
	}),
	loader :dpt.treeLoader
});
dpt.left.on('click',function(node,e){
	if(node.id=='-1'){
		Ext.Msg.alert("系统提示","不能操作根节点");
		return;
	}
	Ext.getCmp("guid_").setValue(node.id);
	Ext.getCmp("sdPanentid_").setValue(node.parentNode.id);
	Ext.getCmp("sdPanentName_").setValue(node.parentNode.text);
	Ext.getCmp("sdDisplayname_").setValue(node.text);
	Ext.getCmp("sdKind_").setValue(node.attributes.sdKind);
	Ext.getCmp("istrue_").setValue(node.attributes.sdIstrue);
	Ext.getCmp("sdIndex_").setValue(node.attributes.sdIndex);
});
dpt.center=new Ext.form.FormPanel({
	region:'center',
	title:'&nbsp;机构详细信息',
	frame:true,
	defaultType:'textfield',
	buttonAlign :'center',
	labelAlign :'right',
	labelWidth :60,
	items:[
		{xtype:'hidden',
		 name:'sd.guid',
		 id:'guid_'
		},
		{
			xtype:'hidden',
			name:'sd.sdId',
			id:'sdId_'
		},
		{
			 xtype:'hidden',
			 name:'sd.sdPanentid',
			 id:'sdPanentid_'
		},
		{
			fieldLabel:'上级机构',
			allowBlank:false,
			id:'sdPanentName_',
			width:300,
			value:'',
			readOnly:true
		},
		{
			fieldLabel:'组织名称',
			allowBlank:false,
			name:'sd.sdDisplayname',
			id:'sdDisplayname_',
			value:'',
			width:300
		},
		 {
		    fieldLabel:'组织类别',
		    xtype:'combo',
		    allowBlank:false,
		    name:'sd.sdKind',
		    id:'sdKind_',
		    width:300,
		    value:'1',
		    forceSelection:false,
		    hiddenName:'sd.sdKind',
		    store:new Ext.data.SimpleStore({
		    	fields:['value','text'],
		    	data:[['1','机构'],['2','部门']]
		    }),
		    valueField:'value',
		    displayField:'text',
		    mode:'local',
		    triggerAction:'all'
		},		
		 {
		    fieldLabel:'是否可用',
		    xtype:'combo',
		    width:300,
		    value:'1',
		    id:'istrue_',
		    name:'sd.sdIstrue',
		    store:new Ext.data.SimpleStore({
		    	fields:['value','text'],
		    	data:[['1','可用'],['0','不可用']]
		    }),
		    valueField:'value',
		    displayField:'text',
		    mode:'local',
		    triggerAction:'all'
		} ,
		{
			fieldLabel:'顺序号',
			width:300,
			xtype:'numberfield',
			name:'sd.sdIndex',
			id:'sdIndex_',
			value:0
		},
		{
			xtype:'panel',
			layout:'column',
			items:[
				{xtype:'panel',columnWidth:0.1},
				{xtype:'panel',columnWidth:0.6,
				 items:[
				 	{
				 		layout:'column',
				 		items:[
				 			{columnWidth:0.25,
				 			 items:[
				 			  	{xtype:'button',text:'保存',handler:saveDpt
				 			  	}
				 			  ]
				 			},
				 			{columnWidth:0.5,
				 				items:[{
				 					xtype:'button',
				 					text:'取消',
				 					 handler:function(){
				 					 	dpt.center.getForm().reset();
				 					 }
				 					}
				 				]
				 			}
				 		  ]
				 	}
				  ]
				}
				]
				}
			]
		});
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:[dpt.left,dpt.center]
	});
	reloadTree();
});
/***
 *
 *刷新树
 *@param node 要刷新的节点 
 *@param  path  要展开的PATH
 */
function reloadTree(node,path){
	var root=dpt.left.root;
	var treeloader=dpt.left.getLoader();
	//treeloader.baseParams.rootTreeId=node!=null?node.id:'';
	treeloader.load(root,function(){
		if(!path){root.expand();}
		else{
		dpt.left.expandPath(path);
		}
	});
}
/***
 *获取树节点中选中的节点
 */
function getTreeSelectNode(){
	var node=dpt.left.getSelectionModel().getSelectedNode();
	return node;
}
/***
 *新增
 */
function addDpt(){
	var node =getTreeSelectNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个机构节点");return;
	}
	dpt.center.getForm().reset();
	Ext.getCmp("sdPanentName_").setValue(node.text);
	Ext.getCmp("sdPanentid_").setValue(node.id);
}
/***
 *保存
 */
function saveDpt(){
	var id=Ext.getCmp("sdPanentid_").getValue();
	var node=dpt.left.getNodeById(id);
	var openPath=node.getPath();
	dpt.center.getForm().submit({
		url:dpt.ACTION_SAVE_URL,
		method :'POST',
		scope :this,
		clientValidation :true,
		params :{},
		success :function(form,action){
			Ext.Msg.alert("系统提示","保存成功");
			reloadTree(node,openPath);//刷新树
			//展开之前的添加的父节点
		
			
		},
		failure :function(form,action){
			Ext.Msg.alert("系统提示","保存失败-------->可能原因是:<br/>"+"<div style=color:red>"+action.result.msg+"</div>");
		}
	});
}
/***
 *删除
 */
function delDpt(){
	var node=getTreeSelectNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个机构节点删除");return;
	}
	if(!node.leaf){
		Ext.Msg.alert("系统提示","非空节点不能删除");
		return;
	}
	Ext.Ajax.request({
		url:dpt.ACTION_DEL_URL,
		method  :'POST',
		scope :this,
		params :{nodeId:node.id},
		success :function(response ,options){
			var obj=Ext.decode(response.responseText);
			reloadTree();
		},
		failure :function(response ,options){
			var obj=Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","删除失败<br/>"+obj.msg);
		}
	});
	
	
}