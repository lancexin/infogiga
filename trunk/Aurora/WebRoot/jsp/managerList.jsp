<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
List<Manager> managerList = (List<Manager>)request.getAttribute("managerList");
int size = managerList.size();
String groupId = (String)request.getAttribute("groupId");
%>

<table id="addUser">
	<tr>
		<td>姓名</td>
		<td>用户名</td>
		<td>密码</td>
		<td>手机号</td>
		<td>邮箱</td>
		<td>设置</td>
	</tr>
	
	<%
	Manager manager;
	for(int i=0;i<size;i++){
	manager = managerList.get(i);
	%>
	<tr id="user<%=manager.getManagerId() %>">
		<td id="user_name<%=manager.getManagerId() %>" ><%=manager.getName() %></td>
		<td id="user_username<%=manager.getManagerId() %>"><%=manager.getUsername() %></td>
		<td id="user_password<%=manager.getManagerId() %>"><%=manager.getPassword() %></td>
		<td id="user_phoneNumber<%=manager.getManagerId() %>"><%=manager.getPhoneNumber() %></td>
		<td id="user_mail<%=manager.getManagerId() %>"><%=manager.getMail() %></td>
		<td><a href="javascript:Setup.deleteUser(<%=manager.getManagerId() %>);">删除</a><a href="javascript:Setup.updateUser(<%=groupId %>,<%=manager.getManagerId() %>);">修改</a></td>
	</tr>
	
	
	<%}%>
	
	
</table>