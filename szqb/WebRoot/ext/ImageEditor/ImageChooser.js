
var ImageChooser = {
	window:null,
	uploadWindow:null,
	proWindow:null,
	proImg:null,
	uploadUrl:"image?upload&type=json",
	imgCutUrl:"image?cut&type=json",
	deleteUrl:"image?delete&type=json",
	tpl:new Ext.XTemplate(
         '<tpl for=".">',
         '<div class="thumb-wrap" >',
         '<div class="thumb"><img src="{url}" class="thumb-img"></div>',
         '<span>{width}*{height}</span></div>',
         '</tpl>'
    ),
    cutConf:{
    
    },
    dateView:null,
	store:new Ext.data.JsonStore({
		url: "image?list&type=json",
        autoLoad: false,
        totalProperty:"totalCount",
        root: 'data',
        id:'pictureId',
        fields:[
            'pictureId','name', 'url','width','height'
        ]
	}),
	config:{
		
	},
	show:function(c){
		Ext.apply(this,c); 
		if(this.window){
			if(this.window.isVisible()){
				return;
			} 
			this.window.show();
			return;
		}
		
		var conf = this.config;
		this.window = this.createWindow();
		this.window.show();
		
	},
	hide:function(){
		if(this.window){
			if(this.window.isVisible()){
				this.window.hide();
				return;
			} 
		}
	},
	clickChoose:function(data){
		if(data){
			this.complete(data);
			return;
		}
		var r = this.dataView.getSelectedRecords()[0];
		if(r){
			this.complete(r.data);
		}
		
	},
	clickShowProImage:function(){
		var _this = this;
		var r = this.dataView.getSelectedRecords()[0];
		if(r == undefined || r == null){
			return;
		}
		this.proWindow = this.createProWindow(r.data);
		this.proWindow.show();
	},
	clickCut:function(){
		var _this = this;
		var r = this.dataView.getSelectedRecords()[0];
		if(r == undefined || r == null){
			return;
		}
		this.showCutWindow(r.data);
	},
	showCutWindow:function(data){
		var _this = this;
		var conf = {
			imageUrl:data.url,
			onClickOKButton:function(x,y,w,h,imgUrl){
				var t = this;
				Ext.Ajax.request({
				   url: _this.imgCutUrl,
				   params: {
						x:x,
						y:y,
						w:w,
						h:h,
						url:imgUrl 
				   },
				   success:function(response){
				   		eval("var temp="+response.responseText);
				   		if(temp.success){
				   			t.close();
				   			_this.onCutSuccess(temp);
				   		}else{
				   			Ext.Msg.alert("提示",temp.msg);
				   		}
				   },
				   falure:function(){
				   		Ext.Msg.alert("提示","系统错误");
				   }
				});
			}
		};
		Ext.apply(this.cutConf,conf);
		new Ext.ux.ImageCuter(this.cutConf).show();		
	},
	clickUpload:function(){
		if(this.uploadWindow){
			this.uploadWindow.show();
			return;
		} 
		this.createUploadWindow();
		this.uploadWindow.show();
	},
	clickDelete:function(){
		var _this = this;
		var r = this.dataView.getSelectedRecords()[0];
		if(r == undefined || r == null){
			return;
		}
		var deleteUrl = this.deleteUrl;
		Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
         		if(bool == 'yes'){
					Ext.Ajax.request({
						url:deleteUrl,
						params:{
							pictureId:r.data.pictureId
						},
						success:function(response){
							eval("var temp="+response.responseText);
					   		if(temp.success){
					   			_this.onDeleteSuccess(temp);
					   		}else{
					   			Ext.Msg.alert("提示",temp.msg);
					   		}
						},
						falure:function(){
							Ext.Msg.alert("提示","连接系统发生错误");
						}
					});
         			return true;
         		}else{
         			return false;
         		}
         });
		
	},
	clickRefresh:function(){
		this.store.reload();
	},
	clickExit:function(){
		this.window.hide();
	},
	onUploadSuccess:function(form,action){
		//this.uploadWindow.hide();
		this.store.reload();
		
		this.clickChoose(action.result.data[0]);
	},
	onDeleteSuccess:function(temp){
		this.store.reload();
	},
	onCutSuccess:function(data){
		this.store.reload();
		if(this.proWindow){
			this.proWindow.close();
		}
		this.proWindow = this.createProWindow(data);
		this.proWindow.show();
	},
	createProWindow:function(data){
		var _this = this;
		return (function(){
		
			var boxImg = new Ext.BoxComponent({
				layout:'absolute',
			    xtype: 'box', //或者xtype: 'component',  
			    width:data.width,
				height:data.height,
			    autoEl: {  
			        tag: 'img',    //指定为img标签  
			        src: data.url    //指定url路径  
			    }  
		  	});
		  	
		  	_this.proImg = boxImg;
			var w = new Ext.Window({
		  		title:"原图片",
		  		layout:"fit",
		  		closeAction:"hide",
		  		resizable:false,
		  		items:boxImg,
			  	buttons:[{
			  		text:"选择",
			  		handler:function(){
			  			w.close();
			  			_this.clickChoose(data);
			  		}
			  	}]
		  	});
		  	return w;
		})();
	},
	createUploadWindow:function(){
		if(this.uploadWindow) return;
		var url = this.uploadUrl;
		var _this = this;
		this.uploadWindow = (function(){
			var fp = new Ext.FormPanel({
		        fileUpload: true,
		     	border:false,
		        autoHeight: true,
		        buttonAlign:'center',
		        bodyStyle: 'padding: 10px 10px 0 10px;',
		        layout:'absolute',
		        defaults: {
		            allowBlank: false,
		            msgTarget: 'side'
		        },
		        items: [{
		        	y:10,
		        	x:7,
		        	anchor: '9%',
		        	xtype:'button',
		        	text: '上传',
		        	handler: function(){
		                if(fp.getForm().isValid()){
		                   	fp.getForm().submit({
		                   		url:url,
		                   		success: function(form, action) {
							       _this.onUploadSuccess(form,action);
							    },
							    failure: function(form, action) {
							        switch (action.failureType) {
							            case Ext.form.Action.CLIENT_INVALID:
							                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
							                break;
							            case Ext.form.Action.CONNECT_FAILURE:
							                Ext.Msg.alert('Failure', 'Ajax communication failed');
							                break;
							            case Ext.form.Action.SERVER_INVALID:
							               Ext.Msg.alert('Failure', action.result.msg);
							        }
							    }
		                   	});
		                }
		            }
		        },{
		        	x:'11%',
		        	anchor: '85%',
		            xtype: 'fileuploadfield',
		            id: 'upload',
		            hideLabel :true,
		            emptyText: 'Select an image',
		            fieldLabel: 'Photo',
		            name: 'picture',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'upload-icon'
		            }
		        }]
		    });
		    
		    var uploadPanel = new Ext.Panel({
		        layout:'fit',
		  		border:false,
		  		items:[fp]
		  	});
		  	
		  	var uploadWindow = new Ext.Window({
		    	title:"图片上传",
		    	layout:"fit",
		    	items:uploadPanel,
		    	width:300,
		    	height:80,
		    	closeAction:"hide"
		    	
		    });
		    return uploadWindow;
		
		})();
		
	},
	createWindow:function(){
		var _this = this;
		
		return (function(){
			_this.store.load({params:{start:0,limit:20}});
			var url = _this.uploadUrl;
			var dv = new Ext.DataView({
		        itemSelector: 'div.thumb-wrap',
		        style:'overflow:auto',
		        height:300,
		        singleSelect :true,
		        loadingText :"正在加载.....",
		        store:_this.store,
		        tpl:_this.tpl,
		        listeners:{
		        	dblclick:function(t,index,node,e){
						_this.clickChoose();
		        	}
		        }
		    });
		    
		    _this.dataView = dv;
			
		    var bbar = new Ext.PagingToolbar({
		        pageSize: 20,
		        store: _this.store,
		        displayInfo: true,
		        displayMsg: '第{0}到{1}条数据 共{2}条',
		        emptyMsg: "没有记录"
		    });
		    
		    var tbar = [];
		    
		    tbar.push({
	  			text: '选择',
	  			id:'imageChooserCompare',
	       	 	iconCls:'picture-choose',
	       	 	handler:function(){
	  				_this.clickChoose();
	  			}
	  		});
	  		//添加原图显示按钮
	  		tbar.push({
	  			text: '原图',
	        	iconCls:'picture',
	  			handler:function(){
	  				_this.clickShowProImage();
	  			}
	  		});
	  		
	  		tbar.push({
	  			 text: '裁剪',
			     iconCls:'picture-edit',
			     handler:function(){
			     	_this.clickCut();
	  			 }
	  		});
	  		
	//  		tbar.push({
	//  			text: '上传',
	//		    iconCls:'picture-add',
	//		    handler:function(){
	//  				_this.clickUpload();
	//  			}
	//  		});
	  		
	  		tbar.push({
	  			text: '删除',
			    iconCls:'picture-delete',
			    handler:function(){
			    	_this.clickDelete();
			    }
	  		});
	  		
	  		tbar.push({
	  			text: '刷新',
			    iconCls:'picture-refresh',
			    handler:function(){
			    	_this.clickRefresh();
			    }
	  		});
	  		
	  		tbar.push({
	  			text: '退出',
	        	iconCls:'picture-away',
	            handler : function(){
	              	_this.clickExit();
	            }
	  		});
		    
		    var panel = new Ext.Panel({
	    		id:"images",
		        region:'center',
		        //layout:'fit',
		        items: dv,
		        //height:333,
		        border:false,
		        buttonAlign:'center',
	    		bbar: bbar,
			    tbar:tbar
	    	});
	    	
	    	var fp = new Ext.FormPanel({
		        fileUpload: true,
		     	border:false,
		        autoHeight: true,
		        buttonAlign:'center',
		        bodyStyle: 'padding: 10px 10px 0 10px;',
		        layout:'absolute',
		        defaults: {
		            allowBlank: false,
		            msgTarget: 'side'
		        },
		        items: [{
		        	x:'11%',
		        	anchor: '85%',
		            xtype: 'fileuploadfield',
		            id: 'upload',
		            hideLabel :true,
		            emptyText: 'Select an image',
		            fieldLabel: 'Photo',
		            name: 'picture',
		            buttonText: '',
		            buttonCfg: {
		                iconCls: 'upload-icon'
		            }
		        },{
		        	y:10,
		        	x:7,
		        	anchor: '9%',
		        	xtype:'button',
		        	text: '上传',
		        	handler: function(){
		                if(fp.getForm().isValid()){
		                   	fp.getForm().submit({
		                   		url:url,
		                   		success: function(form, action) {
							       _this.onUploadSuccess(form,action);
							    },
							    failure: function(form, action) {
							        switch (action.failureType) {
							            case Ext.form.Action.CLIENT_INVALID:
							                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
							                break;
							            case Ext.form.Action.CONNECT_FAILURE:
							                Ext.Msg.alert('Failure', 'Ajax communication failed');
							                break;
							            case Ext.form.Action.SERVER_INVALID:
							               Ext.Msg.alert('Failure', action.result.msg);
							        }
							    }
		                   	});
		                }
		            }
		        }]
		    });
		    
		    var uploadPanel = new Ext.Panel({
		    	id:'uploadPanel',
		  		border:false,
		  		region:'north',
		  		height:40,
		  		items:[fp]
		  	});
	    	
		    
			var window = new Ext.Window({
				id:"imagechooser",
				title:"图片选择器",
				//height: 400,
				width: 460,
				region:'center',
				closeAction:'hide',
				border:false,
				items:[uploadPanel,panel]
			});
			
			return window;
			
		})();
	}

}