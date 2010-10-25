 <%@ page language="java" import='bean.UserBean' pageEncoding="UTF-8"%>
 
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>登录-政企体验馆</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="login">
		
		<link rel="shortcut icon" href="favicon.ico" />
		<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<link rel="stylesheet" type="text/css" href="css/login.css">
	</head>
 
	<body>
		<div id='define' >
			<div id='blank'></div>
			<div id='box'>
				<table style="heigth:300px;">
					<tr>
						<td>
						<table id="formTable">
							<tr class="trTable">
								<td >用户名：</td>
								<td style="height:30px;"><input type="text" name="userName" id='userName' /></td>
							</tr>
							
							<tr class="trTable">
								<td >密码：</td>
								<td style="height:30px;"><input type="password" name="userName" id='password' /></td>
							</tr>
						</table>
						</td>
						<td style="padding-left:30px;border:0;">
							<img src="img/images_7.gif" onclick="login()">
						</td>
					</tr>
					<tr style="heigth:35px;">
						<td colspan="2"><div class='stateBlock'></div></td>
					</tr>
				</table>
				
			</div>
			
		</div>
	</body>
</html>

