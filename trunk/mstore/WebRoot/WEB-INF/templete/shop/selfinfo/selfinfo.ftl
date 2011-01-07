var newComponent = (function(){
	
	 var store = new Ext.data.JsonStore({
	 	autoLoad:true,
    	url :'mg?type=json',
    	root:"data",
    	totalProperty:"totalCount",//返回的总页数
        fields: [
           {name: 'userId'},
           {name: 'nickName'},
           {name: 'userName'}
        ],
        sortInfo: {
            field: 'userId', direction: 'ASC'
        }
    });
	

    var formPanel = new Ext.form.FormPanel({
    	title:"个人信息",
    	width: 250,
		height:190,
		layout: 'fit',
		layout:'absolute',
		style:' font-size: 11px;',
		defaultType: 'textfield',
		items:[{
			xtype:'hidden',
			name:'userId',
			allowBlank:false
		},{
			x: 15,
			y: 10,
			xtype:'label',
			text: '姓名:'
		},{
			x: 75,
			y: 5,
			name: 'nickName',
			allowBlank:false
		},{
			x: 15,
			y: 40,
			xtype:'label',
			text: '账号:'
		},{
			x: 75,
			y: 35,
			name: 'userName',
			allowBlank:false
		},{
			x: 15,
			y: 70,
			xtype:'label',
			text: '旧密码:'
		},{
			x: 75,
			y: 65,
			name: 'passWord',
			inputType:"password",
			allowBlank:false
		},{
			x: 15,
			y: 100,
			xtype:'label',
			text: '新密码:'
		},{
			x: 75,
			y: 95,
			name: 'newPassWord',
			inputType:"password",
			allowBlank:false
		},{
			x: 15,
			y: 125,
			width:60,
			text:"修改",
			xtype:'button',
			handler:function(){
				formPanel.getForm().doAction('submit',{
					url:'manager?update&type=json',
                    method:'post',
					success:function(form,action){
						Ext.Msg.alert('成功',action.result.msg);
					},
					failure:function(form,action){
						if(action.result){
							Ext.Msg.alert('错误',action.result.msg);
						}   
                   }
				});
			}
		}]
		
    });
    
    store.on("load",function(s,records,options ){
    	formPanel.getForm().setValues(store.getAt(0).data); 
    });
    //store.load();
    //alert(store.getTotalCount());
   // formPanel.getForm().setValues(store.getAt(0).data);
    
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
        items:formPanel
    });
    
    return panel;


})();