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
    
    <title>修改设备信息</title>
    
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
			$('form[id="updateForm"]').validate({
				rules:{
					teamName:"required",
					description:"required"
				},
				messages:{
					teamName:"组名不能为空",
					description:"描述不能为空"
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
							修改设备信息
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
  	   <form action="exeUpdate?equipment" method="post" id="updateForm">
    	<table>
    		<tr>
    			<td>设备名称：</td>
    			<td><input type="text" name="equiName" value="${item.equiName }" /></td>
    		</tr>
    		<tr>
    			<td>Mac地址：</td>
    			<td><input name="mac" value="${item.mac}" type="text"/></td>
    		</tr>
    		<tr>
    			<td>IP地址：</td>
    			<td><input name="ip" value="${item.ip}" type="text"/></td>
    		</tr>
    		<tr>
    			<td>硬盘码：</td>
    			<td><input name="harddisk" value="${item.harddisk}" type="text"/></td>
    		</tr>
    		<tr>
    			<td>设备码：</td>
    			<td><input name="code" value="${item.code}" type="text"/></td>
    		</tr>
    		<tr>
    			<td>添加时间：</td>
    			<td>${item.addTime}<input name="addTime" type="hidden" value="${item.addTime}"/></td>
    		</tr>
    		<tr>
    			<td>营业厅：</td>
    			<td><select name="teamId">
    				<c:forEach items="${teamList}" var="t">
    					<option value="${t.teamId }" <c:if test="${item.team.teamId==t.teamId}">selected="selected"</c:if>>${t.teamName }</option>
    				</c:forEach>
    			</select></td>
    		</tr>
    		<tr>
    			<td>系统：</td>
    			<td><select name="systemId">
    				<c:forEach items="${sysList}" var="system">
    					<option value="${system.systemId }" <c:if test="${item.sysinfo.systemId==system.systemId}">selected="selected"</c:if>>${system.systemName }</option>
    				</c:forEach>
    			</select></td>
    		</tr>
    		<tr>
    			<td>状态：</td>
    			<td><select name="status">
    				<option <c:if test="${item.status == 1 }">selected="selected"</c:if>  value="1">启用</option>
    				<option <c:if test="${item.status == 0 }">selected="selected"</c:if> value="0">停用</option>
    			</select></td>
    		</tr>
    		<tr>
    			<td><input name="equipmentId" type="hidden" value="${item.equipmentId}"/>
    			
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
