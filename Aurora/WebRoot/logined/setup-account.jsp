<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
 $(document).ready(function(){
 	
 	$("#setup-tab-c").addClass("setup-tool-bar-el2");
 	$("#setup-tab-c-right").html('<span class="setup-tool-bar-title">管理帐号</span>');
 	var singleInfo = $("#setup-tab-c-dialog");
	$("#setup-right-box").append(singleInfo);
	singleInfo.hide();
	
	$("#setup-tab-c").click(function(){
 		$(".setup-tool-bar-el2").removeClass("setup-tool-bar-el-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-left").removeClass("setup-el-left-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-right").removeClass("setup-el-right-choosed");
		
		$(this).addClass("setup-tool-bar-el-choosed");
		$(this).find(".setup-tool-bar-el-left").addClass("setup-el-left-choosed");
		$(this).find(".setup-tool-bar-el-right").addClass("setup-el-right-choosed");
		
		
		$(".setup-right-tab").hide();
		$("#"+this.id+"-dialog").show();
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
						alert(html.error);
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
				dataType:"json",
				error:function(xhr,states,errorThrown){
					alert("删除失败,该用户可能已经在系统中使用");
				},
				success:function(data, textStatus){
					if(data.success){
						alert("删除成功");
						$("#setup-manager-info")[0].reset();
						$('#setup-manager-info [name="managerId"]').val("-1");
						$(".setup-ul-c-click").remove();
						//$("#setup-manager-list").empty();
					}else{
						alert(data.error);
					}
				}
			});
		});
 });

</script>


 <div class="setup-right-tab" style="display:none;" id="setup-tab-c-dialog" style="display:none;">
        <form id="setup-manager-info">
        <div class="setup-manage-account">
        	<span class="setup-span-a">帐号列表</span>
            <span class="setup-span-b">请选择帐号类别：</span>
            <select name="managerType">
            	<option value="-1" selected="selected">请选择...</option>
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