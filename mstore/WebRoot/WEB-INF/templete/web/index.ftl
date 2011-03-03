<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html manifest="panoroom.manifest" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="apple-touch-icon-precomposed" href="logo.png" />
    <link rel="apple-touch-startup-image" href="logo.png" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" /> 
<title>M-Store手机软件下载平台</title>
<link href="mstore.css" rel="stylesheet" type="text/css" />

<script src="jquery/jquery-1.3.min.js"></script>
<script src="jquery/jquery.lazyload.js"></script>
<script type="text/javascript"><!--

	document.getViewportHeight = function(){
		if (window.innerHeight!=window.undefined) return window.innerHeight;
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
		if (document.body) return document.body.clientHeight; 
		return window.undefined; 
	};
	document.getViewportWidth = function(){
		if (window.innerWidth!=window.undefined) return window.innerWidth; 
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
		if (document.body) return document.body.clientWidth; 
	};

	$(document).ready(function() {
		//window.scrollTo(0, 1);
		//		alert(document.getViewportHeight());			   
		var widgetLine = 0;  //软件有多少排
		var widgetLineCount = 0; //每排多少个
		var widgetCount = 0; //可以请求软件个数
		var containnerHeight = 0; //容器的高度
		var boxHeight = 0;   //盒子的高度

		var page = 1;
		var flag = false;
		var widgetUrl = "web?list&type=json";
		var phonebrandUrl = "phonebrand?type=json";
		var categoryUrl = "category?type=json";
		var phonetypeUrl = "phonetype?byPhonebrand&type=json";
		
		var operate = 1;
		
		var params = {
			softId:-1,
			menuId:-1,
			phonetypeId:-1,
			phoneNumber:"",
			start:0,
			limit:0,
			phonebrandId:-1,
			categoryId:-1
		};
		
		var widgetTemplete = '<li style="float:left">'+
				'<div class="mstore-widget">'+
					'<div class="mstore-widget-icon mstore-icon" style="background-image:url(images/loading.gif);background-position:center center;background-repeat:no-repeat;">'+
						'<img src="{icon}">'+
					'</div>'+
					'<div class="mstore-widget-text">'+
						'<span>{softName}</span>'+
					'</div>'+
				'</div>'+
		'</li>';
		
		var phonebrandTemplete = '<li style="float:left">'+
                    '<div class="mstore-widget">'+
                        '<div class="mstore-phonebrand-widget-icon mstore-icon " style="background-image:url(images/loading.gif);background-position:center center;background-repeat:no-repeat;">'+
                        	'<img src="{url}">'+
                        '</div>'+
                        '<div class="mstore-widget-text">'+
                            '<span>{phonebrandName}</span>'+
                        '</div>'+
                    '</div>'+
                '</li>';
                
        var phonetypeTemplete = '<li style="float:left">'+
				'<div class="mstore-widget">'+
					'<div class="mstore-widget-icon mstore-icon"  style="background-image:url(images/loading.gif);background-position:center center;background-repeat:no-repeat;">'+
					'<img class="mstore-pic-icon" src="{icon}">'+
					'</div>'+
					'<div class="mstore-widget-text">'+
						'<span>{softName}</span>'+
					'</div>'+
				'</div>'+
		'</li>';
							
		resiceWindiw();
		
		$(window).resize(function(){
			if(flag){
				flag = false;
				return;
			}
		  	resiceWindiw();
		});
		
		$(".mstore-navigationbar-li").click(function(){
			$(".mstore-navigationbar-li").removeClass("mstore-navigationbar-clicked");
			$(".mstore-navigationbar-li").css("color","#FFF");
			$(this).addClass("mstore-navigationbar-clicked");
			$(this).css("color","#FF0");
			params.menuId = parseInt($(this).attr("name"));
			operate = 1;
			$(".mstore-ul").hide();
			$("#mstore-widget-ul").show();
			resiceWindiw();
			//alert($(this).attr("name"));
		});
		
		$("#mstore-exit").click(function(){
				if(confirm("您是否真的要退出?")){
					$.post("web?layout&type=json","",function(html){
						eval("action = "+html);
						if(action.success){
							window.location = "/";
						}else{
							alert(action.msg);
						}
					});
				}									 
		});
		
		function setWindowSize(){
			var width = document.getViewportWidth();
			var height =document.getViewportHeight();
			containnerHeight = height-144;
			boxHeight = height-184;
			$("#mstore-containner").css("height",containnerHeight+"px");
			
			
			//算出图片可以显示多少排
			widgetLine = parseInt((boxHeight)/120);
			//算出每排有多少个
			widgetLineCount = parseInt((width-2)/120);
			$(".mstore-containner-box").css("height",widgetLine*120+"px");
			//$(".mstore-button-next").val((width-2)/120);
			//算出总个数
			widgetCount = widgetLineCount*widgetLine;
			$(".mstore-containner-box").css("width",widgetLineCount*120+"px");
		}
		
		function moreWidget(){
			flag = true;
			page = page+1;
			
			params.start = params.start+widgetCount;
			var uurl = "";
			switch(operate){
				case 1:
					uurl = widgetUrl;
					break;
				case 2:
					uurl = phonebrandUrl;
					break;
				case 3:
					uurl = phonetypeUrl;
					break;
				case 4:
					uurl = categoryUrl;
					break;
				default:
					uurl = widgetUrl;
					break;
			}
			$.post(uurl,params,function(html){
				eval("var el = "+html);
				var length = el.data.length;
				if(el.success){
					switch(operate){
						case 1:
							if(length < widgetCount){
								$(".mstore-containner-bottom").hide();
								var addLine = parseInt((length-1)/widgetLineCount)+1;
								containnerHeight = containnerHeight + addLine*120;
								boxHeight = boxHeight+addLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",addLine*120+"px");
							}else{
								containnerHeight = containnerHeight + widgetLine*120;
								boxHeight = boxHeight+widgetLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",widgetLine*page*120+"px");
								$("#mstore-widget-ul").show();
							}
							$.each(el.data, function(i, n){
							  	addWidget(n);
							});
							break;
						case 2:
							
						
							break;
						case 3:
							if(length < widgetCount){
								$(".mstore-containner-bottom").hide();
								var addLine = parseInt((length-1)/widgetLineCount)+1;
								containnerHeight = containnerHeight + addLine*120;
								boxHeight = boxHeight+addLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",addLine*120+"px");
							}else{
								containnerHeight = containnerHeight + widgetLine*120;
								boxHeight = boxHeight+widgetLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",widgetLine*page*120+"px");
								$("#mstore-phonetype-ul").show();
							}
							$.each(el.data, function(i, n){
							  	addPhonetypeWidget(n);
							});
							break;
						case 4:
							if(length < widgetCount){
								$(".mstore-containner-bottom").hide();
								var addLine = parseInt((length-1)/widgetLineCount)+1;
								containnerHeight = containnerHeight + addLine*120;
								boxHeight = boxHeight+addLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",addLine*120+"px");
							}else{
								containnerHeight = containnerHeight + widgetLine*120;
								boxHeight = boxHeight+widgetLine*120;
								$("#mstore-containner").css("height",containnerHeight+"px");
								$(".mstore-containner-box").css("height",widgetLine*page*120+"px");
								$("#mstore-category-ul").show();
							}
							$.each(el.data, function(i, n){
							  	addCategoryWidget(n);
							});
							break;
					}
				
				
					
					//alert(el.totalCount+"   "+widgetCount);
					
				}else{
					alert("访问服务器出现错误");
				}
			});
		}
		
		
		$("#mstore-button-next").click(function(){
			moreWidget();
		});
		
		function resiceWindiw(){
			flag = true;
			params.start = 0;
			
			page = 1;
			$(".mstore-icon").unbind();
			$(".mstore-icon").removeData("data");
			$(".mstore-ul").empty();
			
			setWindowSize();
			$(".mstore-containner-box").css("width",widgetLineCount*120+"px");
			params.limit = widgetCount;
			$(".mstore-containner-bottom").show();	
			var uurl = "";
			switch(operate){
				case 1:
					uurl = widgetUrl;
					break;
				case 2:
					uurl = phonebrandUrl;
					break;
				case 3:
					uurl = phonetypeUrl;
					break;
				case 4:
					uurl = categoryUrl;
					break;
				default:
					uurl = widgetUrl;
					break;
			}
					
			$.post(uurl,params,function(html){
				eval("var el = "+html);
				if(el.success){
					var length = el.data.length;
					switch(operate){
						case 1: //soft
							
							if(el.totalCount <= widgetCount){
								$(".mstore-containner-bottom").hide();
							}else{
								$("#mstore-widget-ul").show();
							}
							$.each(el.data, function(i, n){
							  	addWidget(n)
							});
							break;
						case 2://phonebrand
							if(el.totalCount <= widgetCount){
								$(".mstore-containner-bottom").hide();
							}else{
								$(".mstore-containner-bottom").show();
							}
							$.each(el.data, function(i, n){
							  	addPhonebrandWidget(n);
							});
							break;
						case 3://phonetype
							if(el.totalCount <= widgetCount){
								$(".mstore-containner-bottom").hide();
							}else{
								$(".mstore-containner-bottom").show();
							}
							$.each(el.data, function(i, n){
							  	addPhonetypeWidget(n);
							});
							break;
							
						case 4://category
							if(el.totalCount <= widgetCount){
								$(".mstore-containner-bottom").hide();
							}else{
								$(".mstore-containner-bottom").show();
							}
							$.each(el.data, function(i, n){
							  	addCategoryWidget(n);
							});
							break;
						default:
							if(el.totalCount <= widgetCount){
								$(".mstore-containner-bottom").hide();
							}else{
								$("#mstore-widget-ul").show();
							}
							$.each(el.data, function(i, n){
							  	addWidget(n)
							});
							break;
					}
				}else{
					alert("访问服务器出现错误");
				}
				flag = false;
			});
			
		}
		
		function addWidget(n){
			var text = widgetTemplete.replace("{icon}",n.icon);
		  	text = text.replace("{softName}",n.softName);
		  	var temp = $(text);
		  	temp.find(".mstore-widget-icon").data("data",n);
		  	temp.find(".mstore-widget-icon").click(function(){
				$(".mstore-widget").removeClass("mstore-widget-icon-click");
				$(this).parent().addClass("mstore-widget-icon-click");	
				if(params.phonetypeId == -1){
					alert("请选择您的手机型号");
					return;
				}
				//显示下载框
				params.softId = $(this).data("data").softId;
				showWappushDialog();
			});
			$("#mstore-widget-ul").append(temp);
		}
		
		function addPhonebrandWidget(n){
			var text = phonebrandTemplete.replace("{url}",n.url);
		  	text = text.replace("{phonebrandName}",n.phonebrandName);
		  	var temp = $(text);
		  	temp.find(".mstore-phonebrand-widget-icon").data("data",n);
		  	temp.find(".mstore-phonebrand-widget-icon").click(function(){
				$(".mstore-widget").removeClass("mstore-widget-icon-click");
				$(this).parent().addClass("mstore-widget-icon-click");	
				//alert($(this).data("data").phonebrandId);
				params.phonebrandId = $(this).data("data").phonebrandId;
				showCategory();
				
			});
			$("#mstore-phonebrand-ul").append(temp);
		}
		
		function addPhonetypeWidget(n){
			var text = phonetypeTemplete.replace("{icon}",n.pic);
		  	text = text.replace("{softName}",n.phonetypeName);
		  	var temp = $(text);
		  	temp.find(".mstore-widget-icon").data("data",n);
		  	temp.find(".mstore-widget-icon").click(function(){
				$(".mstore-widget").removeClass("mstore-widget-icon-click");
				$(this).parent().addClass("mstore-widget-icon-click");	
				if(params.categoryId == -1){
					alert("请选择手机分类");
					return;
				}
				//显示下载框
				var data = $(this).data("data");
				params.phonetypeId = data.phonetypeId;
				$(".mstore-mobile-image").attr("src",data.pic);
				$(".mstore-mobile-state").text(data.phonetypeName);
				//alert($(this).data("data").phonetypeId);
				showWidget();
				
			});
			$("#mstore-phonetype-ul").append(temp);
		}
		
		function addCategoryWidget(n){
			var text = phonebrandTemplete.replace("{url}",n.pic);
		  	text = text.replace("{phonebrandName}",n.categoryName);
		  	var temp = $(text);
		  	temp.find(".mstore-phonebrand-widget-icon").data("data",n);
		  	temp.find(".mstore-phonebrand-widget-icon").click(function(){
				$(".mstore-widget").removeClass("mstore-widget-icon-click");
				$(this).parent().addClass("mstore-widget-icon-click");	
				if(params.phonebrandId == -1){
					alert("请选择手机厂商");
					return;
				}
				//显示下载框
				var data = $(this).data("data");
				params.categoryId = data.categoryId;
			//	$(".mstore-mobile-image").attr("src",data.pic);
			//	$(".mstore-mobile-state").text(data.phonetypeName);
				//alert($(this).data("data").phonetypeId);
				showPhonetype();
				
			});
			$("#mstore-category-ul").append(temp);
		}
		
		$(".mstore-mobile-btn").click(function(){
			showPhonebrand();
		});
		
		function showWidget(){
			$(".mstore-ul").hide();
			$("#mstore-widget-ul").empty();
			$("#mstore-widget-ul").show();
			operate = 1;
			resiceWindiw();
		}
		function showPhonebrand(){
			$(".mstore-ul").hide();
			$("#mstore-phonebrand-ul").empty();
			$("#mstore-phonebrand-ul").show();
			operate = 2;
			resiceWindiw();
		}
		
		function showPhonetype(){
			$(".mstore-ul").hide();
			$("#mstore-phonetype-ul").empty();
			$("#mstore-phonetype-ul").show();
			operate = 3;
			resiceWindiw();
		}
		
		function showCategory(){
			$(".mstore-ul").hide();
			$("#mstore-category-ul").empty();
			$("#mstore-category-ul").show();
			operate = 4;
			resiceWindiw();
		}
		
		
		
		$("#wappush-concel-btn").click(function(){
			hideWappushDialog();
		});
		
		$("#wappush-submit-btn").click(function(){
			var phoneNumber = $("#wappush-phonenumber").val();
			var patrn = /^(13[0-9]{9})|(15[289][0-9]{8})|(18[8][0-9]{8})$/;  
			if(phoneNumber =='' || !patrn.exec(phoneNumber)){
				alert("手机号格式不正确");
				return;
			}
			params.phoneNumber = phoneNumber;
			$("#wappush-submit-btn").attr("disabled","disabled");
			$.post("web?push&type=json",params,function(html){
				eval("var el = "+html);
				if(el.success){
					alert(el.msg);
					hideWappushDialog();
				}else{
					alert(el.msg);
				}
				$("#wappush-submit-btn").removeAttr("disabled");
			});
		});
		
		function showWappushDialog(){
			var sTop = getScrollTop();
			var width = document.getViewportWidth();
			var height =document.getViewportHeight();
			var h = $("#wappush-form").height();
			var w = $("#wappush-form").width();
			var top = (height - 100)/2+sTop;
			var left = (width - 270)/2;
			$(".wappush-dialog").css("top",top+"px");
			$(".wappush-dialog").css("left",left+"px");
			$(".wappush-dialog").show();
		}
		
		function hideWappushDialog(){
			$(".wappush-dialog").hide();
			$("#wappush-form")[0].reset();
		}
		
		
		window.onorientationchange = function(){
			location.reload();
		}
		$("#loading-msg").html("80%");
		
		
		loadWidget();
	});
	
	
	var i = 0;
	function loadWidget(){
		 i=i+10;
		 $("#loading-msg").html(i+"%");
		 if(i==100){
		 	$("#loading").hide();
		 	$("#loading-mask").hide();
		 }else{
		 	setTimeout(function(){
		 		loadWidget();
		 	},200);
		 }
	} 
	
	
	function getScrollTop(){
	    var scrollTop=0;
	    if(document.documentElement&&document.documentElement.scrollTop){
	        scrollTop=document.documentElement.scrollTop;
	    }else if(document.body){
	        scrollTop=document.body.scrollTop;
	    }
	    return scrollTop;
	}
	
	
