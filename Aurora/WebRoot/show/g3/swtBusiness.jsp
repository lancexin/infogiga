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
<title>税务通 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=126&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=12&v=3">生产控制区介绍</a>&raquo; 税务通</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p> “税务通”移动税务信息系统为税务行业提供一种高效的移动应用解决方案，利用现有移动通信网络基础，通过无线掌上设备实现移动互联和已在全省推广应用的税收征管辅助系统的相关业务应用，构成新一代立体的税务办公网络。移动税务通的整体框架包括税务移动用户、无线数传系统、税务移动应用服务系统、税收征管辅助系统、税务业务应用系统（CTAIS2.0、金税系统、稽查系统、OA等）、系统运行环境系统、安全保障体系、运行管理体系和标准规范体系等九大部分构成。                 移动税务通系统是在CTAIS2.0和税收征管辅助系统基础上的一个无线应用的延伸，是基于以上两个系统的数据和业务，是以上两个系统的功能在手机的上的延伸，项目的实施和主要定位为：以税收征管辅助系统为移动业务应用和数据交互平台。将辅助系统现有的待办事项、纳税人一户式信息、税源管理、税源监控、纳税服务、辅助工具等功能模块，选择性在平移至移动应用业务平台，并结合OA等其它国税业务系统，在此基础上进行“税务通”移动税务信息系统的扩展开发，并使用依托于移动的GPRS网络，进行无线数据的单点传输。（案例：杭州市国税局）</p>
            <p class="more"><a href="q?p=12&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=126&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=126&v=1">简版</a>&gt;</p>
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