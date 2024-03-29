Ext.onReady(function(){
  
        Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
        
        var centerTab = new Ext.TabPanel({
        	region: 'center',
	    	animCollapse : true,
	    	animScroll : true,   
	    	activeTab: 0,
	    	items: [{
                contentEl: 'center1',
                title: 'Welcome',
                autoScroll: true
            }]
        });
        
        var viewport = new Ext.Viewport({
			layout: 'border',
			items:[{//头部
				region: 'north',
				contentEl: 'north',
				height: 56,
				margins: '0 0 0 0',
				border : false
		    },{//菜单
				region: 'west',
				id: 'west-panel', // see Ext.getCmp() below
				collapsible: true,
				title: '管理菜单',
				xtype: 'treepanel',
				width: 200,
			    minSize: 175,
			    maxSize: 400,
			    collapsible: true,
			    margins: '0 0 0 1',
				autoScroll: true,
				split: true,
				dataUrl:"menu?type=json",
				root: {
					nodeType: 'async',
					draggable: false,
					id: 'source'
				},
				rootVisible: false,
				listeners: {
				    click: function(n) {
				    	
						if(n.leaf){//如果是子菜单这则做如下操作
							Ext.Ajax.request({
							    url: n.attributes.url,
							    params:{
							    	name:n.text,
							    	code:n.attributes.code
							    },
							    success: function(xhr) {
							    	Ext.WindowMgr.hideAll();
							        eval(xhr.responseText);
							        centerTab.add(newComponent);
							        centerTab.setActiveTab(newComponent);
							    },
							    failure: function() {
							        Ext.Msg.alert("Grid create failed", "Server communication failure");
							    }
							});	
													
						}
			    	}
				}
		    },centerTab,{//底部
				region: 'south',
				split: true,
				height: 25,
				contentEl: 'south',
				margins: '0 0 0 0'
		    }]
		 });

});