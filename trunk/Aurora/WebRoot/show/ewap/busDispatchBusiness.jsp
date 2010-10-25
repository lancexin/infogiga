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
<title>公交调度 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=12&v=2">生产控制区介绍</a>&raquo; 公交调度</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>杭州公交调度智能化管理系统（DIMS）系统可以对车辆及线路营运情况，以及营运生产实绩和客运服务质量等进行实时监控，能把相应公交服务信息媒体(如LED发车显示屏、智能电子站牌、声讯台、公交网站、手机短信等)同步传输给乘客，引导乘车、方便乘客，逐步形成公交全网集中、分级监控、责任明确、措施有效客运管理新体系，提高车辆运营效率和效益，提升服务功能与服务质量等方面，发挥了十分重要作用。
把GPS应用于杭州的城市公交车，以定位、监控和调度为主要目的，极大地方便了广大市民的日常公交出行，目前已经实现对全市4000余量公交车进行定位，是全国数字公交项目的领航者，充分凸现了杭州市政府倡导的天堂硅谷的新形象。（案例：杭州市公交总公司）</p>
            <p class="more"><a href="q?p=12&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=12a&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=12a&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>