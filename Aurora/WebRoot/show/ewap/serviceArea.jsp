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
<title>服务营销区简介 | 杭州移动信息化体验馆欢迎您</title>
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
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 服务营销区介绍</div>
        <div id="thumb"><img src="<%=in?"qrcodePic/"+qrcodePic:"img/ewap/fuwuyingxiao.jpg"%>" width="<%=in?"136":"210" %>" height="<%=in?"136":"122" %>" alt="展馆图片" /></div>
        <div id="intro">
            <h2>服务营销区简介</h2>
            <p>营销服务犹如政府部门与企业的对外喉舌，在信息时代，一切够沟通渠道解释企业营销宣传的媒体，谁能全新利用谁就抢占先机。中国移动杭州分公司针对政府、企业在搭建多样化的企业营销文化宣传平台的需求，提供一揽子解决方案。利用移动的语音、网络、信息等各种渠道，为其提供营销推广及文化宣传的平台，其中代表性的应用有12580、集团彩铃一级WEB和WAP建站等。通过多种网络的覆盖，帮助政府企业打倒最大化的宣传效应。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=131&v=2"><li>集团彩铃</li></a>    		
        	<a href="q?p=132&v=2"><li>手机搜索</li></a>    		
        	<a href="q?p=133&v=2"><li>12580</li></a>    		
        	<a href="q?p=134&v=2"><li>商旅通</li></a>    		
        	<a href="q?p=135&v=2"><li>IVVR</li></a>    		
        	<a href="q?p=136&v=2"><li>CallCenter</li></a>    		
        	<a href="q?p=137&v=2"><li>企业建站</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=13&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=13&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>