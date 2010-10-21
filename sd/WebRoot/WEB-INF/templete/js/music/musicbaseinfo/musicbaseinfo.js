var newComponent = (function(){

	 var store1 = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"json?musicmanList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'musicmanId'},
           {name: 'musicmanName'}
        ],
        sortInfo: {
            field: 'musicmanId', direction: 'ASC'
        }
    });
    
    store1.on("beforeload",function(a,b){
    	pId = -1;
    	store2.removeAll();
    	store3.removeAll();
    });
    
     var store2 = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?musicList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'musicId'},
           {name: 'musicName'},
           {name: 'musicmanId'},
           {name: 'musicmanName'},
           {name: 'description'},
           {name: 'addTime'},
           {name: 'status'},
           {name: 'downloadCount'},
           {name: 'downloadId'},
           {name: 'downloadCode'}
        ],
        sortInfo: {
            field: 'musicId', direction: 'ASC'
        }
    });
    
    store2.on("beforeload",function(a,b){
    	pId = -1;
    	store3.removeAll();
    });
    
    var store3 = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?musicindexList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'indexId'},
           {name: 'musicId'},
           {name: 'musicName'},
           {name: 'musicmenuId'},
           {name: 'musicmenuName'}
        ],
        sortInfo: {
            field: 'indexId', direction: 'ASC'
        }
    });
    
     var comboStore1 = new Ext.data.JsonStore({ 
   		storeId:"musicmenuCombo",
		autoLoad : true,
		idProperty: 'musicmenuId',  	
		url :'json?comboMusicmenu&type=json', 
		fields : ['musicmenuId', 'musicmenuName'] 
	});
    
    
   	var addWindow1 = new Ext.Window({
    	title: '歌手添加',
		width: 250,
		height:100,
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
			id:"musicmanAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '歌手姓名:'
			},{
				x: 75,
				y: 0,
				name: 'musicmanName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("musicmanAddForm").getForm().doAction('submit',{
					url:'add?musicman&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						addWindow1.hide();
						store1.reload();
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
				addWindow1.hide();
			}
		}]
    });
    
   
    
    var updateWindow1 = new Ext.Window({
    	title: '歌手修改',
		width: 250,
		height:100,
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
			id:"musicmanUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '歌手姓名:'
			},{
				name: 'musicmanId',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 75,
				y: 0,
				name: 'musicmanName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("musicmanUpdateForm").getForm().doAction('submit',{
					url:'update?musicman&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						updateWindow1.hide();
						store1.reload();
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
				updateWindow1.hide();
			}
		}]
    });
    
    var grid1 = new Ext.grid.GridPanel({
   		title:"歌手菜单",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: store1,
   		border:true,
   		columns:[
   			{id:'musicmanId',hidden:true,sortable: true, dataIndex: 'musicmanId'},
   			{header: "歌手姓名",sortable: true, dataIndex: 'musicmanName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'musicmanId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	addWindow1.show();
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
	             var record = store1.getAt(pId);
	             Ext.getCmp("musicmanUpdateForm").getForm().setValues(record.data);
                 updateWindow1.show();
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
               		var musicmanId = store1.getAt(pId).data.musicmanId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?musicman&type=json",
               				params:{
               					musicmanId:musicmanId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store1.reload();
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
              	store1.reload();
             }
        }]
   	});
   	
   	var pId = -1;
   	
   	grid1.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
   		store2.load({params:{musicmanId:r.data.musicmanId}});
    });
    
    grid1.on("beforeload",function(a,b){
    	pId = -1;
    });
    
    var addWindow2 = new Ext.Window({
    	title: '歌曲添加',
		width: 250,
		height:200,
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
			id:"musicAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '歌曲名称:'
			},{
				x: 75,
				y: 0,
				name: 'musicName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '附件:'
			},{
				x: 75,
				y: 30,
				anchor: '90%',
				xtype: 'fileuploadfield',
				emptyText: '请选择...',
	            fieldLabel: '附件',
				name: 'upload',
				allowBlank:false,
				buttonText: '',
	            buttonCfg: {
	                iconCls: 'upload-icon'
	            }
			},{
				x: 15,
				y: 65,
				xtype:'label',
				text: '描述:'
			},{
				x: 75,
				y: 60,
				xtype:'textarea',
				name:'description',
				allowBlank:false,
				width:127
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var musicmanId = store1.getAt(pId).data.musicmanId;
				Ext.getCmp("musicAddForm").getForm().doAction('submit',{
					url:'add?music&type=json',
                    method:'post',
                    params:{
                    	musicmanId:musicmanId
                    },
					success:function(form,action){
						addWindow2.hide();
						store2.reload();
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
				addWindow2.hide();
			}
		}]
    });
    
    var updateWindow2 = new Ext.Window({
    	title: '歌曲修改',
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
			id:"musicUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '歌曲名称:'
			},{
				name: 'musicId',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 75,
				y: 0,
				name: 'musicName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '描述:'
			},{
				x: 75,
				y: 30,
				xtype:'textarea',
				name:'description',
				width:127,
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				var musicmanId = store1.getAt(pId).data.musicmanId;
				Ext.getCmp("musicUpdateForm").getForm().doAction('submit',{
					url:'update?music&type=json',
                    method:'post',
                    params:{
                    	musicmanId:musicmanId
                    },
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						updateWindow2.hide();
						store2.reload();
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
				updateWindow2.hide();
			}
		}]
    });
    
    
    var grid2 = new Ext.grid.GridPanel({
   		title:"歌曲菜单",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: store2,
   		border:true,
   		columns:[
   			{id:'musicId',hidden:true,sortable: true, dataIndex: 'musicId'},
   			{header: "音乐名称",sortable: true, dataIndex: 'musicName'},
   			{header: "添加时间",sortable: true, dataIndex: 'addTime'},
   			{header: "下载次数",sortable: true, dataIndex: 'downloadCount'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'musicId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	if(pId == undefined || pId ==  -1){
	              		return;
	            }
             	addWindow2.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
	             if(pId2 == undefined || pId2 ==  -1){
	              		Ext.Msg.alert('提示',"请先选择您要操作的列");
	              		return;
	             }
	              //alert(pId);
	             var record = store2.getAt(pId2);
	             Ext.getCmp("musicUpdateForm").getForm().setValues(record.data);
                 updateWindow2.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(pId2 == undefined || pId2 ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var musicId = store2.getAt(pId2).data.musicId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?music&type=json",
               				params:{
               					musicId:musicId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store2.reload();
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
              	store2.reload();
             }
        }]
   	});
   	
   	var pId2 = -1;
   	
   	grid2.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId2 = rowIdx;
   		store3.load({params:{musicId:r.data.musicId}});
    });
    
    grid2.on("beforeload",function(a,b){
    	pId2 = -1;
    });  
    
    var addCombo1 = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : comboStore1, 
		valueField : 'musicmenuId',// 域的值,对应于store里的fields 
		displayField : 'musicmenuName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"musicmenuId",
		mode:'local'
	}); 
		
	var updateCombo1 = new Ext.form.ComboBox({ 
    	x: 75,
		y: 0,
		width:127,
		store : comboStore1, 
		valueField : 'musicmenuId',// 域的值,对应于store里的fields 
		displayField : 'musicmenuName',// 显示的域,对应于store里的fields 
		hideOnSelect:false,
		triggerAction:'all',
		editable : false,
		allowBlank:false,
		hiddenName:"musicmenuId",
		mode:'local'
	}); 
    
   var addWindow3 = new Ext.Window({
    	title: '添加所属菜单',
		width: 250,
		height:100,
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
			id:"musicindexAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},addCombo1]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var musicId = store2.getAt(pId2).data.musicId;
				Ext.getCmp("musicindexAddForm").getForm().doAction('submit',{
					url:'add?musicindex&type=json',
                    method:'post',
                    params:{
                    	musicId:musicId
                    },
					success:function(form,action){
						addWindow3.hide();
						store3.reload();
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
				addWindow3.hide();
			}
		}]
    });
    
    var updateWindow3 = new Ext.Window({
    	title: '所属菜单修改',
		width: 250,
		height:100,
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
			id:"musicindexUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				name: 'indexId',
				allowBlank:false,
				xtype:'hidden'
			},updateCombo1]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				var musicId = store2.getAt(pId2).data.musicId;
				Ext.getCmp("musicindexUpdateForm").getForm().doAction('submit',{
					url:'update?musicindex&type=json',
                    method:'post',
                    params:{
                    	musicId:musicId
                    },
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						updateWindow3.hide();
						store3.reload();
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
				updateWindow3.hide();
			}
		}]
    });
    
    
    var grid3 = new Ext.grid.GridPanel({
   		title:"所属菜单列表",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: store3,
   		border:true,
   		columns:[
   			{id:'indexId',hidden:true,sortable: true, dataIndex: 'indexId'},
   			{header: "菜单名称",sortable: true, dataIndex: 'musicmenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'indexId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	if(pId2 == undefined || pId2 ==  -1){
	              		return;
	            }
             	addWindow3.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
	             if(pId3 == undefined || pId3 ==  -1){
	              		Ext.Msg.alert('提示',"请先选择您要操作的列");
	              		return;
	             }
	              //alert(pId);
	             var record = store3.getAt(pId3);
	             Ext.getCmp("musicindexUpdateForm").getForm().setValues(record.data);
                 updateWindow3.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(pId3 == undefined || pId3 ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var indexId = store3.getAt(pId3).data.indexId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?musicindex&type=json",
               				params:{
               					indexId:indexId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						store3.reload();
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
              	store3.reload();
             }
        }]
   	});
   	
   	var pId3 = -1;
   	
   	grid3.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId3 = rowIdx;
    });
    
    grid3.on("beforeload",function(a,b){
    	pId3 = -1;
    });  
    
    

    var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:900,
    	height:600,
    	border:false,
    	items:[grid1,grid2,grid3]
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
    	addWindow1.close();
    	updateWindow1.close();
    	addWindow2.close();
    	updateWindow2.close();
    	addWindow3.close();
    	updateWindow3.close();

     });
    
    return panel;


})();