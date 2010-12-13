Ext.namespace("Ext.ux");

Ext.ux.CommonTabPanel = Ext.extend(Ext.Panel,{
   	closable:true,
   	layout: 'border',
   	gridStore:null,
   	addForm:null,
   	updateForm:null,
   	addWindow:null,
   	updateWindow:null,
   	selectedRowId:-1,
   	storeUrl:null,
   	addUrl:null,
   	updateUrl:null,
   	deleteUrl:null,
   	storeField:null,
   	updateWindowConfig:null,
   	addWindowConfig:null,
   	storeSortInfo: null,
    deleteId:null,
    gridColumns:null,
    storeAutoLoad:true,
    gridAutoExpandColumn:null,
   	comboStore:null,
   	expander:null,
    getStore:function(){
    	var _this = this;
    	var url = this.storeUrl;
    	var fields = this.storeField;
    	var sortInfo = this.storeSortInfo;
    
    	var store = new Ext.data.JsonStore({
	    	url:url,
	    	root:"data",
	    	totalProperty:"totalCount",//返回的总页数
	        fields: fields,
	        sortInfo: sortInfo
	    });
	    store.on("beforeload",function(a,b){
	    	_this.beforeStoreLoad(a,b);
	    });
	   
	    return store;
    },
    beforeStoreLoad:function(a,b){
    	this.selectedRowId = -1;
    },
    getBbar:function(store){
    	var bbar = new Ext.PagingToolbar({
	        pageSize: 20,
	        store: store,
	        displayInfo: true,
	        displayMsg: '第{0}到{1}条数据 共{2}条',
	        emptyMsg: "没有记录"
	    });
	    return bbar;
    },
    getTbar:function(){
    	var tbar = [];
    	var _this = this;
    	tbar.push({
        	 text: '添加',
        	 iconCls:'add',
             handler : function(){
              	_this.onClickAddButton();
             }
        });
        
        tbar.push({
        	 text: '编辑',
        	 iconCls:'edit',
             handler : function(){
               _this.onClickEditButton();
             }
        });
        
        tbar.push({
        	 text: '删除',
        	 iconCls:'remove',
             handler : function(){
               _this.onClickDeleteButton();
             }
        });
        
        tbar.push({
        	 text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
               _this.onClickRefreshButton();
             }
        });
        
        return tbar;
    	
    },
    getGridPanel:function(){
    	 var _this = this;
    	
    	 var store = this.gridStore;
    	 var tbar = this.getTbar();
    	 var bbar = this.getBbar(store);
    	 var columns = this.gridColumns;
    	 var autoExpandColumn = this.gridAutoExpandColumn;
    	 
        
    	 var config = {
    	 	store: store,
	       	region: 'center',
	       	border:false,
    	 	stripeRows: true,
    	 	autoExpandColumn:autoExpandColumn,
    	 	loadMask: true,
    	 	bbar:bbar,
    	 	tbar:tbar,
    	 	viewConfig : {
		       forceFit : true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
		    }
    	 };
    	 
    	 if(this.expander){
    	 	var e = [this.expander];
    	 	columns = e.concat(this.gridColumns);
    	 	
    	 	Ext.apply(config,{plugins:_this.expander});
    	 }
    	 var cm = new Ext.grid.ColumnModel({
            columns: columns
         });
    	 Ext.apply(config,{cm:cm});
    	 var dataGrid = new Ext.grid.GridPanel(config);
    	 dataGrid.getSelectionModel().on('rowselect', function(sm, rowIdx, r){
	    	_this.onRowSelect(sm, rowIdx, r);
	     });
	     
	    
	     return dataGrid;
    },
    beforeColse:function(p,o){
    	if(this.addWindow){
    		this.addWindow.close();
    	}
    	
    	if(this.updateWindow){
    		this.updateWindow.close();
    	}
    },
    initComboStore:function(){
    	var comboStore = this.comboStore;
    	if(comboStore){
    		Ext.StoreMgr.addAll(comboStore); 
    	}
    },
    getUpdateWindow:function(updateForm,windowConfig){
    	var _this = this;
    	var updateWindow = new Ext.Window(windowConfig);
    	updateWindow.add(updateForm);
    	updateWindow.addButton({
    		text: '修改',
    		handler:function(){
    			_this.onUpdateSubmit();
    		}
    	});
    	
    	updateWindow.addButton({
    		text: '取消',
			handler:function(){
				updateWindow.hide();
			}
    	});
    	
    	updateWindow.on("beforeclose",function(p){
    		_this.onUpdateWindowClose(p);
    	});
    	
    	return updateWindow;
    },
    getAddWindow:function(addForm,windowConfig){
    	var _this = this;
    	var addWindow = new Ext.Window(windowConfig);
    	addWindow.add(addForm);
    	addWindow.addButton({
    		text: '添加',
    		handler:function(){
    			_this.onAddSubmit();
    		}
    	});
    	
    	addWindow.addButton({
    		text: '取消',
			handler:function(){
				addWindow.hide();
			}
    	});
    	
    	addWindow.on("beforeclose",function(p){
    		_this.onAddWindowClose(p);
    	});
    	
    //	addWindow.
    	return addWindow;
    },
    getAddForm:function(){
    	return null;
    },
    getUpdateForm:function(){
    	return null;
    },
    onClickAddButton:function(){
    	if(this.addWindow == null){
    		this.addWindow = this.getAddWindow();
    	}
    	this.addForm.getForm().reset();
    	this.clickAddButton(this.addForm.getForm());
    	this.addWindow.show();
    },
    clickAddButton:function(form){
    	
    	
    },
    onClickEditButton:function(){
    	if(this.selectedRowId == -1){
    		return;
    	}
    	if(this.updateWindow == null){
    		this.updateWindow = this.getUpdateWindow();
    	}
    	
    	var record = this.gridStore.getAt(this.selectedRowId);
        this.updateForm.getForm().setValues(record.data);
    	this.updateWindow.show();
    	this.clickEditButton(this.addForm.getForm());
    },
    clickEditButton:function(form){
    	
    },
    onClickDeleteButton:function(){
    	var _this = this;
    	if(this.selectedRowId == -1){
    		return;
    	}
    	eval("var id = this.gridStore.getAt(this.selectedRowId).data."+this.deleteId+";");
		var deleteUrl = this.deleteUrl+"&"+this.deleteId+"="+id;
    	Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
         		if(bool == 'yes'){
         			Ext.Ajax.request({
         				url:deleteUrl,
         				success:function(response, options){
         					eval("action = "+response.responseText);
         					Ext.Msg.alert('提示',action.msg);
         					if(action.success){
         						_this.gridStore.reload();
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
    },
    onClickRefreshButton:function(){
    	this.gridStore.reload();
    },
    onRowSelect:function(sm, rowIdx, r){
    	this.selectedRowId = rowIdx;
    },
    onAddSubmit:function(){
    	var _this = this;
    	this.addForm.getForm().doAction('submit',{
			url:_this.addUrl,
            method:'post',
			success:function(form,action){
				Ext.Msg.alert('提示',action.result.msg);
				_this.addWindow.hide();
				_this.gridStore.reload({params:{start:0,limit:20}});
			},
			failure:function(form,action){
				if(action.result){
					Ext.Msg.alert('错误',action.result.msg);
				}   
            }
		});
    },
    onUpdateSubmit:function(){
    	var _this = this;
    	this.updateForm.getForm().doAction('submit',{
			url:_this.updateUrl,
            method:'post',
			success:function(form,action){
				Ext.Msg.alert('提示',action.result.msg);
				_this.updateWindow.hide();
				_this.gridStore.reload();
			},
			failure:function(form,action){
				if(action.result){
					Ext.Msg.alert('错误',action.result.msg);
				}   
            }
		});
    },
    onAddWindowClose:function(p){
    	
    },
    onUpdateWindowClose:function(p){
    	
    },
    initComponent:function(){
    	this.initComboStore();
    	Ext.ux.CommonTabPanel.superclass.initComponent.call(this);
    	var _this = this;
    	this.on('beforeclose',function(p,o){
	    	_this.beforeColse(p,o);
	    });
    	this.gridStore = this.getStore();
    	if(this.storeAutoLoad){
    		this.gridStore.load({params:{start:0,limit:20}});
    	}
    	
    	this.add(this.getGridPanel());
    	
    	this.addForm = this.getAddForm();
    	
    	this.updateForm = this.getUpdateForm();
    	
    	this.addWindow = this.getAddWindow(this.addForm,this.addWindowConfig);
    	
    	this.updateWindow = this.getUpdateWindow(this.updateForm,this.updateWindowConfig);
    	
    }
});