<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	<link type="text/css" rel="stylesheet" href="ext/ImageEditor/css/imagechooser.css"/>
	<script type="text/javascript" src="ext/ImageEditor/ImageChooser.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/ImageCuter2.js"></script>
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<script type="text/javascript">
	
		var a = {ss:"asdfasdfasdf"};
		(function(cuter){
			alert(cuter.ss);
			
			
		})(a);
		
		
	
	</script>

  </head>
  
  <body>
   <script type="text/javascript">
  
   </script>
  </body>
</html>
