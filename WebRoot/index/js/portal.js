var ACTION_GETPORTAL_URL=Ext.appRootPath+"/basic/wspgetUserForPorlet.action";

var lp = new Ext.ux.PortalColumn({
	  id: 'left-portal',
      columnWidth: .33,
      bodyStyle:'padding:2px',
      items: null
 });
var rp = new Ext.ux.PortalColumn({
	  id: 'right-portal',
      columnWidth: .33,
      bodyStyle:'padding:2px',
      items: null
 }); 
 var cp = new Ext.ux.PortalColumn({
	  id: 'center-portal',
      columnWidth: .33,
      bodyStyle:'padding:2px',
      items: null
 }); 
 
 var portalPanel = new Ext.ux.Portal({ 
        xtype:'portal',
        region:'center',
        bodyStyle:'overflow-x:hidden;background-color:#eefafb;border-width:0',
        items:[lp,cp,rp]
});

portalPanel.on({
	drop: function(evt){
 		var cId = evt.column.id;
 		var index = evt.position;
 		var panelId=evt.panel.id;
 		moveLocation(panelId,cId,index);
	}
});
var kj=new Ext.Panel({
	height:100,
	region:'south'
});
Ext.onReady(function(){
	Ext.QuickTips.init();
    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
    new Ext.Viewport({
    	layout:'border',
    	items:[
    		portalPanel,kj
    	]
    });	
    getPortal();
});
/***********************************fun定义区****************************************/
/****
 *
 *改变位置
 */
function moveLocation(pid,postionId,index){
	var pos="";
	if(postionId=='left-portal'){
		pos="l";
	}else if(postionId=='center-portal'){
		pos="c";
	}else if(postionId=='right-portal'){
		pos='r';
	}else{
		throw new Error("获取组件位置发生异常");
	}
	Ext.Ajax.request({
					  url:Ext.appRootPath+"/basic/wspsaveWorkspanceporlet.action",
					  params:{
					  	'wsp.id':pid,
					  	'wsp.position':pos,
					  	'wsp.index':index
					  },
					  scope :this,
					  success :function(response ,options ){
							var obj=Ext.decode(response.responseText);
							if(obj.success===true){
								Ext.example.msg("系统提示","操作成功!");							
							}else{
								top._error(obj.msg);
							}
					  },
					  failure :function(response ,options){
					  		top._error("服务器内部发生错误");
					  }
	});	
	
}
/****
 *准备构建组件
 */
function installPortal(root){
	if(root==null||root.length==0)return;
	var leftP=[];
	var rightP=[];
	var centerP=[];
	for(var i=0;i<root.length;i++){
		switch(root[i].position){
			case 'l':
				leftP.push(root[i]);
				break;
			case 'r':
				rightP.push(root[i]);
				break;
			case 'c':
				centerP.push(root[i]);
				break;
			default:
				throw new Error("组件创建出错");
		}
	}
	var itemL=[],itemR=[],itemC=[];
		if(leftP.length!=0){
			 itemL=createPortal(leftP);
		}
		if(rightP.length!=0){
			 itemR=createPortal(rightP);
		}	
		if(centerP.length!=0){
			 itemC=createPortal(centerP);		
		}
		if(itemL!=null){
			for(var i=0;i<itemL.length;i++){
				lp.add(itemL[i]);
			}		
			for(var i=0;i<itemR.length;i++){
				rp.add(itemR[i]);
			}	
			for(var i=0;i<itemC.length;i++){
				cp.add(itemC[i]);
			}				
		}


	
	//if(rightP.length!=0){
	//	createPortal(rightP);
	//}
	//if(centerP.length!=0){
	//	createPortal(centerP);
	//}
	portalPanel.doLayout();
}
/***
 *
 *创建组件
 */
function createPortal(columns){
	var items=[];
	if(columns==null)return null ;
	for(var i=0;i<columns.length;i++){
		//
		var tmp={
			id:columns[i].id,
			xtype:'portlet',
			tools:[{id:'gear',handler:setWorkSpancePortlet},{id:'close',handler:delWSPANCE}],
			title:columns[i].portletid.title,
			width:columns[i].portletid.width,
			height:columns[i].portletid.height,
			html:'<iframe src="' + columns[i].portletid.url + '" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>'
	   }
	   
	   if(columns[i].portletid.optional=='1'){
	   		Ext.apply(items[i], {tools:null}, true);
	   }
	   items.push(tmp);
	}
	return items;
}
/***
 *获取组件
 */
