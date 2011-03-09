<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html manifest="panoroom2.manifest" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="apple-touch-icon-precomposed" href="logo.png" />
    <link rel="apple-touch-startup-image" href="logo.png" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" /> 
	<meta name="viewport" content="user-scalable=no"/>
<title>M-Store手机软件下载平台</title>
<link href="mstore.css" rel="stylesheet" type="text/css" />

<script src="jquery/jquery-1.3.min.js"></script>
<script src="jquery/jquery.cookie.js"></script>
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
		

		if($.cookie('phonetypeId') != null && $.cookie('phonetypeImg') != null && $.cookie('phonetypeName') != null){
			params.phonetypeId = $.cookie('phonetypeId');
			$(".mstore-mobile-image").attr("src",$.cookie('phonetypeImg'));
			$(".mstore-mobile-state").text($.cookie('phonetypeName'));
		}
		
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
			//$(".mstore-navigationbar-li").css("color","#FFF");
			$(this).addClass("mstore-navigationbar-clicked");
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
				$.cookie('phonetypeId',data.phonetypeId);
				$.cookie('phonetypeImg',data.pic);
				$.cookie('phonetypeName',data.phonetypeName);				
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
			$("#wappush-phonenumber").val("");
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
			var top = (height - 460)/2+sTop;
			var left = (width - 600)/2;
			$(".wappush-dialog").css("top",top+"px");
			$(".wappush-dialog").css("left",left+"px");
			$.post("web?info&type=json",params,function(html){
				eval("var el = "+html);
				if(el.success){
					$("#wappush-widget-count").html(el.data.downloadCount);
					$("#wappush-widget-name").html(el.data.softName);
					$("#wappush-widget-date").html(el.data.addTime);
					$("#wappush-widget-description").html(el.data.description);
					$("#wappush-widget-icon").css("background-image","url("+el.data.icon+")");
					$("#wappush-widget-pic").attr("src",el.data.pic1);
					$(".wappush-dialog").show();
				}else{
					alert(el.msg);
				}
				$("#wappush-submit-btn").removeAttr("disabled");
			});
			
			
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
		
		$("div").click(function(event){
			event.preventDefault(); 
		});
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
            <img class="mstore-mobile-image" heigth="70" height="70"  src="images/noPhone.gif" />
        </div>
    </div>
    
    <div id="mstore-navigationbar">
    	<ul class="mstore-navigationbar-ul">
    		<#list menus as menu>
    			<li class="mstore-navigationbar-li mstore-navigationbar-bg" name="${menu.id}"><div style="padding-top:4px;">${menu.menuName}</div></li>
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
        <div class="wappush-dialog-top">
            <div class="wappush-dialog-angle" style="background-repeat: no-repeat;background-image: url(images/border.png);"></div>
            <div class="wappush-dialog-side"></div>
            <div class="wappush-dialog-angle" style="background-repeat: no-repeat;background-image: url(images/border.png);background-position: -20px 0px;"></div>
        </div>
        <div class="wappush-dialog-center">
        	<div class="wappush-dialog-center-left">
            	<div class="wappush-dianlog-center-left-top">
                	<div class="wappush-dialog-center-icon"  id="wappush-widget-icon" style="background-image:url(images/demo1.png)"></div>
                    <div class="wappush-dialog-center-info">
                    	<ul class="wappush-dialog-ul">
                            <li class="wappush-dialog-li">已经下载:<span id="wappush-widget-count">122</span>次</li>
                            <li class="wappush-dialog-li">发布:<span id="wappush-widget-date">2011-02-10</span></li>
                        </ul>
                  	</div>
                </div>
                <div class="wappush-dialog-center-left-bottom">
                	<img id="wappush-widget-pic"  src="images/demo2.jpg" />
                </div>
                
            </div>
            <div class="wappush-dialog-center-right">
            	<div class="wappush-dialog-center-right-top">
               	  <div class="wappush-dialog-title" id="wappush-widget-name">音乐随身听</div>
                    <div class="wappush-dialog-introduction" style="outline:hidden;" id="wappush-widget-description">音乐随身听是中国移动无线音乐基地开发的手机音乐在线播放软件，本软件具备歌词显示，并集歌曲下载，在线实时听歌，彩铃定制等功能于一体；同时提供会员注册和升级的途径，让您随时随地体验动听人生！</div>
                </div>
                <div class="wappush-dialog-center-right-bottom">
                	<form id="wappush-form">
                    	<input id="wappush-phonenumber" style="width:247px;height:27px;font-size:20px" name="phonenumber" type="text"/>
                    </form>
                    <div style="margin-top:9px;">
                    	<img id ="wappush-concel-btn"  style="margin-right:2px;cursor:pointer;" src="images/btn-back.png" />
                    	<img id= "wappush-submit-btn" style="cursor:pointer;" src="images/btn-ok.png" />
                    </div>
                </div>
            </div>
        </div>
        <div class="wappush-dialog-bottom">
      <div class="wappush-dialog-angle" style="background-repeat: no-repeat;background-image: url(images/border.png);background-position: 0px -20px;"></div>
            <div class="wappush-dialog-side"></div>
            <div class="wappush-dialog-angle" style="background-repeat: no-repeat;background-image: url(images/border.png);background-position: -20px -20px;"></div>
        </div>
    </div>
<iframe src="status.html" style="display:none;"></iframe>
</body>
</html>
