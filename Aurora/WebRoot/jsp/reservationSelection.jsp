<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
Integer invitationCount = (Integer)request.getAttribute("reservationCount");
List<Invitation> invitationtList = (List<Invitation>)request.getAttribute("reservationList");
%>
<%if(invitationCount != null && !"".equals(invitationCount)){%>
<script type="text/javascript">
<!--
$("#"+reservationSelection.id+"Count").html(<%=invitationCount %>);
reservationSelection.allPage = <%=invitationCount %>;
$("#"+reservationSelection.id+"Page").html("1");
//-->
</script>
<%}%>
	<table class="printFullHeight listv" cellspacing="0">
			<tr class="lv-row lv-newdate lv-firstevent  lv-alt lv-lastevent">
				
				<td class="lv-eventcell lv-time">
					参观时间
				</td>
				<td class="lv-eventcell lv-time">
					邀请时间
				</td>
				<td class="lv-eventcell lv-time">
					邀请人数
				</td>
				<td class="lv-eventcell lv-time">
					参观名称
				</td>
				<td class="lv-eventcell lv-time">
					客户经理
				</td>
				<td class="lv-eventcell lv-time">
					接待人员
				</td>
				<td class="lv-eventcell lv-time">
					维护人员
				</td>
			</tr>
			<%
			Invitation invitation;
			int size = invitationtList.size();
			for(int i=0;i<size;i++){
				invitation = invitationtList.get(i);
			%>	
			<tr class="mouseOver" style="cursor: pointer;" onmousedown="Index.showSingleDialog(<%=invitation.getInvitationId() %>);">
				
				<td class="lv-eventcell lv-time"><%=invitation.getVisitTime() %></td>
				<td class="lv-eventcell lv-time"><%=invitation.getCreateTime() %></td>
				<td class="lv-eventcell lv-time"><%=invitation.getPenpleCount() %></td>
				<td class="lv-eventcell lv-titlecell"><%=invitation.getInvitationTitle() %>
					
				</td>
				<td class="lv-eventcell lv-time">
					<%=invitation.getManagerByManagerId()==null?
					 	"-":invitation.getManagerByManagerId().getName()%>
				</td>
				<td class="lv-eventcell lv-time">
					<%=invitation.getManagerByGuiderId()==null?
						"-":invitation.getManagerByGuiderId().getName() %>
				</td>
				<td class="lv-eventcell lv-time">
					<%=invitation.getManagerByTechnicianId()==null?
						"-":invitation.getManagerByTechnicianId().getName() %>
				</td>
			</tr>
			<%}%>
	</table>