<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
<!--
    op.quickadd = function(start, end, isallday, pos){
    	//alert(this.url);
       	  var _this = this;
       	 // alert(_this.view);
       	  if(_this.view == "month"){
    		  $("#span-month-add-time").show();
    	  }else{
    	  	  $("#span-month-add-time").hide();
    	  }
          if ((!_this.quickAddHandler && _this.quickAddUrl == "") || _this.readonly) {
              return;
          }
          
          $('#add-title-form [name="startTime"]').val(start.Format("yyyy-MM-dd HH:mm"));
          $('#add-title-form [name="endTime"]').val(end.Format("yyyy-MM-dd HH:mm"));
       
          var dateshow = _this.CalDateShow(start, end, !isallday, true);
          $(".add-cal-timeshow").html(dateshow);
          $(".add-top-exit").unbind("click");
          $(".add-top-exit").click(function() {
              $("#add-calendar").hide();
              _this.realsedragevent();
          });            
          $("#add-bottom-button-next-two").unbind("click");
          $("#add-bottom-button-next-two").click(function(){ 
          		//alert(_this.isloading);                                     
          		if (_this.isloading) {
                     return false;
                }
                _this.isloading = true;
                 // alert(_this.view);
                if(_this.view == "month"){
                  		var t = ($("#add-title-form").find('[name="startTime"]').val()).split(" ");
						//alert(t[0]);
						var tt = t[0]+" "+$("#month-add-time").val();
						$("#add-title-form").find('[name="startTime"]').val(tt);
				 }
				//alert($("#add-title-form").find('[name="startTime"]').val());
                str = $("#add-title-form").serialize();
                     //alert(str);         
				str = str+"&"+$("#add-info-form").serialize();
				$.each($("#add-customer-box .add-tr-b"),function(i,n){
					str =str+"&"+$(n).data("url");
				});
				var newdata = [];
                var tId = -1;
		
                 _this.onBeforeRequestData && _this.onBeforeRequestData(2);
               	$.post(_this.quickAddUrl, str, function(data) {
                     if (data) {
                     	//alert(data.IsSuccess);
                         if (data.IsSuccess == true) {
                             _this.isloading = false;
                             _this.eventItems[tId][0] = data.Data;
                             _this.eventItems[tId][8] = 1;
                             _this.render();
                             _this.onAfterRequestData && _this.onAfterRequestData(2);
                         }else {
                             _this.onRequestDataError && _this.onRequestDataError(2, data);
                             _this.isloading = false;
                             _this.onAfterRequestData && _this.onAfterRequestData(2);
                         }

                     }

                 }, "json");
		                										
				 newdata.push(-1, $('#add-title-form [name="calendarTitle"]').val());
	                  var sd = _this.strtodate($('#add-title-form [name="startTime"]').val());
	                  var ed = _this.strtodate($('#add-title-form [name="endTime"]').val());
	                  var guider = $('#add-info-form [name="guider"]').val();
	                  var technician = $('#add-info-form [name="technician"]').val();
	                  //alert(guider);
	                  if(guider == -1 || -1 == parseInt(guider)){
	                  	guider = defaultGuider.name;
	                  //	alert(guider);
	                  }
	                  
	                  if(technician == -1 || -1 == parseInt(technician)){
	                  	technician = defaultTechincian.name;
	                  }
	                  
	                  var diff = DateDiff("d", sd, ed);
	                  newdata.push(sd, ed, 0, diff > 0 ? 1 : 0, 0);
	                  newdata.push(-1, 0, "", "",guider,technician,""); //主题,权限,参与人,接待人员,维护人员,创建人员
	                  
	                  var customer = [];
	                  $.each($("#add-customer-box .add-tr-b"),function(i,n){
					  customer.push($(n).data("param"));
				 });
	                  newdata.push(customer);
	                  tId = _this.Ind(newdata);
	                  _this.realsedragevent();
	                  _this.render();
				
				$("#add-calendar").hide();					
		});		
	
          this.resetAddCalendar();	
          return false;
    }


