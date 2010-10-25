/*
 * Windows Phone 7 Series Metro - lockscreen
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
// 样式参数
var pagePeople_state = {
	"viewPos":"box_1", // box_1 box_2 box_3
	"rolling":true
}
var pagePeople_style = {
	"m_width":115,
	"m_width_2":240,
	"m_right":10,
	"m_top":10
} 

// 从java传递过来的pagePeople_object
var pagePeople_object = {
	"recent_array":[{"name":"a"},
					{"name":"b"},
					{"name":"c"},
					{"name":"d"},
					{"name":"e"},
					{"name":"f"},
					{"name":"g"},
					{"name":"h"}],
	"all_first":{"des":"heading to the gym now"},
	"all_array":[{"name":"Amy Albert","alphabet":"a"},
				{"name":"Albert2"},
				{"name":"Albert3"},
				{"name":"Albert4"},
				{"name":"Albert5"},
				{"name":"Boom1","alphabet":"b"},
				{"name":"Boom2"},
				{"name":"Boom3"},
				{"name":"Boom4"},
				{"name":"Boom5"},
				{"name":"Cook1","alphabet":"c"},
				{"name":"Cook2"},
				{"name":"Cook3"},
				{"name":"Cook4"},
				{"name":"Cook5"}],
	"what_array":[{"name":"Holly Dickson","from":"Windows Live","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":2},
				  {"name":"Shai Bassli","from":"Facebook","news":"Wrote on Luis Alverca's wall",
				  "content":"Thank you for showing me around town yesterday. I really like the new downtown area and that wine bar.","comment":0},
				  {"name":"Anat Kerry","from":"Facebook","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":5},
				  {"name":"Holly Dickson","from":"Windows Live","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":3}]
}

$(document).ready(function() {
	pagePeople_init(pagePeople_object);//初始化
	
	//测试方法
	$('#title').click(function(){
							 //***********************It's a test*****************************//
						//////toright();
						toleft();
					});
});

// 内部方法
function pagePeople_init(pagePeople_object) {
	//pagePeople_state
	
	var leftPadding=10;
	//box_1: recent
	var mi;
	var ix=-1;
	var iy=0;
	for (mi=0;mi<pagePeople_object.recent_array.length;mi++) {
		$('#box_1').append('<div id="r_l_'+mi+'"class="r_l menublock"></div>');
		ix++;
		if(ix>3) {
			ix=0;
			iy++;
		}
		$('#r_l_'+mi).css({'left':ix*(pagePeople_style.m_width+pagePeople_style.m_right)+leftPadding,'top':iy*(pagePeople_style.m_width+pagePeople_style.m_top)+66});
	}
	
	//box_2: all
	$('#box_2').append('<div id="r_l_x" class="r_l menublock"><div>"'+pagePeople_object.all_first.des+'"</div></div>');
	$('#r_l_x').css({'left':leftPadding,'top':66});
	iy=0;
	for(mi=0;mi<pagePeople_object.all_array.length;mi++){
		if(pagePeople_object.all_array[mi].alphabet!=undefined){
			$('#box_2').append('<div id="all_s_x'+mi+'" class="all_s menublock"><span>'+pagePeople_object.all_array[mi].alphabet+'</span></div>');
			$('#all_s_x'+mi).css({'left':leftPadding,'top':iy*50+66+125});
			iy++;
		}
		$('#box_2').append('<div id="all_s_'+mi+'"class="all_s menublock"><div>'+pagePeople_object.all_array[mi].name+'</div></div>');
		$('#all_s_'+mi).css({'left':leftPadding,'top':iy*50+66+125});
		iy++;
	}
	$('#box_2').css({'height':iy*50+66+125+20});
	
	//box_3: what's new
	for(mi=0;mi<pagePeople_object.what_array.length;mi++){
		if(pagePeople_object.what_array[mi].comment==0){
			$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="name">'+pagePeople_object.what_array[mi].name+'</div><div class="from">'+pagePeople_object.what_array[mi].from+'</div><div class="news accenttext">'+pagePeople_object.what_array[mi].news+'</div><div class="content">"'+pagePeople_object.what_array[mi].content+'"</div></div>');
		} else {
		$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="comment">'+pagePeople_object.what_array[mi].comment+'</div><div class="name">'+pagePeople_object.what_array[mi].name+'</div><div class="from">'+pagePeople_object.what_array[mi].from+'</div><div class="news accenttext">'+pagePeople_object.what_array[mi].news+'</div><div class="content">"'+pagePeople_object.what_array[mi].content+'"</div></div>');
		}
	}
	
	$('.menublock').css({'background-color':Theme.accentcolor});
	$('.accenttext').css({'color':Theme.accentcolor});
}

