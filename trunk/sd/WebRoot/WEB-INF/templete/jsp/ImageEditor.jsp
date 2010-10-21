<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ImageEditor.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<link type="text/css" rel="stylesheet" href="ext/ImageEditor/css/imagechooser.css"/>
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/Ext.ux.ImageCuter.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
    
    
	    	var store = new Ext.data.JsonStore({
	            url: 'image?list&type=json',
	            autoLoad: false,
	            totalProperty:"totalCount",
	            root: 'data',
	            id:'pictureId',
	            fields:[
	                'pictureId','name', 'url','width','height'
	            ]
	        });
	    	store.load({params:{start:0,limit:20}});
	    	
	    	var view = new Ext.DataView({
		        itemSelector: 'div.thumb-wrap',
		        style:'overflow:auto',
		        singleSelect :true,
		        loadingText :"正在加载.....",
		        store: store,
		        tpl: new Ext.XTemplate(
		            '<tpl for=".">',
		            '<div class="thumb-wrap" >',
		            '<div class="thumb"><img src="{url}" class="thumb-img"></div>',
		            '<span>{width}*{height}</span></div>',
		            '</tpl>'
		        ) 
		    });
		
		    var images = new Ext.Panel({
		    	id:"images",
		        region:'center',
		        layout:'fit',
		        items: view,
		        border:false,
		        buttonAlign:'center',
		        bbar: new Ext.PagingToolbar({
			        pageSize: 20,
			        store: store,
			        displayInfo: true,
			        displayMsg: '第{0}到{1}条数据 共{2}条',
			        emptyMsg: "没有记录"
			    }),
			    tbar:[{
		        	 text: '选择',
		        	 iconCls:'picture-choose',
		             handler : function(){
		              	var r = view.getSelectedRecords()[0];
						if(r == undefined || r == null){
							return;
						}
						alert("你选择了 "+r.data.url);
		             }
		        },{
		        	 text: '原图',
		        	 iconCls:'picture',
		             handler : function(){
		              	var r = view.getSelectedRecords()[0];
						if(r == undefined || r == null){
							return;
						}
						
					  	var w = new Ext.Window({
					  		title:"原图片",
					  		layout:"fit",
					  		closeAction:"hide",
					  		resizable:false,
					  		items:new Ext.BoxComponent({
								layout:'absolute',
							    xtype: 'box', //或者xtype: 'component',  
							    autoEl: {  
							        tag: 'img',    //指定为img标签  
							        src: r.data.url    //指定url路径  
							    }  
						  	}),
						  	buttons:[{
						  		text:"选择",
						  		handler:function(){
						  			alert("你选择的图片是："+r.data.url);
						  		}
						  	},{
						  		text:"取消",
						  		handler:function(){
						  			w.close();
						  		}
						  	}]
					  	});
					  	w.show();
		             }
		        },{
		        	 text: '裁剪',
		        	 iconCls:'picture-edit',
		             handler : function(){
		              	var r = view.getSelectedRecords()[0];
						if(r == undefined || r == null){
							return;
						}
						
						var ic = new Ext.ux.ImageCuter({
							width:500,
							height:400,
							baseImgX:150,                //设置图片框的位置x       
							baseImgY:100,                //设置图片框的位置y   
							baseImgWidth:200,            //图片剪裁框的宽度度
							baseImgHeight:200,           //图片剪裁框的高度
							baseImgBorder:true,          //图像剪裁框是否有边框
							imageUrl:r.data.url,         //加载图片的路径
							opacity:80,                  //图片蒙版透明度
							imgWidth:Number(r.data.width),       //图片原始宽度
						    imgHeight:Number(r.data.height),     //图片原始高度
						    onClickOKButton:function(x,y,w,h,imgUrl){
								//alert("x:"+x+"  y:"+y+"  w:"+w+"  h:"+h);
								Ext.Ajax.request({
								   url: 'image?cut&type=json',
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
								   			store.reload();
								   			Ext.Msg.alert("提示",temp.msg);
								   			ic.close();
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
							}
						});
						ic.show();
		             }
		        },{
		        	 text: '上传',
		        	 iconCls:'picture-add',
		             handler : function(){
		              	uploadWindow.show();
		             }
		        },{
		        	 text: '删除',
		        	 iconCls:'picture-delete',
		             handler : function(){
		              	var r = view.getSelectedRecords()[0];
						if(r == undefined || r == null){
							return;
						}
						Ext.Ajax.request({
							url:"image?delete&type=json",
							params:{
								pictureId:r.data.pictureId
							},
							success:function(response){
								eval("var temp="+response.responseText);
						   		if(temp.success){
						   			Ext.Msg.alert("提示",temp.msg);
						   			store.reload();
						   		}else{
						   			Ext.Msg.alert("提示",temp.msg);
						   		}
							},
							falure:function(){
								Ext.Msg.alert("提示","连接系统发生错误");
							}
						
						});
						
		             }
		        },{
		        	 text: '刷新',
		        	 iconCls:'picture-refresh',
		             handler : function(){
		              	store.reload();
		             }
		        },{
		        	 text: '退出',
		        	 iconCls:'picture-away',
		             handler : function(){
		              	window.close();
		             }
		        }]
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
		        	y:10,
		        	x:7,
		        	anchor: '9%',
		        	xtype:'button',
		        	text: '上传',
		        	handler: function(){
		                if(fp.getForm().isValid()){
		                   	fp.getForm().submit({
		                   		url: 'image?upload&type=json',
		                   		success: function(form, action) {
							       Ext.Msg.alert('Success', action.result.msg);
							       store.reload();
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
		  		id:"uploadPanel",
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
		  
	    	var window = new Ext.Window({
				height : 400,
				width  : 445,
				layout : 'fit',
				title  : '图片选择器',
				items  : images
			});
			window.show();
	    });
	
	
	</script>
  </head>
  
  <body>
    
  </body>
</html>
