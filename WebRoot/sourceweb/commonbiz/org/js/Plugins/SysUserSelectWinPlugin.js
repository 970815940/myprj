/*****
 *该类依赖  jslib/tools/GridWindow.js
 *@该类功能描述:用户选择人员的窗口
 *@编写时间:2013-9-6 22:46
 *@编写人:taoxs@sysway.cn
 *@version 1.0 
 */
Ext.ux.UserSelectWin=Ext.extend(Ext.ux.GridWindow,{
	url:Ext.appRootPath+"/commonbiz/sugetSysUserByList.action",
	cm: [
			{header:'guid',dataIndex:'guid',hidden:true},
			{header:'姓名',dataIndex:'suDisplayname'},
			{header:'部门',dataIndex:'suDptid'}
	],	
	title: '选择人员',
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