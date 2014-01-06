Ext.namespace("SSP.ux");
SSP.ux.Panel=Ext.extend(Ext.Panel,{
	width:300,
	height:300,
	autoScroll:true,
	id:'',
	ICON_SAVE:Ext.appRootPath+'/skins/default/sspPanel/save.png',
	ICON_DELETE:Ext.appRootPath+'/skins/default/sspPanel/delete.png',
	ICON_UPDATE:Ext.appRootPath+'/skins/default/sspPanel/edit.png',
	ICON_ADD:Ext.appRootPath+'/skins/default/sspPanel/add.png',
	initComponent:function(){
		if(!this.tbar)this.tbar=[];
		SSP.ux.Panel.superclass.initComponent.call(this);
		this.addEvents(
			"beforeDelete",
			"beforeAdd",
			"beforeSave",
			"beforeUpdate",
			"afterDelete",
			"afterAdd",
			"afterSave",
			"afterUpdate"
		);
	},
	constructor:function(config){
		Ext.apply(this,config);//新属性 覆盖旧属性
		//Ext.applyif(this,config);//新属性 不覆盖旧属性
		SSP.ux.Panel.superclass.constructor.call(this);
	},
	onRender:function(ct, position){
		SSP.ux.Panel.superclass.onRender.call(this, ct, position); 
		this.initTopToolbarButtons();
	},
	initTopToolbarButtons:function(){
			if (this.topToolbar) {
           		 var t = this.topToolbar;	
           		 var btns=[
           		 	{id:'save-btn-'+this.id,text:'保存',cls:'x-btn-text-icon',icon:this.ICON_SAVE,scope:this},
           		 	{id:'delete-btn-'+this.id,text:'删除',cls:'x-btn-text-icon',handler:this.onBeforeDelete,icon:this.ICON_DELETE,scope:this},
           		 	{id:'update-btn-'+this.id,text:'修改',cls:'x-btn-text-icon',icon:this.ICON_UPDATE,scope:this},
           		 	{id:'add-btn-'+this.id,text:'新增',cls:'x-btn-text-icon',icon:this.ICON_ADD,scope:this}
           		 ]
           		 t.add(btns);
           	}else{
           		this.topToolbar=[];
           		this.initTopToolbarButtons();
           	}	
	},
	onBeforeDelete:function(){
		this.fireEvent('beforeDelete',this.id,this.title);
		alert("代码执行成功");
		this.fireEvent('afterDelete',this.id,this.title);
	}
	
});
var pan=new SSP.ux.Panel({region:'center',title:'test'});
pan.on("beforeDelete",function(id,title){
	alert("删除前");
})
pan.on("afterDelete",function(id,title){
	alert("删除后");
})
Ext.onReady(function(){
	//new Ext.Viewport({
	///	layout:'border',
	///	items:pan
	//});
	new SSP.ux.Panel({region:'center',title:'test',renderTo:'ee'});
	
});