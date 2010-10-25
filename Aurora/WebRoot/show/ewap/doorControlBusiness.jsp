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
<title>智能识别门禁 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=14&v=2">楼宇管理区介绍</a>&raquo; 智能识别门禁</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>智能门禁识别系统使用SIM-RFID或RFID技术实现访客的身份识别、门禁管理，来访纪录，SIM-RFID数据库维护，应急等功能。
业务设分识别主要依赖于手机的普及型，使用SIM-RFID技术将业主身份与SIM卡绑定，作为出入门禁的通行证。相对的，RFID卡作为访客的身份标识，可以在前台等级后派发。
门禁管理（主要指需要门禁的特殊区域）将业主、访客的身份和出入权限挂钩，利用门禁感应器和RFID主动识别技术决定是否解除门禁。非许可用户试图进入的行为可以引起不同级别的报警时间，如短信、警铃、摄像等。
来访纪录将在一定时间内纪录来访者的信息和行为，对于符合一定要求的访客可以考虑身份标识（RFID）的重用
SIM-RFID数据库维护考虑手机或SIM卡丢失的及时数据更新，删除原有权限并加入新的SIM-RFID关联信息。</p>
            <p class="more"><a href="q?p=14&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=141&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=141&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>