<%@ page language="java" import="java.util.*,cn.infogiga.bean.*,cn.infogiga.util.*" pageEncoding="utf-8"%>
<%
	List<Invitation>[][] list = (List<Invitation>[][])request.getAttribute("invitationList");
	
	String date = (String)request.getAttribute("date");
%>

<div id="content">



	<table width="100%" cellpadding="0" cellspacing="0" class="weekTitle">
		<tr>
			<td>星期日</td>
			<td>星期一</td>
			<td>星期二</td>
			<td>星期三</td>
			<td>星期四</td>
			<td>星期五</td>
			<td>星期六</td>
		</tr>
	</table>

	<table width="100%" cellpadding="0" cellspacing="0" id="render">
		<%
		Invitation invitation;
		List<Invitation> invitationList;
		for(int i=0;i<5;i++){
		%>
		<tr>
			<%for(int j =0;j<7;j++){
			invitationList = list[i][j];
			if(invitationList == null || invitationList.size() == 0){
			%>
			<td>
				<span class="dayStyle"></span>
				<div class="pros"></div>
			</td>
			<%}else{%>
			<td  style="width:14.3%;">
				<span class="dayStyle"></span>
				<%
				int s = invitationList.size();
				for(int k=0;k<s && k<3;k++){
				invitation = invitationList.get(k);
				%>
				<div class="rb-n" onmousedown='Index.showSingleDialog(<%=invitation.getInvitationId() %>)'>
					<%=DateUtil.getDateString(invitation.getVisitTime(),DateUtil.TIME) %>
				</div>			
				<%}%>
			</td>
			<%}}%>
		</tr>
		<%} %>
		<%if(list.length == 6){
		%>
		<tr style="display:none;">
		<%for(int l=0;l<7;l++){
			invitationList = list[5][l];
		%>
		<td  style="width:14.3%;">
			<span class="dayStyle"></span>
			<%
			int s = invitationList.size();
			for(int k=0;k<s && k<4;k++){
			invitation = invitationList.get(k);
			%>
			<div class="rb-n">
				<%=DateUtil.getDateString(invitation.getVisitTime(),DateUtil.TIME) %>
			</div>
			
			<%}%>
		</td>	 
		<%} %>
		</tr>
		<%}else{%>
		<tr style="display:none;">
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		   <td><span class="dayStyle"></span><div id="pros"></div></td>
		</tr>
		<%} %>
		
	</table>
</div>
	<script type="text/javascript">
	 view = new Calendar.view();
	 view.tds = document.getElementById("render").getElementsByTagName("td");
	 view.days = $(".dayStyle");
	 view.freeDays = $(".pros");
	 view.init();
//	 document.getElementById("testButton").onclick = function(){
//	  view.setCurrMonth(document.getElementById("testYear").value
//	       ,document.getElementById("testMonth").value);
//	 };
	</script>
