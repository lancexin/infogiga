/*
 * Windows Phone 7 Series Metro - lockscreen
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
// 参数
var statP = {
	"lr":0 // 0是左 1是右
}
var styleP = {
	"mb_top":58,
	"mb_left":10,
	"m_width":115,
	"m_width_2":240,
	"m_right":10,
	"m_top":10,
	
	"r_mb_left":10,
	"r_m_width":40,
	"r_m_top":10,
}
var startP = {
	"custom_color": "#F09609",  //红#E51400 //蓝#1BA1E2 //橙#F09609 //绿#339933
	
	"m_array_left":[{"name":"phone","bulk":"1","bg":"image/m-l-phone.png"},
			   {"name":"people","bulk":"1","bg":"image/m-l-people.png"},
			   {"name":"text","bulk":"1","bg":"image/m-l-text.png"},
			   {"name":"mail","bulk":"1","bg":"image/m-l-outlook.png"},
			   {"name":"picture","bulk":"2","bg":"image/m-l-pictures.png"},
			   {"name":"games","bulk":"1","bg":"m-l-games.png"},
//			   {"name":"games","bulk":"1","bg":"../image/Start/xbox.gif"},
			   {"name":"me","bulk":"1","bg":"image/m-l-me.png"},
			   {"name":"music+video","bulk":"2","bg":"image/m-l-music-video.png"},
			   
			   {"name":"ie","bulk":"1","bg":"image/m-l-ie.png"},
			   {"name":"calendar","bulk":"2","bg":"image/m-l-calendar.png"}],
	
	/*"m_array_right":[{"name":"alarm","des":"Alarm","bg":"../image/StartMenu/m-s-alarm.png"},
			   {"name":"calculator","des":"Calculator","bg":"../image/StartMenu/m-s-calculator.png"},
			   {"name":"calendar","des":"Calendar","bg":"../image/StartMenu/m-s-calendar.png"},
			   {"name":"camera","des":"Camera","bg":"../image/StartMenu/m-s-camera.png"},
			   {"name":"email","des":"E-mail","bg":"../image/StartMenu/m-s-email.png"},
			   {"name":"games","des":"Games","bg":"../image/StartMenu/m-s-games.png"},
			   {"name":"ie","des":"Internet Explorer","bg":"../image/StartMenu/m-s-ie.png"},
			   {"name":"map","des":"Map","bg":"../image/StartMenu/m-s-map.png"},
			   {"name":"marketplace","des":"Marketplace","bg":"../image/StartMenu/m-s-marketplace.png"}]*/
	 m_array_right:[]
}

function readAppList() {
	var apps = Data_application;
	var len = apps.length;
	for(var i=0; i<len; i++) {
		var obj = apps[i];
		var app = {name:obj.cls,des:obj.name,bg:obj.icon,pkg:obj.pkg};
		startP.m_array_right.push(app);
	}
	/*var apps = window.application.getAll();
	var len = apps.length();
	
	for(var i=0; i<len; i++) {
		var obj = apps.get(i);
		var app = {name:obj.getCls().toLocaleString(),
			des:obj.getName().toLocaleString(),
			bg:obj.getIcon().toLocaleString(),
			pkg:obj.getPkg().toLocaleString()};
		startP.m_array_right.push(app);
	}
	apps = null;*/
}

function initialize() {
//	readAppList();	//读取数据
//	initRight();
	loadImage();
}

function release() {
	startP.m_array_left.length = 0;
	startP.m_array_right.length = 0;	
}
/*
function load() {
	//window.base.debug("start document ready");
	readAppList();
	//window.base.debug("start read applist ok");
	preload();
	//window.base.debug("start preload ok");
	init(startP);//初始化
	//window.base.debug("start init startP ok");
	initRight(startP);
	//window.base.debug("start init right ok");
	createLocker();
	//window.base.debug("start createLocker ok");
	//release();//释放资源
	$('#arrow').onclick = function(){
						if(statP.lr==0){
							//切换到startMenu
							//$('#menu_box_right').css({'top':48});
							$('#menu_box_right').style.top = '48px'
							changeAnimateA();
							
						}else{
							//切换到start
							//$('#menu_box_left').css({'top':48});
							$('#menu_box_left').style.top = '48px'
							changeAnimateB();
							
						}
					}
}*/

function createXmlHttpRequest()
{
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
	         window.base.debug("can't create ajax");
	         return false;
	      }
		}
	 }
	 return xmlHttp;
 }
 
Ajax = {
	request:null,
	init: function(){
		if(this.request == null) {
			this.request = createXmlHttpRequest();
		}
	},
	get: function(str, callback){
		this.init();
		var req = this.request;
		req.onreadystatechange = function() {
			if(req.readyState==4) {
				callback(str);
			}
		}
		req.open("get", str, true);
		req.send(null);		
	}
}

