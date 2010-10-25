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

function $(n) {
	if(!n) return;
	if(Object.prototype.toString.apply(n).search('Element') != -1){
		//元素
		return new Finger(n);
	}
	if(n.type && n.type == '[object Finger]') {
		//Finger对象
		return n;
	}
	var prefix = n.substr(0,1);
	if(prefix == '#') {
		//ID选择器
		var e = document.getElementById(n.substr(1));
		return new Finger(e);
	} 
	if(prefix == '.') {
		//class选择器
		var all = document.getElementsByTagName('*');
		var rest = [];
		for(var index=0; index<all.length; index++) {
			if(all[index].className.search(n.substr(1)) != -1) rest.push(new Finger(all[index]));
		}
		return rest;
	} 
	//tagname
	return new Finger(n);
}

function clone() {
	var tp = typeof(this);
	var objc;
	switch(tp){
		case 'number':
		case 'string':return this;
		case 'object':
			if(Object.prototype.toString.call(this) == '[object Array]') {
				objc = [];
				for(var key in this) {
					objc[key] = this[key];
				}
				return objc;
			}
			for(var key in this) {
				objc = {};
				objc[key] = this[key]; 	
			}
			return objc;
	}
}

function Finger(e) {
	/**添加style
	* @param obj 可以是字符串，也可以是json。
	*		如果是字符串，则返回此样式，如果
	*		是json，则设置样式。可省略px
	* @return 参数是'top', 'left', 'width', 'height'四种样式之一，
	*		则返回值是整型。
	*/
	this.css = function(obj){		
		var argType = typeof(obj);
		var dest = ['top', 'left', 'width', 'height'];
		var check = function(d, key){
			if(d.length <= 0) return false;
			return d.pop()==key?true:arguments.callee(d, key);	
		}

		if(argType == 'string') {
			var inDest = check(dest, obj);
			var stl = document.defaultView.getComputedStyle(e, null)[obj] || e.style[obj];
			var result = ((inDest && stl.slice(-2)=='px')?stl.replace('px',''):stl) || '';
			if(!result) {
				switch (obj)
				{
				case 'width':
					return e.clientWidth;
				case 'height':
					return e.clientHeight;
				}
			}
			return result;
		} else if(argType == 'object') {
			for(var key in obj) {
				var inDest = check(clone.call(dest), key);
				e.style[key] = inDest?(typeof(obj[key])=='number'||obj[key].slice(-2)!='px'?obj[key]+'px':obj[key]):obj[key];
			}
		}
		return this;
	}
	/**
	* 添加属性
	* @param obj 可以是字符串，也可以是json。
	*		如果是字符串，则返回此属性，如果
	*		是json，则设置属性
	*/
	this.attr = function(obj){
		var argType = typeof(obj);
		if(argType == 'string') {
			if(arguments.length == 1) {
				return e.getAttribute(obj);
			} else if(arguments.length == 2) {
				e.setAttribute(arguments[0], arguments[1]);
			}
		} else if(argType == 'object') {
			for(var key in obj) {
				e.setAttribute(key, obj[key]);
			}
		}
		return this;
	}
	/**
	* 添加点击事件
	* @param callback 回调事件
	*/
	this.click = function(callback){	
		e.addEventListener('click', callback, false);
		return this;
	}
	/**
	* 添加子节点
	* @param et:元素tagname
	* @param attrs:属性列表
	* @param txt:文本内容
	* @return 被添加的节点对象
	*/
	this.append = function(et, attrs, txt) {
		var ex = document.createElement(et);
		if(typeof(attrs) != 'object') return;		
		for(var key in attrs) {
			$(ex).attr(key, attrs[key]);			
		}
		if(txt) {
			var t = document.createTextNode(txt);
			ex.appendChild(t);
		}
		e.appendChild(ex);
		return $(ex);
	}
	/**动画
	* @param p 属性
	* @param sp 速度，为整数
	* @param callback 回调事件
	*/
	this.animate = function(p, sp, callback) {
		var mg = Animate.getManager();
		var elm = this.attr('id');	
		if(!elm) return;
		if(!sp) sp = 1000;
		var obj = mg.createAnimObject(elm);
		var prop = {};
		for(var key in p) {
			prop.property = Prop[key];
			prop.to = p[key];
			prop.duration = sp;
			//break;
		}	
		var o = this;
		if(callback) {
			prop.onComplete = function() {
				callback.call(o);
			}
		}
		obj.add(prop);
		this.animObject = obj;
		return this;
	}
	this.animObject = null;
	/**
	* 停止动画
	*/
	this.stop = function(){
		if(this.animObject) this.animObject.kill();
		return this;
	}
	/**遍历数组，执行callback函数
	* @param callback 回调事件
	*/
	this.each = function(callback){
		//不是数组
		if(Object.prototype.toString.apply(this) !== '[object Array]') return;
		for(var i=0; i<this.length; i++) {
			callback.call(this[i]);
		}
		return this;
	}
	/**ajax的get方法
	* @param data 请求的数据
	* @param callback 回调事件
	*/
	this.get = function(data, callback){
		var request = Ajax.createXmlHttpRequest();
		request.onreadystatechange = function() {
			if(request.readyState==4) {
				callback(data);
			}
		}
		request.open("get", data, true);
		request.send(null);
		return this;
	}
}

Ajax = {
	request: null,
	createXmlHttpRequest: function(){
		 var xmlHttp;	 
		 try {
		    xmlHttp=new XMLHttpRequest();
		 } catch (e) {
			try {
		      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {	
		      try {
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		      } catch (e) {
		         alert("您的浏览器不支持AJAX！");
		         return false;
		      }
			}
		 }
		 return xmlHttp;
	 },
	getRequest: function() {
		if(!this.request) {
			this.request = this.createXmlHttpRequest();
		}
		return this.request;
	}
}

Animate = {
	animateManager:null,
	getManager: function(){
		if(!this.animateManager) {
			this.animateManager = new jsAnimManager();
		}
		return this.animateManager;
	}
}

//给元素数组中每个元素添加css
function mcss(array, obj) {
//	alert(Object.prototype.toString.apply(array) === '[object Array]');
	/*array.each(function(){
		this.css(obj);
	})*/
	each.call(array, function(){
		this.css(obj);
	})
}

function each(callback){
	for(var i=0; i<this.length; i++) {
		callback.call(this[i]);
	}
}

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
/*Array.prototype.each = function(callback){
	for(var i=0; i<this.length; i++) {
		callback.call(this[i]);
	}
}*/

Tool = {
	calcDistance: function(x1, y1, x2, y2){
		var x = Math.pow(x2-x1, 2);
		var y = Math.pow(y2-y1, 2);
		return Math.sqrt(x+y);
	},
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
	speed: function(){
		var trace = arguments[0];	
		var len = trace.length;
		if(len < 2) {
			return;
		}
		var distance = Math.abs(trace[len-2] - trace[len-1]);
		return distance/1;		
	},
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