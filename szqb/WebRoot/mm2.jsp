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

	
	function getSmsCode(){
		$.post("http://mm.10086.cn/portal/web/SmsRandomSendAction.do?msisdn=15268114857&type=", function(data){
		  alert(data);
		});
	}
	
	
	function clickLoginAction(){
		var password = $("#pwd").val();
		$.post("http://mm.10086.cn/portal/web/checkLoginAction.do?msisdn=15268114857&type=login&password="+password, function(data){
		  alert(data);
		});
	}

</script>

</head>
<body>
<form action="http://mm.10086.cn/portal/web/SmsRandomSendAction.do" method="get">
	手机号码：<input name="msisdn" type="text"/><br/>
	<input name="type" type="text"/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>