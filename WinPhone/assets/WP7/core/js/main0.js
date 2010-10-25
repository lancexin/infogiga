/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
//缺省配置
var ColorPad = {
	'dark':'#141414',
	'light':'#fff',
	'blue':'#1BA1E2',
	'red':'#E51400',
	'orange':'#F09609',
	'green':'#339933'
};
var Shortcut_L = {
	'phone':'url(images/Shorcut_L/phone.png)',
};
var Shortcut_S = {
	'phone':'url(images/Shorcut_S/phone.png)',
};
var Theme = {
	'bgcolor':ColorPad.dark,
	'accentcolor':ColorPad.orange,
};
function getTheme(){
	return Theme;
}

// 设置主题
function setThemeBgcolor(s){
	if(s=='dark'){
		Theme.bgcolor=ColorPad.dark;
	}else if(s=='light'){
		Theme.bgcolor=ColorPad.light;
	}
}
function setThemeAccentcolor(s){
	if(s=='blue'){
		Theme.accentcolor=ColorPad.blue;
	}else if(s=='red'){
		Theme.accentcolor=ColorPad.red;
	}else if(s=='orange'){
		Theme.accentcolor=ColorPad.orange;
	}else if(s=='green'){
		Theme.accentcolor=ColorPad.green;
	}
}

function createLocker(back) {
	var e = document.createElement('div');
	e.id = 'screenLocker';
	//$(e).attr('id', 'screenLocker');
	if(back) {
//		$(e).attr('background', back);
		e.style.background=back;
	}
	document.body.appendChild(e);
}


/*Locker = {
	trace:[],   //运动轨迹
	offsetX:0,
	offsetY:0, 	//偏移量
	handler:null, //定时器句柄
	lockDown: function(){
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push({x:x,y:y});
		this.offsetY = y - $('#screenLocker').css('top').replace('px','');
		this.handler = setInterval(this.run(this), 50);
	},
	run: function(A){
		return function() {
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push({x:x,y:y});
			$('#screenLocker').css('top', Math.min(y-A.offsetY, 0));
		}
	},
	lockUp: function(){
		this.run();		
		//var dir = Gesture.direction(this.trace);
		if($('#screenLocker').css('top').replace('px','') > -240) {
			$('#screenLocker').animate({top:0}, 300, 'easeOutBounce', function(){
				$(this).css({'top':0});
				$.locked = true;
			});
		} else {
			$('#screenLocker').animate({top:-480}, 300, function(){
				$(this).css({'top':-480});
				$.locked=false;
			});
		}
		this.offsetY = 0; //重置offsetY		
		this.trace.length = 0;
		clearInterval(this.handler);
	}	
}*/

/*$.extend({
	locked: false,
	lockScreen: function(options) {
		if($.locked) return;
		var locker = $('#screenLocker');
		var setting = {
			top: -480,
			left: 0,
			//是否由自己控制
			control: false,
			zIndex: 100,
			background: 'url(../image/wallpaper.png) no-repeat'
		};
		
		var opts = $.extend(setting, options);
		
		if(locker.length <= 0) {			
			var e = document.createElement('div');
			$(e).attr('id', 'screenLocker');			
			document.body.appendChild(e);
			locker = $(e);	
		}	
		init();	
		$.locked = true;
		locker.stop().animate({top:0}, 1000, 'easeOutBounce', function(){
			$(this).css({'top':0});
		});
		return locker;
		
		//取最大z-index
		function getMaxZindex() {
			var max = opts.zIndex;
			$('*').not(locker).each(function(){
				var zindex = $(this).css('z-index');
				if(isNaN(zindex)) return;
				max = Math.max($(this).css('z-index')-0, max);
			});
			return max+1;
		}

		function init() {
			locker.css({
				top:opts.top, 
				left:opts.left,
				background:opts.background
			});				
//			locker.css('z-index', getMaxZindex());
		}
	}
});
*/

