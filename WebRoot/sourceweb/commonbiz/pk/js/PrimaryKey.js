var pk=function(){}
pk.ACTION_LIST_URL=Ext.appRootPath+"/commonbiz/pkgetPrimarykeyByList.action";
pk.ACTION_SAVE_URL=Ext.appRootPath+"/commonbiz/pksavePrimarykey.action";
pk.ACTION_DELETE_URL=Ext.appRootPath+"/commonbiz/pkdeletePrimarykey.action";
pk.sm=new Ext.grid.RowSelectionModel({singleSelect:true});
pk.store=new  Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
               		 url: pk.ACTION_LIST_URL
       			}),
       			reader: new Ext.data.JsonReader({
       				   totalProperty: 'allRowCount',
            		   root: 'root'},
            		   [
            			{name:'pkId'},{name:'pkCode'},
            			{name:'pkName'},{name:'pkStartvalue'}	,
            			{name:"pkMaxvalue"},{name:"pkCurrentvalue"} ,
            			{name:'pkRule'}  ,{name:'pkPrefix'},
            			{name:'pkPostfix'},{name:'pkIncrement'},
            			{name:'pkDesc'},{name:'pkSequence'}
              ]),
              listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
       		 }
       });
pk.columns=[
		{header:'pkId',dataIndex:'pkId',hidden:true},
		{header:'编码',dataIndex:'pkCode'},
		{header:'别名',dataIndex:'pkName',renderer:function(val){return "<div title="+val+">"+val+"</div>"}},
		{header:'开始值',dataIndex:'pkStartvalue'},
		{header:'最大值',dataIndex:'pkMaxvalue'},
		{header:'当前值',dataIndex:'pkCurrentvalue'},
		{header:'规则',dataIndex:'pkRule'},
		{header:'前缀',dataIndex:'pkPrefix'},
		{header:'后缀',dataIndex:'pkPostfix'},
		{header:'增量',dataIndex:'pkIncrement'},
		{header:'序列',dataIndex:'pkSequence'}
] 
pk.bbar = new Ext.PagingToolbar({
	pageSize: 20,
	store:pk.store,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条{3}{4}{5}</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
});
/****
 *@id dom元素ID
 *@sdoc dom元素描述
 */
pk.form_tip=function(id,sdoc){
	new Ext.ToolTip({
	        target:id,
	        html: sdoc,
	        width:300,
	        showDelay:100,
	        height:40
	});
}
/***
 *渲染tip到指定位置
 */
pk.form_tipRender=function(){
	  	pk.form_tip("pkCode_",'系统编码必须唯一,程序使用');//为文本框绑定tip提示
	  	pk.form_tip("pkStartvalue_",'主键别名');//为文本框绑定tip提示
	  	pk.form_tip("pkStartvalue_",'主键初始开始值,默认为0');//为文本框绑定tip提示
	  	pk.form_tip("pkMaxvalue_",'主键最大值,为0表示没有限制最大值');//为文本框绑定tip提示
	  	pk.form_tip("pkCurrentvalue_",'主键当前值');//为文本框绑定tip提示
	  	pk.form_tip("pkRule_",'主键生成规则 如yyyy-mm-dd ,yyyymmdd');//为文本框绑定tip提示
	  	pk.form_tip("pkPrefix_",'主键前缀');//为文本框绑定tip提示
	  	pk.form_tip("pkPostfix_",'主键后缀');//为文本框绑定tip提示
	  	pk.form_tip("pkIncrement_",'主键增量,每次增长量,默认为1');//为文本框绑定tip提示
	    pk.form_tip("pkDesc_",'对该主键生成策略的描述');//为文本框绑定tip提示	
		pk.form_tip("pkSequence_","序列,也就是最大几位数,不够位数则用0填充,比如序列值为3 (99=099,1=001)<br/>小于等于0则不使用序列,该多少就是多少");//绑定序列tip提示
}

