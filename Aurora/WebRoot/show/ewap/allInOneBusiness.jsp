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
<title>一号通 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=11&v=2">移动办公区介绍</a>&raquo; 一号通</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>留了手机还要固话——无需再留多个号码，被叫一号通，各种终端同时振铃或顺序振铃，一次性找到你。
手机、固话号码不一，影响形象——无论你用固话、手机还是PC终端呼叫，对方只看到统一的号码，提升统一形象。
电话接不过去——可以用固定电话、手机、PC接听，情景模式呼叫转移，方便接听。（案例：浙江中大集团股份有限公司）</p>
            <p class="more"><a href="q?p=11&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=114&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=114&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>