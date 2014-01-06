//URL
var　ACTION_TREE_URL=Ext.appRootPath+"/commonbiz/sdgetDptTree.action";
var ACTION_SAVE_URL=Ext.appRootPath+"/commonbiz/susaveSysUser.action";
var ACTION_GRID_URL=Ext.appRootPath+"/commonbiz/sugetSysUserByList.action";
var ACTION_DELETE_URL=Ext.appRootPath+"/commonbiz/sudeleteSysUser.action";
var ACTION_FINDBYID_URL=Ext.appRootPath+"/commonbiz/sugetSysUserById.action";
var grid_grid=null;//人员列表
var grid_store=null;//列表数据源
var leftTreeLoader=new Ext.tree.TreeLoader({
	dataUrl :ACTION_TREE_URL,
	baseParams :{}
});
var leftTreeTool=[
	{text:'&nbsp;'}
]
/**
 *左边树形
 */
var leftTree=new Ext.tree.TreePanel({
	tbar:leftTreeTool,
	region:'west',
	collapsible:true,
	width:300,
	height:500,
	autoScroll:true,
	split:true,
	title:'&nbsp;行政人员维护',
	root:new Ext.tree.TreeNode({
		id:'-1',
		text:'组织机构(单击节点刷新右边人员列表)'
	}),	
	loader :leftTreeLoader	
});
leftTree.on('click',function(n,e){
	if(!grid_store)return;
	if(!n.leaf)return;
	grid_store.baseParams.dptId=n.id;
	grid_store.load({params: {start:0,limit:25}});
});
grid_store =new  Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
               		 url: ACTION_GRID_URL
       			}),
       			reader: new Ext.data.JsonReader({
       				   totalProperty: 'allRowCount',
            		   root: 'root'},
            		   [
            			{name:'guid'},{name:'suId'},
            			{name:'suDisplayname'},{name:'suDptid'}	,
            			{name:"suStatus"},{name:"suPassword"} ,
            			{name:'suIndex'} 
              ]),
              listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
       		 }
       });
var grid_columns=[
	{header:'GUID',dataIndex:'guid',hidden:true},
	{header:'用户ID',dataIndex:'suId',hidden:false},
	{header:'用户名称',dataIndex:'suDisplayname'},
	{header:'部门',dataIndex:'suDptid',renderer:function(){
		var node=leftTree.getSelectionModel().getSelectedNode();
		return node.text;
	}},
	{header:'suId',dataIndex:'suPassword',hidden:true},
	{header:'顺序号',dataIndex:'suIndex'}
]       
var grid_bbar = new Ext.PagingToolbar({
	pageSize: 30,
	store:grid_store,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条{3}{4}{5}</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
});
var formPanel=null;//编辑组件对象
var grid_Tool=[
	{
		text:'新增',
		icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  	iconCls:'--',
	  	handler:UserAdd
	},
	{
		text:'编辑',
		icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	  	iconCls:'--'		,handler:UserEdit
	},
	{
		text:'删除',
		icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  	iconCls:'--',
	  	handler:UserDel
	},
	{
		text:'刷新',
		icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	  	iconCls:'--',
	  	handler:reloadGrid	
	}
]
grid_grid=new Ext.grid.GridPanel({
    enableColumnMove:false,
    stripeRows:true,
    animCollapse:true,
    trackMouseOver:true,
    loadMask:true,
	region:'center',
	title:'人员列表',
    viewConfig:{
        forceFit:true
    },
    frame:true, 
    store:grid_store,
    columns:grid_columns,
    bbar:grid_bbar  ,
    tbar:grid_Tool,
   	/**
   	 *获取并展示编辑组件
   	 */
    getInstanceEditPanel:function(){
    	this.DestroyEditPanel();
 		formpanel=new Ext.form.FormPanel({
			id:'sysuser-grid-edit-form-panel',
	    	labelAlign:'right',
	    	bodyStyle : 'padding:5px 5px 0',
	    	labelWidth:60,
	    	height:320,
	    	anchor : '95%',
	    	frame:true,
			items:[
			 {
			    layout:'column',
			    anchor : '95%',
			    items:[
					{
						columnWidth:0.5,
						anchor : '95%',
						layout:'form',
						labelWidth:60,
						labelAlign:'right',
						defaultType:'textfield',
						items:[
							{xtype:'hidden',id:'guid_',name:'su.guid'},
							{xtype:'hidden',id:'suDptid_',name:"su.suDptid"},
							{id:'suId_',name:'su.suId',fieldLabel:'用户ID',anchor : '95%',allowBlank:false},
							{xtype:'combo',id:'suStatus_',name:'su.suStatus',fieldLabel:'人员状态',anchor : '95%',value:1,hiddenName:'su.suStatus',store:new Ext.data.SimpleStore({fields:['value','text'],data:[['1','可用'],['0','不可用']]}), valueField:'value',displayField:'text',mode:'local',triggerAction:'all',readOnly:true},
							{id:'suPassword_',name:'su.suPassword',fieldLabel:'用户密码',anchor : '95%',inputType:'password',allowBlank:false,minLength :6,minLengthText:'密码不能小于6位'}
						]
					},
					{   
					    columnWidth:0.5,
						layout:'form',
						anchor : '95%',
						labelWidth:60,
						labelAlign:'right',
						defaultType:'textfield',
						items:[
							{id:'suDisplayname_',name:'su.suDisplayname',fieldLabel:'用户名',anchor : '95%',allowBlank:false},
							{id:'suIndex_',name:'su.suIndex',fieldLabel:'顺序号',anchor : '95%',value:0},
							{id:'suDptName_',fieldLabel:'部门',anchor:'95%',disabled :true}
						]				
					
					}]
			 },
			 	{
				  id:'pkDesc_',xtype:'textarea',
				  width:370,fieldLabel:'描述',
				  height:100
				}
			] 		
		});	
		new Ext.Window({
			title:'编辑主键',
			width:500,
			height:320,
			id:'sysuser-grid-edit-win-panel',
			items:formpanel,
			modal:true,
			buttonAlign:'center',
			buttons:[
				{text:'确定',handler:function(){
					saveUser();
				}},
				{text:'取消',handler:function(){grid_grid.DestroyEditPanel();}}
			]	
		}).show();
		return formpanel 	   ;
    },
    /***
     *销毁编辑组件
     */
    DestroyEditPanel:function(){
    	var win=Ext.getCmp("sysuser-grid-edit-win-panel");
    	if(win)win.destroy();
    }	
});
grid_grid.on('dblclick',function(){
	UserEdit();
});
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:[
			leftTree,
			grid_grid
		]
	});
	reloadTree();//页面初始化完成后，刷新树节点
});
/***
 *
 *刷新树
 *@param node 要刷新的节点 
 */