Element.prototype.css = function(obj){
	var argType = typeof(obj);
	var dest = ['top', 'left', 'width', 'height'];
	var check = function(key){
		if(dest.length <= 0) return false;
		return dest.pop()==key?true:arguments.callee(key);	
	}

	if(argType == 'string') {
		var inDest = check(obj);
		var stl = this.style[obj];
		return ((inDest && stl.slice(-2)=='px')?stl.replace('px',''):stl) || '';
	} else if(argType == 'object') {		
		for(var key in obj) {
			var inDest = check(key);
			this.style[key] = inDest?(typeof(obj[key])=='number'||obj[key].slice(-2)=='px'?obj[key]:obj[key]+'px'):obj[key];
		}
	}
}

/*function $(n) {
	if(!n) return;
	var prefix = n.substr(0,1);
	if(prefix == '#') {
		var e = document.getElementById(n.substr(1));
		return e;
	} else if(prefix == '.') {
	} else {
		var e = document.getElementsByTagName(n.substr(1));
		return e;
	}
}*/

/*Array.prototype.each = function(callback){
	for(var i=0; i<this.length; i++) {
		callback.call(this, this[i]);
	}
}*/

Tool = {
	//计算两点距离
	calcDistance: function(x1, y1, x2, y2){
		var x = Math.pow(x2-x1, 2);
		var y = Math.pow(y2-y1, 2);
		return Math.sqrt(x+y);
	},
	//计算角度
	calcAngle: function(x1, y1, x2, y2){
		var angle = 0;
		var distance = this.calcDistance(x1, y1, x2, y2);
		var x = Math.abs(x2-x1);
		var y = Math.abs(y2-y1);
		if(y2 > y1) {
			if (x2 > x1) {
                angle = Math.acos(x / distance) * 180 / Math.PI + 90; //返回数字的反余弦值
            }
            if (x2 == x1) {
                angle = 180;
            }
            if (x2 < x1) {
                angle = -(Math.atan(y / x) * 180 / Math.PI + 90);
            }
		}
		if(y2 < y1) {
			if (x2 > x1) {
                angle = Math.asin(x / distance) * 180 / Math.PI;
            }
            if (x2 == x1) {
                angle = 0;
            }
            if (x2 < x1) {
                angle = -(Math.atan(x / y) * 180 / Math.PI);
            }
		}
		if(y2 == y1) {
			if (x2 > x1) {
                angle = 90;
            }
            if (x2 == x1) {
                angle = 0;
            }
            if (x2 < x1) {
                angle = -90;
            }
		}
		return angle;
	},
	//给定值是否在a和b之间
	between: function(a, b){
		return this >= a && this <= b;
	}
}

Movement = {
	decelerate: function() {
		
	},
	accelerate: function() {
		
	}
}

Gesture = {
	////从点数组中得到速度
	speed: function(){
		var trace = arguments[0];	
		var len = trace.length;
		if(len < 2) {
			return;
		}
		var distance = Math.abs(trace[len-2] - trace[len-1]);
		return distance/1;		
	},
	//从点数组中得到方向
	direction: function(){
		var trace = arguments[0];
		var len = trace.length;
		if(len < 3 || 
			(Tool.between.call(trace[0].x -trace[len-1].x, -100, 100)
			&& Tool.between.call(trace[0].y -trace[len-1].y, -100, 100))) {
			return;
		}
		var angle = Tool.calcAngle(trace[0].x, trace[0].y, trace[len-1].x, trace[len-1].y);
		if(Tool.between.call(angle, 45, 135)) {
			return 'right';	
		}
		if(Tool.between.call(angle, -135, -45)) {
			return 'left';	
		}
		if(Tool.between.call(angle, -45, 0) || Tool.between.call(angle, 0, 45)) {
			return 'up';	
		}
		if(Tool.between.call(angle, -180, -135) || Tool.between.call(angle, 135, 180)) {
			return 'down';	
		}
		return '';
	}
}

// 内部方法

// 调用外部方法
