<%@ page language="java" pageEncoding="utf-8"%>
<style type="text/css">
.selected {
	background: url(images/iconbtn1.jpg) left top no-repeat;
}

li.hover {
	cursor: pointer;
}
</style>

<script type='text/javascript'>
/*$(document).ready(function(){
	$(".request-show-box").jscroll({ 
			W:"15px"//设置滚动条宽度
	       ,BgUrl:"url(Theme/Default/images/scrollbar_bg.gif)"//设置滚动条背景图片地址
	       ,Bg:"right 0 repeat-y"//设置滚动条背景图片position,颜色等
	       ,Bar:{Pos:"bottom" //设置滚动条初始化位置在底部
	       ,Bd:{Out:"#a3c3d5",Hover:"#b7d5e6"} //设置滚动滚轴边框颜色：鼠标离开(默认)，经过
	       ,Bg:{Out:"-45px 0 repeat-y",Hover:"-58px 0 repeat-y",Focus:"-71px 0 repeat-y"}} //设置滚动条滚轴背景：鼠标离开(默认)，经过，点击
	       ,Btn:{btn:true //是否显示上下按钮 false为不显示
	             ,uBg:{Out:"0 0",Hover:"-15px 0",Focus:"-30px 0"} //设置上按钮背景：鼠标离开(默认)，经过，点击
	             ,dBg:{Out:"0 -15px",Hover:"-15px -15px",Focus:"-30px -15px"}} //设置下按钮背景：鼠标离开(默认)，经过，点击
	       ,Fn:function(){} //滚动时候触发的方法
	});
});*/

function showRequest(p) {
	var key = arguments[0]|| 'this';
	$('#request-result-frame').attr('src', 'request.htm?key='+key);
}

$(function() {
            var $reqli = $("#requl2>li");
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
                    showRequest(p);
                } else {
                    alert("没有设置参数");
                }
            });
        });

$(function() {
        
	$("#head-right-menu-middle-container").append('<a href="javascript:;" id="request">预约请求</a>');

    $("#request").click(function() {
		$("#head-right-menu-middle-container").stop().animate(
			{"backgroundPosition":"0px 4px"},
			{"duration":"500"});
		right_menu_choosed($(this));
		$(".add-continer").hide();
		$("#request-dialog").show();
		
		$('#request-top-exit').click(function() {
	   		$("#request-dialog").hide();
	   	});
	  	$('#request-bottom-button').click(function(){
	   		$("#request-dialog").hide();
	   	});
   });
   
	//$('.request-show-box').bind('scroll', scrollBase);

	$('.request-show-box').bind('scroll', scrollBase);

	
	//$('.request-div-e').bind('scroll', scrollResult);
	
	$('#request-search-clear').bind('click', clearText);	
	
	function scrollBase() {
		var scrollTop = $(this).scrollTop();
		var height = 0;
		var index = $('.request-show-base').index($('.request-show-base').filter(function() {
			height = $(this).outerHeight(true)+ height;
			if(scrollTop <= height) {
				return true;
			} 
		}));
		var h = 0;
		$('.request-show-result').each(function() {
			if(index>0) {
				h = h+ $(this).outerHeight(true);
				index--;
			}			
		});
		
		$('.request-div-e').scrollTop(h);
	}
	
	function scrollResult() {
		var scrollTop = $(this).scrollTop();
		var height = 0;
		var index = $('.request-show-result').index($('.request-show-result').filter(function() {
			height = $(this).outerHeight(true)+ height;
			if(scrollTop <= height) {
				return true;
			} 
		}));
		var h = 0;
		$('.request-show-base').each(function() {
			if(index > 0) {
				h = h+ $(this).outerHeight(true);
				index--;
			}			
		});
		$('.request-show-box').scrollTop(h);
	}
	
	function clearText() {
		$('#request-search-text').val('');
	}
})
</script>
<div class="add-continer" id="request-dialog" style="display: none;">
	<div class="add-top">
		<div class="add-top-left"></div>
		<div class="add-top-center">
			<div class="add-top-title">
				<img src="Theme/Default/images/zq2admin_40.png" align="top" />
				<span class="add-top-title-new">预约请求</span>

			</div>
			<div class="add-top-exit" id="request-top-exit">
				<img src="Theme/Default/images/zq2admin_11.png" />
			</div>
		</div>
		<div class="add-top-right"></div>
	</div>
	<div class="add-main">
		<div class="show-main-continer">
			<div class="add-div-d">
				<div class="request-div-f">
					<ul id="requl2" class="request-ul-a">
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
				<iframe src='request.htm?key=all' frameborder='0'
					id='request-result-frame' class='result-frame'></iframe>
			</div>
		</div>
	</div>

	<div class="add-bottom">
		<div class="add-bottom-left"></div>
		<div class="add-bottom-center">

			<div class="add-bottom-button-right">
				<span class="add-bottom-button-next" id="request-bottom-button">确定</span>
			</div>
		</div>
		<div class="add-bottom-right"></div>
	</div>

</div>
