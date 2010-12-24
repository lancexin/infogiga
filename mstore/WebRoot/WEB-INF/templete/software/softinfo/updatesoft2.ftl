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
		var ll = 0;
		var submit_button;
		
		var p = new Ext.FormPanel({
	        labelWidth: 75,
	        width:780,
	        bodyStyle:'padding:5px 5px 0;',
	        frame:true,
	        title:'软件修改',
	        fileUpload: true,
	        buttons:[{
	        	text:'提交',
	        	handler:function(){
	        		submit_button = this;
	        		submit_button.disable();
	        		p.getForm().doAction('submit',{
						url:'soft?update&softId=${soft.id}&type=json',
                   		method:'post',
						success:function(form,action){
							//Ext.Msg.alert('提示',action.result.msg);
							p.getForm().reset();
							document.location = "p?updatephonesoft&softId=${soft.id}";
							submit_button.enable();
						},
						failure:function(form,action){
							if(action.result){
								Ext.Msg.alert('错误',action.result.msg);
							}   
                  	 	}
					});
	        	}
	        },{
	        	text:"恢复",
	        	handler:function(){
	        		submit_button.enable();
	        	}
	        }],
	        items: [{
	        		xtype:'hidden',
					allowBlank:false,
					name:'icon',
					value:'${soft.icon!""}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic1',
					value:'${soft.pic1!""}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic2',
					value:'${soft.pic2!""}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic3',
					value:'${soft.pic3!""}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic4',
					value:'${soft.pic4!""}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'pic5',
					value:'${soft.pic5!""}'
	        	},{
	                fieldLabel: '软件名称',
	                name: 'softName',
	                allowBlank:false,
	                xtype:'textfield',
	            	width: 230,
					value:'${soft.softName!""}'
	            },{
	                fieldLabel: '英文缩写',
	                name: 'shortName',
	                allowBlank:false,
	                xtype:'textfield',
	            	width: 230,
	            	value:'${soft.shortName!""}'
	            },{
	           		fieldLabel: '软件状态',
					width: 230,
					xtype:'combo',
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
			        	data: [[1, '启用'], [0, '停用']]
			    	}),
					valueField: 'statusId',
					displayField: 'text',
					hiddenValue:1,
					hiddenName:"status"
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
            				{boxLabel:'${menu.menuName}',name: 'softmenuId',inputValue: '${menu.id}' 
            					<#list soft.softindexes as si>
            						<#if si.softmenu.id == menu.id>
            							,checked: true
            						</#if>
            					</#list>},
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
	            	width: 230,
					value:"${soft.description!""}"
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
										baseImgWidth:88,            //图片剪裁框的宽度度
										baseImgHeight:88,           //图片剪裁框的高度
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
						        src: "${soft.icon!""}"
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
						        src: "${soft.pic1!""}"
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
						        src: "${soft.pic2!""}"
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
						        src: "${soft.pic3!""}"
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
						        src: "${soft.pic4!""}"
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
						        src: "${soft.pic5!""}"
						    }  
					  	}]
	            	}]
			    },{
			    	xtype:'button',
			    	text:'添加附件',
			    	handler:function(){
			    		var tt = ll;
			    		ll = ll+1;
			    		p.add({
			    			xtype:'fieldset',
			    			autoHeight:true,
			    			items:[{
	            				xtype: 'checkboxgroup',
	            				fieldLabel: '软件分组',
	            				columns: 5,
	            				itemCls: 'x-check-group-alt',
	            				allowBlank :false,
	            				value:'asdfasd',
            					items: [
            					<#list phonearray as a>
            						<#if a_has_next>
            							{boxLabel:'${a.phonearrayName}',inputValue: '${a.id}',listeners:{
            								check:function(t,b){
            									if(b){
            										Ext.getDom('upload'+tt+'-file').name = Ext.getDom('upload'+tt+'-file').name+'${a.id}_';
            									}else{
            										var name = Ext.getDom('upload'+tt+'-file').name;
            										Ext.getDom('upload'+tt+'-file').name = name.replace("_${a.id}_","_");
            									}
            								}
            							}},
            						<#else>
            							{boxLabel:'${a.phonearrayName}',inputValue: '${a.id}',listeners:{
            								check:function(t,b){
            									if(b){
            										Ext.getDom('upload'+tt+'-file').name = Ext.getDom('upload'+tt+'-file').name+'${a.id}_';
            									}else{
            										var name = Ext.getDom('upload'+tt+'-file').name;
            										Ext.getDom('upload'+tt+'-file').name = name.replace("_${a.id}_","_");
            									}
            								}
            							}}
            						</#if>
            			
	    						</#list>
           						]
	            			},{
	            				id:'upload'+tt,
	            				xtype: 'fileuploadfield',
			            		width:210,
			            		emptyText: '请选择...',
			            		fieldLabel: '附件上传',
			            		name: '_',
			            		buttonText: '',
			            		buttonCfg: {
			                		iconCls: 'upload-icon'
			            		}
	            			},{
	            				xtype:'button',
	            				text:'删除附件',
	            				handler:function(){
	            					var _panel = this.ownerCt;
	            					p.remove(_panel);
	            					p.doLayout(); 
	            				}
	            			}]
			    		
			    		});
			    		
			    		p.doLayout();
			    	}
			    }
	        ]
	    });
		
		var nd = [];
			    
	    <#list soft.attachments as attachment>
	    	<#assign ts = ""> 
	    	<#list attachment.attachandarraies as aaa>
	    		<#assign ts = ts+aaa.phonearray.phonearrayName+","> 
	    	</#list>
	        nd.push({
			   xtype:'fieldset',
			   autoHeight:true,
			   items:[{
			   			layout:'column',
	            		border:false,
	            		style:'margin:5px 0 0;',
	            		items:[{
	                    	xtype:'label',
	                    	text:'所属分组:'
	                	},{
	                   	 	xtype:'label',
	                    	text:'${ts}'
	                	}]
	            	},{
	            		layout:'column',
	            		border:false,
	            		style:'margin:5px 0 0;',
	            		items:[{
	                    	xtype:'label',
	                    	text:'附件名称:'
	               		},{
	                    	xtype:'label',
	                    	text:'${attachment.name}'
	                	}]
	            	},{
	            	xtype:'button',
	            	text:'删除附件',
	            	handler:function(){
	            		var _panel = this.ownerCt;
	            		
	            		Ext.Ajax.request({
               				url:'attachment?delete&attachmentId=${attachment.id}&type=json',
               		 		success:function(response, options){
               		 			eval("action = "+response.responseText);
               		 			if(action.success){
               		 				p.remove(_panel);
	            					p.doLayout(); 
               		 			}
         						Ext.Msg.alert('提示',action.msg);
         						
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
	    				
	    </#list>
	   
	    
	   
	    p.add(nd);
	    p.render('mainfest');
	});

</script>
<title>软件添加</title>
</head>
<body>
	<div id=mainfest style="padding:5px;width:780;"></div>
</body>
</html>