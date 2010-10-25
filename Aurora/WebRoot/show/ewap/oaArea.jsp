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
<title>移动办公区简介 | 杭州移动信息化体验馆欢迎您</title>
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
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 移动办公区介绍</div>
        <div id="thumb"><img src="<%=in?"qrcodePic/"+qrcodePic:"img/ewap/yidongbangong.jpg"%>" width="<%=in?"136":"210" %>" height="<%=in?"136":"122" %>" alt="展馆图片" /></div>
        <div id="intro">
            <h2>移动办公区简介</h2>
            <p>中国移动杭州分公司政企信息化体验馆总面积为三百平方米，全馆共分为八个区域。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=111&v=2"><li>6A商务理念</li></a>    		
        	<a href="q?p=112&v=2"><li>企业通信助理</li></a>    		
        	<a href="q?p=113&v=2"><li>融合VPN</li></a>    		
        	<a href="q?p=114&v=2"><li>一号通</li></a>    		
        	<a href="q?p=115&v=2"><li>自由行</li></a>    		
        	<a href="q?p=116&v=2"><li>高清视频会议</li></a>    		
        	<a href="q?p=117&v=2"><li>移动OA</li></a>    		
        	<a href="q?p=118&v=2"><li>物联网</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=11&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=11&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>