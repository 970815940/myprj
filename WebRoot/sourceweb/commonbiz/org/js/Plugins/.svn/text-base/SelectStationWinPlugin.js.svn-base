/*****
 *@该类功能描述:用户选择岗位的窗口
 *@编写时间:2013-9-6 17:20
 *@编写人:taoxs@sysway.cn
 *@version 1.0 
 */
 
 
 //定义全局变量
 var COMMON_STATION={
 	ACTION_PATH:Ext.appRootPath+"/commonbiz/ssgetSysStationByList.action",
 	gridPanel:null,
 	winOkBtn:null,
 	winCancelBtn:null,
 	winPnl:null,
 	getValue:null
 }
 Ext.onReady(function(){
 	Ext.QuickTips.init();
 	/**
	 * 数据源(ds)定义
     */	
	var ds = new Ext.data.Store({ 
		proxy: new Ext.data.HttpProxy({
			url: COMMON_STATION.ACTION_PATH
		}),
		baseParams: {
			limit: 20
        },
		reader: new Ext.data.JsonReader(
			{
				totalProperty: 'allRowCount',
				root: 'root'
			}, [
				{
					name: 'guid'
				}, { 
					name: 'ssId'
				},  {
		            name: 'ssDispalyname'
		        }, {
		            name: 'ssDemo'
		        }
		]),
		listeners: {
		    loadexception: function(){
		       	top._alert('加载数据失败,请检查网络');
		   	}
		}
	});	
	//表格定义	
 	//定义选择模型(复择框)
	var sm = new Ext.grid.CheckboxSelectionModel();
	sm.on("rowdeselect",function(sm, rowIndex, record){ 
		if(record.data.isPrivate) {
			sm.selectRow(rowIndex,true);
		}
	});	
	/**
	 * 列模型(cm)定义
	 */
	var cm = new Ext.grid.ColumnModel([ 
		new Ext.grid.RowNumberer(), sm,//行号及复选框定义
		{
	        header: 'guid',
	        dataIndex: 'guid',
	        hidden:true
	    }, {
	        header: '岗位名称',
	        dataIndex: 'ssDispalyname',
	        resizable: true,
	        fixed: true,
	        width:300,
	        sortable: true,
	        align: 'center'
	    },, {
	        header: '岗位描述',
	        dataIndex: 'ssDemo',
	        resizable: true,
	        fixed: true,
	        width:300,
	        sortable: true,
	        align: 'center'
	    }
	    	
	]);	
	/**
	 * 列表控件(grid)的定义
	 */
	COMMON_STATION.gridPanel = new Ext.grid.GridPanel({
		id:"position-grid-pnl", 
		sm: sm,
		ds: ds,
		cm: cm,
		frame: false,
		enableColumnMove: true,
		stripeRows: true,
		height:352,
		width:491,
		autoScroll:true,
		viewConfig: {
			forceFit: true
		},
		loadMask:{
		    	msg: "正在加载数据..."
		},
        bbar: new Ext.PagingToolbar({ //定义分页条
            pageSize: 15,
            store: ds,
            displayInfo: true,
            displayMsg: '显示第 {0} 条到 {1} 条记录， 共 {2} 条',
            emptyMsg: "没有记录"
        })	
	});
	COMMON_STATION.winOkBtn=new Ext.Button({text:" 确 定 "});
	COMMON_STATION.winCancelBtn=new Ext.Button({text:" 取&nbsp;消 "});
 	COMMON_STATION.winPnl=new Ext.Window({
 		title:'选择岗位',
 		closeAction:'hide',
 		autoScroll:true,
 		closable:false,
 		width:505,
 		height:402,
 		items:COMMON_STATION.gridPanel,
 		buttons:[
	 		COMMON_STATION.winOkBtn,
	 		COMMON_STATION.winCancelBtn
 		]
 	});
 });
 /***
  *获取值
  *@param allowMulti 是否多选 true  false=单选 
  */
  COMMON_STATION.getValue=function(allowMulti){
 	var selectedRows = COMMON_STATION.gridPanel.getSelectionModel().getSelections();
 	var guids = new Array();
 	if (selectedRows && selectedRows.length > 0) {
 		if (allowMulti == false && selectedRows.length > 1) {
			top._alert("只能选择一条记录");
			return null;
		}
		for (var i=0; i<selectedRows.length; i++) {
			guids.push({
				guid:selectedRows[i].data.guid,
				ssId:selectedRows[i].data.ssId,
				ssDispalyname:selectedRows[i].data.ssDispalyname
			});
		}	
		return (allowMulti == false ? guids[0] : guids);			
 	}else {
		Ext.Msg.alert("系统提示","请先选择记录再确定!");
	}
 }
 /****
  *
  *绑定事件
  */
 function relayEvent(callFun,allowMulti){
 	COMMON_STATION.winOkBtn.purgeListeners();
 	COMMON_STATION.winCancelBtn.purgeListeners();
  	COMMON_STATION.winOkBtn.on({
  		"click":function(){  		
	  		var value = COMMON_STATION.getValue(allowMulti);
	  		if(value == null) return;
	  		
	  		COMMON_POSITION.winPnl.hide();
	  		if(callFun != null) {
	  			callFun(value);
	  		}
  		}
  	}); 
	COMMON_STATION.winCancelBtn.on({
		click:function(){
		  	COMMON_STATION.winPnl.hide();
		  	if(callFun != null) {
		  		callFun(null);
		  	}	  		
  		}
	});  		
 }
 /********用户接口****************/
//allowMulti 是否允许多选， true 为是[默认], false为单选
function selectSysStation(callbackFun,allowMulti) {
	COMMON_STATION.registryEvt(callbackFun,allowMulti);
	COMMON_STATION.winPnl.show();
	var ds = COMMON_STATION.gridPanel.getStore();
	ds.load();
}