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
	
	"m_array_left":[{"name":"phone","bulk":"1","bg":"../image/Start/m-l-phone.png"},
			   {"name":"people","bulk":"1","bg":"../image/Start/m-l-people.png"},
			   {"name":"text","bulk":"1","bg":"../image/Start/m-l-text.png"},
			   {"name":"mail","bulk":"1","bg":"../image/Start/m-l-outlook.png"},
			   {"name":"picture","bulk":"2","bg":"../image/Start/m-l-pictures.png"},
			   {"name":"games","bulk":"1","bg":"../image/Start/m-l-games.png"},
//			   {"name":"games","bulk":"1","bg":"../image/Start/xbox.gif"},
			   {"name":"me","bulk":"1","bg":"../image/Start/m-l-me.png"},
			   {"name":"music+video","bulk":"2","bg":"../image/Start/m-l-music-video.png"},
			   
			   {"name":"ie","bulk":"1","bg":"../image/Start/m-l-ie.png"},
			   {"name":"calendar","bulk":"2","bg":"../image/Start/m-l-calendar.png"}],
	
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

function preload() {
	var img = new Image();
	img.src="../image/common/before.png";
	var img2 = new Image();
	img2.src="../image/common/next.png";
}

function readAppList() {
	var apps = window.application.getAll();
	var len = apps.length();
	
	for(var i=0; i<len; i++) {
		var obj = apps.get(i);
		var app = {name:obj.getCls(),des:obj.getName(),bg:obj.getIcon(),pkg:obj.getPkg()};
		startP.m_array_right.push(app);
	}
	apps = null;
}

function initialize() {
//	readAppList();	//读取数据
//	initRight();
}

function release() {
	startP.m_array_left.length = 0;
	startP.m_array_right.length = 0;	
}

$(document).ready(function() {
	readAppList();
	preload();
	init(startP);//初始化
	initRight();
	createLocker();
	//release();//释放资源
	$('#arrow').click(function(){
						if(statP.lr==0){
							//切换到startMenu
							$('#menu_box_right').css({'top':48});
							changeAnimateA();
							
						}else{
							//切换到start
							$('#menu_box_left').css({'top':48});
							changeAnimateB();
							
						}
					});
});

// 内部方法
function init(startP) {
	var mi;
	var ix=-1;
	var iy=0;
	// menu_box_left
	for (mi=0;mi<startP.m_array_left.length;mi++) {
		$('#menu_box_left').append('<div id="m_l_'+mi+'" class="m_l"></div>');		
		$('#m_l_'+mi).click(function(){
			this.blur();
			var index = $('div.m_l').index($(this));
			if(index == 1) {
				Action.show('people');
			} else {
				Action.show('people'+index);
			}
		}).focus(function(){
			alert('focus');
			this.blur();
		});
		$('#m_l_'+mi).css({'background':'url('+startP.m_array_left[mi].bg+')'});
//		$('#m_l_'+mi).css({'background':'url(file:///data/data/cn.infogiga.phone/red.png)'});
		$('#m_l_'+mi).css({'background-color':startP.custom_color});
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
			$('#m_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right),'top':iy*(styleP.m_width+styleP.m_top),'width':styleP.m_width_2});
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right),'top':iy*(styleP.m_width+styleP.m_top)});
	}
	$('#menu_box_left').css({'height':iy*(startP.m_width+startP.m_top)+30});	
}

function initRight() {
	// menu_box_right
	var iy=0;
	for (mi=0;mi<startP.m_array_right.length;mi++) {
		$('#menu_box_right').append('<div id="m_s_'+mi+'"class="m_s"><div>'+startP.m_array_right[mi].des+'</div></div>');
		$('#m_s_'+mi).css({'background':'url('+startP.m_array_right[mi].bg+') no-repeat'});
		$('#m_s_'+mi).css({'background-color':startP.custom_color});
		$('#m_s_'+mi).data('pkg', startP.m_array_right[mi].pkg);
		$('#m_s_'+mi).data('cls', startP.m_array_right[mi].name);
		$('#m_s_'+mi).click(function(){
								window.base.start($(this).data('pkg'), $(this).data('cls'));
							});
		$('#m_s_'+mi).css({'top':iy*(styleP.r_m_width+styleP.r_m_top)});
		$('#m_s_'+mi).css({left:0});
		iy++;
	}
	$('#menu_box_right').css({'height':iy*(styleP.r_m_width+styleP.r_m_top)+30});
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
		$('#m_s_'+mi).css({'left':mi*d});
		iy++;
	}
	for (mi=0;mi<appLength;mi++) {
		$('#m_s_'+mi).stop().animate({'left':0},900);
	}
	
	$('#menu_box_left').stop().animate({'left':-300},500);
	$('#menu_box_right').stop().animate({'left':50},500);
	$('#arrow').stop().animate({'left':10},500);
	$('#arrow').css({'background-image':'url(../image/common/before.png)'});
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
			$('#m_l_'+mi).stop().animate({'left':ix*(styleP.m_width+styleP.m_right)},800);
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).stop().animate({'left':ix*(styleP.m_width+styleP.m_right)},800);
	}
	
	$('#menu_box_left').stop().animate({'left':10},500);
	$('#menu_box_right').stop().animate({'left':340},500);
	$('#arrow').stop().animate({'left':270},500);
	$('#arrow').css({'background':'url(../image/common/next.png)'});
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
	$('#arrow').css({'background':'url(../image/common/next.png)'});
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
		if($.locked) {
			Locker.lockDown();
			return;
		}
		if(!this.current) return;
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push({x:x,y:y});
		this.offsetY = y - this.current.css('top').replace('px','');
		//this.offsetX = x - this.current.css('left').replace('px','');
		this.handler = setInterval(this.run(this), 50);
	},
	//移动位置
	run: function(A) {
		return function() {
			if(!A.current || $.locked) return;
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push({x:x,y:y});
			A.current.css('top', Math.max(Math.min(y - A.offsetY, 48), -A.current.css('height').replace('px', '')));
		}
	},	
	//停止move
	up: function() {
		if($.locked) {
			Locker.lockUp();
			return;
		}
		if(!this.current) return;	
		var hot = this.current;	
		this.run();		
		var dir = Gesture.direction(this.trace);
		if(dir == 'left') {
			$('#menu_box_right').css({'top':48})
			changeAnimateA();
		} else if(dir == 'right') {
			$('#menu_box_left').css({'top':48})
			changeAnimateB();
		} else if(dir == 'up') {
			var speed = Gesture.speed(this.trace);
			hot.animate({top: -speed*3}, {duration:1000, easing:'easeOutCirc'});
		} else if(dir == 'down') {
			var speed = Gesture.speed(this.trace);
			hot.animate({top: speed*3}, {duration:1000, easing:'easeOutCirc'});
		}
		this.offsetY = 0; //重置offsetY		
		this.trace.length = 0;
		clearInterval(this.handler);
	},
	//显示某个页面
	show: function(n) {
		window.base.deactivate('start');
		window.base.activate(n);
		reset();
	},
	//锁屏
	lock: function() {
//		window.base.lock();
	}	
}

Key = {
	menu: function(){		
		$.lockScreen();		
	},
	back:function(){
		
	}
}

// 调用外部方法
