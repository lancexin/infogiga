<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String date = (String)request.getAttribute("date");
Invitation[] list = (Invitation[])request.getAttribute("invitationList");

%>
    <div id="topcontainerwk">
	<table class="wk-weektop" cellpadding="0" cellspacing="0">
			<tr class="wk-daynames">
				<td class="wk-tzlabel" rowspan="3"></td>
				<th title="<%=date %>" scope="col">
					<div class="wk-dayname">
						<span class="wk-daylink"><%=date %></span>
					</div>
				</th>
				<th class="wk-dummyth" rowspan="3">
					&nbsp;
				</th>
			</tr>

	</table>
</div>
<div id="scrolltimedeventswk" class="wk-scrolltimedevents">
	<table id="tgTable" class="tg-timedevents">
		<tbody>
			<tr height="1">
				<td style="width: 60px;"></td>
				<td colspan="1">
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
					
					<div id="tgnowptr" class="tg-nowptr"></div>
				</td>
				<td class="tg-col">
					<div class="tg-today">
						&nbsp;
					</div>
					
					
					<div class="tg-col-eventwrapper">
						<div class="tg-gutter" id="tgCol0">
							<%
							Invitation invitation2 = null;
							for(int i=0;i<=13;i++){
							invitation2 = list[i];
							if(invitation2 == null){
							%>
							<div class="chip_no" style="top:<%=i*42 %>px;height: 42px;" onmousedown="Next.showFastInvitation('<%=date+" "+(i+7)+":00" %>');">
								
							</div>
						    <%}else{%>
							<div class="chip_ok" style="top:<%=i*42 %>px;height: 42px;" onmousedown="Index.showSingleDialog(<%=invitation2.getInvitationId() %>);" date="<%=date+" "+(i+7)+":00" %>">
								<dl class="cbrd" style="height: 39px;border-color: rgb(163, 41, 41);  background-color: rgb(217, 102, 102);">
									<dt style="background-color: rgb(163, 41, 41);">
										<%=invitation2.getVisitTime() %>
									</dt>
									<dd>
										<span><%=invitation2.getInvitationTitle() %></span>
									</dd>
								</dl>
								
							</div>	
							<%}}%>
						</div>
					</div>
					
				</td>
			</tr>
		</tbody>
	</table>
</div>
