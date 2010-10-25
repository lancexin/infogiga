<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
List<Comment> commentList = (List<Comment>)request.getAttribute("commentList");
int size = commentList.size();
String commentCount = (String)request.getAttribute("commentCount");
%>
<%if(commentCount != null && !"".equals(commentCount)){%>
<script type="text/javascript">
<!--
$("#"+feedbackSelection.id+"Count").html(<%=commentCount %>);
feedbackSelection.allPage = <%=commentCount %>;
$("#"+feedbackSelection.id+"Page").html("1");
//-->
</script>
<%}%>

<%
Comment comment;
for(int i=0;i<size;i++){
comment = commentList.get(i);
%>
 <ul class="feedback-e">
     <li class="feedback-b"><span class="feedback-f">时间：<%=comment.getReceiveTime() %></span></li>
     <li class="feedback-b"><span class="feedback-f">姓名：<%=comment.getCustomer().getName() %></span><span class="feedback-f">手机号：<%=comment.getCustomer().getPhoneNumber() %></span><span class="feedback-f">公司：<%=comment.getCustomer().getCompany() %></span></li>
     <li class="feedback-d"><%=comment.getContent() %></li>
 </ul>
<%}%>