--></script>
</head>

<body>

<div id="loading-mask"></div>
<div id="loading"> 
    <div class="loading-indicator">
    	<img src="images/extanim32.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:center;"/>正在加载请稍后...<br/>
    	<span id="loading-msg">0%</span>
    </div> 
</div>

<div class="mstore-common">
  	<div id="mstore-hander">
      	<div id="mstore-logo">
        	
   		</div>   
        
        <div id="mstore-moblie-choose">
            <div>
                <div class="mstore-mobile-state">您尚未选择机型</div>
                <input class="mstore-mobile-btn" type="button" value="选择机型"/>
            </div>
            <img class="mstore-mobile-image" width="70" height="70" src="images/noPhone.gif" />
        </div>
    </div>
    
    <div id="mstore-navigationbar">
    	<ul class="mstore-navigationbar-ul">
    		<#list menus as menu>
    			<li class="mstore-navigationbar-li" name="${menu.id}">${menu.menuName}</li>
  			</#list>
        	
        </ul>
    </div>
    
    <div id="mstore-containner">
    	<div class=" mstore-containner-box">
            <ul id="mstore-widget-ul" class="mstore-ul">
                
            </ul>
            
            <ul id="mstore-phonebrand-ul" class="mstore-ul" style="display:none;">
            	
            
            </ul>
            
             <ul id="mstore-category-ul" class="mstore-ul" style="display:none;">
            	
            
            </ul>
            
            <ul id="mstore-phonetype-ul" class="mstore-ul" style="display:none;">
            	
            
            </ul>
        </div>
        
        <div class="mstore-containner-bottom">
            <input id="mstore-button-next" class="mstore-button-next" type="button" value="显示更多结果"/>
        </div>
        
    </div>
    
    <div id="mstore-bottom">
       <div id="mstore-exit">
       
       </div>
    </div>
</div>

<div class="wappush-dialog" style="display:none;">
	<div class="wappush-dialog-title">软件下载</div>
    <div class="wappush-dialog-box">
    	<form id="wappush-form" action="#" method="post">
    	  <table width="100%">
    	    <tr>
    	      <td>手机号码：</td>
    	      <td><input id="wappush-phonenumber" type="text" name="phoneNumber" /></td>
  	      </tr>
    	    <tr >
    	       <th colspan="2"><input id="wappush-submit-btn" class="wappush-btn  " style="width: 120px ! important;" type="button" value="获取下载地址" />
                  	<input id="wappush-concel-btn" class="wappush-btn " style="width: 80px ! important;" type="button" value="取消" /></th>
  	      </tr>
  	    </table>
    	</form>
    </div>
</div>
<iframe src="status.html" style="display:none;"></iframe>
</body>
</html>
