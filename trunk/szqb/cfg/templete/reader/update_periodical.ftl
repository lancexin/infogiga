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
		
		var readerStore = new Ext.data.JsonStore({ 
			autoLoad : true,
			idProperty: 'readerId',  	
			url :'reader?comboReader&type=json', 
			fields : ['readerId', 'readerName'] 
		});
		
		    var readerCombo = new Ext.form.ComboBox({ 
		    	fieldLabel: '所属读物',
				store : readerStore, 
				valueField : 'readerId',// 域的值,对应于store里的fields 
				displayField : 'readerName',// 显示的域,对应于store里的fields 
				hideOnSelect:false,
				triggerAction:'all',
				editable : false,
				allowBlank:false,
				hiddenName:"readerId",
				mode:'local',
				width:200
			}); 
	
		
		var p = new Ext.FormPanel({
	        labelWidth: 75,
	        width:600,
	        frame:true,
	        title:'期刊添加',
	        buttons:[{
	        	text:'完成并上传资料',
	        	handler:function(){
	        		Ext.MessageBox.confirm('确认', '确认要进行下一步吗?', function(btn) {
						if (btn == 'yes') {
							p.getForm().doAction('submit',{
								url:'periodical?update&type=json',
                   				method:'post',
								success:function(form,action){
									document.location = "p?addPage&periodicalId="+action.result.periodicalId;
								},
								failure:function(form,action){
									if(action.result){
										Ext.Msg.alert('错误',action.result.msg);
									}   
                  	 			}
							});
						}
					});
	        	}
	        }],
	        items: [{
	        		xtype:'hidden',
					allowBlank:false,
					name:'periodicalId',
					value:'${model.periodicalId}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'indexPic',
					value:'${model.indexPic}'
	        	},{
	        		xtype:'hidden',
					allowBlank:false,
					name:'tabloidPic',
					value:'${model.tabloidPic}'
	        	},{
	                fieldLabel: '发布时间',
	                name: 'publishTime',
	                allowBlank:false,
	                xtype:'datefield',
	                format: 'Y-m-d',
					width:200,
					value:'${model.publishTime}'
	            },{
	                fieldLabel: '出版刊号',
	                name: 'number',
	                allowBlank:false,
	                xtype:'numberfield',
	                allowDecimals:false, 
	                allowNegative:false,
	                minValue:1,
					width:200,
					value:'${model.number}'
	            },readerCombo,{
	            	 layout:'column',
	            	 border:false,
	            	 items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	items:[{
	            	 		xtype:'label',
		                    text: '首页图片:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:400,
										height:300,
										baseImgX:50,                //设置图片框的位置x       
										baseImgY:50,                //设置图片框的位置y   
										baseImgWidth:300,            //图片剪裁框的宽度度
										baseImgHeight:200,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 300 || data.height != 200){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("img1").getEl().dom.src = data.url;
								    	p.getForm().findField('indexPic').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'img1',
						    xtype: 'box',
						    width:300,
						    heigth:200, 
						    style: {
            					marginBottom: '10px'
        					},
						    autoEl: {  
						        tag: 'img',
						        src: "${model.indexPic}"
						    }  
					  	}]
	            	}]
	            },{
	            	 layout:'column',
	            	 border:false,
	            	 items:[{
	            		layout: 'form',
	            	 	width:80,
	            	 	items:[{
	            	 		xtype:'label',
		                    text: '缩略图:'
	            	 	}]
	            	},{
	            		layout: 'form',
	            		border:false,
	            		
	            		items:[{
	            			xtype:'button',
		                    text:'选择图片',
		                    anchor:'100%',
		                    handler:function(){
		                    	ImageChooser.show({
									cutConf:{
										width:300,
										height:400,
										baseImgX:80,                //设置图片框的位置x       
										baseImgY:97,                //设置图片框的位置y   
										baseImgWidth:140,            //图片剪裁框的宽度度
										baseImgHeight:205,           //图片剪裁框的高度
										baseImgBorder:true          //图像剪裁框是否有边框
									},
									complete:function(data){
										if(data.width != 140 || data.height != 205){
								    		Ext.Msg.alert("提示","图片大小不合适");
								    		return;
								    	}
								    	Ext.getCmp("img2").getEl().dom.src = data.url;
								    	p.getForm().findField('tabloidPic').setValue(data.url);
								    	this.window.hide();
									}
								});
		                    }
	            		},{
		            	 	id:'img2',
						    xtype: 'box',
						    width:140,
						    heigth:205, 
						    autoEl: {  
						        tag: 'img',
						        src: "${model.tabloidPic}"
						    }  
					  	}]
	            	}]
	            }
	        ]
	    });
	    
	    readerStore.on("load",function(){
	    	readerCombo.setValue(${model.readerId});
	    });
	    p.render(Ext.getBody());
	    
	    
	    
	});

</script>
<title>期刊添加</title>
</head>
<body>
	<script type="text/javascript">
		
	</script>
</body>
</html>