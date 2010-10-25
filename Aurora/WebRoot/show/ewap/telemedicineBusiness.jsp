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
<title>远程医疗 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=12&v=2">生产控制区介绍</a>&raquo; 远程医疗</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>远程医疗（Telemedicine）通常包括：远程诊断、专家会诊、信息服务、在线检查和远程交流等几个主要部分，它以计算机和网络通信为基础，实现对医学资料和远程视频、音频信息的传输、存储、查询、比较、显示及共享。 
　　远程医疗是指通过计算机技术、通信技术与多媒体技术，同医疗技术相结合，旨在提高诊断与医疗水平、降低医疗开支、满足广大人民群众保健需求的一项全新的医疗服务。
 广泛地说，远程医疗是电子医务数据从一个地方到另一个地方的传输，这些数据包括高清晰度照片、声音、视频和病历。这种数据传输将利用许多通信技术，包括普通的电话、ISDN、部分或整个IT专线、ATM、Internet、Intranet和卫星等。远程医疗正日益渗入到医学的各个领域，包括：皮肤医学、肿瘤学、放射医学、外科手术、心脏病学、精神病学和家庭医疗保健等，说的通俗一点就是,医生和患者在不同的地方,医生通过各种科技媒介来获知患者病情,并为患者提供医疗服务。
 我国远程医疗的开展及现状：广州远洋航运公司自1986年对远洋货轮船员急症患者进行了电报跨海会诊，有人认为这是我国最早的远程医疗活动。伴随计算机及通信技术的发展，我国现代意义的远程医疗活动开始于80年代。在卫生部直接领导和有关部委的支持下，中国金卫医疗网络即卫生部卫生卫星专网于1997年7月正式开通。金卫医疗网络全国网络管理中心在北京成立并投入运营。</p>
            <p class="more"><a href="q?p=12&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=128&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=128&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>