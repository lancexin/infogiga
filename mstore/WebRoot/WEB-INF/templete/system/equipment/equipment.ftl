var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"equipment?type=json",
   	addUrl:"equipment?add&type=json",
   	updateUrl:"equipment?update&type=json",
   	deleteUrl:"equipment?delete&type=json",
   	storeField:[
   		{name: 'equipmentId'},
        {name: 'equipmentCode'},
        {name: 'equipmentName'},
        {name: 'mac'},
        {name: 'hallId'},
        {name: 'hallName'},
        {name: 'addTime'},
        {name: 'status'}
   	],
   	updateWindowConfig:{
   		title: '设备修改',
		width: 250,
		height:210,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	addWindowConfig:{
   		title: '设备添加',
		width: 250,
		height:210,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'equipmentId', direction: 'ASC'
    },
    deleteId:"equipmentId",
    gridColumns:[
         {id:'equipmentId',hidden:true,sortable: true, dataIndex: 'equipmentId'},
         {header: "设备名称", sortable: true, dataIndex: 'equipmentName'},
         {header: "设备码", sortable: true, dataIndex: 'equipmentCode'},
         {header: "MAC", sortable: true, dataIndex: 'mac'},
         {header: "营业厅", sortable: true, dataIndex: 'hallName'},
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
    gridAutoExpandColumn:"equipmentId",
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"hallStore",
		autoLoad : true,
		idProperty: 'hallId',  	
		url :'hall?comboHall&type=json', 
		fields : ['hallId', 'hallName'] 
	})],
	getAddForm:function(){
    
    	var addHallCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("hallStore"), 
			valueField : 'hallId',// 域的值,对应于store里的fields 
			displayField : 'hallName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"hallId",
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
				text: '设备名称:'
			},{
				x: 75,
				y: 0,
				name: 'equipmentName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: 'MAC:'
			},{
				x: 75,
				y: 30,
				name: 'mac',
				allowBlank:false
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '营业厅:'
			},addHallCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 90,
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
    	var updateHallCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 60,
			width:127,
			store : Ext.StoreMgr.get("hallStore"), 
			valueField : 'hallId',// 域的值,对应于store里的fields 
			displayField : 'hallName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"hallId",
			mode:'local'
		}); 
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [{
				name: 'equipmentId',
				xtype:'hidden',
				allowBlank:false
			},{
				name: 'equipmentCode',
				xtype:'hidden',
				allowBlank:false
			},{
				name: 'addTime',
				xtype:'hidden',
				allowBlank:false
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '设备名称:'
			},{
				x: 75,
				y: 0,
				name: 'equipmentName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: 'MAC:'
			},{
				x: 75,
				y: 30,
				name: 'mac',
				allowBlank:false
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '营业厅:'
			},updateHallCombo,{
				x: 15,
				y: 95,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 90,
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
    }
});