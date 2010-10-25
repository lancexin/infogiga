/*
 * Windows Phone 7 Series Metro - lockscreen
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/23
 */
 
// 参数
var Profile = {
	
}

var weekday=new Array(7);
	weekday[0]="Sunday";
	weekday[1]="Monday";
	weekday[2]="Tuesday";
	weekday[3]="Wednesday";
	weekday[4]="Thursday";
	weekday[5]="Friday";
	weekday[6]="Saturday";
	
var month=new Array(12);
	month[0]="January";
	month[1]="February";
	month[2]="March";
	month[3]="April";
	month[4]="May";
	month[5]="June";
	month[6]="July";
	month[7]="August";
	month[8]="September";
	month[9]="October";
	month[10]="November";
	month[11]="December";

$(document).ready(function() {
	init();//初始化
	clock();
});

// 内部方法

function init() {
	
}

//显示时间、日期
function clock() {
	var d=new Date();
	$('.now_time').html(d.getHours()+':'+d.getMinutes());
	$('.now_day').html(weekday[d.getDay()]);
	$('.now_date').html(d.getDate()+' '+month[d.getMonth()]);
	myClock=setTimeout("clock()",1000);
}

// 调用外部方法