function get(str, callback) {
	var request = createXmlHttpRequest() || null;
	request.onreadystatechange = function() {
		if(request.readyState==4) {
			callback(str);
		}
	}
	request.open("get", str, true);
	request.send(null);	 	
}	

function loadImage() {
	window.base.debug('start load image..');
	for(var index=0; index<startP.m_array_left.length; index++) {
		(function(i){
			var index = i;
			get(startP.m_array_left[index].bg , function(msg){
				$('#m_l_'+index).css({background: 'url('+msg+') no-repeat'});
				$('#m_l_'+index).css({backgroundColor: startP.custom_color});	
			});
		})(index)
	}
	for(var index=0; index<startP.m_array_right.length; index++) {
		(function(i){
			var index = i;
			get(startP.m_array_right[index].bg , function(msg){
				$('#m_s_'+index).css({background: 'url('+msg+') no-repeat'});
				$('#m_s_'+index).css({backgroundColor: startP.custom_color});
			});
		})(index)
	}	
}

function load() {
	window.base.debug("start document ready");
	readAppList();
	window.base.debug("start read applist ok");
	//preload();
	//window.base.debug("start preload ok");
	init(startP);//初始化
	window.base.debug("start init startP ok");
	initRight(startP);
	window.base.debug("start init right ok");
//	createLocker();
//	window.base.debug("start createLocker ok");
	$('#arrow').click(function(){
						if(statP.lr==0){
							//切换到startMenu
							$('#menu_box_right').css({top:'48px'});
							//$('#menu_box_right').style.top = '48px'
							changeAnimateA();
							
						}else{
							//切换到start
							$('#menu_box_left').css({top:'48px'});
							//$('#menu_box_left').style.top = '48px'
							changeAnimateB();
							
						}
					});
	//window.base.debug("start loadImage...");
	//loadImage();
	window.base.debug("init complete");
}
/*$(document).ready(function() {
	window.base.debug("start document ready");
	readAppList();
	window.base.debug("start read applist ok");
	preload();
	window.base.debug("start preload ok");
	init(startP);//初始化
	window.base.debug("start init startP ok");
	//initRight(startP);
	//window.base.debug("start init right ok");
	createLocker();
	window.base.debug("start createLocker ok");
	//release();//释放资源
	$('#arrow').click(function(){
						if(statP.lr==0){
							//切换到startMenu
							//$('#menu_box_right').css({'top':48});
							$('#menu_box_right').style.top = '48px'
							changeAnimateA();
							
						}else{
							//切换到start
							//$('#menu_box_left').css({'top':48});
							$('#menu_box_left').style.top = '48px'
							changeAnimateB();
							
						}
					});
});
*/
// 内部方法
function init() {
	var mi;
	var ix=-1;
	var iy=0;
	// menu_box_left
	for (mi=0;mi<startP.m_array_left.length;mi++) {
		//$('#menu_box_left').append('<div id="m_l_'+mi+'" class="m_l"></div>');	
		
		$('#menu_box_left').append('div', {id:'m_l_'+mi, 'class':'m_l'});
		
		$('#m_l_'+mi).click((function(index){
			return function(){
				if(index == 1) {
					Action.show('People.people');
				}
			}	
		})(mi));
		$('#m_l_'+mi).css({'background': 'url('+startP.m_array_left[mi].bg+') no-repeat'});
		$('#m_l_'+mi).css({backgroundColor: startP.custom_color});

		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(startP.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).css({left:ix*(styleP.m_width+styleP.m_right),
				top:iy*(styleP.m_width+styleP.m_top),
				width:styleP.m_width_2});
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).css({left:ix*(styleP.m_width+styleP.m_right),
			top:iy*(styleP.m_width+styleP.m_top)});
	}
	$('#menu_box_left').css({height:iy*(startP.m_width+startP.m_top)+30});	
}

function initRight() {
	// menu_box_right
	var iy=0;
	var len = startP.m_array_right.length;
	for (mi=0;mi<len;mi++) {
		var dest = startP.m_array_right[mi];

		$('#menu_box_right').append('div', {id:'m_s_'+ mi,'class':'m_s', pkg:dest.pkg, cls:dest.name}).append('div', {}, dest.des);
		$('#m_s_'+mi).css({'background':'url('+dest.bg+') no-repeat'});
		$('#m_s_'+mi).css({backgroundColor: startP.custom_color});
		$('#m_s_'+mi).click(function(){
								window.base.start($(this).attr('pkg'), $(this).attr('cls'));
							});
		$('#m_s_'+mi).css({top:iy*(styleP.r_m_width+styleP.r_m_top)});
		$('#m_s_'+mi).css({left:0});
		iy++;
	}
	$('#menu_box_right').css({height:iy*(styleP.r_m_width+styleP.r_m_top)+30});
}

