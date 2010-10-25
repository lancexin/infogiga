<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
Integer invitationCount = (Integer)request.getAttribute("reservationCount");
List<Invitation> invitationtList = (List<Invitation>)request.getAttribute("reservationList");
%>

<script type="text/javascript">
		$("#reservation_search_bar").accordion({
			autoHeight:false,
			collapsible:true
		});
		
		$("#reservationFromTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		
		$("#reservationFromCreateTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		
		$("#reservationToTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		
		$("#reservationToCreateTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		
		reservationSelection.allPage =<%=invitationCount%>
	</script>

	
	<div id="reservation_search_bar">
        <h3><a href="#">搜索</a></h3>
        <div>
        	<form id="reservationSearchForm">
            	<table>
                	<tr>
                    	<td>参观名称：<input name="title" type="text" style="width:80px"/></td>
                        <td>客户经理：<input name="manager" type="text"  style="width:80px"/></td>
                        <td>参观时间：<input id="reservationFromTime" name="fromTime" type="text"  style="width:80px"/>~<input id="reservationToTime" name="toTime" type="text"  style="width:80px"/></td>
                        
                    </tr>
                     <tr>
                    	
                        <td>接待人员：<input name="guider" type="text"  style="width:80px"/></td>
                        <td>维护人员：<input name="technician" type="text" style="width:80px"/></td>
                        <td>邀请时间：<input  id="reservationFromCreateTime" name="fromCreateTime" type="text"  style="width:80px"/>~<input  id="reservationToCreateTime" name="toCreateTime"  type="text"  style="width:80px"/></td>
                        
                    </tr>
                     <tr>
                    	
                        <td>每页显示：<select name="pageCount">
                        	<option>5</option>
                            <option>10</option>
                            <option>15</option>
                        </select></td>
                        
                        <td style="text-align:center"><input type="reset" value="清空"/><input type="button" value="搜索" onclick="Page.search(reservationSelection)"/></td>
                    </tr>
                </table>
            </form>
        	
           
        </div>
        </div>
	
<span>一共有<i id="reservationCount"><%=invitationCount %></i>条数据</span>	
<div class="feedback-e"><a href="javascript:Page.pageUp(reservationSelection);">上一页</a><a href="javascript:Page.pageDown(reservationSelection);" >下一页</a> 当前第<i id="reservationPage">1</i>页 </div>
<div id="reservation">
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
				<td class="lv-eventcell lv-titlecell">
					<%=invitation.getInvitationTitle()==null?"-":invitation.getInvitationTitle() %>
					
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

</div>