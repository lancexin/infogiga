var newComponent = (function() {

	var store = new Ext.data.JsonStore({
	    	url:"downloadstat?type=json",
	    	root:"data",
	    	totalProperty:"totalCount",//返回的总页数
	        fields: [
	            {name: 'statId'},
		        {name: 'equipmentName'},
		        {name: 'hallName'},
		        {name: 'softName'},
		        {name: 'phonetypeName'},
		        {name: 'phonebrandName'},
		        {name: 'employeeName'},
		        {name: 'employeeNo'},
		        {name: 'downloadtypeName'},
		        {name: 'phoneNumber'},
		        {name: 'addTime'}
	        ],
	        sortInfo: {
	            field: 'statId', direction: 'ASC'
	        }
	    });
	    
	    var downloadtypeStore = new Ext.data.JsonStore({ 
	   		storeId:"softComboStore",
			autoLoad : true,
			idProperty: 'downloadtypeId',  	
			url :'downloadstat?comboDownloadtype&type=json', 
			fields : ['downloadtypeId', 'downloadtypeName'] 
		});
	    
	    store.load({params:{start:0,limit:20}});
	    var record_start = 0;
	
		var downloadtypeCombo = new Ext.form.ComboBox({ 
	    	x:496,
			y:5,
			width:127,
			store : downloadtypeStore, 
			valueField : 'downloadtypeId',// 域的值,对应于store里的fields 
			displayField : 'downloadtypeName',// 显示的域,对应于store里的fields 
			hideOnSelect:false,
			triggerAction:'all',
			editable : false,
			hiddenName:"downloadtypeId",
			mode:'local',
			lazyInit:true
		});
	
		var dataGrid = new Ext.grid.GridPanel({
	    	xtye:'gridpanel',
	    	collapsible:false,
	    	store: store,
	       	region: 'center',
			tbar:[{
	        	 text: 'Excel导出',
	        	 iconCls:'excel',
	             handler : function(){
	             	var params = Ext.getCmp('searchForm').getForm().getValues(true);
	              	//window.loaction.href = 'export?softdownloadstat&'+params;
	              	window.location = 'export?softdownloadstat&'+params;
	              	//alert(window.location);
	             }
	        }],
	
	       	collapsible: false,
	        columns: [
	        	new Ext.grid.RowNumberer({
	        		renderer:function(value,metadata,record,rowIndex){
				　　　return　record_start　+　1　+　rowIndex;
				　　}
	        	}), 
	            {id:'statId',hidden:true,sortable: true, dataIndex: 'statId'},
	            {header: "员工姓名", sortable: true, dataIndex: 'employeeName'},
	            {header: "员工账号", sortable: true, dataIndex: 'employeeNo'},
	            {header: "设备名称", sortable: true, dataIndex: 'equipmentName'},
	            {header: "营业厅", sortable: true, dataIndex: 'hallName'},
	            {header: "软件名称", sortable: true, dataIndex: 'softName'},
	            {header: "手机厂商", sortable: true, dataIndex: 'phonebrandName'},
	            {header: "手机型号", sortable: true, dataIndex: 'phonetypeName'},
	            {header: "手机号码", sortable: true, dataIndex: 'phoneNumber'},
	            {header: "下载类型", sortable: true, dataIndex: 'downloadtypeName'},
	            {header: "下载时间", sortable: true, dataIndex: 'addTime'}
	        ],
	        stripeRows: true,
	        autoExpandColumn: 'statId',
	        loadMask: true,
	        bbar: new Ext.PagingToolbar({
		        pageSize: 20,
		        store: store,
		        displayInfo: true,
		        displayMsg: '第{0}到{1}条数据 共{2}条',
		        emptyMsg: "没有记录",
			　　 doLoad　:　function(start){
					record_start　=　start;
					var o = {}, pn = this.getParams(); 
					o[pn.start]　=　start;
					o[pn.limit]　=　this.pageSize;
					var params = Ext.getCmp('searchForm').getForm().getValues(false);
					Ext.apply(o,params);
					this.store.reload({params:o});
			　　}
		    })
	    });
	    
	    
		var panel = new Ext.Panel({
			id:"${code}",
			title:"${name}",
	        layout: 'border',
	        border:false,
	        closable:true,
	        items: [{
		    	region: 'south',
		        title: '搜索栏',
		        collapsible:true,
		        height:130,
		        border:false,
		        items:new Ext.form.FormPanel({
			        layout:'absolute',
			        height:110,
			   		style:' font-size: 11px;',
			        id:'searchForm',
			        defaultType: 'textfield',
					margin:5,
			        items: [{
			        	x:15,
			        	y:10,
			            xtype:'label',
			            text:'员工姓名：'
			        },{
			        	x:75,
			        	y:5,
			            name: 'employeeName'
			        },{
			        	x:15,
			        	y:40,
			            xtype:'label',
			            text:'员工帐号：'
			        },{
			        	x:75,
			        	y:35,
			            name: 'employeeNo'
			        },{
			        	x:15,
			        	y:70,
			            xtype:'label',
			            text:'设备名称：'
			        },{
			        	x:75,
			        	y:65,
			            name: 'equipmentName'
			        },{
			        	x:235,
			        	y:10,
			            xtype:'label',
			            text:'手机厂商：'
			        },{
			        	x:295,
			        	y:5,
			            name: 'phonebrandName'
			        },{
			        	x:235,
			        	y:40,
			            xtype:'label',
			            text:'手机型号：'
			        },{
			        	x:295,
			        	y:35,
			            name: 'phonetypeName'
			        },{
			        	x:235,
			        	y:70,
			            xtype:'label',
			            text:'软件名称：'
			        },{
			        	x:295,
			        	y:65,
			            name: 'softName'
			        },{
			        	x:435,
			        	y:10,
			            xtype:'label',
			            text:'下载类型：'
			        },downloadtypeCombo,{
			        	x:435,
			        	y:40,
			            xtype:'label',
			            text:'下载时间：'
			        },{
			        	x:496,
			        	y:35,
			        	xtype:'datefield',
			            name: 'startTime',
			            format:'Y-m-d', 
			            width:127
			        },{
			        	x:630,
			        	y:35,
			        	xtype:'label',
			            text:'~'
			        },{
			        	x:435,
			        	y:70,
			            xtype:'label',
			            text:'营业厅：'
			        },{
			        	x:495,
			        	y:65,
			            name: 'hallName'
			        },{
			        	x:640,
			        	y:35,
			        	xtype:'datefield',
			            name: 'endTime',
			            format:'Y-m-d', 
			            width:127
			        },{
			        	x:640,
			        	y:64,
			        	xtype:'button',
			            text:'搜索',
			            iconCls: 'button-search',
			            handler:function(){
			            	var params = Ext.getCmp('searchForm').getForm().getValues(false);
			            	params.start = 0;
			            	params.limit = 20;
			            	store.reload({params:params});
			            }
			        },{
			        	x:700,
			        	y:64,
			        	xtype:'button',
			            text:'清空',
			            iconCls: 'button-search-clear',
			            handler:function(){
			            	Ext.getCmp('searchForm').getForm().reset();
			            	var params = Ext.getCmp('searchForm').getForm().getValues(false);
			            	params.start = 0;
			            	params.limit = 20;
			            	store.reload({params:params});
			            }
			        }]
			    })
		    },dataGrid]
		});

   		return panel;
})();