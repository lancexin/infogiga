<%@ page language="java"
	import="java.util.*,cn.infogiga.bean.Invitation,cn.infogiga.bean.Customer,cn.infogiga.util.DateUtil"
	pageEncoding="utf-8"%>
<html>
	<head>
		<link href="Theme/Default/request.css" rel="stylesheet"
			type="text/css" />
		<script src="Javascripts/jquery.min.js" type="text/javascript"></script>
		<style type="text/css">
.selected {
	background: url(images/iconbtn2.jpg) left top no-repeat scroll;
}

li.hover {
	cursor: pointer;
}
</style>
	</head>
	<%
		List<Invitation> list = (List<Invitation>) request
				.getAttribute("invitations");
		int count = list.size();
	%>
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
    $(document).ready(function() {
        var $list = $("#list>li>div");
        var $result = $("#result>div");
       
        function search(value) {
            var pat = new RegExp(value);
            $list.each(function(i) {
                var s = $(this), r = $result.get(i);
                var e = pat.test(s.text());
                if (e) {
                    s.parent().show();
                    $(r).show();
                } else {
                     s.parent().hide();
                     $(r).hide();
                }
            });
        }

        $("#request-search-text").keyup(function() {
            search(this.value);
        });
        
         function searchbyli(value) {
            var pat = new RegExp(value);
            $list.each(function(i) {
                var s = $(this), r = $result.get(i);
                var e = pat.test(s.text());
                if (e) {
                    s.parent().show();
                    $(r).show();
                } else {
                     $(r).hide();
                }
            });
        }
        
        $list.click(function() {
                    var $obj = $(this);
                    $list.removeClass("selected");
                    $obj.addClass("selected");
                    searchbyli($(this).text());
            }); 
            $("#list>li").hover(
			  function () {
			    $(this).addClass("hover");
			  },
			  function () {
			    $(this).removeClass("hover");
			  }
			);            
    });
$(function() {
	//$('.request-show-box').bind('scroll', scrollBase);
	
	$('.request-div-e').bind('scroll', scrollResult);
	
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
	<body style="background: #fff; overflow: hidden;">
		<div class="container">
			<div class="request-div-g">
				<div class="request-search-box">
					<input type="text" id="request-search-text" />
					<span id="request-search-clear"></span>
				</div>
				<div class="request-show-box">
					<ul id="list" class="request-show-list">
						<%
							for (Invitation req : list) {
						%>
						<li class="request-show-base">
							<div id="box">
								<div><%=req.getInvitationTitle()%></div>
								<div>
									<font size=2><%=DateUtil.getDateString(req.getVisitTime(),
								DateUtil.NOW_TIME2)%></font>
								</div>
							</div>
						</li>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div id="result" class="request-div-e">
				<%
					for (Invitation req : list) {
						Iterator<Customer> customers = req.getCustomers().iterator();
				%>
				<div class="request-show-result">
					<div>
						标题：<%=req.getInvitationTitle()%></div>
					<div>
						时间：<%=DateUtil.getDateString(req.getVisitTime(),
								DateUtil.NOW_TIME2)%></div>
					<div>
						客户列表：
						<%
						while (customers.hasNext()) {
								Customer customer = customers.next();
					%>
						<div><%=customer.getName()%>
							<%=customer.getPhoneNumber()%></div>
						<%
							}
						%>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</body>
</html>