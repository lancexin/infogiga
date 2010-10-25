<%@ page language="java" import="java.util.*,cn.infogiga.filther.LoginInfo" pageEncoding="utf-8"%>
<%
String technicians = (String)request.getAttribute("technicians");
String guiders = (String)request.getAttribute("guiders");
String mmsTempletes = (String)request.getAttribute("mmsTempletes");
String defaultTechincian = (String)request.getAttribute("defaultTechincian");
String defaultGuider = (String)request.getAttribute("defaultGuider");
String defaultMMSTemplate = (String)request.getAttribute("defaultTechincian");
LoginInfo info = (LoginInfo)session.getAttribute("userInfo");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>日程管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="favicon.ico" />
<link href="Theme/Default/main.css" rel="stylesheet" type="text/css" />
<link href="Theme/Default/dailog.css" rel="stylesheet" type="text/css" />
<link href="Theme/Default/calendar.css" rel="stylesheet" type="text/css" /> 
<link href="Theme/Default/dp.css" rel="stylesheet" type="text/css" />   
<link href="Theme/Default/alert.css" rel="stylesheet" type="text/css" />     
<link href="Theme/Default/shader.css" rel="stylesheet" type="text/css" />     
<link href="Theme/Default/add.css" rel="stylesheet" type="text/css" />     
<link href="Theme/Default/request.css" rel="stylesheet" type="text/css" />
<link href="Theme/Shared/blackbird.css" rel="stylesheet" type="text/css" />


<script src="Javascripts/jquery.min.js" type="text/javascript"></script>  

<script src="Javascripts/Common.js" type="text/javascript"></script>    
<script src="Javascripts/jscroll.js" type="text/javascript"></script>
<script src="Javascripts/Plugins/jquery.datepicker.js" type="text/javascript"></script>
<script src="Javascripts/Plugins/jquery.color.js" type="text/javascript"></script>  
<script src="Javascripts/Plugins/jquery.validate.js" type="text/javascript"></script>  

<script src="Javascripts/Plugins/jquery.calendar.0.1.js" type="text/javascript"></script>   
<script src="Javascripts/Plugins/jquery.ajaxselect.js" type="text/javascript"></script>
<script src="Javascripts/Plugins/ajaxupload.js" type="text/javascript"></script>
<script src="Javascripts/Plugins/jquery.linscroll.js" type="text/javascript"></script>
<script type="text/javascript">
var userInfo={
	name:"<%=info.name%>",
	mail:"<%=info.mail%>",
	phoneNumber:"<%=info.phoneNumber%>"
};

var technicianList = <%=technicians%>;
var guiderList = <%=guiders%>;
var mmsTempleteList = <%=mmsTempletes%>;
var defaultTechincian = <%=defaultTechincian %>;
var defaultGuider = <%=defaultGuider %>;
var defaultMMSTemplate = <%=defaultMMSTemplate %>;

var op = {
	view: "week",
	theme:3,
	showday: new Date(),  
	onWeekOrMonthToDay:function(p){
		if (p && p.datestrshow) {
		     $("#txtdatetimeshow").text(p.datestrshow);
		}
		$("#caltoolbar div.fcurrent").each(function() {
		     $(this).removeClass("fcurrent");
		})
		$("#showdaybtn").addClass("fcurrent");
	},
	onBeforeRequestData:false, 
	onAfterRequestData:false,
	onRequestDataError: function(type,date){
		  alert("错误代号："+type);
		  $("#errorpannel").show();
	}, 
	url: "items.htm?show" ,  
	quickAddUrl: "items.htm?add", //快速添加日程Post Url 地址
	quickUpdateUrl:"items.htm?update",
	quickDeleteUrl: "items.htm?delete"//快速删除日程的 
};

function clear_3model(el) {
	$("#day").removeClass("head_3model_active");
	$("#day").addClass("head_3model");
	$("#day").css({
		color:"#ffffff"
	});
	$("#week").removeClass("head_3model_active");
	$("#week").addClass("head_3model");
	$("#week").css({
		color:"#ffffff"
	});
	$("#month").removeClass("head_3model_active");
	$("#month").addClass("head_3model");
	$("#month").css({
		color:"#ffffff"
	});
	el.addClass("head_3model_active");
	el.css({
		color:"#3B6EAF"
	});
}

function right_menu_choosed(el) {
	$("#head_right_menu").find("a").each(function() {
		//$(this).removeClass("right_menu_choosed");
		$(this).stop().animate({"color":"rgb(63,114,177)"}, {"duration":"500"});
	});
	el.stop().animate({"color":"rgb(256,256,256)"}, {"duration":"500"});
}



function CloseOpen() {
	$.post("exit.htm","",function(html){
		window.location = "/online";
	});
} 

