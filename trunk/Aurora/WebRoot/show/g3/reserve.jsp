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
<title>预约 | 杭州移动信息化体验馆</title>
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
            <li><%if(in) {%><a href="q?p=3&v=3&sc=<%=showCode?0:1%>">二维码</a><%}else{%><a href="q?p=4&v=3">登录</a><%} %></li>
        </ul>
    </div>
    <div id="content">
        <div id="crumb-nav" class="small"><a href="q?p=1&v=3">首页</a>&raquo; 预约</div>
        <div id="thumb"></div>
        <div id="intro">
            <p><%if(request.getAttribute("reserve") !=null && (Boolean)request.getAttribute("reserve")) { %>
				预约成功，我们将在3个工作日内处理您的请求。<br/>
				<a href="q?p=1&v=2">&laquo;返回首页</a>
			<%}%></p>
            <h2>请填写预约申请表：</h2>
            <p class="phone-reserve">或者通过<a href="wtai://wp/mc;10086">电话</a>预约</p>
            <form action="app" method="post">
            <ul class="form-list">
            	<li><label for="name">您的姓名</label><input name="name" type="text" id="name" /></li>
                <li><label for="phonenumber">手机号</label><input type="text" name="phonenumber" id="phonenumber" /></li>
                <li><label for="company">公司名</label><input type="text" name="company" id="company" /></li>
                <li><label for="name">申请理由</label><textarea name="reason" rows="3" id="reason" ></textarea></li>
                <li class="form-list-button"><input type="submit" value="确认提交"/></li>
            </ul>
            <input type='hidden' name='v' value='3'/>
            <input type='hidden' name='p' value='3'/>
            </form>
        </div>
    </div>
	<div id="version">
		<p>&lt;<a href="q?p=3&v=2">彩版</a>|<span class="bk">G3版</span>|<a href="q?p=3&v=1">简版</a>&gt;</p>
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