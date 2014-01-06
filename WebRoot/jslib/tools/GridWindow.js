/**
 * 基于列表选择的窗口控件
 *  
 * 使用示例:
 *     var ts = new Ext.ux.TreeWindow({
 *         pageSize: 10,
 *         cm: [
 *             { header: 'guid', dataIndex: 'guid', hidden:true },
 *             { header: '登录ID', dataIndex: 'psnId', resizable: true, width:100, sortable: true },
 *             { header: '用户姓名', dataIndex: 'psnDisplayname', width:120, resizable: true, sortable: true, fixed: true },
 *             { header: '所属部门', dataIndex: 'psnDeptName', width:150, resizable: true, sortable: true, fixed: true } 
 *         ],
 *         url : top.SSF_CONTEXTPATH + "/commonbiz/org/SysPersonControl.do",
 *         baseParams: { 
 *             method:'list' 
 *         },
 *         mulitiSelect: false,
 *         title: '选择人员',
 *         width: 400,
 *         height: 500
 *     }); 
 * 
 * 作者: LiHong
 * 时间: 2010-07-07
 * 版本: v1.0.0
 */

Ext.ux.GridWindow = Ext.extend(Ext.Window, {
    
    /**
     * cm 表格列模型定义
     *    只需要列模型定义, 相应的读取器自动根据列模型的dataIndex生成
     */
    /**
     * 请求的服务地址
     */
    url: null,
    
    /**
     * 请求的服务参数
     * 例如:
     *   {
     * 		method: 'getTreeNodes',
     *		id: '0'
     *	}
     */
    baseParams: {}, 

    /**
     * 是否可以多选, 多出是出现复选框
     */
    mulitiSelect: false,

    /**
     * 每页的条数, 默认20
     */
    pageSize: 20,

    ICON_SURE_BTN: null,
    ICON_CANCEL_BTN: null,

    /**
     * 打开窗口时自动执行过滤查询
     */
    autoFilter: true,
    /**
     * 是否打开默认过滤工具条, 只有打开默认过滤工具条后, 
     * 过滤相关的事件才可能被触发执行
     */
    enableDefaultFilter: true,
    
    //改变窗口的一些默认属性
    closeAction: 'hide',
    modal: true,
    //private
    elements: 'header,tbar,body',

    /*
     * private
     */
    initComponent : function(){
        Ext.ux.GridWindow.superclass.initComponent.call(this);


        this.addEvents(
            /** 
             * 确定按钮被点击后, 监听链中只要有一个方法返回false, 则停止隐藏窗口
             *
             * @param records 用户选择的Ext.data.Record 数组
             * @param this   当前窗口实例
             */
            'sureButtonClicked',

            /** 
             * 取消按钮被点击后 , 监听链中只要有一个方法返回false, 则停止隐藏窗口
             *
             * @param this   当前窗口实例
             */
            'cancelButtonClicked',

            /**
             * 执行过滤前触发此事件 , 监听链中返回false, 则停止过滤
             *
             * @param store  Ext.data.Store的实例
             * @param value  String 用户输入的过滤条件
             */
            'beforeFilter',

            /**
             * 过滤执行后触发此事件
             *
             * @param store Ext.data.Store的实例
             */
            'filter',
        
            /**
             * 过滤请求失败时触发此事件
             */
            'filterexception',

            /**
             * 用户双击表格时触发此事件
             *
             * param grid Ext.grid.GridPanel 实例
             * param rowIndx Number 用户双击的行
             * param evt Ext.EventObject实例, 双击事件对象
             */
            'rowdblclick'
        );
    },

    /**
     * render
     */
    onRender : function(ct, position){   

        if (!this.sureBtn) {
            this.sureBtn = this.addButton({text: '确定', icon: this.ICON_SURE_BTN}, this.onSureBtnClick, this);
        }
        if (!this.cancelBtn) {
            this.cancelBtn = this.addButton({text: '取消', icon: this.ICON_CANCEL_BTN}, this.onCancelBtnClick, this);
        }

        Ext.ux.GridWindow.superclass.onRender.call(this, ct, position);

        if (!this.loader) {
            var cfg = new Array();
            for (var i = 0; i < this.cm.length; i++) {
                if (this.cm[i].header !== undefined && this.cm[i].dataIndex !== undefined) {
                    cfg.push({name:this.cm[i].dataIndex});
                }
            }

            if (this.baseParams) {
                this.baseParams.limit = this.pageSize;
                this.baseParams.size = this.pageSize;
            }

            this.loader = new Ext.data.Store({
                proxy: new Ext.data.HttpProxy({url: this.url}),
                baseParams: this.baseParams,
                reader: new Ext.data.JsonReader(
                    {totalProperty: 'allRowCount', root: 'root'},
                    cfg
                )
            });
            this.loader.on('loadexception', this.onRecordLoadException, this);
            this.loader.on('load', this.onFilter, this);
        }
        
        if (!this.sm) {
            this.sm = (this.mulitiSelect 
                ? 
                new Ext.grid.CheckboxSelectionModel({
                    listeners: {
                        'rowdeselect': function(sm, rowIndex, record) {
                            if (record.data.isPrivate) {
                                sm.selectRow(rowIndex, true);
                            }
                        }
                    }
                }) 
                :
                new Ext.grid.RowSelectionModel({singleSelect:true})
            );
        }

        if (this.isArray(this.cm)) {
            var cm = this.cm;
            if (!(this.cm[0] instanceof Ext.grid.RowNumberer)) {
            	cm.unshift(new Ext.grid.RowNumberer());
            }
            if (this.mulitiSelect) {
                cm.unshift(this.sm);
            }
            this.cm = new Ext.grid.ColumnModel(cm);
        }

        if (!this.pagingBar) {
            this.pagingBar = new Ext.PagingToolbar({ 
                pageSize: this.pageSize,
                store: this.loader,
                displayInfo: true,
                displayMsg: '共{2}条记录',
                emptyMsg: "没有记录"
            });
        }

        if (!this.grid) {
            this.grid = new Ext.grid.GridPanel({
                id:this.id + "-grid", 
                sm: this.sm,
                cm: this.cm,
                ds:this.loader,
                width:200,
                frame: false,
                enableColumnMove: true,
                stripeRows: true,
                autoScroll:true,
                viewConfig: {
                    forceFit: true
                },
                loadMask:{
                    msg: "正在加载数据..."
                },
                bbar: this.pagingBar
            });   

            this.grid.on('rowdblclick', this.onRowDblClick, this);
            this.add(this.grid);
        }

        this.createTopToolbar();
    },
    
    createTopToolbar: function() {
        if (this.enableDefaultFilter && this.tbar) {
            if (!this.topToolbar) {
                this.topToolbar = new Ext.Toolbar();
                this.topToolbar.render(this.tbar);
            }

            var trigger = new Ext.form.TwinTriggerField({
                parentWin: this,
                hideTrigger1:true,
                width: (this.filterFieldWidth || 200),
                trigger2Class:'x-form-search-trigger',
                trigger1Class:'x-form-clear-trigger',
                onTrigger1Click: function() {
                    trigger.setValue("");
                    trigger.triggers[0].hide();
                    this.parentWin.doFilter(this.getValue());
                },

                onTrigger2Click: function() {
                    if (trigger.getValue() != "") {
                        trigger.triggers[0].show();
                    }
                    this.parentWin.doFilter(this.getValue());
                },

                listeners: {
                    specialkey: function(field, evt) {
                       if (evt.getKey() == Ext.EventObject.ENTER) {
                            this.parentWin.doFilter(field.getValue());
                       }
                    },
                    blur: function() {
                        if (this.getValue() == "") {
                            this.triggers[0].hide();
                        } else {
                            this.triggers[0].show();
                        }
                    }
                }
            });

            this.trigger = trigger;
            this.topToolbar.add('&nbsp;查询条件:',trigger);
        }
    },

    /**
     * 执行查询
     */
    doFilter: function(value) {
        if (this.fireEvent('beforeFilter', this.loader, value) === false)  return;
        this.loader.load();
    },

    /**
     * 查询结束后回调
     */
    onFilter: function(ds, opt) {
        this.fireEvent('filter', ds);
    },

    /**
     * 私有方法
     * 在渲染完成后, 执行一些运行时渲染
     */
    afterRender: function() {
    	Ext.ux.GridWindow.superclass.afterRender.call(this);
    	this.grid.setSize(this.getInnerWidth(),this.getInnerHeight());
    },
    
    /**
     * 判断是否为一个数组
     */
    isArray : function(v) {
    	return v && typeof v.pop == 'function';
    },

    /**
     * 列表数据加载失败时
     */
    onRecordLoadException: function() {
        this.fireEvent('filterexception');
    },

    /**
     * 行被双击
     */
    onRowDblClick: function(grid, rowIndex, evt) {
       this.fireEvent('rowdblclick', grid, rowIndex, evt); 
    },

	/**
	 * 显示方法
	 */
    show: function(){
        Ext.ux.GridWindow.superclass.show.call(this);
        if (this.autoFilter) {
            var v = this.trigger ? this.trigger.getValue() : null;
            this.doFilter(v);
        }
    },
    
    /**
     * 私有方法
     * 获取已选择的节点
     */
    getSelections: function() {
        var records = this.grid.getSelectionModel().getSelections(); 
        return records;
    },

    /**
     * 私有方法
     *
     * 确定按钮点击后
     */
    onSureBtnClick: function() {
    	if (this.fireEvent('sureButtonClicked', this.getSelections(), this) === false) {
    		return;
    	}
       this.hide(); 
    },

    /**
     * 私有方法
     * 取消按钮点击后
     */
    onCancelBtnClick: function() {
    	if (this.fireEvent('cancelButtonClicked', this) === false) {
    		return;
    	}
		this.hide(); 
    },
    
    /**
     * private
     */
   	onDestroy : function(){
        if(this.rendered){
			this.sureBtn.destroy(); 
      		this.cancelBtn.destroy();
              /*this.loader.destroy();
              this.tree.destroy();*/
        }
        Ext.tree.TreePanel.superclass.onDestroy.call(this);
    }
});

Ext.reg('gridwindow', Ext.ux.GridWindow);

