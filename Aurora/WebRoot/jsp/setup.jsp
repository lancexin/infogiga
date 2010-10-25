<%@ page language="java" import="java.util.*,cn.infogiga.bean.*,cn.infogiga.po.*" pageEncoding="utf-8"%>
<%
Manager userInfo = (Manager)request.getAttribute("userInfo");
%>
<script type="text/javascript">
<!--
//添加用户dialog
$("#add_user_dialog").dialog({
	resizable:false,
	bgiframe: true,
	modal: true,
	autoOpen:false,
	buttons: {
		"取消": function() {
			$(this).dialog('close');
			var addUserForm = document.getElementById("addUserForm");
			addUserForm.reset();
		},
		"确定": function() {
			var addUserForm = document.getElementById("addUserForm");
			$("#addUserForm-loading").show();
			document.getElementById("addUserForm").groupId.value = Setup.groupId;
			var date = $('form[id="addUserForm"]').serialize();
			$.post("addUser.do",date,function(html){
				$("#addUserForm-loading").hide();
				var temp = "<tr id='user"+html+"'><td id='user_name"+html+"'>"+addUserForm.name.value+"</td><td id='user_username"+html+"'>"
		  					+addUserForm.username.value+"</td><td id='user_password"+html+"'>"
		  					+addUserForm.password.value+"</td><td id='user_phoneNumber"+html+"'>"
		  					+addUserForm.phoneNumber.value+"</td><td id='user_mail"+html+"'>"
		  					+addUserForm.mail.value+"</td><td><a href='javascript:;' style='color:red;' onclick='Setup.deleteUser("+html+")'>删除</a><a href='javascript:;' style='color:red;' onclick='Setup.updateUser("+Setup.groupId.value+","+html+")'>修改</a></td></tr>";
		 		
		 		$("#addUser").append(temp);
				$("#add_user_dialog").dialog('close');
				addUserForm.reset();
			});
		}
	}
});
//删除用户提醒提示框
$("#delete_user_dialog").dialog({
	resizable:false,
	bgiframe: true,
	autoOpen:false,
	buttons: {
		"取消": function() {
			$(this).dialog('close');
		},
		"确定": function() {
			$("#deleteUserForm-loading").show();
			$.ajax({
				 type: "GET",
				 url: "deleteUser.do",
				 data:{userId:Setup.userId},
				 success:function(html){
					  if(html == 'true'){
					  	//	alert('删除成功！');
					  		$("#deleteUserForm-loading").hide();
					  		$("#delete_warning_dialog").dialog('close');
					  		var elId = "#user"+Setup.userId;
					  		$(elId).remove();
					  }else if(html == 'false'){
					  		alert('添加出现异常');
					  		$("#delete_warning_dialog").dialog('close');
					  }
				 }
			});
		}
	}
});

