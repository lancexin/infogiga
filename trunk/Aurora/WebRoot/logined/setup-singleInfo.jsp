<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
 $(document).ready(function(){
 	$("#setup-tab-a").addClass("setup-tool-bar-el2");
 	$("#setup-tab-a-right").html('<span class="setup-tool-bar-title">个人设置</span>');
 	var singleInfo = $("#setup-tab-a-dialog");
	$("#setup-right-box").append(singleInfo);
	singleInfo.hide();
	
	$("#setup-tab-a").click(function(){
		$(".setup-tool-bar-el2").removeClass("setup-tool-bar-el-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-left").removeClass("setup-el-left-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-right").removeClass("setup-el-right-choosed");
		
		$(this).addClass("setup-tool-bar-el-choosed");
		$(this).find(".setup-tool-bar-el-left").addClass("setup-el-left-choosed");
		$(this).find(".setup-tool-bar-el-right").addClass("setup-el-right-choosed");
		
		
		$(".setup-right-tab").hide();
		$("#"+this.id+"-dialog").show();
	});
	
	$('#setup-base-info [name="name"]').val(userInfo.name);
	$('#setup-base-info [name="phoneNumber"]').val(userInfo.phoneNumber);
	$('#setup-base-info [name="mail"]').val(userInfo.mail);
	
	$("#setup-base-info").validate({
			rules:{
				name:{
					required:true
				},
				phoneNumber:{
					required:true,
					isMobile:true
				},
				mail:{
					required:true,
					email:true
				}
			},
			message:{
				name:{
					required:"不能为空"
				},
				phoneNumber:{
					required:"不能为空"
				},
				mail:{
					required:"不能为空",
					email:"电子邮箱格式不正确"
				}
			},
			submitHandler:function(){
				var data = $("#setup-base-info").serialize();
				$.post("updateUserCommonInfo.do",data,function(html){
					if(html == 'true'){
						userInfo.name = $('#setup-base-info [name="name"]').val();
						userInfo.phoneNumber = $('#setup-base-info [name="phoneNumber"]').val();
						userInfo.mail = $('#setup-base-info [name="mail"]').val();
						alert("修改成功！");
					
					}else{
						alert("修改失败！");
					}
				}); 
				return false;
			},
			errorPlacement:function(error, element){
				var pos = element.position();
				var height = element.height();
				var newpos = { left: pos.left, top: pos.top + height + 6 }
				var form = $("#setup-base-info");
				error.appendTo(form).css(newpos);
			},
			errorElement:"div",
			errorClass:"cusErrorPanel"
		});
		
		$("#setup-reset-password").validate({
			rules:{
				oldPassword:{
					required:true
				},
				newPassword:{
					required:true
				},
				reNewPassword:{
					required:true,
					equalTo: '#setup-reset-password [name="newPassword"]'
				}
			},
			message:{
				oldPassword:{
					required:"不能为空"
				},
				newPassword:{
					required:"不能为空"
				},
				reNewPassword:{
					required:"不能为空",
					equalTo: "两次密码输入要相同"
				}
			},
			submitHandler:function(){
				var data = $("#setup-reset-password").serialize();
				$.post("updateUserPassword.do",data,function(html){
					if(html == 'true'){
						alert("修改成功！");
						$("#setup-reset-password")[0].reset();
					
					}else{
						alert("修改失败！");
					}
				}); 
				return false;
			},
			errorPlacement:function(error, element){
				var pos = element.position();
				var height = element.height();
				var newpos = { left: pos.left, top: pos.top + height + 6 }
				var form = $("#setup-reset-password");
				error.appendTo(form).css(newpos);
			},
			errorElement:"div",
			errorClass:"cusErrorPanel"
		});
	
 });

</script>

<div class="setup-right-tab" id="setup-tab-a-dialog"  style="display:none;">
      <h3 class="setup-h3-a">个人信息</h3>
     <form id="setup-base-info">
     <ul class="setup-ul-a">	
     	<li class="setup-li-a">
         	<label for="" class="setup-label">姓名：</label>
             <input class="input-text-a" name="name" type="text"/>
         </li>
         <li class="setup-li-a">
         	<label class="setup-label">电话：</label>
             <input class="input-text-a" name="phoneNumber" type="text" />
         </li>
         <li class="setup-li-a">
         	<label class="setup-label">邮箱：</label>
            	<input class="input-text-a" name="mail" type="text" />
             <input type="submit" value="提交" />
         </li>
     </ul>
     </form>
     <h3 class="setup-h3-a">重置密码</h3>
     <form id="setup-reset-password">
     <ul class="setup-ul-a">	
     	<li class="setup-li-a">
         	<label for="" class="setup-label">原密码：</label>
             <input class="input-text-a" name="oldPassword" type="text"/>
         </li>
         <li class="setup-li-a">
         	<label class="setup-label">新密码：</label>
             <input class="input-text-a" name="newPassword" type="password" />
         </li>
         <li class="setup-li-a">
         	<label class="setup-label">再输一次：</label>
            	<input class="input-text-a" name="reNewPassword" type="password" />
             <input type="submit" value="提交" />
         </li>
     </ul>
     </form>
 </div>
