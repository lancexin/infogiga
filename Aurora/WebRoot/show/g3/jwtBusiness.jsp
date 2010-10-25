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
<title>警务通 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=125&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=12&v=3">生产控制区介绍</a>&raquo; 警务通</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p> 公安系统中外勤人员居多，他们担负着人口管理，地区治安管理，案件侦破，紧急突发事件处理等重要的工作，这些工作的性质和特点决定了他们需要在非办公环境对信息数据进行查询和处理。通过移动网络完成信息调配和警力调度，在实时和快速反应上有着不可替代的优势。
 1.警务通的面向对象：责任区民警、火警、巡查大队、刑侦人员、防暴大队、交警
 2.警务通应用的技术：GPRS/SMS实现警员与后台系统的互动实现信息传递、AGPS/GPS/Cell ID实现混合的全方位定位支持、MIPU语音鉴权实现语音定位、后台管理操作/安全解决方案实现GIS/信息公告
 3.功能：
 警务管理部分：控制命令下发、信息通告、执勤管理、统计分析、协同作战、信息管理
 民警部分：人口信息查询、纠纷调解
 交警部分：快速事故处理、辅助事故处理、车辆排查、交通肇事人员查询
 刑警部分：在逃人员查询、案件处理
 全面警务协同工作部分：协同作战、信息管理
 前台即终端部分，主要包括手机和一些必需的外部附件设备，例如便携式打印机、便携式扫描枪等。移动警务系统分为高级版版本(主要应用于高端手机)和普及版版本（主要应用于中低端手机），以适应用户不同的终端选择。
 后台即移动警务的信息来源，主要是公安内部网络系统。通过中间件连接数据库，进行各种业务处理。（案例：杭州市公安局）</p>
            <p class="more"><a href="q?p=12&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=125&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=125&v=1">简版</a>&gt;</p>
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