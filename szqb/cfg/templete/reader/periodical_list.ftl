var newComponent = new Ext.ux.CommonTabPanel({
	id:"${code}",
	title:"${name}",
	storeUrl:"periodical?type=json",
   	addUrl:"periodical?add&type=json",
   	updateUrl:"periodical?update&type=json",
   	deleteUrl:"periodical?delete&type=json",
   	gridAutoExpandColumn:'status',
   	updatePeriodicalId:null,
   	search:-1,
   	storeField:[
   		{name: 'periodicalId'},
        {name: 'shortName'},
        {name: 'number'},
        {name: 'readerId'},
        {name: 'readerName'},
        {name: 'readerShortName'},
        {name: 'downloadCount'},
        {name: 'indexPic'},
        {name: 'addTime'},
        {name: 'publishTime'},
        {name: 'attachmentUrl'},
        {name: 'tabloidPic'},
        {name: 'attachmentMd5'},
        {name: 'status'}
   	],
   	expander:new Ext.ux.grid.RowExpander({
        tpl : new Ext.Template(
        	'<img style="margin:5px;" src="{indexPic}"/>',
        	'<img style="margin:5px;width:120px;height:160px;" src="{tabloidPic}"/>',
        	'</br>'
        )
    }),
   	updateWindowConfig:{
   		title: '手机型号修改',
		width: 410,
		height:610,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	addWindowConfig:{
   		title: '手机型号添加',
		width: 410,
		height:610,
		layout: 'fit',
		plain:true,
		closable :true,
		collapsible : true ,
		bodyStyle:'padding:5px;',
		buttonAlign:'center',
		closeAction:"hide"
   	},
   	storeSortInfo: {
        field: 'periodicalId', direction: 'DESC'
    },
    deleteId:"periodicalId",
    gridColumns:[
         {id:'periodicalId',hidden:true,sortable: true, dataIndex: 'periodicalId'},
         {header: "读物名称", sortable: true, dataIndex: 'readerName'},
         {header: "期号", sortable: true, dataIndex: 'number'},
         {header: "缩写", sortable: true, dataIndex: 'shortName'},
         {header: "发布时间", sortable: true, dataIndex: 'publishTime'},
         {header: "添加时间", sortable: true, dataIndex: 'addTime'},
         {header: "状态",sortable: true, dataIndex: 'status',renderer:function(value, metaData, record, rowIndex, colIndex, store){
	         	if(value == 0){
	         		return "未完成 ";
	         	}else if(value == 1){
	         		return "图片转换中";
	         	}else if(value == 2){
	         		return "待审核";
	         	}else if(value == 3){
	         		return "已发布";
	         	}else if(value == 4){
	         		return "发布失败";
	         	}
	         } 
         }
    ],
    gridAutoExpandColumn:"periodicalId",
   	comboStore:[],
	getAddForm:function(){
		return null;
    },
    getUpdateForm:function(){
    	return null;
    },
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
	        sortInfo: sortInfo,
	        baseParams:{
	        	status:-1
	        }
	    });
	    store.on("beforeload",function(a,b){
	    	_this.beforeStoreLoad(a,b);
	    });
	   
	    return store;
    },
    getTbar:function(){
    	var tbar = new Ext.Toolbar();
    	tbar.x_buttons = {};
    	var _this = this;
        tbar.x_buttons.add =  tbar.addItem(new Ext.Button({
			 text: '发布',
        	 iconCls:'add',
             handler : function(){
             	var postUrl = "p?addPeriodical&name=刊物发布&code=B_2";
              	openNewFrame(centerTab,"刊物发布","B_2",postUrl);
             }
		}));
		
		tbar.x_buttons.update =  tbar.addItem(new Ext.Button({
			 text: '修改',
        	 iconCls:'edit',
             handler : function(){
             	_this.updatePeriodical();
             }
		}));
		
		
		tbar.x_buttons.del =  tbar.addItem(new Ext.Button({
			  text: '删除',
        	 iconCls:'remove',
             handler : function(){
             	Ext.MessageBox.confirm('确认', '您确定要删除吗?删除将永远无法恢复', function(btn) {
					if (btn == 'yes') {
						_this.deletePeriodical();
					}
				});
             }
		}));
		
        
		tbar.x_buttons.search =  tbar.addItem(new Ext.Button({
			text: '查看',
        	 iconCls:'search2',
             handler : function(){
             	_this.searchPeriodical();
             }
		}));
       

		tbar.x_buttons.review =  tbar.addItem(new Ext.Button({
			text: '审核',
        	iconCls:'review',
            handler : function(){
             	_this.reviewPeriodical();
            }
		}));
        

        
		tbar.x_buttons.refresh =  tbar.addItem(new Ext.Button({
			text: '刷新',
        	 iconCls:'refresh',
             handler : function(){
               _this.onClickRefreshButton();
               _this.search = -1;
               _this.updateToolbar();
               
             }
		}));
		
		tbar.addItem("-");
		
		tbar.addItem({
			xtype:'label',
			text:'状态:'
		});
		tbar.x_buttons.select = tbar.addItem(new Ext.form.ComboBox({
				name: 'status',
				mode: 'local',
				triggerAction: 'all',
				typeAhead: true,
				allowBlank:false,
				editable:false,
				store:new Ext.data.ArrayStore({
			        fields: [
			            'statusId',
			            'text'
			        ],
			        data: [[-1, '全部'],[0, '未完成'],[1, '转换中'],[2, '待审核'],[3, '已发布'],[4, '发布失败']]
			    }),
				valueField: 'statusId',
				displayField: 'text',
				hiddenValue:1,
				hiddenName:"status",
				listeners:{
         			'select': function(index,scrollIntoView){
         				var s = this.getValue();
         				_this.gridStore.setBaseParam("status",s) 
         				_this.gridStore.load({params:{start:0,limit:20}});
         			}
    			}
				
			}));

                
        return tbar;
    	
    },
    deletePeriodical:function(){
    	var _this = this;
    	var pId = this.gridStore.getAt(this.selectedRowId).get("periodicalId");
    	if(pId == -1){
    		return;
    	}
    	Ext.Ajax.request({
			url: "periodical?delete&type=json&periodicalId="+pId,
			success: function(xhr) {
				eval("action ="+xhr.responseText);
				Ext.Msg.alert("提示", action.msg);
				_this.gridStore.reload();
			},
			failure: function() {
				Ext.Msg.alert("Grid create failed", "Server communication failure");
			}
		});	
    },
    updatePeriodical:function(){
    	var pId = this.gridStore.getAt(this.selectedRowId).get("periodicalId");
    	if(pId == -1){
    		return;
    	}
    	var code = "${code}_"+pId;
    	var postUrl = "p?updatePeriodical&periodicalId="+pId;
    	openNewFrame(centerTab,'信息修改',code,postUrl);
    },
    reviewPeriodical:function(){
    	var pId = this.gridStore.getAt(this.selectedRowId).get("periodicalId");
    	if(pId == -1){
    		return;
    	}
    	var code = "${code}_"+pId;
    	var postUrl = "p?reviewPeriodical&periodicalId="+pId;
    	openNewFrame(centerTab,'读物审核',code,postUrl);
    },
    searchPeriodical:function(){
    	var pId = this.gridStore.getAt(this.selectedRowId).get("periodicalId");
    	if(pId == -1){
    		return;
    	}
    	var code = "${code}_"+pId;
    	var postUrl = "p?searchPeriodical&periodicalId="+pId;
    	openNewFrame(centerTab,'读物查看',code,postUrl);
    },
    clickAddButton:function(form){},
    clickEditButton:function(form){},
    updateToolbar:function(){
    	var tb = this.dataGrid.getTopToolbar();
    	if(this.search == -1){
    		tb.x_buttons.update.disable();
    		tb.x_buttons.del.disable();
    		tb.x_buttons.search.disable();
    		tb.x_buttons.review.disable();
    	}else if(this.search == 0){
    		tb.x_buttons.update.enable();
    		tb.x_buttons.del.enable();
    		tb.x_buttons.search.disable();
    		tb.x_buttons.review.disable();
    	}else if(this.search == 1){
    		tb.x_buttons.update.disable();
    		tb.x_buttons.del.disable();
    		tb.x_buttons.search.disable();
    		tb.x_buttons.review.disable();
    	}else if(this.search == 2){
    		tb.x_buttons.update.enable();
    		tb.x_buttons.del.enable();
    		tb.x_buttons.search.disable();
    		tb.x_buttons.review.enable();
    	}else if(this.search == 3){
    		tb.x_buttons.update.disable();
    		tb.x_buttons.del.disable();
    		tb.x_buttons.search.enable();
    		tb.x_buttons.review.disable();
    	}else if(this.search == 4){
    		tb.x_buttons.update.disable();
    		tb.x_buttons.del.enable();
    		tb.x_buttons.search.disable();
    		tb.x_buttons.review.disable();
    	}
    },
    onRowSelect:function(sm, rowIdx, r){
    	this.selectedRowId = rowIdx;
    	this.search = this.gridStore.getAt(rowIdx).get("status");
    	this.updateToolbar();
    },
    getAddWindow:function(addForm,windowConfig){
    	return null;
    },
    onClickAddButton:function(){
    	
    },
    beforeColse:function(){
    	
    },
    beforeShow:function(){
    	this.updateToolbar();
    },
    getUpdateWindow:function(updateForm,windowConfig){
    	return null;
    },
    onClickEditButton:function(){}
});