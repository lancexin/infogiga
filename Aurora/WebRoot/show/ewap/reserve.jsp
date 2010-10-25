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
	request.setCharacterEncoding("UTF-8");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="max-age=300"/>
<title>预约 | 杭州移动信息化体验馆</title>
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
        <div id="crumb-nav" class="small"><a href="q?p=1&v=2">首页</a>&raquo; 预约</div>
        <div id="thumb"></div>
        <div id="intro">
			<p><%if(request.getAttribute("reserve") !=null && (Boolean)request.getAttribute("reserve")) { %>
				预约成功，我们将在3个工作日内处理您的请求。<br/>
				<a href="q?p=1&v=2">&laquo;返回首页</a>
			<%}%></p><br/>
            <h2>请填写预约申请表：</h2>
            <p class="phone-reserve">或者通过<a href="wtai://wp/mc;10086">电话</a>预约</p>
            <form action="app" method="post">
            <ul class="form-list">
            	<li><label for="name">您的姓名</label><input name="name" type="text" id="name" emptyok="false"/></li>
                <li><label for="phonenumber">手机号</label><input type="text" name="phonenumber" id="phonenumber" emptyok="false"/></li>
                <li><label for="company">公司名</label><input type="text" name="company" id="company" /></li>
                <li><label for="name">申请理由</label><textarea name="reason" rows="3" id="reason" ></textarea></li>
                <li class="form-list-button"><input type="submit" value="确认提交"/></li>
            </ul>
	            <input type='hidden' name='v' value='2'/>
	            <input type='hidden' name='p' value='3'/>
            </form>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=3&v=3">G3版</a>|<span class="bk">彩版</span>|<a href="q?p=3&v=1">简版</a>&gt;</p>
	</div>
    <div id="widget">
        <p><a href="q?p=7&v=2">公司介绍</a>|<a href="q?p=8&v=2">服务条款</a>|<a href="q?p=9&v=2">意见反馈</a></p>
    </div>
    <div id="footer">
        <p>&copy; 2010 中国移动杭州分公司</p>
    </div>
</div>
</body></html>