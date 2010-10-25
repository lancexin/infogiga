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
<title>数据网 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="to">二维码</a><%}else{%><a href="q?p=4&v=2">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=15&v=2">基础网络区介绍</a>&raquo; 数据网</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>CMNET是一个全国性的，以宽带IP为技术核心，同时提供语音、传真、数据、图像、多媒体等服务的电信基础网络，接入号是“172”。                                                               应用技术：CMNET从网络结构上可分为骨干网和省网，网络以IP over DWDM为核心技术，现已覆盖全国所有省市。CMNET向固定互用和移动用户在内的所有用户提供IP电话、电子邮件、FTP业务，Web业务、电子商务等业务，捅死根据移动用户的特点逐步推出WAP业务，基于位置信息的业务，短信息结合业务等具有移动特色的因特网服务。                                                           应用功能:●IP电话、●固定拨号用户上网、●无线拨号用户上网（含WAP上网）、●IDC业务、●宽带批发、●IP长途中继传输、●虚拟专用网、●专线用户上网、●移动梦网短信业务。</p>
            <p class="more"><a href="q?p=15&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=151&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=151&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>