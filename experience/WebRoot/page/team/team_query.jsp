<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath%>plugin/dp/dp.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>plugin/jquery-1.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/validate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/dp/jquery.datepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>plugin/Common.js"></script>
	</head>
	<script type="text/javascript">
		function querySubmit(_this){
			var teamName = $('#queryTeam [name="teamName"]').val();
			var startTime = $('#queryTeam [name="startTime"]').val();
			var endTime = $('#queryTeam [name="endTime"]').val();
			alert(teamName.trim()== '');
			var prames = "prames=";
			if((teamName.trim()) == ''){
				prames = prames+"-,"
			}else{
				prames = prames+teamName+",";
			}
			
			ir(startTime.trim()) == ''){
				
			}else{
			
			}
			
			ir(endTime.trim()) == ''){
				
			}else{
			
			}
			
			return false;
		}
		
		$(document).ready(function(){
		
			$("#startTime").datepicker({ 
	     		picker: "#startTime", 
	     		showtarget: $("#startTime")
	    	});      
			$("#endTime").datepicker({ 
	     		picker: "#endTime", 
	     		showtarget: $("#endTime")
	    	});      
		});
		
	
	</script>
	
	<body>
		<form action="/list?team" method="get" id="queryTeam" onsubmit="return querySubmit(this)">
			<table>
				<tr>
					<td>组名：</td>
					<td><input type="text" name="teamName"/></td>
				</tr>
				<tr>
					<td colspan="2">添加时间：</td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td><input type="text" name="startTime" id="startTime"/></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input type="text" name="endTime"  id="endTime"/></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="查询"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
