$(function() {	   
	//实例化日历组件	   
	$("#datepicker").datepicker({
		changeMonth: true,
		changeYear: true,
		showButtonPanel:true,
		showOtherMonths:true,
		onSelect:function(selectedDate){
			Index.selectDay(selectedDate);
		},
		onChangeMonthYear:function(year, month, inst){
			Index.selectMonth(year, month, inst);
		}
	});
	
	//实例化tabs
	$("#maintabs").tabs({
		cache:true,
		ajaxOption:{},
		selected:2
	});
	
	//实例化 help dialog
	$("#help_dialog").dialog({
		position:"center",
		resizable:false,
		bgiframe: true,
		modal: true,
		autoOpen:false,
		buttons: {
			"OK": function() {
				$(this).dialog('close');
			}
		}
	});
	//实例化user dialog
	$("#user_dialog").dialog({
		resizable:false,
		bgiframe: true,
		modal: true,
		autoOpen:false,
		buttons: {
			"OK": function() {
				$(this).dialog('close');
			}
		}
	});
	
	//退出提醒dialog
	$("#out_dialog").dialog({
		resizable:false,
		bgiframe: true,
		modal: true,
		autoOpen:false,
		buttons: {
			"取消": function() {
				$(this).dialog('close');
			},
			"确定": function() {
				$.ajax({
				   type: "get",
				   url: "exit.htm",
				   success: function(msg){
				     $("#out_dialog").dialog('close');
				     window.location = 'login.html';
				   }
				});
			}
		}
	});
	
	//添加预约dialog
	$("#add_order_dialog").dialog({
		draggable:true,
		resizable:false,
		bgiframe: true,
		modal: true,
		width:500,
		autoOpen:false,
		buttons: {
			"取消": function() {
				$(this).dialog('close');
			},
			"发送": function() {
				var url = 'addReservation.do';
				var data = $('form[id="sendInvitation"]').serialize();
				
				$.post(url, data, function() {
					$("#maintabs").tabs('load',0); //刷新一下
					/*更新预约信息*/
				});		
				$('.customerLine :text').val(''); //清空输入框					
				$(this).dialog('close');				
			}
		}
	});
	
	//显示预约dialog
	$("#select_order_dialog").dialog({
		draggable:true,
		resizable:false,
		bgiframe: true,
		modal: true,
		width:500,
		autoOpen:false,
		buttons: {
			"确定": function() {
				$(this).dialog('close');
			}
		}
	});
	
	//上传dialog
	$("#upload_flie_dialog").dialog({

		resizable:false,
		bgiframe: true,
		autoOpen:false,
		buttons: {
			"取消": function() {
				$(this).dialog('close');
			}
		}
	});
	

	
});



