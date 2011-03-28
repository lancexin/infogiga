
var newComponent = (function(){
	    
    var pId = -1;
    var cId = -1;
    
    
	var readtypeStore = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"readtype?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'readtypeId'},
           {name: 'typeName'},
           {name: 'typeShortName'}
        ],
        sortInfo: {
            field: 'readtypeId', direction: 'ASC'
        }
    });
    
    readtypeStore.on("beforeload",function(a,b){
    	pId = -1;
    	readerStore.removeAll();
    });
    
    var readerStore = new Ext.data.JsonStore({
    	url:"reader?type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'readerId'},
           {name: 'readerName'},
           {name: 'readtypeId'},
           {name: 'typeShortName'},
           {name: 'readerShortName'}
        ],
        sortInfo: {
            field: 'readerId', direction: 'ASC'
        }
    });
    
   
      
    var readtypeAddWindow = new Ext.Window({
    	title: '类型添加',
		width: 250,
		height:130,
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
			id:"readtypeAddForm",
			defaultType: 'textfield',
			items:[
				{
					x: 15,
					y: 5,
					xtype:'label',
					text: '类别名称:'
				},{
					x: 75,
					y: 0,
					name: 'typeName',
					allowBlank:false
				},{
					x: 15,
					y: 35,
					xtype:'label',
					text: '名称缩写:'
				},{
					x: 75,
					y: 30,
					name: 'typeShortName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("readtypeAddForm").getForm().doAction('submit',{
					url:'readtype?add&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						readtypeAddWindow.hide();
						readtypeStore.reload();
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
				readtypeAddWindow.hide();
			}
		}]
    });
    
     var readtypeUpdateWindow = new Ext.Window({
    	title: '省修改',
		width: 250,
		height:130,
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
			id:"readtypeUpdateForm",
			defaultType: 'textfield',
			items:[
				{
					name: 'readtypeId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '类别名称:'
				},{
					x: 75,
					y: 0,
					name: 'typeName',
					allowBlank:false
				},{
					x: 15,
					y: 35,
					xtype:'label',
					text: '省名称:'
				},{
					x: 75,
					y: 30,
					name: 'typeShortName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("readtypeUpdateForm").getForm().doAction('submit',{
					url:'readtype?update&type=json',
                       method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						readtypeUpdateWindow.hide();
						readtypeStore.reload();
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
				readtypeUpdateWindow.hide();
			}
		}]
    });

   
   	var readtypeGrid = new Ext.grid.GridPanel({
  	 	width: 250,
		height: 600,
   		store: readtypeStore,
   		border:true,
   		columns:[
   			{id:'readtypeId',hidden:true,sortable: true, dataIndex: 'readtypeId'},
   			{header: "缩写", sortable: true, dataIndex: 'typeShortName'},
   			{header: "类型名称", sortable: true, dataIndex: 'typeName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'readtypeId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	readtypeAddWindow.show();
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
               var record = readtypeStore.getAt(pId);
               Ext.getCmp("readtypeUpdateForm").getForm().setValues(record.data);
               readtypeUpdateWindow.show();
               
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
               		var readtypeId = readtypeStore.getAt(pId).data.readtypeId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"readtype?delete&type=json",
               				params:{
               					readtypeId:readtypeId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						readtypeStore.reload();
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
               readtypeStore.reload();
             }
        }]
   	
   	});
   	
   	readtypeGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
    	readerStore.load({params:{readtypeId:r.data.readtypeId}});
    });
    
    
    
    
    /***************************************************************************************/
    
    
    
    var readerAddWindow = new Ext.Window({
    	title: '读物添加',
		width: 250,
		height:130,
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
			id:"readerAddForm",
			defaultType: 'textfield',
			items:[{
				x: 15,
				y: 5,
				xtype:'label',
				text: '读物名称:'
			},{
				x: 75,
				y: 0,
				name: 'readerName',
				allowBlank:false
			},{
				x: 15,
				y: 35,
				xtype:'label',
				text: '名称缩写:'
			},{
				x: 75,
				y: 30,
				name: 'readerShortName',
				allowBlank:false
			}]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				var record = readtypeStore.getAt(pId);
				
				Ext.getCmp("readerAddForm").getForm().doAction('submit',{
					url:'reader?add&type=json',
                    method:'post',
                    params:{
                    	readtypeId:record.data.readtypeId
                    },
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						readerAddWindow.hide();
						readerStore.reload();
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
				readerAddWindow.hide();
			}
		}]
    });
    
     var readerUpdateWindow = new Ext.Window({
    	title: '读物修改',
		width: 250,
		height:130,
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
			id:"readerUpdateForm",
			defaultType: 'textfield',
			items:[{
					name: 'readtypeId',
					xtype:"hidden",
					allowBlank:false
				},{
					name: 'readerId',
					xtype:"hidden",
					allowBlank:false
				},{
					x: 15,
					y: 5,
					xtype:'label',
					text: '读物名称:'
				},{
					x: 75,
					y: 0,
					name: 'readerName',
					allowBlank:false
				},{
					x: 15,
					y: 35,
					xtype:'label',
					text: '名称缩写:'
				},{
					x: 75,
					y: 30,
					name: 'readerShortName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("readerUpdateForm").getForm().doAction('submit',{
					url:'reader?update&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
						readerUpdateWindow.hide();
						readerStore.reload();
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
				readerUpdateWindow.hide();
			}
		}]
    });
   	
   	var readerGrid = new Ext.grid.GridPanel({
   		width: 250,
		height: 600,
   		store: readerStore,
   		border:true,
   		columns:[
   			{id:'readerId',hidden:true,sortable: true, dataIndex: 'readerId'},
   			{header: "读物名称",sortable: true, dataIndex: 'readerName'},
   			{header: "名称缩写",sortable: true, dataIndex: 'readerShortName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'readerId',
        loadMask: true,
        tbar:[{
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){

				if(pId == undefined || pId == -1){
					return;
				}
             	Ext.getCmp("readerAddForm").getForm().reset()
              	readerAddWindow.show();
             }
        },{
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
                if(cId == undefined || cId == -1){
              		Ext.Msg.alert('提示',"请先选择您要操作的列");
               		return;
              	}
                var record = readerStore.getAt(cId);
                Ext.getCmp("readerUpdateForm").getForm().setValues(record.data);
                readerUpdateWindow.show();
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
               		var readerId = readerStore.getAt(cId).data.readerId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"reader?delete&type=json",
               				params:{
               					readerId:readerId
               				},
               				success:function(response, options){
               					eval("action = "+response.responseText);
               					Ext.Msg.alert('提示',action.msg);
               					if(action.success){
               						readerStore.reload();
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
             		readerStore.removeAll();
             		return;
             	}
             	var record = readtypeStore.getAt(pId);
              	readerStore.load({params:{readtypeId:record.data.readtypeId}});
             }
        }]
   	});
   	
   	readerGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		cId = rowIdx;
    });
   	
   	readerStore.on("beforeload",function(a,b){
    	cId = -1;
    });
   	/**********************************************************************/

   	var showPanel = new Ext.Panel({
   		title:"基本信息管理",
    	layout: 'hbox',
    	width:500,
    	height:600,
    	items:[readtypeGrid,readerGrid]
    });
    
   	
   	
   	 var panel = new Ext.Panel({
    	id:"${code}",
		title:"${name}",
    	baseCls:'x-plain',
    	closable:true,
    	layout: {
             type:'hbox',
             padding:'10 10 10 10'
        },
        items:[showPanel]
    });
    
     panel.on('beforeclose',function(p,o){
    	readtypeAddWindow.close();
    	readtypeUpdateWindow.close();
    	readerAddWindow.close();
    	readerUpdateWindow.close();
     });
    
    return panel;

})();