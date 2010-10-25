$(document).ready(function(){
	$('#login').bind('click', check);
	$('#userName').focus(); 
	$('#userName, #password').bind('keydown', function(event) {
		if(event.keyCode == 13) {
			check();
		}
	});  			
}); 

function check() {
	if(/^\s*$/.test($('#userName').val())) {
		alert("用户名不能为空");
		//$('.stateBlock').text('用户名不能为空');
		$('#userName').focus();
	}
	else if(/\s+/.test($('#userName').val())) {
		alert("用户名不能用空格");
		//$('.stateBlock').text('用户名不能有空格');
		$('#userName').focus();
	}
	else if(!/^\w{5,20}$/.test($('#password').val())) {
		//$('.stateBlock').text();
		alert('密码必须是5到20位');
		$('#password').val('');
		$('#password').focus();
	}
	else {
		login();
	}
}

function login() {
	var params = 
	{type:'login',
	 user: $('#userName').val(),
	 pwd: $('#password').val()
	}
	var url = '/zq/door?'+$.param(params);
	$('.stateBlock').ajaxSend(function(){
		//$(this).text('登录中，请稍后...');
	});
	$('#submitButton').attr('disabled', 'true');
	$.ajax({
		type:'post',
		url:url,
		cache:false, //缓存
		success:function(msg) {
			if(/\s*true\s*/.test(msg)) {
				window.location.href = 'index.jsp';
			}
			else if(msg == -2) {
				reset();  			
				alert("没有这个用户，请重新输入!");			 
				//$('.stateBlock').text('没有这个用户，请重新输入!');
			}
			else if(msg == -1) { 
				$('#password').val('').focus();  		
				alert('密码错误，请重新输入!');				
				//$('.stateBlock').text('密码错误，请重新输入!');
			}
			else if(/\s*false\s*/.test(msg)) {
				reset();
				alert('未知错误，登录失败!');
				//$('.stateBlock').text('未知错误，登录失败!');
			}
		},
		error:function(msg) {
			$('.stateBlock').text('网络传输异常!');
		},
		complete:function() {
			$('#submitButton').removeAttr('disabled');
		}
	});
}

function reset() {
	$('#userName').val('');
	$('#password').val('');
	$('#userName').focus(); 
}
