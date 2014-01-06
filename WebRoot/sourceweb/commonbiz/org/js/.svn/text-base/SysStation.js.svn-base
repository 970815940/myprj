var ACTION_SAVE_URL=Ext.appRootPath+"/commonbiz/sssaveSysStation.action";
var ACTION_GRID_URL=Ext.appRootPath+"/commonbiz/ssgetSysStationByList.action";
var ACTION_DELETE_URL=Ext.appRootPath+"/commonbiz/ssdelSysStation.action";

var Tool=[
	{ text:'新增',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  iconCls:'-',
	  handler:add_click
	},
	{ text:'编辑',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	  iconCls:'-',
	  handler:edit_click
	},
	{ text:'删除',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  iconCls:'-',
	  handler:delete_click
	},
	{ text:'刷新',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	  iconCls:'-' ,
	  handler:refresh_grid 
	}
]
var column=[
	{header:'岗位ID',dataIndex:'guid',hidden:true},
	{header:'岗位名称',dataIndex:'ssDispalyname'},
	{header:'岗位描述',dataIndex:'ssDemo'},
	{header:'顺序号',dataIndex:'ssIndex'},
	{header:'状态',hidden:true,dataIndex:'ssState'}
]
var store=new Ext.data.Store({
			proxy :new Ext.data.HttpProxy({
				url:ACTION_GRID_URL
			}),
			limit:'size',
			reader:new  Ext.data.JsonReader({
				root: "root",
				totalProperty:'allRowCount'
			},[
				{name:'guid'},
				{name:'ssDispalyname'},
				{name:'ssDemo'},
				{name:'ssIndex'},
				{name:'ssState'},
				{name:'ssId'}
			]),
             listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
       		 }			
    });
var sm=new Ext.grid.RowSelectionModel({singleSelect:true});
var bbar = new Ext.PagingToolbar({
	pageSize: 20,
	store:store,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条{3}{4}{5}</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
});
var grid=new Ext.grid.GridPanel({
	store:store,
	title:'岗位维护',
    enableColumnMove:false,
    stripeRows:true,
    animCollapse:true,
    trackMouseOver:true,
    loadMask:{msg: '正在加载数据,请稍候...'},	
	region:'center',
	viewConfig: {
        forceFit: true,
        frame:true
    },
    columns:column,
    sm:sm,
    bbar:bbar,
    tbar:Tool,
    /***
     *定义销毁编辑组件的方法
     */
    destoryForm:function(){
    	var formWin=Ext.getCmp("sysstation-edit-win-panel");
    	if(formWin)formWin.destroy();
    },
    /**
     *定义编辑岗位表单
     *@return  表单组件对象 
     */
    getInstanceEditPanel:function(){
    	grid.destoryForm();
    	var form=new Ext.form.FormPanel({
    		id:'systation-edit-from-panel',
    		labelAlign:'right',
 			bodyStyle : 'padding:5px 5px 0',
	    	labelWidth:60,
	    	height:300,
	    	anchor : '95%',
	    	frame:true,   
	    	items:[{
	    			layout:'column',
	  				items:[
	  					{
	  					  columnWidth:0.5,
	  					  layout:'form',
	  					  defaultType:'textfield',
	  					  items:[
	  					  	{xtype:'hidden',id:'guid_',name:'ss.guid'},
	  					  		{
	  					  		  fieldLabel:'岗位名称',
	  					  		  allowBlank:false,
	  					  		  width:140,
	  					  		  id:'ssDispalyname_',
	  					  		  name:'ss.ssDispalyname'
	  					  		}
	  					  ]
	  					
	  					},
	  					{
	  					  columnWidth:0.5,
	  					  layout:'form',
	  					  defaultType:'numberfield',
	  					  items:[
	  					  	 {
	  					  	 	fieldLabel:'顺序号',
	  					  	 	width:140,
	  					  	 	value:0,
	  					  	 	id:'ssIndex_',
	  					  	 	name:'ss.ssIndex'
	  					  	 }
	  					  ]
	  					}
	  				]
	  		},
	  		{
	  			xtype:'textarea',
	  			width:370,
	  			height:100,
	  			fieldLabel:'描述',
	  			id:'ssDemo_',
	  			name:'ss.ssDemo'
	  		}
	  		]		
    	});
    	new Ext.Window({
    		title:'编辑岗位信息',
    		items:form,
    		buttonAlign:'center',
    		modal:true,
    		id:'sysstation-edit-win-panel',
    		width:500,
    		height:300,
    		buttons:[
    			{text:'保存',handler:save_click},
    			{text:'取消',handler:grid.destoryForm}
    		]
    	}).show();
    	return form;
    }
});
grid.on('dblclick',edit_click);


Ext.onReady(function(){
	new Ext.Viewport({
		layout:'border',
		items:grid
	});
	refresh_grid();
});

/***************************************/
/**
 *刷新列表数据
 */
function refresh_grid(){
if(store)store.reload();
}
var edit_form=null;
/***
 *新增按钮触发事件
 */
function add_click(){
	edit_form=grid.getInstanceEditPanel();
}
/**
 *点击保存按钮触发
 */
function save_click(){
	if(!edit_form)return;
	edit_form.getForm().submit({
		url:ACTION_SAVE_URL,
		method :'POST',
		params :{},
		scope :this,
		clientValidation :true,
		success :function(form,action){
			refresh_grid();
			grid.destoryForm();
		},
		failure:function(form,action){
			var obj=action.result;
			Ext.Msg.alert("系统提示","保存失败<br/>"+obj.msg);
		}
	});
	
}
/***
  *点击删除按钮
  */
function delete_click(){
	var rows=grid.getSelectionModel().getSelections();
	if(rows.length==0){
		Ext.Msg.alert("系统提示","请选择一条数据执行删除");
		return;
	}
	if(rows.length>1){
		Ext.Msg.confirm("系统提示","你选中了多条数据,默认执行删除第一条数据,你确认执行吗?",function(btn){
			if(btn=='no'){
				return;
			}
		});
	}
	var id=rows[0].get('guid');
	Ext.Ajax.request({
		url:ACTION_DELETE_URL,
		scope :this,
		params:{ssid:id},
		success:function(response,options){
			refresh_grid();
			grid.destoryForm();			
		},
		failure:function(response,options){
			var msg=Ext.decode(response.responseText);
			Ext.Msg.alert("系统提示","删除发生错误<br/>"+msg.msg);
		}
	});
}
/***
 *
 *点击编辑按钮
 */
function edit_click(){
	var rows=grid.getSelectionModel().getSelections();
	if(rows.length==0){
		Ext.Msg.alert("系统提示","请先选择一条数据");
		return;
	}
	if(rows.length>1){
		Ext.Msg.confirm("系统提示","你选中了多条数据,默认执行编辑第一条数据,你确认执行吗?",function(btn){
			if(btn=='no'){
				return;
			}
		});
	}
	var data=rows[0];
	edit_form=grid.getInstanceEditPanel();
	Ext.getCmp("guid_").setValue(data.get('guid'));
	Ext.getCmp("ssDispalyname_").setValue(data.get('ssDispalyname'));
	Ext.getCmp("ssIndex_").setValue(data.get('ssIndex'));
	Ext.getCmp("ssDemo_").setValue(data.get('ssDemo'));
}