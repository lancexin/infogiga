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
    
    <title>用户添加</title>
    
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
			$('form[id="addForm"]').validate({
				rules:{
					systemName:"required"
				},
				messages:{
					systemName:" 系统名称不能为空"
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
							添加用户
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
  	 <form action="exeUpdate?user" method="post" id="addForm">
   		<table>
   			<tr>
   				<td>用户名：</td>
   				<td><input type="text" name="userName" value="${item.userName }"/></td>
   			</tr>
   			<tr>
   				<td>密码：</td>
   				<td><input type="text" name="userPassword" value="${item.userPassword }"/></td>
   			</tr>
   			<tr>
   				<td>姓名：</td>
   				<td><input type="text" name="nickname" value="${item.nickName }"/></td>
   			</tr>
   			<tr>
   				<td>所属系统：</td>
   				<td><select name="systemId">
   					<c:forEach items="${sysList}" var="system">
   						<option <c:if test="${item.sysinfo.systemId==system.systemId}">selected="selected"</c:if> value="${system.systemId }">${system.systemName }</option>
   					</c:forEach>
   				</select></td>
   			</tr>
   			<tr>
   				<td colspan="2">权限：</td>
   			</tr>
   			
			<c:forEach items="${power.menuList}" var="menu">
			<tr>
				<td colspan="2">
				<c:forEach items="${menu.itemList}" var="item">
					<input <c:forEach items="${ownPower}" var="p">
						<c:if test="${p eq item.code}">checked="checked"</c:if>
					</c:forEach> type="checkbox" name="power" value="${item.code }"/>${item.name }
				</c:forEach>
				</td>
			</tr>
			</c:forEach>
			
   			<tr>
    			<td>状态：</td>
    			<td><select name="status">
    				<option <c:if test="${item.status == 1 }">selected="selected"</c:if>  value="1">启用</option>
    				<option <c:if test="${item.status == 0 }">selected="selected"</c:if> value="0">停用</option>
    			</select></td>
    		</tr>

   			<tr>
   				<td>
   				<input type="hidden" name="userId" value="${item.userId}"/>
   				<input type="hidden" name="addTime" value="${item.addTime}"/>
   				<input type="submit" value="提交"/></td>
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

