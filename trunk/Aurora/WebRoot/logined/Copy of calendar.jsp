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

<script src="Javascripts/Plugins/jquery.calendar.js" type="text/javascript"></script>   
<script src="Javascripts/Plugins/jquery.ajaxselect.js" type="text/javascript"></script>
 
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
	

	
	function clear_3model(el) {
		$("#day").removeClass("head_3model_active");
		$("#day").addClass("head_3model");
		$("#week").removeClass("head_3model_active");
		$("#week").addClass("head_3model");
		$("#month").removeClass("head_3model_active");
		$("#month").addClass("head_3model");
		el.addClass("head_3model_active");
	}
	function right_menu_choosed(el) {
		$("#head_right_menu").find("a").each(function() {
			//$(this).removeClass("right_menu_choosed");
			$(this).stop().animate({"color":"rgb(63,114,177)"}, {"duration":"500"});
		});
		el.stop().animate({"color":"rgb(256,256,256)"}, {"duration":"500"});
	}
	
   $("#today").click(function() {
     	var p = $("#gridcontainer").BCalGoToday().BcalGetOp();
	    if (p && p.datestrshow) {
	        $("#date").text(p.datestrshow);
	    }
   });
   
   $("#day").click(function() {
		clear_3model($(this));
		//初始话当前面板
		var p = $("#gridcontainer").BCalSwtichview("day").BcalGetOp();
		//设置日期选择器的值
		if (p && p.datestrshow) {
		    $("#date").text(p.datestrshow);
		}
   });
   
   $("#week").click(function() {
	 	clear_3model($(this));
	 	
	    var p = $("#gridcontainer").BCalSwtichview("week").BcalGetOp();
	    if (p && p.datestrshow) {
	        $("#date").text(p.datestrshow);
	    }
   });
   
   $("#month").click(function() {
	 	clear_3model($(this));
	    var p = $("#gridcontainer").BCalSwtichview("month").BcalGetOp();
	    if (p && p.datestrshow) {
	        $("#date").text(p.datestrshow);
	    }
   });
   
   $("#refresh").click(function() {
     	$("#gridcontainer").BCalReload();
   });
   
   $("#date-prev").click(function(e) {
        var p = $("#gridcontainer").BCalPrev().BcalGetOp();
        if (p && p.datestrshow) {
            $("#date").text(p.datestrshow);
        }
   });
    
   $("#date-next").click(function(e) {
        var p = $("#gridcontainer").BCalNext().BcalGetOp();
        if (p && p.datestrshow) {
            $("#date").text(p.datestrshow);
        }
    });
   
   //right menu
  
   $('.request-top-exit').click(function() {
   		$("#request-dialog").hide();
   });
   $('.request-bottom-button').click(function(){
   		$("#request-dialog").hide();
   });
   
   $("#request").click(function() {
	$("#head-right-menu-middle-container").stop().animate(
		{"backgroundPosition":"0px 4px"},
		{"duration":"500"});
	right_menu_choosed($(this));
	$("#request-dialog").show();
  });


 $("#reservation").click(function() {
	$("#head-right-menu-middle-container").stop().animate(
		{"backgroundPosition":"72px 4px"},
		{"duration":"500"});
	right_menu_choosed($(this));
  });
  
  $("#feedback").click(function() {
	$("#head-right-menu-middle-container").stop().animate(
		{"backgroundPosition":"146px 4px"},
		{"duration":"500"});
	right_menu_choosed($(this));
  });
  
  $("#setup").click(function() {
	$("#head-right-menu-middle-container").stop().animate(
		{"backgroundPosition":"220px 4px"},
		{"duration":"500"});
	right_menu_choosed($(this));
	$(".add-continer").hide();
	$("#setup-dialog").show();
	
	$("#setup-add-top-exit").click(function(){
		$("#setup-dialog").hide();
	});
	
	$("#setup-button-exit").click(function(){
		$("#setup-dialog").hide();
	});
  });
   
   $("#showOK").click(function(){
   		$("#show-calendar").hide();
   });
           
   $.ajaxSetup({
		contentType: "application/x-www-form-urlencoded;charset=UTF-8"
   });

    var op = {
        view: "week",
        theme:3,
        showday: new Date(),
        EditCmdhandler:function(data){	
       		
        },
        DeleteCmdhandler:function(data){
        
        },
        ViewCmdhandler:function(data){
        	 
        },    
        onWeekOrMonthToDay:function(p){
        	if (p && p.datestrshow) {
	             $("#txtdatetimeshow").text(p.datestrshow);
	        }
	        $("#caltoolbar div.fcurrent").each(function() {
	             $(this).removeClass("fcurrent");
	        })
	        $("#showdaybtn").addClass("fcurrent");
        },
        onBeforeRequestData: function(type){
        	var t="正在加载数据...";
         switch(type)
         {
             case 1:
                 t="正在加载数据...";
                 break;
             case 2:                      
             case 3:  
             case 4:    
                 t="正在处理请求...";                                   
                 break;
         }
         $("#errorpannel").hide();
         $("#loadingpannel").html(t).show();  
        },
        onAfterRequestData: function(type){
        	switch(type)
         {
             case 1:
                 $("#loadingpannel").hide();
                 break;
             case 2:
             case 3:
             case 4:
                 $("#loadingpannel").html("操作成功!");
                 window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
             break;
         }    
        },
        onRequestDataError: function(type,date){
        	  alert("错误代号："+type);
        	  $("#errorpannel").show();
        }, 
        url: "items.htm?show" ,  
        quickAddUrl: "items.htm?add", //快速添加日程Post Url 地址
        quickUpdateUrl:"items.htm?update",
        quickDeleteUrl: "items.htm?delete"//快速删除日程的              
    };
         
         
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
        	<a href="javascript:;" id="today" class="head_bbline head_middle_menu_left">今天</a>
            <a href="javascript:;" id="day" class="head_3model">日</a>
            <a href="javascript:;" id="week" class="head_3model_active">周</a>
            <a href="javascript:;" id="month" class="head_3model">月</a>
            <a href="javascript:;" id="date-prev" class="arial">&laquo;</a><input type="hidden" id="hdtxtshow"/><a href="#" id="date" class="head_bbline">2010年12月12日</a><a id="date-next" href="#" class="arial">&raquo;</a>
            <a href="javascript:;" id="refresh" class="head_refresh"></a>
        </div>
    </div>
    <div id="head_right">
    	<div class="layout"><a href="javascript:;" id="sign_out" class="head_right_exit_btn"></a></div>
    	<div id="head_right_menu">
        
        	<div class="head-right-menu-right"></div>
            <div class="head-right-menu-middle">
            	<div id="head-right-menu-middle-container">
                	<a href="javascript:;" id="request">预约请求</a>
                    <a href="javascript:;" id="reservation">查看日程</a>
                    <a href="javascript:;" id="feedback">客户反馈</a>
                    <a href="javascript:;" id="setup">系统设置</a>
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
 
 

<jsp:include page="add-calendar.jsp"></jsp:include>
<jsp:include page="show-calendar.jsp"></jsp:include>
<jsp:include page="setup.jsp"></jsp:include>
<jsp:include page="request.jsp"></jsp:include>
  
</body>
</html>