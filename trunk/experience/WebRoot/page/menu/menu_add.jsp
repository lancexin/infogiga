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
    
    <title>添加业务</title>
    
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
					menuId:"required",
					funcId:"required",
					parentMenuId:"required",
					menuDesc:"required",
					domainId:"required",
					menuUrl:"required",
					menuPicUrl:"required",
					menuIdx:"required",
					needLogin:"required",
					needEcontrct:"required",
					needSecondPasswd:"required",
					stopShow:"required",
					notLoginShow:"required",
					busiKind:"required",
					isUsed:"required",
					validDate:"required",
					expireDate:"required",
					lastDayCando:"required",
					helpUrl:"required",
					channelLevel:"required",
					menuName:"required"
					pkg:"required"
				},
				messages:{
					menuId:"不能为空",
					funcId:"不能为空",
					parentMenuId:"不能为空",
					menuDesc:"不能为空",
					domainId:"不能为空",
					menuUrl:"不能为空",
					menuPicUrl:"不能为空",
					menuIdx:"不能为空",
					needLogin:"不能为空",
					needEcontrct:"不能为空",
					needSecondPasswd:"不能为空",
					stopShow:"不能为空",
					notLoginShow:"不能为空",
					busiKind:"不能为空",
					isUsed:"不能为空",
					validDate:"不能为空",
					expireDate:"不能为空",
					lastDayCando:"不能为空",
					helpUrl:"不能为空",
					channelLevel:"不能为空",
					menuName:"不能为空"
					pkg:"不能为空"
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
							添加业务
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
  	   <form action="exeAdd?menu" method="post" id="updateForm">
    	<table>
    		<tr>
    			<td>菜单编号：</td>
    			<td><input type="text" name="menuId" /></td>
    		</tr>
    		<tr>
    			<td>程序包：</td>
    			<td><input type="text" name="pkg" /></td>
    		</tr>
    		<tr>
    			<td>业务名称：</td>
    			<td><input name="menuName" type="text"/></td>
    		</tr>
    		<tr>
    			<td>功能编号：</td>
    			<td><input name="funcId" type="text"/></td>
    		</tr>
    		<tr>
    			<td>父菜单编号：</td>
    			<td><input name="parentMenuId" type="text"/></td>
    		</tr>
    		<tr>
    			<td>业务描述：</td>
    			<td><input name="menuDesc" type="text"/></td>
    		</tr>
    		<tr>
    			<td>域编号：</td>
    			<td><input name="domainId" type="text"/></td>
    		</tr>
    		<tr>
    			<td>菜单地址：</td>
    			<td><input name="menuUrl" type="text"/></td>
    		</tr>
    		<tr>
    			<td>菜单图片地址：</td>
    			<td><input name="menuPicUrl" type="text"/></td>
    		</tr>
    		<tr>
    			<td>排序：</td>
    			<td><input name="menuIdx" type="text"/></td>
    		</tr>
    		<tr>
    			<td>是否要登录：</td>
    			<td><input name="needLogin" type="text"/></td>
    		</tr>
    		<tr>
    			<td>是否要签订电子协议：</td>
    			<td><input name="needEcontrct" type="text"/></td>
    		</tr>
    		<tr>
    			<td>是要二次密码确认：</td>
    			<td><input name="needSecondPasswd" type="text"/></td>
    		</tr>
    		<tr>
    			<td>营业停机否可见：</td>
    			<td><input name="stopShow" type="text"/></td>
    		</tr>
    		<tr>
    			<td>未登录是否显示：</td>
    			<td><input name="notLoginShow" type="text"/></td>
    		</tr>
    		<tr>
    			<td>业务性质：</td>
    			<td><input name="busiKind" type="text"/></td>
    		</tr>
    		<tr>
    			<td>菜单状态：</td>
    			<td><input name="isUsed" type="text"/></td>
    		</tr>
    		<tr>
    			<td>生效时间：</td>
    			<td><input name="validDate" type="text"/></td>
    		</tr>
    		<tr>
    			<td>失效时间：</td>
    			<td><input name="expireDate" type="text"/></td>
    		</tr>
    		<tr>
    			<td>月底是否可受理：</td>
    			<td><input name="lastDayCando" type="text"/></td>
    		</tr>
    		<tr>
    			<td>帮助地址：</td>
    			<td><input name="helpUrl" type="text"/></td>
    		</tr>
    		<tr>
    			<td>渠道星级：</td>
    			<td><input name="channelLevel" type="text"/></td>
    		</tr>
    		

    		<tr>
    			<td>

    			<input value="添加" type="submit"/></td>
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
