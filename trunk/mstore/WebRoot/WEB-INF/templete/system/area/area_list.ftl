
var newComponent = (function(){
	
	 var provinceStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"province?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'provinceId'},
           {name: 'provinceName'}
        ],
        sortInfo: {
            field: 'provinceId', direction: 'ASC'
        }
    });
    
    provinceStore.on("beforeload",function(a,b){
    	pId = -1;
    	cityStore.removeAll();
    });
    
    var cityStore = new Ext.data.JsonStore({
    	url:"city?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'cityId'},
           {name: 'cityName'},
           {name: 'provinceId'}
        ],
        sortInfo: {
            field: 'provinceId', direction: 'ASC'
        }
    });
   
      
    var provinceAddWindow = new Ext.Window({
    	title: '省添加',
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
			id:"provinceAddForm",
			defaultType: 'textfield',
			items:[
				{
					x: 15,
					y: 5,
					xtype:'label',
					text: '省名称:'
				},{
					x: 75,
					y: 0,
					name: 'provinceName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("provinceAddForm").getForm().doAction('submit',{
					url:'province?add&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						provinceAddWindow.hide();
						provinceStore.reload();
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
				provinceAddWindow.hide();
			}
		}]
    });
    
     var provinceUpdateWindow = new Ext.Window({
    	title: '省修改',
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
			id:"provinceUpdateForm",
			defaultType: 'textfield',
			items:[
				{
					name: 'provinceId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '省名称:'
				},{
					x: 75,
					y: 0,
					name: 'provinceName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("provinceUpdateForm").getForm().doAction('submit',{
					url:'province?update&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						provinceUpdateWindow.hide();
						provinceStore.reload();
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
				provinceUpdateWindow.hide();
			}
		}]
    });
    
    var pId = -1;
    var cId = -1;
   
   	var provinceGrid = new Ext.grid.GridPanel({
  	 	width: 300,
		height: 600,
   		store: provinceStore,
   		border:true,
   		columns:[
   			{id:'provinceId',hidden:true,sortable: true, dataIndex: 'provinceId'},
   			{header: "店名",width:270, sortable: true, dataIndex: 'provinceName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'provinceId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	provinceAddWindow.show();
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
               var record = provinceStore.getAt(pId);
               Ext.getCmp("provinceUpdateForm").getForm().setValues(record.data);
               provinceUpdateWindow.show();
               
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
               		var provinceId = provinceStore.getAt(pId).data.provinceId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"province?delete&type=json",
               				params:{
               					provinceId:provinceId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						provinceStore.reload();
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
               provinceStore.reload();
             }
        }]
   	
   	});
   	
   	provinceGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
    	cityStore.load({params:{provinceId:r.data.provinceId}});
    });
    
    
    
    
    /***************************************************************************************/
    var cityAddWindow = new Ext.Window({
    	title: '市添加',
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
			id:"cityAddForm",
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '市名称:'
			},{
				x: 75,
				y: 0,
				name: 'cityName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var record = provinceStore.getAt(pId);
				
				Ext.getCmp("cityAddForm").getForm().doAction('submit',{
					url:'city?add&type=json',
                    method:'post',
                    params:{
                    	provinceId:record.data.provinceId
                    },
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						cityAddWindow.hide();
						cityStore.reload();
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
				cityAddWindow.hide();
			}
		}]
    });
    
     var cityUpdateWindow = new Ext.Window({
    	title: '省修改',
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
			id:"cityUpdateForm",
			defaultType: 'textfield',
			items:[{
					name: 'provinceId',
					xtype:"hidden",
					allowBlank:false
				},{
					name: 'cityId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '市名称:'
				},{
					x: 75,
					y: 0,
					name: 'cityName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("cityUpdateForm").getForm().doAction('submit',{
					url:'city?update&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						cityUpdateWindow.hide();
						cityStore.reload();
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
				cityUpdateWindow.hide();
			}
		}]
    });
   	
   	var cityGrid = new Ext.grid.GridPanel({
   		width: 300,
		height: 600,
   		store: cityStore,
   		border:true,
   		columns:[
   			{id:'cityId',hidden:true,sortable: true, dataIndex: 'cityId'},
   			{header: "城市名称",width:270,sortable: true, dataIndex: 'cityName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'cityId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){

				if(pId == undefined || pId == -1){
					return;
				}
             	Ext.getCmp("cityAddForm").getForm().reset()
              	cityAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
                var record = cityStore.getAt(cId);
                Ext.getCmp("cityUpdateForm").getForm().setValues(record.data);
                cityUpdateWindow.show();
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
               		var cityId = cityStore.getAt(cId).data.cityId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"city?delete&type=json",
               				params:{
               					cityId:cityId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						cityStore.reload();
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
             	if(pId == undefined || pId == -1){
             		cityStore.removeAll();
             		return;
             	}
             	var record = provinceStore.getAt(pId);
              	cityStore.load({params:{provinceId:record.data.provinceId}});
             }
        }]
   	});
   	
   	cityGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
   	
   	cityStore.on("beforeload",function(a,b){
    	cId = -1;
    });
   	/**********************************************************************/
   	var showPanel = new Ext.Panel({
   		title:"区域管理",
    	layout: 'hbox',
    	width:600,
    	height:600,
    	items:[provinceGrid,cityGrid]
    });
   	
   	
   	 var panel = new Ext.Panel({
    	id:"${code}",
		title:"${name}",
    	baseCls:'x-plain',
    	closable:true,
    	layout: {
             type:'vbox',
             padding:'50 0 0 0',
             align:'center'
        },
        items:showPanel
    });
    
     panel.on('beforeclose',function(p,o){
    	provinceAddWindow.close();
    	provinceUpdateWindow.close();
    	cityAddWindow.close();
    	cityUpdateWindow.close();
     });
    
    return panel;

})();