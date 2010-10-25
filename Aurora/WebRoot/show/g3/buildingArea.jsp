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
<title>楼宇管理区 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=14&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; 楼宇管理区介绍</div>
        <%if(in && showCode) {%>
        <div class="thumb">
        <img id='qrcode' src="<%="qrcodePic/"+qrcodePic%>" alt="二维码" style="width:136px;height:136px;"/>
        </div>
        <%} %>
        <div class="thumb">        
        <img id='hpic' src="img/ewap/gallery_home.jpg" alt="展馆图片" style="width:206px;height:132px;"/>
        </div>
        <div id="intro">
            <h2>楼宇管理区简介</h2>
            <p>楼宇智能化是信息化的重要组成部分，在现代的西方发达国家，楼宇智能化早已兴起，但在我国，楼宇智能化还是近几年才出现的新鲜事物，中国移动杭州分公司根据形势的发展需要，及时组织各项研发力量，结合目前的实际国情，常识性的开展楼宇智能化这一新课题的应用。在功能方面具有代表性的就是智能识别门禁及无线视频监控。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=141&v=3"><li>智能识别门禁</li></a>    		
        	<a href="q?p=142&v=3"><li>无线视频监控</li></a>    		
        	<a href="q?p=143&v=3"><li>单兵监控</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=14&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=14&v=1">简版</a>&gt;</p>
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