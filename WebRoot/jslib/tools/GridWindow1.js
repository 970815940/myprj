Ext.ns("Ext.ux");
Ext.ux.GridWindow=Ext.extend(Ext.Window,{
	//请求地址
	url:null,
	//参数
	baseParams:{},
	//是否可以多选 默认为true
	mulitiSelect:true,
	pageSize:20,
	ICON_OK_BTN:null,
	ICON_CENCEL_BTN:null,
	modal:true,
	closeAction:'hide',
	initComponent:function(){
		Ext.ux.GridWindow.superclass.initComponent.call(this);
		this.addEvents(
			'okBtnChecked',
			'cencelBtnChecked',
			'rowdbCilck'
		);
	},
	onRender:function(ct,position){
		
		if(!this.okbtn){
			this.okbtn=this.addButton({
				text:'确定',
				icon:this.ICON_OK_BTN
			},function(){},this);
		}
		
		if(!this.cencelbtn){
			this.cencelbtn=this.addButton({
				text:'取消',
				icon:this.ICON_CENCEL_BTN
			},function(){},this);
		}
		Ext.ux.GridWindow.superclass.onRender.call(this,ct,position);
		if(!this.loader){
			var cfg=new Array();
			for(var i=0;i<this.cm.length;i++){
				var column=this.cm[i];
				if(column.header!==undefined&&column.dataIndex!==undefined){
					cfg.push({name:column.dataIndex});
				}
			}
			if(this.baseParams){
				this.baseParams.limit=this.pageSize;
				this.baseParams.size=this.pageSize;
			}
			this.loader=new Ext.data.Store({
				 proxy: new Ext.data.HttpProxy({url: this.url}),
				 baseParams: this.baseParams,
                 reader: new Ext.data.JsonReader(
                    {totalProperty: 'allRowCount', root: 'root'},
                    cfg
                 )				
			});
			this.loader.on('load', function(){}, this);
		}
	  if(!this.sm){
	  	this.sm=this.mulitiSelect?
	  		new Ext.grid.CheckboxSelectionModel({
                    listeners: {
                        'rowdeselect': function(sm, rowIndex, record) {
                            if (record.data.isPrivate) {
                                sm.selectRow(rowIndex, true);
                            }
                        }
                    }	  			
	  		}):
	  		new Ext.grid.RowSelectionModel({singleSelect:true}); 
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
        if(!this.grid){
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
            this.grid.on('rowdblclick', function(){}, this); 
            this.add(this.grid);        
        }
        	  
	},
	afterRender:function(){
		Ext.ux.GridWindow.superclass.afterRender.call(this);
	},
	show:function(){
		Ext.ux.GridWindow.superclass.show.call(this);
	}
});
Ext.reg('gridwindow', Ext.ux.GridWindow);