$(function(){


	$("#add-custromer-boxx").setScroll( //scrollContent为滚动层的ID
		{img:"Theme/Default/images/scroll_bg.gif",width:10},//背景图及其宽度
		{img:"",height:3},//up image
		{img:"",height:3},//down image
		{img:"Theme/Default/images/scroll_bar.gif",height:25}//bar image
	);

	//添加预约，第一步，点击下一步
	$("#add-bottom-button-next-one").click(function(){
			$('form[id="add-title-form"]').submit();
	}); 
	//添加预约，第二步，点击上一步
	$("#add-bottom-button-prev-two").click(function(){
			$(".add-customer").empty();
			$("#add-step-one").show();
			$("#add-step-two").hide();
			$("#add-bottom-button-next-one").show();
			$("#add-bottom-button-prev-two").hide();
			$("#add-bottom-button-next-two").hide();																					
	});
	
	//添加预约第一步，点击 添加一个顾客
	$("#add-customer-next").click(function(){
			$('form[id="add-customer-form"]').submit();
	});
	//为展开，收缩用户列表添加事件
	$("#open-customer-list").click(function(){
			$("#open-customer-list").hide();
			$("#close-customer-list").show();
			$("#add-customer-list .add-customer").show();
	});
	
	$("#add-info-form").find('[name="guider"]').change(function(){	
		var el = $("#add-info-form").find('[name="guider"]');
		var id = parseInt(el.val());
		if(id == -1){
			$(".guider").html("");
		}else{
			$(".guider").html(el.find('option:selected').text());
		}									
	});
	
	$("#add-info-form").find('[name="technician"]').change(function(){
		var el = $("#add-info-form").find('[name="technician"]');
		var id = parseInt(el.val());
		if(id == -1){
			$(".technician").html("");
		}else{
			$(".technician").html(el.find('option:selected').text());
		}										   
	});
	
	$("#add-info-form").find('[name="mmsContent"]').change(function(){													
		var el = $("#add-info-form").find('[name="mmsContent"]');
		var id = parseInt(el.val());
		if(id == -1){
			$(".mmsContent").html("");
		}else{
			
			$(".mmsContent").html(el.find('option:selected').data("dt").content);
		}		
	});
	
	$("#close-customer-list").click(function(){
		$("#open-customer-list").show();
		$("#close-customer-list").hide();
		$("#add-customer-list .add-customer").hide();
	});	
	
	//添加预约第一步， 顾客添加验证
	 $('form[id="add-customer-form"]').validate({
		rules:{
			name:"required",
			phoneNumber:{
				required:true,
				minlength:6,
				maxlength:11,
				digits:true
			},
			company:"required"
		},
		messages:{
			name:"顾客姓名不能为空",
			phoneNumber:{
				required:"电话号码不能为空",
				minlength:"不能小于6位数",
				maxlength:"不能大于11位数",
				digits:"只能是整数"
			},
			company:"公司名称不能为空"
		
		},
		submitHandler: function() {
			var customerForm = $('form[id="add-customer-form"]');
			var name = customerForm.find('[name="name"]').val();
			var phoneNumber = customerForm.find('[name="phoneNumber"]').val();
			var company = customerForm.find('[name="company"]').val();			
			addCustomer(customerForm, name, phoneNumber, company);
		},
		errorPlacement: function(error, element) {
			//showerror(error, element);
			
			var pos = element.position();
			var height = element.height();
			var newpos = { left: pos.left, top: pos.top + height + 6 }
			var form = $("#add-customer-form");
			error.appendTo(form).css(newpos);
		},
		errorElement:"div",
		errorClass:"cusErrorPanel"
	});
		
	//添加预约，标题验证
	$('form[id="add-title-form"]').validate({
		rules:{
			title:"required"
		},
		messages:{
			title:"标题不能为空"
		},
		errorElement:"div",
		errorClass:"cusErrorPanel",
		errorPlacement: function(error, element) {
			//showerror(error, element);
			
			var pos = element.position();
			var height = element.height();
			var newpos = { left: pos.left, top: pos.top + height + 6 }
			var form = $("#add-title-form");
			error.appendTo(form).css(newpos);
		},
		submitHandler:function(){
			
			var length = $("#add-customer-box .add-tr-b").length;
			if(length == 0){
				alert("至少要有一个客户！");
			} else if(!/^[^\$\<\>]+$/.test($('#add-title-form [name="calendarTitle"]').val())) {
				alert("日程标题不能为空且不能包含符号($<>)");
                $('#add-title-form [name="calendarTitle"]').focus();
			} else {
			
				
				var title = $("#add-title-form").find('[name="calendarTitle"]').val();
				//alert(title);
				$("#add-submit-title").html(title);
				$("#customer-count").html($("#add-customer-box .add-tr-b").length);
				$.each($("#add-customer-box .add-tr-b"),function(i,n){
					$("#add-customer-list").append('<li class="add-customer" style="display:none;"><span class="add-span-h">'
												   +$(n).data("param").name+'</span></li>');
				});
				
				$("#add-step-one").hide();
				$("#add-step-two").show();
				$("#add-bottom-button-next-one").hide();
				$("#add-bottom-button-prev-two").show();
				$("#add-bottom-button-next-two").show();
			}
		}
	});
	
	new AjaxUpload('upload-customer-file', {
        action: 'upload.up',
		onSubmit : function(file , ext){
               // Allow only images. You should add security check on the server-side.
			if (ext && /^(txt)$/.test(ext)){
				/* Setting data 
				this.setData({
					'key': 'This string will be send with the file'
				});	*/				
				//$('#example2 .text').text('Uploading ' + file);	
			} else {					
				// extension is not allowed
				alert("上传格式不正确");
				// cancel upload
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
			}
			
			for(var index = 0;index < count; index++) {
				var customer = customers[index];
				var cInfoArray = customer.split('|');
				var name = cInfoArray[0];
				var phoneNumber = cInfoArray[1];
				var company = cInfoArray[2];
				if(!name || !phoneNumber || !/^\d{6,11}$/.test(phoneNumber)) continue;
				addCustomer(customerForm, name, phoneNumber, company);
			}	
		}		
	});
	
});

