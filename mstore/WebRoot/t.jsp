<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>M-Store用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png" />
    <link rel="apple-touch-startup-image" href="splash.png" />
   
    <meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<script src="jquery/jquery-1.3.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		var width = window.innerWidth;
		var height = window.innerHeight;
		$("#t").html("w:"+width+",h:"+height);
		
		//alert(window.orientation);
		window.onorientationchange = null;
	});	
	
	
	
</script>
</head>
<style>
* {
	margin: 0px;
	padding: 0px;
}

</style>

<body style="height:100%;width:100%;background:red;">
<div id="t" >w:0,h:0</div>
    	
</body>
</html>

