<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base>
    
    <title>M-Store后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link type="text/css" rel="stylesheet" href="ext/3.2.1/resources/css/ext-all.css"/>
	<link type="text/css" rel="stylesheet" href="ext/lovcombo/css/Ext.ux.form.LovCombo.css"/>
	<link type="text/css" rel="stylesheet" href="ext/common/css/common.css"/>
	<link type="text/css" rel="stylesheet" href="ext/fileuploadfield/css/fileuploadfield.css"/>
	<link type="text/css" rel="stylesheet" href="ext/ImageEditor/css/imagechooser.css"/>
	
	<script type="text/javascript" src="ext/3.2.1/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/3.2.1/ext-all-debug.js"></script>
	<script type="text/javascript" src="ext/lovcombo/js/Ext.ux.form.LovCombo.js"></script>
	<script type="text/javascript" src="ext/common/common.js"></script>
	<script type="text/javascript" src="ext/common/RowExpander.js"></script>
	<script type="text/javascript" src="ext/common/Ext.ux.CommonTabPanel.js"></script>
	
	
	<script type="text/javascript" src="ext/fileuploadfield/FileUploadField.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/Ext.ux.ImageCuter.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/Ext.ux.ImageChooser.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/ImageCuter.js"></script>
	<script type="text/javascript" src="ext/ImageEditor/ImageChooser.js"></script>
	
  </head>
  
 <body>
 
	<div id="north">
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background="images/header_bg.jpg" border=0>
			<TR height=56>
				<TD width=260>
					<IMG height=56 src="images/header_left.jpg" width=260>
				</TD>
				<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
					align=middle>
				</TD>
				<TD align=right width=268>
					<IMG height=56 src="images/header_right.jpg" width=268>
				</TD>
			</TR>
		</TABLE>
	</div>
   
    <div id="center1" class="x-hide-display">
        <p><b>欢迎进入M-Store管理平台</b></p>
    </div>
	
	<div id="south">
		<p>Password By 杭州讯杰科技 2010-8-4</p>
	</div>
</body>
</html>