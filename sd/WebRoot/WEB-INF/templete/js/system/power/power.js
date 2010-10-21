var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"json?powerList&type=json",
   	addUrl:"add?power&type=json",
   	updateUrl:"update?power&type=json",
   	deleteUrl:"delete?power&type=json",
   	storeField:[
   		{name: 'powerId'},
        {name: 'powerName'},
        {name: 'powerValue'},
        {name: 'status'}
   	],
   	updateWindowConfig:{
   		title: '权限修改',
		width: 400,
		height:160,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	addWindowConfig:{
   		title: '权限添加',
		width: 400,
		height:160,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'powerId', direction: 'ASC'
    },
    deleteId:"powerId",
    gridColumns:[
            {id:'powerId',hidden:true,sortable: true, dataIndex: 'powerId'},
            {header: "权限名称", width:100,sortable: true, dataIndex: 'powerName'},
            {header: "权值", width:850,sortable: true, dataIndex: 'powerValue',renderer:function(value, metaData, record, rowIndex, colIndex, store){
            	var callback = "";
            	var powers = value.split(",");
            	for(var i=0;i<powers.length;i=i+1){
            		var r = Ext.StoreMgr.get("powerValueStore").getById(powers[i]); 
            		if(r){
            			callback=callback+r.data.text+",";
            		}
            	}
            	return callback;
            }},
            {header: "状态",width:50,sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
            	if(value == 0){
            		return "停用";
            	}else{
            		return "启用";
            	}
            } }
        ],
    gridAutoExpandColumn:"powerId",
   	comboStore:[new Ext.data.JsonStore({ 
   		storeId:"powerValueStore",
		autoLoad : true,
		idProperty: 'code',  	
		url :'json?comboAllPower&type=json', 
		fields : ['code', 'text'] 
	})],
	getAddForm:function(){
		var addPowerValueCombo = new Ext.ux.form.LovCombo({ 
	    	x: 75,
			y: 30,
			width: 270,
			fieldLabel : '分类名称', 
			hiddenName : 'powerValue',// 传递到后台的参数 
			store : Ext.StoreMgr.get("powerValueStore"), 
			valueField : 'code',// 域的值,对应于store里的fields 
			displayField : 'text',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			allowBlank:false,
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
				text: '权限名称:'
			},{
				x: 75,
				y: 0,
				name: 'powerName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '权值:'
			},addPowerValueCombo,{
				x: 15,
				y: 65,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 60,
				xtype:'combo',
				name: 'status',
				mode: 'local',
				editable:false,
				allowBlank:false,
				triggerAction: 'all',
				typeAhead: true,
				store:new Ext.data.ArrayStore({
			        fields: [
			            'statusId',
			            'text'
			        ],
			        data: [[0, '停用'],[1, '启用']]
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
    	var updatePowerValueCombo = new Ext.ux.form.LovCombo({ 
	    	x: 75,
			y: 30,
			width: 270,
			fieldLabel : '分类名称', 
			hiddenName : 'powerValue',// 传递到后台的参数 
			store : Ext.StoreMgr.get("powerValueStore"), 
			valueField : 'code',// 域的值,对应于store里的fields 
			displayField : 'text',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false, 	
			allowBlank:false,
			mode:'local'
	
		}); 
		
		var updateForm = new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"updateForm",
			defaultType: 'textfield',
			items: [{
				x: 75,
				y: 0,
				name: 'powerId',
				xtype:"hidden" 
			},{
				x: 15,
				y: 5,
				xtype:'label',
				text: '权限名称:'
			},{
				x: 75,
				y: 0,
				name: 'powerName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '权值:'
			},updatePowerValueCombo,{
				x: 15,
				y: 65,
				xtype:'label',
				text: '状态:'
			},{
				x: 75,
				y: 60,
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