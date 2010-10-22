<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加设备</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">
	<LINK href="page/css/add-style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>plugin/jquery-1.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/dp/jquery.datepicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/Common.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('form[id="addEquipment"]').validate({
				rules:{
					mac:"required",
					ip:"required",
					harddisk:"required",
					equiName:"required"
				},
				messages:{
					mac:" MAC地址不能为空",
					ip:" IP地址不能为空",
					harddisk:" 硬盘码不能为空",
					equiName:" 设备名称不能为空"
				}
			});
		});
	</script>	

  </head>
  
  <body>
  <div>
			<div>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center
					border=0>
					<TR height=28>
						<TD background=images/title_bg1.jpg>
							添加设备
						</TD>
					</TR>
					<TR>
						<TD bgColor=#b1ceef height=1></TD>
					</TR>
					<TR height=20>
						<TD background=images/shadow_bg.jpg></TD>
					</TR>
				</TABLE>
			</div>
			<div id="context">
				<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
					<TR height=100>
						<TD width=60>
							&nbsp;
						</TD>
						<TD>
						<TD colSpan=3>
  	 <form action="exeAdd?equipment" method="post" id="addEquipment">
   		<table>
   			<tr>
   				<td>mac地址：</td>
   				<td><input type="text" name="mac"/></td>
   			</tr>
   			<tr>
   				<td>设备名称：</td>
   				<td><input type="text" name="equiName"/></td>
   			</tr>
   			<tr>
   				<td>ip地址：</td>
   				<td><input type="text" name="ip"/></td>
   			</tr>
   			<tr>
   				<td>硬盘码：</td>
   				<td><input type="text" name="harddisk"/></td>
   			</tr>
   			<tr>
   				<td>营业厅：</td>
   				<td><select name="teamId">
   					<c:forEach items="${teamList}" var="team">
   						<option value="${team.teamId }">${team.teamName }</option>
   					</c:forEach>
   				</select></td>
   			</tr>
   			<tr>
   				<td>系统：</td>
   				<td><select name="systemId">
   					<c:forEach items="${sysList}" var="system">
   						<option value="${system.systemId }">${system.systemName }</option>
   					</c:forEach>
   				</select></td>
   			</tr>
   			<tr>
   				<td><input type="hidden" name="teamId" value="${teamId}"/><input type="submit" value="提交"/></td>
   			</tr>
   		</table>
   
   </form>
   	</TD>
					</TR>
				</TABLE>
			</div>
		</div>

  </body>
</html>

