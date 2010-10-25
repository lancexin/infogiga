/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/31
 */
 
//系统定义
var ColorPad = {
	'dark':'#141414',
	'light':'#fff',
	'blue':'#1BA1E2',
	'red':'#E51400',
	'orange':'#F09609',
	'green':'#339933'
};
var Shortcut_L = {
	'phone':'url(images/Shorcut_L/phone.png)',
};
var Shortcut_S = {
	'phone':'url(images/Shorcut_S/phone.png)',
};
var Theme = {
	'bgcolor':ColorPad.dark,
	'accentcolor':ColorPad.blue,
};

//初始化主题参数
// Ask java
/* 

*/

//初始化界面
$(document).ready(function(){
	$('body').css({'backgroundColor':Theme.bgcolor});
});