function addCustomer(customerForm, name, phoneNumber, company) {
	if($("#add-title-form .add-input-a[name='calendarTitle']").val() == '') { //自动添加标题
		$("#add-title-form .add-input-a[name='calendarTitle']").val(name+ '等参观');
	}
	var count = parseInt($("#customerCount").html());
	$("#customerCount").html(count+1);
	var str = '<tr class="add-tr-b">'+
			'<td align="center" class="add-td-c">'+
				'<div class="add-customer-delete"><img src="Theme/Default/images/zq2admin_21.gif" /></div>'+
			'</td>'+
			'<td >'+
			'<table>'+
				'<tr >'+
					'<td width="185" class="add-td-f">'+
						'<span class="add-span-f">'+
							'<span class="add-span-name">'+name+'</span>'+
							'<span>'+phoneNumber+'</span>'+
						'</span>'+
					'</td>'+
				'</tr>'+
				'<tr>'+ 
					'<td  class="add-td-g">'+
						'<span class="add-span-f">'+company+'</span>'+
					'</td>'+
				'</tr>'+
			'</table>'+
			'</td>'+
		'</tr>';
		var el = $(str);
		//var data = customerForm.serialize();
		var data = "name="+name+"&phoneNumber="+phoneNumber+"&company="+company;

		el.data("url",data);
		el.data("param",{"name":name,"phoneNumber":phoneNumber,"company":company,"states":0});
		el.find(".add-customer-delete").click(function(){
			el.remove();
			var count = parseInt($("#customerCount").html());
			if(count == 1){
				$("#customerCount").html("0");
				$("#add-title-form .add-input-a[name='calendarTitle']").val('');
			}else{
				$("#add-title-form .add-input-a[name='calendarTitle']").val($('.add-span-f .add-span-name:first').text()+'等参观');
				$("#customerCount").html(count-1);
			}
		});
		$("#add-customer-box").append(el);
		//alert($("#add-customer-box .add-tr-b").length);
		//$.each($("#add-customer-box .add-tr-b"),function(i,n){
		//		alert($(n).data("param").name);										 
		//});
		
		document.getElementById("add-customer-form").reset();
}

