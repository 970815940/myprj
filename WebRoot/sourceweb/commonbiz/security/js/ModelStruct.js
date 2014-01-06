var ms={};
//功能节点URL
ms.ACTION_GRID_LIST_URL=Ext.appRootPath+"/commonbiz/megetModelEntityForMSByList.action";
ms.ACTION_SAVE_URL=Ext.appRootPath+"/commonbiz/mesaveModelEntity.action";
ms.ACTION_GETBYID_URL=Ext.appRootPath+"/commonbiz/megetModelEntityById.action";
ms.ACTION_DELETE_URL=Ext.appRootPath+"/commonbiz/medeleteModelEntity.action";
//结构URL
ms.ACTION_TREE_URL=Ext.appRootPath+"/commonbiz/msgetModelStructTree.action";
ms.ACTION_SAVE_STRUCT_URL=Ext.appRootPath+"/commonbiz/mssaveModelStruct.action";
ms.ACTION_DELETE_STRUCT_URL=Ext.appRootPath+"/commonbiz/msdeleteModelStruct.action";
ms.ACTION_FINDBYID_STRUCT_URL=Ext.appRootPath+"/commonbiz/msgetModelStructById.action";
ms.loader=new Ext.tree.TreeLoader({
	dataUrl:ms.ACTION_TREE_URL,
	baseParams:{}
});
var treeStructTool=[ 
			{
				text:'新增',
				icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
				iconCls:'--',
				handler:StructAdd		
			},
			{ 
				text:'删除',
				icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
			    iconCls:'--',
			    handler:StructDelete	
			},
			{
				text:'编辑',
			    icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
			    iconCls:'--',
			    handler:StructEdit			
			}
];
ms.struct=new Ext.tree.TreePanel({
				tbar:treeStructTool,
                region:'west',
                title:'<center>功能结构</center>',
                id: 'model-struct-left',
                width:200,
                split:true,
                border:false,
                autoScroll:true,
	            layoutConfig:{
	                animate:true
	            },
	            rootVisible:true,
	            root:new Ext.tree.TreeNode({id:'-1',text:"模块资源",leaf:false
	            	}
	            ),
	            loader:ms.loader,
			    /***
			     *定义销毁编辑组件的方法
			     */
			    destoryForm:function(){
			    	var formWin=Ext.getCmp("ModelStruct-struct-edit-win-panel");
			    	if(formWin)formWin.destroy();
			    },
			    /**
			     *定义编辑结构表单
			     *@return  表单组件对象 
			     */
			    getInstanceEditPanel:function(){
			    	ms.struct.destoryForm();
			    	var form=new Ext.form.FormPanel({
			    		id:'ModelStruct-struct-edit-form-panel',
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
				  					  	{xtype:'hidden',id:'msId_',name:'ms.msId'},
				  					  	{xtype:'hidden',id:'msParentid_',name:'ms.msParentid'},
				  					  		{
				  					  		  fieldLabel:'名称',
				  					  		  allowBlank:false,
				  					  		  width:140,
				  					  		  id:'msDisplayname_',
				  					  		  name:'ms.msDisplayname'
				  					  		},
				  					  		{
				  					  		  id:'msState_',
				  					  		  name:'ms.msState',
				  					  		  fieldLabel:'状态',
				  					  		  xtype:'combo',
											  width:140,
											  value:'1',
											  readOnly:true,
											  forceSelection:false,
											  hiddenName:'ms.msState',
											  store:new Ext.data.SimpleStore({
											    	fields:['value','text'],
											    	data:[['1','可用'],['2','禁用']]
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
				  					  defaultType:'numberfield',
				  					  items:[
				  					  	 {
				  					  	 	fieldLabel:'顺序号',
				  					  	 	width:140,
				  					  	 	value:0,
				  					  	 	id:'ssIndex_',
				  					  	 	name:'ss.ssIndex'
				  					  	 },
				  					  	 {
				  					  	      fieldLabel:'节点类型',
				  					  		  xtype:'combo',
											  width:140,
											  value:'1',
											  readOnly:true,
											  id:'msType_',
											  forceSelection:false,
											  hiddenName:'ms.msType',
											  store:new Ext.data.SimpleStore({
											    	fields:['value','text'],
											    	data:[['1','功能结构'],['2','功能点']]
											  }),
											  valueField:'value',
											  displayField:'text',
											  mode:'local',
											  triggerAction:'all'						  					  	  
				  					  	 }				  					 
				  					  ]
				  					}
				  				]
				  		},
				  		{
				  			fieldLabel:'图标路径',
				  			xtype:'textfield',
				  			width:370,
				  			name:'ms.msIconurl',
				  			id:'msIconurl_'
				  		},
				  		{
				  			xtype:'textarea',
				  			width:370,
				  			height:100,
				  			fieldLabel:'描述',
				  			id:'msDemo_',
				  			name:'ms.msDemo'
				  		}
				  		]		
			    	});
			    	new Ext.Window({
			    		title:'编辑资源结构详细信息',
			    		items:form,
			    		buttonAlign:'center',
			    		modal:true,
			    		id:'ModelStruct-struct-edit-win-panel',
			    		width:500,
			    		height:300,
			    		buttons:[
			    			{text:'保存',handler:saveStruct},
			    			{text:'取消',handler:ms.struct.destoryForm}
			    		]
			    	}).show();
			    	return form;
			    }	            	            
	        });
