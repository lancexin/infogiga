<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出错啦!</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="shortcut icon" href="./favicon.ico" />
	<link rel="stylesheet" type="text/css" href="./css/error.css">	
	<script type="text/javascript" src="./js/jquery-1.3.2.min.js"></script>	
  </head>
  <body>
  	<div class='logo'><img src='./img/logo.png'/></div>
  	<div class='title'>出错啦!</div>
  	<div class='message'>
	  <%
	  	String message = request.getParameter("message");
	  	if(message == null){return;}
	  	else if(message.equals("authority")) {
	  %>
	  	您没有权限查看该页
	  <%
	  	}
	  	else if(message.equals("login")) {
	  %>
	    您还未登录，请<a href='./login.jsp'>登录</a>后查看
	  <%
	  	}
	  	else if(message.equals("illegal")) {	  
	  %>
	    请按正常流程提交，谢谢合作。
	  <%
	  	}
	  %>
  	</div>
  </body>  
</html>
