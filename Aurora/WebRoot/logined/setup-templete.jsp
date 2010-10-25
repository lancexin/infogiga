<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
 $(document).ready(function(){
 	$("#setup-tab-d").addClass("setup-tool-bar-el2");
 	$("#setup-tab-d-right").html('<span class="setup-tool-bar-title">模板设置</span>');
 	var singleInfo = $("#setup-tab-d-dialog");
	$("#setup-right-box").append(singleInfo);
	singleInfo.hide();
	
	
	$("#setup-tab-d").click(function(){
 		$(".setup-tool-bar-el2").removeClass("setup-tool-bar-el-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-left").removeClass("setup-el-left-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-right").removeClass("setup-el-right-choosed");
		
		$(this).addClass("setup-tool-bar-el-choosed");
		$(this).find(".setup-tool-bar-el-left").addClass("setup-el-left-choosed");
		$(this).find(".setup-tool-bar-el-right").addClass("setup-el-right-choosed");
		
		
		$(".setup-right-tab").hide();
		$("#"+this.id+"-dialog").show();
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
 });
    function getById(id) { return document.getElementById(id); }

    // 在光标处插入字符串
    // myField文本框对象
    // 要插入的值
    function insertAtCursor(myField, myValue) 
    {
        if (document.selection) 
        {
            myField.focus();
            sel= document.selection.createRange();
            sel.text= myValue;
            sel.select();
        }
        else if (myField.selectionStart || myField.selectionStart == '0') 
        {
            var startPos= myField.selectionStart;
            var endPos= myField.selectionEnd;
            // save scrollTop before insert
            var restoreTop= myField.scrollTop;
            myField.value= myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
            if (restoreTop > 0)
            {
                myField.scrollTop = restoreTop;
            }
            myField.focus();
            myField.selectionStart= startPos + myValue.length;
            myField.selectionEnd= startPos + myValue.length;
        } else {
            myField.value += myValue;
            myField.focus();
        }
    }
</script>

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
             	<form  id="setup-templete">
             	<ul class="setup-ul-d">
                 	<li class="setup-li-e">模板名称</li>
                     <li><input name="templeteName" type="text" />
                     <input name="templeteId" value="-1" type="hidden" /></li>
                     <li class="setup-li-e">模板内容</li>
                     <li><textarea name="templeteContent" id="templeteContent" cols="23" rows="3"></textarea></li>
                     <li>
                     	<span class="setup-span-d">可用标签:</span>
                     	<span class="setup-span-d">[<a href="javascript:void(0);"  onclick="insertAtCursor(document.getElementById('templeteContent'), '[姓名]');">姓名</a>]</span>
                         <span class="setup-span-d">[<a href="javascript:void(0);" onclick="insertAtCursor(document.getElementById('templeteContent'),  '[时间]');">时间</a>]</span>
                         <span class="setup-span-d">[<a href="javascript:void(0);" onclick="insertAtCursor(document.getElementById('templeteContent'),  '[地点]');">地点</a>]</span>
                         <span class="setup-span-d">[<a href="javascript:void(0);" onclick="insertAtCursor(document.getElementById('templeteContent'),  '[url]');">url</a>]</span>
                        
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
