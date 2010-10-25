<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
Integer applicationCount = (Integer)request.getAttribute("applicationCount");
List<Application> applicationList = (List<Application>)request.getAttribute("applicationList");
%>


<script type="text/javascript">
	$("#request_search_bar").accordion({
		autoHeight:false,
		collapsible:true
	});
	
	$("#requestFromTime").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-mm-dd' 
	});
	
	$("#requestToTime").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: 'yy-mm-dd' 
	});
	
	requestSelection.allPage=<%=applicationCount%>
</script>

<div id="request_search_bar">
        <h3><a href="#">搜索</a></h3>
        <div>
        	<form id="requestSearchForm">
            	<table>
                	<tr>
                    	<td>姓名：<input name="name"  style="width:100px" type="text"/></td>
                        <td>电话：<input name="phoneNumber"  style="width:100px" type="text"/></td>
                        <td>公司：<input name="company"  style="width:100px" type="text"/></td>
                        <td>时间：<input id="requestFromTime"  style="width:80px" name="fromTime" type="text"/>~<input  style="width:80px" id="requestToTime" name="toTime" type="text"/></td>
                        
                    </tr>
                    
                    <tr>
                    	<td>邮箱：<input name="mail"  style="width:100px" type="text"/></td>
                    	<td>每页显示：<select name="pageCount">
                        	<option value="5">5</option>
                        	<option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                        </select></td>
                        <td style="text-align:center"><input type="reset" value="清空"/><input type="button" value="搜索" onclick="Page.search(requestSelection)"/></td>
                    </tr>
                </table>
            </form>
        </div>
</div>
 <div id="request-msg">
 	<span>一共有<i id="requestCount"><%=applicationCount %></i>条数据</span>
    <div class="feedback-e"><a href="javascript:Page.pageUp(requestSelection);">上一页</a> 当前第<i id="requestPage">1</i>页 <a href="javascript:Page.pageDown(requestSelection);" >下一页</a></div>
 	<div id="request">
	        <%
	        int size = applicationList.size();
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
    </div>
 </div>
	
	
	
