<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">

  </head>
  <FRAMESET border=0 frameSpacing=0 rows="60, *" frameBorder=0>
	<FRAME name=header src="page/header.jsp" frameBorder=0 noResize scrolling=no>
	<FRAMESET cols="170, *">
	<FRAME name=menu src="list?power" frameBorder=0 noResize scrolling=no>
	<FRAME name=main src="page/main.htm" frameBorder=0 noResize scrolling="auto">
	</FRAMESET>
   </FRAMESET>
	<noframes>
	</noframes>
</html>
