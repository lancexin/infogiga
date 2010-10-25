<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String inv = (String)request.getAttribute("inv");
%>
<body>
	<form id="fastInvitation">
	<input type='hidden' id='visitTime-fast' name='visitTime' />
	您希望邀请： 
		<input type="button"  class="add-order-a" value="从文件导入" onclick="Index.showUploadFileDialog();"/>
		<div class='stub'></div> <!-- 后面紧跟customerList，不能跟别的 -->
		<table class='customerList'>
			<tr>
				<td>姓名</td>
				<td>手机号</td>
				<td>公司名称</td>
				<td>选项</td>
			</tr>
			<tr class="customerLine">
				<td><input type="text" name="name" /></td>
				<td><input type="text" name="phoneNumber" /></td>
				<td><input type="text" name="company" /></td>
				<td onclick="Next.removeCustomerLine(this)">删除</td>
			</tr>
			<tr class="customerLine">
				<td><input type="text" name="name" /></td>
				<td><input type="text" name="phoneNumber" /></td>
				<td><input type="text" name="company" /></td>
				<td onclick="Next.removeCustomerLine(this)">删除</td>
			</tr>
		</table>
		<div class="add-order-a"><a href="javascript:Next.addCustomerLine();">添加</a></div>
		<a href="javascript:Next.closeFastInvitation();Index.addSingleReservation(this);">修改详细信息 >></a>
	</form>
</body>
