var newComponent = (function(){

	 var attachmentStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"json?softattactmentList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'attachmentId'},
           {name: 'softId'},
           {name: 'softName'},
           {name: 'downloadId'},
           {name: 'attachmentName'}
        ],
        sortInfo: {
            field: 'attachmentId', direction: 'ASC'
        }
    });
    
    var softIndexStore = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?softindexList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'indexId'},
           {name: 'phonetypeId'},
           {name: 'phonetypeName'},
           {name: 'attachmentId'}
        ],
        sortInfo: {
            field: 'indexId', direction: 'ASC'
        }
    });
    
    var softComboStore = new Ext.data.JsonStore({ 
   		storeId:"softComboStore",
		autoLoad : true,
		idProperty: 'softId',  	
		url :'json?comboSoft&type=json', 
		fields : ['softId', 'softName'] 
	});
	
	var phonetypeComboStore = new Ext.data.JsonStore({ 
   		storeId:"phonetypeComboStore",
		autoLoad : true,
		idProperty: 'phonetypeId',  	
		url :'json?comboPhonetype&type=json', 
		fields : ['phonetypeId', 'phonetypeName'] 
	});
    
    
    var softIndexGrid = new Ext.grid.GridPanel({
   		title:"支持型号",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: softIndexStore,
   		border:true,
   		columns:[
   			{id:'indexId',hidden:true,sortable: true, dataIndex: 'indexId'},
   			{header: "手机型号",sortable: true, dataIndex: 'phonetypeName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'indexId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	softindexAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(cId == undefined || cId ==  -1){
	              		Ext.Msg.alert('提示',"请先选择您要操作的列");
	              		return;
	             }
	              //alert(pId);
	             var record = softIndexStore.getAt(cId);
	             Ext.getCmp("softindexUpdateForm").getForm().setValues(record.data);
                 softindexUpdateWindow.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(cId == undefined || cId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var softindexId = softIndexStore.getAt(cId).data.softindexId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?softindex&type=json",
               				params:{
               					softindexId:softindexId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						softIndexStore.reload();
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
              	indexStore.reload();
             }
        }]
   	});
   	
   	var addSoftCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 30,
		width:127,
		store : softComboStore, 
		valueField : 'softId',// 域的值,对应于store里的fields 
		displayField : 'softName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"softId",
		mode:'local'
	}); 
	
	var updateSoftCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 30,
		width:127,
		store : softComboStore, 
		valueField : 'softId',// 域的值,对应于store里的fields 
		displayField : 'softName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"softId",
		mode:'local'
	});
   	
   	var attachmentAddWindow = new Ext.Window({
    	title: '手机软件附件添加',
		width: 250,
		height:170,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"attachmentAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '附件名称:'
			},{
				x: 75,
				y: 0,
				name: 'attachmentName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '软件名称:'
			},addSoftCombo,{
				x: 15,
				y: 65,
				xtype:'label',
				text: '附件上传:'
			},{
				x:75,
				y:60,
	            xtype: 'fileuploadfield',
	            id: 'upload',
	            anchor: '90%',
	            hideLabel :true,
	            emptyText: '请选择...',
	            fieldLabel: '附件',
	            name: 'upload',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'upload-icon'
	            }
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("attachmentAddForm").getForm().doAction('submit',{
					url:'add?softattachment&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						attachmentAddWindow.hide();
						attachmentStore.reload();
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
				attachmentAddWindow.hide();
			}
		}]
    });
    
   var addPhonetypeCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : phonetypeComboStore, 
		valueField : 'softId',// 域的值,对应于store里的fields 
		displayField : 'softName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"softId",
		mode:'local'
	});
	
	 var updatePhonetypeCombo = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : phonetypeComboStore, 
		valueField : 'softId',// 域的值,对应于store里的fields 
		displayField : 'softName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"softId",
		mode:'local'
	});
    
    var softindexAddWindow = new Ext.Window({
    	title: '添加支持手机型号',
		width: 250,
		height:170,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"softindexAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '手机型号:'
			},addPhonetypeCombo]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("softindexAddForm").getForm().doAction('submit',{
					url:'add?softindex&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						attachmentAddWindow.hide();
						attachmentStore.reload();
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
				softindexAddWindow.hide();
			}
		}]
    });
    
    var softindexUpdateWindow = new Ext.Window({
    	title: '修改支持手机型号',
		width: 250,
		height:170,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"softindexUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '手机型号:'
			},updatePhonetypeCombo]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("softindexUpdateForm").getForm().doAction('submit',{
					url:'update?softindex&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						softindexUpdateWindow.hide();
						softIndexStore.reload();
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
				softindexUpdateWindow.hide();
			}
		}]
    });
    
    var attachmentUpdateWindow = new Ext.Window({
    	title: '手机软件附件修改',
		width: 250,
		height:140,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide",
		items:new Ext.form.FormPanel({
			baseCls: 'x-plain',
			layout:'absolute',
			id:"attachmentUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '附件名称:'
			},{
				x: 75,
				y: 0,
				name: 'attachmentId',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 75,
				y: 0,
				name: 'attachmentName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '软件名称:'
			},updateSoftCombo]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("attachmentUpdateForm").getForm().doAction('submit',{
					url:'update?softattachment&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						attachmentUpdateWindow.hide();
						attachmentStore.reload();
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
				attachmentUpdateWindow.hide();
			}
		}]
    });
    
    var attachmentGrid = new Ext.grid.GridPanel({
   		title:"附件菜单",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: attachmentStore,
   		border:true,
   		columns:[
   			{id:'attachmentId',hidden:true,sortable: true, dataIndex: 'attachmentId'},
   			{header: "软件名称",sortable: true, dataIndex: 'softName'},
   			{header: "附件名称",sortable: true, dataIndex: 'attachmentName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'attachmentId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	attachmentAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
	             if(pId == undefined || pId ==  -1){
	              		Ext.Msg.alert('提示',"请先选择您要操作的列");
	              		return;
	             }
	              //alert(pId);
	             var record = attachmentStore.getAt(pId);
	             Ext.getCmp("attachmentUpdateForm").getForm().setValues(record.data);
                 attachmentUpdateWindow.show();
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
               		var attachmentId = attachmentStore.getAt(pId).data.attachmentId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?softattachment&type=json",
               				params:{
               					attachmentId:attachmentId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						attachmentStore.reload();
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
              	attachmentStore.reload();
             }
        }]
   	});
   	
   	var pId = -1;
   	
   	attachmentGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
   		softIndexStore.load({params:{attachmentId:r.data.attachmentId}});
    });
    
    attachmentGrid.on("beforeload",function(a,b){
    	pId = -1;
    });
    
    var cId = -1;
   	
   	softindexGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
    
    softindexGrid.on("beforeload",function(a,b){
    	cId = -1;
    });
    
    var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:900,
    	height:600,
    	border:false,
    	items:[attachmentGrid,softIndexGrid]
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
    
     panel.on('beforeclose',function(p,o){
    	
     });
    
    return panel;


})();