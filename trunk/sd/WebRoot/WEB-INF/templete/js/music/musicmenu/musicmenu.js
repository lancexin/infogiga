var newComponent = (function(){

	 var musicmenuStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"json?musicmenuList&isLeaf=0&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'musicmenuId'},
           {name: 'musicmenuName'}
        ],
        sortInfo: {
            field: 'musicmenuId', direction: 'ASC'
        }
    });
    
    
   var musicmenuLeafStore = new Ext.data.JsonStore({
	 	autoLoad:false,
    	url:"json?musicmenuList&isLeaf=1&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'musicmenuId'},
           {name: 'musicmenuName'},
           {name: 'fathermusicmenuId'}
        ],
        sortInfo: {
            field: 'musicmenuId', direction: 'ASC'
        }
    });
    
   	var musicmenuAddWindow = new Ext.Window({
    	title: '音乐主菜单添加',
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
			id:"musicmenuAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				x: 75,
				y: 0,
				name: 'musicmenuName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("musicmenuAddForm").getForm().doAction('submit',{
					url:'add?musicmenu&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						musicmenuAddWindow.hide();
						musicmenuStore.reload();
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
				musicmenuAddWindow.hide();
			}
		}]
    });
    
   
    
    var musicmenuUpdateWindow = new Ext.Window({
    	title: '音乐主菜单修改',
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
			id:"musicmenuUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				name: 'musicmenuId',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 75,
				y: 0,
				name: 'musicmenuName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("musicmenuUpdateForm").getForm().doAction('submit',{
					url:'update?musicmenu&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						musicmenuUpdateWindow.hide();
						musicmenuStore.reload();
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
				musicmenuUpdateWindow.hide();
			}
		}]
    });
    
    var musicmenuGrid = new Ext.grid.GridPanel({
   		title:"音乐主菜单",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: musicmenuStore,
   		border:true,
   		columns:[
   			{id:'musicmenuId',hidden:true,sortable: true, dataIndex: 'musicmenuId'},
   			{header: "菜单名称",sortable: true, dataIndex: 'musicmenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'musicmenuId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	musicmenuAddWindow.show();
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
	             var record = musicmenuStore.getAt(pId);
	             Ext.getCmp("musicmenuUpdateForm").getForm().setValues(record.data);
                 musicmenuUpdateWindow.show();
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
               		var musicmenuId = musicmenuStore.getAt(pId).data.musicmenuId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?musicmenu&type=json",
               				params:{
               					musicmenuId:musicmenuId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						musicmenuStore.reload();
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
              	musicmenuStore.reload();
             }
        }]
   	});
   	
   	var pId = -1;
   	
   	musicmenuGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
   		musicmenuLeafStore.load({params:{fathermusicmenuId:r.data.musicmenuId}});
    });
    
    musicmenuGrid.on("beforeload",function(a,b){
    	pId = -1;
    });
    
    
    
    
    var musicmenuLeafAddWindow = new Ext.Window({
    	title: '添加音乐子菜单',
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
			id:"musicmenuLeafAddForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '手机型号:'
			},{
				x: 75,
				y: 0,
				name: 'musicmenuName'
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				if(pId == undefined || pId == -1){
					return;
				}
				var fathermusicmenuId = musicmenuStore.getAt(pId).data.musicmenuId;
			
				Ext.getCmp("musicmenuLeafAddForm").getForm().doAction('submit',{
					url:'add?leafmusicmenu&type=json',
                    method:'post',
                    params:{
                    	fathermusicmenuId:fathermusicmenuId
                    },
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						musicmenuLeafAddWindow.hide();
						musicmenuLeafStore.reload();
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
				musicmenuLeafAddWindow.hide();
			}
		}]
    });
    
    var musicmenuLeafUpdateWindow = new Ext.Window({
    	title: '修改音乐子菜单',
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
			id:"musicmenuLeafUpdateForm",
			defaultType: 'textfield',
			fileUpload: true,
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				name: 'musicmenuId',
				allowBlank:false,
				xtype:'hidden'
			},{
				x: 75,
				y: 0,
				name: 'musicmenuName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
			
				if(pId == undefined || pId == -1){
					return;
				}
			
				Ext.getCmp("musicmenuLeafUpdateForm").getForm().doAction('submit',{
					url:'update?musicmenu&type=json',
                    method:'post',
					success:function(form,action){
						//Ext.Msg.alert('提示',action.result.msg);
						musicmenuLeafUpdateWindow.hide();
						musicmenuLeafStore.reload();
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
				musicmenuLeafUpdateWindow.hide();
			}
		}]
    });
    
    var musicmenuLeafGrid = new Ext.grid.GridPanel({
   		title:"音乐子菜单列表",
   		style:"padding:5px;",
   		width: 300,
		height: 600,
   		store: musicmenuLeafStore,
   		border:true,
   		columns:[
   			{id:'musicmenuId',hidden:true,sortable: true, dataIndex: 'musicmenuId'},
   			{header: "菜单名称",sortable: true, dataIndex: 'musicmenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'musicmenuId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
            	 if(pId == undefined || pId ==  -1){
	              		return;
	             }
             	musicmenuLeafAddWindow.show();
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
	             var record = musicmenuLeafStore.getAt(cId);
	             Ext.getCmp("musicmenuLeafUpdateForm").getForm().setValues(record.data);
                 musicmenuLeafUpdateWindow.show();
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
               		var musicmenuId = musicmenuLeafStore.getAt(cId).data.musicmenuId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?musicmenu&type=json",
               				params:{
               					musicmenuId:musicmenuId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						musicmenuLeafStore.reload();
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
              	musicmenuLeafStore.reload();
             }
        }]
   	});
   	
   	var cId = -1;
   	
   	musicmenuLeafGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
    
    musicmenuLeafGrid.on("beforeload",function(a,b){
    	cId = -1;
    });
    
    
    var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:900,
    	height:600,
    	border:false,
    	items:[musicmenuGrid,musicmenuLeafGrid]
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
    	musicmenuAddWindow.close();
    	musicmenuUpdateWindow.close();
    	musicmenuLeafUpdateWindow.close();
    	musicmenuLeafAddWindow.close();
     });
    
    return panel;


})();