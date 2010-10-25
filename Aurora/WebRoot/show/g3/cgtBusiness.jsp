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
<title>城管通 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=127&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=12&v=3">生产控制区介绍</a>&raquo; 城管通</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>城市信息化管理即通过有效的移动通信手段和相应的管理支撑网元实现对城市管理的信息化和电子化，通过有效的技术手段保障管理部门可以对整个城市的状况进行实时的监控，并在第一时间根据城市的最新状况进行相应的调度和事务处理，做到“实时了解、快速响应、有效管理”。从而，提升这个城市的管理水平，和进一步提升政府相应的职能部门在人民群众中的形象。
“杭州市数字城市管理信息系统”分为移动巡查子系统和信息采集子系统两个子系统，该系统完全基于中国移动的GSM/GPRS网络环境，并能够实现该系统平滑升级到3G网络，以确保系统的平滑过渡。该系统和定位网络系统具有较强的扩展能力和延伸能力，基于手机GIS系统功能模块，可以根据实际应用需求对系统进行优化和平滑升级到高精度定位应用系统。
根据不同的权限管理共实现了以下功能：用户管理、问题上报、个人任务历史记录、信息提示、语音功能、短信呼叫、拍照录音、表单填写、报表自动生成、地图实时获取、地图浏览、GPS定位、查询及统计、打印功能、数据同步、智能报警、公共信息查询、公文查询、人员查询、系统设置、使用帮助（案例：杭州市行政执法大队）</p>
            <p class="more"><a href="q?p=12&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=127&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=127&v=1">简版</a>&gt;</p>
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