<%@ page language="java" import="java.util.*,net.fckeditor.*,cn.infogiga.exp.pojo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加邮件模板</title>
    
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
					templeteName:"required"
				},
				messages:{
					templeteName:"不能为空"
				}
			});
		});
	</script>	

  </head>
  
  <body>
  <form action="exeUpdate?emailTemplete" method="post" id="addForm">
  		<%
  		Emailtemplete templete = (Emailtemplete)request.getAttribute("item");
  		%>
  		<input type="hidden" name="templeteId" value="<%=templete.getTempleteId() %>"/>
  		模板名称：<input type="text" name="templeteName" value="<%=templete.getTempleteName() %>"/></br>
  		模板状态：<select name="status">
  			<option <c:if test="${item.status== 1}">selected="selected"</c:if> value="1">启用</option>
  			<option <c:if test="${item.status== 0}">selected="selected"</c:if> value="0">停用</option>
  		</select></br>
  		<%
  			
			FCKeditor fckEditor = new FCKeditor(request, "templeteView");
			fckEditor.setHeight("500");
			fckEditor.setWidth("800");
			fckEditor.setValue(templete.getTempleteView());
			out.println(fckEditor);
			
		  %>
		  <input type="submit" value="提交"/>
  </form>
   </body>
</html>