//-->
</script>
 <div class="add-continer" id="add-calendar" style="display:none;">
	<div class="add-top">
    	<div class="add-top-left"></div>
        <div class="add-top-center">
          <div class="add-top-title">
       		<img src="Theme/Default/images/zq2admin_40.png" align="top"/>
                <span class="add-top-title-new">新建预约  </span>
                <span class="add-top-title-new2">轻松邀请您的客户</span>
          </div>
          <div class="add-top-exit"><img src="Theme/Default/images/zq2admin_11.png"/></div>
        </div>
        <div class="add-top-right"></div>
    </div>
    <div class="add-main" >
  <div style="margin-top:15px;margin-bottom:5px;" id="add-step-one">
            <table class="add-main-continer add-main-continer2">
                <tr class="add-tr-a">
                    <td width="90"  class="add-td-a"><span class="add-span-a">预约时间</span></td>
                    <td colspan="2"><span class="add-span-a add-cal-timeshow">2010年11月23日（周日） 10：30</span>
                    <span id="span-month-add-time" style="display:none;"><select id="month-add-time">
                    	<option>00:00</option><option>00:30</option>
                    	<option>01:00</option><option>01:30</option>
                    	<option>02:00</option><option>02:30</option>
                    	<option>03:00</option><option>03:30</option>
                    	<option>04:00</option><option>04:30</option>
                    	<option>05:00</option><option>05:30</option>
                    	<option>06:00</option><option>06:30</option>
                    	<option selected="selected">07:00</option><option>07:30</option>
                    	<option>08:00</option><option>08:30</option>
                    	<option>09:00</option><option>09:30</option>
                    	<option>10:00</option><option>10:30</option>
                    	<option>11:00</option><option>11:30</option>
                    	<option>12:00</option><option>12:30</option>
                    	<option>13:00</option><option>13:30</option>
                    	<option>14:00</option><option>14:30</option>
                    	<option>15:00</option><option>15:30</option>
                    	<option>16:00</option><option>16:30</option>
                    	<option>17:00</option><option>17:30</option>
                    	<option>18:00</option><option>18:30</option>
                    	<option>19:00</option><option>19:30</option>
                    	<option>20:00</option><option>20:30</option>
                    	<option>21:00</option><option>21:30</option>
                    	<option>22:00</option><option>22:30</option>
                    	<option>23:00</option><option>23:30</option>
                    </select></span>
                    </td>
                   
                </tr>
                <tr class="add-tr-a">
                    <td ><span class="add-span-a">预约名称</span></td>
                    <td ><form id="add-title-form">
                    	<input class="add-input-a" name="calendarTitle" type="text" />
                        <input class="add-input-a" id='startTime' name="startTime" type="hidden" value="2010-11-23 10:30"/>
                        <input class="add-input-a" id='endTime' name="endTime" type="hidden" value="2010-11-23 10:30"/>
                    </form></td>
                    <td ></td>
                </tr>
                <tr  class="add-tr-a">
                    <td colspan="3">
                        <span class="add-span-a">设置邀请名单</span>
                        <span class="add-span-c">
                        	名单中共有<span id="customerCount">0</span>人 <span class="add-span-b">(至少添加1人)</span>
                        </span>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" valign="top" class="add-td-b">
                        
                          <table cellpadding="0" cellspacing="0" class="add-table-a">
                            <tr>
                                <td  valign="top"  >
                                	<form id="add-customer-form">
                                    	<ul class="add-ul-a">
                                            <li class="add-li-a">
                                              <span class="add-span-d">客户姓名</span>
                                                <input class="add-input-b" name="name" type="text" />
                                            </li>
                                            <li class="add-li-a">
                                              <span class="add-span-d">手机号码</span>
                                                <input class="add-input-b" name="phoneNumber" type="text" />
                                            </li>
                                            <li class="add-li-a">
                                              <span class="add-span-d">公司名称</span>
                                                <input class="add-input-b" name="company" type="text" />
                                            </li>
                                         </ul>
                                    </form>
                                     
                                    <div style="width:200px">
                                    	<span class="add-span-e">Tips: 输入麻烦? 从<a id="upload-customer-file" style="cursor: pointer;" href="javascript:;">文件导入</a>名单吧.</span>
                                    	
                                    	</div>
                                </td>
                                <td valign="top">
                                    <div class="add-div-b" id="add-customer-next">
                                    	<img src="Theme/Default/images/zq2admin_18.gif" />
                                    </div>
                                </td>
                                <td  valign="top" width="250">
                                  <div  class="add-div-c" id="add-custromer-boxx">
                                 
                                        <table  id="add-customer-box">
                                        
                                            
                                            
                                     	</table>
                                    </div>
                                </td>
                            </tr>
                        </table>
                        
                    
                    </td>
                </tr>
            
            </table>
        </div>
        
  <div id="add-step-two" style="display:none;">
			<div class="add-main-left">
        	<ul class="add-ul-c" id="add-customer-list">
            	<li class="add-li-c">
                	<span class="add-span-g">预约时间</span>
                </li>
                
                <li>
               		<span class="add-span-h add-cal-timeshow">2010年11月23日<br />(周三)上午 10:30</span> 
                </li>
                
                <li class="add-li-c">
                	<span class="add-span-g">预约名称</span>
                </li>
                
                <li>
                	<span class="add-span-h" id="add-submit-title">预约</span>
                </li>
                
                <li class="add-li-c">
                	<span>
                    	<span class="add-span-g">邀请名单</span>
                        <span class="add-span-h">共<span id="customer-count">6</span>人</span><br />
                        <span class="add-span-h">
                        	<a id="open-customer-list" href="javascript:;">[展开]</a>
                            <a id="close-customer-list" href="javascript:;" style="display:none;">[关闭]</a>
                        </span>
                    </span>
                </li>
            </ul>
        </div>
        
            <div class="add-main-right">
                <form id="add-info-form">
                	<ul class="add-ul-e">
            	<li class="add-li-c">
                	<span  class="add-span-g">
                    选择接待人员
                	<select name="guider">
                    	<option value="-1" selected="selected">选择默认</option>
                    </select>
                    </span>
                </li>
                <li>
                	<span class="add-span-h guider"></span>
               </li>
                <li class="add-li-c">
                	<span  class="add-span-g">
                    	选择服务人员
                    <select name="technician">
                    	<option value="-1" selected="selected">选择默认</option>
                    </select>
                    </span>
                </li>
                <li>
                	<span class="add-span-h technician"></span>
                </li>
                <li class="add-li-c">
                	<span  class="add-span-g">
                	选择彩信内容
                    <select name="mmsContent">
                    	<option value="-1" selected="selected">选择默认</option>
                    </select>
                    </span>
                </li>
                <li><textarea disabled="disabled" name="" rows="4" cols="40" class="mmsContent"></textarea></li>
            </ul>
                
                </form>
                
            </div>
        
        </div>
		
    
	</div>
    
    <div class="add-bottom">
    	<div class="add-bottom-left"></div>
        <div class="add-bottom-center">
        	<div class="add-bottom-button-right" id="add-bottom-button-next-one"><span class="add-bottom-button-next">下一步</span></div>
          <div class="add-bottom-button-left" id="add-bottom-button-prev-two" style="display:none;"><span class="add-bottom-button-prev">上一步</span></div>
       	  <div class="add-bottom-button-right" id="add-bottom-button-next-two" style="display:none;"><span class="add-bottom-button-next">完成</span></div>
      	</div>
        <div class="add-bottom-right"></div>
    </div>

</div>
