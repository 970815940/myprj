var ACTION_LIST_URL=Ext.appRootPath+"/basic/pltgetPortletByList.action";
var ACTION_SAVE_URL=Ext.appRootPath+"/basic/pltsavePortlet.action";
var ACTION_DELETE_URL=Ext.appRootPath+"/basic/pltdeletePortlet.action";
var ACTION_GETBYID_URL=Ext.appRootPath+"/basic/pltgetPortletById.action";
var store=new  Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
               		 url: ACTION_LIST_URL
       			}),
       			reader: new Ext.data.JsonReader({
       				   totalProperty: 'allRowCount',
            		   root: 'root'},
            		   [
            			{name:'id'},{name:'title'},
            			{name:'url'},{name:'describe'}	,
            			{name:"width"},{name:"height"} ,
            			{name:'available'}  ,{name:'defaultportal'},
            			{name:'optional'}
              ]),
              listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
       		 }
       });
var columns=[
		{header:'id',dataIndex:'id',hidden:true},
		{header:'标题',dataIndex:'title'},
		{header:'地址',dataIndex:'url',renderer:function(val){return "<div title="+val+">"+val+"</div>"}},
		{header:'宽度',dataIndex:'width'},
		{header:'高度',dataIndex:'height'},
		{header:'是否可用',dataIndex:'available',renderer:conver},
		{header:'是否默认',dataIndex:'defaultportal',renderer:conver},
		{header:'是否可选',dataIndex:'optional',renderer:conver}
]        
function conver(v){
	if(v==1){
		return "是";
	}else if(v==0){
		return "否";
	}else{
		return "<span style='color:red'>ERROE</span>";
	}
}
var bbar = new Ext.PagingToolbar({
	pageSize: 20,
	store:store,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
});
var tbar=[{ text:'新增',
			icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
		  	 iconCls:'--',
		  	 handler:showForm
		  },
		  {
		    text:'编辑',
			icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
			iconCls:'---',
			handler:edit	  	
		  },
		  { 
		  	text:'删除',
			icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
			iconCls:'---'	,
			handler:delete_		  
		  },
		  {
		    text:'刷新',
			icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
			iconCls:'---',
			handler:reloadList			  
		  }
];
var sm=new Ext.grid.RowSelectionModel({singleSelect:true});
var grid=new Ext.grid.GridPanel({
	region:'center',
	title:'组件维护',
    viewConfig:{
        forceFit:true
    },
    frame:true,
    sm:sm,	
	store:store,
	columns:columns,
	bbar:bbar,
	tbar:tbar,
    enableColumnMove:false,
    stripeRows:true,
    animCollapse:true,
    trackMouseOver:true,
    loadMask:true
});
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:[
			grid
		]
	});
	reloadList();
});
/***********************方法定义区*******************************************************************/
var editform=new Ext.form.FormPanel({
			id:'portlet-grid-edit-form-panel',
	    	labelAlign:'right',
	    	bodyStyle : 'padding:5px 5px 0',
	    	labelWidth:60,
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
							{xtype:'hidden',id:'id_',name:'pr.id'},
							{id:'title_',name:'pr.title',fieldLabel:'标题',anchor : '95%',allowBlank:false},
							{id:'width_',name:'pr.width',fieldLabel:'宽度',anchor : '95%',allowBlank:false},
							 {
							    fieldLabel:'是否可用',
							    xtype:'combo',
							    anchor : '95%',
							    value:'1',
							    id:'available_',
							   // name:'pr.available',
							    hiddenName:'pr.available',
							    store:new Ext.data.SimpleStore({
							    	fields:['value','text'],
							    	data:[['1','是'],['0','否']]
							    }),
							    valueField:'value',
							    displayField:'text',
							    mode:'local',
							    triggerAction:'all'
							},
							 {
							    fieldLabel:'是否必选',
							    xtype:'combo',
							    anchor : '95%',
							    value:'0',
							    id:'optional_',
							   // name:'pr.optional',
							    hiddenName:'pr.optional',
							    store:new Ext.data.SimpleStore({
							    	fields:['value','text'],
							    	data:[['1','是'],['0','否']]
							    }),
							    valueField:'value',
							    displayField:'text',
							    mode:'local',
							    triggerAction:'all'
							} 								 						
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
							{id:'url_',name:'pr.url',fieldLabel:'URL',anchor : '95%',allowBlank:false},
							{id:'height_',name:'pr.height',fieldLabel:'高度',anchor : '95%',allowBlank:false},
							 {
							    fieldLabel:'是否默认',
							    xtype:'combo',
							    anchor : '95%',
							    value:'0',
							    id:'defaultportal_',
							    //name:'pr.defaultportal',
							    hiddenName:'pr.defaultportal',
							    store:new Ext.data.SimpleStore({
							    	fields:['value','text'],
							    	data:[['1','是'],['0','否']]
							    }),
							    valueField:'value',
							    displayField:'text',
							    mode:'local',
							    triggerAction:'all'
							} 								
						
						]				
					
					}]
			 },
			 	{
				  id:'describe_',name:'pr.describe',xtype:'textarea',
				  width:370,fieldLabel:'描述',
				  height:100
				}
			] 		
	});
