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
    
    <title>添加组</title>
    
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
					version:"required",
					file:"required"
				},
				messages:{
					version:" version不能为空",
					file:" 文件不能为空"
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
							添加更新
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
  	 <form action="exeAdd?renewal" method="post" id="addForm" enctype="multipart/form-data">
   		<table>
   			<tr>
   				<td>版本号：</td>
   				<td><input type="text" name="version"/></td>
   			</tr>
   			<tr>
   				<td>系统：</td>
   				<td><select name="systemId">
   					<c:forEach items="${syslist}" var="sys">
   						<option value="${sys.systemId }">${sys.systemName }</option>
   					</c:forEach>
   				</select></td>
   			</tr>
   			<tr>
   				<td>设置默认：</td>
   				<td><input type="checkbox" value="1" name="status"/></td>
   			</tr>
   			<tr>
   				<td>文件上传：</td>
   				<td><input type="file" name="file"/></td>
   			</tr>

   			<tr>
   				<td>
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

