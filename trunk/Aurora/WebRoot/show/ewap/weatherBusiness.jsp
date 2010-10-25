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
<title>气象水文监测 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=12&v=2">生产控制区介绍</a>&raquo; 气象水文监测</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>气象水文预报关系老百姓生命财产安危，对气象水文信息的及时采集与监控至关重要，蛋讯多采集监控工作往往人所不能急。移动气象水文监控系统则可以在人所不能到达的地方24小时不间断工作。
水文监控是将采集数据通过无线或有线网络发送到监控系统中，这些数据可以是信号信息、高清晰图片、视频，在系统后台服务器中将数据统计并在前端图文表现出来，通过前端界面对监控结果进行处理。
应用效果安全可靠、先进的技术、快速响应、智能化系统、监控范围打等特点。（案例：浙江省水文局）</p>
            <p class="more"><a href="q?p=12&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=129&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=129&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>