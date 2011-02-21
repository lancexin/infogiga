<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>M-Store用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png" />
    <link rel="apple-touch-startup-image" href="splash.png" />
    <meta name="viewport" content="width=device-width,height=device-height,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<script src="jquery/jquery-1.3.min.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function () {
			var height = $(document.body).height();
			var width =  $(document.body).width();
			$("#t").html("w:"+width+",h"+height);
		}, 100);
		
		$(window).resize(function(){
			alert("resize");
		});
	});
	
	function hideUrlBar(){
   		setTimeout(function () { window.scrollTo(0, 1) }, 100);
	}
</script>
</head>

<body onload="hideUrlBar()">
<div id="t">w:0,h:0</div>
    	
</body>
</html>

