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
	"recent_array":[{"name":"a",pid:"-1"},
					{"name":"b",pid:"-1"},
					{"name":"c",pid:"-1"},
					{"name":"d",pid:"-1"},
					{"name":"e",pid:"-1"},
					{"name":"f",pid:"-1"},
					{"name":"g",pid:"-1"},
					{"name":"h",pid:"-1"}],
	//recent_array:[],
	"all_first":{"des":"heading to the gym now"},
	//allContact:[{name:['a','am'], alphabet:'a', pid:[-1,-1]}, {name:['b', 'bit'], alphabet:'b', pid:[-1,-1]}],
	allContact:[],
	"what_array":[{"name":"Holly Dickson","from":"Windows Live","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":2},
				  {"name":"Shai Bassli","from":"Facebook","news":"Wrote on Luis Alverca's wall",
				  "content":"Thank you for showing me around town yesterday. I really like the new downtown area and that wine bar.","comment":0},
				  {"name":"Anat Kerry","from":"Facebook","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":5},
				  {"name":"Holly Dickson","from":"Windows Live","news":"Updated her status 23 minutes ago",
				  "content":"Taking the grandkids to the zoo.","comment":3}]
}

Key = {
	menu: function(){
		$.lockScreen();		
	},
	back:function(){
		if($('#alphabet').css('top').replace('px', '')-0 == 0) {
			$('#alphabet').css({top:-480});
			return;
		}
		if($.locked) return;
		window.base.deactivate('People.people');
		window.base.activate('Start.start');
	}
}

function getCurrentHot() {
	switch(stateP.viewPos) {
		case "box_1":
			return $('#box_1');
			break;
		case "box_2":
			return $('#box_2');
			break;
		case "box_3":
			return $('#box_3');
			break;
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
		if($.locked) {
			Locker.lockDown();
			return;
		}
		if(!this.current) return;
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push({x:x,y:y});
		if(this.current.attr('id') == $('#box_1').attr('id')) {
			this.offset = x - this.current.css('left').replace('px','');
		}
		else if(this.current.attr('id') == $('#box_2').attr('id')) {
			this.offset = y - this.current.css('top').replace('px','');
		}
		this.handler = setInterval(this.run(this), 50);
	},
	//移动位置
	run: function(A) {
		return function() {
			if(!A.current || $.locked) return;
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push({x:x,y:y});
			if(A.current.attr('id') == $('#box_1').attr('id')) {
				getCurrentHot().css('left', Math.max(Math.min(x - A.offset, 0), -180));
			}
			else if(A.current.attr('id') == $('#box_2').attr('id')) {
				getCurrentHot().css('top', Math.min(y - A.offset, 110));
			}
		}
	},	
	//停止move
	up: function() {
		if($.locked) {
			Locker.lockUp();
			return;
		}
		if(!this.current) return;		
		this.run();
		var dir = Gesture.direction(this.trace);
		if(dir == 'left') {
			toleft();
		} else if(dir == 'right') {
			toright();
		}
		this.offset = 0; //重置offset
		this.trace.length = 0
		clearInterval(this.handler);
	},
	show: function(n, p) {
		window.base.deactivate('People.people');
		window.base.activate(n, p);
	}
}

