//视觉、动作
$(document).ready(function(){
	$('.logo img').bind('click', goChinaMobile);
	$('#st').bind('click', function() {
		getDateString(document.getElementById('stime'),oCalendarChs);
		//document.all.searchBar.style.top = parent.document.body.scrollTop+ parent.document.body.clientHeight- 240;
	});
	$('#et').bind('click', function() {
		getDateString(document.getElementById('etime'),oCalendarChs);
		//document.all.searchBar.style.top = parent.document.body.scrollTop+ parent.document.body.clientHeight- 240; 
	});
	$('#query').bind('click', chartQuery);
	$('#fastQuery').bind('click', fastQuery);
	$('#buildReport').bind('click', report);
	$('#up').bind('click', up);
	$('#down').bind('click', down);
	fixSearchBar();
	changeFrameHeight(380);
	parent.document.body.onscroll = fixSearchBar;
	showSearchBar();
});

function showSearchBar() {
	$('.showBar').click(function() {
		if($('.searchBar').css('display') == 'none') {
			$('.searchBar').show(100);
		} else {
			$('.searchBar').hide(200);
		}
	});
}

function up() {	
	parent.document.body.scrollTop = 0;
}

function down() {
	parent.document.body.scrollTop = parent.document.body.scrollHeight;	
}

function fixSearchBar() {
	if($('.searchBar').css('display') != 'none') {
		$('.searchBar').hide();
	}
	document.all.showBar.style.top = parent.document.body.scrollTop+ parent.document.body.clientHeight- 400;
	document.all.searchBar.style.top = parent.document.body.scrollTop+ parent.document.body.clientHeight- 400;	
	changeFrameHeight();	
}

function goChinaMobile() {
	window.open('http://www.zj.chinamobile.com');
}

function query(type) {//查询  			
	var params = 
	{type: type,
	 area: $('#area').val(),
	 selling: $('#selling').val(),
	 region: $('#region').val(),
	 equipment: $('#equipment').val(),
	 sys: $('#sys').val(),
	 operate: $('#operate').val(),
	 stime: transFormat($('#stime').val()),
	 etime: transFormat($('#etime').val()),
	 mobile: $('#mobile').val()
	};
	var url = '/zq/door?'+ $.param(params);
	if(!check()) {return;}//检查日期合法性
	
	$('#query').attr('disabled', 'true');
	$('#fastQuery').attr('disabled', 'true');
	$('.searchBar').hide(200);
	$('.layer').text('查询中, 请稍后...');
	$.ajax({
		type:'post',
		url:url,
		//async:false, //同步
		cache:false, //缓存
		success:function(msg) { 
			$('.layer').hide();
			$('#buildReport').show(); //显示报表产生按钮
			dataFrame.window.location.reload(); //重新载入			
		},
		error:function(msg) {
			$('.layer').text('查询失败');
		},
		complete:function() {
			$('#query').removeAttr('disabled');
			$('#fastQuery').removeAttr('disabled');
		}
	});
}

function heightAdapter() {
	var height = document.body.scrollHeight;
	parent.document.getElementById('page').style.height = height;
}

function chartQuery() {//生成图表
	query('search');
}

function fastQuery(){//不生成图表
	query('fast');
}

function check() {//检验日期的合法性
	var stime = $('#stime').val();
	var etime = $('#etime').val();
	var phone = $('#mobile').val();
	var flags = false;
	var flage = false;
	var flagp = false;
	
	checkStarttime();
	checkEndtime();
	checkPhone();
	return flags && flage && flagp;
	
	function checkStarttime() {//检查starttime
		if(stime=='' || /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/.test(stime)) {//时间可以为空
			$('#stime').css('border-color', '');
			flags = true;
		}
		else {
			$('#stime').css('border-color', 'red');
			flags = false;
		}
	}
	
	function checkEndtime() {//检查endtime
		if(etime=='' || /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/.test(etime)) {  					
			$('#etime').css('border-color', '');
			flage = true;
		}
		else {
			$('#etime').css('border-color', 'red');
			flage = false;
		}
	}
	
	function checkPhone() {//检验手机号码的合法性	  			
		if(/^\d{7,14}$/.test(phone) || phone=='') {
			$('#mobile').css('border-color', '');
			flagp = true; //7到14位数字	  				
		}
		else {
			$('#mobile').css('border-color', 'red');
			flagp = false;	  				
		}
	}
}  		

function hideSearchBar() {//隐藏搜索栏
	
}

function changeFrameHeight() {//frame自适应高度
	var height = dataFrame.document.body.scrollHeight;
	var delta = arguments[0] || 0;
	
	if(height < 380) delta=380;	
	$('.showData').height(height+ delta);
	$('#dataFrame').height(height+ delta);
	heightAdapter();	
}

function transFormat(str) {//时间格式转换
	if(!/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/.test(str)) return '';
	return str.split(' ')[0].split('-').join('')+str.split(' ')[1].split(':').join('');
}

function report() {//报表
	$('.searchBar').hide(200);
	var url = '/zq/door?type=report';
	//location.replace(url);
	reportForm.submit();
}
  	