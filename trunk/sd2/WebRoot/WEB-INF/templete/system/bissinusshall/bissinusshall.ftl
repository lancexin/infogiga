var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"hall?type=json",
   	addUrl:"hall?add&type=json",
   	updateUrl:"hall?update&type=json",
   	deleteUrl:"hall?delete&type=json",
   	storeField:[
   		{name: 'hallId'},
        {name: 'hallName'},
        {name: 'description'},
        {name: 'addTime'},
        {name: 'code'},
        {name: 'status'},
        {name: 'cityId'},
        {name: 'cityName'}
   	],
   	updateWindowConfig:{
   		title: '管理员修改',
		width: 250,
		height:290,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	addWindowConfig:{
   		title: '管理员添加',
		width: 250,
		height:290,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'hallId', direction: 'ASC'
    },
    deleteId:"hallId",
    gridColumns:[
           {id:'hallId',hidden:true,sortable: true, dataIndex: 'hallId'},
           {header: "店名", sortable: true, dataIndex: 'hallName'},
           {header: "编号", sortable: true, dataIndex: 'code'},
           {header: "地区", sortable: true, dataIndex: 'cityName'},
           {header: "添加时间", sortable: true, dataIndex: 'addTime'},
           {header: "介绍", sortable: true, dataIndex: 'description'},
           {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
           	if(value == 0){
           		return "停用";
           	}else{
           		return "启用";
           	}
           } }
       ],
    gridAutoExpandColumn:"hallId",
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"cityStore",
		autoLoad : false,
		idProperty: 'cityId',  	
		url :'city?comboCity&type=json', 
		fields : ['cityId', 'cityName'] 
	}),new Ext.data.JsonStore({ 
		storeId:"provinceStore",
		autoLoad : false,
		idProperty: 'provinceId',  	
		url :'province?comboProvince&type=json', 
		fields : ['provinceId', 'provinceName'] 
	})],
	getAddForm:function(){
	
		var addProvinceCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("provinceStore"), 
			valueField : 'provinceId',// 域的值,对应于store里的fields 
			displayField : 'provinceName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"provinceId"
		}); 
		
		addProvinceCombo.on("select",function(combo,record,index){
			Ext.StoreMgr.get("cityStore").reload({params:{provinceId:record.data.provinceId}});
		});
    
    	var addCityCombo = new Ext.form.ComboBox({
			x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("cityStore"), 
			valueField : 'cityId',// 域的值,对应于store里的fields 
			displayField : 'cityName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"cityId",
			mode:'local'
		});
		
		var addForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"addForm",
			defaultType: 'textfield',
			items: [{
				x: 15,
				y: 5,
				xtype:'label',
				text: '店名:'
			},{
				x: 75,
				y: 0,
				name: 'hallName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '编号:'
			},{
				x: 75,
				y: 30,
				name: 'code',
				allowBlank:false
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '地区:'
			},addProvinceCombo,
			  addCityCombo,{
				x: 15,
				y: 125,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 120,
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
			},{
				x: 15,
				y: 155,
				xtype:'label',
				text: '介绍:'
			},{
				x: 75,
				y: 150,
				name: 'description',
				xtype:"textarea",
				width:127,
				allowBlank:false
			}]
		});
		
		return addForm;
    },
    getUpdateForm:function(){
    	var updateProvinceCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("provinceStore"), 
			valueField : 'provinceId',// 域的值,对应于store里的fields 
			displayField : 'provinceName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"provinceId"
		}); 
		
		updateProvinceCombo.on("select",function(combo,record,index){
			Ext.StoreMgr.get("cityStore").reload({params:{provinceId:record.data.provinceId}});
		});
		
		var updateCityCombo = new Ext.form.ComboBox({
			x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("cityStore"),
			valueField : 'cityId',// 域的值,对应于store里的fields 
			displayField : 'cityName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"cityId",
			mode:'local'
		});
		
		
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [{
				name: 'hallId',
				allowBlank:false,
				xtype:'hidden'
			},{
				name: 'addTime',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '店名:'
			},{
				x: 75,
				y: 0,
				name: 'hallName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '编号:'
			},{
				x: 75,
				y: 30,
				name: 'code',
				allowBlank:false
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '地区:'
			},updateProvinceCombo,
			  updateCityCombo,{
				x: 15,
				y: 125,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 120,
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
			},{
				x: 15,
				y: 155,
				xtype:'label',
				text: '介绍:'
			},{
				x: 75,
				y: 150,
				name: 'description',
				xtype:"textarea",
				width:127,
				allowBlank:false
			}]
		});
		return updateForm;
    }
});