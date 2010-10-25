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
<title>金融短信密码认证 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=121&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=12&v=3">生产控制区介绍</a>&raquo; 金融短信密码认证</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>伴随着电子商务的发展，现代银行业务综合化、全能化趋势的形成，银行卡交易量日趋增多，跨行交易、异地交易逐步增多，银行卡的安全性越来越受到关注，客户需求也开始朝多元化、个性化的方向发展。银行竞争的焦点也集中到能否为客户提供全方位的个性化服务，能否不断提升客户的忠诚度和满意度，能否科学准确地细分市场并快速推出易于接受的服务产品上来。经济的发展使得银行卡逐步变为交易的主要手段，客户迫切需要及时了解卡证资金变化情况，代交费资金扣除情况，刷卡消费、ATM柜员机消费资金变化情况，贷款资金扣除情况等。中国移动提供的短信平台正是满足客户需要，通过及时将卡证资金变化情况通过短信方式通知用户，使得用户做到心中有数。在提高用户的忠诚度和满意度、提高全面的个性化服务以及在银行开展行销方面将发挥极其重要的作用。</p>
            <p class="more"><a href="q?p=12&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=121&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=121&v=1">简版</a>&gt;</p>
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