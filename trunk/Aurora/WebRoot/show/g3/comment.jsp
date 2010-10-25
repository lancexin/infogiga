<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%
	Object obj = session.getAttribute("qrcodePic");
	String qrcodePic="";
	boolean in = false;
	if(obj != null) {
		qrcodePic = (String) obj;
		in = true;
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="max-age=300"/>
<title>意见反馈 | 杭州移动信息化体验馆</title>
<link href="css/ewap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/ewap.css" rel="stylesheet" type="text/css" media="handheld" />
</head>
<body>
<div id="wrap">
    <div id="header"><img src="img/ewap/logo.jpg" width="228" height="30" alt="移动信息化体验馆" /></div>
    <div id="nav">
        <ul>
            <li><a href="q?p=1&v=2">首页</a></li>
            <li><a href="q?p=2&v=2">展馆</a></li>
            <li><a href="q?p=3&v=2">预约</a></li>
            <li><%if(in) {%><a href="to?v=2">二维码</a><%}else{%><a href="q?p=4&v=2">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
        <div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 意见反馈</div>
        <div id="thumb"></div>
        <div id="intro">
			<p><%if(request.getAttribute("comment") !=null && (Boolean)request.getAttribute("comment")) { %>
				感谢您的热心参与，我们会仔细考虑您的意见。<br/>
				<a href="q?p=1&v=2">&laquo;返回首页</a>
			<%}%></p>
            <h2>留下您宝贵的意见：</h2>
            <p class="phone-reserve">或者<a href="wtai://wp/mc;10086">打电话</a>告诉我们</p>
            <form action="comment" method="post">
            	<div>您的姓名:<input name="name" type="text" id="name"/></div>
            	<div>手机号:<input type="text" name="phonenumber" id="phonenumber"/></div>
            	<div>意见或建议：<textarea name="content" rows="3"></textarea></div>            	
            	<input type='hidden' name='v' value='3'/>
	            <input type='hidden' name='p' value='9'/>
	            <input type="submit" value="提交"/>
	        </form>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=9&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=9&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=3">公司介绍</a>|<a href="q?p=8&v=3">服务条款</a>|<a href="q?p=9&v=3">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>