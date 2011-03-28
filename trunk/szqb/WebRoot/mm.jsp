<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery/jquery-1.3.min.js"></script>
<script type="text/javascript" src="jquery/jquery.cookie.js"></script>
<script type="text/javascript">

	$(function(){
		$.cookie('us','120.199.13.26.1299045951327390');
		$.cookie('userLogontype','sms');
		alert($.cookie('userLogontype'));
	});
</script>

</head>
<body>
<h2>获得手机验证码：</h2>
<form action="http://mm.10086.cn/portal/web/SmsRandomSendAction.do" method="get" target="a1">
	手机号码：<input name="msisdn" type="text"/><br/>
	<input name="type" type="hidden" />
	<input type="submit" value="提交"/>
</form>
<iframe id="a1" name="a1"></iframe>
<hr/>
<h2>检查验证码是否正确：</h2>
<form action="http://mm.10086.cn/portal/web/checkLoginAction.do" method="get" target="a2">
	手机号码：<input name="msisdn" type="text"/><br/>
	password:<input name="password" type="text" value="" /><br/>
	type:<input name="type" type="text" value="login" /><br/>
	<input type="submit" value="提交"/>
</form>
<iframe id="a2" name="a2"></iframe>
<h2>下载确认：</h2>
<form action="http://mm.10086.cn/portal/web/orderConfirmAction.do" method="post" target="a2">
	手机号码：<input name="msisdn" type="text"/><br/>
	c：<input name="c" type="text" value="300001135978"/><br/>
	devicename：<input name="devicename" type="text" value="NokiaNokia 5320"/><br/>
	downloadType：<input name="downloadType" type="text" value="mobile"/><br/>
	p:<input name="p" type="text" value="7010" /><br/>
	subtype:<input name="subtype" type="text" value="" /><br/>
	<input type="submit" value="提交"/>
</form>
<iframe id="a2" name="a2"></iframe>
</body>
</html>