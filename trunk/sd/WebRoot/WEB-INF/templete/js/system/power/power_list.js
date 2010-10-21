var newComponent = (function() {

	//Ext.QuickTips.init();
	//main grid
    var store = new Ext.data.JsonStore({
    	url:"json?powerList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'powerId'},
           {name: 'powerName'},
           {name: 'powerValue'},
           {name: 'status'}
        ],
        sortInfo: {
            field: 'powerId', direction: 'ASC'
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
            {id:'powerId',hidden:true,sortable: true, dataIndex: 'powerId'},
            {header: "权限名称", width:100,sortable: true, dataIndex: 'powerName'},
            {header: "权值", width:850,sortable: true, dataIndex: 'powerValue',renderer:function(value, metaData, record, rowIndex, colIndex, store){
            	var callback = "";
            	var powers = value.split(",");
            	for(var i=0;i<powers.length;i++){
            		var record = powerValueStore.getById(powers[i]); 
            		if(record){
            			callback=callback+record.data.text+",";
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
            } },
        ],
        stripeRows: true,
        autoExpandColumn: 'powerId',
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
              	if(addForm){
              		//addForm.getForm().getEl().dom.reset();
              	}
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
               //powerValueStore.load();
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
               		var powerId = store.getAt(pId).data.powerId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?power&type=json",
               				params:{
               					powerId:powerId
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
   	var powerValueStore = new Ext.data.JsonStore({ 
		autoLoad : true,
		idProperty: 'code',  	
		url :'json?comboAllPower&type=json', 
		fields : ['code', 'text'] 
	});
	
	
    var addPowerValueCombo = new Ext.ux.form.LovCombo({ 
    	x: 75,
		y: 30,
		width: 270,
		fieldLabel : '分类名称', 
		hiddenName : 'powerValue',// 传递到后台的参数 
		store : powerValueStore, 
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
	
	  
    var addWindow = new Ext.Window({
    	title: '权限添加',
		width: 400,
		height:160,
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
					url:'add?power&type=json',
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
	
	
	addWindow.on("beforeshow",function(){
		addForm.getForm().reset();
	});
	
	//update
	
	var updatePowerValueCombo = new Ext.ux.form.LovCombo({ 
    	x: 75,
		y: 30,
		width: 270,
		fieldLabel : '分类名称', 
		hiddenName : 'powerValue',// 传递到后台的参数 
		store : powerValueStore, 
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
	
	
	var updateWindow = new Ext.Window({
		title: '权限修改',
		width: 400,
		height:160,
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
						url:'update?power&type=json',
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
    	title:"权限管理",
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