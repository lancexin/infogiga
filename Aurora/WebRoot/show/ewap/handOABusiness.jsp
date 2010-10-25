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
<title>移动OA | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=11&v=2">移动办公区介绍</a>&raquo; 移动OA</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>移动OA是移动办公的一项重要内容，它将原有OA系统上的公文、通讯录、日程、文件管理、通知公告等功能迁移到手机，让您可以随时随地进行掌上办公，对于突发性事件和紧急性事件有极其高效和出色的支持，是管理者、市场人员等贴心的掌上办公产品。
 移动OA是通过部署MAS服务器的方式与集团客户原有的OA系统进行耦合，主要为集团客户提供基于移动无线网络访问单位内部OA系统的解决方案。客户能够通过移动终端随时随地地接入公司或者单位内部的OA系统，实现公文处理、邮件提醒和集团通讯录等的业务流程。移动OA既可以有效改善企业、政府的办公环境，简化机构之间的沟通方式，又可以有效改善企业、政府的办公接入方式。外出人员通过手机，就可随时随地进行公文处理，完成移动公文审批、移动信息查询等工作。（案例：太平洋人寿保险公司）</p>
            <p class="more"><a href="q?p=11&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=117&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=117&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>