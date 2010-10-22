<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>组信息修改</title>
    
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
			$('form[id="updateTeam"]').validate({
				rules:{
					teamName:"required",
					teamCode:"required",
					description:"required"
				},
				messages:{
					teamName:"组名不能为空",
					description:"描述不能为空"
					teamCode:"描述不能为空"
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
							组信息修改
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
  	  <form action="exeUpdate?team" method="post" id="updateTeam">
    	<table>
    		<tr>
    			<td>营业厅名称：</td>
    			<td><input type="text" name="teamName" value="${team.teamName }" /></td>
    		</tr>
    		<tr>
    			<td>营业厅编号：</td>
    			<td><input type="text" name="teamCode" value="${team.teamCode }" /></td>
    		</tr>
    		<tr>
    			<td>所在地区：</td>
    			<td><select name="areaId">
    				<c:forEach items="${areaList}" var="area">
    					<option <c:if test="${team.area.areaId==area.areaId}">selected="selected"</c:if> value="${area.areaId }">${area.areaName }</option>
    				</c:forEach>
    			</select></td>
    		</tr>
    		<tr>
    			<td>添加时间：</td>
    			<td>${team.addTime }<input name="addTime" value="${team.addTime}" type="hidden"/></td>
    		</tr>
    		<tr>
    			<td>描述：</td>
    			<td><textarea name="description">${team.description}</textarea></td>
    		</tr>
    		<tr>
    			<td>状态：</td>
    			<td><select name="status">
    				<option <c:if test="${team.status == 1 }">selected="selected"</c:if>  value="1">启用</option>
    				<option <c:if test="${team.status == 0 }">selected="selected"</c:if> value="0">停用</option>
    			</select></td>
    		</tr>
    		<tr>
    			<td>
    			<input name="teamId" type="hidden" value="${team.teamId}"/>
    			<input value="修改" type="submit"/></td>
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
