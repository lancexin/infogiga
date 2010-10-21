Ext.namespace("Ext.ux");

Ext.ux.ImageChooser = Ext.extend(Ext.Window,{
	id:"imagechooser",
	title:"图片选择器",
	height: 400,
	width: 445,
	region:'center',
	layout:'fit',
	border:false,
	uploadWindow:null,
	imageCutWindow:null,
	imgStore:null,
	imgView:null,
	dataView:null,
	closeAction:"hide",
	proWindowId:"proWindow",
	cutWindowCinfig:{
		id:"imageCuter",
		imagePanelId:"imagePanel",   
		backAreaId:"backArea",       //图片背景区id
		drugAreaId:"drugArea",       //鼠标拖动区id
		baseImgId:"baseImg",         //图片剪裁框id
		width:500,
		height:400,
		baseImgX:150,                //设置图片框的位置x       
		baseImgY:100,                //设置图片框的位置y   
		baseImgWidth:200,            //图片剪裁框的宽度度
		baseImgHeight:200,           //图片剪裁框的高度
		baseImgBorder:true          //图像剪裁框是否有边框
	},
	uploadUrl:"image?upload&type=json",
	imgCutUrl:"image?cut&type=json",
	deleteUrl:"image?delete&type=json",
	imgStoreConfig:{
        url: "image?list&type=json",
        autoLoad: false,
        totalProperty:"totalCount",
        root: 'data',
        id:'pictureId',
        fields:[
            'pictureId','name', 'url','width','height'
        ]
    },
    tpl:new Ext.XTemplate(
         '<tpl for=".">',
         '<div class="thumb-wrap" >',
         '<div class="thumb"><img src="{url}" class="thumb-img"></div>',
         '<span>{width}*{height}</span></div>',
         '</tpl>'
    ),
	getImgStore:function(){
		if(this.imgStore){
			return this.imgStore;
		}else{
			this.imgStore =  new Ext.data.JsonStore(this.imgStoreConfig);
			this.imgStore.load({params:{start:0,limit:20}});
			return this.imgStore;
		}
	},
    getDataView:function(){
    	var tpl = this.tpl;
    	var store = this.getImgStore();
    	this.dataView = new Ext.DataView({
    		
	        itemSelector: 'div.thumb-wrap',
	        style:'overflow:auto',
	        singleSelect :true,
	        loadingText :"正在加载.....",
	        store: store,
	        tpl: tpl 
	    });
	    return this.dataView;
    },
    getBBar:function(){
    	var store = this.getImgStore();
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
    	//添加选择按钮
  		tbar.push({
  			text: '选择',
       	 	iconCls:'picture-choose',
            handler : function(){
            	var r = _this.dataView.getSelectedRecords()[0];
				if(r == undefined || r == null){
					return;
				}
            	_this.onClickChooseButton(r.data);
            }
  		});
  		//添加原图显示按钮
  		tbar.push({
  			text: '原图',
        	iconCls:'picture',
  			handler:function(){
  				var r = _this.dataView.getSelectedRecords()[0];
				if(r == undefined || r == null){
					return;
				}
  				_this.onClickProImageButton(r.data);
  			}
  		});
  		
  		tbar.push({
  			 text: '裁剪',
		     iconCls:'picture-edit',
		     handler:function(){
		     	var r = _this.dataView.getSelectedRecords()[0];
				if(r == undefined || r == null){
					return;
				}
  				_this.onClickCutButton(r.data);
  			 }
  		});
  		
  		tbar.push({
  			text: '上传',
		    iconCls:'picture-add',
		    handler:function(){
  				_this.onClickUploadButton();
  			}
  		});
  		
  		tbar.push({
  			text: '删除',
		    iconCls:'picture-delete',
		    handler:function(){
		    	Ext.MessageBox.confirm("提示","您确定要删除该项吗?",function(bool){
 
               		if(bool == 'yes'){
               			var r = _this.dataView.getSelectedRecords()[0];
						if(r == undefined || r == null){
							return;
						}
				    	_this.onClickDeleteButton(r.data);
               		
               			return true;
               		}else{
               			return false;
               		}
               });
		    }
  		});
  		
  		tbar.push({
  			text: '刷新',
		    iconCls:'picture-refresh',
		    handler:function(){
		    	_this.onClickRefreshButton();
		    }
  		});
  		
  		tbar.push({
  			text: '退出',
        	iconCls:'picture-away',
            handler : function(){
              	_this.onClickLogoutButton();
            }
  		});
  		return tbar;
    },
    getUploadFormWindow:function(w){
    	var uploadUrl = w.uploadUrl;
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
	                   		url: uploadUrl,
	                   		success: function(form, action) {
						       w.onUploadSuccess(form,action,w,uploadWindow);
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
    },
    getImageView:function(w){
    	var view = this.getDataView();
    	var bbar = this.getBBar();
    	var tbar = this.getTbar(w);
    	var panel = new Ext.Panel({
    		id:"images",
	        region:'center',
	        layout:'fit',
	        items: view,
	        border:false,
	        buttonAlign:'center',
    		bbar: bbar,
		    tbar:tbar
    	});
    	return panel;
    },
    showImageCutWindow:function(data){
    	var _this = this;
		var imgCutUrl = this.imgCutUrl;
    	var ic = new Ext.ux.ImageCuter({
    		id:_this.cutWindowCinfig.id,
			imagePanelId:_this.cutWindowCinfig.imagePanelId,   
			backAreaId:_this.cutWindowCinfig.backAreaId,       //图片背景区id
			drugAreaId:_this.cutWindowCinfig.drugAreaId,       //鼠标拖动区id
			baseImgId:_this.cutWindowCinfig.baseImgId,         //图片剪裁框id
			width:_this.cutWindowCinfig.width,
			height:_this.cutWindowCinfig.height,
			baseImgX:_this.cutWindowCinfig.baseImgX,
			baseImgY:_this.cutWindowCinfig.baseImgY,       
			baseImgWidth:_this.cutWindowCinfig.baseImgWidth,      
			baseImgHeight:_this.cutWindowCinfig.baseImgHeight,
			baseImgBorder:_this.cutWindowCinfig.baseImgBorder,
			imageUrl:data.url,
			opacity:80,
			imgWidth:Number(data.width),
		    imgHeight:Number(data.height),
		    onClickOKButton:function(x,y,w,h,imgUrl){
				Ext.Ajax.request({
				   url: imgCutUrl,
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
				   			_this.onCutSuccess(temp,ic);
				   		}else{
				   			Ext.Msg.alert("提示",temp.msg);
				   		}
				   },
				   falure:function(){
				   		Ext.Msg.alert("提示","系统错误");
				   }
				});
												
			},
			onClickCancelButton:function(){
				this.close();
				_this.imageCutWindow = null;
			}
		});
		ic.show();
    },
    showProWindow:function(data){
    
    	var _this = this;
    	
    	var w = new Ext.Window({
	  		title:"原图片",
	  		layout:"fit",
	  		closeAction:"hide",
	  		resizable:false,
			id:_this.proWindowId,
	  		items:new Ext.BoxComponent({
				layout:'absolute',
			    xtype: 'box', //或者xtype: 'component',  
			    width:data.width,
				height:data.height,
			    autoEl: {  
			        tag: 'img',    //指定为img标签  
			        src: data.url    //指定url路径  
			    }  
		  	}),
		  	buttons:[{
		  		text:"选择",
		  		handler:function(){
		  			w.close();
		  			_this.onClickChooseButton(data);
		  		}
		  	}]
	  	});
	  	w.show();
    
    },
    initComponent:function(){
    	var _this = this;
    	Ext.ux.ImageCuter.superclass.initComponent.call(this);
    	this.on('beforeclose',function(p){
    		_this.onWindowClose();
	    });
    	this.imgView = this.getImageView(this);
    	this.add(this.imgView);
    	this.uploadWindow = this.getUploadFormWindow(this);
    	
    	
    },
    onClickChooseButton:function(data){//当点击选择按钮
		this.complete(data);
    },
    onClickProImageButton:function(data){
    	this.showProWindow(data);
    },
    onClickCutButton:function(data){//当点击剪切按钮
		this.showImageCutWindow(data);
    },
    onClickUploadButton:function(){//当点击上传按钮
    	this.uploadWindow.show();
    },
    onClickDeleteButton:function(data){//当点击删除按钮
    	var _this = this;
    	var deleteUrl = this.deleteUrl;
    	Ext.Ajax.request({
			url:deleteUrl,
			params:{
				pictureId:data.pictureId
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
    },
    onClickRefreshButton:function(){
    	this.imgStore.reload();
    },
    onClickLogoutButton:function(){
    	this.close();
    },
    onUploadSuccess:function(form,action,w,a){
    	 //Ext.Msg.alert('提示', action.result.msg);
		 this.imgStore.reload();
		 a.hide();
		 this.showProWindow(action.result);
    },
    onCutSuccess:function(el,window){
    	//Ext.Msg.alert("提示","裁剪成功！");
    	
    	this.imgStore.reload();
    	window.close();
    	this.showProWindow(el);
    },
    onDeleteSuccess:function(data){
    	Ext.Msg.alert("提示",data.msg);
		this.imgStore.reload();
    },
    onWindowClose:function(){
    	var _this = this;
    	this.uploadWindow.close();
    	if(Ext.WindowMgr.get(_this.cutWindowCinfig.id)){
    		Ext.WindowMgr.get(_this.cutWindowCinfig.id).close();
    	}
    	if(Ext.WindowMgr.get(_this.proWindowId)){
    		Ext.WindowMgr.get(_this.proWindowId).close();
    	}
    },
    complete:function(data){
    	alert(data.url);
    }

});