//修改用户信息
$("#update_user_dialog").dialog({
	resizable:false,
	bgiframe: true,
	autoOpen:false,
	buttons: {
		"取消": function() {
			$(this).dialog('close');
		},
		"确定": function() {
			$("#updateUserForm-loading").show();
			var updateUserForm = document.getElementById("updateUserForm");

			var date = $('form[id="updateUserForm"]').serialize();
			$.post("updateUser.do",date,function(html){
				 if(html == 'true'){
					// alert("修改成功！");
					 $("#updateUserForm-loading").hide();
				 	 $("#user_name"+Setup.userId).html(document.getElementById("updateUserForm").name.value);
				 	 $("#user_username"+Setup.userId).html(document.getElementById("updateUserForm").username.value);
				 	 $("#user_password"+Setup.userId).html(document.getElementById("updateUserForm").password.value);
				 	 $("#user_mail"+Setup.userId).html(document.getElementById("updateUserForm").mail.value);
				 	 $("#user_phoneNumber"+Setup.userId).html(document.getElementById("updateUserForm").phoneNumber.value);
					 $("#update_user_dialog").dialog('close');
				 }else{
				 	 alert("修改失败，请与管理员联系！");
				 	 $("#update_user_dialog").dialog('close');
				 }
			});
		}
	}
});
//添加模板信息
$("#add_templete_dialog").dialog({
	resizable:false,
	bgiframe: true,
	modal: true,
	autoOpen:false,
	width:500,
	buttons:{
		"取消":function(){
			$(this).dialog('close');
			document.getElementById('addTempleteForm').reset();
		},
		"确定":function(){
			 $("#addTempleteForm-loading").show();
			 var date = $('form[id="addTempleteForm"]').serialize();
			 $.post("addTemplete.do",date+"&templeteType="+Setup.templeteType,function(html){
			 	var addTempleteForm = document.getElementById('addTempleteForm');
			 	var templeteName = addTempleteForm.templeteName.value;
			 	var templeteContent = addTempleteForm.templeteContent.value;
				var temp = "<tr id='templete_mms"+html+"'>"+
				"<td id='templete_templeteName"+html+"' >"+templeteName+"</td>"+
				"<td id='templete_content"+html+"' >"+templeteContent+"</td>"+
				"<td><a href=\"javascript:Setup.deleteTemplete('"+Setup.templeteType+"',"+html+")\">删除</a><a href=\"javascript:Setup.updateTemplete('"+Setup.templeteType+"',"+html+")\">修改</a></td></tr>";

			 	$("#addTemplete").append(temp);	
			 
			 	$("#addTempleteForm-loading").hide();
			 	addTempleteForm.reset();
			 	$("#add_templete_dialog").dialog('close');
			 });
		}
	}
});
//删除模板信息
$("#delete_templete_dialog").dialog({
	resizable:false,
	bgiframe: true,
	modal: true,
	autoOpen:false,
	buttons:{
		"取消":function(){
			$(this).dialog('close');
		},
		"确定":function(){
			$("#deleteTempleteForm-loading").show();
			 var date = $('form[id="templeteSearchForm"]').serialize();
			 $.get('deleteTemplete.do',date+"&templeteId="+Setup.templeteId,function(html){
			 	if(html == 'true')
			 		$("#templete_"+Setup.templeteType+Setup.templeteId).remove();
			 		$("#deleteTempleteForm-loading").hide();
			 		$("#delete_templete_dialog").dialog('close');
			 });
		}
		
	}
});
//修改模板信息
$("#update_templete_dialog").dialog({
	resizable:false,
	bgiframe: true,
	modal: true,
	autoOpen:false,
	buttons:{
		"确定":function(){
			$("#updateTempleteForm-loading").show();
			var updateTempleteForm = document.getElementById("updateTempleteForm");
			
			var date = $('form[id="updateTempleteForm"]').serialize();
			$.post('updateTemplete.do',date+"&templeteType="+Setup.templeteType,function(html){
				$("#templete_"+Setup.templeteType+"_templeteName"+Setup.templeteId).html(updateTempleteForm.templeteName.value);
				$("#templete_"+Setup.templeteType+"_templeteContent"+Setup.templeteId).html(updateTempleteForm.templeteContent.value);
				$("#updateTempleteForm-loading").hide();
				$("#update_templete_dialog").dialog('close');
			});
		},
		"取消":function(){
			$(this).dialog('close');
		}
	}
});


