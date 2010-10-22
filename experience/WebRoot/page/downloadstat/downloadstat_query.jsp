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
					<td style="text-align: left;">下载类型：</td>
					<td style="text-align: left;"><select name="downloadtypeId">
						<option value="-">请选择..</option>
						<c:forEach items="${downloadtypeList}" var="downloadtype" varStatus="count">
							<option value="${downloadtype.downloadtypeId}">${downloadtype.typeName}</option>
						</c:forEach>
					</select></td>
				</tr>
								<tr>
					<td style="text-align: left;">手机厂家：</td>
					<td style="text-align: left;"><select name="phonebrandId">
						<option value="-">请选择..</option>
						<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="count">
							<option value="${phonebrand.phonebrandId}">${phonebrand.phonebrandName}</option>
						</c:forEach>
					</select></td>
				</tr>
				
				<tr>
					<td style="text-align: left;">手机型号：</td>
					<td style="text-align: left;"><input type="text" name="phonetypeName"/></td>
				</tr>

				<tr>
					<td style="text-align: left;">软件名称：</td>
					<td style="text-align: left;"><input type="text" name="softName"/></td>
				</tr>
				<tr>
					<td style="text-align: left;">顾客电话：</td>
					<td style="text-align: left;"><input type="text" name="phoneNumber"/></td>
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
				var phoneNumber = $('#queryTeam [name="phoneNumber"]').val();
				var phonetypeName = $('#queryTeam [name="phonetypeName"]').val();
				var phonebrandId = $('#queryTeam [name="phonebrandId"]').val();
				var softName = $('#queryTeam [name="softName"]').val();
				var downloadtypeId = $('#queryTeam [name="downloadtypeId"]').val();
				var startTime = $('#queryTeam [name="startTime"]').val();
				var endTime = $('#queryTeam [name="endTime"]').val();


				if(phoneNumber== '' || phoneNumber == undefined){
					phoneNumber= "-";
				}

				if(phonetypeName == '' || phonetypeName == undefined){
					phonetypeName= "-";
				}

				if(softName == '' || softName == undefined){
					softName= "-";
				}

				if(startTime == '' || startTime == undefined ){
					startTime= "-";
				}
				if(endTime == '' ||  endTime == undefined ){
					endTime= "-";
				}
				//var prames = empName+","+empNo+","+equipmentName+","+mac+","+downloadtypeId+","+softName+","+phonebrandName+","+phonetypeName+","+startTime+","+endTime+","+phoneNumber;
				var prames = teamId+","+equipmentId+","+employeeId+","+phoneNumber+","+phonetypeName+","+phonebrandId+","+softName+","+downloadtypeId+","+startTime+","+endTime;
				
				//alert(prames);
				window.location="<%=basePath%>list?downloadstat&prames="+prames;
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
