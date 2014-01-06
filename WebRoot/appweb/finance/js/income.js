//fields 
var ACTION_LIST_URL=Ext.appRootPath+"/finance/fngetFinanceByList.action";
var ACTION_SAVE_URL=Ext.appRootPath+"/finance/fnsaveFinance.action";
var ACTION_DEL_URL=Ext.appRootPath+"/finance/fndeleteFinance.action";
var ACTION_GETBYID_URL=Ext.appRootPath+"/finance/fngetFinanceById.action";
var ACTION_TREE_URL=Ext.appRootPath+"/finance/ftpgetFinanceTree.action";
var WIDTH=300;
var searchTypePath="";
var ds=new Ext.data.Store({
	proxy :new Ext.data.HttpProxy({
		url:ACTION_LIST_URL
	}),
	reader :new Ext.data.JsonReader({
		totalProperty:'allRowCount',
		root:'root'
	},[
		{name:'FId'},
		{name:'FTypepath'},
		{name:'FMoney'},
		{name:'FReadme'},
		{name:'FApplytime'},
		{name:'FType'}
	
	]),
    listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
    }	
});
var cm=[
	new Ext.grid.RowNumberer(),
	{header:'FId',dataIndex:'FId',hidden:true},
	{header:'FTypepath',dataIndex:'FTypepath',hidden:true},
	{header:'类型',dataIndex:'FType'},
	{header:'金额',dataIndex:'FMoney'},
	{header:'日期',dataIndex:'FApplytime'},
	
	{header:'描述',dataIndex:'FReadme'}
]
var pageTool = new Ext.PagingToolbar({
	pageSize: 20,
	store:ds,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条{3}</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有支出记录</span>'
});
var sm=new Ext.grid.RowSelectionModel({singleSelect:true});
var tool=[
	{text:'新增',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  iconCls:'--' ,
	  handler:addForm
	},
	{text:'编辑',
	 icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	 iconCls:'---',
	 handler:edItForm
	},
	{text:'删除',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  iconCls:'--',
	  handler:deleteRecord
	},
	{ text:'刷新',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	  iconCls:'--',
	  handler:function(){reloadStore()}
	},
	{
	 text:'批量导入'
	},
	{
	 text:'批量导出'
	},
	'->',
	{
			  xtype:'combo',
			  readOnly:true,
			  id:'FtpyeSeach',
			  width:200,
			  store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
			  editable:false,
			  style:'background:#ffffff',
			  triggerAction:'all', 
			  mode: 'local',  
			  tpl: "<tpl><div style='height:200px;'><div style='overflow:auto' id='x-gridSearch-tree'></div></div></tpl>", 
			  emptyText:'请选择过滤财务类型',
			  listeners:{
			  	'expand':function(){
			  		var fts=Ext.getCmp("financetype-treeSearch-select");
			  		if(fts){return;}
			  		var tree=createTreeSearch();
			  		tree.render("x-gridSearch-tree");
			  		var loader=tree.getLoader();
			  		loader.baseParams.pid="FT_003";//只加载收入节点
			  		var root=tree.getRootNode();
			  		loader.load(root,function(){
			  			root.expand();
			  		});
			  	}
			  }				  
	},
	{
	  text:'查询',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/query.png',
	  iconCls:'--',	  
	  handler:function(){
	  	reloadStore(searchTypePath);
	  }
	}	
]
var grid=new Ext.grid.GridPanel({
	region:'center',
	title:'收入管理',
    viewConfig:{
        forceFit:true
    },
    frame:true,
    sm:sm,	
	store:ds,
	columns:cm,
	bbar:pageTool,
	tbar:tool,
    enableColumnMove:true,
    stripeRows:true,
    animCollapse:true,
    trackMouseOver:true,
    loadMask:{msg:'正在加载中，请稍后...'}
});
grid.on({
	'dblclick':function(){
			edItForm();
	}
})

Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:grid
	});
	reloadStore(null);
});
function reloadStore(path){
	if(path==null||path==""){
		ds.baseParams.typPath="/-1/FT_003";//收入
	}else{
		ds.baseParams.typPath=path;//收入
	}
	
	ds.load();
}
function deleteRecord(){
		var record=grid.getSelectionModel().getSelected();
		if(record==null)return;
		Ext.Msg.confirm("系统提示","删除后将不能恢复该数据，你确定删除吗?",function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
					url:ACTION_DEL_URL,
					params:{fnId:record.get('FId')},
					scope :this,
					success :function(response ,options ){
								reloadStore();	
					},
					failure :function(response ,options){
					   var data = Ext.decode(response.responseText);
					    Ext.Msg.alert("错误提示","删除失败<br/>"+data.msg);
					}
			 });			
			}
		});
}
function edItForm(){
		var record=grid.getSelectionModel().getSelected();
		if(record==null)return;
		addForm();
	Ext.Ajax.request({
					  url:ACTION_GETBYID_URL,
					  params:{fnId:record.get('FId')},
					  scope :this,
					  success :function(response ,options ){
					  		var data = Ext.decode(response.responseText).object;
							Ext.getCmp("FType").setValue(data.FType);
							Ext.getCmp("FMoney").setValue(data.FMoney);
							Ext.getCmp("FApplytime").setValue(data.FApplytime);
							Ext.getCmp("FReadme").setValue(data.FReadme);
							Ext.getCmp("FId").setValue(data.FId);
							Ext.getCmp("FTypepath").setValue(data.FTypepath);
					  },
					  failure :function(response ,options){
					  		var data = Ext.decode(response.responseText);
					  		Ext.Msg.alert("错误提示","获取数据失败<br/>"+data.msg);
					}
 });
}
function addForm(){
	var form=new Ext.form.FormPanel({
		width:450,
		height:340,
		labelWidth:60,
		labelAlign:'right',
		frame:true,
		id:'from-finance-income-editFrom',
		defaultType:'textfield',
		items:[
			{
			  fieldLabel:'类型',
			  xtype:'combo',
			  allowBlank:false,
			  readOnly:true,
			  id:'FType',
			  name:'fn.FType',
			  width:WIDTH,
			  store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
			  editable:false,
			  style:'background:#ffffff',
			  triggerAction:'all', 
			  mode: 'local',  
			  tpl: "<tpl><div style='height:200px;'><div style='overflow:auto' id='x-grid-tree'></div></div></tpl>", 
			  emptyText:'请选择财务类型',
			  listeners:{
			  	'expand':function(){
			  		var fts=Ext.getCmp("financetype-tree-select");
			  		if(fts){
			  			fts.destroy();
			  		}
			  		var tree=createTree();
			  		tree.render("x-grid-tree");
			  		var loader=tree.getLoader();
			  		loader.baseParams.pid="FT_003";//只加载支出节点
			  		var root=tree.getRootNode();
			  		loader.load(root,function(){
			  			root.expand();
			  		});
			  	}
			  }		  
			},
			{
			  fieldLabel:'金额',
			  xtype:'numberfield',
			  id:'FMoney',
			  allowBlank :false,
			  name:'fn.FMoney',
			  width:WIDTH
			},
			{ 
			 fieldLabel:'发生日期',
			 xtype:'datefield',
			 format:'Y-m-d H:i:s',
			 id:'FApplytime',
			 value:new Date(),
			 name:'fn.FApplytime',
			 width:WIDTH
			},
			{
				  xtype:'textarea',
				  fieldLabel:'描述',
				  width:WIDTH,
				  height:100,
				  id:'FReadme',
				  name:'fn.FReadme'
			},
			{xtype:'hidden',id:'FId',name:'fn.FId'},
			{xtype:'hidden',id:'FTypepath',name:'fn.FTypepath'}
		]		
	});
	new Ext.Window({
		title:'编辑支出详细信息',
		id:'win-finance-income-formGrid',
		width:450,
		modal:true,
		height:340,
		items:form,
		buttons:[
			{text:'保存',handler:saveFN},
			{text:'取消',handler:function(){
				Ext.getCmp("win-finance-income-formGrid").destroy();
			}}
		]
	}).show();
}
function createTree(){
	var typeTree=new Ext.tree.TreePanel({
	border:false,
	id:'financetype-tree-select',
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'财务类型-->收入',
		children:[{text:'收入',id:'FT_003',expanded :true}]
	}),
	loader:new Ext.tree.TreeLoader({
			dataUrl :ACTION_TREE_URL,
			baseParams :{},
		    listeners: {
		        	loadexception: function(loader, node, response) {
		        		var obj=Ext.decode(response.responseText);
		        		Ext.Msg.alert("系统提示","对不起,数据交互失败.<br/>");
		        	}
		    }	
	}),
	listeners:{
		'click':function(node){
			if(node.id=='-1')return;
			if(!node.leaf){
				alert("只能选择叶节点");
				return;
			}
			Ext.getCmp("FTypepath").setValue(node.getPath());
			Ext.getCmp("FType").setValue(node.text);
			Ext.getCmp("FType").collapse();
		}
	}
 });
	return typeTree;
}
function createTreeSearch(){
	var typeTree=new Ext.tree.TreePanel({
	border:false,
	id:'financetype-treeSearch-select',
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'财务类型-->收入',
		children:[
			{text:'收入',id:'FT_003',expanded :true}
		]
	}),
	loader:new Ext.tree.TreeLoader({
			dataUrl :ACTION_TREE_URL,
			baseParams :{pid:'FT_003'},
		    listeners: {
		        	loadexception: function(loader, node, response) {
		        		var obj=Ext.decode(response.responseText);
		        		Ext.Msg.alert("系统提示","对不起,数据交互失败.<br/>");
		        	}
		    }	
	}),
	listeners:{
		'click':function(node){
			if(node.id=='-1')return;
			//if(!node.leaf){
			//	alert("只能选择叶节点");
			//	return;
			//}
			Ext.getCmp("FtpyeSeach").setValue(node.text);
			searchTypePath=node.getPath();
			Ext.getCmp("FtpyeSeach").collapse();
		}
	}
 });
	return typeTree;
}
function saveFN(){
	var bisicForm=Ext.getCmp("from-finance-income-editFrom").getForm();
	if(bisicForm.isValid()===false)return;
	bisicForm.submit({
		url:ACTION_SAVE_URL ,
		scope :this,	
		clientValidation:true,
		success:function(form ,action){
			Ext.Msg.alert("系统提示","保存成功");
			reloadStore();
			Ext.getCmp("win-finance-income-formGrid").destroy();
			Ext.getCmp("financetype-tree-select").destroy();
		},
		failure:function(form,action){
			Ext.Msg.alert("系统提示","保存失败");
		}
		
	});	
}