function getPortal(){
	Ext.Ajax.request({
		url:ACTION_GETPORTAL_URL,
		params:{UserId:ORGGUID},
		success:function(r,o){
			var obj=Ext.decode(r.responseText);
			if(obj.success===true){
				installPortal(obj.root);
			}else{
				top._error(obj.msg);
			}
		},
		failure:function(r,o){
			top._error("组件获取失败，服务器内部发生错误");
		}
	});
}
/*******
 *
 *设置工作空间组件
 */
function setWorkSpancePortlet(){
	var store=new  Ext.data.Store({
			        proxy: new Ext.data.HttpProxy({
	               		 url: Ext.appRootPath+"/basic/wspgetPortletSetList.action"
	       			}),
	       			reader: new Ext.data.JsonReader({
	       				   totalProperty: 'allRowCount',
	            		   root: 'root'},
	            		   [
	            		   	{name:'istrue'},
	            			{name:'portlet.id'},{name:'portlet.title'},
	            			{name:'portlet.url'},{name:'portlet.describe'}	,
	            			{name:"portlet.width"},{name:"portlet.height"} ,
	            			{name:'portlet.available'}  ,{name:'portlet.defaultportal'},
	            			{name:'portlet.optional'}
	              ]),
	              listeners: {
	            		loadexception: function(){
	            			top._error("对不起，与数据库的交互出错！");
	             		}
	       		 }
	       });
	var bbar = new Ext.PagingToolbar({
		pageSize: 20,
		store:store,
		displayInfo: true,
		displayMsg: '<span style="font-weight: bolder;">显示第 {0} 条到 {1} 条记录， 共 {2} 条</span>',
	    emptyMsg: '<span style="font-weight: bolder;">没有记录</span>'
	});	
	new Ext.Window({
		title:'用户组件维护',
		width:500,
		height:300,
		modal:true,
		items:[
				new Ext.grid.GridPanel({
						id:'userPortlet',
						width:500,
						height:300,
					    viewConfig:{
					        forceFit:true
					    },
					    frame:true,
					    sm:new Ext.grid.RowSelectionModel({singleSelect:true}),	
						store:store,
						columns:[
							{header:'id',dataIndex:'portlet.id',hidden:true},
							{header:'istrue',dataIndex:'istrue',hidden:true},
							{header:'标题',dataIndex:'portlet.title'},
							{header:'地址',dataIndex:'portlet.url',renderer:function(val){return "<div title="+val+">"+val+"</div>"}},
							{header:'宽度',dataIndex:'portlet.width'},
							{header:'高度',dataIndex:'portlet.height'},
							{header:'操作',renderer:function(value, metaData, record, rowIndex, colIndex, store) {
								var str="";
								if(record.get('istrue')==0){
									str="<a href=\"javascript:addWSPANCE('"+record.get('portlet.id')+"')\">添加</a>";
								}else{
									str="<img src='"+Ext.appRootPath+"/skins/default/portlet/icon-complete.gif'/>";
								}
								return str;
							}}
						] ,
						bbar:bbar,
					    enableColumnMove:false,
					    stripeRows:true,
					    animCollapse:true,
					    trackMouseOver:true,
					    loadMask:true
			})	
		],
		listeners:{
			'close':function(){
				window.location.reload();
			}
		}
	}).show();
	store.load();
}
//添加用户定义组件
function addWSPANCE(pid){
	Ext.Ajax.request({
					  url:Ext.appRootPath+"/basic/wspsaveWorkspanceporlet.action",
					  params:{
					  	'wsp.portletid.id':pid,
					  	'wsp.id':''
					  },
					  scope :this,
					  success :function(response ,options ){
							var obj=Ext.decode(response.responseText);
							if(obj.success===true){
								Ext.example.msg("提示","成功!");
								Ext.getCmp("userPortlet").getStore().load();							
							}else{
								top._error(obj.msg);
							}

					  },
					  failure :function(response ,options){
					  		top._error("组件添加失败,可能是服务器内部发生错误!");
					  }
	});	
	
	
}
//删除用户定义组件
function delWSPANCE(evt, toolEl, panel){
	var wsId=panel.id;
	Ext.Ajax.request({
					  url:Ext.appRootPath+"/basic/wspdeleteWorkspanceporlet.action",
					  params:{
					  	wspId:wsId
					  },
					  scope :this,
					  success :function(response ,options ){
							var obj=Ext.decode(response.responseText);
							if(obj.success===true){
								Ext.example.msg("提示","成功!");	
								panel.ownerCt.remove(panel, true); 								
							}else{
								top._error(obj.msg);
							}
					  },
					  failure :function(response ,options){
					  		top._error("移除意见发生错误");
					  }
	});		
}

