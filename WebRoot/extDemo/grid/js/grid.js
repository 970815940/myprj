var grid=null;
var ACTION_GETC=Ext.appRootPath+"/extdemo/gcreateView.action";
var aa=[
			{name:'paId'},
			{name:'paDptid'},
			{name:'paDptname'},
			{name:'paUsername'},
			{name:'paUserid'},
			{name:'paPromoney'},
			{name:'paApplydate'},
			{name:'paProname'},
			{name:'paProcategory'},
			{name:'paState'},
			{name:'paProcessinstanceid'},
			{name:'paPurchasetype'}
		];
var aa2=[
	{name:'dpt1'},
	{name:'dpt2'},
	{name:'dpt3'},
	{name:'dpt4'}

]
var addBtn=new Ext.Button({
	text:'新增',
	cls: "x-btn-text-icon", 
	icon: Ext.appRootPath + "/skins/default/images/share/add.png"
});
var updateBtn=new Ext.Button({
	text:'修改',
	cls: "x-btn-text-icon", 
	icon: Ext.appRootPath + "/skins/default/images/share/edit.png"
});
var infoBtn=new Ext.Button({
	text:'查看',
	cls: "x-btn-text-icon", 
	icon: Ext.appRootPath + "/skins/default/images/share/query.png"
});
var deleteBtn=new Ext.Button({
	text:'删除',
	cls: "x-btn-text-icon", 
	icon: Ext.appRootPath + "/skins/default/images/share/delete.png"
});
function getStore(record){
	var store_=new Ext.data.Store({
		proxy :new Ext.data.HttpProxy({
			url: ""
		}),
		baseParams: {
			method: 'getPurApplyByList'
		},	
		reader :new Ext.data.JsonReader({
			totalProperty: "allRowCount",
			root:'root'},record)
	});	
	return store_;
}		
var store=getStore(aa2);
var ccc=[
	{header:'paId',dataIndex:'paId',hidden:true},
	{header:'承办部门',dataIndex:'paDptname'},
	{header:'经办人',dataIndex:'paUsername'},
	{header:'申请时间',dataIndex:'paApplydate'},	
	{header:'项目名称',dataIndex:'paProname'},
	{header:'项目类别',dataIndex:'paProcategory'},
	{header:'金额(万元)',dataIndex:'paPromoney'},
	{header:'状态',dataIndex:'paState'}
]
var ccc2=[
	{header:'dpt1',dataIndex:'dpt1',hidden:true}
]
var cm=ccc2;
var sm = new Ext.grid.CheckboxSelectionModel({handleMouseDown:Ext.emptyFn}); 

var bbar=new Ext.PagingToolbar({
	pageSize: 5,
	store: store,
	displayInfo: true,
	displayMsg: '显示 {0} 条到 {1} 条记录，共 {2} 条',
	emptyMsg: '没有采购项目计划申请记录',
	stateful:true
});
var stateCombox=new Ext.form.ComboBox({
			id:'stateCombox',
			readOnly : true,
			mode : 'local',
			width:100,
			height:26,
			triggerAction:'all',
			forceSelection:false,
			valueField:'key',
			displayField:'value',
			store:new Ext.data.SimpleStore({
				fields: ['key','value'],
				data : [
					['user','user'],['dpt','dpt']
				]
			}),
			listeners:{
				'change':function(com,newV,oldV){
						if(newV=="user"){
							Ext.Ajax.request({
								url:ACTION_GETC,
								params:{type:'1'},
								success:function(response){
									var obj=Ext.decode(response.responseText);
									obj.cm.unshift(new Ext.grid.CheckboxSelectionModel());
									grid.reconfigure(new Ext.data.JsonStore({
											url:Ext.appRootPath+obj.url,
											root:"root",
											baseParams:{},
											totalProperty:"allRowCount",
											fields:obj.fd
										}),new Ext.grid.ColumnModel(
											
											obj.cm
										)
									 );
									 grid.getStore().load();
									grid.getBottomToolbar().bind(grid.getStore());
								},
								failure:function(){
									Ext.Msg.alert("系统提示","创建列失败");
								}
							});
							/*var s=getStore(aa);
							
							cm=ccc;
							s=new Ext.data.JsonStore({ 
								url:"sample.cfc",
							 	root:"data", 
							 	baseParams:{
							 		method:"getGridData"
							    }, 
							  	 totalProperty:"totalCount",
							  	 id:"id", 
							fields:aa }
						  	);
						  	var c=new Ext.grid.ColumnModel(cm);
							grid.reconfigure(s,c);
							grid.getBottomToolbar().bind(s); */
						}else if(newV=="dpt"){
							Ext.Ajax.request({
								url:ACTION_GETC,
								params:{type:'2'},
								success:function(response){
									var obj=Ext.decode(response.responseText);
									obj.cm.unshift(new Ext.grid.CheckboxSelectionModel());
									grid.reconfigure(new Ext.data.JsonStore({
											url:Ext.appRootPath+obj.url,
											root:"root",
											baseParams:{},
											totalProperty:"allRowCount",
											fields:obj.fd
										}),new Ext.grid.ColumnModel(
											obj.cm
										)
										
									 );
									 grid.getStore().load();
									 grid.getBottomToolbar().bind(grid.getStore());
								},
								failure:function(){
									Ext.Msg.alert("系统提示","创建列失败");
								}
							});						
						/*
							var s=getStore(aa);
							
							cm=ccc2;
							s=new Ext.data.JsonStore({ 
								url:"sample.cfc",
							 	root:"data", 
							 	baseParams:{
							 		method:"getGridData"
							    }, 
							  	 totalProperty:"totalCount",
							  	 id:"id", 
							fields:aa2 }
						  	);
						  	var c=new Ext.grid.ColumnModel(cm);
							grid.reconfigure(s,c);
							grid.getBottomToolbar().bind(s); 
							*/
						}
						
				}
			}
	});
addBtn.fireEvent('',this);
var tbar=[stateCombox,addBtn,updateBtn,infoBtn,deleteBtn];
grid=new Ext.grid.GridPanel({
	stripeRows : true,
	animCollapse : true,
	enableColumnHide:false,
	enableColumnMove: false,	
	region:'center',
	store:store,
	columns:cm,
    viewConfig: {
        forceFit: true
    },
	sm: sm,
	frame:true,
	title:'采购项目计划申请列表',
	bbar:bbar,
	tbar:tbar	,
	loadMask: {
		msg: '正在加载数据...'
	}
});

Ext.onReady(function(){
	new Ext.Viewport({
		layout:'border',
		items:[
			grid
		]
	});

});