/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/31
 */
 
// 参数
var statP = {
	"lr":0 // 0是左start , 1是右startMenu
};
var styleP = {
	"mb_top":58,
	"mb_left":10,
	"m_width":115,
	"m_width_2":240,
	"m_right":10,
	"m_top":10,
	"r_mb_left":10,
	"r_m_width":40,
	"r_m_top":10
};
var startP = {
	"custom_color": "#F09609",  //红#E51400 //蓝#1BA1E2 //橙#F09609 //绿#339933
	
	"m_array_left":[{"name":"phone","bulk":"1","bg":"images/Shortcut_L/phone.png"},
			   {"name":"people","bulk":"1","bg":"images/Shortcut_L/people.png"},
			   {"name":"text","bulk":"1","bg":"images/Shortcut_L/text.png"},
			   {"name":"mail","bulk":"1","bg":"images/Shortcut_L/outlook.png"},
			   {"name":"picture","bulk":"2","bg":"images/Shortcut_L/pictures.png"},
			   {"name":"games","bulk":"1","bg":"images/Shortcut_L/games.png"},
			   {"name":"me","bulk":"1","bg":"images/Shortcut_L/me.png"},
			   {"name":"music+video","bulk":"2","bg":"images/Shortcut_L/music-video.png"},
			   
			   {"name":"ie","bulk":"1","bg":"images/Shortcut_L/ie.png"},
			   {"name":"calendar","bulk":"2","bg":"images/Shortcut_L/calendar.png"}],
	
	"m_array_right":[{"name":"alarm","des":"Alarm","bg":"images/Shortcut_S/alarm.png"},
			   {"name":"calculator","des":"Calculator","bg":"images/Shortcut_S/calculator.png"},
			   {"name":"calendar","des":"Calendar","bg":"images/Shortcut_S/calendar.png"},
			   {"name":"camera","des":"Camera","bg":"images/Shortcut_S/camera.png"},
			   {"name":"email","des":"E-mail","bg":"images/Shortcut_S/email.png"},
			   {"name":"games","des":"Games","bg":"images/Shortcut_S/games.png"},
			   {"name":"ie","des":"Internet Explorer","bg":"images/Shortcut_S/ie.png"},
			   {"name":"map","des":"Map","bg":"images/Shortcut_S/map.png"},
			   {"name":"marketplace","des":"Marketplace","bg":"images/Shortcut_S/marketplace.png"}]
};

$(document).ready(function(){
	startP.custom_color=parent.Theme.accentcolor;
	init(startP);//初始化
	$('#arrow').click(function(){
						if(statP.lr==0){
							//切换到startMenu
							changeAnimateA();
							
						}else{
							//切换到start
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
		$('#menu_box_left').append('<div id="m_l_'+mi+'"class="m_l"></div>');
		$('#m_l_'+mi).click(function(){
								alert('test');//这个怎么弄？
							});
		$('#m_l_'+mi).css({'background':'url('+startP.m_array_left[mi].bg+')'});
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
	$('#menu_box_left').css({'height':iy*(styleP.m_width+styleP.m_top)+30});
	
	// menu_box_right
	var iy=0;
	for (mi=0;mi<startP.m_array_right.length;mi++) {
		$('#menu_box_right').append('<div id="m_s_'+mi+'"class="m_s"><div>'+startP.m_array_right[mi].des+'</div></div>');
		$('#m_s_'+mi).css({'background':'url('+startP.m_array_right[mi].bg+')'});
		$('#m_s_'+mi).css({'background-color':startP.custom_color});
		$('#m_s_'+mi).click(function(){
								alert('test');//这个怎么弄？
							});
		$('#m_s_'+mi).css({'top':iy*(styleP.r_m_width+styleP.r_m_top)});
		iy++;
	}
	$('#menu_box_right').css({'height':iy*(styleP.r_m_width+styleP.r_m_top)+30});
}


//切换动画
function changeAnimateA() {
	var mi;
	var d=10;
	var iy=0;
	for (mi=0;mi<startP.m_array_right.length;mi++) {
		if(mi>5){
			d=d-1;
		}
		$('#m_s_'+mi).css({'left':mi*d});
		iy++;
	}
	for (mi=0;mi<startP.m_array_right.length;mi++) {
		$('#m_s_'+mi).stop().animate({'left':0},900);
	}
	
	$('#menu_box_left').stop().animate({'left':-300},500);
	$('#menu_box_right').stop().animate({'left':50},500);
	$('#arrow').stop().animate({'left':10},500);
	$('#arrow').css({'background-image':'url(images/icon/before.png)'});
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
	$('#arrow').css({'background':'url(images/icon/next.png)'});
	statP.lr=0;
}

// 拖动---------------------------------------------------------------------------------------------
// o->	#menu_box_left	大图菜单，可上下拖动
//		#menu_box_right	小图菜单，可上下拖动
function drag(o,x,y) {
	$(o).css({'left':x,'top':y});
}

// 调用外部方法