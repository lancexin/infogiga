<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
	Object obj = session.getAttribute("qrcodePic");
	Object obj2 = session.getAttribute("showCode");
	boolean in = false;
	boolean showCode = true;
	String qrcodePic = "";
	if(obj != null) {
		in = true;
		qrcodePic = (String) obj;
	}
	if(obj2 != null) {
		showCode = (Boolean)obj2;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="max-age=300"/>
<title>无线视频监控 | 杭州移动信息化体验馆</title>
<link href="css/g3.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/g3.css" rel="stylesheet" type="text/css" media="handheld" />
<script type="text/javascript" src="css/g3.js"></script>
</head>
<body>
<div id="wrap">
    <div id="header"><img src="img/ewap/logo.jpg" width="228" height="30" alt="移动信息化体验馆" /></div>
    <div id="nav">
        <ul>
            <li><a href="q?p=1&v=3">首页</a></li>
            <li><a href="q?p=2&v=3">展馆</a></li>
            <li><a href="q?p=3&v=3">预约</a></li>
            <li><%if(in) {%><a href="q?p=142&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=14&v=3">楼宇管理区介绍</a>&raquo; 无线视频监控</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>无线视频监控充分利用现有的无线基站和网络资源，系统安装方便，开通快捷，无需进行布线，节约实施费用、工程建设周期短。前端设备可任意部署在有网络信号的地方，不受距离限制、监控范围广、扩充能力强；传输距离远、低时延（毒视频应用非常关键）、系统软件和前端设备价格低廉，容易推广普及，可以提供高效和经济的视频传输解决方案。所选无线网络稳定可靠、传输速率高、实时性强，适于大规模部署；从硬件到软件均为本公司设计、生产，有能力安装客户需求和行业需求进行定制。
无线视频监控系统由四大部分组成：传输网络、前端设备、中心后台、监控终端，该系统整合了TD-SCDMA网络和INTERNET网络的优势，无论您身在何处、任何时间、都可以迅速接入系统，随时随地的进行远程监控管理。（案例：富阳港航局）</p>
            <p class="more"><a href="q?p=14&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=142&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=142&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=3">公司介绍</a>|<a href="q?p=8&v=3">服务条款</a>|<a href="q?p=9&v=3">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body>
</html>