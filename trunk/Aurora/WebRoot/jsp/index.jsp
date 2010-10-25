<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Manager manager = (Manager)request.getAttribute("userInfo");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<link type="text/css" href="jqueryui/themes/base/ui.all.css" rel="stylesheet" />
		<link type="text/css" href="css/main.css" rel="stylesheet" />
		<link type="text/css" href="css/dept.css" rel="stylesheet" />

		<script type="text/javascript" src="jqueryui/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.widget.js"></script>
		<script charset="utf-8" type="text/javascript" src="jqueryui/ui/jquery.ui.datepicker.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.tabs.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.dialog.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.position.js"></script>
		<script type="text/javascript" src="jqueryui/ui/jquery.ui.accordion.js"></script>
		<script type="text/javascript" src="jqueryui/main.js"></script>
		<script type="text/javascript" src="jqueryui/config.js"></script>
		<script type="text/javascript" src="jqueryui/bus.js"></script>
		<title>日程管理平台</title>
	</head>
	<body>
		<div id="top">
			<h1 id="HeaderTitle"> 
				al日程管理平台 
			</h1>
			
			<div id="dh">
				<ul>
					<li>
						<a href="javascript:;" title="退出" onclick="Index.showOutDialog();">退出</a>
					</li> 
					<li>
						<a href="javascript:;" title="帮助" onclick="Index.showHelpDialog();">帮助</a>
					</li>
					<li>
						<a href="javascript:;" title="用户名"><%=manager.getName() %></a>
					</li>
				</ul>
			</div>
		</div>
		
		<div id="contain">
			<div id="center">
				<div id="left">
					<div id="datepicker"></div>
				</div>
				<div id="right">
					<div id="maintabs">
						<ul>
							<li>
								<a href="day.do">日</a>
							</li>
							<li>
								<a href="week.do">周</a>
							</li>
							<li>
								<a href="month.do">月</a>
							</li>
							<li>
								<a href="reservation.do">预约日程</a>
							</li>
							<li>
								<a href="request.do">客户请求</a>
							</li>
							<li>
								<a href="feedback.do">意见反馈</a>
							</li>
							<li>
								<a href="setup.do">设置</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="bottom"></div>
		
		
		
		
	<!-- ##############dialog############## -->
		<div id="help_dialog" title="帮助">
			<p>这里是帮助信息</p>
		</div>
				
		<div id="out_dialog" title="退出本系统">
			<p>
				您真的要退出本系统吗？
			</p>
		</div>
		
		<div id="upload_flie_dialog" title="导入文件">
			<form action="upload.up" method="post" enctype="multipart/form-data" target="uploadFrame">
				导入：<input type="file" name="uploadFile" />
				<input type="submit" value="确定" onclick='Next.uploading()'/>
			</form>
			<span style="color:red;" id='uploadStatus'></span>
			<iframe name="uploadFrame" style="display: none;"></iframe>
		</div>
		
		<!-- 快速预约 -->
		<div id="fast_order_dialog" title="快速预约">
			<jsp:include page="fastInvitation.jsp"></jsp:include>
		</div>
		
		<!-- 添加预约 -->
		<div id="add_order_dialog" title="添加预约">
			<jsp:include page="addInvitation.jsp"></jsp:include>
		</div>
		
		<!-- 预约信息 -->
		<div id="select_order_dialog" title="预约信息">
			<jsp:include page="invitationInfo.jsp" flush="true"></jsp:include>			
		</div>
		
	</body>
</html>
