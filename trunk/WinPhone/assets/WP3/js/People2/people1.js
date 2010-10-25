/*
 * Windows Phone 7 Series Metro - lockscreen
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
// 样式参数
var stateP = {
	"viewPos":"box_1", // box_1 box_2 box_3
	"rolling":true
}
var styleP = {
	"m_width":115,
	"m_width_2":240,
	"m_right":10,
	"m_top":10
} 

// 从java传递过来的startP
var startP = {
	"recent_array":[{"name":"a"},
					{"name":"b"},
					{"name":"c"},
					{"name":"d"},
					{"name":"e"},
					{"name":"f"},
					{"name":"g"},
					{"name":"h"}],
	"all_first":{"des":"heading to the gym now"},
	//allContact:[{name:['',''], alphabet:'a'}, {name:['', ''], alphabet:'b'}],
	allContact:[],
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

function getCurrentHot() {
	switch(stateP.viewPos) {
		case "box_1":break;
		case "box_2":
			return $('#box_2');
			break;
		case "box_3":break;
	}
}

Action = {
	current:null,
	trace:[],   //运动轨迹
	offset:0, 	//偏移量
	handler:null, //定时器句柄
	//由Java调用这个函数
	down: function() {
		this.current = getCurrentHot();
		if(!this.current) return;
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push(x);
		this.offset = y - this.current.css('top').replace('px','');
		this.handler = setInterval(this.run(this), 50);
	},
	//移动位置
	run: function(A) {
		return function() {
			if(!A.current) return;
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push(x);
			getCurrentHot().css('top', Math.min(y - A.offset, 110));
		}
	},	
	//停止move
	up: function() {
		if(!this.current) return;		
		this.run();
		this.offset = 0; //重置offset
		this.trace.length = 0;
		clearInterval(this.handler);
	}
}

function containsAlphabet(c) {
	for(var i=0; i<startP.allContact.length; i++) {
		if(startP.allContact[i].alphabet.toString().toLocaleLowerCase() == c.toLocaleLowerCase()) {
			return i;
		}
	}
	return -1;
}

//从首字母
function readData() {
	var contacts = window.contact.getAll();
	var contactName = contacts.get(0).getName();
	
	var len = contacts.length()-0;
	for(var i=0; i<len; i++) {
		var contactName = contacts.get(i).getName().toLocaleString();//联系人姓名	
		if(!contactName) { //联系人姓名为空
			///进行相关处理
			continue;
		}		
		var py = getPY(contactName.substr(0,1));	//首字母拼音
		var index = containsAlphabet(py)//在allContact中的索引
		
		if(index != -1) {
			startP.allContact[index].name.push(contactName);			
		} else {
			var obj = {name:[contactName], alphabet:py.toLocaleUpperCase()};
			startP.allContact.push(obj);
		}		
	}	
	startP.allContact.sort(function(a,b){ //排序		
		if(a.alphabet < b.alphabet) {
			return -1;
		} else if(a.alphabet > b.alphabet) {
			return 1;
		} else {
			return 0;
		}
	});
	
	var recent = window.contact.getRecent(8);
	var len = Math.min(recent.length()-0, 8);
	for(var i=0; i<len; i++) {
		startP.recent_array[i].name = recent.get(i).getName() || recent.get(i).getNumber();
	}	
}

$(document).ready(function() {
	readData();
	init(startP);//初始化
	$('#plus').click(function(){
		window.base.deactivate('people');
		window.base.activate('start');
	});
	//测试方法
	$('#title').click(function(){
							 //***********************It's a test*****************************//
						//////toright();
						toleft();
					});
});

