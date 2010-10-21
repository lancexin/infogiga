var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"json?adminList&type=json",
   	addUrl:"add?admin&type=json",
   	updateUrl:"update?admin&type=json",
   	deleteUrl:"delete?admin&type=json",
   	storeField:[
   		{name: 'adminId'},
        {name: 'nickName'},
        {name: 'userName'},
        {name: 'passWord'},
        {name: 'powerId'},
        {name: 'powerName'},
        {name: 'status'},
        {name: 'addTime'}
   	],
   	updateWindowConfig:{
   		title: '管理员修改',
		width: 250,
		height:240,
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
		height:240,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'adminId', direction: 'ASC'
    },
    deleteId:"adminId",
    gridColumns:[
            {id:'adminId',hidden:true,sortable: true, dataIndex: 'adminId'},
            {header: "昵称", sortable: true, dataIndex: 'nickName'},
            {header: "用户名", sortable: true, dataIndex: 'userName'},
            {header: "密码", sortable: true, dataIndex: 'passWord'},
            {header: "身份", sortable: true, dataIndex: 'powerName'},
          	{header: "添加时间", sortable: true, dataIndex: 'addTime'},
            {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
            	if(value == 0){
            		return "停用";
            	}else{
            		return "启用";
            	}
            } },
        ],
    gridAutoExpandColumn:"adminId",
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"powerStore",
		autoLoad : true,
		idProperty: 'powerId',  	
		url :'json?comboPower&type=json', 
		fields : ['powerId', 'powerName'] 
	})],
	getAddForm:function(){
    
    	var addPowerCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("powerStore"), 
			valueField : 'powerId',// 域的值,对应于store里的fields 
			displayField : 'powerName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"powerId",
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
				text: '昵称:'
			},{
				x: 75,
				y: 0,
				name: 'nickName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '用户名:'
			},{
				x: 75,
				y: 30,
				name: 'userName',
				allowBlank:false
			
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '密码:'
			},{
				x: 75,
				y: 60,
				name: 'passWord',
				allowBlank:false
			},{
				x: 15,
				y: 95,
				xtype:'label',
				text: '权限:'
			},addPowerCombo,{
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
			}]
		});
		
		return addForm;
    },
    getUpdateForm:function(){
    	var updatePowerCombo = new Ext.form.ComboBox({ 
	    	x: 75,
			y: 90,
			width:127,
			store : Ext.StoreMgr.get("powerStore"), 
			valueField : 'powerId',// 域的值,对应于store里的fields 
			displayField : 'powerName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
			hiddenName:"powerId",
			mode:'local'
		}); 
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [{
				name: 'adminId',
				allowBlank:false,
				xtype:'hidden',
			},{
				name: 'addTime',
				allowBlank:false,
				xtype:'hidden',
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '昵称:'
			},{
				x: 75,
				y: 0,
				name: 'nickName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '用户名:'
			},{
				x: 75,
				y: 30,
				name: 'userName',
				allowBlank:false
			
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '密码:'
			},{
				x: 75,
				y: 60,
				name: 'passWord',
				allowBlank:false
			},{
				x: 15,
				y: 95,
				xtype:'label',
				text: '权限:'
			},updatePowerCombo,{
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
			}]
		});
		return updateForm;
    }
});