Index = {
	userId:null,
	groupId:null,
	showHelpDialog:function(){
		$("#help_dialog").dialog('open');
	//	$("#maintabs").tabs('add','tab.html','设置',6);
	//	$("#maintabs").tabs('select',2);

	},
	showUserDialog:function(){
		$("#user_dialog").dialog('open');
	},
	showOutDialog:function(){
		$("#out_dialog").dialog('open');
	},
	selectDay:function(time){
		$("#maintabs").tabs('select',0);
		$('#maintabs').tabs('option', 'ajaxOptions', {
			data:{
				date:time
			},
			success:function(){
				var current = time || '';	 
				Next.getInvitationDays(time);
		//		$('#maintabs').tabs('option', 'cache',true);
		//		$('#maintabs').tabs('option', 'ajaxOptions',{
		//			data:{}
		//		});
			}
		});
		$("#maintabs").tabs('load',0);
	//	$('#maintabs').tabs('option', 'cache',false);
	//	$('#maintabs').tabs('option', 'deselectable',true);
		
	},
	selectMonth:function(y, m, inst){
		$("#maintabs").tabs('select',2);
		$('#maintabs').tabs('option', 'ajaxOptions', {
			data:{
				date:y+"-"+m+"-1"
			},
			success:function(){
		//		$('#maintabs').tabs('option', 'cache',true);
		//		$('#maintabs').tabs('option', 'ajaxOptions',{
		//			data:{}
		//		});
				 view.setCurrMonth(y,m-1);
			}
		});
			$("#maintabs").tabs('load',2);
	},
	selectWeek:function(date){
		//do ajax
		
		//end
		$("#maintabs").tabs('select',1);
	},
	showSingleDialog:function(e){		
		var id = e;
		$.getJSON('info.do?invitation&invitationId='+id, function(json) {
			Info.invitation = json;
			$('#select_order_dialog #showInvitationTime').text(json[0].time[0]);
			$('#select_order_dialog #showInvitationTitle').text(json[0].title[0]);
			$('#select_order_dialog #showGuider').text(json[0].guider[0] == ''?'':json[0].guider[0].name[0]);
			$('#select_order_dialog #showTechnician').text(json[0].technician[0] == ''?'':json[0].technician[0].name[0]);
			
			var list = json[0].customers[0]; //客户列表
			var count = list.length; //客户的数量
			var maxCountInLine = 4; //一行上的最大人数
			$('#customerListTable .customerListLine').children().each(function(){
				//复位列表
				$(this).children('a').text('');
				$(this).children('img').hide();
			});
			if(count <= maxCountInLine) { //人数少于maxCountInLine
				for(var index=0; index<count; index++) {
					$('#customerListTable .customerListLine').children().eq(index).children('a').text(list[index].name[0]);
					$('#customerListTable .customerListLine').children().eq(index).children('img').attr('src', 'img/status'+list[index].sendStatus[0]+'.png').show();
				}
				for(var index=count; index<maxCountInLine; index++) {
					$('#customerListTable .customerListLine').children().eq(index).children('img').hide();
				}
			} else { //人数多于maxCountInLine
				var lineCount = count/maxCountInLine; //需要增加的行数
				
			}
		});
		
		$("#select_order_dialog").dialog('open');
	},
	addSingleReservation:function(el){
	//	alert("添加一件预约，添加的时间为："+el.id);	
		$('.customerList').insertAfter($('#sendInvitation .stub'));	
		var visitTime = $('#fastInvitation #visitTime-fast').attr('value');
		$('#sendInvitation #visitTime').attr('value', visitTime);
		$("#add_order_dialog").dialog('open');
	},
	showUploadFileDialog:function(){
		$('#uploadStatus').text('');
		$("#upload_flie_dialog").dialog('open');
	},
	
	deleteUserDialog:function(id){
		this.userId = id;
		$("#delete_warning_dialog").dialog('open');
	},
	addMmsTempleteDialog:function(){
	//	var form = $("#addMmsTempleteForm")
	//	$("#addMmsTempleteForm").attr("value",'');
		$("#mmsTempleteName").attr("value",'');
		$("#mmsContent").attr("value",'');
		$("#defalutFlag").attr("selected",false);
		
		
		$("#add_mms_templete_dialog").dialog('open');
	},
	deleteMmsTempleteDialog:function(id){
		$("#delete_mms_templete_dialog").dialog('open');
	},
	updateMmsTempleteDialog:function(id){
		$("#update_mms_templete_dialog").dialog('open');
	}
	
};
/****************************************************************/
/**********************      退出系统       ***********************/
/****************************************************************/
function exit(){
	$.ajax({
	   type: "POST",
	   url: "exit.htm"
	});
}


