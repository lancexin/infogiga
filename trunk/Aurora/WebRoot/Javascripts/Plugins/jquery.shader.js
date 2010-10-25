

(function($) {
	$.getViewportHeight = function(){
		if (window.innerHeight!=window.undefined) return window.innerHeight;
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientHeight;
		if (document.body) return document.body.clientHeight; 
		return window.undefined; 
	}

	$.getViewportWidth = function(){
		if (window.innerWidth!=window.undefined) return window.innerWidth; 
		if (document.compatMode=='CSS1Compat') return document.documentElement.clientWidth; 
		if (document.body) return document.body.clientWidth; 
	}

	$.fn.shader = function(option){
	
		var def = {
			width:500,
			height:400,
			url:null,
			templete:null,
			container:null,
			title:"",
			autoHide:false,
			templete:null
		}
		
		var Shader_this = $(this);
		
		option = $.extend(def, option);
			
			Shader_this.addClass("mask");
			setMaskSize();
			
			if(option.autoHide){
				$(this).hide();
			}
			
			
			var popcont = document.createElement('div');
			option.container = $(popcont);
	
			option.container.html("<div class='popupInner'>"+
									"<div class='popupTitleBar'>"+
										"<div class='popupTitle'></div>"+
										"<div class='popupControls'>"+
										"<img class='popCloseBox' src=\"images/close.gif\" />"+
									"</div></div>"+
								"<div class='popupFrame'>dd</div>");
			
			option.container.appendTo(document.body);
			
			option.container.addClass("popupContainer");
			
			option.container.css({
				"height":option.height,
				"width":option.width
			});
			
			toCenter();
			
			var popupTitleBar = option.container.find(".popupTitleBar");
			var popupTitle = popupTitleBar.find(".popupTitle");
			var popupControls = popupTitleBar.find(".popupControls");
			var popCloseBox = popupControls.find(".popCloseBox");
			var popupFrame = option.container.find(".popupFrame");
			popupTitle.html(option.title);
			popupFrame.html("<img src=\"images/loading.gif\"/>");
			
			if(option.url){
				$.post(option.url,null,function(data){
					popupFrame.html(data);
				});
			}else if(option.templete){
				popupFrame.html(option.templete);
			}
			
			if(option.autoHide){
				Shader_this.hide();
				option.container.hide();
			}
			
			popCloseBox.click(function(e){
				Shader_this.hide();
				option.container.hide();
			});
			
			window.onresize = function(){
				setMaskSize();
				toCenter();
			} ;
			
			
		
		function setMaskSize(){
			var popHeight = $.getViewportHeight();
			var popWidth = $.getViewportWidth();
			
			Shader_this.height(popHeight);
			Shader_this.width(popWidth);
		}
		
		function toCenter(){
			var top = 0;
			var left = 0;
			if(($.getViewportWidth()-option.width)/2 > 0){
				left = ($.getViewportWidth()-option.width)/2;
			}
			if(($.getViewportHeight()-option.height)/2 > 0){
				top = ($.getViewportHeight()-option.height)/2;
			}
			option.container.css({
				"left":left,
				"top":top
			});
		}
		
		var c = {
			hide:function(){
				Shader_this.hide();
				option.container.hide();
			},
			show:function(){
				Shader_this.show();
				option.container.show();
			}
		}
		this[0].sh = c;
		return this;
	};
	
	$.fn.showShader = function(){
		this[0].sh.show();
	}
	
	$.fn.hideShader = function(){
		this[0].sh.hide();
	}
	
})(jQuery);