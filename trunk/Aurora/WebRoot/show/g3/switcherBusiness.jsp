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
<title>自由行 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=115&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; <a href="q?p=11&v=3">移动办公区介绍</a>&raquo; 自由行</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>拨打简单的业务操作码，实现多种终端之间的自由切换。
手机打倒一半快没电——为避免手机电量不足，可将通话从手机切换到身边的固话上。
固话接到一半想离开——又事要离开或私密通话，可将通话从固话切换到手机上。
不是分机，不能切换——提供拍叉功能，通话在校团体内部成员之间切换，提供一站式服务。（案例：珀莱雅化妆品有限公司）</p>
            <p class="more"><a href="q?p=11&v=3">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=115&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=115&v=1">简版</a>&gt;</p>
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