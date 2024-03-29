<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<link type="text/css" rel="stylesheet" href="ext/lovcombo/css/Ext.ux.form.LovCombo.css"/>
	<link type="text/css" rel="stylesheet" href="ext/common/css/common.css"/>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<link type="text/css" rel="stylesheet" href="ext/ImageEditor/css/imagechooser.css"/>
	
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all.js"></script>
	<script type="text/javascript" src="ext/lovcombo/js/Ext.ux.form.LovCombo.js"></script>
	
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>

	<script type="text/javascript" src="ext/ImageEditor/ImageCuter.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/ImageChooser.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){
		
		var p = new Ext.FormPanel({
	        labelWidth: 75,
	        width:780,
	        bodyStyle:'padding:5px 5px 0;',
	        frame:true,
	        title:'软件添加',
	        fileUpload: true,
	        buttons:[{
	        	text:'提交',
	        	handler:function(){
	        		p.add({
	        		xtype:'textfield',
					allowBlank:false,
					name:'asdfasdfasdf',
					id:'aaaaa'
	        	});
					
					p.doLayout();
	        	}
	        },{
	        	text:'取消',
	        	handler:function(){
	        		p.remove(Ext.getCmp("aaaaa"));
					
					p.doLayout();
	        	}
	        },{
	        	text:'设置名称',
	        	handler:function(){
	        	
	        		var na = Ext.getDom("aaaaa").name;
	        		Ext.getDom("aaaaa").name = 'kkkkkkkkkkkkkkkk';
	        		alert(Ext.getDom("aaaaa").name);
					
					//p.doLayout();
	        	}
	        }],
	        items: [{
	        		xtype:'hidden',
					allowBlank:false,
					name:'icon'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic1'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic2'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic3'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic4'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic5'
	        	},{
	                fieldLabel: '软件名称',
	                name: 'softName',
	                allowBlank:false,
	                xtype:'textfield',
	            	width: 230
	            },{
	            	xtype: 'checkboxgroup',
	            	fieldLabel: '软件菜单',
	            	columns: 5,
	            	itemCls: 'x-check-group-alt',
	            	allowBlank :false,
	            	value:'asdfasd',
            		items: [
            		<#list softmenu as menu>
            			<#if menu_has_next>
            				{boxLabel:'${menu.menuName}',name: 'softmenuId',inputValue: '${menu.id}'},
            			<#else>
            				{boxLabel:'${menu.menuName}',name: 'softmenuId',inputValue: '${menu.id}'}
            			</#if>
            			
	    			</#list>
           			]
	            },{
	                fieldLabel: '软件介绍',
	                xtype:'textarea',
	                name: 'description',
	                allowBlank:false,
	            	width: 230
	            },{
	            	 layout:'column',
	            	 border:false,
	            	 width: 230,
	            	 items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	items:[{
	            	 		xtype:'label',
		                    text: '软件图标:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:300,
										baseImgX:150,                //设置图片框的位置x       
										baseImgY:100,                //设置图片框的位置y   
										baseImgWidth:70,            //图片剪裁框的宽度度
										baseImgHeight:70,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 88 || data.height != 88){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("icon").getEl().dom.src = data.url;
								    	p.getForm().findField('icon').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'icon',
						    xtype: 'box',
						    width:88,
						    heigth:88, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/100x100.gif"
						    }  
					  	}]
	            	}]
	            },{
	            	layout:'column',
	            	border:false,
	            	style:'margin:5px 0 0;',
	            	items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	items:[{
	            	 		xtype:'label',
		                    text: '软件截图:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:500,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:90,                //设置图片框的位置y   
										baseImgWidth:240,            //图片剪裁框的宽度度
										baseImgHeight:320,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 240 || data.height != 320){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("pic1").getEl().dom.src = data.url;
								    	p.getForm().findField('pic1').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'pic1',
						    xtype: 'box',
						    width:120,
						    heigth:160, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/240x320.jpg"
						    }  
					  	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:500,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:90,                //设置图片框的位置y   
										baseImgWidth:240,            //图片剪裁框的宽度度
										baseImgHeight:320,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 240 || data.height != 320){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("pic2").getEl().dom.src = data.url;
								    	p.getForm().findField('pic2').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    } 
	            		},{
		            	 	id:'pic2',
						    xtype: 'box',
						    width:120,
						    heigth:160, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/240x320.jpg"
						    }  
					  	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:500,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:90,                //设置图片框的位置y   
										baseImgWidth:240,            //图片剪裁框的宽度度
										baseImgHeight:320,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 240 || data.height != 320){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("pic3").getEl().dom.src = data.url;
								    	p.getForm().findField('pic3').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'pic3',
						    xtype: 'box',
						    width:120,
						    heigth:160, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/240x320.jpg"
						    }  
					  	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:500,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:90,                //设置图片框的位置y   
										baseImgWidth:240,            //图片剪裁框的宽度度
										baseImgHeight:320,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 240 || data.height != 320){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("pic4").getEl().dom.src = data.url;
								    	p.getForm().findField('pic4').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'pic4',
						    xtype: 'box',
						    width:120,
						    heigth:160, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/240x320.jpg"
						    }  
					  	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		style:'margin:0 5px 0 ',
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:500,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:90,                //设置图片框的位置y   
										baseImgWidth:240,            //图片剪裁框的宽度度
										baseImgHeight:320,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 240 || data.height != 320){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("pic5").getEl().dom.src = data.url;
								    	p.getForm().findField('pic5').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'pic5',
						    xtype: 'box',
						    width:120,
						    heigth:160, 
						    autoEl: {  
						        tag: 'img',
						        src: "material/images/240x320.jpg"
						    }  
					  	}]
	            	}]
			    }
	        ]
	    });
		
	    p.render('mainfest');
	    
	});

</script>
<title>软件添加</title>
</head>
<body>
	<div id=mainfest style="padding:5px;width:780;"></div>
</body>
</html>