var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"json?softList&type=json",
   	addUrl:"add?soft&type=json",
   	updateUrl:"update?soft&type=json",
   	deleteUrl:"delete?soft&type=json",
   	storeField:[
   		{name: 'softId'},
        {name: 'softmenuId'},
        {name: 'softmenuName'},
        {name: 'softName'},
        {name: 'description'},
        {name: 'addTime'},
        {name: 'downloadCount'},
        {name: 'pic1'},
        {name: 'pic2'},
        {name: 'status'}
   	],
   	expander:new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
        	'<img style="margin:5px;" src="{pic1}"/>',
        	'<img style="margin:5px;" src="{pic2}"/></br>',
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
        field: 'softId', direction: 'ASC'
    },
    deleteId:"softId",
    gridColumns:[
         {id:'softId',hidden:true,sortable: true, dataIndex: 'softId'},
         {header: "软件名称", sortable: true, dataIndex: 'softName'},
         {header: "菜单名称", sortable: true, dataIndex: 'softmenuName'},
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
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"softmenuStore",
		autoLoad : true,
		idProperty: 'softmenuId',  	
		url :'json?comboSoftmenu&type=json', 
		fields : ['softmenuId', 'softmenuName'] 
	})],
	getAddForm:function(){
    	var addSoftmenuCombo = new Ext.form.ComboBox({ 
	    	x: 250,
			y: 0,
			width:127,
			store : Ext.StoreMgr.get("softmenuStore"), 
			valueField : 'softmenuId',// 域的值,对应于store里的fields 
			displayField : 'softmenuName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"softmenuId",
			mode:'local'
		}); 
		
		
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
				x: 195,
				y: 5,
				xtype:'label',
				text: '所属菜单:'
			},addSoftmenuCombo,{
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
    
    	var updateSoftmenuCombo = new Ext.form.ComboBox({ 
	    	x: 250,
			y: 0,
			width:127,
			store : Ext.StoreMgr.get("softmenuStore"), 
			valueField : 'softmenuId',// 域的值,对应于store里的fields 
			displayField : 'softmenuName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"softmenuId",
			mode:'local'
		}); 
		
		
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
				x: 195,
				y: 5,
				xtype:'label',
				text: '所属菜单:'
			},updateSoftmenuCombo,{
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
    }
});