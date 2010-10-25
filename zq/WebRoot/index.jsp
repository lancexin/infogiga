<%@ page language="java" import='bean.UserBean' pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	UserBean user = (UserBean)session.getAttribute("user");	
	String url = (String) session.getAttribute("url");	
	
	if(user == null) {	
	%>
	<jsp:forward page='admin/errorPage.jsp'>
		<jsp:param name='message' value='login'/>
	</jsp:forward>
	<%
	} else if(user.getAuthority() > 1) {
	%>
	<jsp:forward page='admin/errorPage.jsp'>
		<jsp:param name='message' value='authority'/>
	</jsp:forward>
	<%
	} else {	
		int authority = user.getAuthority();
		url = url == null? "admin/search.jsp":url;
%>
<html>
  <head>
    <title>政企体验馆</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="首页">
	
	<link rel="shortcut icon" href="favicon.ico" />
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
	<script type='text/javascript' src='js/swfobject.js'></script>
	<style>
		#page {
			width:100%;
			height:500px;
		}
		body {
			margin-bottom:0px;
		}
	</style>
  </head>
  
  <body>
  	<div id='banner'>
  		<div id='logo'>
  		
  		</div>
	   	<div id='navigation'>
			<ul style="margin:0;padding:0">
		    	<li class="toolbar" onclick="which(1)">体验查询</li>
		        <li class="toolbar" onclick="which(2)">保险定损</li>
		        <li class="toolbar" onclick="which(3)">设备维护</li>
		        <li class="toolbar" onclick="which(4)">浙江移动</li>
		    </ul>
		</div>
	</div>
	<iframe src='<%=url%>' id='page' name='page' frameborder='0'></iframe>
	<form action="" method='post' name='form0'>
		<input type='hidden' name='link'/>
	</form>	
  </body>
  <script language='vbscript'>
	  Sub myFlash_FSCommand(ByVal command, ByVal args)
	  call myFlash_DoFSCommand(command, args)
	  end sub
  </script>
  <script type='text/javascript'>
  	var authority = <%=authority%>;
  	$(document).ready(function() {
  		//loadFlash();
  		ajaxInit();
  	});
  	

   function which(index) {
	switch(index) {
		case 1:$('#page').attr('src', 'admin/search.jsp');updateCurrentUrl('admin/search.jsp');break;
		case 2:checkAuthority(0);$('#page').attr('src', 'bx/list');updateCurrentUrl('bx/list');break;
		case 3:checkAuthority(0);$('#page').attr('src', 'admin/master.jsp');updateCurrentUrl('admin/master.jsp');break;
		case 4:window.open("http://www.zj.chinamobile.com");break;
		case 5:openNew();break;
		case 6:window.open("/zq/music");break;
		case 7:checkAuthority(0);$('#page').attr('src', 'admin/master.jsp');updateCurrentUrl('admin/master.jsp');break;
		case 8:checkAuthority(0);$('#page').attr('src', 'admin/set.jsp');updateCurrentUrl('admin/set.jsp');
	}
   }
   
   //打开链接url，先在后台判断权限
   function updateCurrentUrl(url) {
   		$.post('/zq/door', 'type=updateLink&url='+url);
   }
   
   //权限检查
   function checkAuthority() {
   		var level = arguments[0]=='undefined'?1:arguments[0];//默认级别1
   	
   		if(authority > level) {
   			window.location='error.jsp?message=authority';   						   			
   		}
   }
   
   //ajax初始化
   	function ajaxInit() {
   		$.ajaxSetup({
   			type:'post',
   			cache:false,
   			async:true
   		})
   	}
   
   function openNew() {
   		
   }
  </script>
</html>
<%
}%>