//切换动画
function changeAnimateA() {
	var mi;
	var d=20;
	var iy=0;
	var appLength = Math.min(startP.m_array_right.length, 9);
	
	for (mi=0;mi<appLength;mi++) {
		if(mi>5){
			d=d-1;
		}
		$('#m_s_'+mi).css({left:mi*d});
		iy++;
	}
	for (mi=0;mi<appLength;mi++) {
		$('#m_s_'+mi).stop().animate({'left':0},450);
	}
	
	$('#menu_box_left').stop().animate({'left':-300},250);
	$('#menu_box_right').stop().animate({'left':50},250);
	$('#arrow').stop().animate({'left':10},250, function(){
		this.css({'left':10});		
	});
	$('#arrow').css({'backgroundImage':'url(image/before.png)'});
	
	statP.lr=1;
}

function changeAnimateB() {
	var mi;
	var ix=-1;
	var iy=0;
	for (mi=0;mi<startP.m_array_left.length;mi++) {
		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(startP.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right)-mi*40});
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right)-mi*40+(ix-1)*40});
	}
	ix=-1;
	iy=0;
	for (mi=0;mi<startP.m_array_left.length;mi++) {
		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(startP.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).stop().animate({'left':ix*(styleP.m_width+styleP.m_right)},400);
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).stop().animate({'left':ix*(styleP.m_width+styleP.m_right)},400);
	}
	
	$('#menu_box_left').stop().animate({'left':10},250);
	$('#menu_box_right').stop().animate({'left':340},250);
	$('#arrow').stop().animate({'left':270},250);
	$('#arrow').css({'background':'url(image/next.png)'});

	statP.lr=0;
}

// 拖动---------------------------------------------------------------------------------------------
// o->	#menu_box_left	大图菜单，可上下拖动
//		#menu_box_right	小图菜单，可上下拖动
function drag(o,x,y) {
	$(o).css({'left':x,'top':y});
}

function getCurrentHot() {
	return statP.lr==0?$('#menu_box_left'):$('#menu_box_right')
}

function reset() {
	$('#menu_box_left').css({'left':10});
	$('#menu_box_right').css({'left':340});
	$('#arrow').stop().css({'left':270});
	$('#arrow').css({'background':'url(image/next.png)'});
	statP.lr=0;
}

Action = {
	current:null,
	trace:[],   //运动轨迹
	offsetX:0,
	offsetY:0, 	//偏移量
	handler:null, //定时器句柄
	//由Java调用这个函数
	down: function() {
		this.current = getCurrentHot();
		/*if($.locked) {
			Locker.lockDown();
			return;
		}*/
		if(!this.current) return;
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push({x:x,y:y});
//		this.offsetY = y - this.current.css('top').replace('px','');
		this.offsetY = y - this.current.css('top');
		//this.offsetX = x - this.current.css('left').replace('px','');
		this.handler = setInterval(this.run(this), 50);
	},
	//移动位置
	run: function(A) {
		return function() {
//			if(!A.current || $.locked) return;
			if(!A.current) return;
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push({x:x,y:y});
//			A.current.css('top', Math.max(Math.min(y - A.offsetY, 48), -A.current.css('height').replace('px', '')));
			A.current.css({'top':Math.max(Math.min(y - A.offsetY, 48), -A.current.css('height'))});
		}
	},	
	//停止move
	up: function() {
		/*if($.locked) {
			Locker.lockUp();
			return;
		}*/
		if(!this.current) return;	
		var hot = this.current;	
		this.run();		
		var dir = Gesture.direction(this.trace);
		if(dir == 'left') {
			$('#menu_box_right').css({top:48});
			changeAnimateA();
		} else if(dir == 'right') {
			$('#menu_box_left').css({top:48});
			changeAnimateB();
		} else if(dir == 'up') {
			var speed = Gesture.speed(this.trace);
			//hot.animate({top: -speed*3}, {duration:1000, easing:'easeOutCirc'});
		} else if(dir == 'down') {
			var speed = Gesture.speed(this.trace);
			//hot.animate({top: speed*3}, {duration:1000, easing:'easeOutCirc'});
		}
		this.offsetY = 0; //重置offsetY		
		this.trace.length = 0;
		clearInterval(this.handler);
	},
	//显示某个页面
	show: function(n) {
		window.base.deactivate('Start.start');
		window.base.activate(n);
		//reset();
	},
	//锁屏
	lock: function() {
//		window.base.lock();
	}	
}

Key = {
	menu: function(){		
		//$.lockScreen();		
	},
	back:function(){
		
	}
}

// 调用外部方法
