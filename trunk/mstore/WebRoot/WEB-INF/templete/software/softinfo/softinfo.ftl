var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"soft?type=json",
   	addUrl:"soft?add&type=json",
   	updateUrl:"soft?update&type=json",
   	deleteUrl:"soft?delete&type=json",
   	gridAutoExpandColumn:'status',
   	updateSoftId:null,
   	storeField:[
   		{name: 'softId'},
        {name: 'softName'},
        {name: 'softCode'},
        {name: 'shortName'},
        {name: 'description'},
        {name: 'addTime'},
        {name: 'downloadCount'},
        {name: 'icon'},
        {name: 'pic1'},
        {name: 'pic2'},
        {name: 'pic3'},
        {name: 'pic4'},
        {name: 'pic5'},
        {name: 'status'}
   	],
   	expander:new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
        	'<img style="margin:5px;" src="{icon}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{pic1}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{pic2}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{pic3}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{pic4}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{pic5}"/></br>',
        	'<span>{description}</span>'
        )
    }),
   	updateWindowConfig:{
   		title: '手机型号修改',
		width: 410,
		height:610,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	addWindowConfig:{
   		title: '手机型号添加',
		width: 410,
		height:610,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'softId', direction: 'DESC'
    },
    deleteId:"softId",
    gridColumns:[
         {id:'softId',hidden:true,sortable: true, dataIndex: 'softId'},
         {header: "软件名称", sortable: true, dataIndex: 'softName'},
         {header: "软件缩写", sortable: true, dataIndex: 'shortName'},
         {header: "软件编码", sortable: true, dataIndex: 'softCode'},
         {header: "添加时间", sortable: true, dataIndex: 'addTime'},
         {header: "下载次数", sortable: true, dataIndex: 'downloadCount'},
         {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
	         	if(value == 0){
	         		return "停用";
	         	}else{
	         		return "启用";
	         	}
	         } 
         }
    ],
    gridAutoExpandColumn:"softId",
   	comboStore:[],
	getAddForm:function(){
    	
		var pic1 = new Ext.form.Hidden({
			name: 'pic1',
			allowBlank:false
		});
				
		var imgBox1 = new Ext.BoxComponent({
			x:15,
			y:60,
			id:"softmenuImageBox3",
			layout:'absolute',
		    xtype: 'box',
		    width:88,
		    heigth:88, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/88x88.png"    //指定url路径  
		    }  
	  	});
		
		var pic2 = new Ext.form.Hidden({
			name: 'pic2',
			allowBlank:false
		});
				
		var imgBox2 = new Ext.BoxComponent({
			x:125,
			y:60,
			id:"softmenuImageBox4",
			layout:'absolute',
		    xtype: 'box',
		    width:240,
		    heigth:320, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/240x320.jpg"    //指定url路径  
		    }  
	  	});
		
		var addForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"addForm",
			defaultType: 'textfield',
			items: [pic1,pic2,{
				x: 0,
				y: 5,
				xtype:'label',
				text: '软件名称:'
			},{
				x: 60,
				y: 0,
				name: 'softName',
				allowBlank:false
			},{
				x: 0,
				y: 35,
				xtype:'label',
				text: '图片1:'
			},{
				x: 60,
				y: 30,
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
					    	
					    	imgBox1.getEl().dom.src = data.url;
					    	pic1.setValue(data.url);
					    	this.window.hide();
						}
					});
				}
			},imgBox1,{
				x: 195,
				y: 35,
				xtype:'label',
				text: '图片2:'
			},{
				x: 250,
				y: 30,
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
					    	imgBox2.getEl().dom.src = data.url;
					    	pic2.setValue(data.url);
					    	this.window.hide();
						}
					});
				}
			},imgBox2,{
				x: 0,
				y: 400,
				xtype:'label',
				text: '软件介绍:'
			},{
				x: 60,
				y: 395,
				width:320,
				height:70,
				name:"description",
				xtype:"textarea",
				allowBlank:false 
			},{
				x: 0,
				y: 475,
				xtype:'label',
				text: '状态:'
			},{
				x: 60,
				y: 475,
				width:127,
				xtype:'combo',
				name: 'status',
				mode: 'local',
				triggerAction: 'all',
				typeAhead: true,
				allowBlank:false,
				editable:false,
				store:new Ext.data.ArrayStore({
			        fields: [
			            'statusId',
			            'text'
			        ],
			        data: [[1, '启用'], [0, '停用']]
			    }),
				valueField: 'statusId',
				displayField: 'text',
				hiddenValue:1,
				hiddenName:"status",
				allowBlank:false
			}]
		});
		
		return addForm;
    },
    getUpdateForm:function(){
    	var _this = this;
		
		
		var pic1 = new Ext.form.Hidden({
			name: 'pic1',
			id:"pic1",
			allowBlank:false
		});
				
		var imgBox1 = new Ext.BoxComponent({
			x:15,
			y:60,
			id:"softmenuImageBox5",
			layout:'absolute',
		    xtype: 'box',
		    width:88,
		    heigth:88, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/88x88.png"    //指定url路径  
		    }  
	  	});
		
		var pic2 = new Ext.form.Hidden({
			name: 'pic2',
			id:"pic2",
			allowBlank:false
		});
				
		var imgBox2 = new Ext.BoxComponent({
			x:125,
			y:60,
			id:"softmenuImageBox6",
			layout:'absolute',
		    xtype: 'box',
		    width:240,
		    heigth:320, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/240x320.jpg"    //指定url路径  
		    }  
	  	});
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [pic1,pic2,{
				xtype:'hidden',
				name:"softId",
				allowBlank:false
			},{
				xtype:'hidden',
				name:"addTime",
				allowBlank:false
			},{
				xtype:'hidden',
				name:"downloadCount",
				allowBlank:false
			},{
				x: 0,
				y: 5,
				xtype:'label',
				text: '软件名称:'
			},{
				x: 60,
				y: 0,
				name: 'softName',
				allowBlank:false
			},{
				x: 0,
				y: 35,
				xtype:'label',
				text: '图片1:'
			},{
				x: 60,
				y: 30,
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
					    	imgBox1.getEl().dom.src = data.url;
					    	pic1.setValue(data.url);
					    	this.window.hide();
						}
					});
				}
			},imgBox1,{
				x: 195,
				y: 35,
				xtype:'label',
				text: '图片2:'
			},{
				x: 250,
				y: 30,
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
					    	imgBox2.getEl().dom.src = data.url;
					    	pic2.setValue(data.url);
					    	this.window.hide();
						}
					});
				}
			},imgBox2,{
				x: 0,
				y: 400,
				xtype:'label',
				text: '软件介绍:'
			},{
				x: 60,
				y: 395,
				width:320,
				height:70,
				name:"description",
				xtype:"textarea",
				allowBlank:false 
			},{
				x: 0,
				y: 475,
				xtype:'label',
				text: '状态:'
			},{
				x: 60,
				y: 475,
				width:127,
				xtype:'combo',
				name: 'status',
				mode: 'local',
				triggerAction: 'all',
				typeAhead: true,
				allowBlank:false,
				editable:false,
				store:new Ext.data.ArrayStore({
			        fields: [
			            'statusId',
			            'text'
			        ],
			        data: [[1, '启用'], [0, '停用']]
			    }),
				valueField: 'statusId',
				displayField: 'text',
				hiddenValue:1,
				hiddenName:"status",
				allowBlank:false
			}]
		});
	
		return updateForm;
    },
    getTbar:function(){
    	var tbar = [];
    	var _this = this;
    	tbar.push({
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	_this.onClickAddButton();
             }
        });
        
        tbar.push({
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
               _this.onClickEditButton();
             }
        });
        
        tbar.push({
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
               _this.onClickDeleteButton();
             }
        });
        
        tbar.push({
        	 text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
               _this.onClickRefreshButton();
             }
        });
        
        tbar.push({
        	text: '软件导出',
        	 handler : function(){
               Ext.Ajax.request({
               		 url:'soft?export&type=json',
               		 timeout:60000,
               		 success:function(response, options){
               		 		eval("action = "+response.responseText);
         					Ext.Msg.alert('提示',action.msg);
               		 }
               });
             }
        });
        
        tbar.push(' ',
            new Ext.ux.form.SearchField({
                store: _this.gridStore,
                width:320
            }));
        
        return tbar;
    	
    },
    clickAddButton:function(form){
    	var box = Ext.getDom("softmenuImageBox3");
    	if(box){
    		box.src = "material/images/88x88.png";
    	}
    	
    	var box2 = Ext.getDom("softmenuImageBox4");
    	if(box2){
    		box2.src = "material/images/240x320.jpg";
    	}
    },
    clickEditButton:function(form){
    	var box = Ext.getDom("softmenuImageBox5");
    	var pic = Ext.getDom("pic1");
    	if(box){
    		box.src = pic.value;
    	}
    	
    	var box2 = Ext.getDom("softmenuImageBox6");
    	var pic2 = Ext.getDom("pic2");
    	if(box2){
    		box2.src = pic2.value;
    	}
    },
    beforeColse:function(a,b){
    	if(this.addWindow){
    		this.addWindow.close();
    	}
    	
    	if(this.updateWindow){
    		this.updateWindow.close();
    	}
    	ImageChooser.hide();
    },
    getAddWindow:function(addForm,windowConfig){
    	var _this = this;
    	var addWindow = new Ext.Window({
    		width:820,
    		height:600,
    		closeAction:"hide",
    		html:'<iframe src="p?addphonesoft" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    	});
    	
    	return addWindow;
    },
    getUpdateWindow:function(updateForm,windowConfig){
    	var _this = this;
    	var softId = this.updateSoftId;
    	var updateWindow = new Ext.Window({
    		width:820,
    		height:600,
    		closeAction:"hide",
    		html:'<iframe src="p?updatephonesoft&softId='+softId+'" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    	});
    	
    	return updateWindow;
    },
    onClickEditButton:function(){
    	if(this.selectedRowId == -1){
    		return;
    	}
    	var record = this.gridStore.getAt(this.selectedRowId);

        var softId = record.data.softId;

    	if(this.updateWindow == null){
    		this.updateWindow = new Ext.Window({
    			width:820,
    			height:600,
    			html:'<iframe src="p?updatephonesoft&softId='+softId+'" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    		});
    	}else{
    		this.updateWindow.close();
    
    		this.updateWindow = new Ext.Window({
    			width:820,
    			height:600,
    			html:'<iframe src="p?updatephonesoft&softId='+softId+'" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>'
    		});
    	}
    	
    	this.updateWindow.show();

    }
});