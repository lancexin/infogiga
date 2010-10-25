<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
List<Application> applicationList = (List<Application>)request.getAttribute("applicationList");
int size = applicationList.size();
String applicationCount = (String)request.getAttribute("applicationCount");
%>

<%if(applicationCount != null && !"".equals(applicationCount)){%>
<script type="text/javascript">
<!--
$("#"+requestSelection.id+"Count").html(<%=applicationCount %>);
requestSelection.allPage = <%=applicationCount %>;
$("#"+requestSelection.id+"Page").html("1");
//-->
</script>
<%}%>

 <%
 Application app;
 for(int i=0;i<size;i++){
 app = applicationList.get(i);
 %>
 <ul class="feedback-e">
    <li class="feedback-b"><span class="feedback-f">时间：<%=app.getCreateTime() %></span><span class="feedback-f">姓名：<%=app.getCustomer().getName() %></span><span class="feedback-f">手机号：<%=app.getCustomer().getPhoneNumber() %></span><span class="feedback-f">公司：<%=app.getCustomer().getCompany() %></span></li>
    <li class="feedback-b"></li>
    <li class="feedback-d"><%=app.getReason() %></li>
</ul>
<%}%>
