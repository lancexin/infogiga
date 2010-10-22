<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 	<form action="/list?employee" method="get" id="queryTeam">
			<table style="text-align: left;margin-left: 10px;margin-top: 10px">
				
				<tr>
					<td style="text-align: left;">电话号码：</td>
					<td style="text-align: left;"><input type="text" name="phoneNumber"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">员工姓名：</td>
					<td style="text-align: left;"><input type="text" name="empName"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">员工编号：</td>
					<td style="text-align: left;"><input type="text" name="empNo"/></td>
				</tr>

				<tr>
					<td style="text-align: left;" colspan="2">注册时间：</td>
				</tr>
				<tr>
					<td style="text-align: left;">开始时间：</td>
					<td style="text-align: left;"><input type="text" name="startTime" id="startTime"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">结束时间：</td>
					<td style="text-align: left;"><input type="text" name="endTime" id="endTime"/></td>
				</tr>
				<tr>
					<td style="text-align: left;"><input type="button" onclick="querySubmit()" value="查询"/></td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			function querySubmit(){
				

				var phoneNumber = $('#queryTeam [name="phoneNumber"]').val();
				var empName = $('#queryTeam [name="empName"]').val();
				var empNo = $('#queryTeam [name="empNo"]').val();
				var teamId = $('#queryTeam [name="teamId"]').val();
				var startTime = $('#queryTeam [name="startTime"]').val();
				var endTime = $('#queryTeam [name="endTime"]').val();

				if(phoneNumber== ''){
					phoneNumber= "-";
				}
				if(empName == ''){
					empName= "-";
				}
				if(empNo == ''){
					empNo= "-";
				}
				if(startTime == ''){
					startTime= "-";
				}
				if(endTime == ''){
					endTime= "-";
				}
				
				var prames = empName+","+empNo+","+phoneNumber+","+teamId+","+startTime+","+endTime;
				//alert(prames);
				window.location="<%=basePath%>list?employee&prames="+prames;
			}
			$("#startTime").datepicker({ 
	     		picker: "#startTime", 
	     		showtarget: $("#startTime")
	    	});      
			$("#endTime").datepicker({ 
	     		picker: "#endTime", 
	     		showtarget: $("#endTime")
	    	}); 
		</script>
