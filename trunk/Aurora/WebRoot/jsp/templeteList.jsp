<%@ page language="java" import="java.util.*,cn.infogiga.bean.*" pageEncoding="utf-8"%>
<%
String templeteType = (String)request.getAttribute("templeteType");
%>





<table id="addTemplete">
	<tr>
		<td>模板名称</td>
		<td>模板内容</td>
		<td>设置</td>
	</tr>

	<%if("mms".equals(templeteType)){ 
		List<Mmstemplate> templeteList = (List<Mmstemplate>)request.getAttribute("templeteList");
		int size = templeteList.size();
		Mmstemplate template;
		for(int i=0;i<size;i++){
		template = templeteList.get(i);
	%>
		<tr id="templete_mms<%=template.getMmstemplateId() %>">
			<td id="templete_mms_templeteName<%=template.getMmstemplateId() %>" ><%=template.getTemplateName() %></td>
			<td id="templete_mms_templeteContent<%=template.getMmstemplateId() %>" ><%=template.getContent() %></td>
			
			<td><a href="javascript:Setup.deleteTemplete('<%=templeteType %>',<%=template.getMmstemplateId() %>);">删除</a><a href="javascript:Setup.updateTemplete('<%=templeteType %>',<%=template.getMmstemplateId() %>);">修改</a></td>
		</tr>
	
	
	<%}}else if("mail".equals(templeteType)){ 
		List<MailTemplate> templeteList = (List<MailTemplate>)request.getAttribute("templeteList");
		int size = templeteList.size();
		MailTemplate template;
		for(int i=0;i<size;i++){
		template = templeteList.get(i);
	%>
		<tr id="templete_mail<%=template.getMailTemplateId() %>">
			<td id="templete_mail_templeteName<%=template.getMailTemplateId() %>" ><%=template.getTemplateName() %></td>
			<td id="templete_mail_templeteContent<%=template.getMailTemplateId() %>" ><%=template.getContent() %></td>
			
			<td><a href="javascript:Setup.deleteTemplete('<%=templeteType %>',<%=template.getMailTemplateId() %>);">删除</a><a href="javascript:Setup.updateTemplete(<%=templeteType %>,<%=template.getMailTemplateId() %>);">修改</a></td>
		</tr>
	<%}}%>
	
	
</table>