/****************************************************************/
/**********************      月列表生成          ***********************/
/****************************************************************/
El = {
	start:0,
	end:0,
	add:function(t){
		var textBox = document.getElementById("mmsContent");
        var pre = textBox.value.substr(0, this.start);
        var post = textBox.value.substr(this.end);
        textBox.value = pre + t.name + post;

		var textLength = document.getElementById("mmsContent").value.length;
		this.start = textLength;
		this.end = textLength;
	},
	savePos:function(textBox){
		 //如果是Firefox(1.5)的话，方法很简单
        if(typeof(textBox.selectionStart) == "number"){
            this.start = textBox.selectionStart;
            this.end = textBox.selectionEnd;
        }
        //下面是IE(6.0)的方法，麻烦得很，还要计算上'\n'
        else if(document.selection){
            var range = document.selection.createRange();
            if(range.parentElement().id == textBox.id){
                // create a selection of the whole textarea
                var range_all = document.body.createTextRange();
                range_all.moveToElementText(textBox);
                //两个range，一个是已经选择的text(range)，一个是整个textarea(range_all)
                //range_all.compareEndPoints()比较两个端点，如果range_all比range更往左(further to the left)，则                //返回小于0的值，则range_all往右移一点，直到两个range的start相同。
                // calculate selection start point by moving beginning of range_all to beginning of range
                for (this.start=0; range_all.compareEndPoints("StartToStart", range) < 0; start++)
                    range_all.moveStart('character', 1);
                // get number of line breaks from textarea start to selection start and add them to start
                // 计算一下\n
                for (var i = 0; i <= start; i ++){
                    if (textBox.value.charAt(i) == '\n')
                        this.start++;
                }
                // create a selection of the whole textarea
                 var range_all = document.body.createTextRange();
                 range_all.moveToElementText(textBox);
                 // calculate selection end point by moving beginning of range_all to end of range
                 for (end = 0; range_all.compareEndPoints('StartToEnd', range) < 0; this.end ++)
                     range_all.moveStart('character', 1);
                     // get number of line breaks from textarea start to selection end and add them to end
                     for (var i = 0; i <= this.end; i ++){
                         if (textBox.value.charAt(i) == '\n')
                             this.end ++;
                     }
                }
            }
	}
};


