var newComponent = (function() {

	//Ext.QuickTips.init();
	//main grid
    var store = new Ext.data.JsonStore({
    	url:"json?equiList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'equipmentId'},
           {name: 'equipmentCode'},
           {name: 'equipmentName'},
           {name: 'mac'},
           {name: 'hallId'},
           {name: 'hallName'},
           {name: 'addTime'},
           {name: 'status'}
        ],
        sortInfo: {
            field: 'equipmentId', direction: 'ASC'
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
            } }
        ],
        stripeRows: true,
        autoExpandColumn: 'equipmentId',
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
               //alert("编辑");
               if(pId == undefined || pId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
               }
               //alert(pId);
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
               		var equipmentId = store.getAt(pId).data.equipmentId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?equipment&type=json",
               				params:{
               					equipmentId:equipmentId
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
   	var hallStore = new Ext.data.JsonStore({ 
		autoLoad : true,
		idProperty: 'hallId',  	
		url :'json?comboHall&type=json', 
		fields : ['hallId', 'hallName'] 
	});
	
	
    var addHallCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 60,
		width:127,
		store : hallStore, 
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
	
	  
    var addWindow = new Ext.Window({
    	title: '设备添加',
		width: 250,
		height:210,
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
					url:'add?equipment&type=json',
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
	
	  var updateHallCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 60,
		width:127,
		store : hallStore, 
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
	
	
	var updateWindow = new Ext.Window({
    	title: '设备修改',
		width: 250,
		height:210,
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
					url:'update?equipment&type=json',
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
    	title:"设备管理",
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