<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<%=basePath%>plugin/jquery-1.3.min.js"></script>
<title>邮件模板预览</title>
</head>
<body>
	<h1>标题：${templeteName}</h1>
	<hr/>
	<div style="width: 800px;border: 1px solid #eee;">
		${templeteView}	
	</div>
	<form action="exeAdd?emailTempleteReview" method="post" id="addForm">
		<c:if test="${not empty templeteId}"><input type="hidden" name="templeteId" value="${templeteId}"/></c:if>
		<c:if test="${not empty status}"><input type="hidden" name="status" value="${status}"/></c:if>
		<input type="hidden" name="templeteName" value="${templeteName}"/>
		<input type="hidden" name="templeteView" value='${templeteView}'/>
		<input type="submit" value="确定">
		<input type="button" value="返回" onclick="history.go(-1)">
	</form>
</body>
</html>