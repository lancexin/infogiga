<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 	<form action="/list?statistics" method="get" id="queryTeam">
			<table style="text-align: left;margin-left: 10px;margin-top: 10px">
	
				<tr>
					<td style="text-align: left;">营业厅：</td>
					<td style="text-align: left;"><select name="teamId">
						<option value="-">请选择...</option>
						<c:forEach items="${teamList}" var="team" varStatus="count">
							<option value="${team.teamId}">${team.teamName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left;">设备：</td>
					<td style="text-align: left;"><select name="equipmentId">
						<option value="-">请选择...</option>
						<c:forEach items="${equipmentList}" var="equipment" varStatus="count">
							<option value="${equipment.equipmentId}">${equipment.equiName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left;">员工：</td>
					<td style="text-align: left;"><select name="employeeId">
						<option value="-">请选择...</option>
						<c:forEach items="${employeeList}" var="employee" varStatus="count">
							<option value="${employee.employeeId}">${employee.empName}(${employee.empNo})</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left;">业务名称：</td>
					<td style="text-align: left;"><select name="menuId">
						<option value="-">请选择..</option>
						<c:forEach items="${menuList}" var="menu" varStatus="count">
							<option value="${menu.mid}">${menu.menuName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: left;">情景名称：</td>
					<td style="text-align: left;"><select name="sceneId">
						<option value="-">请选择..</option>
						<option value="5">业务办理</option>
						<option value="8">业务退订</option>
					</select></td>
				</tr>
				
				<tr>
					<td style="text-align: left;">顾客电话：</td>
					<td style="text-align: left;"><input type="text" name="phoneNumber"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">员工电话：</td>
					<td style="text-align: left;"><input type="text" name="empPhone"/></td>
				</tr>
						
				<tr>
					<td style="text-align: left;" colspan="2">发生时间：</td>
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
				
				var teamId = $('#queryTeam [name="teamId"]').val();
				var equipmentId = $('#queryTeam [name="equipmentId"]').val();
				var employeeId = $('#queryTeam [name="employeeId"]').val();
				var menuId = $('#queryTeam [name="menuId"]').val();
				var sceneId = $('#queryTeam [name="sceneId"]').val();
				var phoneNumber = $('#queryTeam [name="phoneNumber"]').val();
				var empPhone = $('#queryTeam [name="empPhone"]').val();
				var startTime = $('#queryTeam [name="startTime"]').val();
				var endTime = $('#queryTeam [name="endTime"]').val();
				if(empPhone== ''|| empPhone==undefined){
					empPhone= "-";
				}
				if(phoneNumber== '' || phoneNumber == undefined){
					phoneNumber= "-";
				}
				if(startTime == '' || startTime == undefined ){
					startTime= "-";
				}
				if(endTime == '' ||  endTime == undefined ){
					endTime= "-";
				}
				//var prames = equipmentName+","+mac+","+phoneNumber+","+menuId+",-,"+empName+","+empNo+","+empPhone+","+startTime+","+endTime;
				var prames = teamId+","+equipmentId+","+employeeId+","+menuId+","+sceneId+","+phoneNumber+","+empPhone+","+startTime+","+endTime;
				
				//alert(prames);
				window.location="<%=basePath%>list?statistics&prames="+prames;
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
