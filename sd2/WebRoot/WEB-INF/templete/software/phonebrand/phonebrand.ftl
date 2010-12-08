
var newComponent = (function(){
	
	 var phonebrandStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"phonebrand?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'phonebrandId'},
           {name: 'url'},
           {name: 'phonebrandName'}
        ],
        sortInfo: {
            field: 'phonebrandId', direction: 'ASC'
        }
    });
    
    phonebrandStore.on("beforeload",function(a,b){
    	pId = -1;
    	categoryStore.removeAll();
    });
    
    var categoryStore = new Ext.data.JsonStore({
    	url:"category?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'categoryId'},
           {name: 'categoryName'},
           {name: 'phonebrandId'}
        ],
        sortInfo: {
            field: 'phonebrandId', direction: 'ASC'
        }
    });
   
    var phonebrandAddWindow = new Ext.Window({
    	title: '厂商添加',
		width: 250,
		height:220,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			id:"phonebrandAddForm",
			labelWidth: 75,
			items:[
				{
					fieldLabel: '厂商名称',
					name: 'phonebrandName',
					allowBlank:false,
					xtype:'textfield'
				},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'url'
	        	},{
					layout:'column',
					bodyStyle: "background-color:#CCD9E8", 
					border:false,
	            	width: 230,
	            	items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	bodyStyle: "background-color:#CCD9E8;font:12px tahoma,arial,helvetica,sans-serif", 
	            	 	border:false,
	            	 	items:[{
	            	 		
	            	 		xtype:'label',
		                    text: '软件图标:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:300,
										baseImgX:150,                //设置图片框的位置x       
										baseImgY:100,                //设置图片框的位置y   
										baseImgWidth:100,            //图片剪裁框的宽度度
										baseImgHeight:100,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										//if(data.width != 100 || data.height != 100){
								    	//	Ext.Msg.alert("提示","图片大小不合适");
								    	//	return;
								    	//}
								    	Ext.getCmp("addphonebrandUrl").getEl().dom.src = data.url;
								    	Ext.getCmp("phonebrandAddForm").getForm().findField('url').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'addphonebrandUrl',
						    xtype: 'box',
						    width:100,
						    heigth:100, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/100x100.gif"
						    }  
					  	}]
	            	}] 
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("phonebrandAddForm").getForm().doAction('submit',{
					url:'phonebrand?add&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						phonebrandAddWindow.hide();
						phonebrandStore.reload();
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				phonebrandAddWindow.hide();
			}
		}]
    });
    
     var phonebrandUpdateWindow = new Ext.Window({
    	title: '厂商修改',
		width: 250,
		height:220,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			id:"phonebrandUpdateForm",
			labelWidth: 75,
			items:[
				{
					name: 'phonebrandId',
					xtype:"hidden",
					allowBlank:false
				},{
					fieldLabel: '厂商名称',
					name: 'phonebrandName',
					allowBlank:false,
					xtype:'textfield'
				},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'url'
	        	},{
					layout:'column',
					bodyStyle: "background-color:#CCD9E8", 
					border:false,
	            	width: 230,
	            	items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	bodyStyle: "background-color:#CCD9E8;font:12px tahoma,arial,helvetica,sans-serif", 
	            	 	border:false,
	            	 	items:[{
	            	 		
	            	 		xtype:'label',
		                    text: '软件图标:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:300,
										baseImgX:150,                //设置图片框的位置x       
										baseImgY:100,                //设置图片框的位置y   
										baseImgWidth:100,            //图片剪裁框的宽度度
										baseImgHeight:100,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										//if(data.width != 100 || data.height != 100){
								    	//	Ext.Msg.alert("提示","图片大小不合适");
								    	//	return;
								    	//}
								    	Ext.getCmp("updatephonebrandUrl").getEl().dom.src = data.url;
								    	Ext.getCmp("phonebrandAddForm").getForm().findField('url').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'updatephonebrandUrl',
						    xtype: 'box',
						    width:100,
						    heigth:100, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/100x100.gif"
						    }  
					  	}]
	            	}] 
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("phonebrandUpdateForm").getForm().doAction('submit',{
					url:'phonebrand?update&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						phonebrandUpdateWindow.hide();
						phonebrandStore.reload();
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				phonebrandUpdateWindow.hide();
			}
		}]
    });
    
    var pId = -1;
    var cId = -1;
   
   	var phonebrandGrid = new Ext.grid.GridPanel({
  	 	width: 400,
		height: 600,
   		store: phonebrandStore,
   		viewConfig : {
		      forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
		},
   		border:true,
   		plugins:new Ext.ux.grid.RowExpander({
       		 tpl : new Ext.Template(
        		'<img style="margin:5px;" src="{url}"/>'
       	 	)
   		}),
   		columns:[new Ext.ux.grid.RowExpander({
       		 	tpl : new Ext.Template(
        			'<img style="margin:5px;" src="{url}"/>'
       	 		)
   			}),
   			{id:'phonebrandId',hidden:true,sortable: true, dataIndex: 'phonebrandId'},
   			{header: "厂商名称", sortable: true, dataIndex: 'phonebrandName'},
   			{header: "图片地址", sortable: true, dataIndex: 'url'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'phonebrandId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	phonebrandAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
               if(pId == undefined || pId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
               }
               //alert(pId);
               var record = phonebrandStore.getAt(pId);
               Ext.getCmp("phonebrandUpdateForm").getForm().setValues(record.data);
               var box = Ext.getDom("updatephonebrandUrl");
               var pic = Ext.getDom("updatePic");
    			if(box){
    				box.src = record.data.url;
    			}
               phonebrandUpdateWindow.show();
               
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(pId == undefined || pId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var phonebrandId = phonebrandStore.getAt(pId).data.phonebrandId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"phonebrand?delete&type=json",
               				params:{
               					phonebrandId:phonebrandId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						phonebrandStore.reload();
               					}
               				},
               				failure:function(response, options){
               					Ext.Msg.alert('提示',"连接服务器失败");
               				}
               			});
               		
               			return true;
               		}else{
               			return false;
               		}
               });
             }
        },{
        	 text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
               phonebrandStore.reload();
             }
        }]
   	
   	});
   	
   	phonebrandGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
    	categoryStore.load({params:{phonebrandId:r.data.phonebrandId}});
    });
    
    
    
    
    /***************************************************************************************/
    var categoryAddWindow = new Ext.Window({
    	title: '厂商分类添加',
		width: 250,
		height:100,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"categoryAddForm",
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '市名称:'
			},{
				x: 75,
				y: 0,
				name: 'categoryName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var record = phonebrandStore.getAt(pId);
				
				Ext.getCmp("categoryAddForm").getForm().doAction('submit',{
					url:'category?add&type=json',
                    method:'post',
                    params:{
                    	phonebrandId:record.data.phonebrandId
                    },
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						categoryAddWindow.hide();
						categoryStore.reload();
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				categoryAddWindow.hide();
			}
		}]
    });
    
     var categoryUpdateWindow = new Ext.Window({
    	title: '厂商分类修改',
		width: 250,
		height:100,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"categoryUpdateForm",
			defaultType: 'textfield',
			items:[{
					name: 'phonebrandId',
					xtype:"hidden",
					allowBlank:false
				},{
					name: 'categoryId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '市名称:'
				},{
					x: 75,
					y: 0,
					name: 'categoryName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("categoryUpdateForm").getForm().doAction('submit',{
					url:'category?update&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						categoryUpdateWindow.hide();
						categoryStore.reload();
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				categoryUpdateWindow.hide();
			}
		}]
    });
   	
   	var categoryGrid = new Ext.grid.GridPanel({
   		width: 300,
		height: 600,
   		store: categoryStore,
   		border:true,
   		columns:[
   			{id:'categoryId',hidden:true,sortable: true, dataIndex: 'categoryId'},
   			{header: "分类名称",width:270,sortable: true, dataIndex: 'categoryName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'categoryId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){

				if(pId == undefined || pId == -1){
					return;
				}
             	Ext.getCmp("categoryAddForm").getForm().reset()
              	categoryAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
                var record = categoryStore.getAt(cId);
                Ext.getCmp("categoryUpdateForm").getForm().setValues(record.data);
                categoryUpdateWindow.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
              	 Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var categoryId = categoryStore.getAt(cId).data.categoryId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"category?delete&type=json",
               				params:{
               					categoryId:categoryId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						categoryStore.reload();
               					}
               				},
               				failure:function(response, options){
               					Ext.Msg.alert('提示',"连接服务器失败");
               				}
               			});
               		
               			return true;
               		}else{
               			return false;
               		}
               });
              	
             }
        },{
        	 text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
             	if(pId == undefined || pId == -1){
             		categoryStore.removeAll();
             		return;
             	}
             	var record = phonebrandStore.getAt(pId);
              	categoryStore.load({params:{phonebrandId:record.data.phonebrandId}});
             }
        }]
   	});
   	
   	categoryGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
   	
   	categoryStore.on("beforeload",function(a,b){
    	cId = -1;
    });
   	/**********************************************************************/
   	var showPanel = new Ext.Panel({
   		title:"${name}",
    	layout: 'hbox',
    	width:700,
    	height:600,
    	items:[phonebrandGrid,categoryGrid]
    });
   	
   	
   	 var panel = new Ext.Panel({
    	id:"${code}",
		title:"${name}",
    	baseCls:'x-plain',
    	closable:true,
    	layout: {
             type:'vbox',
             padding:'50 0 0 0',
             align:'center'
        },
        items:showPanel
    });
    
     panel.on('beforeclose',function(p,o){
    	phonebrandAddWindow.close();
    	phonebrandUpdateWindow.close();
    	categoryAddWindow.close();
    	categoryUpdateWindow.close();
     });
    
    return panel;

})();