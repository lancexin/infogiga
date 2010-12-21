<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>M-Store用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="jquery/jquery-1.3.min.js"></script>

<style type="text/css">
<!--
#mstore-login-containner {
	height: 356px;
	width: 637px;
	margin-right: auto;
	margin-left: auto;
	background-image: url(images/mstore_login_bg.png);
}
#mstore-form-containner {
	height: 150px;
	width: 320px;
	color: #999;
	font-size: 16px;
	margin-right: auto;
	margin-left: auto;
	padding-top: 200px;
}
.login-btn-text {
	cursor:pointer;
}

.login-btn-text:active{
	color: #FFF;	
}
.login-form-input {
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	height: 28px;
	width: 196px;
	font-size: 24px;
	margin-top: 2px;
	background-color: #c8cacf;
}


#login-form {
	font-size: 24px;
	font-weight: bold;
	color: #CCC;
	text-align: center;
}
.login-form-submit-containner {
}
.login-form-submit-btn {
	margin-top: 20px;
	font-size: 36px;
	color: #000;
}
.login-form-username {
	background-image: url(images/mstore_login_input.png);
	height: 35px;
	width: 208px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	margin-right: 5px;
	margin-left: 5px;
	padding: 0px;
}
-->
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$(".login-btn-text").click(function(){
			var date = $('form[id="login-form"]').serialize();
			$.post("login?type=json",date,function(html){
				eval("el = "+html);
				if(el.success){
					document.location = "web";
				}else{
					alert(el.msg);
				}
			});
		});
	});
</script>
</head>

<body>
<div id="mstore-login-containner">
<div id="mstore-form-containner">
    	<form id="login-form" action="login?type=json" method="post">
        	<table>
            	<tr>
                	<td >账户：</td>
                    <td><div  class="login-form-username"><input class="login-form-input" type="text" name="userName"/></div></td>
                </tr>
                <tr>
                	<td>密码：</td>
                    <td><div  class="login-form-username"><input class="login-form-input" type="password" name="passWord"/></div></td>
                </tr>
            </table>
        	
       		<div class="login-form-submit-containner">
            	<div class="login-form-submit-btn"><span class="login-btn-text">登录</span></div>
            </div>
            
        </form>
    </div>
</div>

</body>
</html>

