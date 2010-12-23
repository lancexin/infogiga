
var newComponent = (function(){

    var cId = -1;
    var fId = -1;
    var aId = -1;
	

    var phonearrayStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"phonearray?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'phonearrayId'},
           {name: 'phonearrayName'}
        ],
        sortInfo: {
            field: 'phonearrayId', direction: 'ASC'
        }
    });
    
    var platformStore = new Ext.data.JsonStore({
    	autoLoad:true,
    	url:"platform?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'platformId'},
           {name: 'platformName'}
        ],
        sortInfo: {
            field: 'platformId', direction: 'ASC'
        }
    });
   
   
    var softmenuStore = new Ext.data.JsonStore({
    	autoLoad:true,
    	url:"softmenu?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'softmenuId'},
           {name: 'softmenuName'}
        ],
        sortInfo: {
            field: 'softmenuId', direction: 'ASC'
        }
    });
    
    
      
   
    
    
    
   
   	
    
    
    
    /***************************************************************************************/ 
    
     var phonearrayAddWindow = new Ext.Window({
    	title: '手机分类添加',
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
			id:"phonearrayAddForm",
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
					name: 'phonearrayName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("phonearrayAddForm").getForm().doAction('submit',{
					url:'phonearray?add&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						phonearrayAddWindow.hide();
						phonearrayStore.reload();
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
				phonearrayAddWindow.hide();
			}
		}]
    });
    
     var phonearrayUpdateWindow = new Ext.Window({
    	title: '分类修改',
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
			id:"phonearrayUpdateForm",
			defaultType: 'textfield',
			items:[
				{
					name: 'phonearrayId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '分类名称:'
				},{
					x: 75,
					y: 0,
					name: 'phonearrayName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("phonearrayUpdateForm").getForm().doAction('submit',{
					url:'phonearray?update&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						phonearrayUpdateWindow.hide();
						phonearrayStore.reload();
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
				phonearrayUpdateWindow.hide();
			}
		}]
    });
    
   
   	var phonearrayGrid = new Ext.grid.GridPanel({
   		title:"手机分类",
   		style:"padding:5px;",
  	 	width: 230,
		height: 500,
   		store: phonearrayStore,
   		border:true,
   		columns:[
   			{id:'phonearrayId',hidden:true,sortable: true, dataIndex: 'phonearrayId'},
   			{header: "分类名称",width:200, sortable: true, dataIndex: 'phonearrayName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'phonearrayId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	phonearrayAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
               if(aId == undefined || aId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
               }
               //alert(aId);
               var record = phonearrayStore.getAt(aId);
               Ext.getCmp("phonearrayUpdateForm").getForm().setValues(record.data);
               phonearrayUpdateWindow.show();
               
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(aId == undefined || aId ==  -1){
               		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
	            }
               Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var phonearrayId = phonearrayStore.getAt(aId).data.phonearrayId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"phonearray?delete&type=json",
               				params:{
               					phonearrayId:phonearrayId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						phonearrayStore.reload();
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
               phonearrayStore.reload();
             }
        }]
   	
   	});
   	
   	phonearrayGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		aId = rowIdx;
    });
    
    phonearrayStore.on("beforeload",function(a,b){
    	aId = -1;
    });
    
    
    
    
    
    
    
    
    /***************************************************************************************/
    var platformAddWindow = new Ext.Window({
    	title: '手机平台添加',
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
			id:"platformAddForm",
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '平台名称:'
			},{
				x: 75,
				y: 0,
				name: 'platformName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				
				Ext.getCmp("platformAddForm").getForm().doAction('submit',{
					url:'platform?add&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						platformAddWindow.hide();
						platformStore.reload();
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
				platformAddWindow.hide();
			}
		}]
    });
    
     var platformUpdateWindow = new Ext.Window({
    	title: '手机平台修改',
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
			id:"platformUpdateForm",
			defaultType: 'textfield',
			items:[{
					name: 'platformId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '手机平台:'
				},{
					x: 75,
					y: 0,
					name: 'platformName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				var record = platformStore.getAt(cId);
				
				Ext.getCmp("platformUpdateForm").getForm().doAction('submit',{
					url:'platform?update&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						platformUpdateWindow.hide();
						platformStore.reload();
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
				platformUpdateWindow.hide();
			}
		}]
    });
   	
   	var platformGrid = new Ext.grid.GridPanel({
   		title:"手机平台",
   		style:"padding:5px;",
   		width: 230,
		height: 500,
   		store: platformStore,
   		border:true,
   		columns:[
   			{id:'platformId',hidden:true,sortable: true, dataIndex: 'platformId'},
   			{header: "平台名称",width:200,sortable: true, dataIndex: 'platformName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'platformId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	Ext.getCmp("platformAddForm").getForm().reset();
              	platformAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
                var record = platformStore.getAt(cId);
                Ext.getCmp("platformUpdateForm").getForm().setValues(record.data);
                platformUpdateWindow.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
              	 Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var platformId = platformStore.getAt(cId).data.platformId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"platform?delete&type=json",
               				params:{
               					platformId:platformId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						platformStore.reload();
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

              	platformStore.reload();
             }
        }]
   	});
   	
   	platformGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
   	
   	platformStore.on("beforeload",function(a,b){
    	cId = -1;
    });
   	/**********************************************************************/
   	var softmenuAddWindow = new Ext.Window({
    	title: '菜单添加',
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
			id:"softmenuAddForm",
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '菜单名称:'
			},{
				x: 75,
				y: 0,
				name: 'softmenuName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				
				Ext.getCmp("softmenuAddForm").getForm().doAction('submit',{
					url:'softmenu?add&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						softmenuAddWindow.hide();
						softmenuStore.reload();
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
				softmenuAddWindow.hide();
			}
		}]
    });
    
     var softmenuUpdateWindow = new Ext.Window({
    	title: '菜单修改',
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
			id:"softmenuUpdateForm",
			defaultType: 'textfield',
			items:[{
					name: 'softmenuId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '菜单名称:'
				},{
					x: 75,
					y: 0,
					name: 'softmenuName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("softmenuUpdateForm").getForm().doAction('submit',{
					url:'softmenu?update&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						softmenuUpdateWindow.hide();
						softmenuStore.reload();
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
				softmenuUpdateWindow.hide();
			}
		}]
    });
   	
   	var softmenuGrid = new Ext.grid.GridPanel({
   		title:"软件菜单",
   		style:"padding:5px;",
   		width: 230,
		height: 500,
   		store: softmenuStore,
   		border:true,
   		columns:[
   			{id:'softmenuId',hidden:true,sortable: true, dataIndex: 'softmenuId'},
   			{header: "菜单名称",width:200,sortable: true, dataIndex: 'softmenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'softmenuId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
             	Ext.getCmp("softmenuAddForm").getForm().reset()
              	softmenuAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(fId == undefined || fId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
                var record = softmenuStore.getAt(fId);
                Ext.getCmp("softmenuUpdateForm").getForm().setValues(record.data);
                softmenuUpdateWindow.show();
             }
        },{
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
              	if(fId == undefined || fId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
              	 Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
               		var softmenuId = softmenuStore.getAt(fId).data.softmenuId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"softmenu?delete&type=json",
               				params:{
               					softmenuId:softmenuId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						softmenuStore.reload();
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
              	softmenuStore.reload();
             }
        }]
   	});
   	
   	softmenuGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		fId = rowIdx;
    });
   	
   	softmenuStore.on("beforeload",function(a,b){
    	fId = -1;
    });
    /******************************************************************************/
   	
   	var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:920,
    	height:600,
    	border:false,
    	items:[platformGrid,softmenuGrid,phonearrayGrid]
    });
   	
   	
   	 var panel = new Ext.Panel({
   	 	id:"${code}",
		title:"${name}",
    	baseCls:'x-plain',
    	closable:true,
    	border:false,
    	layout: {
             type:'vbox'
        },
        items:showPanel
    });
    
     panel.on('beforeclose',function(p,o){

    	phonearrayAddWindow.close();
    	phonearrayUpdateWindow.close();
    	
    	platformAddWindow.close();
    	platformUpdateWindow.close();
    	
    	softmenuAddWindow.close();
    	softmenuUpdateWindow.close();
    	
     });
    
    return panel;

})();