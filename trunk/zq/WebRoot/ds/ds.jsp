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
    
    <title>短信发送配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
	<script type="text/javascript">
		$(function(){
			$("#add-context").click(function(){
			/*	var str = "<tr>"+
		    		"<td><input class='id' type='hidden' value='-1'/><input class='code' type='text'/></td>"+
		    		"<td><input class='phoneNumber' type='text' /></td>"+
		    		"<td><input class='context' size='100' type='text'/></td>"+
		    		"<td><a href='javascript:;' onclick='del(this);' style='margin-right: 10px'>删除</a><a href='javascript:;' onclick='saveOrUpdate(this);'>保存</a></td>"+
		    	"</tr>";*/
		   		 var str = "<tr>"+
	    			"<td><input class='id' type='hidden' value='-1'/><input class='phoneNumber' type='text' value='${item.phoneNumber}'/></td>"+
		    		"<td><input class='code' type='text'/></td>"+
		    		"<td><input class='context' type='text'/></td>"+
		    		"<td><input class='context2' type='text' value='${item.context2}'/></td>"+
		    		"<td><a href='javascript:;' onclick='del(this);' style='margin-right: 10px'>删除</a><a href='javascript:;' onclick='saveOrUpdate(this);'>保存</a></td>"+
		    	"</tr>";
		    	
		    	$("#containner").append(str);
			});
		});
		
		function del(el){
			var id = $(el).parent().parent().find(".id").val();
			
			//alert(id+"  "+code+"  "+context);
			if(id == -1){
				$(el).parent().parent().remove();
			}else{
				var d = "id="+id;
				$.ajax({
				   type: "POST",
				   url: "bx/delete",
				   data: d,
				   success: function(msg){
				     if(msg == '0'){
				     	alert("删除成功");
				     	$(el).parent().parent().remove();
				     }else{
				     	alert("删除失败");
				     }
				   }
				});
			}
		}
		
		function saveOrUpdate(el){
			var id = $(el).parent().parent().find(".id").val();
			var code = $(el).parent().parent().find(".code").val();
			var context = $(el).parent().parent().find(".context").val();
			var context2 = $(el).parent().parent().find(".context2").val();
			var phoneNumber = $(el).parent().parent().find(".phoneNumber").val();
			//alert("saveOrUpdate "+id+"  "+code+"  "+context);
			if(code == ""){
				alert("编码1不能为空");
				return;
			}
			if(context == ""){
				alert("内容1不能为空");
				return;
			}
			if(context2 == ""){
				alert("内容2不能为空");
				return;
			}
			if(phoneNumber == ""){
				alert("手机号码不能为空");
				return;
			}
			var d = "id="+id+"&code="+code+"&context="+context+"&context2="+context2+"&phoneNumber="+phoneNumber;
			$.ajax({
			   type: "POST",
			   url: "bx/addOrUpdate",
			   data: d,
			   success: function(msg){
			     alert(msg);
			   }
			});
		}
	
	</script>

  </head>
  
  <body>
    <h2>短信发送配置</h2><hr/>
    <a id="add-context" href="javascript:;">添加配置</a>
    <table id="containner">
    	<tr>
    		<td>手机号</td>
    		<td>标识码</td>
    		<td>内容1</td>
    		<td>内容2</td>
    		<td>操作</td>
    	</tr>
    	
    	<c:forEach items="${list}" var="item" varStatus="count">
    		<tr>
    			<td><input class="id" type="hidden" value="${item.smsConfigId}"/><input class="phoneNumber" type="text" value="${item.phoneNumber}"/></td>
	    		<td><input class="code" type="text" value="${item.code}"/></td>
	    		<td><input class="context" type="text" value="${item.context}"/></td>
	    		<td><input class="context2" type="text" value="${item.context2}"/></td>
	    		<td><a href="javascript:;" onclick="del(this);" style="margin-right: 10px">删除</a><a href="javascript:;" onclick="saveOrUpdate(this);">保存</a></td>
	    	</tr>
    	</c:forEach>

    </table>
  </body>
</html>
