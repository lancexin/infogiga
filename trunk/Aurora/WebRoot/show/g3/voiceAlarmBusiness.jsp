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
<title>行业语音报警 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=120&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=12&v=3">生产控制区介绍</a>&raquo; 行业语音报警</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>功能上：当市民使用移动电话拨打特定号码（如：110、119、122）报告紧急情况时，系统可以通过定位平台语音鉴权功能，获取紧急呼叫发起方的位置信息，并支持在一定生存周期范围内（目前暂定于30分钟）获得报警发起方的实时位置信息，以监控中心方便进行警员调动，警员接警后的快速反应，与此同时，实现接警、处警、联动调度、指挥集成为一体的综合指挥系统、实现“集中接警、统一指挥、快速反应、信息共享”，充分响应省市两级政府提出的“平安杭州”和“平安浙江”社会环境，提升市民满意度和对社会的认可度。
技术上：主要通过IT技术、移动通信技术、数据库技术实现整个系统所需的后台处理、通信交互、语音呼叫调度、报表自动生成、手机定位以及一线干警人员的手持设备和相应的管理信息客户端。通过上述4种技术的互动，实现不同区域、不同管理人员、不同管理对象和不同管理主体的信息一体化处理。达到：接处警快速准确性、系统实用可靠性、派出所联动性、科学决策分析。最终通过该语音位置服务系统实现公安部门接处警能力的提升、管理人员评估体系信息化和事务处理效率的提高。（案例：杭州市公安局）</p>
            <p class="more"><a href="q?p=12&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=120&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=120&v=1">简版</a>&gt;</p>
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