function reset() {
	
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
	var contacts = Data_contact;
	//var contactName = contacts.get(0).getName();
	
	var len = contacts.length;
	for(var i=0; i<len; i++) {
		var contactName = contacts[i].name;//联系人姓名	
		var pid = contacts[i].pid;
		if(!contactName) { //联系人姓名为空
			///进行相关处理
			continue;
		}		
		var py = getPY(contactName.substr(0,1));	//首字母拼音
		var index = containsAlphabet(py)//在allContact中的索引
		
		if(index != -1) {
			startP.allContact[index].name.push(contactName);
			startP.allContact[index].pid.push(pid);			
		} else {
			var obj = {name:[contactName], alphabet:py.toLocaleLowerCase(), pid:[pid]};
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
	
	var recent = Data_recent;
	var len = Math.min(recent.length, 8);
	for(var i=0; i<len; i++) {
		startP.recent_array[i].name = recent[i].name || recent[i].number;
		startP.recent_array[i].pid = recent[i].pid;
	}	
}

function load() {
	readData();
	init(startP);//初始化
	createLocker();
}

function initialize() {
	$('#box_3').css({'left':320});
	$('#box_1').css({'left':640});
	$('#box_2').css({'left':1140});
	$('#main').stop().animate({'left':-640},500,function(){stateP.viewPos="box_1";
															$('#box_3').css({'left':-320});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':500});
															$('#main').css({'left':0});
															stateP.rolling=false;});
}

/*$(document).ready(function() {
	//readData();
	init(startP);//初始化
	createLocker();
	//测试方法	
	/*$('#all_s_x'+0).click(function(){
		clickAlphabet();
	});
});*/

// 内部方法
function init(startP) {
	//stateP
	//$('#box_3').css({'left':320});
	//$('#box_1').css({'left':640});
	//$('#box_2').css({'left':1140});
	/*$('#main').stop().animate({'left':-640},1000,function(){stateP.viewPos="box_1";
															$('#box_3').css({'left':-320});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':500});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });*/
	
	var leftPadding=10;
	//box_1: recent
	var mi;
	var ix=-1;
	var iy=0;
	for (mi=0;mi<startP.recent_array.length;mi++) {
		$('#box_1').append('div', {'id':'r_l_'+mi, 'class':'r_l', pid:startP.recent_array[mi].pid}, startP.recent_array[mi].name);
		
		$('#r_l_'+mi).click(function(){
			var pid = this.attr('pid');	
			if(pid != -1) {
				Action.show('People.contactDetail', pid);
			}
		});
		ix++;
		if(ix>3) {
			ix=0;
			iy++;
		}
		$('#r_l_'+mi).css({'left':ix*(styleP.m_width+styleP.m_right)+leftPadding,'top':iy*(styleP.m_width+styleP.m_top)+66});
	}
	
	//box_2: all
	$('#box_2').append('div', {id:"r_l_x", 'class':"r_l menublock"}, startP.all_first.des);

	$('#r_l_x').css({'left':leftPadding,'top':66});
	iy=0;
	
	for(index=0;index<startP.allContact.length;index++){
		//添加字母标识
		if(startP.allContact[index].name[0]!=''){
			$('#box_2').append('div', {id:'all_s_x'+index,'class':'all_s menublock'}).append('span',{},startP.allContact[index].alphabet);
			$('#all_s_x'+index).css({'left':leftPadding,'top':iy*50+66+125});
			$('#all_s_x'+index).click(function(){clickAlphabet();});
			iy++;
		}else{
			continue;	
		}
		//循环以x字母开头的名字
		for(i=0;i<startP.allContact[index].name.length;i++){
			$('#box_2').append('div', {id:'all_s_'+index+'_'+i, 'class':'all_s menublock', pid:startP.allContact[index].pid[i]}).append('div', {}, startP.allContact[index].name[i]);
			$('#all_s_'+index+'_'+i).css({'left':leftPadding,'top':iy*50+66+125});
			//$('#all_s_'+index+'_'+i).attr('pid', startP.allContact[index].pid[i]);
			$('#all_s_'+index+'_'+i).click(function(){
				Action.show('People.contactDetail', $(this).attr('pid'));
			});
			iy++;
		}
	}
	$('#box_2').css({'height':iy*50+66+125+20});
	
	//box_3: what's new
	for(mi=0;mi<startP.what_array.length;mi++){
		if(startP.what_array[mi].comment==0){
//			$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="name">'+startP.what_array[mi].name+'</div><div class="from">'+startP.what_array[mi].from+'</div><div class="news accenttext">'+startP.what_array[mi].news+'</div><div class="content">"'+startP.what_array[mi].content+'"</div></div>');
			var r = document.createElement('div');
			r.id = 'what_'+mi;
			r.className = 'what';			

			var n = document.createElement('div');
			var tx = document.createTextNode(startP.what_array[mi].name);
			n.className = 'name';
			n.appendChild(tx);
			r.appendChild(n);
			
			var from = document.createElement('div');
			var ftx = document.createTextNode(startP.what_array[mi].from);
			from.className = 'from';
			from.appendChild(ftx);
			r.appendChild(from);

			var news = document.createElement('div');
			var ntx = document.createTextNode(startP.what_array[mi].news);
			news.className = 'news accenttext';
			news.appendChild(ntx);
			r.appendChild(news);

			var content = document.createElement('div');
			var ctx = document.createTextNode(startP.what_array[mi].content);
			content.className = 'news accenttext';
			content.appendChild(ctx);
			r.appendChild(content);

			document.getElementById('box_wrap').appendChild(r);

		} else {
//		$('#box_3 .box_wrap').append('<div id="what_'+mi+'"class="what"><div class="comment">'+startP.what_array[mi].comment+'</div><div class="name">'+startP.what_array[mi].name+'</div><div class="from">'+startP.what_array[mi].from+'</div><div class="news accenttext">'+startP.what_array[mi].news+'</div><div class="content">"'+startP.what_array[mi].content+'"</div></div>');
			var r = document.createElement('div');
			r.id = 'what_'+mi;
			r.className = 'what';

			var comment = document.createElement('div');
			var cctx = document.createTextNode(startP.what_array[mi].comment);
			comment.className = 'comment';
			comment.appendChild(cctx);
			r.appendChild(comment);

			var n = document.createElement('div');
			var tx = document.createTextNode(startP.what_array[mi].name);
			n.className = 'name';
			n.appendChild(tx);
			r.appendChild(n);
			
			var from = document.createElement('div');
			var ftx = document.createTextNode(startP.what_array[mi].from);
			from.className = 'from';
			from.appendChild(ftx);
			r.appendChild(from);

			var news = document.createElement('div');
			var ntx = document.createTextNode(startP.what_array[mi].news);
			news.className = 'news accenttext';
			news.appendChild(ntx);
			r.appendChild(news);

			var content = document.createElement('div');
			var ctx = document.createTextNode(startP.what_array[mi].content);
			content.className = 'news accenttext';
			content.appendChild(ctx);
			r.appendChild(content);

			document.getElementById('box_wrap').appendChild(r);
		}
	}
	mcss($('.menublock'), {'background-color':Theme.accentcolor});
	mcss($('.accenttext'), {'color':Theme.accentcolor});
}

// 拖动

function drag(x,y) {
}

// 动画接口 1:x 2:280 3:320
function toleft() { //向左滑动
	stateP.rolling=true;
	var speed=500;
	var box_1_width=$('#box_1').css('width')-0; // maybe 500
	var box_2_width=$('#box_2').css('width')-0; // maybe 280
	var box_3_width=$('#box_3').css('width')-0; // maybe 320
	if(stateP.viewPos=="box_1"){ // from 3 1 2 to 1 (2) 3
		$('#title').stop().animate({'left':-70},600);
		$('#box_3').css({'left':box_1_width+box_2_width});
		$('#main').stop().animate({'left':-box_1_width},speed,function(){
															stateP.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	} else if(stateP.viewPos=="box_2"){ // from 1 2 3 to 2 (3) 1
		$('#title').stop().animate({'left':-140},600);
		$('#box_1').css({'left':box_2_width+box_3_width});
		$('#main').stop().animate({'left':-box_2_width},speed,function(){
															stateP.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	} else if(stateP.viewPos=="box_3"){ // from 2 3 1 to 3 (1) 2
		$('#title').stop().animate({'left':0},600);
		$('#box_2').css({'left':box_3_width+box_1_width});
		$('#main').stop().animate({'left':-box_3_width},speed,function(){
															stateP.viewPos="box_1";
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
	var speed=500;
	var box_1_width=$('#box_1').css('width')-0; // maybe 500
	var box_2_width=$('#box_2').css('width')-0; // maybe 280
	var box_3_width=$('#box_3').css('width')-0; // maybe 320
	if(stateP.viewPos=="box_1"){ // from 3 (1) 2 to 2 (3) 1
		$('#title').stop().animate({'left':-140},600);
		$('#box_2').css({'left':-box_2_width-box_2_width});
		$('#main').stop().animate({'left':box_3_width},speed,function(){
			stateP.viewPos="box_3";
															$('#box_2').css({'left':-box_2_width});
															$('#box_3').css({'left':0});
															$('#box_1').css({'left':box_3_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_2"){ // from 1 (2) 3 to 3 (1) 2
		$('#title').stop().animate({'left':0},600);
		$('#box_3').css({'left':-box_3_width-box_1_width});
		$('#main').stop().animate({'left':box_1_width},speed,function(){
			stateP.viewPos="box_1";
															$('#box_3').css({'left':-box_3_width});
															$('#box_1').css({'left':0});
															$('#box_2').css({'left':box_1_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}else if(stateP.viewPos=="box_3"){ // from 2 (3) 1 to 1 (2) 3
		$('#title').stop().animate({'left':-70},600);
		$('#box_1').css({'left':-box_1_width-box_2_width});
		$('#main').stop().animate({'left':box_2_width},speed,function(){
			stateP.viewPos="box_2";
															$('#box_1').css({'left':-box_1_width});
															$('#box_2').css({'left':0});
															$('#box_3').css({'left':box_2_width});
															$('#main').css({'left':0});
															stateP.rolling=false;
														  });
	}
}

//字母表
function clickAlphabet(){
	showAlphabet();
	$('#alphabet').css({'top':0});
}
function showAlphabet(){
	if($('.alpha_block').length >= 1) {
		return;
	} 
	var a=new Array('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
	var ax=0;
	var ay=0;
	$('#alphabet').append('div', {id:"alphabet_first", 'class':"alpha_block"}, '#');
	$('#alphabet_first').css({'left':26+ax*67,'top':10+ay*67});
	$('#alphabet_first').css({'backgroundColor':Theme.accentcolor});
	ax++;
	for(ai=0;ai<a.length;ai++){
		$('#alphabet').append('div', {id:'alphabet_'+ai, 'class':"alpha_block"}, a[ai]);
		if(ax>3){
			ax=0;
			ay++;
		}
		$('#alphabet_'+ai).css({'left':26+ax*67,'top':10+ay*67});
		var find = findFromAll(a[ai]);
		if(find) {
			$('#alphabet_'+ai).css({'backgroundColor':Theme.accentcolor});
			$('#alphabet_'+ai).click(function(j){
				return function(){
					var index = j;
					alphabetFilter(a, a[index]);
				}
			}(ai));
		} else {
			$('#alphabet_'+ai).css({'backgroundColor':''});
		}
		ax++;
	}
}
function findFromAll() {
	var alpha = arguments[0] || '';
	for(var i=0; i<startP.allContact.length; i++) {
		if(startP.allContact[i] && startP.allContact[i].alphabet == alpha) {
			return true;
		}
	}
	return false;
}
function alphabetFilter(a, x) {
	$('#alphabet').css({'top':-480});
	var array  = a;

	for(var i=0; i<array.length; i++){
		if(!$('#all_s_x'+i)) continue;
		if(document.getElementById('all_s_x'+i).children[0].innerHTML == x) {
			var top = 110-$('#all_s_x'+i).css('top');
			$('#box_2').animate({top:top}, 200);	
			$('#box_2').css({top:top});
			break;
		}
	}	
}