var Calendar = {
model:function(){} ,
controller:function(){} ,
view:function(){}
}; 
//视图层
Calendar.view = function(){
this.currDate = new Date();
this.tds = null;
this.days = null;
this.freeDays = null;
this.backNode = null;
this.disableDays = new Array();
};
//根据年份返回每月天数
Calendar.view.getMonthDays = function(year){
var feb = (year % 4 == 0)? 29:28; 
return new Array(31, feb , 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
};
//初始化
Calendar.view.prototype.init = function(){
this.setCurrMonth(new Date().getFullYear() ,new Date().getMonth());
this.addEventForTd();
};
//为单元格注册事件
Calendar.view.prototype.addEventForTd = function(){

	for (var i=0;i<this.tds.length ;i++ )
	{
		this.tds[i].onclick = function(){
		   var arr = this.getAttribute("dateValue").split("-");
		  
		   var time = arr[0] +"-"+ (parseInt(arr[1])+1) +"-"+ arr[2];
		   
		};
	}
		
	$(this.freeDays).bind('onclick',
		function() {
			alert($(this).parent().children('.dayStyle').text());
		}		
	);
};
//设定当前版面
Calendar.view.prototype.setCurrMonth = function(y ,m){
this.currDate.setFullYear(y);
this.currDate.setMonth(m);
this.currDate.setDate(1);
this.loadDaysByMonth(y ,m);
};
//标示当前天
Calendar.view.prototype.markCurrDate = function(bDay ,eDay){
var temp = new Date();
if (this.currDate.getFullYear() == temp.getFullYear() 
      && this.currDate.getMonth() == temp.getMonth())
{
for (var i=bDay;i<eDay ;i++ )
{
   if (this.tds[i].getAttribute("dateValue").split("-")[2] == temp.getDate())
   {
    if (this.backNode)
    {
     this.backNode.className = "";
    }
    this.tds[i].className = "currDay";
    this.backNode = this.tds[i];
    return false;
   }
}
}
};
//复位版面状态
Calendar.view.prototype.reInState = function(){
this.tds[35].parentNode.style.display = "none";
if (this.backNode)
{
this.backNode.className = "";
}
for (var i=0;i<this.disableDays.length ;i++ )
{
this.disableDays[i].className = "dayStyle";
}
this.disableDays.length = 0;
};
//根据年月加载当前视图
Calendar.view.prototype.loadDaysByMonth = function(y ,m){
y = parseInt(y) ,m = parseInt(m);
this.reInState(); //复位版面状态
//参数定位
var beginDay = this.currDate.getDay();
var _m = (m == 0)?11 : (m-1);
var m_ = (m == 11)?0 : (m+1);
var _y = (m == 0)?(y-1) : y;
var y_ = (m == 11)?(y+1) : y;
var prevMonthDays = Calendar.view.getMonthDays(_y)[_m];
var currMonthDays = Calendar.view.getMonthDays(y)[m];
var prevFlag = prevMonthDays - beginDay + 1 ,currFlag = 1 ,nextFlag = 1;
//加载上月信息
for (var i=0;i<beginDay ;i++ )
{
this.tds[i].setAttribute("dateValue" ,_y +"-"+ _m +"-"+ prevFlag);
//this.days[i].innerHTML = prevFlag;
this.days[i].innerHTML = (_y +"-"+ (_m+1) +"-"+ currFlag);
this.days[i].className = "dayStyle disableText";
this.disableDays.push(this.days[i]);
prevFlag++;
}
//加载当月信息
for (var i=beginDay;i<currMonthDays+beginDay ;i++ )
{
this.tds[i].setAttribute("dateValue" ,y +"-"+ m +"-"+ currFlag);
//this.days[i].innerHTML = currFlag;
this.days[i].innerHTML = (y +"-"+ (m+1) +"-"+ currFlag);
currFlag++;
}
//加载下月信息
for (var i=currMonthDays+beginDay;i<this.days.length ;i++ )
{
this.tds[i].setAttribute("dateValue" ,y_ +"-"+ m_ +"-"+ nextFlag);
//this.days[i].innerHTML = nextFlag;
this.days[i].innerHTML = (y_ +"-"+ (m_+1) +"-"+ currFlag);
this.days[i].className = "dayStyle disableText";
this.disableDays.push(this.days[i]);
nextFlag++;
}
//若当月数据显示到第7行，那么显示它
if (this.tds[35].getAttribute("dateValue"))
{
if (this.tds[35].getAttribute("dateValue").split("-")[2] > 20)
{
   this.tds[35].parentNode.style.display = "";
}
}
//标示当前天
this.markCurrDate(beginDay ,currMonthDays+beginDay);
};






/****************************************************************/
/**********************      分页相关       ***********************/
/****************************************************************/
Page = {
	//下一页
	pageDown:function(power){
		var maxCount = parseInt((power.allPage-1)/power.pageCount+"");
		if( power.page >= maxCount){
			alert("已经是最后一页了！");
		}else{
			$("#"+power.id).html("正在加载请稍后....");
			$.post(power.url,power.data+"&page="+(power.page+1),function(html){
				$("#"+power.id).html(html);
				power.page = power.page+1;
				$("#"+power.id+"Page").html(power.page+1);
			});
		}
	},
	//上一页
	pageUp:function(power){
		if(power.page == 0){
			alert("已经是第一页了！");
		}else{
			$("#"+power.id).html("正在加载请稍后....");
			$.post(power.url,power.data+"&page="+(power.page-1),function(html){
				$("#"+power.id).html(html);
				power.page = power.page-1;
				$("#"+power.id+"Page").html(power.page+1);
			});
		}
	},
	//搜索
	search:function(power){
		$("#"+power.id).html("正在加载请稍后....");
		power.data = $("form[id='"+power.id+"SearchForm']").serialize();
		$.post(power.url,power.data+"&method=1",function(html){
			$("#"+power.id).html(html);
			power.page = 0;
			
			power.pageCount = document.getElementById(power.id+"SearchForm").pageCount.value;
			//	alert(power.pageCount);
		});
	}
};


				$.post(url, data, function() {
					$("#maintabs").tabs('load',0); //刷新一下
					/*更新预约信息*/
				});		
