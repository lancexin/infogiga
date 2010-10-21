var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"json?phonetypeList&type=json",
   	addUrl:"add?phonetype&type=json",
   	updateUrl:"update?phonetype&type=json",
   	deleteUrl:"delete?phonetype&type=json",
   	storeField:[
   		{name: 'phonetypeId'},
        {name: 'phonebrandId'},
        {name: 'phonebrandName'},
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
		height:330,
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
		height:330,
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
         {header: "平台名称", sortable: true, dataIndex: 'platformName'},
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
   		storeId:"phonebrandStore",
		autoLoad : true,
		idProperty: 'phonebrandId',  	
		url :'json?comboPhonebrand&type=json', 
		fields : ['phonebrandId', 'phonebrandName'] 
	}),new Ext.data.JsonStore({ 
   		storeId:"platformStore",
		autoLoad : true,
		idProperty: 'platformId',  	
		url :'json?comboPlatform&type=json', 
		fields : ['platformId', 'platformName'] 
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
		
		var addPlatformCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
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
		
		var imgBox = new Ext.BoxComponent({
			x:75,
			y:120,
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
		
		var imageChooser = new Ext.ux.ImageChooser({
			id:"imagechooser1",
			proWindowId:"proWindow1",
			complete:function(data){
		    	
		    	if(data.width != 100 || data.height != 100){
		    		Ext.Msg.alert("提示","图片大小不合适");
		    		return;
		    	}
		    	imgBox.getEl().dom.src = data.url;
		    	pic.setValue(data.url);
		    	imageChooser.hide();
		    	
		    },cutWindowCinfig:{
		    	id:"phonetypeimageCuter",
				imagePanelId:"phonetypeimagePanel",   
				backAreaId:"phonetypebackArea",       //图片背景区id
				drugAreaId:"phonetypedrugArea",       //鼠标拖动区id
				baseImgId:"phonetypebaseImg",         //图片剪裁框id
				width:400,
				height:300,
				baseImgX:150,                //设置图片框的位置x       
				baseImgY:100,                //设置图片框的位置y   
				baseImgWidth:100,            //图片剪裁框的宽度度
				baseImgHeight:100,           //图片剪裁框的高度
				baseImgBorder:true          //图像剪裁框是否有边框
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
			},addPhonebrandCombo,{
				x: 15,
				y: 65,
				xtype:'label',
				text: '平台名称:'
			},addPlatformCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '手机图片:'
			},{
				x: 75,
				y: 90,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					imageChooser.show();
				}
			},imgBox,{
				x: 15,
				y: 235,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 230,
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
		
		
		var updatePlatformCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
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
		
		var imgBox = new Ext.BoxComponent({
			x:75,
			y:120,
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
		
		var imageChooser = new Ext.ux.ImageChooser({
			id:"imagechooser2",
			proWindowId:"proWindow2",
			complete:function(data){
		    	
		    	if(data.width != 100 || data.height != 100){
		    		Ext.Msg.alert("提示","图片大小不合适");
		    		return;
		    	}
		    	imgBox.getEl().dom.src = data.url;
		    	pic.setValue(data.url);
		    	imageChooser.hide();
		    	
		    },cutWindowCinfig:{
		    	id:"phonetypeimageCuter2",
				imagePanelId:"phonetypeimagePanel2",   
				backAreaId:"phonetypebackArea2",       //图片背景区id
				drugAreaId:"phonetypedrugArea2",       //鼠标拖动区id
				baseImgId:"phonetypebaseImg2",         //图片剪裁框id
				width:400,
				height:300,
				baseImgX:150,                //设置图片框的位置x       
				baseImgY:100,                //设置图片框的位置y   
				baseImgWidth:100,            //图片剪裁框的宽度度
				baseImgHeight:100,           //图片剪裁框的高度
				baseImgBorder:true          //图像剪裁框是否有边框
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
			id:"addForm",
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
			},updatePhonebrandCombo,{
				x: 15,
				y: 65,
				xtype:'label',
				text: '平台名称:'
			},updatePlatformCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '手机图片:'
			},{
				x: 75,
				y: 90,
				width:100,
				xtype:"button",
				text:"选择图片",
				handler:function(){
					imageChooser.show();
				}
			},imgBox,{
				x: 15,
				y: 235,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 230,
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
    	if(Ext.WindowMgr.get("imagechooser1")){
    		Ext.WindowMgr.get("imagechooser1").close();
    	}
    	
    	if(Ext.WindowMgr.get("phonetypeimageCuter")){
    		Ext.WindowMgr.get("phonetypeimageCuter").close();
    	}
    },
    onUpdateWindowClose:function(p){
    	if(Ext.WindowMgr.get("imagechooser2")){
    		Ext.WindowMgr.get("imagechooser2").close();
    	}
    	
    	if(Ext.WindowMgr.get("phonetypeimageCuter2")){
    		Ext.WindowMgr.get("phonetypeimageCuter2").close();
    	}
    },
});