<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">
 $(document).ready(function(){
 	$("#setup-tab-f").addClass("setup-tool-bar-el2");
 	$("#setup-tab-f-right").html('<span class="setup-tool-bar-title">选项设置</span>');
 	var singleInfo = $("#setup-tab-f-dialog");
	$("#setup-right-box").append(singleInfo);
	singleInfo.hide();
	
	$("#setup-tab-f").click(function(){
		$(".setup-tool-bar-el2").removeClass("setup-tool-bar-el-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-left").removeClass("setup-el-left-choosed");
		$(".setup-tool-bar-el2 .setup-tool-bar-el-right").removeClass("setup-el-right-choosed");
		
		$(this).addClass("setup-tool-bar-el-choosed");
		$(this).find(".setup-tool-bar-el-left").addClass("setup-el-left-choosed");
		$(this).find(".setup-tool-bar-el-right").addClass("setup-el-right-choosed");
		
		
		$(".setup-right-tab").hide();
		$("#"+this.id+"-dialog").show();
	
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

</script>

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

