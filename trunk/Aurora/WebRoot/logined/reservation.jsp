<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style type="text/css">
.selected {
	background: url(images/iconbtn1.jpg) left top no-repeat;
}

li.hover {
	cursor: pointer;
}
</style>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type='text/javascript'>
function showReserve() {
	var key = arguments[0]|| 'this';
	$('#reserve-result-frame').attr('src', 'reserve.htm?key='+key);
}

$(function() {
            var $reqli = $("#requl3>li");
            $reqli.hover(
			  function () {
			    $(this).addClass("hover");
			  },
			  function () {
			    $(this).removeClass("hover");
			  }
			); 
            $reqli.click(function() {
                var $obj = $(this);
                var p = $obj.attr("param");
                if (p) {
                    $reqli.removeClass("selected");
                    $obj.addClass("selected");
                    $reqli.each(function(){
                        if($(this).is(".selected")==true)
                        {
                            $(this).css("color","#ffffff");
                        }
                        else
                        {    
                            $(this).css("color","#7298C6");
                        }
                    });
                      
                 
                    showReserve(p);
                } else {
                    alert("没有设置参数");
                }
            });
        });

	$(document).ready(function(){
		$("#head-right-menu-middle-container").append('<a href="javascript:;" id="reservation">查看日程</a>');
		
		$("#reservation").click(function() {
			$("#head-right-menu-middle-container").stop().animate(
				{"backgroundPosition":"72px 4px"},
				{"duration":"500"});
			right_menu_choosed($(this));
			$(".add-continer").hide();
			$('#reserve-dialog').show();
			
			$('#reserve-top-exit').click(function() {
		   		$("#reserve-dialog").hide();
		   	});
		   	
		   	$('#reserve-bottom-button').click(function(){
		   		$("#reserve-dialog").hide();
		   	}); 
	   });	
	 			
	   

		
	});
</script>
<div class="add-continer" id="reserve-dialog" style="display: none;">
	<div class="add-top">
		<div class="add-top-left"></div>
		<div class="add-top-center">
			<div class="add-top-title">
				<img src="Theme/Default/images/zq2admin_40.png" align="top" />
				<span class="add-top-title-new">查看日程</span>

			</div>
			<div class="add-top-exit" id="reserve-top-exit">
				<img src="Theme/Default/images/zq2admin_11.png" />
			</div>
		</div>
		<div class="add-top-right"></div>
	</div>
	<div class="add-main">
		<div class="show-main-continer">
			<div class="add-div-d">
				<div class="request-div-f">
					<ul id="requl3" class="request-ul-a">
						<li id='show-request-this' param="this">
							本周
						</li>
						<li id='show-request-last' param="last">
							上周
						</li>
						<li id='show-request-history' param="his">
							历史
						</li>
						<li id='show-request-all' param="all">
							全部
						</li>
					</ul>
				</div>
				<iframe src='reserve.htm?key=all' frameborder='0'
					id='reserve-result-frame' class='result-frame'></iframe>
			</div>
		</div>
	</div>

	<div class="add-bottom">
		<div class="add-bottom-left"></div>
		<div class="add-bottom-center">

			<div class="add-bottom-button-right">
				<span class="add-bottom-button-next" id="reserve-bottom-button">确定</span>
			</div>
		</div>
		<div class="add-bottom-right"></div>
	</div>

</div>