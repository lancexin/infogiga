<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<link type="text/css" rel="stylesheet" href="ext/lovcombo/css/Ext.ux.form.LovCombo.css"/>
	<link type="text/css" rel="stylesheet" href="ext/common/css/common.css"/>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<link type="text/css" rel="stylesheet" href="ext/ImageEditor/css/imagechooser.css"/>
	<link type="text/css" rel="stylesheet" href="ext/UploadDialog/css/Ext.ux.UploadDialog.css"/>
	
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all.js"></script>
	<script type="text/javascript" src="ext/lovcombo/js/Ext.ux.form.LovCombo.js"></script>
	
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>

	<script type="text/javascript" src="ext/ImageEditor/ImageCuter.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/ImageChooser.js"></script>
	<script type="text/javascript" src="ext/UploadDialog/UploadPanel.js"></script>
<script type="text/javascript">
	Ext.onReady(function(){
		var dialog  = new Ext.ux.UploadDialog.Dialog({ 
			  frame:true,
			  applyTo:"dialog",
	          title: '读物资料上传',    
	          width : 600,   
	          height : 600,   
	          minWidth : 450,   
	          minHeight : 300,   
	          constraintoviewport: true,    
	          permitted_extensions:['pdf','PDF'],      
	          post_var_name: 'upload',   
	          reset_on_hide: false,    
	          allow_close_on_upload: true,   //关闭上传窗口是否仍然上传文件   
	          upload_autostart: true,
	          pId:'${periodicalId}',
	          url:"page?add&type=json&periodicalId=${periodicalId}",    
	          delUrl:'page?delete&type=json&dId=${periodicalId}',
	          listUrl:'page?type=json&periodicalId=${periodicalId}'   
	    });
		
		
		
	    
	});

</script>
<title>期刊添加</title>
</head>
<body>
	<div id="dialog"></div>
</body>
</html>