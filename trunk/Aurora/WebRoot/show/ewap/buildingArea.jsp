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
<title>楼宇管理区简介 | 杭州移动信息化体验馆欢迎您</title>
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
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 楼宇管理区介绍</div>
        <div id="thumb"><img src="<%=in?"qrcodePic/"+qrcodePic:"img/ewap/louyuguanli.jpg"%>" width="<%=in?"136":"210" %>" height="<%=in?"136":"122" %>" alt="展馆图片" /></div>
        <div id="intro">
            <h2>楼宇管理区简介</h2>
            <p>楼宇智能化是信息化的重要组成部分，在现代的西方发达国家，楼宇智能化早已兴起，但在我国，楼宇智能化还是近几年才出现的新鲜事物，中国移动杭州分公司根据形势的发展需要，及时组织各项研发力量，结合目前的实际国情，常识性的开展楼宇智能化这一新课题的应用。在功能方面具有代表性的就是智能识别门禁及无线视频监控。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=141&v=2"><li>智能识别门禁</li></a>    		
        	<a href="q?p=142&v=2"><li>无线视频监控</li></a>    		
        	<a href="q?p=143&v=2"><li>单兵监控</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=14&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=14&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>