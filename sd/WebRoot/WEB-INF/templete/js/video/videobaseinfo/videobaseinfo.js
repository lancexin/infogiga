
var newComponent = (function(){

	
    var pId = -1;
	
	var store1 = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"json?videomenuList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'videomenuId'},
           {name: 'videomenuName'}
        ],
        sortInfo: {
            field: 'videomenuId', direction: 'ASC'
        }
    });
    
    
	var store2 = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?videoList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'videoId'},
           {name: 'videoName'},
           {name: 'description'},
           {name: 'addTime'},
           {name: 'status'},
           {name: 'pic1'},
           {name: 'pic2'},
           {name: 'downloadCount'}
        ],
        sortInfo: {
            field: 'videoId', direction: 'ASC'
        }
    });
    
    var store3 = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?videoindexList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'indexId'},
           {name: 'videoId'},
           {name: 'videoName'},
           {name: 'videomenuId'},
           {name: 'videomenuName'}
        ],
        sortInfo: {
            field: 'indexId', direction: 'ASC'
        }
    });
    
    var comboStore1 = new Ext.data.JsonStore({ 
   		storeId:"videomenuCombo",
		autoLoad : true,
		idProperty: 'videomenuId',  	
		url :'json?comboVideomenu&type=json', 
		fields : ['videomenuId', 'videomenuName'] 
	});
    
   store2.load({params:{start:0,limit:20}});
      
    var addWindow1 = new Ext.Window({
    	title: '菜单添加',
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
			id:"videomenuAddForm",
			defaultType: 'textfield',
			items:[
				{
					x: 15,
					y: 5,
					xtype:'label',
					text: '菜单名称:'
				},{
					x: 75,
					y: 0,
					name: 'videomenuName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("videomenuAddForm").getForm().doAction('submit',{
					url:'add?videomenu&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						addWindow1.hide();
						store1.reload();
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
				addWindow1.hide();
			}
		}]
    });
    
     var updateWindow1 = new Ext.Window({
    	title: '菜单修改',
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
			id:"videomenuUpdateForm",
			defaultType: 'textfield',
			items:[{
				name: 'videomenuId',
				xtype:"hidden",
				allowBlank:false
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				x: 75,
				y: 0,
				name: 'videomenuName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("videomenuUpdateForm").getForm().doAction('submit',{
					url:'update?videomenu&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						updateWindow1.hide();
						store1.reload();
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
				updateWindow1.hide();
			}
		}]
    });
    
   
   	var grid1 = new Ext.grid.GridPanel({
   		title:"菜单",
   		style:"padding:5px;",
  	 	width: 300,
		height: 600,
   		store: store1,
   		border:true,
   		columns:[
   			{id:'videomenuId',hidden:true,sortable: true, dataIndex: 'videomenuId'},
   			{header: "菜单名称",width:270, sortable: true, dataIndex: 'videomenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'videomenuId',
        loadMask: true,
        
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
            	 Ext.getCmp("videomenuAddForm").getForm().reset();
              	addWindow1.show();
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
               var record = store1.getAt(pId);
               Ext.getCmp("videomenuUpdateForm").getForm().setValues(record.data);
               updateWindow1.show();
               
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
               		var videomenuId = store1.getAt(pId).data.videomenuId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?videomenu&type=json",
               				params:{
               					videomenuId:videomenuId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store1.reload();
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
               store1.reload();
             }
        }]
   	
   	});
   	
   	grid1.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
    });
    
    store1.on("beforeload",function(a,b){
    	pId = -1;
    });
    
    var addImgBox1 = new Ext.BoxComponent({
    	id:'videoAddImgBox1',
		x:15,
		y:160,
		layout:'absolute',
	    xtype: 'box',
	    width:88,
	    heigth:88, 
	    autoEl: {  
	        tag: 'img',    //指定为img标签  
	        src: "material/images/88x88.png"    //指定url路径  
	    }  
  	});
  	
  	var addImgBox2 = new Ext.BoxComponent({
  		id:'videoAddImgBox2',
		x:125,
		y:160,
		layout:'absolute',
	    xtype: 'box',
	    width:240,
	    heigth:320, 
	    autoEl: {  
	        tag: 'img',    //指定为img标签  
	        src: "material/images/240x320.jpg"    //指定url路径  
	    }  
  	});
  	
  	var addPic1 = new Ext.form.Hidden({
		name: 'pic1',
		allowBlank:false
	});
	
	var addPic2 = new Ext.form.Hidden({
		name: 'pic2',
		allowBlank:false
	});
  	
	var addWindow2 = new Ext.Window({
    	title: '视频添加',
		width: 410,
		height:560,
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
			id:"videoAddForm",
			fileUpload: true,
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '视频名称:'
			},{
				x: 75,
				y: 0,
				name: 'videoName',
				anchor: '90%',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '附件上传:'
			},{
				x:75,
				y:30,
	            xtype:'fileuploadfield',
	            anchor: '90%',
	            hideLabel :true,
	            emptyText: '请选择...',
	            fieldLabel: '附件',
	            name: 'upload',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'upload-icon'
	            }
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '视频介绍:'
			},{
				x: 75,
				y: 60,
				name: 'description',
				anchor: '90%',
				xtype:'textarea',
				allowBlank:false
			},{
				x: 10,
				y: 130,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					ImageChooser.show({
						cutConf:{
							width:400,
							height:300,
							baseImgX:150,                //设置图片框的位置x       
							baseImgY:100,                //设置图片框的位置y   
							baseImgWidth:88,            //图片剪裁框的宽度度
							baseImgHeight:88,           //图片剪裁框的高度
							baseImgBorder:true
						},
						complete:function(data){
							if(data.width != 88 || data.height != 88){
					    		Ext.Msg.alert("提示","图片大小不合适 88x88");
					    		return;
					    	}
							this.window.hide();
							addImgBox1.getEl().dom.src = data.url;
							addPic1.setValue(data.url);
						}
					});
				}
			},{
				x: 130,
				y: 130,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					ImageChooser.show({
						cutConf:{
							width:500,
							height:600,
							baseImgX:130,                //设置图片框的位置x       
							baseImgY:140,                //设置图片框的位置y   
							baseImgWidth:240,            //图片剪裁框的宽度度
							baseImgHeight:320,           //图片剪裁框的高度
							baseImgBorder:true          //图像剪裁框是否有边框
						},
						complete:function(data){
							if(data.width != 240 || data.height != 320){
					    		Ext.Msg.alert("提示","图片大小不合适 240x320");
					    		return;
					    	}
					    	this.window.hide();
					    	addImgBox2.getEl().dom.src = data.url;
					    	addPic2.setValue(data.url);
						}
					});
				}
			},addImgBox1,addImgBox2,addPic1,addPic2]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("videoAddForm").getForm().doAction('submit',{
					url:'add?video&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						addWindow2.hide();
						store2.reload();
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
				addWindow2.hide();
			}
		}]
    });
    
    var updateImgBox1 = new Ext.BoxComponent({
    	id:'videoUpdateImgBox1',
		x:15,
		y:160,
		layout:'absolute',
	    xtype: 'box',
	    width:88,
	    heigth:88, 
	    autoEl: {  
	        tag: 'img',    //指定为img标签  
	        src: "material/images/88x88.png"    //指定url路径  
	    }  
  	});
  	
  	var updateImgBox2 = new Ext.BoxComponent({
  		id:'videoUpdateImgBox2',
		x:125,
		y:160,
		layout:'absolute',
	    xtype: 'box',
	    width:240,
	    heigth:320, 
	    autoEl: {  
	        tag: 'img',    //指定为img标签  
	        src: "material/images/240x320.jpg"    //指定url路径  
	    }  
  	});
  	
  	var updatePic1 = new Ext.form.Hidden({
		name: 'pic1',
		allowBlank:false
	});
	
	var updatePic2 = new Ext.form.Hidden({
		name: 'pic2',
		allowBlank:false
	});
    
    
    var updateWindow2 = new Ext.Window({
    	title: '视频修改',
		width: 410,
		height:560,
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
			id:"videoUpdateForm",
			fileUpload: true,
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '视频名称:'
			},{
				name: 'videoId',
				xtype:'hidden',
				allowBlank:false
			},{
				x: 75,
				y: 0,
				name: 'videoName',
				anchor: '90%',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '视频介绍:'
			},{
				x: 75,
				y: 30,
				name: 'description',
				anchor: '90%',
				xtype:'textarea',
				allowBlank:false
			},{
				x: 10,
				y: 130,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					ImageChooser.show({
						cutConf:{
							width:400,
							height:300,
							baseImgX:150,                //设置图片框的位置x       
							baseImgY:100,                //设置图片框的位置y   
							baseImgWidth:88,            //图片剪裁框的宽度度
							baseImgHeight:88,           //图片剪裁框的高度
							baseImgBorder:true
						},
						complete:function(data){
							if(data.width != 88 || data.height != 88){
					    		Ext.Msg.alert("提示","图片大小不合适 88x88");
					    		return;
					    	}
							this.window.hide();
							updateImgBox1.getEl().dom.src = data.url;
							updatePic1.setValue(data.url);
						}
					});
				}
			},{
				x: 130,
				y: 130,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					ImageChooser.show({
						cutConf:{
							width:500,
							height:600,
							baseImgX:130,                //设置图片框的位置x       
							baseImgY:140,                //设置图片框的位置y   
							baseImgWidth:240,            //图片剪裁框的宽度度
							baseImgHeight:320,           //图片剪裁框的高度
							baseImgBorder:true          //图像剪裁框是否有边框
						},
						complete:function(data){
							if(data.width != 240 || data.height != 320){
					    		Ext.Msg.alert("提示","图片大小不合适 240x320");
					    		return;
					    	}
					    	this.window.hide();
					    	updateImgBox2.getEl().dom.src = data.url;
					    	updatePic2.setValue(data.url);
						}
					});
				}
			},updateImgBox1,updateImgBox2,updatePic1,updatePic2]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("videoUpdateForm").getForm().doAction('submit',{
					url:'update?video&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						updateWindow2.hide();
						store2.reload();
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
				updateWindow2.hide();
			}
		}]
    });
    
    addWindow2.on('beforeshow',function(){
    	Ext.getCmp("videoAddForm").getForm().reset();
	        var box = Ext.getDom("videoAddImgBox1");
	   	if(box){
	   		box.src = "material/images/88x88.png";
	   	}
	   	
	   	var box2 = Ext.getDom("videoAddImgBox2");
	   	if(box2){
	   		box2.src = "material/images/240x320.jpg";
	   	}
    	return true;
    });
    
    updateWindow2.on('beforeshow',function(){
    	if(pId2 == undefined || pId2 ==  -1){
        		Ext.Msg.alert('提示',"请先选择您要操作的列");
        		return false;
        }
        //alert(pId);
        var record = store2.getAt(pId2);
        Ext.getCmp("videoUpdateForm").getForm().setValues(record.data);
        var box = Ext.getDom("videoUpdateImgBox1");
    	if(box){
    		box.src = record.data.pic1;
    	}
    	
    	var box2 = Ext.getDom("videoUpdateImgBox2");
    	if(box2){
    		box2.src = record.data.pic2;
    	}
    	return true;
    });
    
    var expander = new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
        	'<img style="margin:5px;" src="{pic1}"/>',
        	'<img style="margin:5px;" src="{pic2}"/></br>',
        	'<span>{description}</span>'
        )
    });
    
    var grid2 = new Ext.grid.GridPanel({
   		title:"视频",
   		style:"padding:5px;",
  	 	width: 440,
		height: 600,
   		store: store2,
   		border:true,
   		plugins: expander,
   		bbar: new Ext.PagingToolbar({
	        pageSize: 20,
	        store: store2,
	        displayInfo: true,
	        displayMsg: '第{0}到{1}条数据 共{2}条',
	        emptyMsg: "没有记录"
	    }),
   		columns:[
   			expander,
   			{id:'videoId',hidden:true,sortable: true, dataIndex: 'videoId'},
   			{header: "视频名称", sortable: true, dataIndex: 'videoName'},
   			{header: "添加时间", sortable: true, dataIndex: 'addTime'},
	        {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
		         	if(value == 0){
		         		return "停用";
		         	}else{
		         		return "启用";
		         	}
		         } 
	        }
   		],
   		stripeRows: true,
        autoExpandColumn: 'videoId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
            	
              	addWindow2.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
              
               updateWindow2.show();
               
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(pId2 == undefined || pId2 ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var videoId = store2.getAt(pId2).data.videoId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?video&type=json",
               				params:{
               					videoId:videoId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store2.reload();
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
               store2.reload();
             }
        }]
   	
   	});
   	
   	var pId2 = -1;
   	grid2.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId2 = rowIdx;
   		store3.load({params:{videoId:r.data.videoId}});
    });
    
    store2.on("beforeload",function(a,b){
    	pId2 = -1;
    });
    
    
    var addCombo1 = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : comboStore1, 
		valueField : 'videomenuId',// 域的值,对应于store里的fields 
		displayField : 'videomenuName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"videomenuId",
		mode:'local'
	}); 
		
	var updateCombo1 = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : comboStore1, 
		valueField : 'videomenuId',// 域的值,对应于store里的fields 
		displayField : 'videomenuName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"videomenuId",
		mode:'local'
	}); 
    
   var addWindow3 = new Ext.Window({
    	title: '添加所属菜单',
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
			id:"videoindexAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},addCombo1]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var videoId = store2.getAt(pId2).data.videoId;
				Ext.getCmp("videoindexAddForm").getForm().doAction('submit',{
					url:'add?videoindex&type=json',
                    method:'post',
                    params:{
                    	videoId:videoId
                    },
					success:function(form,action){
						addWindow3.hide();
						store3.reload();
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
				addWindow3.hide();
			}
		}]
    });
    
    var updateWindow3 = new Ext.Window({
    	title: '所属菜单修改',
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
			id:"videoindexUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				name: 'indexId',
				allowBlank:false,
				xtype:'hidden'
			},updateCombo1]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				var videoId = store2.getAt(pId2).data.videoId;
				Ext.getCmp("videoindexUpdateForm").getForm().doAction('submit',{
					url:'update?videoindex&type=json',
                    method:'post',
                    params:{
                    	videoId:videoId
                    },
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						updateWindow3.hide();
						store3.reload();
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
				updateWindow3.hide();
			}
		}]
    });
    
    
    var grid3 = new Ext.grid.GridPanel({
   		title:"所属菜单列表",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: store3,
   		border:true,
   		columns:[
   			{id:'indexId',hidden:true,sortable: true, dataIndex: 'indexId'},
   			{header: "菜单名称",sortable: true, dataIndex: 'videomenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'indexId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	if(pId2 == undefined || pId2 ==  -1){
	              		return;
	            }
             	addWindow3.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
	             if(pId3 == undefined || pId3 ==  -1){
	              		Ext.Msg.alert('提示',"请先选择您要操作的列");
	              		return;
	             }
	              //alert(pId);
	             var record = store3.getAt(pId3);
	             Ext.getCmp("videoindexUpdateForm").getForm().setValues(record.data);
                 updateWindow3.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(pId3 == undefined || pId3 ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var indexId = store3.getAt(pId3).data.indexId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?videoindex&type=json",
               				params:{
               					indexId:indexId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store3.reload();
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
              	store3.reload();
             }
        }]
   	});
   	
   	var pId3 = -1;
   	
   	grid3.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId3 = rowIdx;
    });
    
    store3.on("beforeload",function(a,b){
    	pId3 = -1;
    });  
    
   	var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:1200,
    	height:600,
    	border:false,
    	items:[grid1,grid2,grid3]
    });
   	
   	
   	 var panel = new Ext.Panel({
   	 	id:"${code}",
		title:"${name}",
    	baseCls:'x-plain',
    	closable:true,
    	border:false,
    	layout: {
             type:'vbox',
             padding:'50 0 0 0',
             align:'center'
        },
        items:showPanel
    });
    
     panel.on('beforeclose',function(p,o){
    	addWindow1.close();
    	updateWindow1.close();
    	ImageChooser.hide();
    	addWindow2.close();
    	updateWindow2.close();
     });
    
    return panel;

})();