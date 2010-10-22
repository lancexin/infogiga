<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 	<form action="/list?equipment" method="get" id="queryTeam">
			<table style="text-align: left;margin-left: 10px;margin-top: 10px">
				<tr>
					<td style="text-align: left;">设备名称：</td>
					<td style="text-align: left;"><input type="text" name="equipmentName"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">设备mac：</td>
					<td style="text-align: left;"><input type="text" name="mac"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">组名：</td>
					<td style="text-align: left;"><select name="teamId">
						<option value="-">请选择..</option>
						<c:forEach items="${teamList}" var="team" varStatus="count">
							<option value="${team.teamId}">${team.teamName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left;" colspan="2">创建时间：</td>
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
				
				var equipmentName = $('#queryTeam [name="equipmentName"]').val();
				var mac = $('#queryTeam [name="mac"]').val();
				var teamId = $('#queryTeam [name="teamId"]').val();
				var startTime = $('#queryTeam [name="startTime"]').val();
				var endTime = $('#queryTeam [name="endTime"]').val();
				if(equipmentName== ''){
					equipmentName= "-";
				}
				if(mac == ''){
					mac= "-";
				}
				if(startTime == ''){
					startTime= "-";
				}
				if(endTime == ''){
					endTime= "-";
				}

				var prames = equipmentName+","+mac+","+teamId+","+startTime+","+endTime;
				//alert(prames);
				window.location="<%=basePath%>list?equipment&prames="+prames;
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
