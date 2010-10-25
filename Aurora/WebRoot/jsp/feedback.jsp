<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
Integer feedBackCount = (Integer)request.getAttribute("commentCount");
List<Comment> commentList = (List<Comment>)request.getAttribute("commentList");
%>

<script type="text/javascript">
		$("#feedback_search_bar").accordion({
			autoHeight:false,
			collapsible:true
		});
		
		$("#feedbackTromTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		
		$("#feedbackToTime").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd' 
		});
		feedbackSelection.allPage =<%=feedBackCount%>
</script>
<div id="feedback_search_bar">
        <h3><a href="#">搜索</a></h3>
        <div>
        	<form id="feedbackSearchForm">
            	<table>
                	<tr>
                		<td>姓名：<input name="name"  style="width:100px"  type="text"/></td>
                        <td>电话：<input name="phoneNumber"  style="width:100px"  type="text"/></td>
                        <td>时间：<input id="feedbackTromTime" style="width:80px" name="fromTime" type="text"/>~<input id="feedbackToTime" style="width:80px" name="toTime" type="text"/></td>
                        
                    </tr>
                    <tr>
                    	<td>公司：<input name="company"  style="width:100px"  type="text"/></td>
                    	<td>每页显示：<select name="pageCount">
                        	<option value="5">5</option>
                        	<option value="10">10</option>
                            <option value="15">15</option>
                            <option value="20">20</option>
                        </select></td>
                        
                        <td style="text-align:center"><input type="reset" value="清空" /><input type="button" value="搜索" onclick="Page.search(feedbackSelection)"/></td>
                    </tr>
                </table>
            </form>

        </div>
        </div>
    	
        <div id="feedback-msg">
        	<span>一共有<i id="feedbackCount"><%=feedBackCount %></i>条数据</span>
        	<div class="feedback-e"><a href="javascript:Page.pageUp(feedbackSelection);">上一页</a> 当前第<i id="feedbackPage">1</i>页 <a href="javascript:Page.pageDown(feedbackSelection);" >下一页</a></div>
	        <div id="feedback">
		        <%
		        int size = commentList.size();
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
	        </div>
        </div>