ms.struct.on('click',function(node,e){//监听树节点点击事件
		if(!node.isLeaf())return;
		if(node.attributes.msType=='1')return ;
		ms.model_store.baseParams.meMsid=node.id;
		ms.model_store.reload();
});        
ms.model_columns=[
	{header:'id',dataIndex:'meId',hidden:true},
	{header:'显示名称',dataIndex:'meDispalyname'},
	{header:'创建时间',dataIndex:'meCreatetime'}
]	
ms.model_tbar=[
	{ text:'新增',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/add.png',
	  iconCls:'--',
	  handler:function(){
	  	var node=ms.struct.getSelectionModel().getSelectedNode();
	  	if(!node){
	  		Ext.Msg.alert("系统提示","请选择功能模块资源")
	  		return;
	  	}
	  	var nodeType=node.attributes.msType;
	  	if(nodeType!="2"){
	  		Ext.Msg.alert("系统提示","只能在功能节点上添加功能");
	  			return;
	  	}
	  /*	if(!node.isLeaf()){
	  		Ext.Msg.alert("系统提示","非子节点上不能添加功能");
	  		return;
	  	}
	  */	
	  	ms.grid.getInstanceEditForm();
	  	Ext.getCmp("meMsid_").setValue(node.id);
	  },
	  scope:this
	},
	{ text:'删除',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/delete.png',
	  iconCls:'--',
	  handler:function(){
	  		var rows=ms.grid.getSelectionModel().getSelections();
	  		if(rows.length==0){
	  			Ext.Msg.alert("系统提示","请选择功能后,再删除");return ;
	  			return;
	  		}
	  		if(rows.length>1){
	  			Ext.Msg.alert("系统提示","只能选择一条数据进行删除,默认为所选数据中的第一条");
	  			return ;
	  		}	
	  		Ext.Msg.confirm("警告","你确认删除吗?删除后数据无法找回",function(btn){
	  			if(btn=='yes'){
	  					var meid_=rows[0].get('meId');
				  		Ext.Ajax.request({
							url:ms.ACTION_DELETE_URL,
							params:{meId:meid_},
							method:'POST',
							success:function(response ,options){
								var msg=Ext.decode(response.responseText);
								Ext.Msg.alert("系统提示",msg.msg);
								ms.model_store.load();
							},
							failure:function(response ,options){
								var msg=Ext.decode(response.responseText);
								Ext.Msg.alert("系统提示","删除失败_<br/>"+msg.msg);
							}
						
						});	  			
	  			}
	  		});  
	  },
	  scope:this
	},
	{ text:'编辑',
	  icon:Ext.appRootPath+'/skins/default/grid-tbar/edit.png',
	  iconCls:'--',
	  handler:function(){
	  		var rows=ms.grid.getSelectionModel().getSelections();
	  		if(rows.length==0){
	  			Ext.Msg.alert("系统提示","请选择功能后,再编辑");return ;
	  			return;
	  		}
	  		if(rows.length>1){
	  			Ext.Msg.alert("系统提示","只能选择一条数据进行编辑,默认为所选数据中的第一条");
	  			return ;
	  		}
	  		var meid_=rows[0].get('meId');
	  		Ext.Ajax.request({
				url:ms.ACTION_GETBYID_URL,
				params:{meId:meid_},
				method:'POST',
				success:function(response ,options){
					var msg=Ext.decode(response.responseText);
					ms.grid.getInstanceEditForm();
					msg=msg.object;
					setDataForm(msg);
				},
				failure:function(response ,options){
					var msg=Ext.decode(response.responseText);
					Ext.Msg.alert("系统提示","编辑失败_<br/>"+msg.msg);
				}
			
			});
	  },
	  scope:this
	}		
]
ms.model_sm=new Ext.grid.RowSelectionModel({singleSelect:true});
ms.model_store=new  Ext.data.Store({
		        proxy: new Ext.data.HttpProxy({
               		 url:ms.ACTION_GRID_LIST_URL
       			}),
       			reader: new Ext.data.JsonReader({
       				   totalProperty: 'allRowCount',
            		   root: 'root'},
            		   [
            			{name:'meId'},
            			{name:'meDispalyname'},
            			{name:'meCreatetime'},
            			{name:'meMsid'}
              ]),
              listeners: {
            		loadexception: function(){
               			 Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
             		}
       		 }
       }); 
