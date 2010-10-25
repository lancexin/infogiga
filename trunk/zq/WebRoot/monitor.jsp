<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>流量监控</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="watch">
	
	<link rel="shortcut icon" href="favicon.ico" />
	<script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
	<script type='text/javascript' src='js/swfobject.js'></script>
	<link rel="stylesheet" type="text/css" href="css/monitor.css">
  </head>
  
  <body>
    <!--div id='box'>
    	<div id='leftDiv'>
    		<img src='img/sniffer.png' id='left'></img>
    		<img src='img/sniffer.png' id='leftx'></img>
    		<div id='leftTitle'></div>
    		<span id='leftAvg'></span>
    		<span id='leftAll'></span>
    	</div> 
    	<div id='rightDiv'>
    		<img src='img/sniffer.png' id='right'></img>
    		<img src='img/sniffer.png' id='rightx'></img>
    		<div id='rightTitle'></div>
    		<span id='rightAll'></span>
    		<span id='rightAvg'></span>    		
    	</div>
    </div-->
   
    <div id='flash'></div>
    
    <script type='text/javascript'>
    var time = 5000; //更新频率，毫秒为单位
    var url = '/zq/watch';
    var data = 'type=watch&index=1';
    
    $(document).ready(function() {
    	loadFlash();    	
    	ajaxInit();
    	getData();
    	setInterval(getData, time);
    });
    
    function loadFlash() {
    	var so = new SWFObject("speeder.swf", "myFlash", "1024", "384", "7", "#ffffff"); 
		//参数:地址，Flash 的 id（不是容器的 id），宽，高，版本需求，背景颜色		
		so.write("flash"); 
    }
    
    /*ajax初始化*/
    function ajaxInit() {
    	$.ajaxSetup({
    		cache:false,
    		async:true
    	});
    }
    
    /*两组中选择一组显示*/
    function choose() {
    	$.post(url, data, function(msg){
    		var value 	= msg.split(',');
    		var values1 = value[0].split('_');
    		var values2 = value[1].split('_');
    		
    		if(value[2] == '2') {
    			data = 'type=watch&index=2';
    		} else {
    			data = 'type=watch&index=1';
    		}    		
    	});
    }
    
    /*从服务器获取当前流量数据信息*/
    function getData() {
    	$.post(url, data, function(msg){
    		parse(msg);
    	});
    }
    
    /*解析返回的消息*/
    function parse(msg) {
    	try{
    	var value 	= msg.split(',');
    	var values1 = value[0].split('_');
    	var values2 = value[1].split('_');
    	var avg_max = Math.max(values1[1], values2[1]);
    	var total_max = Math.max(values1[2], values2[2]);
    	var e = values1.join('_')+ '_'+ values2.join('_')+ '_'+ 
    		getAvgTop(avg_max)+ '_'+ getTotalTop(total_max);
    		
    	send(e);
    	}catch(err){}
    }
    
    /*当前流量峰值*/
    function getAvgTop(max) {
    	/*var avgs = [max, 100, 500, 1024];
    	var returnVar = 1024;
    	
    	avgs = avgs.sort();
    	for(i=0; i<avgs.length; i++) {
    		if(avgs[i] == max && i != 3) {
    			returnVars = avgs[i+1];
    			break;
    		}
    	}
    	alert(returnVar);
    	return returnVar;
    	*/
    	if(max < 100) 
    		return 100;
    	else if (max < 500)
    		return 500;
    	else 
    		return 1024;
    }
    
    /*总流量峰值*/
    function getTotalTop(max) {   
    	if(max < 1024) 
    		return 1024;
    	else if(max < 1024*10)
    		return 1024*10;
    	else if(max < 1024*100)
    		return 1024*100;
    	else if(max < 1024*1024)
    		return 1024*1024;
    	else 
    		return 1024* 1024* 3;
    }
        
    /*发送到flash*/
    function send(e) {
    	window.document.myFlash.updateSpeed(e);
    }
        
    /*保留1位小数*/
    function keepCarry(f) {
    	return Math.round(f*10)/10;
    }
    </script>
  </body>
</html>
