var newComponent = (function(){

	var phonebrandGrid = new Ext.ux.CommonTabPanel({
		id:"phonebrandGrid",
		title:"厂商管理",
		storeUrl:"json?phonebrandList&type=json",
	   	addUrl:"add?phonebrand&type=json",
	   	updateUrl:"update?phonebrand&type=json",
	   	deleteUrl:"delete?phonebrand&type=json",
	   	storeField:[
	   		 {name: 'phonebrandId'},
         	 {name: 'phonebrandName'}
	   	],
	   	updateWindowConfig:{
	   		title: '省修改',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	addWindowConfig:{
	   		title: '手机厂商添加',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	storeSortInfo: {
	        field: 'phonebrandId', direction: 'ASC'
	    },
	    deleteId:"phonebrandId",
	    gridColumns:[
   			{id:'phonebrandId',hidden:true,sortable: true, dataIndex: 'phonebrandId'},
   			{header: "厂商名称",width:270, sortable: true, dataIndex: 'phonebrandName'}
   		],
	    gridAutoExpandColumn:"hallId",
	   	comboStore:[],
		getAddForm:function(){
		
			var addForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"phonebrandAddForm",
				defaultType: 'textfield',
				items:[
					{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'phonebrandName',
						allowBlank:false
					}
				]
			});
			
			return addForm;
	    },
	    getUpdateForm:function(){
	    	var updateForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"phonebrandUpdateForm",
				defaultType: 'textfield',
				items:[
					{
						name: 'phonebrandId',
						xtype:"hidden",
						allowBlank:false
					},{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'phonebrandName',
						allowBlank:false
					}
				]
			});
			return updateForm;
	    }
	});

	
	var platformGrid = new Ext.ux.CommonTabPanel({
		id:"platformGrid",
		title:"厂商管理",
		storeUrl:"json?platformList&type=json",
	   	addUrl:"add?platform&type=json",
	   	updateUrl:"update?platform&type=json",
	   	deleteUrl:"delete?platform&type=json",
	   	storeField:[
	   		 {name: 'platformId'},
         	 {name: 'platformName'}
	   	],
	   	updateWindowConfig:{
	   		title: '手机平台修改',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	addWindowConfig:{
	   		title: '手机平台添加',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	storeSortInfo: {
	        field: 'platformId', direction: 'ASC'
	    },
	    deleteId:"platformId",
	    gridColumns:[
   			{id:'phonebrandId',hidden:true,sortable: true, dataIndex: 'phonebrandId'},
   			{header: "厂商名称",width:270, sortable: true, dataIndex: 'phonebrandName'}
   		],
	    gridAutoExpandColumn:"platformId",
	   	comboStore:[],
		getAddForm:function(){
		
			var addForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"platformAddForm",
				defaultType: 'textfield',
				items:[
					{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'platformName',
						allowBlank:false
					}
				]
			});
			
			return addForm;
	    },
	    getUpdateForm:function(){
	    	var updateForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"platformUpdateForm",
				defaultType: 'textfield',
				items:[
					{
						name: 'platformId',
						xtype:"hidden",
						allowBlank:false
					},{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'platformName',
						allowBlank:false
					}
				]
			});
			return updateForm;
	    }
	});
	
	var softmenuGrid = new Ext.ux.CommonTabPanel({
		id:"softmenuGrid",
		title:"厂商管理",
		storeUrl:"json?softmenuList&type=json",
	   	addUrl:"add?softmenu&type=json",
	   	updateUrl:"update?softmenu&type=json",
	   	deleteUrl:"delete?softmenu&type=json",
	   	storeField:[
	   		 {name: 'softmenuId'},
         	 {name: 'softmenuName'}
	   	],
	   	updateWindowConfig:{
	   		title: '手机平台修改',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	addWindowConfig:{
	   		title: '手机平台添加',
			width: 250,
			height:100,
			layout: 'fit',
			plain:true,
			closable :true,
			collapsible : true ,
			bodyStyle:'padding:5px;',
			buttonAlign:'center',
			closeAction:"hide"
	   	},
	   	storeSortInfo: {
	        field: 'softmenuId', direction: 'ASC'
	    },
	    deleteId:"softmenuId",
	    gridColumns:[
   			{id:'softmenuId',hidden:true,sortable: true, dataIndex: 'softmenuId'},
   			{header: "厂商名称",width:270, sortable: true, dataIndex: 'softmenuName'}
   		],
	    gridAutoExpandColumn:"softmenuId",
	   	comboStore:[],
		getAddForm:function(){
		
			var addForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"softmenuAddForm",
				defaultType: 'textfield',
				items:[
					{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'softmenuName',
						allowBlank:false
					}
				]
			});
			
			return addForm;
	    },
	    getUpdateForm:function(){
	    	var updateForm = new Ext.form.FormPanel({
				baseCls: 'x-plain',
				layout:'absolute',
				id:"softmenuUpdateForm",
				defaultType: 'textfield',
				items:[
					{
						name: 'softmenuId',
						xtype:"hidden",
						allowBlank:false
					},{
						x: 15,
						y: 5,
						xtype:'label',
						text: '厂商名称:'
					},{
						x: 75,
						y: 0,
						name: 'softmenuName',
						allowBlank:false
					}
				]
			});
			return updateForm;
	    }
	});

   	var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:600,
    	height:600,
    	border:false,
    	items:[phonebrandGrid,platformGrid,softmenuGrid]
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

    
    return panel;









})();