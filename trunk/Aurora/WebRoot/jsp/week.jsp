<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String date = (String)request.getAttribute("date");
Invitation[][] list = (Invitation[][])request.getAttribute("invitationList");
%>

<script type="text/javascript">
<!--
var dateStr = "<%=date%>";
var dStr = dateStr.split('-');
var oToday = new Date(dStr[0],dStr[1]-1,dStr[2]);
var currentDay=oToday.getDay();
if(currentDay==0){currentDay=7}
var s='<td class="wk-tzlabel" style="width: 60px;" rowspan="3"></td>';
var c=["周日","周一","周二","周三","周四","周五","周六"];
for(var i=0;i<7;i++){
	var time = oToday.getTime()-(currentDay-i)*24*60*60*1000;
	var newDate = new Date(time);
	var date = newDate.getFullYear()+"-"+(newDate.getMonth()+1)+"-"+newDate.getDate();
	var day = 
	s = s+'<th title="'+date+' ('+c[i]+')" scope="col">'+
		'<div class="wk-dayname">'+
			'<span class="wk-daylink">'+c[i]+'</span>'+
		'</div>'+
		'<div class="wk-dayname">'+
			'<span class="wk-daylink">'+date+'</span>'+
		'</div>'+
	'</th>';
}
s = s+'<th class="wk-dummyth" rowspan="3" style="width: 16px;">&nbsp;</th>';
$("#week-bar").html(s);
//-->
</script>
<div class="" style="overflow-y: visible;" id="gridcontainer">
	<div id="topcontainerwk">
		<table class="wk-weektop" cellpadding="0" cellspacing="0">
			<tbody>
				<tr class="wk-daynames" id="week-bar">
					
				</tr>
			</tbody>
		</table>
	</div>
	<div id="scrolltimedeventswk" class="wk-scrolltimedevents">
		<table id="tgTable" class="tg-timedevents">
			<tbody>
				<tr height="1">
					<td style="width: 60px;"></td>
					<td colspan="7">
						<div class="tg-spanningwrapper">
							<div class="tg-hourmarkers">
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
								<div class="tg-dualmarker"></div>
							</div>
						</div>
						<div class="tg-spanningwrapper tg-chipspanningwrapper"
							id="tgspanningwrapper"></div>
					</td>
				</tr>
				<tr>
					<td class="tg-times-pri">
					<div class="tg-time-pri">7：00</div>
					<div class="tg-time-pri">8：00</div>
					<div class="tg-time-pri">9：00</div>
					<div class="tg-time-pri">10：00</div>
					<div class="tg-time-pri">11：00</div>
					<div class="tg-time-pri">12：00</div>
					<div class="tg-time-pri">13：00</div>
					<div class="tg-time-pri">14：00</div>
					<div class="tg-time-pri">15：00</div>
					<div class="tg-time-pri">16：00</div>
					<div class="tg-time-pri">17：00</div>
					<div class="tg-time-pri">18：00</div>
					<div class="tg-time-pri">19：00</div>
					<div class="tg-time-pri">20：00</div>
					</td>
					
					<%
					Invitation invitation;
					for(int i=0;i<7;i++){
					%>
						<td class="tg-col">
							
						
							<div id="tgCol<%=i %>" class="tg-col-eventwrapper">
								<div class="tg-gutter">
									<%
									for(int j=0;j<14;j++){
										invitation = list[j][i];
										if(invitation == null){
									%>
										<div class="chip" style="top: <%=j*42 %>px;height: 42px;" onmousedown="Next.showFastInvitation('<%=date+" "+(i+7)+":00" %>');">
											
										</div>
										<%}else{%>
										<div class="chip" style="top: <%=j*42 %>px;height: 42px;" onmousedown="Index.showSingleDialog(<%=invitation.getInvitationId() %>);">
											<dl class="cbrd"
												style="border-color: rgb(163, 41, 41); height: 39px; background-color: rgb(217, 102, 102);">
												<dt style="background-color: rgb(163, 41, 41);">
													<%=invitation.getVisitTime() %>
												</dt>
												<dd>
													<span><%=invitation.getInvitationTitle() %></span>
												</dd>
		
											</dl>
										</div>
										<%}}%>
									
								</div>
							</div>
							<div id="tgOver<%=i %>" class="tg-col-overlaywrapper"></div>
						</td>
					<%}%>
				</tr>
			</tbody>
		</table>
	</div>
</div>
