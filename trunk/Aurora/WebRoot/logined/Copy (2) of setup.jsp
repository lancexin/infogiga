<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
	$(document).ready(function(){
		$(".setup-tool-bar-el2").click(function(){
			//alert(this.id);
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
		
		
		
		
		
		$("#setup-manager-info").validate({
			rules:{
				name:{
					required:true
				},
				username:{
					required:true
				},
				password:{
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
				username:{
					required:"不能为空"
				},
				password:{
					required:"不能为空"
				},
				phoneNumber:{
					required:"不能为空"
				},
				mail:{
					required:"不能为空"
				}
			},
			submitHandler:function(){
				var data = $("#setup-manager-info").serialize();
				$.post("updateUser.do",data,function(html){
					if(html.type == "update" && html.success == true){
						alert("保存成功");
						$("#setup-manager-list .setup-ul-c").data("data",html.data);
					}else if(html.type == "insert" && html.success == true){
						alert("添加成功");
						$("#setup-manager-info")[0].reset();
						$('#setup-manager-info [name="managerId"]').val(html.data.managerId);
						/**
						if(html.data.type ==  parseInt($('#setup-manager-info [name="managerType"]'))){
							
							var el =$('<div class="setup-ul-c">'+n.name+'</div>');
							el.data("data",html.data);
							el.click(function(){
								//alert("dddd");
								$(this).addClass("setup-ul-c-click");
								var data = el.data("data");
								$('#setup-manager-info [name="managerId"]').val(data.managerId);
								$('#setup-manager-info [name="name"]').val(data.name);
								$('#setup-manager-info [name="username"]').val(data.username);
								$('#setup-manager-info [name="password"]').val(data.password);
								$('#setup-manager-info [name="phoneNumber"]').val(data.phoneNumber);
								$('#setup-manager-info [name="mail"]').val(data.mail);
								$('#setup-manager-info [name="type"]').attr("value",data.type);
								
							});
							$("#setup-manager-list").append(el);
							el.addClass("setup-ul-c-click");
							
						}*/
					}else{
						alert("添加失败");
					}
				
				},"json"); 
				return false;
			},
			errorPlacement:function(error, element){
				var pos = element.position();
				var height = element.height();
				var newpos = { left: pos.left, top: pos.top + height + 6 }
				var form = $("#setup-manager-info");
				error.appendTo(form).css(newpos);
			},
			errorElement:"div",
			errorClass:"cusErrorPanel"
		
		});
		
		
		$("#setup-templete").validate({
			rules:{
				templeteName:{
					required:true
				},
				templeteContent:{
					required:true
				}
			},
			message:{
				templeteName:{
					required:"不能为空"
				},
				templeteContent:{
					required:"不能为空"
				}
			},
			submitHandler:function(){
				var type = $(".setup-manage-templete").data("type");
				if(type == null){
					alert("必须先选择一个类型");
					return;
				}
				var data = $("#setup-templete").serialize();
				$.post("updateTemplete.do",data+"&type="+type,function(html){
					if(html.type == "update"){
						alert("修改成功");
						//alert(html.data);
						//alert($("#setup-templete-list .setup-ul-c-click").data("data").name);
						$("#setup-templete-list .setup-ul-c-click").html(html.data.name);
						$("#setup-templete-list .setup-ul-c-click").data("data",html.data);
						//alert(html.data.name);
						//alert($("#setup-templete-list .setup-ul-c-click").data("data").name);
						
					}else if(html.type == "insert"){
						alert("添加成功");
						var el = $('<div class="setup-ul-c">'+html.data.name+'</div>');
						el.data("data",html.data);
						el.click(function(){
							$("#setup-templete-list .setup-ul-c-click").removeClass("setup-ul-c-click");
							el.addClass("setup-ul-c-click");
							var data = el.data("data");
							$('#setup-templete [name="templeteName"]').val(html.data.name);
							$('#setup-templete [name="templeteContent"]').val(html.data.content);
							$('#setup-templete [name="templeteId"]').val(html.data.templeteId);
						});
						$("#setup-templete-list").append(el);
					}
				
				},"json");
				
				return false;
			},
			errorPlacement:function(error, element){
				var pos = element.position();
				var height = element.height();
				var newpos = { left: pos.left, top: pos.top + height + 6 }
				var form = $("#setup-templete");
				error.appendTo(form).css(newpos);
			},
			errorElement:"div",
			errorClass:"cusErrorPanel"
		});
		
		$('#setup-manager-info [name="managerType"]').change(function(){
			var v = $('#setup-manager-info [name="managerType"]').val();
			$("#update-manager-title").text("删除/修改/查看帐号");
			if(v != -1 && v != "-1"){
				$.post("getManagers.do","managerType="+v,function(data){
					//alert(data);
					$("#setup-manager-list .setup-ul-c").unbind();
					$("#setup-manager-list").empty();
					
					$.each(data,function(i,n){
						var el =$('<div class="setup-ul-c">'+n.name+'</div>');
						el.data("data",n);
						el.click(function(){
							//alert("dddd");
							$("#setup-manager-list .setup-ul-c").removeClass("setup-ul-c-click");
							$(this).addClass("setup-ul-c-click");
							var data = el.data("data");
							$('#setup-manager-info [name="managerId"]').val(data.managerId);
							$('#setup-manager-info [name="name"]').val(data.name);
							$('#setup-manager-info [name="username"]').val(data.username);
							$('#setup-manager-info [name="password"]').val(data.password);
							$('#setup-manager-info [name="phoneNumber"]').val(data.phoneNumber);
							$('#setup-manager-info [name="mail"]').val(data.mail);
							$('#setup-manager-info [name="type"]').attr("value",data.type);
							
						});
						$("#setup-manager-list").append(el);
					});
					
					//alert(data.length);
					if(data.length != 0){
						$("#setup-manager-list .setup-ul-c:first").addClass("setup-ul-c-click");
						$('#setup-manager-info [name="managerId"]').val(data[0].managerId);
						$('#setup-manager-info [name="name"]').val(data[0].name);
						$('#setup-manager-info [name="username"]').val(data[0].username);
						$('#setup-manager-info [name="password"]').val(data[0].password);
						$('#setup-manager-info [name="phoneNumber"]').val(data[0].phoneNumber);
						$('#setup-manager-info [name="mail"]').val(data[0].mail);
						$('#setup-manager-info [name="type"]').attr("value",data[0].type);
					}
					
				},"json");
			}
		});
		
		
		//点击添加帐号按钮
		$("#add-account-button").click(function(){
			$("#update-manager-title").text("添加帐号");
			$('#setup-manager-info')[0].reset();
			$('#setup-manager-info [name="managerId"]').val("-1");
			$("#setup-manager-list").empty();
		});
		
		
		//点击删除帐号按钮
		$("#delete-manager").click(function(){
			var managerId = $('#setup-manager-info [name="managerId"]').val();
			if(managerId == ""){
				return;
			}
			if(parseInt(managerId) == -1){
				alert("你还未保存");
				return;
			}
			
			$.ajax({
				data:"managerId="+managerId,
				type:"get",
				url:"deleteUser.do",
				error:function(xhr,states,errorThrown){
					alert("删除失败,该用户是否已经在系统中使用？");
				},
				success:function(data, textStatus){
					if(data == "true"){
						alert("删除成功");
						$("#setup-manager-info")[0].reset();
						$('#setup-manager-info [name="managerId"]').val("-1");
						$(".setup-ul-c-click").remove();
						//$("#setup-manager-list").empty();
					}else{
						alert("删除失败");
					}
				}
			});
		});
		
		
		//点击删除模板按钮
		$("#setup-delete-templete-button").click(function(){
			var templeteId = $('#setup-templete [name="templeteId"]').val();
			var type = $(".setup-manage-templete").data("type");
			if(parseInt(templeteId) == -1){
				alert("你还未保存");
				return;
			}
		
			$.ajax({
				data:"templeteId="+templeteId+"&type="+type,
				type:"get",
				url:"deleteTemplete.do",
				error:function(xhr,states,errorThrown){
					alert("删除失败,该用户是否已经在系统中使用？");
				},
				success:function(data, textStatus){
					if(data == "true"){
						alert("删除成功");
						$("#setup-templete")[0].reset();
						$('#setup-templete [name="templeteId"]').val("-1");
						$("#setup-templete-list .setup-ul-c-click").remove();
						//$("#setup-manager-list").empty();
					}else{
						alert("删除失败");
					}
				}
			});
		
		});
		
		//彩信模板 或 邮件模板选择
		$(".setup-div-b").click(function(){
			$("#update-templete-title").text("查看/修改/删除模板");
			$(".setup-div-b").removeClass("setup-div-b-click");
			$(this).addClass("setup-div-b-click");
			//alert($(this).attr("id"));
			if($(this).attr("id") == "setup-mail-button"){
				$(".setup-manage-templete").data("type","mail");
				$("#setup-templete-list").empty();
				$.post("getTempletes.do","type=mail",function(html){
					//alert(html);
					$.each(html.data,function(i,n){
						var el = $('<div class="setup-ul-c">'+n.name+'</div>');
						//alert(n);
						el.data("data",n);
						el.click(function(){
							$("#setup-templete-list .setup-ul-c").removeClass("setup-ul-c-click");
							el.addClass("setup-ul-c-click");
							var data = el.data("data");
							$('#setup-templete [name="templeteName"]').val(data.name);
							$('#setup-templete [name="templeteContent"]').val(data.content);
							$('#setup-templete [name="templeteId"]').val(data.templeteId);
							
						});
						$("#setup-templete-list").append(el);
					});
					
					if(html.data[0] != null){
						$("#setup-templete-list .setup-ul-c:first").addClass("setup-ul-c-click");
						$('#setup-templete [name="templeteName"]').val(html.data[0].name);
						$('#setup-templete [name="templeteContent"]').val(html.data[0].content);
						$('#setup-templete [name="templeteId"]').val(html.data[0].templeteId);
					}
				},"json");
				
			}else if($(this).attr("id") == "setup-mms-button"){
				$(".setup-manage-templete").data("type","mms");
				$.post("getTempletes.do","type=mms",function(html){
					//alert(html);
					$("#setup-templete-list").empty();
					$("#setup-templete-list .setup-ul-c").unbind();
					$.each(html.data,function(i,n){
						var el = $('<div class="setup-ul-c">'+n.name+'</div>');
						//alert(n);
						el.data("data",n);
						el.click(function(){
							$("#update-templete-title").text("查看/修改/删除模板");
							$("#setup-templete-list .setup-ul-c").removeClass("setup-ul-c-click");
							el.addClass("setup-ul-c-click");
							var data = el.data("data");
							$('#setup-templete [name="templeteName"]').val(data.name);
							$('#setup-templete [name="templeteContent"]').val(data.content);
							$('#setup-templete [name="templeteId"]').val(data.templeteId);
						});
						$("#setup-templete-list").append(el);
					});
					if(html.data[0] != null){
						$("#setup-templete-list .setup-ul-c:first").addClass("setup-ul-c-click");
						$('#setup-templete [name="templeteName"]').val(html.data[0].name);
						$('#setup-templete [name="templeteContent"]').val(html.data[0].content);
						$('#setup-templete [name="templeteId"]').val(html.data[0].templeteId);
					}
				},"json");
				
			}
		});
		
		//点击添加模板
		$("#setup-add-templete-button").click(function(){
			$("#update-templete-title").text("添加模板");
			$('#setup-templete')[0].reset();
			$('#setup-templete [name="templeteId"]').val("-1");
			$("#setup-templete-list").empty();
		});
		
		
		$("#setup-option").validate({
			submitHandler:function(){
				if($("#defaultGuider").val() == "-1" || 
					$("#defalutTechincian").val() == "-1" ||
					$("#defaultMmsTemplete").val() == "-1" || 
					$("#defaultMailTemplete").val() == "-1"){
					
					alert("必须选择");
					return false;
				}
				var data = $("#setup-option").serialize();
				$.post("updateSettingOption.do",data,function(html){
					if(html){
						alert("修改成功");
					}
				});
				return false;
			}
		});
		
		$("#defaultGuider").ajaxSelect({
			url:"getSelectManagers.do?groupId=3",
			value:"managerId",
			text:"name"
		});
		
		$("#defalutTechincian").ajaxSelect({
			url:"getSelectManagers.do?groupId=4",
			value:"managerId",
			text:"name"
		});
		
		$("#defaultMailTemplete").ajaxSelect({
			url:"getSelectMailTemplete.do",
			value:"templeteId",
			text:"name"
		});
		
		$("#defaultMmsTemplete").ajaxSelect({
			url:"getSelectMmsTemplete.do",
			value:"templeteId",
			text:"name"
		});
		
		
	});
//-->
</script>
<div class="add-continer" id="setup-dialog" style="display:none;">
	<div class="add-top">
    	<div class="add-top-left"></div>
        <div class="add-top-center">
          <div class="add-top-title">
          		<img src="Theme/Default/images/zq2admin_40.png" align="top"/>
                <span class="add-top-title-new">设置</span>
          </div>
          <div class="add-top-exit" id="setup-add-top-exit"><img src="Theme/Default/images/zq2admin_11.png"/></div>
        </div>
        <div class="add-top-right"></div>
    </div>
    <div class="add-main" >
		<div class="setup-left">
        	<div class="setup-tool-middle"></div>
        	<div class="setup-tool-bar">
            	<div class="setup-tool-bar-middle"></div>
            	<div class="setup-tool-bar-el setup-tool-bar-el2 setup-tool-bar-el-choosed" id="setup-tab-a">
                	<div class="setup-tool-bar-el-left setup-el-left-choosed"></div>
                    <div class="setup-tool-bar-el-right setup-el-right-choosed" id="setup-tab-a-right">
                    	<span class="setup-tool-bar-title">个人设置</span>
                    </div>
                </div>
                
                <div class="setup-tool-bar-middle"></div>
                <div class="setup-tool-bar-el setup-tool-bar-el2"  id="setup-tab-c">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-c-right">
                    	<span class="setup-tool-bar-title">管理帐号</span>
                    </div>
                </div>
                
                <div class="setup-tool-bar-middle"></div>
                <div class="setup-tool-bar-el setup-tool-bar-el2"  id="setup-tab-d">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-d-right">
                    	<span class="setup-tool-bar-title">模板设置</span>
                    </div>
                </div>
            
                <div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-el setup-tool-bar-el2"  id="setup-tab-f">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-f-right">
                    	<span class="setup-tool-bar-title">选项设置</span>
                    </div>
                </div>
               <div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-el" id="setup-tab-e">
                	<div class="setup-tool-bar-el-left"></div>
                    <div class="setup-tool-bar-el-right" id="setup-tab-e">
                    	
                    </div>
                </div>
                <div class="setup-tool-bar-middle setup-tab-b"></div>
                <div class="setup-tool-bar-middle setup-tab-b"></div>
            </div>
        	 <div class="setup-tool-middle"></div>
        </div>
        <div class="setup-right" id="setup-right-box">
        
        	<div class="setup-right-tab" id="setup-tab-a-dialog">
            	
                
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
           
            
            <div class="setup-right-tab" style="display:none;" id="setup-tab-c-dialog">
            <form id="setup-manager-info">
            <div class="setup-manage-account">
            	<span class="setup-span-a">帐号列表</span>
                <span class="setup-span-b">请选择帐号类别：</span>
                <select name="managerType">
                	<option value="-1" selected="selected">请选择...</option>
            		<option value="1">管理员</option>
                    <option value="2">客户经理</option>
                    <option value="3">接待人员</option>
                    <option value="4">维护人员</option>
                </select>
                <br />
                <h4 class="setup-h4-a" id="update-manager-title">删除/修改/查看帐号</h4>
            </div>
            
            <div class="setup-manage">
            	<div class="setup-manage-detailed">
                	<ul class="setup-ul-b">
                        <li class="setup-li-b">
                        	<label class="setup-label-b">姓名</label>
                            <input class="setup-inupt-a" type="text" name="name"/>
                            <input class="setup-inupt-a" type="hidden" value="-1" name="managerId"/>
                        </li>
                        <li class="setup-li-b">
                        	<label class="setup-label-b">类别</label>
                            <select name="type">
			            		<option value="1">管理员</option>
			                    <option value="2">客户经理</option>
			                    <option value="3">接待人员</option>
			                    <option value="4">维护人员</option>
			                </select>
                        </li>
                        <li class="setup-li-b">
                        	<label class="setup-label-b">帐号</label>
                            <input class="setup-inupt-a" type="text" name="username"/>
                         </li>
                        <li class="setup-li-b">
                        	<label class="setup-label-b">密码</label>
                            <input class="setup-inupt-a" type="text" name="password"/>
                        </li>
                        <li class="setup-li-b">
                        	<label class="setup-label-b">手机</label>
                            <input class="setup-inupt-a" type="text" name="phoneNumber"/>
                        </li>
                        <li class="setup-li-b">
                        	<label class="setup-label-b">邮箱</label>
                            <input class="setup-inupt-a" type="text" name="mail"/>
                        </li>
                        <li class="setup-li-c">
                        	<span class="setup-span-c"><input type="submit" value="保存"/></span>
                        	<span class="setup-span-c"><input id="delete-manager" type="button" value="删除"/></span>
                            
                        </li>
                    </ul>
                </div>
                <div class="setup-account-list">
                	<div class="setup-add-account"><a href="javascript:;" id="add-account-button">+添加帐号</a></div>
                    <div class="setup-account-list-body" id="setup-manager-list">
                    
                    
                    </div>

                </div>
            </div>
           
            </form>
       	   </div>
    
      
      		<div class="setup-right-tab setup-right-tab2" style="display:none;" id="setup-tab-d-dialog">
            	<div class="setup-manage-account">
            		<span class="setup-span-a">模板类型</span>
           		</div>
            	
                <div class="setup-manage-account setup-manage-templete">
                	<div class="setup-div-b" id="setup-mail-button"><span class="setup-span-e">邮件模板</span></div>
                	<div class="setup-div-b" id="setup-mms-button"><span >彩信模板</span></div>
                </div>
                
                <div>
                	<div >
                    	<h4 class="setup-h4-b" id="update-templete-title">查看/修改/删除模板</h4>
                        <div class="setup-div-c">
                        	<form id="setup-templete">
                        	<ul class="setup-ul-d">
                            	<li class="setup-li-e">模板名称</li>
                                <li><input name="templeteName" type="text" />
                                <input name="templeteId" value="-1" type="hidden" /></li>
                                <li class="setup-li-e">模板内容</li>
                                <li><textarea name="templeteContent" cols="23" rows="3"></textarea></li>
                                <li>
                                	<span class="setup-span-d">可用标签:</span>
                                	<span class="setup-span-d">[<a href="javascript:;">姓名</a>]</span>
                                    <span class="setup-span-d">[<a href="javascript:;">时间</a>]</span>
                                    <span class="setup-span-d">[<a href="javascript:;">地址</a>]</span>
                                   
                                </li>
                                <li class="setup-li-f">
                                	<span class=""><input class="setup-span-c" type="submit" value="保存" /></span>
                                    <span><input class="setup-span-c" id="setup-delete-templete-button" type="button" value="删除" /></span>
                                </li>
                            </ul>
                            </form>
                        </div>
                    
                    </div>
                    
                    
               		<div class="setup-account-list">
                        <div class="setup-add-account"><a id="setup-add-templete-button" href="javascript:;">+添加模板</a></div>
                        <div class="setup-account-list-body" id="setup-templete-list">
                        	
                        
                        </div>
                    </div>
                </div>
            
            </div>
      	
      		
            
			<div class="setup-right-tab " style="display:none;" id="setup-tab-e-dialog">
            	<div class="setup-manage-account setup-div-e">
                    <span class="setup-span-a">宣传资料列表</span>
                    <span class="setup-span-b"><a href="javascript：;">上传资料</a></span>
                </div>
                <div>
                 
          </div>
               
                <div class="setup-div-d">
                	<table width="260">
                    	<tr>
                        	<td width="105">资料名</td>
                       	    <td width="84">是否发送</td>
                            <td width="55">操作</td>
                        </tr>
                    </table>
                	<div class="setup-div-f">
                    	<table>
                        	<tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                            
                            <tr>
                                <td width="105">某某某资料</td>
                                <td width="90"><input type="checkbox" /></td>
                                <td width="55"><a href="javascript:;">删除</a></td>
                            </tr>
                        
                        
                       </table>
                    
                    
                  </div>
</div>
            
            
            </div>
   
         <div class="setup-right-tab" id="setup-tab-f-dialog" style="display:none;">
	      	<form id="setup-option">
	        	<ul class="setup-ul-e">
	            	<li class="setup-li-g">
	                	<span>选择默认的接待人员</span>
	                    <select name="defaultGuider" id="defaultGuider">
	                    	<option value="-1" selected="selected">请选择...</option>
	                    </select>
	                </li>
	                
	                <li class="setup-li-g">
	                	<span>选择默认的维护人员</span>
	                    <select name="defalutTechincian" id="defalutTechincian">
	                    	<option value="-1" selected="selected">请选择...</option>
	                    </select>
	                </li>
	                
	                <li class="setup-li-g">
	                	<span>选择默认发送的彩信模板</span>
	                    <select name="defaultMmsTemplete" id="defaultMmsTemplete">
	                    	<option value="-1" selected="selected">请选择...</option>
	                    </select>
	                </li>
	                
	                <li class="setup-li-g">
	                	<span>选择默认发送的邮件模板</span>
	                    <select name="defaultMailTemplete" id="defaultMailTemplete">
	                    	<option value="-1" selected="selected">请选择...</option>
	                    </select>
	                </li>
	                
	                <li class="setup-li-g">
	                	<span>参观完后是否发送宣传资料到参观者邮箱</span><br />
	                    <input type="radio" name="sendMailAfterVisit" value="1"  />是
	                    <input type="radio" name="sendMailAfterVisit" checked="checked" value="0"  />否
	                </li>
	                
	                <li>
	                	<input type="submit" value="完成" />
	                </li>
	            
	            </ul>
	        </form>
	      
	      
	      </div>   
	</div>
            
            
   </div>
        
        
    
    <div class="add-bottom">
    	<div class="add-bottom-left"></div>
        <div class="add-bottom-center">
        	
        	<div class="add-bottom-button-right" id="setup-button-exit"><span class="add-bottom-button-next">退出</span></div>
      </div>
        <div class="add-bottom-right"></div>
    </div>

</div>