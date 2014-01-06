var grid=new  Ext.Panel({
	title:'文件长传测试',
	region:'center',
	frame:true,
	html:'<div id="textAre"></div>',
	items:[
		{xtype:'button',text:'上传文件',handler:function(){
			Ext.get("textAre").dom.innerHTML="123";
			 var att=new Ext.ux.Attachment();
				att.showAttWin();
		}}
	]
});

Ext.onReady(function(){
	Ext.QuickTips.init();
	new Ext.Viewport({
		layout:'border',
		items:grid
	});

});