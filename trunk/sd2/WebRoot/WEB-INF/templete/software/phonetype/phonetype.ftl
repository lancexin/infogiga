var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"phonetype?type=json",
   	addUrl:"phonetype?add&type=json",
   	updateUrl:"phonetype?update&type=json",
   	deleteUrl:"phonetype?delete&type=json",
   	storeField:[
   		{name: 'phonetypeId'},
   		{name: 'categoryId'},
        {name: 'categoryName'},
        {name: 'phonebrandId'},
        {name: 'phonebrandName'},
        {name: 'phonearrayId'},
        {name: 'phonearrayName'},
        {name: 'platformId'},
        {name: 'platformName'},
        {name: 'phonetypeName'},
        {name: 'pic'},
        {name: 'status'}
   	],
   	expander:new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
        	'<img style="margin:5px;" src="{pic}"/>'
        )
    }),
   	updateWindowConfig:{
   		title: '手机型号修改',
		width: 250,
		height:390,
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
		width: 250,
		height:390,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'phonetypeId', direction: 'ASC'
    },
    deleteId:"phonetypeId",
    gridColumns:[
         {id:'phonetypeId',hidden:true,sortable: true, dataIndex: 'phonetypeId'},
         {header: "型号名称", sortable: true, dataIndex: 'phonetypeName'},
         {header: "厂商名称", sortable: true, dataIndex: 'phonebrandName'},
         {header: "厂商分类", sortable: true, dataIndex: 'categoryName'},
         {header: "平台名称", sortable: true, dataIndex: 'platformName'},
         {header: "型号分组", sortable: true, dataIndex: 'phonearrayName'},
         {header: "图片地址", sortable: true, dataIndex: 'pic'},
         {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
	         	if(value == 0){
	         		return "停用";
	         	}else{
	         		return "启用";
	         	}
	         } 
         }
    ],
    gridAutoExpandColumn:"phonetypeId",
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"categoryStore",
		autoLoad : false,
		idProperty: 'categoryId',  	
		url :'category?comboCategory&type=json', 
		fields : ['categoryId', 'categoryName'] 
	}),new Ext.data.JsonStore({ 
   		storeId:"phonebrandStore",
		autoLoad : true,
		idProperty: 'phonebrandId',  	
		url :'phonebrand?comboPhonebrand&type=json', 
		fields : ['phonebrandId', 'phonebrandName'] 
	}),new Ext.data.JsonStore({ 
   		storeId:"platformStore",
		autoLoad : true,
		idProperty: 'platformId',  	
		url :'platform?comboPlatform&type=json', 
		fields : ['platformId', 'platformName'] 
	}),new Ext.data.JsonStore({ 
   		storeId:"phonearrayStore",
		autoLoad : true,
		idProperty: 'phonearrayId',  	
		url :'phonearray?comboPhonearray&type=json', 
		fields : ['phonearrayId', 'phonearrayName'] 
	})],
	getAddForm:function(){
		
	
	
    	var addPhonebrandCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 30,
			width:127,
			store : Ext.StoreMgr.get("phonebrandStore"), 
			valueField : 'phonebrandId',// 域的值,对应于store里的fields 
			displayField : 'phonebrandName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"phonebrandId",
			mode:'local'
		}); 
		
		addPhonebrandCombo.on("select",function(combo,record,index){
			Ext.StoreMgr.get("categoryStore").reload({params:{phonebrandId:record.data.phonebrandId}});
			addCategoryCombo.clearValue();
		});
		
		var addCategoryCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("categoryStore"), 
			valueField : 'categoryId',// 域的值,对应于store里的fields 
			displayField : 'categoryName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"categoryId",
			mode:'local'
		});
		
		
		var addPlatformCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("platformStore"), 
			valueField : 'platformId',// 域的值,对应于store里的fields 
			displayField : 'platformName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"platformId",
			mode:'local'
		}); 
		
		var addPhonearrayCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 120,
			width:127,
			store : Ext.StoreMgr.get("phonearrayStore"), 
			valueField : 'phonearrayId',// 域的值,对应于store里的fields 
			displayField : 'phonearrayName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"phonearrayId",
			mode:'local'
		}); 
		
		var imgBox = new Ext.BoxComponent({
			x:75,
			y:180,
			id:"phonetypeImageBox",
			layout:'absolute',
		    xtype: 'box', //或者xtype: 'component', 
		    width:100,
		    heigth:100, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/100x100.gif"    //指定url路径  
		    }  
	  	});
		
		
		var pic = new Ext.form.Hidden({
			name: 'pic',
			allowBlank:false
		});
		
		
		var addForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"addForm",
			defaultType: 'textfield',
			items: [pic,{
				x: 15,
				y: 5,
				xtype:'label',
				text: '型号名称:'
			},{
				x: 75,
				y: 0,
				name: 'phonetypeName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '厂商名称:'
			},addPhonebrandCombo,
			addCategoryCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '平台名称:'
			},addPlatformCombo,{
				x: 15,
				y: 125,
				xtype:'label',
				text: '型号分组:'
			},addPhonearrayCombo,{
				x: 15,
				y: 155,
				xtype:'label',
				text: '手机图片:'
			},{
				x: 75,
				y: 150,
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
							baseImgWidth:100,            //图片剪裁框的宽度度
							baseImgHeight:100,           //图片剪裁框的高度
							baseImgBorder:true          //图像剪裁框是否有边框
						},
						complete:function(data){
							//if(data.width != 100 || data.height != 100){
					    	//	Ext.Msg.alert("提示","图片大小不合适");
					    	//	return;
					    	//}
					    	imgBox.getEl().dom.src = data.url;
					    	pic.setValue(data.url);
					    	this.window.hide();
						}
					});
				
				
					//imageChooser.show();
				}
			},imgBox,{
				x: 15,
				y: 295,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 290,
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
				hiddenName:"status"
			}]
		});
		
		return addForm;
    },
    getUpdateForm:function(){
    	var _this = this;
    
    	var updatePhonebrandCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 30,
			width:127,
			store : Ext.StoreMgr.get("phonebrandStore"), 
			valueField : 'phonebrandId',// 域的值,对应于store里的fields 
			displayField : 'phonebrandName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"phonebrandId",
			mode:'local'
		}); 
		
		updatePhonebrandCombo.on("select",function(combo,record,index){
			Ext.StoreMgr.get("categoryStore").reload({params:{phonebrandId:record.data.phonebrandId}});
			updateCategoryCombo.clearValue();
		});
		
		var updateCategoryCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("categoryStore"), 
			valueField : 'categoryId',// 域的值,对应于store里的fields 
			displayField : 'categoryName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"categoryId",
			mode:'local'
		});
    
		var updatePlatformCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("platformStore"), 
			valueField : 'platformId',// 域的值,对应于store里的fields 
			displayField : 'platformName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"platformId",
			mode:'local'
		}); 
		
		var updatePhonearrayCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 120,
			width:127,
			store : Ext.StoreMgr.get("phonearrayStore"), 
			valueField : 'phonearrayId',// 域的值,对应于store里的fields 
			displayField : 'phonearrayName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"phonearrayId",
			mode:'local'
		}); 
		
		var imgBox = new Ext.BoxComponent({
			x:75,
			y:180,
			id:"updateImageBox",
			layout:'absolute',
		    xtype: 'box', //或者xtype: 'component', 
		    width:100,
		    heigth:100, 
		    autoEl: {  
		        tag: 'img',    //指定为img标签  
		        src: "material/images/100x100.gif"    //指定url路径  
		    }  
	  	});
		
		var pic = new Ext.form.Hidden({
			name: 'pic',
			allowBlank:false,
			id:"updatePic"
		});
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [pic,{
				xtype:'hidden',
				name: 'phonetypeId',
				allowBlank:false
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '型号名称:'
			},{
				x: 75,
				y: 0,
				name: 'phonetypeName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '厂商名称:'
			},updatePhonebrandCombo,
			updateCategoryCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '平台名称:'
			},updatePlatformCombo,{
				x: 15,
				y: 125,
				xtype:'label',
				text: '型号分组:'
			},updatePhonearrayCombo,{
				x: 15,
				y: 155,
				xtype:'label',
				text: '手机图片:'
			},{
				x: 75,
				y: 150,
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
							baseImgWidth:100,            //图片剪裁框的宽度度
							baseImgHeight:100,           //图片剪裁框的高度
							baseImgBorder:true          //图像剪裁框是否有边框
						},
						complete:function(data){
							//if(data.width != 100 || data.height != 100){
					    	//	Ext.Msg.alert("提示","图片大小不合适");
					    	//	return;
					    	//}
					    	imgBox.getEl().dom.src = data.url;
					    	pic.setValue(data.url);
					    	this.window.hide();
						}
					});
				}
			},imgBox,{
				x: 15,
				y: 265,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 290,
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
				hiddenName:"status"
			}]
		});
	
		return updateForm;
    },
    clickAddButton:function(form){
    	var box = Ext.getDom("phonetypeImageBox");
    	if(box){
    		box.src = "material/images/100x100.gif";
    	}
    	
    },
    clickEditButton:function(form){
    	var box = Ext.getDom("updateImageBox");
    	var pic = Ext.getDom("updatePic");
    	if(box){
    		box.src = pic.value;
    	}
    },
    onAddWindowClose:function(p){
    	ImageChooser.hide();
    },
    onUpdateWindowClose:function(p){
    	ImageChooser.hide();
    }
});