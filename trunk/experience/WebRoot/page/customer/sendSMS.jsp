<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发送短信</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="page/css/admin.css" type="text/css" rel="stylesheet">
	<LINK href="page/css/add-style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>plugin/jquery-1.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/ajaxupload.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugin/validate/jquery.validate.js"></script>
	<script type="text/javascript">
		$(function(){
		
			var l = $("#sms-context").val().length;
			$("#context-count").html(70-l);
			
			new AjaxUpload('upload-sms-customer', {
		        action: 'upload?customer',
				onSubmit : function(file , ext){
		               // Allow only images. You should add security check on the server-side.
					if (ext && /^(txt)$/.test(ext)){
						return true;	
					} else if(ext && /^(xls)$/.test(ext)){					
						return true;				
					}else{
						alert("上传格式不正确");
						return false;
					}		
				},
				onComplete : function(file, response){
					//$('#example2 .text').text('Uploaded ' + file);	
					//alert(response);	
					if(response == "false"){
						alert("上传失败");
						return;
					}
					if(response == "error"){
						alert("格式不正确");
						return;
					}
					var customerForm = $('form[id="add-customer-form"]');
					var customers = response.split(';');
					var count = customers.length - 1;//待添加的客户数		
					
					if(count <= 0) {
						alert('客户列表为空，请检查后重新上传');
						return;
					}
					
					for(var index = 0;index < count; index++) {
						var customer = customers[index];
						var cInfoArray = customer.split(' ');
						var name = cInfoArray[0];
						var phoneNumber = cInfoArray[1];
						if(!name || !phoneNumber || !/^\d{6,11}$/.test(phoneNumber)) continue;
						var ra = Math.round(Math.random()*10000);
						var el = $("<tr>"+
	    					"<td><input size='5' type='text' name='sendName' value='"+name+"'/></td>"+
	    					"<td><input size='12' type='text' name='sendPhone' value='"+phoneNumber+"'/></td>"+
	    					"<td ><a onclick='removeSelf(this);' href='javascript:;'>删除</a></td>"+
	    				"</tr>");
						$("#sms-customer-list").append(el);
						
					}	
					
				}		
			});
			
			$("#add-sms-customer").click(function(){
				var el = $("<tr>"+
	    					"<td><input size='5' type='text' name='sendName' /></td>"+
	    					"<td><input size='12' type='text' name='sendPhone' /></td>"+
	    					"<td ><a onclick='removeSelf(this);' href='javascript:;'>删除</a></td>"+
	    				"</tr>");
				$("#sms-customer-list").append(el);
			});
			
			$('form[id="updateForm"]').validate({
				rules:{
					context:"required",
					sendName:"required",
					sendPhone:"required"
				},
				messages:{
					context:"信息不能为空",
					sendName:"接收人姓名不能为空",
					sendPhone:"接收人手机不能为空"
				}
			});
			

		});
		
		function removeSelf(el){
			$(el).parent().parent().remove();
		}
		
		function contextChange(el){
			var value = $(el).val();
			var l = $(el).val().length;
			if(l >70){
				alert("最多70个字");
				$(el).val(value.substring(0,70));
				$("#context-count").html(0);
			}else{
				$("#context-count").html(70-l);
			}
			
		}
		
		
	</script>
  </head>
  
  <body>
    <div>
			<div>
				<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center
					border=0>
					<TR height=28>
						<TD background=images/title_bg1.jpg>
							发送短信
						</TD>
					</TR>
					<TR>
						<TD bgColor=#b1ceef height=1></TD>
					</TR>
					<TR height=20>
						<TD background=images/shadow_bg.jpg></TD>
					</TR>
				</TABLE>
			</div>
			<div id="context">
				<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
					<TR height=100>
						<TD width=60>
							&nbsp;
						</TD>
						<TD>
						<TD colSpan=3>
  	   <form action="exeAdd?sendSMS" method="post" id="updateForm">
    	<table>
    		<tr>
    			<td><font color="blue"><strong>接收手机：</strong></font></td>
    		</tr>
    		<tr>
    			<td>[<a id="upload-sms-customer" href="javascript:;"><font color="red">文件导入</font></a>][<a href="upload/sms_txt_templete.txt"><font color="red">txt文件范例下载</font></a>][<a href="upload/sms_excel_templete.xls"><font color="red">excel文件范例下载</font></a>]</td>
    		</tr>
    		<tr>
    			<td><table id="sms-customer-list">
    				<tr>
    					<td width="100">姓名</td>
    					<td width="100">手机</td>
    					<td width="100">操作</td>
    				</tr>
    				
    			</table></td>
    		</tr>
    		<tr>
    			<td><a id="add-sms-customer" href="javascript:;"><font color="red">添加发送人</font></a></td>
    		</tr>
    		<tr>
    			<td><font color="blue"><strong>短信内容：</strong></font></td>
    		</tr>
    		<tr>
    			<td><textarea onpropertychange="contextChange(this);" oninput="contextChange(this);" id="sms-context" name="context" cols="50" rows="5">尊敬的{name}您好,您的手机号码是{phoneNumber},欢迎您体验M-Store订购营销平台</textarea></td>
    		</tr>
    		<tr>
    			<td><font color="red">(客户姓名用{name},客户手机用{phoneNumbar})</font></td>
    		</tr>
    		<tr>
    			<td>每条短信最多70个字,您还可以输入<font  color="red"><span id="context-count">70</span></font>个字</td>
    		</tr>
    		
    		<tr>
    			<td>
    			<input value="发送" type="submit"/></td>
    		</tr>
    	</table>
    
    </form>
   	</TD>
					</TR>
				</TABLE>
			</div>
		</div>
  </body>
</html>
