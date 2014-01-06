var global={}; //申明命名空间
global.ACTION_LOAD_MENU=Ext.appRootPath+"/commonbiz/aragetAutorizationTree.action";//已授权树

global.TabCloseMenu = function(){
    var tabs, menu, ctxItem;
    this.init = function(tp){
        tabs = tp;
        tabs.on('contextmenu', onContextMenu);
    }

    function onContextMenu(ts, item, e){
        if(!menu){ // create context menu on first right click
            menu = new Ext.menu.Menu([{
                id: tabs.id + '-close',
                text: '关闭',
                handler : function(){
                    tabs.remove(ctxItem);
                }
            },{
                id: tabs.id + '-close-others',
                text: '关闭其他',
                handler : function(){
                    tabs.items.each(function(item){
                        if(item.closable && item != ctxItem){
                            tabs.remove(item);
                        }
                    });
                }
            }]);
        }
        ctxItem = item;
        var items = menu.items;
        items.get(tabs.id + '-close').setDisabled(!item.closable);
        var disableOthers = true;
        tabs.items.each(function(){
            if(this != item && this.closable){
                disableOthers = false;
                return false;
            }
        });
        items.get(tabs.id + '-close-others').setDisabled(disableOthers);
        menu.showAt(e.getPoint());
    }
};
var ps_style_01=Ext.appRootPath+"/skins/default/index/ps-style-01.jpg";
  global.header=new Ext.BoxComponent({
                region:'north',
                el: 'global-header',
                style:"background-image: url('"+ps_style_01+"');background-repeat: no-repeat",
                height:55
  				});
  global.leftPanel=new Ext.Panel({
            id:'left-menu-panel',
            region:'west',
            title:'   ',
            iconCls:'icon-header',
            split:true,
            width: 220,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            layout:'accordion',
            layoutConfig:{
                animate:true,
                titleCollapse:true
            },
            items:null
  });	
  /*		
  global.left=new Ext.tree.TreePanel({
                region:'west',
                title:'<center>功能菜单</center>',
                el: 'global-left',
                width:200,
                split:true,
                collapsible:true,
                autoScroll:true,
                loadMask:true,
	            layoutConfig:{
	                animate:true
	            },
	            rootVisible:false,
	            
	          //  root:new Ext.tree.AsyncTreeNode({id:'0',text:"功能管理菜单",leaf:false,children:[
	          //  			{text:'主键管理',id:'1',leaf:true},
	           // 			{text:'组件管理',id:'2',leaf:true},
	         //   			{text:'功能模块管理',id:'3',leaf:true},
	          //  			{text:'部门管理',id:'4',leaf:true},
	          //  			{text:'组织机构',id:'5',leaf:true},
	          //  			{text:'系统岗位管理',id:'6',leaf:true},
	          //  			{text:'机构人员管理',id:'7',leaf:true},
	          //  			{text:'权限管理',id:'8',leaf:true}
	          //  	  ]
	          //  	}
	          //     ),
	             root:new Ext.tree.TreeNode({
	             	id:'-1',
	             	text:'功能菜单'
	             }), 
	            loader:new Ext.tree.TreeLoader({
						dataUrl: global.LEFT_ACTION_URL
				}),
				listeners:{
					'render':function(){
						var root=global.left.getRootNode();
						var loader=global.left.getLoader();
						loader.baseParams.orgGUID=ORGGUID;
						loader.load(root,function(){
							root.expand();
						});
					},
					'click':function(node){
						
						if(node.leaf){
							var url=node.attributes.href_url;
							var iconurl=node.attributes.icon_url;
							createTab(node.id,iconurl,url,node.text);
						}
					}
				}
  			 })
  */			 
  global.center=new Ext.TabPanel({
                region:'center',
                el: 'global-center',
                activeTab: 0,
                animScroll:true,
                autoScroll:true,
                enableTabScroll:true,
                plugins:new global.TabCloseMenu(),
                items:[
           			 {
                	 xtype:'iframepanel',
                	 id:'global-center-portal',
                	 title:'门&nbsp;&nbsp;&nbsp;户',
                	 loadMask:global.mask,
                	 border:false,
                	 closable:false,
                	 defaultSrc:Ext.appRootPath+'/index/portal.jsp'
            		 }               
                ]
  			 });
