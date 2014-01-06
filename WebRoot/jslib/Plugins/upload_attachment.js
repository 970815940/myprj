/******
 *name：上传附件 管理
 *描述:  该类是用来上传附件，所有的附件资源保存在硬盘，基础信息保存在数据表ATTACHMENT
 */
 Ext.ns("Ext.ux");
 Ext.ux.Attachment=Ext.extend(Ext.Window,{
 	//服务请求URL常量 
 	ATTACHMENT_URL:Ext.appRootPath+"/attupload.action",
 	//附件上传路径
 	uploadPath:null,
 	//附件加载的页面位置
 	showArea:null,
 	//附件所属模块
 	model:null,
 	//附件对应业务ID
 	businessid:null,
 	//允许附件上传的类型
 	allowTypes:'',
 	//附件窗口的标题
 	winName:'附件管理',
 	//上传附件窗口的标题
 	formName:'上传附件',
 	//页面上传时遮罩的内容
 	loadMark:null,
 	//附件列表的表格
 	attGrid:null,
 	//附件最外层win显示窗口
 	attWin:null,
 	//附件框是否透明 模式  默认为不透明
  	arrModal:true,
 	//选择模型
 	sm:null,
 	//列模型
 	cm:null,
 	//数据源
 	ds:null,
 	width:null,
 	height:null,
 	initComponent:function(){
 				 Ext.ux.Attachment.superclass.initComponent.call(this);
 		 		if(!this.width)this.width=300;
 		 		if(!this.height)this.height=300;
 		 		this.addEvents(
 		 			'show1'
 		 		);
		   /* if(!this.attWin){
		    	width=this.width;
		    	height=this.height;
		    	this.attWin=new Ext.Window({
					title:this.title,
					width:width,
					modal:this.arrModal,
					height:width,
					items:this.attGrid,
					buttons:[
						{text:'取消',handler:function(){
							this.ownerCt.hide();
						}}
					]
				});	    	
		    }  */ 
		  //  this.add(this.attGrid);		 		
 		
 	},
 	onRender:function(ct, position){
 		 Ext.ux.Attachment.superclass.onRender.call(this, ct, position); 
 		 this.initGridUI();
 		 this.initWinUI();
 		 items=new Array();
 		 items.push(this.attGrid);
 		 alert(items.length);
 		 this.items =items ;
 		
 	},
 	initGridUI:function(){
  		 		if(!this.loadMark)this.loadMark={msg:"附件信息加载中.."};
 				if(!this.sm) this.sm=new Ext.grid.CheckboxSelectionModel();
 		 		if(!this.cm){
			 		 this.cm=[{
				            header: 'ID',
				            dataIndex: 'id',
				            hidden: true
			            }, {
				            header: '附件路径',
				            dataIndex: 'upload',
				            hidden: true
			        	}, new Ext.grid.RowNumberer(), this.sm, {
				            header: '附件名称',
				            dataIndex: 'name',
				            align: 'center'
			            }, {
			            header: '附件大小',
			            dataIndex: 'size',
			            align: 'center',
			            width: 25
			          }
			        ];			        
			  }
			    //dataStore
		      if(!this.ds){
			        this.ds =new Ext.data.Store({
								proxy :new Ext.data.HttpProxy({
									url:this.ATTACHMENT_URL
								}),
								reader :new Ext.data.JsonReader({
									totalProperty:'allRowCount',
									root:'root'
								},[
									{name:'id'},
									{name:'model'},
									{name:'businessid'},
									{name:'upload'},
									{name:'name'},
									{name:'size'}
								
								]),
							    listeners: {
							          loadexception: function(){
							             Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
							          }
							    }	
					});
		     }

		     if(!this.attGrid){
		    	this.attGrid=new Ext.grid.GridPanel({
						    viewConfig:{
						        forceFit:true
						    },
						    frame:true,
						    sm:this.sm,	
							store:this.ds,
							columns:this.cm,
						    enableColumnMove:false,
						    stripeRows:true,
						    animCollapse:true,
						    trackMouseOver:true,
						    loadMask:this.loadMark
				 });
		     }			
 	},
 	//初始化界面
 	initWinUI:function(){
 		this.title=this.winName;
 		this.width=this.width;
 		this.height=this.height;
 		//this.items.add(this.attGrid);
 			var aa=new Ext.form.TextField({
			  fieldLabel:'金额',
			  id:'FMoney',
			  allowBlank :false,
			  name:'fn.FMoney',
			  width:200
			});		
	  // this.add(aa);
 		
 	},
 	//显示附件窗口
 	showAttWin:function(){
 		//this.attGrid=this.createAttGrid();
 		 this.show();
 		//attWin=this.attWin;
 		//attWin.show();
 		//var grid=attWin.findByType("grid")[0];//.getStore().load();
 		//grid.getStore().load();
 	}        
 
 });
 