pk.tbar=[
	{ text:'新增',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  iconCls:'--',
	  handler:function(){
	  	pk.grid.getInstanceFormPanel();
		pk.form_tipRender();
	  },
	  scope:this
	},
	{
	  text:'删除',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  iconCls:'--',
	  handler:function(){
	  	var rs=pk.grid.getSelectionModel().getSelections();
	  	if(rs.length>0){
	  	}else{
	  		Ext.Msg.alert("系统提示","请选择一行数据");
	  		return;
	  	}
		Ext.Msg.confirm("警告","你确定删除吗?",function(btn){
			if(btn=='yes'){
				var entityId=rs[0].get('pkId');//
				Ext.Ajax.request({
					  url:pk.ACTION_DELETE_URL,
					  params:{pkId:entityId},
					  scope :this,
					  success :function(response ,options ){
					  		Ext.Msg.alert("系统提示","删除成功");
					  		pk.store.load();
					  },
					  failure :function(response ,options){
					  		Ext.Msg.alert("错误提示","删除失败<br/>可能原因是:<br/>");
					  		}
			    });
			}				
		});	  	

	  }
	},
	{ 
	 text:'编辑',
	 icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	 iconCls:'---',
	 handler:function(){
	 	var rs=pk.grid.getSelectionModel().getSelections();
	 	if(rs.length<=0){
	 		Ext.Msg.alert("系统提示","请选择一行数据");
	 		return ;
	 	}
	 	var rsId=rs[0].id;
	  	var records=pk.store.getById(rsId);
	  	pk.grid.getInstanceFormPanel();
		pk.form_tipRender();
	 	renderFormValues(records);
	 }
	},
	{
	  text:'刷新',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/refresh.png',
	  iconCls:'--',
	  handler:function(){
	  	pk.store.load();
	  }
	}
]
pk.grid=new Ext.grid.GridPanel({
	region:'center',
	title:'主键管理',
    viewConfig:{
        forceFit:true
    },
    frame:true,
    sm:pk.sm,	
	store:pk.store,
	columns:pk.columns,
	bbar:pk.bbar,
	tbar:pk.tbar,
    enableColumnMove:false,
    stripeRows:true,
    animCollapse:true,
    trackMouseOver:true,
    loadMask:true,
    destroyFormPanel:function(){
    	//var formpanel=Ext.getCmp("pk-grid-edit-form-panel");由于表单是在win上的。所以删除win就同时也删除了表单
    	//if(formpanel)formpanel.destroy();//如果存在就销毁重建
    	var form_win_panel=Ext.getCmp("pk-grid-edit-win-panel");
    	if(form_win_panel)form_win_panel.destroy();
    },
    getInstanceFormPanel:function(){
    	this.destroyFormPanel();
		formpanel=new Ext.form.FormPanel({
			id:'pk-grid-edit-form-panel',
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
							{xtype:'hidden',id:'pkId_',name:'pk.pkId'},
							{id:'pkCode_',name:'pk.pkCode',fieldLabel:'编码',anchor : '95%',allowBlank:false},
							{id:'pkStartvalue_',name:'pk.pkStartvalue',fieldLabel:'开始值',anchor : '95%',value:0},
							{id:'pkCurrentvalue_',name:'pk.pkCurrentvalue',fieldLabel:'当前值',anchor : '95%'},
							{id:'pkPrefix_',name:'pk.pkPrefix',fieldLabel:'前缀',anchor : '95%'},
							{id:'pkIncrement_',name:'pk.pkIncrement',fieldLabel:'增量',anchor : '95%',value:1}
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
							{id:'pkName_',name:'pk.pkName',fieldLabel:'别名',anchor : '95%',allowBlank:false},
							{id:'pkMaxvalue_',name:'pk.pkMaxvalue',fieldLabel:'最大值',anchor : '95%',value:0},
							{id:'pkRule_',name:'pk.pkRule',fieldLabel:'规则',anchor : '95%'},
							{id:'pkPostfix_',name:'pk.pkPostfix',fieldLabel:'后缀',anchor : '95%'},
							{id:'pkSequence_',name:'pk.pkSequence',fieldLabel:'序列',anchor : '95%'}
						]				
					
					}]
			 },
			 	{
				  id:'pkDesc_',name:'pk.pkDesc',xtype:'textarea',
				  width:370,fieldLabel:'描述',
				  height:100
				}
			] 		
		});	
		new Ext.Window({
			title:'编辑主键',
			width:500,
			height:320,
			id:'pk-grid-edit-win-panel',
			items:formpanel,
			modal:true,
			buttonAlign:'center',
			exitWait:function(){
				Ext.Msg.confirm("警告","你确定退出吗?",function(btn){
							if(btn=='yes'){
								pk.grid.destroyFormPanel();	
							}				
				});
			},
			buttons:[
				{text:'确定',handler:function(){
					savePK();
				}},
				{text:'取消',handler:function(){this.ownerCt.exitWait()}}
			],
			listeners:{
				'close':function(){
					return false;
						Ext.Msg.confirm("警告","你确定退出吗?",function(btn){
								if(btn=='yes')pk.grid.destroyFormPanel();						
						});				
				}
			}
		}).show();
		return formpanel;
    }
    
});
pk.grid.on('dblclick',function(){
	 	var rs=pk.grid.getSelectionModel().getSelections();
	 	if(rs.length<=0){
	 		Ext.Msg.alert("系统提示","请选择一行数据");
	 		return ;
	 	}
	 	var rsId=rs[0].id;
	  	var records=pk.store.getById(rsId);
	  	pk.grid.getInstanceFormPanel();
		pk.form_tipRender();
	 	renderFormValues(records);	
});
/***
 *持久化PK对象
 */
function savePK(){
	var from=Ext.getCmp("pk-grid-edit-form-panel");
	if(!from)return;
	var basicForm=from.getForm();
	if(!basicForm.isValid())return ;
	basicForm.submit({
		url:pk.ACTION_SAVE_URL,
		method :'POST',
	//	params:{},
		scope:this,
		clientValidation:true,
		success :function(form ,action){
			Ext.Msg.alert("系统提示","保存成功");
			pk.store.load();
			pk.grid.destroyFormPanel();
			
		},
		failure :function(form ,action ){
			Ext.Msg.alert("错误提示",action.result.msg+"<br/>原因可能是:<br/>"+action.result.object);
		} 
	});	
}
/***
 *数据行
*/
function renderFormValues(records){
		Ext.getCmp("pkId_").setValue(records.get('pkId'));
		Ext.getCmp("pkCode_").setValue(records.get('pkCode'));
		Ext.getCmp("pkCurrentvalue_").setValue(records.get('pkCurrentvalue'));
		Ext.getCmp("pkStartvalue_").setValue(records.get('pkStartvalue'));
		Ext.getCmp("pkPrefix_").setValue(records.get('pkPrefix'));
		Ext.getCmp("pkIncrement_").setValue(records.get('pkIncrement'));
		Ext.getCmp("pkName_").setValue(records.get('pkName'));
		Ext.getCmp("pkMaxvalue_").setValue(records.get('pkMaxvalue'));
		Ext.getCmp("pkRule_").setValue(records.get('pkRule'));
		Ext.getCmp("pkPostfix_").setValue(records.get('pkPostfix'));
		Ext.getCmp("pkDesc_").setRawValue(records.get('pkDesc'));
		Ext.getCmp("pkSequence_").setRawValue(records.get('pkSequence'));
}
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		border:false,
		items:[pk.grid]
	});	
	pk.store.load();
});