ms.model_bbar = new Ext.PagingToolbar({
	pageSize: 20,
	store:ms.model_store,
	displayInfo: true,
	displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条{3}{4}{5}</span>',
    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
});             
ms.grid=new Ext.grid.GridPanel({
		region:'center',
		title:'功能模块',
	    viewConfig:{
	        forceFit:true
	    },
	    border:false,
	    frame:true,
	    sm:ms.model_sm,	
		store:ms.model_store,
		columns:ms.model_columns,
		bbar:ms.model_bbar,
		tbar:ms.model_tbar,
	    enableColumnMove:false,
	    stripeRows:true,
	    animCollapse:true,
	    trackMouseOver:true,
	    loadMask:true,
	    destroyFormPanel:function(){//定义销毁编辑窗口的方法
	    	var win=Ext.getCmp("ms-edit-win")
	    	if(win)win.destroy();
	    },
	    getInstanceEditForm:function(){//创建获取新增编辑窗口的方法窗口的时候,
	    	this.destroyFormPanel();//为了防止同时存在多个编辑窗口,每创建执行删除一次
	    	var form=new Ext.form.FormPanel({
				id:'ms-grid-edit-form-panel',
		    	labelAlign:'right',
		    	bodyStyle : 'padding:5px 5px 0',
		    	labelWidth:60,
		    	widht:500,
		    	anchor : '95%',
		    	frame:true,
		    	items:[
		    		{
		    		  layout:'column',
		    		  items:[
		    		  	{
		    		  		layout:'form',
		    		  		columnWidth:.5,
		    		  		defaultType:'textfield',
		    		  		items:[
		    		  			{
		    		  				xtype:'hidden',
		    		  				name:'me.meId',
		    		  				id:'meId_'
		    		  			},
		    		  			{
		    		  				xtype:'hidden',
		    		  				name:'me.meMsid',
		    		  				value:'',
		    		  				allowBlank :false,
		    		  				id:'meMsid_'
		    		  			},
		    		  			{
		    		  				fieldLabel:'名称',
		    		  				allowBlank :false,
		    		  				name:'me.meDispalyname',
		    		  				id:'meDispalyname_',
		    		  				width:130,
		    		  				value:''
		    		  			},
		    		  			{
		    		  			  fieldLabel:'图标URL',
		    		  			  name:'me.meIconurl',
		    		  			  id:'meIconurl_',
		    		  			  width:130
		    		  			}
		    		  		]
		    		  	
		    		  	},
		    		  	{
		    		  		columnWidth:.5,
		    		  		layout:'form',
		    		  		defaultType:'textfield',
		    		  		items:[
		    		  			{
		    		  				fieldLabel:"URL",width:130,
		    		  				allowBlank :false,
		    		  				name:'me.meUrl',
		    		  				id:'meUrl_',
		    		  				value:''
		    		  			},
		    		  			{
		    		  				fieldLabel:'是否显示',
		    		  				xtype:'combo',
		    		  				width:130,
		    		  				name:'me.meIsshow',
		    		  				id:'meIsshow_',
		    		  				value:'1',
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
		    		  	}
		    		  ]
		    		},
		    		{
		    		 xtype:'textarea',
		    		 fieldLabel:'描述',
		    		 name:'me.meDemo',
		    		 id:'meDemo_',
		    		 width:355,
		    		 height:130
		    		}
		    	]
	    	});
	    	new Ext.Window({
	    		id:'ms-edit-win',
	    		items:form,
	    		modal:true,
	    		height:300,
	    		width:500,
	    		title:'编辑功能',
	    		buttons:[
	    			{text:'确定',handler:function(){
	    				saveModelEntity();
	    			}},
	    			{text:'取消',handler:function(){
	    				this.ownerCt.destroy();
	    			}}
	    		]
	    	}).show();
	    }
    });
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:[
			ms.struct,ms.grid
		]
	});
	loadTree();
	ms.struct.root.attributes.msType='1';
});
/************************************************FUN***********/
/**
 *刷新树
 */
function loadTree(){
	var root=ms.struct.root;
	var treeload=ms.struct.getLoader();
	treeload.load(root,function(){
		root.expand();
	});
}
/****
  *
  *保存功能实体.
  */
function saveModelEntity(){
	var form_tmp=Ext.getCmp("ms-grid-edit-form-panel");
	form_tmp.getForm().submit({
		url:ms.ACTION_SAVE_URL,
		method :'POST',
		params :{},
		scope :this,
		clientValidation :true,
		success :function(form ,action){
			Ext.Msg.alert("系统提示","保存成功");
			ms.model_store.load();
			ms.grid.destroyFormPanel();
			
		},
		failure :function(form,action){
			Ext.Msg.alert("系统提示","保存失败!");
		}
	});
}
/***
 *把值注入到FORM表单中
 *@param data 数据源 
 */
