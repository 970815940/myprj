/*****
 *该类依赖  jslib/tools/GridWindow.js
 *@该类功能描述:用户选择岗位的窗口
 *@编写时间:2013-9-6 17:20
 *@编写人:taoxs@sysway.cn
 *@version 1.0 
 */
Ext.ux.StationSelectWin=Ext.extend(Ext.ux.GridWindow,{
	url:Ext.appRootPath+"/commonbiz/ssgetSysStationByList.action",
	cm: [
			{header:'guid',dataIndex:'guid',hidden:true},
			{header:'岗位名称',dataIndex:'ssDispalyname'},
			{header:'描述',dataIndex:'ssDemo'}
	],	
	title: '选择岗位',
	width:400,
	height:400,
    doFilter: function(value) {
        this.loader.baseParams.ssDispalyname = (value == "" ? null : value);
        Ext.ux.StationSelectWin.superclass.doFilter.call(this, value);
    },
    onRowDblClick: function(grid, rowIndex, evt) {
       Ext.ux.StationSelectWin.superclass.onRowDblClick.call(this, grid, rowIndex, evt);
      this.onSureBtnClick(); 
    }    
});