//
function peopleStartAnimation(){
	$('#box_3').css({'left':320});
		$('#box_1').css({'left':640});
		$('#box_2').css({'left':1140});
		$('#people_main').stop().animate({'left':-640},1000,function(){
			pagePeople_state.viewPos="box_1";
			$('#box_3').css({'left':-320});
			$('#box_1').css({'left':0});
			$('#box_2').css({'left':500});
			$('#people_main').css({'left':0});
			pagePeople_state.rolling=false;
		});
}

// 拖动

function drag(x,y) {
}

// 动画接口 1:x 2:280 3:320
function toleft() { //向左滑动
	pagePeople_state.rolling=true;
	var speed=1000;
	var box_1_width=parseInt($('#box_1').css('width').replace('px','')); // maybe 500
	var box_2_width=parseInt($('#box_2').css('width').replace('px','')); // maybe 280
	var box_3_width=parseInt($('#box_3').css('width').replace('px','')); // maybe 320
	if(pagePeople_state.viewPos=="box_1"){ // from 3 1 2 to 1 (2) 3
		$('#title').stop().animate({'left':-70},1200);
		$('#box_3').css({'left':box_1_width+box_2_width});
		$('#people_main').stop().animate({'left':-box_1_width},speed,function(){pagePeople_state.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}else if(pagePeople_state.viewPos=="box_2"){ // from 1 2 3 to 2 (3) 1
		$('#title').stop().animate({'left':-140},1200);
		$('#box_1').css({'left':box_2_width+box_3_width});
		$('#people_main').stop().animate({'left':-box_2_width},speed,function(){pagePeople_state.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}else if(pagePeople_state.viewPos=="box_3"){ // from 2 3 1 to 3 (1) 2
		$('#title').stop().animate({'left':0},1200);
		$('#box_2').css({'left':box_3_width+box_1_width});
		$('#people_main').stop().animate({'left':-box_3_width},speed,function(){pagePeople_state.viewPos="box_1";
															$('#box_3').css({'left':-box_3_width});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':box_1_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}
}
function toright() { //向右滑动
	pagePeople_state.rolling=true;
	var speed=1000;
	var box_1_width=parseInt($('#box_1').css('width').replace('px','')); // maybe 500
	var box_2_width=parseInt($('#box_2').css('width').replace('px','')); // maybe 280
	var box_3_width=parseInt($('#box_3').css('width').replace('px','')); // maybe 320
	if(pagePeople_state.viewPos=="box_1"){ // from 3 (1) 2 to 2 (3) 1
		$('#title').stop().animate({'left':-140},1200);
		$('#box_2').css({'left':-box_2_width-box_2_width});
		$('#people_main').stop().animate({'left':box_3_width},speed,function(){pagePeople_state.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}else if(pagePeople_state.viewPos=="box_2"){ // from 1 (2) 3 to 3 (1) 2
		$('#title').stop().animate({'left':0},1200);
		$('#box_3').css({'left':-box_3_width-box_1_width});
		$('#people_main').stop().animate({'left':box_1_width},speed,function(){pagePeople_state.viewPos="box_1";
															$('#box_3').css({'left':-box_3_width});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':box_1_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}else if(pagePeople_state.viewPos=="box_3"){ // from 2 (3) 1 to 1 (2) 3
		$('#title').stop().animate({'left':-70},1200);
		$('#box_1').css({'left':-box_1_width-box_2_width});
		$('#people_main').stop().animate({'left':box_2_width},speed,function(){pagePeople_state.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#people_main').css({'left':0});
															pagePeople_state.rolling=false;
														  });
	}
}

// 调用外部方法
