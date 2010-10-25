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
<title>生产控制区 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=12&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
   		<div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; 生产控制区介绍</div>
        <%if(in && showCode) {%>
        <div class="thumb">
        <img id='qrcode' src="<%="qrcodePic/"+qrcodePic%>" alt="二维码" style="width:136px;height:136px;"/>
        </div>
        <%} %>
        <div class="thumb">        
        <img id='hpic' src="img/ewap/gallery_home.jpg" alt="展馆图片" style="width:206px;height:132px;"/>
        </div>
        <div id="intro">
            <h2>生产控制区简介</h2>
            <p>商品生产、公共服务管理工程企业与政府的活动中心，在其间引入信息化应用将大大帮助政府企业提高生产效率、服务职能。中国移动杭州分公司专门针对政府企业在生产、作业、管理过程中的生产传输、安全生产、传感监控、位置服务四个环节提供信息化解决手段、缩短生产流程中的信息传递、加快企业作战反应，提高非办公环境下政府服务水平等，至今移动信息化已在农业、医疗、佳偶、金融、交通物流等各行业中取得成功应用。</p>            
        </div>
    </div>
    <div id="bottom-nav">
    	<ul>
        	<a href="q?p=120&v=3"><li>行业语音报警</li></a>    		
        	<a href="q?p=121&v=3"><li>金融短信密码认证</li></a>    		
        	<a href="q?p=122&v=3"><li>机房自动监控</li></a>    		
        	<a href="q?p=123&v=3"><li>保险行业彩信定损</li></a>    		
        	<a href="q?p=124&v=3"><li>政府行业应用介绍</li></a>    		
        	<a href="q?p=125&v=3"><li>警务通</li></a>    		
        	<a href="q?p=126&v=3"><li>税务通</li></a>    		
        	<a href="q?p=127&v=3"><li>城管通</li></a>    		
        	<a href="q?p=128&v=3"><li>远程医疗</li></a>    		
        	<a href="q?p=129&v=3"><li>气象水文监测</li></a>    		
        	<a href="q?p=12a&v=3"><li>公交调度</li></a>    		
        </ul>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=12&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=12&v=1">简版</a>&gt;</p>
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