/*
 * Windows Phone 7 Series Metro
 * Copyright 2010, InfoGiga
 * Author: Wenbin He
 * Date: 2010/03/31
 */

//初始化界面
$(document).ready(function(){
	
});
Action = {
	down:function(){},
	up:function(){}	
}

/*Action = {
	trace:[],   //运动轨迹
	offset:0, 	//偏移量
	handler:null, //定时器句柄
	//由Java调用这个函数
	down: function() {
		var y = window.motion.getY();
		var x = window.motion.getX();
		this.trace.push(x);
		this.offset = y - getCurrentHot().css('top').replace('px','');
		this.handler = setInterval(this.run(this), 50);
	},
	//移动位置
	run: function(A) {
		return function() {
			var y = window.motion.getY();
			var x = window.motion.getX();
			A.trace.push(x);
			getCurrentHot().css('top', Math.min(y - A.offset, 48));
		}
	},	
	//停止move
	up: function() {		
		this.run();
		this.offset = 0; //重置offset
		this.trace.length = 0;
		clearInterval(this.handler);
	},
	//锁屏
	lock: function() {
		window.base.lock();
	}
}*/