global.mask={msg:'<img src="'+Ext.appRootPath+'/skins/default/global/load_01.gif"/>努力加载中...'};	
/*
global.left.on('click',function(node,e){
	if(node.text=="主键管理"){
			var pk_=global.center.getComponent('global-center-'+node.id);
			if(pk_){
				global.center.activate(pk_);
				return;
			}	
           var pk={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/pk/PrimaryKey.jsp'
            } 	
            global.center.add(pk);	
            pk_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(pk_);
	}
	if(node.text=="权限管理"){
			var dpt_=global.center.getComponent('global-center-'+node.id);
			if(dpt_){
				global.center.activate(dpt_);
				return;
			}	
           var dpt_={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/security/authority.jsp'
            } 	
            global.center.add(dpt_);	
            dpt_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(dpt_);		
	}
	if(node.text=='机构人员管理'){
			var dpt_=global.center.getComponent('global-center-'+node.id);
			if(dpt_){
				global.center.activate(dpt_);
				return;
			}	
           var dpt_={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/org/SysUser.jsp'
            } 	
            global.center.add(dpt_);	
            dpt_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(dpt_);		
	}
	if(node.text=="系统岗位管理"){
			var dpt_=global.center.getComponent('global-center-'+node.id);
			if(dpt_){
				global.center.activate(dpt_);
				return;
			}	
           var dpt_={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/org/SysStation.jsp'
            } 	
            global.center.add(dpt_);	
            dpt_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(dpt_);	
	}	
	if(node.text=="组织机构"){
			var dpt_=global.center.getComponent('global-center-'+node.id);
			if(dpt_){
				global.center.activate(dpt_);
				return;
			}	
           var dpt_={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/org/SysOrg.jsp'
            } 	
            global.center.add(dpt_);	
            dpt_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(dpt_);	
	}
	if(node.text=="部门管理"){
			var dpt_=global.center.getComponent('global-center-'+node.id);
			if(dpt_){
				global.center.activate(dpt_);
				return;
			}	
           var dpt_={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/org/SysDepartment.jsp'
            } 	
            global.center.add(dpt_);	
            dpt_=global.center.getComponent('global-center-'+node.id);
            global.center.activate(dpt_);	
	}
	if(node.text=="功能模块管理"){
			var mm=global.center.getComponent('global-center-'+node.id);
			if(mm){
				global.center.activate(mm);
				return;
			}	
           var mm={
               xtype:'iframepanel',
               id:'global-center-'+node.id,
               title:node.text,
               loadMask:global.mask,
               border:false,
               closable:true,
               defaultSrc:Ext.appRootPath+'/sourceweb/commonbiz/security/ModelStruct.jsp'
            } 	
            global.center.add(mm);	
            mm=global.center.getComponent('global-center-'+node.id);
            global.center.activate(mm);
	}	
});*/
Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
	    layout: 'border',
	    items: [
	    	global.header,
	    	global.leftPanel,
			global.center
	    ]
	});
	global.leftPanel.setTitle("正在加载菜单...");	
	loadMenu();//加载菜单信息	
	global.leftPanel.setTitle("功能菜单");
	initIndex();
});
function loadMenu(){
		Ext.Ajax.request({
				url:global.ACTION_LOAD_MENU,
				params:{orgGUID:ORGGUID},
				method:'POST',
				success:function(response ,options){
						var str=response.responseText;			
						var msg=Ext.decode(str);
						if(msg==null)throw new Error("加载菜单失败,菜单为空");
						addMenu(msg);//添加菜单						
				},
				failure:function(response ,options){

						//	var msg=Ext.decode(str);
						Ext.Msg.alert("系统提示","获取菜单失败");
				}
						
		});	
}
function createTab(id,iconUrl,HrefUrl,text){
		var tabObj=global.center.getComponent('global-center-'+id);
		if(tabObj){
			global.center.activate(tabObj);
			return;
		}
		var title_="";
		if(iconUrl!="null"&&iconUrl!=""){
			title="<image  src='"+Ext.appRootPath+"/"+iconUrl+"'/>"
		}else{
			title=text;
		}
		if(HrefUrl==null||HrefUrl=="")return;
		if(/^[^www: | https: |http: | ftp:]\w+/.test(HrefUrl)){
			HrefUrl=Ext.appRootPath+HrefUrl;
		}
		 tabObj={
			xtype:'iframepanel',
			id:'global-center-'+id,
			title:text,
			loadMask:global.mask,
			border:false,
			closable:true,
			//icon:Ext.appRootPath+'/default/grid-tbar/add.png',
			defaultSrc:HrefUrl
		 } 	
		 global.center.add(tabObj);	
		 tabObj=global.center.getComponent('global-center-'+id);
		 global.center.activate(tabObj);
}
/***
 *创建菜单
 */
function addMenu(obj){
	if(obj==null)return;
	for(var i=0;i<obj.length;i++){
		var title_="";
         if(obj[i].icon_url!="null"&&obj[i].icon_url!=""){
               		title_="<center><image src='"+Ext.appRootPath+"/"+obj[i].icon_url+"'/>"+obj[i].text+"<center>";
           }else{
               		title_="<center>"+obj[i].text+"<center>";
          	}		
			rootMenu=new Ext.tree.TreePanel({
                id:'tree-panel-'+obj[i].id,
                title:title_,
                border:false,
                width:200,
                split:true,
                collapsible:true,
                autoScroll:true,
                loadMask:true,
	            layoutConfig:{
	                animate:true
	            },
	            rootVisible:false,	
	            root:new Ext.tree.TreeNode({id:'0',text:'子菜单根节点',expanded:true}),
	            listeners:{
	            	'click':onclick
	            }
	        });   
	       rootMenu.getRootNode().appendChild(addNode(obj[i]));	
		   global.leftPanel.add(rootMenu);
	}
	global.leftPanel.doLayout();
}
/***
 *动态新增节点
 */	
function addNode(obj){
	var node=new Ext.tree.TreeNode({
		id:obj.id,
		text:obj.text,
		leaf:obj.leaf,
		expanded:true,
		href_url:obj.href_url,
		icon:(obj.icon_url!=null&&obj.icon_url!="null"&&obj.icon_url!='')?(Ext.appRootPath+obj.icon_url):''
	});
	if(!obj.leaf){//加载节点
		var cNode=obj.children;
		for(var i=0;i<cNode.length;i++){
				node.appendChild(addNode(cNode[i]));
				//root.appendChild(addNode(cNode[i]));
		}
	}
	//node.ui.hide();
	return node;
}
/***
 *树节点点击事件触发
 */
function onclick(node){
			if(node.leaf){
							var url=node.attributes.href_url;
							url=packURL(url,node.id);
							var iconurl=node.attributes.icon;
							createTab(node.id,iconurl,url,node.text);
			}
}
/***
 *加载页面信息
 */
function initIndex(){
	Ext.get('userInfo').dom.innerHTML=userName1;
}