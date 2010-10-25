/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/31
 */
 
// 参数
var pageStart_state = {
	"lr":0 // 0是左start , 1是右startMenu
};
var pageStart_style = {
	"mb_top":58,
	"mb_left":10,
	"m_width":115,
	"m_width_2":240,
	"m_right":10,
	"m_top":10,
	"r_mb_left":10,
	"r_m_width":40,
	"r_m_top":10,
	
	"custom_color": "#F09609"//红#E51400 //蓝#1BA1E2 //橙#F09609 //绿#339933
};
var pageStart_object = {
	
	"m_array_left":[{"name":"sc_phone","bulk":"1","bg":"images/Shortcut_L/phone.png"},
			   {"name":"sc_people","bulk":"1","bg":"images/Shortcut_L/people.png"},
			   {"name":"sc_text","bulk":"1","bg":"images/Shortcut_L/text.png"},
			   {"name":"sc_mail","bulk":"1","bg":"images/Shortcut_L/outlook.png"},
			   {"name":"sc_picture","bulk":"2","bg":"images/Shortcut_L/pictures.png"},
			   {"name":"sc_games","bulk":"1","bg":"images/Shortcut_L/games.png"},
			   {"name":"sc_me","bulk":"1","bg":"images/Shortcut_L/me.png"},
			   {"name":"sc_music+video","bulk":"2","bg":"images/Shortcut_L/music-video.png"},
			   
			   {"name":"sc_ie","bulk":"1","bg":"images/Shortcut_L/ie.png"},
			   {"name":"sc_calendar","bulk":"2","bg":"images/Shortcut_L/calendar.png"}],
	
	"m_array_right":[{"name":"sc_alarm","des":"Alarm","bg":"images/Shortcut_S/alarm.png"},
			   {"name":"sc_calculator","des":"Calculator","bg":"images/Shortcut_S/calculator.png"},
			   {"name":"sc_calendar","des":"Calendar","bg":"images/Shortcut_S/calendar.png"},
			   {"name":"sc_camera","des":"Camera","bg":"images/Shortcut_S/camera.png"},
			   {"name":"sc_email","des":"E-mail","bg":"images/Shortcut_S/email.png"},
			   {"name":"sc_games","des":"Games","bg":"images/Shortcut_S/games.png"},
			   {"name":"sc_ie","des":"Internet Explorer","bg":"images/Shortcut_S/ie.png"},
			   {"name":"sc_map","des":"Map","bg":"images/Shortcut_S/map.png"},
			   {"name":"sc_marketplace","des":"Marketplace","bg":"images/Shortcut_S/marketplace.png"}]
};

$(document).ready(function(){
	pageStart_style.custom_color=parent.Theme.accentcolor;
	pageStart_init(pageStart_object);//初始化
	$('#arrow').click(function(){
						if(pageStart_state.lr==0){
							//切换到startMenu
							changeAnimateA();
							
						}else{
							//切换到start
							changeAnimateB();
							
						}
					});
});

// 内部方法
function pageStart_init(pageStart_object) {
	var mi;
	var ix=-1;
	var iy=0;
	
	// menu_box_left
	for (mi=0;mi<pageStart_object.m_array_left.length;mi++) {
		$('#menu_box_left').append('<div id="m_l_'+mi+'"class="m_l menublock '+pageStart_object.m_array_left[mi].name+'"></div>');
		$('#m_l_'+mi).css({'background':'url('+pageStart_object.m_array_left[mi].bg+')'});
		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(pageStart_object.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).css({'left':ix*(pageStart_style.m_width+pageStart_style.m_right),'top':iy*(pageStart_style.m_width+pageStart_style.m_top),'width':pageStart_style.m_width_2});
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).css({'left':ix*(pageStart_style.m_width+pageStart_style.m_right),'top':iy*(pageStart_style.m_width+pageStart_style.m_top)});
	}
	$('#menu_box_left').css({'height':iy*(pageStart_style.m_width+pageStart_style.m_top)+30});
	
	// menu_box_right
	var iy=0;
	for (mi=0;mi<pageStart_object.m_array_right.length;mi++) {
		$('#menu_box_right').append('<div id="m_s_'+mi+'"class="m_s menublock"><div>'+pageStart_object.m_array_right[mi].des+'</div></div>');
		$('#m_s_'+mi).css({'background':'url('+pageStart_object.m_array_right[mi].bg+')'});
		$('#m_s_'+mi).css({'top':iy*(pageStart_style.r_m_width+pageStart_style.r_m_top)});
		iy++;
	}
	$('#menu_box_right').css({'height':iy*(pageStart_style.r_m_width+pageStart_style.r_m_top)+30});
	
	$('.menublock').css({'background-color':Theme.accentcolor});
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	$('.sc_people').click(function(){openpeople();});///////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


//切换动画
function changeAnimateA() {
	var mi;
	var d=10;
	var iy=0;
	for (mi=0;mi<pageStart_object.m_array_right.length;mi++) {
		if(mi>5){
			d=d-1;
		}
		$('#m_s_'+mi).css({'left':mi*d});
		iy++;
	}
	for (mi=0;mi<pageStart_object.m_array_right.length;mi++) {
		$('#m_s_'+mi).stop().animate({'left':0},900);
	}
	
	$('#menu_box_left').stop().animate({'left':-300},500);
	$('#menu_box_right').stop().animate({'left':50},500);
	$('#arrow').stop().animate({'left':10},500);
	$('#arrow').css({'background-image':'url(images/icon/before.png)'});
	pageStart_state.lr=1;
}
function changeAnimateB() {
	var mi;
	var ix=-1;
	var iy=0;
	for (mi=0;mi<pageStart_object.m_array_left.length;mi++) {
		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(pageStart_object.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).css({'left':ix*(pageStart_style.m_width+pageStart_style.m_right)-mi*40});
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).css({'left':ix*(pageStart_style.m_width+pageStart_style.m_right)-mi*40+(ix-1)*40});
	}
	ix=-1;
	iy=0;
	for (mi=0;mi<pageStart_object.m_array_left.length;mi++) {
		ix++;
		if(ix>1) {
			ix=0;
			iy++;
		}
		if(pageStart_object.m_array_left[mi].bulk==2){
			if(ix!=0){
				ix=0;
				iy++;
			}
			$('#m_l_'+mi).stop().animate({'left':ix*(pageStart_style.m_width+pageStart_style.m_right)},800);
			iy++;
			ix=-1;
			continue;
		}
		$('#m_l_'+mi).stop().animate({'left':ix*(pageStart_style.m_width+pageStart_style.m_right)},800);
	}
	
	$('#menu_box_left').stop().animate({'left':10},500);
	$('#menu_box_right').stop().animate({'left':340},500);
	$('#arrow').stop().animate({'left':270},500);
	$('#arrow').css({'background':'url(images/icon/next.png)'});
	pageStart_state.lr=0;
}

// 拖动---------------------------------------------------------------------------------------------
// o->	#menu_box_left	大图菜单，可上下拖动
//		#menu_box_right	小图菜单，可上下拖动
function drag(o,x,y) {
	$(o).css({'left':x,'top':y});
}