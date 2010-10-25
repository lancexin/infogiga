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
<title>保险行业彩信定损 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=12&v=2">生产控制区介绍</a>&raquo; 保险行业彩信定损</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>保险行业是我国市场经济体系的重要组成部分，随着国际化竞争地加剧，对内降低组织的摩擦力，提升公司的运营效率；对外实现与客户更友好的沟通，提升服务水平，对客户需求给予迅速回应成为行业亟待解决的战略问题。而优化业务与管理流程在技术层面要更多地借助信息技术，特别使快速发展的移动通信技术为保险行业提升管理和服务水平提供了有效手段。
 保险行业移动通信解决方案是基于移动通信技术，通过客户报警定位、彩信定损、手机支付等手段快速进行保险定损、理赔支付等工作，为保险行业从业务到管理的移动信息化提供了更为便捷的技术条件。（案例：杭州市人民保险公司）</p>
            <p class="more"><a href="q?p=12&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=123&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=123&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>