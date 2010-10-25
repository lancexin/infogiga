<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
<!--
    op.dayshow=function(e, data) {
      // alert(data == undefined);
          var _this = this;
          if (data != null) {
              if (_this.quickDeleteUrl != "" && data[8] == 1 && _this.readonly != true) {
                 
                  var ss = [];
                  var iscos = DateDiff("d", data[2], data[3]) != 0;
                  ss.push(data[2].Format("M 月d 日"), " (周", _this.getCHNUM()[data[2].getDay()], ")");
                  if (data[4] != 1) {
                      ss.push(",", data[2].Format("HH:mm"));
                  }

                  if (iscos) {
                      ss.push("-", data[3].Format("M 月d 日"), " (周", _this.getCHNUM()[data[3].getDay()], ")");
                      if (data[4] != 1) {
                          ss.push(",", data[3].Format("HH:mm"));
                      }
                  }
                  //加载时间
                  $("#showDate").html(ss.join(""));
                  //加载标题
                  $("#showTitle").html(data[1]);
                  //加载id
                  $("#showId").val(data[0]);
                  
                  var ee = [];
                  for(var i=0;i<_this.eventItems.length;i++){
                  	if(_this.eventItems[i][0] == data[0]){
                  		ee = _this.eventItems[i];
                  		break;
                  	}
                  }
                  $("#showCustomerBox").empty();
                	var box = $("#showCustomerBox");
                	
                	$.each( ee[14], function(i, n){
                		
                		var tempEl =$(document.createElement('div'));
                		tempEl.addClass("add-div-h");
                		tempEl.html('<div class="add-div-h-left"></div>'+
                      '<div class="add-div-h-center">'+
                      	'<div class="add-div-h-a">'+
                          	'<ul class="add-ul-e add-span-i" >'+
                              	'<li class="add-li-f">'+n.name+'<span class="add-span-i">'+n.phoneNumber+'</span></li>'+
                                  '<li class="add-li-f">'+n.company+'</li>'+
                              '</ul>'+
                          '</div>'+
                          '<div class="add-div-h-b"></div>'+
                      '</div>'+
                      '<div class="add-div-h-right"></div>');
                	
			  	if(n.states == 0){
			  		tempEl.find(".add-div-h-b").css({
			  			"background-image":"url(Theme/Default/images/zq2admin_73.gif)"
			  		});
			  	}else{
			  		tempEl.find(".add-div-h-b").css({
			  			"background-image":"url(Theme/Default/images/zq2admin_66.gif)"
			  		});
			  	}
                    
                box.append(tempEl); 
			});  
                                  
                  $("#showCustomerCount").html(ee[14].length);
                  //加载接待人员
                  $("#showGuider").html(ee[11]);
                  //加载维护员
                  $("#showTechnician").html(ee[12]);
                  $(".add-top-exit").unbind("click");
                  $(".add-top-exit").click(function() {
                $("#show-calendar").hide();
            });
            
            $(".add-continer").hide();
      			$("#show-calendar").show();

              } else {
                  if (!_this.ViewCmdhandler) {
                      alert("参数ViewCmdhandler没有配置");
                  }
                  else {
                      if (_this.ViewCmdhandler && $.isFunction(_this.ViewCmdhandler)) {
                      	
                          _this.ViewCmdhandler.call(this, data);
                      }
                  }
              }
          }else {
              alert("数据格式错误！");
          }
          return false;
      } 
      
      $(document).ready(function(){
      	      $("#showOK").click(function(){
			   		$("#show-calendar").hide();
			   });
      });
//-->
</script>
<div class="add-continer" id="show-calendar" style="display: none;">
	<div class="add-top">
		<div class="add-top-left"></div>
		<div class="add-top-center">
			<div class="add-top-title">
				<img src="Theme/Default/images/zq2admin_40.png" align="top" />
				<span class="add-top-title-new">查看预约</span>

			</div>
			<div class="add-top-exit">
				<img src="Theme/Default/images/zq2admin_11.png" />
			</div>
		</div>
		<div class="add-top-right"></div>
	</div>
	<div class="add-main">
		<div class="show-main-continer">
			<div class="add-div-d">
				<div class="add-div-f">
					<ul class="add-ul-e">
						<li class="add-li-d">
							预约时间
							<span class="add-span-i" id="showDate">1月27日(周日) 上午10:31</span>
						</li>
						<li class="add-li-d">
							预约名称
							<span class="add-span-i" id="showTitle">某某某次预约</span>
						</li>
						<li class="add-li-d">
							邀请名单
							<span class="add-span-i add-span-j">(共<span
								id="showCustomerCount">6</span>人)</span>
						</li>
					</ul>
				</div>
				<div class="add-div-g">
					<ul class="add-ul-e">
						<li class="add-li-d add-li-e">
							接待人员:
							<span class="add-span-i" id="showGuider"></span>
						</li>
						<li class="add-li-d add-li-e">
							维护人员:
							<span class="add-span-i" id="showTechnician"></span>
						</li>

					</ul>
				</div>
				<div class="add-div-e" id="showCustomerBox">



				</div>



			</div>


		</div>
		<input type="hidden" id="showId" />
	</div>

	<div class="add-bottom">
		<div class="add-bottom-left"></div>
		<div class="add-bottom-center">

			<div class="add-bottom-button-right">
				<span class="add-bottom-button-next" id="showOK">确定</span>
			</div>
		</div>
		<div class="add-bottom-right"></div>
	</div>

</div>
