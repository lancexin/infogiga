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
	<script type="text/javascript" src="ext/ImageEditor/ImageCuter.js"></script>
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<script type="text/javascript">
		function s(s){
			var c = {
				cutConf:{
					width:400,
					height:300,
					baseImgX:150,                //设置图片框的位置x       
					baseImgY:100,                //设置图片框的位置y   
					baseImgWidth:88,            //图片剪裁框的宽度度
					baseImgHeight:88,           //图片剪裁框的高度
					baseImgBorder:true
				},
				complete:function(data){
					alert(s+" "+data.url);
				}
			};
			ImageChooser.show(c);
		}
	
	</script>

  </head>
  
  <body>
   <input type='button' onclick='s("dddddddddd")' value='t'/>
   <input type='button' onclick='s("bbbbbbbbbb")' value='t2'/>
   <input type='button' onclick='s2()' value='t2'/>
  </body>
</html>
