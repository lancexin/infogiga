<%@ page language="java" import="java.util.*,bean.ReportBean,bean.ResultBean" pageEncoding="UTF-8"%>
<%@page import="data.Database,bean.RegionBean"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据显示页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="数据显示">
	
	<link rel="stylesheet" type="text/css" href="../css/data.css">
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script type='text/javascript'>
	function toDataPage() {
		window.open('./data.jsp');
	}
	</script>
  </head>
  <%  	
  	Object type = session.getAttribute("isChart");
  	Database db = new Database();
  	ArrayList<ReportBean> list = (ArrayList<ReportBean>)session.getAttribute("dataList");
  	ArrayList<RegionBean> regionList = (ArrayList<RegionBean>)session.getAttribute("regionList");   	
	if(list == null || type == null) {
		return;
	}
	boolean isChart = (Boolean)type;
   %>
  <body>
   	<div class='result'>
   		<%if(isChart) { %>
   		<div class='chart'>
   			<img src='..\report\<%=session.getAttribute("barChart") %>' id='barChart'></img>
   			<img src='..\report\<%=session.getAttribute("pieChart") %>' id='pieChart'></img>
   		</div>
   		<hr />
   		<%} %>
   		<div class='regionCount'>
			<%for(RegionBean region: regionList) {%>
			<fieldset class='region'>
				<legend><%=region.getRegionName() %></legend>
				<ul class='operateList'>
				<% 
				for(ResultBean bean :(ArrayList<ResultBean>)session.getAttribute(region.getRegionCode())){ %>   			
					<li class='operateLine'>
						<span class='operateName'><%=bean.getName()%>:</span>
						<span class='operateNum'><%=bean.getNum() %></span>次
					</li>
				<%} %>
				</ul>
			</fieldset>
			<% }%> 
		</div>
		<hr />
   		<div class='operate'>体验次数：<span id='state'></span></div>		
   		<div class='stime'><span>开始时间：</span><span id='stime'></span></div>
   		<div class='etime'><span>结束时间：</span><span id='etime'></span></div>  	
   		<table class='dataTable' id='dataTable'>
			<thead>
				<tr class='titleLine'>
					<td width='4%'>序号</td>
					<td width='5%'>地区</td>
					<td width='16%'>营业厅</td>
					<td width='16%'>设备</td>
					<td width='14%'>体验</td>
					<td width='10%'>操作</td>
					<td width='13%'>体验时间</td>
				</tr>						
			</thead>
			<tbody>
	   		<% 
	   		String pageArea = request.getParameter("pageArea")==null?"1":request.getParameter("pageArea"); 
	   		int p = Integer.parseInt(pageArea);
	   		if(list.size() == 0) {
	   			out.print("<script type='text/javascript'>$('#state').text('-')</script>");
	   		} else if(list.size()/500 < p-1) {
	   			p = 1;
	   		}
	   		for(ReportBean bean: list) { 
	   			if(list.indexOf(bean)/500 == p-1) {
	   		%>
	   			<tr <%if(list.indexOf(bean)%2 == 1) {%>class='even'<%} else {%>class='odd'<%} %>>
	   				<td><%=list.indexOf(bean)+1 %></td>
	   				<td><%=bean.getAddressName() %></td>
	   				<td><%=bean.getSellingName() %></td>
	   				<td><%=bean.getEquipmentName() %></td>
	   				<td><%=bean.getSystemName() %></td>
	   				<td><%=bean.getOperateName() %></td>
	   				<td><%=bean.getTime() %></td>
	   			</tr>
	   		<%}
	   		} %>
   			</tbody>
   		</table>
   		<div class='dpage'> 
   		-
	   		<%
	   		int i=0;
	   		String link = "curLink";
	   		for(; i<list.size()/500; i++) {
	   		%>
	   			<a href='javascript:toPage(<%=i+1 %>)' class='<%=i+1==p?link:"" %>'><%=i+1 %></a>
	   		<%}
	   		if(list.size()%500!=0) {
	   		%>
	   			<a href='javascript:toPage(<%=i+1 %>)' class='<%=i+1==p?link:"" %>'><%=i+1 %></a>
	   		<%}
	   		%>
	   	-
   		</div>
   		<form name='form0' method='post' action='#'>
   		<input type='hidden' name='pageArea'/>
   	</form>
   	</div>
  <script type="text/javascript">
  $(document).ready(function(){
  	if(!allowVisit()) return;
  	var stime = window.parent.document.getElementById('stime').value;
  	var etime = window.parent.document.getElementById('etime').value;
  	
  	$('#state').text('<%=list.size()%>次');
  	$('#stime').text(stime==''?'-':stime);
  	$('#etime').text(etime==''?'-':etime);
  	
  	$('.operateList').each(function(){
  		if($(this).html() == '') $(this).html('无体验数据');
  	})  	
  });
  
  /*限制直接访问*/
	function allowVisit() {
		if(parent.document.getElementById('stime') == null) {
			document.body.innerHTML = '禁止直接访问';
			return false;
		}
		return true;
	}
	
   function toPage(index) {
  	form0.pageArea.value = index;
  	form0.submit();
 	} 
  </script>
  </body>  
</html>
