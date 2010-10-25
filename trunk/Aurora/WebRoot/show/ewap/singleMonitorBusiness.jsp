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
<title>单兵监控 | 杭州移动信息化体验馆</title>
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
    	<div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; <a href="q?p=14&v=2">楼宇管理区介绍</a>&raquo; 单兵监控</div>
        <div id="thumb"></div>
        <div id="intro">
            <h2>业务介绍</h2>
            <p>3G便携背负式无线应急指挥视频监控终端，此阿勇仙剑的NMVS-UH，264视频压缩算法、流媒体视频处理技术，呵呵了EDGE数据通讯功能，可靠的无线传输技术和自适应心通捆绑技术。该系统可把摄像机采集到的图像，经视频压缩编码，通过EDGE智能无线通讯模块，把实时动态图像传输到距离用户最近的移动EDGE无线网络，用户可方便地通过Internet/Intranet访问中心管理服务器，通过计算机、PDA和监控指挥中心监控实时图像。在总体上实现了视频数据的编解码、加解密、交互、发送/接收和远程控制等功能。                                                                               3G便携背负式无线应急指挥视频监控系统是一套完善、高效、移动性极强且性价比极高的远程无线视频管理熊，该系统融合了中国移动公司EDGE数据通讯和H.264数字视频编码功能，并进行优化；利用中国移动公司的EDGE无线网络和Internet网络的优势，使您无论在何时、何地都可以迅速的与该系统连接，方便地实现远程监控管理。主教御用视频监控、远程视频监控、无线网络视频监控、视频监控管理、视频监控应急指挥。                                                                      3G便携背负式无线应急指挥视频监控终端，2卡/4卡EDGE无线视频传输，图像清晰连贯，内置锂电池组，支持双向语音对讲，D1图片抓拍和紧急呼叫按钮，及时捕捉关键影像，为决策者提供远程指挥的第一手资料。可选多卡EDGE/3G的无线网络，内置大容量锂电池组，供电时间大于8小时。支持本地显示、云台控制功能。（案例：杭州市城管）</p>
            <p class="more"><a href="q?p=14&v=2">&laquo; 返回上层</a></p>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=143&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=143&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>