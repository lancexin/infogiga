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
<title>登录 | 杭州移动信息化体验馆</title>
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
        <div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 登录</div>
        <div id="thumb"></div>
        <div id="intro">
            <form action="q?login" method="post">
        	<%
        		Object info = request.getAttribute("loginInfo");
        		boolean success = true;
        		if(info != null) {success = (Boolean)info;}
        		if(!success) { 
        	 %>
        	 	<p>登录失败，请重新登录</p>
        	 <%} %>
            <ul class="form-list">
            	<li><label for="username">二维码</label><input name="code" size="20" id="username" /></li>
                <li><label for="password">密码</label><input name="pwd" size="20" id="password" /></li>
                <li class="form-list-button"><input type="submit" value="登录"/></li>
            </ul>
            <input type="hidden" name="v" size="0" value="2"/>
            </form>
            <h2>登录帮助：</h2>
            <p>
            	二维码示例：<br/>ZMCC_GE_AM00000W<br/>
            	密码长度为5位
            </p>            
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=4&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=4&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>