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
<title>服务营销区 | 杭州移动信息化体验馆</title>
<link href="css/g3.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/g3.css" rel="stylesheet" type="text/css" media="handheld" />
<script type="text/javascript" src="css/g3.js"></script>
</head>
<body onload="<%=in?"Action.checkQrcode();":"" %>">
<div id="wrap">
    <div id="header"><img src="img/ewap/logo.jpg" width="228" height="30" alt="移动信息化体验馆" /></div>
    <div id="nav">
        <ul>
            <li><a href="q?p=1&v=3">首页</a></li>
            <li><a href="q?p=2&v=3">展馆</a></li>
            <li><a href="q?p=3&v=3">预约</a></li>
            <li><%if(in) {%><a href="q?p=13&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; 服务营销区介绍</div>
        <%if(in && showCode) {%>
        <div class="thumb">
        <img id='qrcode' src="<%="qrcodePic/"+qrcodePic%>" alt="二维码" style="width:136px;height:136px;"/>
        </div>
        <%} %>
        <div class="thumb">        
        <img id='hpic' src="img/ewap/gallery_home.jpg" alt="展馆图片" style="width:206px;height:132px;"/>
        </div>
        <div id="intro">
            <h2>服务营销区简介</h2>
            <p>营销服务犹如政府部门与企业的对外喉舌，在信息时代，一切够沟通渠道解释企业营销宣传的媒体，谁能全新利用谁就抢占先机。中国移动杭州分公司针对政府、企业在搭建多样化的企业营销文化宣传平台的需求，提供一揽子解决方案。利用移动的语音、网络、信息等各种渠道，为其提供营销推广及文化宣传的平台，其中代表性的应用有12580、集团彩铃一级WEB和WAP建站等。通过多种网络的覆盖，帮助政府企业打倒最大化的宣传效应。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=131&v=3"><li>集团彩铃</li></a>    		
        	<a href="q?p=132&v=3"><li>手机搜索</li></a>    		
        	<a href="q?p=133&v=3"><li>12580</li></a>    		
        	<a href="q?p=134&v=3"><li>商旅通</li></a>    		
        	<a href="q?p=135&v=3"><li>IVVR</li></a>    		
        	<a href="q?p=136&v=3"><li>CallCenter</li></a>    		
        	<a href="q?p=137&v=3"><li>企业建站</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=13&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=13&v=1">简版</a>&gt;</p>
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