Setup = {
	userId:0,
	groupId:0,
	templeteType:null,
	templeteId:0,
	searchUser:function(select){
		var val = select.value;
		if(val != -1){
		document.getElementById("addUserButton").disabled = false;
			$("#manager").html("正在加载请稍等....");
			$("#manager").show();
			var data = $('form[id="managerSearchForm"]').serialize();
			$.get("getManagers.do",data,function(html){
				$("#manager").html(html);
			
			});
		}else{
			document.getElementById("addUserButton").disabled = true;
		}
	},
	addUser:function(){
		this.groupId = document.getElementById("managerSearchForm").groupId.value;
		//alert("adduser");
		$("#add_user_dialog").dialog('open');
	},
	deleteUser:function(id){
		this.userId = id;
		$("#delete_warning_dialog").dialog('open');
	},
	updateUser:function(gid,id){
		this.userId = id;
		this.groupId = gid;
		document.getElementById("updateUserForm").groupId.value = gid;
		document.getElementById("updateUserForm").userId.value = id;
		document.getElementById("updateUserForm").name.value = document.getElementById("user_name"+id).innerHTML;
		document.getElementById("updateUserForm").username.value = document.getElementById("user_username"+id).innerHTML;
		document.getElementById("updateUserForm").password.value = document.getElementById("user_password"+id).innerHTML;
		document.getElementById("updateUserForm").mail.value = document.getElementById("user_mail"+id).innerHTML;
		document.getElementById("updateUserForm").phoneNumber.value = document.getElementById("user_phoneNumber"+id).innerHTML;
		$("#update_user_dialog").dialog('open');
	
	},
	updateUserInfo:function(){
		var date = $('form[id="updateBaseInfo"]').serialize();
		$.post("updateUserCommonInfo.do",date,function(html){
			if(html == 'true'){
				alert('修改成功！');
				
			}else{
				alert('修改失败！');
				
			}
		});
	},
	updateUserPassword:function(){
		var date = $('form[id="updatePassword"]').serialize();
		$.post("updateUserPassword.do",date,function(html){
			if(html == 'true'){
				alert('修改成功！');
			}else if(html == 'false'){
				alert('修改失败！');
			}else{
				alert(html);
			}
		});
	},
	searchTemplete:function(select){
		this.templeteType = select.value;
		if(this.templeteType != -1){
			document.getElementById("addTempleteButton").disabled = false;
			$("#templete").html("正在加载请稍等....");
			$("#templete").show();
			var data = $('form[id="templeteSearchForm"]').serialize();
			$.get("getTempletes.do",data,function(html){
				$("#templete").html(html);
			});
		}else{
			document.getElementById("addTempleteButton").disabled = true;
		}
	},
	addTemplete:function(){
		$("#add_templete_dialog").dialog('open');
	},
	deleteTemplete:function(type,id){
		this.templeteId = id;
		this.templeteType = type;
		$("#delete_templete_dialog").dialog('open');
	},
	updateTemplete:function(type,id){
		this.templeteId = id;
		this.templeteType = type;
		var form = document.getElementById("updateTempleteForm");
		form.templeteId.value = id;
		form.templeteName.value = document.getElementById("templete_"+type+"_templeteName"+id).innerHTML;
		form.templeteContent.value = document.getElementById("templete_"+type+"_templeteContent"+id).innerHTML;
		
		$("#update_templete_dialog").dialog('open');
	}
	
	
}
//-->
</script>
<div id="setup-bar">
	<script type="text/javascript">
		$("#setup-bar").accordion({
			autoHeight:false
		});
	</script>
  <h3><a href="#">个人信息设置</a></h3>
  <div>
    <form id="updateBaseInfo">
    	<ul>
    	<li class="setup-a">基本信息：</li>
    	<li class="setup-b">您的姓名：<input type="text" name="name" value="<%=userInfo.getName() %>"/></li>
        <li class="setup-b">您的电话：<input type="text" name="phoneNumber" value="<%=userInfo.getPhoneNumber() %>"/></li>
        <li class="setup-b">您的邮箱：<input type="text" name="mail" value="<%=userInfo.getMail() %>" /></li>
        <li class="setup-b"><input type="button" value="提交" onclick="Setup.updateUserInfo()" /><input type="button" value="重置" /></li>
        
        </ul>
    </form>
    
    <form id="updatePassword">
    	<ul>
    	<li  class="setup-a">修改密码：</li>
        <li class="setup-b">原始密码：<input name="oldPassword" type="text" /></li>
        <li class="setup-b">最新密码：<input name="newPassword" type="password" /></li>
        <li class="setup-b">重复密码：<input name="reNewPassword" type="password" /></li>
        <li class="setup-b"><input type="button" value="提交" onclick="Setup.updateUserPassword()" /><input type="button" value="重置" /></li>
    	</ul>
    </form>
  </div>
  <h3><a href="#">子帐号设置</a></h3>
  <div>
  	<ul>
	  	<li>
	  	<form id="managerSearchForm">
		  	查找：<select name="groupId" onchange="Setup.searchUser(this)">
		  	<option value="-1">选择你要设置的用户类别</option>
		  	<%
		  	List<Groups> list = (List<Groups>)request.getAttribute("groupList");
		  	int size = list.size();
		  	Groups groups;
		  	for(int i=0;i<size;i++){
		  		groups = list.get(i);
		  	%>
		  	<option value="<%=groups.getGroupId() %>"><%=groups.getGroupName() %></option>
		  	<%}%>
		  	</select><input id="addUserButton" disabled="disabled" type="button" value="添加子帐号" onclick="Setup.addUser()"/>
	  	</form>
	  	</li>
	  	<li id="manager" style="display:none;">
	  		
	  	
	  	</li>
  	</ul>
  </div>
  <h3><a href="#">默认参数设置</a></h3>
  <div>
   		<ul>
        	<form method="get" action="">
        	<%
        	Setting setting = userInfo.getSetting();
        	
        	 %>
            	<li class="setup-a">是否将宣传资料发送到用户邮箱</li>
            	<li><span class="setup-c"><input type="radio" value="1" name="a" />是</span><span  class="setup-c"><input type="radio"  value="0" name="a"  />否</span></li>
        		<li class="setup-a">是否定期将意见反馈以电子邮件形式发送到客户经理邮箱</li>
            	<li><span  class="setup-c"><input type="radio" value="1" name="b" />是</span><span  class="setup-c"><input type="radio"  value="0" name="b"  />否</span></li>
                <li class="setup-a">是否将预约请求以邮件形式发送到客户经理邮箱</li>
                <li><span  class="setup-c"><input type="radio" value="1" name="c" />是</span><span  class="setup-c"><input type="radio"  value="0" name="c"  />否</span></li>
            </form>
        </ul>
  </div>
 <h3><a href="#">宣传资料设置</a></h3>
  <div>
   		<ul>
        	<li  class="setup-a">发送方式：</li>
            <li  class="setup-f"><span class="setup-c" style="width:100px"><input type="radio" name="d" />附件发送</span><span  style="width:100px" class="setup-c"><input type="radio"  name="d" />邮件发送</span></li>
        	<li style="margin-bottom:10px;"><input type="button" value="上传附件" /></li>
            <li>附件列表：</li>
            
            <li><table>
            	<tr>
                	<td style="width:100px;text-align:center">附件名</td>
                    <td style="width:100px;text-align:center">选择发送</td>
                    <td style="width:100px;text-align:center"">选项</td>
                </tr>
                
                <tr>
                	<td style="width:100px;text-align:center"><a href="#">asdfadsf.pdf</a></td>
                    <td style="width:100px;text-align:center"><input type="radio"  name="e"/></td>
                    <td style="width:100px;text-align:center"><a href="#">删除</a></td>
                </tr>
                
                <tr>
                	<td style="width:100px;text-align:center"><a href="#">asdfadsf.pdf</a></td>
                    <td style="width:100px;text-align:center"><input type="radio"  name="e"/></td>
                    <td style="width:100px;text-align:center"><a href="#">删除</a></td>
                </tr>
            
            </table></li>
            <li><input type="button" value="保存"/></li>
        </ul>
  </div>
  <h3><a href="#">邀请模板设置</a></h3>
  <div>
      	<ul>
	  	<li>
	  	<form id="templeteSearchForm">
		  	查找：<select name="templeteType" onchange="Setup.searchTemplete(this)">
		  	<option value="-1">选择你要设置的模板类别</option>
		  	<option value="mms">彩信模板</option>
		  	<option value="mail">邮件模板</option>
		  	</select><input id="addTempleteButton" disabled="disabled" type="button" value="添加模板" onclick="Setup.addTemplete()"/>
	  	</form>
	  	</li>
	  	<li id="templete" style="display:none;"></li>
  	</ul>
  </div>