function setDataForm(data){
	Ext.getCmp("meId_").setValue(data.meId);
	Ext.getCmp("meMsid_").setValue(data.meMsid);
	Ext.getCmp("meDispalyname_").setValue(data.meDispalyname);
	Ext.getCmp("meIconurl_").setValue(data.meIconurl);
	Ext.getCmp("meUrl_").setValue(data.meUrl);
	Ext.getCmp("meDemo_").setValue(data.meDemo);
	Ext.getCmp("meIsshow_").setValue(data.meIsshow);
	
}
var structForm=null;//功能结构编辑表单；
/****
 *新增结构按钮触发
 */
function StructAdd(){
	var treenode=ms.struct.getSelectionModel().getSelectedNode();
	if(!treenode)return;
	var nodetype=treenode.attributes.msType;
	if(!nodetype)return ;
	if(nodetype=='2'){
		Ext.Msg.alert("系统提示","功能节点不允许添加结构文件夹");
		return;
	}
	structForm=ms.struct.getInstanceEditPanel();
	Ext.getCmp("msParentid_").setValue(treenode.id);
}
/***
 *点击功能结构保存按钮时触发
 */
function saveStruct(){
	if(!structForm)return;
	structForm.getForm().submit({
		url:ms.ACTION_SAVE_STRUCT_URL,
		method :'POST',
		params :{},
		scope :this,
		clientValidation :true,
		success :function(form ,action){
			Ext.Msg.alert("系统提示","保存成功");
			loadTree();
			ms.struct.destoryForm();
			ms.model_store.load();
		},
		failure :function(form,action){
			Ext.Msg.alert("系统提示","保存失败!<BR/>"+action.result.msg);
		}
	});
}
/*****
 *
 *结构维护  -->删除按钮触发
 */
function StructDelete(){
	var treenode=ms.struct.getSelectionModel().getSelectedNode();
	if(!treenode)return;
	if(!treenode.isLeaf()){
		Ext.Msg.alert("系统提示","非空节点不能删除");
		return;
	}
	var rows=0;
	Ext.Ajax.request({//根据结构获取功能点
			url:ms.ACTION_GRID_LIST_URL,
			params:{msId:treenode.id},
			method:'POST',
			scope:this,
			success:function(response ,options){
					var msg=Ext.decode(response.responseText);
					rows=msg.allRowCount;
					if(rows>0){
						Ext.Msg.confirm("系统提示","该结构功能下面包含<span style=color:red>"+rows+"</span>个功能节点，删除结构同时将删除功能节点，你确认删除吗?",function(btn){
							if(btn=='yes'){
								delStuuctInstance(treenode.id);
							}
						});	
						return;					
					}
					delStuuctInstance(treenode.id);
					
		    },
			failure:function(response ,options){
					var msg=Ext.decode(response.responseText);
					Ext.Msg.alert("系统提示","获取功能个数失败_<br/>"+msg.msg);
			}	
	});		

}
/***
 *
 *结构上 编辑按钮触发 
 */
function StructEdit	(){
	var treenode=ms.struct.getSelectionModel().getSelectedNode();
	if(!treenode)return;
	structForm=ms.struct.getInstanceEditPanel();
	var msId=treenode.id;
	Ext.Ajax.request({
		url:ms.ACTION_FINDBYID_STRUCT_URL,
		params:{msId:msId},
		method:'POST',
		success:function(response ,options){
				var msg=Ext.decode(response.responseText);
				var data=msg.object;
				Ext.getCmp("msId_").setValue(data.msId);
				Ext.getCmp("msParentid_").setValue(data.msParentid);
				Ext.getCmp("msDisplayname_").setValue(data.msDisplayname);
				Ext.getCmp("msState_").setValue(data.msState);
				Ext.getCmp("msDemo_").setValue(data.msDemo);
				Ext.getCmp("msType_").setValue(data.msType);
				Ext.getCmp("msIconurl_").setValue(data.msIconurl);
		},
		failure:function(response ,options){
					var msg=Ext.decode(response.responseText);
					Ext.Msg.alert("系统提示","删除失败_<br/>"+msg.msg);
		}	
	});		
}
/***
 *删除结构
 *@param id 结构ID 
 */
function delStuuctInstance(id){
				 Ext.Ajax.request({
					url:ms.ACTION_DELETE_STRUCT_URL,
					params:{msId:id},
					method:'POST',
					success:function(response ,options){
							var msg=Ext.decode(response.responseText);
							loadTree();
							ms.model_store.load();
					},
					failure:function(response ,options){
							var msg=Ext.decode(response.responseText);
							Ext.Msg.alert("系统提示","删除失败_<br/>"+msg.msg);
					}	
				});		
}