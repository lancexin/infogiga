/*
 * Windows Phone 7 Series Metro - header
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
// 参数

$(document).ready(function() {
	$('#main').click(function(){
							easeIn();
						});
	$('#icon_1').css({'left':10,'top':-26});
	$('#icon_2').css({'left':40,'top':-26});
	$('#icon_3').css({'left':70,'top':-26});
	$('#icon_4').css({'left':100,'top':-26});
});

// 内部方法
function easeIn(){
	$('#icon_1').stop().animate({'top':0},100);
	$('#icon_2').stop().animate({'top':0},200);
	$('#icon_3').stop().animate({'top':0},300);
	$('#icon_4').stop().animate({'top':0},400);
}

function easeOut(){
	$('#icon_1').stop().animate({'top':'-26'},300);
}

// 调用外部方法