function reloadTree(node){
	var root=leftTree.root;
	var treeloader=leftTree.getLoader();
	treeloader.load(root,function(){
		root.expand();
	});
}
/****
 *刷新列表
 */
function reloadGrid(){
	grid_store.reload();
}
/***
 *用户列表新增按钮
 */
function UserAdd(){
	var node=leftTree.getSelectionModel().getSelectedNode();
	if(!node){
		Ext.Msg.alert("系统提示","请选择一个部门机构添加人员");
		return ;
	}
	if(!node.leaf){
		Ext.Msg.alert("系统提示","该节点不能添加人员,因为人员只能添加在部门机构上");
		return ;
	}
	formPanel=grid_grid.getInstanceEditPanel();
	Ext.getCmp("suDptid_").setValue(node.id);
	Ext.getCmp("suDptName_").setValue(node.text);
	
}
/***
 *持久化用户对象
 */
function saveUser(){
	if(!formPanel)return;
	formPanel.getForm().submit({
		url:ACTION_SAVE_URL,
		scope:this,
		clientValidation:true,
		success :function(form ,action){
			grid_store.reload();
			grid_grid.DestroyEditPanel();
		},
		failure :function(form ,action ){
			Ext.Msg.alert("错误提示","保存失败,原因可能是:<br/>"+action.result.msg);
		} 				
	});
}
/****
 *编辑组件按钮触发
 */
function UserEdit(){
	var rows=grid_grid.getSelectionModel().getSelections();
	if(rows.length==0)return;
	if(rows.length>1){
		Ext.Msg.confirm("系统提示","选择多条数据时,默认编辑第一行数据，你确认继续吗?",function(btn){
			if(btn=='no'){
				return;
			}
			editFormPanel(rows[0].get('guid'));
		})
	}else{
		editFormPanel(rows[0].get('guid'));
	}
}
/***
 *编辑组件时。开打表单,及，展示默认数据
 */
function editFormPanel(id){
	var node=leftTree.getSelectionModel().getSelectedNode();
	Ext.Ajax.request({
		url:ACTION_FINDBYID_URL,
		method  :'POST',
		scope :this,
		params :{userId:id},
		success :function(response ,options){
			var obj=Ext.decode(response.responseText);
			formPanel=grid_grid.getInstanceEditPanel();
			var data=obj.object;
			Ext.getCmp("guid_").setValue(data.guid);
			Ext.getCmp("suDptid_").setValue(data.suDptid);
			Ext.getCmp("suId_").setValue(data.suId);
			Ext.getCmp("suStatus_").setValue(data.suStatus);
			Ext.getCmp("suPassword_").setValue(data.suPassword);
			Ext.getCmp("suDisplayname_").setValue(data.suDisplayname);
			Ext.getCmp("suIndex_").setValue(data.suIndex);
			Ext.getCmp("suDptName_").setValue(node.text);
			Ext.getCmp("suId_").disable();
		},
		failure :function(response ,options){
			var obj=Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","获取数据失败<br/>"+obj.msg);
		}
	});
	
	
}
/**
 *删除按钮触发
 */
function UserDel(){
	var rows=grid_grid.getSelectionModel().getSelections();
	if(rows.length==0)return;
	if(rows.length>1){
		Ext.Msg.confirm("系统提示","选择多条数据时,默认删除第一行数据，你确认继续吗?",function(btn){
			if(btn=='no'){
				return;
			}
			deleteUser(rows[0].get('guid'));
		})
	}else{
		deleteUser(rows[0].get('guid'));
	}
}
/***
 *执行删除
 */
function deleteUser(id){
	Ext.Ajax.request({
		url:ACTION_DELETE_URL,
		method  :'POST',
		scope :this,
		params :{userId:id},
		success :function(response ,options){
			var obj=Ext.decode(response.responseText);
			reloadGrid();
		},
		failure :function(response ,options){
			var obj=Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","删除失败<br/>"+obj.msg);
		}
	});
}