$(document).ready(function() {
	
	$.each(technicianList,function(i,n){
		var el = $("<option value="+n.id+">"+n.name+"</option>");
		el.data("dt",n);
		$('#add-info-form [name="guider"]').append(el);
	});
	
	$.each(guiderList,function(i,n){
		var el = $("<option value="+n.id+">"+n.name+"</option>");
		el.data("dt",n);
		$('#add-info-form [name="technician"]').append(el);
	});
	
	$.each(mmsTempleteList,function(i,n){
		var el = $("<option value="+n.id+">"+n.name+"</option>");
		el.data("dt",n);
		$('#add-info-form [name="mmsContent"]').append(el);
	});
	
	
	 function getDateString(data,view){
        	//alert(option.view);
        	var year = data.getFullYear();
	    	var month = data.getMonth()+1;
	    	var day = data.getDate();
        	if(view == "day"){
        		return year+"年"+month+"月"+day+"日";
        	}else if(view == "week"){
        		return year+"年"+month+"月"+day+"日";
        	}else if(view == "month"){
	    		return year+"年"+month+"月";
        	}
        }
	
	
   $("#today").click(function() {
     	var p = $("#gridcontainer").BCalGoToday().BcalGetOp();
	    if (p && p.datestrshow) {
	        //$("#date").text(p.datestrshow);
	        $("#date").text(getDateString(p.showday,p.view));
	        
	    }
   });
   
   $("#day").click(function() {
		clear_3model($(this));
		//初始话当前面板
		var p = $("#gridcontainer").BCalSwtichview("day").BcalGetOp();
		//设置日期选择器的值
		if (p && p.datestrshow) {
		   // $("#date").text(p.datestrshow);
		   $("#date").text(getDateString(p.showday,p.view));
		}
   });
   
   $("#week").click(function() {
	 	clear_3model($(this));
	 	
	    var p = $("#gridcontainer").BCalSwtichview("week").BcalGetOp();
	    if (p && p.datestrshow) {
	        //$("#date").text(p.datestrshow);
	        $("#date").text(getDateString(p.showday,p.view));
	    }
   });
   
   $("#month").click(function() {
	 	clear_3model($(this));
	    var p = $("#gridcontainer").BCalSwtichview("month").BcalGetOp();
	    if (p && p.datestrshow) {
	    	//alert(p.datestrshow);
	       // $("#date").text(p.datestrshow);
	       $("#date").text(getDateString(p.showday,p.view));
	    }
   });
   
   $("#refresh").click(function() {
     	$("#gridcontainer").BCalReload();
   });
   
   $("#date-prev").click(function(e) {
        var p = $("#gridcontainer").BCalPrev().BcalGetOp();
        if (p && p.datestrshow) {
           // $("#date").text(p.datestrshow);
           $("#date").text(getDateString(p.showday,p.view));
        }
   });
    
   $("#date-next").click(function(e) {
        var p = $("#gridcontainer").BCalNext().BcalGetOp();
        if (p && p.datestrshow) {
            //$("#date").text(p.datestrshow);
            $("#date").text(getDateString(p.showday,p.view));
        }
    });
   
   //right menu
 
   
   //sign out
   $("#sign_out").click(function() {
    	if(window.confirm("确定要退出吗?")){
    		CloseOpen();
    	}
   });
      
	 $.ajaxSetup({
			contentType: "application/x-www-form-urlencoded;charset=UTF-8"
	 });
 
     var $dv = $("#calhead");
     var _MH = document.documentElement.clientHeight;
     var dvH = $dv.height() + 2;
     op.height = _MH - dvH;
     
     var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
     var date = new Date();
     $("#date").text(date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日");

     //初始话日期选择器
     $("#hdtxtshow").datepicker({ 
	     		picker: "#date", 
	     		showtarget: $("#date"),
	    		    onReturn:function(r){                         
	                     var p = $("#gridcontainer").BCalGoToday(r).BcalGetOp();
	                     if (p && p.datestrshow) {
	                         $("#date").text(p.datestrshow);
	                     }
	             } 
	     });      
	});

</script> 

</head>
<body>

<div id="calhead" style="top:0px;padding:0;margin:0">  
	
	<div id="header">
	<div id="head_logo"></div>
    <div id="head_middle">
    	<div id="head_middle_menu">
        	<a href="javascript:;" id="today" class="head_bbline head_middle_menu_left" style="margin-left:5px">今天</a>
            <a href="javascript:;" id="day" class="head_3model">日</a>
            <a href="javascript:;" id="week" class="head_3model_active" style="color:#3B6EAF">周</a>
            <a href="javascript:;" id="month" class="head_3model">月</a>
            <a href="javascript:;" id="date-prev" class="arial">&laquo;</a><input type="hidden" id="hdtxtshow"/><a href="javascript:;" id="date" class="head_bbline">2010年12月12日</a><a id="date-next" href="javascript:;" class="arial">&raquo;</a>
            <a href="javascript:;" id="refresh" class="head_refresh"></a>
        </div>
    </div>
    <div id="head_right">
    	<div class="layout"><a href="javascript:;" id="sign_out" class="head_right_exit_btn"></a></div>
    	<div id="head_right_menu">
        
        	<div class="head-right-menu-right"></div>
            <div class="head-right-menu-middle">
            	<div id="head-right-menu-middle-container">
                	
                    
                    
                    
                </div>
            </div>
            <div class="head-right-menu-left"></div>
            
        	
        </div>
        
    </div>
    
</div>          
</div>
   
   
   
<div style="padding:1px;" id="tab1" class="tab">
     <div class="t1 chromeColor">&nbsp;</div>
     
     <div class="t2 chromeColor">&nbsp;</div>
     
     <div id="dvCalMain" class="calmain printborder">
         <div id="gridcontainer" style="overflow-y: visible;">
         </div>
     </div>
     
     <div class="t2 chromeColor">&nbsp;</div>
     
     <div class="t1 chromeColor">&nbsp;</div>   
 </div>
 
 
<%
	List<String> power = (List<String>)request.getAttribute("power");
	int size = power.size();
	String s ="";
	for(int i=0;i<size;i++){
	
	s = (String)power.get(i);
%>
<jsp:include page="<%=s %>"></jsp:include>
<%
}
%>

<iframe style="display:none;" src="logined/loginState.jsp"></iframe>


</body>
</html>