</div>


<div id="add_user_dialog" title ="添加用户信息">
	<form id="addUserForm">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>帐号：</td>
				<td><input type="text" name ="username"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="text" name="password"/></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name ="phoneNumber"/></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="mail"/></td>
			</tr>

		</table>
		<input type="hidden" name="groupId"/>
		<span id="addUserForm-loading" style="color:red;display:none;">正在添加请稍后.....</span>
	</form>
</div>

<div id="delete_user_dialog" title="删除选项">
	<p>你确定要删除该项吗?</p>
	<span id="deleteUserForm-loading" style="color:red;display:none;">正在删除请稍后.....</span>
</div>


<div id="update_user_dialog" title="修改用户信息">
	<form id="updateUserForm">
		<table>
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>帐号：</td>
				<td><input type="text" name ="username"/></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name ="phoneNumber"/></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="mail"/></td>
			</tr>
		</table>
		<input type="hidden" name="groupId"/>	
		<input type="hidden" name="userId"/>
	</form>
	<span id="updateUserForm-loading" style="color:red;display:none;">正在修改请稍后.....</span>
</div>


		<div id="add_templete_dialog" title="添加模板">
			<form id="addTempleteForm">
				<table>
					<tr>
						<td>模板名称：</td>
						<td><input type="text" name="templeteName" id="mmsTempleteName"/></td>
					</tr>

					<tr>
						<td>模板内容：</td>
						<td><textarea id="mmsContent" name="templeteContent" cols="50" rows="5"  onKeydown="El.savePos(this)" onKeyup="El.savePos(this)" onmousedown="El.savePos(this)" onmouseup="El.savePos(this)" onfocus="El.savePos(this)" ></textarea></td>
					</tr>
					<tr>
						<td>模板标签：</td>
						<td><input type="button" value="姓名" name="[name]" onclick="El.add(this);"/><input type="button" value="时间" name="[time]" onclick="El.add(this);"/><input type="button" value="地点" name="[address]" onclick="El.add(this);"/></td>
					</tr>
				</table>
			</form>
			<span id="addTempleteForm-loading" style="color:red;display:none;">正在添加请稍后.....</span>
		</div>
		
		<div id="delete_templete_dialog" title="删除模板">
			<p>你确定要删除该项吗?</p>
			<span id="deleteTempleteForm-loading" style="color:red;display:none;">正在删除请稍后.....</span>
		</div>
		
		<div id="update_templete_dialog" title="修改模板">
			<form id="updateTempleteForm">
				<table>	
					<tr style="display:none;">
						<td>彩信id：</td>
						<td><input type="text" name="templeteId"/></td>
					</tr>
				
					<tr>
						<td>彩信名称：</td>
						<td><input type="text" name="templeteName"/></td>
					</tr>
					
					<tr>
						<td>彩信内容：</td>
						<td><textarea name="templeteContent"  onKeydown="El.savePos(this)" onKeyup="El.savePos(this)" onmousedown="El.savePos(this)" onmouseup="El.savePos(this)" onfocus="El.savePos(this)"></textarea></td>
					</tr>
					<tr>
						<td>模板标签：</td>
						<td><input type="button" value="姓名" name="[name]" onclick="El.add(this);"/><input type="button" value="时间" name="[time]" onclick="El.add(this);"/><input type="button" value="地点" name="[address]" onclick="El.add(this);"/></td>
					</tr>
				</table>
			</form>
			<span id="updateTempleteForm-loading" style="color:red;display:none;">正在修改请稍后.....</span>
		</div>	
