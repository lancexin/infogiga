var newComponent = (function() {

	//Ext.QuickTips.init();
	//main grid
    var store = new Ext.data.JsonStore({
    	url:"json?hallList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'hallId'},
           {name: 'hallName'},
           {name: 'description'},
           {name: 'addTime'},
           {name: 'status'},
           {name: 'cityId'},
           {name: 'cityName'}
        ],
        sortInfo: {
            field: 'hallId', direction: 'ASC'
        }
    });
    
    store.on("beforeload",function(a,b){
    	pId = -1;
    });
    
    store.load({params:{start:0,limit:20}});
    
    var dataGrid = new Ext.grid.GridPanel({
    	store: store,
       	region: 'center',
       	border:false,
        columns: [
            {id:'hallId',hidden:true,sortable: true, dataIndex: 'hallId'},
            {header: "店名", sortable: true, dataIndex: 'hallName'},
            {header: "地区", sortable: true, dataIndex: 'cityName'},
            {header: "添加时间", sortable: true, dataIndex: 'addTime'},
            {header: "介绍", sortable: true, dataIndex: 'description'},
            {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
            	if(value == 0){
            		return "停用";
            	}else{
            		return "启用";
            	}
            } },
        ],
        stripeRows: true,
        autoExpandColumn: 'hallId',
        loadMask: true,
        bbar: new Ext.PagingToolbar({
	        pageSize: 20,
	        store: store,
	        displayInfo: true,
	        displayMsg: '第{0}到{1}条数据 共{2}条',
	        emptyMsg: "没有记录"
	    }),
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	addWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
               if(pId == undefined || pId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
               }
               //cityStore.reload({params:{provinceId:-1}});
               var record = store.getAt(pId);
               updateForm.getForm().setValues(record.data);
               updateWindow.show();
               
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
               if(pId == undefined || pId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
               }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var hallId = store.getAt(pId).data.hallId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?hall&type=json",
               				params:{
               					hallId:hallId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store.reload({params:{start:0,limit:20}});
               					}
               				},
               				failure:function(response, options){
               					Ext.Msg.alert('提示',"连接服务器失败");
               				}
               			});
               		
               			return true;
               		}else{
               			return false;
               		}
               		
               });
             }
        },{
        	 text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
               store.reload({params:{start:0,limit:20}});
             }
        }]
    }); 
    
    
   
   	//add 
   	
   	
   	var cityStore = new Ext.data.JsonStore({ 
		autoLoad : false,
		idProperty: 'cityId',  	
		url :'json?comboCity&type=json', 
		fields : ['cityId', 'cityName'] 
	});
	
	var provinceStore = new Ext.data.JsonStore({ 
		autoLoad : false,
		idProperty: 'provinceId',  	
		url :'json?comboProvince&type=json', 
		fields : ['provinceId', 'provinceName'] 
	});
	
    var addProvinceCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 30,
		width:127,
		store : provinceStore, 
		valueField : 'provinceId',// 域的值,对应于store里的fields 
		displayField : 'provinceName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"provinceId"
	}); 
	
	addProvinceCombo.on("select",function(combo,record,index){
		cityStore.reload({params:{provinceId:record.data.provinceId}});
	});
	
	
	var addCityCombo = new Ext.form.ComboBox({
		x: 75,
		y: 60,
		width:127,
		store : cityStore, 
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
			text: '地区:'
		},addProvinceCombo,
		  addCityCombo,{
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
		},{
			x: 15,
			y: 125,
			xtype:'label',
			text: '介绍:'
		},{
			x: 75,
			y: 120,
			name: 'description',
			xtype:"textarea",
			width:127,
			allowBlank:false
		}]
	});
	
	  
    var addWindow = new Ext.Window({
    	title: '营业厅添加',
		width: 250,
		height:240,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:addForm,
		buttons: [{
			text: '添加',
			handler:function(){
				addForm.getForm().doAction('submit',{
					url:'add?hall&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						addWindow.hide();
						store.reload({params:{start:0,limit:20}});
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				addWindow.hide();
			}
		}]
    });
	
	
	//update
	 var updateProvinceCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 30,
		width:127,
		store : provinceStore, 
		valueField : 'provinceId',// 域的值,对应于store里的fields 
		displayField : 'provinceName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"provinceId"
	}); 
	
	updateProvinceCombo.on("select",function(combo,record,index){
		cityStore.reload({params:{provinceId:record.data.provinceId}});
	});
	
	var updateCityCombo = new Ext.form.ComboBox({
		x: 75,
		y: 60,
		width:127,
		store : cityStore, 
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
			xtype:'hidden',
		},{
			name: 'addTime',
			allowBlank:false,
			xtype:'hidden',
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
			text: '地区:'
		},updateProvinceCombo,
		  updateCityCombo,{
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
		},{
			x: 15,
			y: 125,
			xtype:'label',
			text: '介绍:'
		},{
			x: 75,
			y: 120,
			name: 'description',
			xtype:"textarea",
			width:127,
			allowBlank:false
		}]
	});
	
	
	var updateWindow = new Ext.Window({
    	title: '营业厅修改',
		width: 250,
		height:240,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:updateForm,
		buttons: [{
			text: '修改',
			handler:function(){
				updateForm.getForm().doAction('submit',{
					url:'update?hall&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						updateWindow.hide();
						store.reload({params:{start:0,limit:20}});
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		},{
			text: '取消',
			handler:function(){
				updateWindow.hide();
			}
		}]
    });
	
    var pId = -1;
    
    dataGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
    	pId = rowIdx;
    });

    
    //main panel
    var panel = new Ext.Panel({
    	title:"营业厅管理",
    	closable:true,
    	layout: 'border',
    	defaults: {
            split: true
        },
    	items:[dataGrid]
    });
    
     panel.on('beforeclose',function(p,o){
    	addWindow.close();
    	updateWindow.close();
     });
    
    return panel;
})();