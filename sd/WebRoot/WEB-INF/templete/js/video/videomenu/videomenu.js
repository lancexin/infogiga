var newComponent = (function(){
	var store1 = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url:"json?videomenuList&type=json",
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'videomenuId'},
           {name: 'videomenuName'}
        ],
        sortInfo: {
            field: 'videomenuId', direction: 'ASC'
        }
    });
    
    var addWindow1 = new Ext.Window({
    	title: '添加菜单',
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
			id:"videomenuAddForm",
			defaultType: 'textfield',
			items:[
				{
					x: 15,
					y: 5,
					xtype:'label',
					text: '菜单名称:'
				},{
					x: 75,
					y: 0,
					name: 'videomenuName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '添加',
			handler:function(){
				Ext.getCmp("videomenuAddForm").getForm().doAction('submit',{
					url:'add?videomenu&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
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
    	title: '厂商修改',
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
			id:"videomenuUpdateForm",
			defaultType: 'textfield',
			items:[
				{
					name: 'videomenuId',
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
					name: 'videomenuName',
					allowBlank:false
				}
			]
		}),
		buttons: [{
			text: '修改',
			handler:function(){
				Ext.getCmp("videomenuUpdateForm").getForm().doAction('submit',{
					url:'update?videomenu&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('提示',action.result.msg);
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
   		title:"视频菜单",
   		style:"padding:5px;",
  	 	width: 300,
		height: 600,
   		store: store1,
   		border:true,
   		columns:[
   			{id:'videomenuId',hidden:true,sortable: true, dataIndex: 'videomenuId'},
   			{header: "菜单名称",width:270, sortable: true, dataIndex: 'videomenuName'}
   		],
   		stripeRows: true,
        autoExpandColumn: 'videomenuId',
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
               Ext.getCmp("videomenuUpdateForm").getForm().setValues(record.data);
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
               		var videomenuId = store1.getAt(pId).data.videomenuId;
               		if(bool == 'yes'){
               			Ext.Ajax.request({
               				url:"delete?phonebrand&type=json",
               				params:{
               					videomenuId:videomenuId
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
   	
   	grid1.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
   		pId = rowIdx;
    });
    
    store1.on("beforeload",function(a,b){
    	pId = -1;
    });
    

	var showPanel = new Ext.Panel({
    	layout: 'hbox',
    	width:900,
    	height:600,
    	border:false,
    	items:[phonebrandGrid,platformGrid,softmenuGrid]
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
     });
    
    return panel;


});