/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/31
 */

function wp7start(){
	$('#welcome').stop().animate({'top':-480,'opacity':0},1000,function(){
		$('#welcome').remove();
	});
}

function unlock(){
	$('#lockscreen').stop().animate({'top':-480},1000,function(){
		$('#lockscreen').css({'top':-480});
	});
}

function lock(){
	$('#lockscreen').stop().animate({'top':0},1000,function(){
		$('#lockscreen').css({'top':0});
	});
}

function openpeople(){
	$('#menu_box_right').css({'display':'none'});
	$('#start').stop().animate({'left':-640},1000,function(){
		$('#menu_box_right').css({'display':''});
	});
	$('#people').css({'left':320});								   
		$('#people').stop().animate({'left':0},1500,function(){
	});
	peopleStartAnimation();
}