var formWin=new Ext.Window({
			title:'编辑组件',
			width:500,
			height:320,
			id:'portlet-grid-edit-win-panel',
			items:editform,
			modal:true,
			buttonAlign:'center',
			buttons:[
				{text:'确定',handler:add},
				{text:'取消',handler:function(){formWin.hide()}}
			]
		});
function showForm(){
	formWin.show();
	editform.getForm().reset();
}
function add(){
	if(editform.getForm().isValid()===false){
		Ext.Msg.alert("提示","请确认数据完整性");
		return;
	}
	editform.getForm().submit({
		url:ACTION_SAVE_URL,
		method :'POST',
		scope:this,
		clientValidation:true,
		success :function(form ,action){
			reloadList();
			formWin.hide();
		},
		failure :function(form ,action ){
			Ext.Msg.alert("提示","保存失败");
		} 
	});		
	
}
function edit(){
	var record=grid.getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert("提示","请选择一行数据");
		return;
	}			
	showForm();
	var prId=record.get('id');
	Ext.Ajax.request({
					  url:ACTION_GETBYID_URL,
					  params:{prId:prId},
					  scope :this,
					  success :function(response ,options ){
					  	var obj=Ext.decode(response.responseText).object;
					  	Ext.getCmp("id_").setValue(obj.id);
					  	Ext.getCmp("title_").setValue(obj.title);
					  	Ext.getCmp("url_").setValue(obj.url);
					  	Ext.getCmp("describe_").setValue(obj.describe);
					  	Ext.getCmp("width_").setValue(obj.width);
					  	Ext.getCmp("height_").setValue(obj.height);
					  	Ext.getCmp("available_").setValue(obj.available);
					  	Ext.getCmp("defaultportal_").setValue(obj.defaultportal);
					  	Ext.getCmp("optional_").setValue(obj.optional);
					  },
					  failure :function(response ,options){
					  		Ext.Msg.alert("提示","数据获取失败");
					  }
	});	
}
function delete_(){
	var record=grid.getSelectionModel().getSelected();
	if(record==null){
		Ext.Msg.alert("提示","请选择一行数据");
		return;
	}
	var prId=record.get('id');
	Ext.Ajax.request({
					  url:ACTION_DELETE_URL,
					  params:{prId:prId},
					  scope :this,
					  success :function(response ,options ){
					  		var obj=Ext.decode(response.responseText)
					  		if(obj.success===true){
						  		Ext.Msg.alert("系统提示","删除成功");
						  		reloadList();
					  		}else{
					  			Ext.Msg.alert("提示",obj.msg);
					  		}

					  },
					  failure :function(response ,options){
					  		Ext.Msg.alert("错误提示","删除失败<br/>可能原因是:<br/>");
					  		}
	});	
}
function reloadList(){
	store.load();
}