// 内部方法
function init(startP) {
	//stateP
	$('#box_3').css({'left':320});
	$('#box_1').css({'left':640});
	$('#box_2').css({'left':1140});
	$('#main').stop().animate({'left':-640},1000,function(){stateP.viewPos="box_1";
															$('#box_3').css({'left':-320});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':500});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	
	var leftPadding=10;
	//box_1: recent
	var mi;
	var ix=-1;
	var iy=0;
	for (mi=0;mi<startP.recent_array.length;mi++) {
		$('#box_1').append('<div id="r_l_'+mi+'" class="r_l">'+ startP.recent_array[mi].name+ '</div>');
		ix++;
		if(ix>3) {
			ix=0;
			iy++;
		}
		$('#r_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right)+leftPadding,'top':iy*(styleP.m_width+styleP.m_top)+66});
	}
	
	//box_2: all
	$('#box_2').append('<div id="r_l_x" class="r_l"><div>"'+startP.all_first.des+'"</div></div>');
	$('#r_l_x').css({'left':leftPadding,'top':66});
	iy=0;
	/*for(mi=0;mi<startP.all_array.length;mi++){
		if(startP.all_array[mi].alphabet!=undefined){
			$('#box_2').append('<div id="all_s_x'+mi+'" class="all_s"><span>'+startP.all_array[mi].alphabet+'</span></div>');
			$('#all_s_x'+mi).css({'left':leftPadding,'top':iy*50+66+125});
			iy++;
		}
		$('#box_2').append('<div id="all_s_'+mi+'"class="all_s"><div>'+startP.all_array[mi].name+'</div></div>');
		$('#all_s_'+mi).css({'left':leftPadding,'top':iy*50+66+125});
		iy++;
	}
	$('#box_2').css({'height':iy*50+66+125+20});*/
	for(mi=0; mi<startP.allContact.length;mi++) {
		$('#box_2').append('<div id="all_s_x'+mi+'" class="all_s"><span>'+startP.allContact[mi].alphabet+'</span></div>');
		$('#all_s_x'+mi).css({'left':leftPadding,'top':iy*50+66+125});
		iy++;
		
		for(var x=0; x<startP.allContact[mi].name.length; x++) {
			$('#box_2').append('<div id="all_s_'+iy+'"class="all_s"><div>'+startP.allContact[mi].name[x]+'</div></div>');
			$('#all_s_'+iy).css({'left':leftPadding,'top':iy*50+66+125});
			iy++;
		}
	}
	$('#box_2').css({'height':iy*50+66+125+20});
	
	//box_3: what's new
	for(mi=0;mi<startP.what_array.length;mi++){
		if(startP.what_array[mi].comment==0){
			$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="name">'+startP.what_array[mi].name+'</div><div class="from">'+startP.what_array[mi].from+'</div><div class="news">'+startP.what_array[mi].news+'</div><div class="content">"'+startP.what_array[mi].content+'"</div></div>');
		} else {
		$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="comment">'+startP.what_array[mi].comment+'</div><div class="name">'+startP.what_array[mi].name+'</div><div class="from">'+startP.what_array[mi].from+'</div><div class="news">'+startP.what_array[mi].news+'</div><div class="content">"'+startP.what_array[mi].content+'"</div></div>');
		}
	}
}

// 拖动

function drag(x,y) {
}

// 动画接口 1:x 2:280 3:320
function toleft() { //向左滑动
	stateP.rolling=true;
	var speed=1000;
	var box_1_width=$('#box_1').css('width').replace('px','')-0; // maybe 500
	var box_2_width=$('#box_2').css('width').replace('px','')-0; // maybe 280
	var box_3_width=$('#box_3').css('width').replace('px','')-0; // maybe 320
	if(stateP.viewPos=="box_1"){ // from 3 1 2 to 1 (2) 3
		//$('#title').stop().animate({'left':-70},1200);
		$('#box_3').css({'left':box_1_width+box_2_width});
		$('#main').stop().animate({'left':-box_1_width},speed,function(){stateP.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_2"){ // from 1 2 3 to 2 (3) 1
		//$('#title').stop().animate({'left':-140},1200);
		$('#box_1').css({'left':box_2_width+box_3_width});
		$('#main').stop().animate({'left':-box_2_width},speed,function(){stateP.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_3"){ // from 2 3 1 to 3 (1) 2
		//$('#title').stop().animate({'left':0},1200);
		$('#box_2').css({'left':box_3_width+box_1_width});
		$('#main').stop().animate({'left':-box_3_width},speed,function(){stateP.viewPos="box_1";
															$('#box_3').css({'left':-box_3_width});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':box_1_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}
}
function toright() { //向右滑动
	stateP.rolling=true;
	var speed=1000;
	var box_1_width=parseInt($('#box_1').css('width').replace('px','')); // maybe 500
	var box_2_width=parseInt($('#box_2').css('width').replace('px','')); // maybe 280
	var box_3_width=parseInt($('#box_3').css('width').replace('px','')); // maybe 320
	if(stateP.viewPos=="box_1"){ // from 3 (1) 2 to 2 (3) 1
		$('#title').stop().animate({'left':-140},1200);
		$('#box_2').css({'left':-box_2_width-box_2_width});
		$('#main').stop().animate({'left':box_3_width},speed,function(){stateP.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_2"){ // from 1 (2) 3 to 3 (1) 2
		$('#title').stop().animate({'left':0},1200);
		$('#box_3').css({'left':-box_3_width-box_1_width});
		$('#main').stop().animate({'left':box_1_width},speed,function(){stateP.viewPos="box_1";
															$('#box_3').css({'left':-box_3_width});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':box_1_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_3"){ // from 2 (3) 1 to 1 (2) 3
		$('#title').stop().animate({'left':-70},1200);
		$('#box_1').css({'left':-box_1_width-box_2_width});
		$('#main').stop().animate({'left':box_2_width},speed,function(){stateP.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}
}
Key = {
	menu: function(){
		$.lockScreen({background:'url(../../image/spec/wallpaper.png) no-repeat'});		
	}	
}

// 调用外部方法
