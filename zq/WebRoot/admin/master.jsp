<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="listener.RemoteControl,java.util.ArrayList,bean.ClientBean;"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%	
	Object o = getServletContext().getAttribute("deviceInfo");	
	if(o == null) {
		RemoteControl god = new RemoteControl();
		getServletContext().setAttribute("deviceInfo", god.check());
	} 
	ArrayList<ClientBean> list = 
		(ArrayList<ClientBean>) getServletContext().getAttribute("deviceInfo");
	int size = list.size();
	int index = 0;
 %>
<html>
  <head>
    <title>全场控制-政企体验馆</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
	<link rel="shortcut icon" href="../favicon.ico" />
	<link rel="stylesheet" type="text/css" href="../css/master.css">
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>	
  </head>
  
  <body>
  	<hr style='margin-top:-8px;'/>	
  	<div class='toolBar'>
  		<span class='filterGroup'>
	  		<input type='button' id='every' value='全选'/>
	  		<input type='button' id='none' value='全不选'/>
	  		<input type='button' id='reverse' value='反选'/>
	  		<input type='button' id='allAlive' value='所有在线'/>
  		</span>
  		<span class='controlGroup'>
	  		<input type='button' id='shutdown' value='关机'/>
	  		<input type='button' id='reboot' value='重启'/>
	  		<input type='button' id='logoff' value='注销'/>
	  		<input type='button' id='check' value='检测'/>
	  		<input type='button' id='checkAll' value='重新搜索'/>
  		</span>
  	</div>
 	<table class='deviceTable' cellspacing='0' cellpadding='2' border='1' style='float:left;'>
 		<tr>
 			<th class='checkCol'></th>
 			<th class='deviceName'>描述</th>
 			<th class='name'>机器名</th>
 			<th class='ipAddress'>IP地址</th>
 		</tr>
 		<% 
 			for(int i=0; i<=(size-1)/2; i++) {
 				ClientBean e = list.get(i);
 		%>
 		<tr class='device <%=e.getStatus()==0?"grayColor":""%>' style='color:<%=e.getStatus()==0?"#d11":"green"%>'>
 			<td><input type='checkbox' class='checkBox'/>
 				<img class='state hide'></img></td>
 			<td><%=e.getSubject() %></td>
 			<td><%=e.getName() %></td>
 			<td class='ip'><%=e.getIp() %></td>
 		</tr>
 		<%
 			} 
 		%>
 	</table>
 	<table class='deviceTable' cellspacing='0' cellpadding='2' border='1' style='float:right;'>
 		<tr>
 			<th class='checkCol'></th>
 			<th class='deviceName'>描述</th>
 			<th class='name'>机器名</th>
 			<th class='ipAddress'>IP地址</th>
 		</tr>
 		<% 
 			for(int i=(size-1)/2+1; i<size; i++) {
 				ClientBean e = list.get(i);				
 		%>
 		<tr class='device <%=e.getStatus()==0?"grayColor":""%>' style='color:<%=e.getStatus()==0?"#d11":"green"%>'>
 			<td><input type='checkbox' class='checkBox'/>
 				<img class='state hide'></img></td>
 			<td><%=e.getSubject() %></td>
 			<td><%=e.getName() %></td>
 			<td class='ip'><%=e.getIp() %></td>
 		</tr>
 		<%
 			} 
 			if(size%2 == 1) {
 		%>
 			<tr disabled>
 				<td>&nbsp;</td>
 				<td>&nbsp;</td>
 				<td>&nbsp;</td>
 				<td>&nbsp;</td>
 			</tr>
 		<%
 			}
 		%>
 	</table> 	
  	
  	<script type='text/javascript'>
  	(function(){
  		var url = '/zq/rc';  		
  		var status = [];
  		var x;
  		
	  	$(document).ready(function() {
	  		if(!allowVisit()) void(0);
	  		heightAdapter();
	  		ajaxInit();
	  		changeTitle();
	  		buttonEffect();
	  		bindCommand();
	  		bindCheckAll();	 
	  		bindFilter();
	  	});	  	
	  	
	  	function changeTitle() {
	  		parent.document.title = document.title;
	  	}
	  		  	
	  	//恢复正常
  		function recover(){  				
  			if(status.length == $('.device').length) {
  				clearInterval(x);
  				$('#checkAll').attr('value', '重新搜索').removeAttr('disabled').css('background', '#fff');
  			}
  		}
	  	
	  	//检测中
	  	function detecting() {	
	  		$('#checkAll').attr('value', '搜索中...').attr('disabled', 'disabled');  		
	  		x = setInterval(recover, 1000);
	  	}
	  	
	  	//按钮的效果
	  	function buttonEffect() {
	  		$('.filterGroup input, .controlGroup input'
	  			).css('cursor', 'pointer'
	  			).bind('mouseover', function(){
					$(this).css('background', '#bdd0ef');
				}).bind('mouseout', function(){
					$(this).css('background', '#fff').blur();
				});
	  	}
	  	
	  	//左边的选择按钮组事件
	  	function bindFilter() {
	  		$('#reverse').bind('click', reverse); 
	  		$('#every').bind('click', selectAll);
	  		$('#none').bind('click', selectNone);
	  		$('#allAlive').bind('click', selectAllAlive);
	  	}
	  	
	  	//检测所有机器状态
	  	function bindCheckAll() {
	  		$('#checkAll').bind('click', CheckAllStatus);
	  	}
	  	
	  	//全选
	  	function selectAll() {
	  		/*$('.device input:checkbox').filter(function(){
	  			return !$(this).parents('.device').attr('disabled');
	  		}).attr('checked', true);*/
	  		$('.device input:checkbox').attr('checked', true);
	  	}
	  	
	  	//全选在线机器
	  	function selectAllAlive() {
	  		$('.device input:checkbox').filter(function(){
	  		//选中在线的
	  			return !$(this).parents('.device').hasClass('grayColor');
	  		}).attr('checked', true).end().filter(function(){
	  		//取消不在线的
	  			return $(this).parents('.device').hasClass('grayColor');
	  		}).attr('checked', false);
	  	}
	  	
	  	//全不选
	  	function selectNone() {
	  		$('.device input:checkbox').attr('checked', false);
	  	}
	  	
	  	//反选
	  	function reverse() {
	  		$('.device input:checkbox').each(function(){
	  			$(this).attr('checked', !$(this).attr('checked'));
	  		});
	  	}

	  	/*限制直接访问*/
		function allowVisit() {
			if(parent.document.getElementById('page') == null) {
				document.body.innerHTML = '禁止直接访问';
				return false;
			}
			return true;
		}
		
		/*高度自适应*/
		function heightAdapter() {
			try{
				var height = document.body.scrollHeight;
				parent.document.getElementById('page').style.height = height;
			} catch(err) {}
		}
		
		//绑定命令按钮组
		function bindCommand() {
			bind('shutdown');
			bind('reboot');
			bind('logoff');
			bind('check');
		}
		
		//绑定命令
		function bind(command) {
			$('#'+ command).bind('click', function(){
				if(command != 'check' && !confirm("确定执行吗？")) {
					return;
				}
				$('.device').filter(function(){	
					return $(':checkbox', this).attr('checked');
					//return $(this).children(':first-child').children().eq(0).attr('checked');
				}).each(function(){	
					execute($(this), command, $(this).children().eq(3).text());
				});
			});
		}
		
		//对IP执行命令
		function execute(device, command, ip) {
			var line = device.children().eq(0);
			line.children('.checkBox').hide();
			line.children('.state').attr('src', '../img/loading.gif').show();
			
			$.post(url, {
				command: command,
				ip: ip
			}, function(msg){
				if(/^###true###$/.test(msg)) {
					//device.removeAttr('disabled', 'disabled');
					device.removeClass('grayColor');
					device.css('color', 'green');
				} else {
					//device.attr('disabled', 'disabled');
					//取消选中状态
					device.addClass('grayColor');
					device.css('color', '#d11');					
					line.children('.checkBox').attr('checked', false);
				}
				line.children('.state').hide();
				line.children('.checkBox').show();
				line = null;
			});
		}
		
		//重新检测所有机器状态
		function CheckAllStatus(){
			detecting();
			$('.ip').each(function(){
				var obj = $(this);
				var line = obj.parent('.device').children().eq(0);
				line.children('.checkBox').hide();
				line.children('.state').attr('src', '../img/loading.gif').show();
				
				$.post(url, {
					command: 'check',
					ip: $(this).text()
				},function(msg) {
					if(/^###true###$/.test(msg)) {	
						status.push(1);	
						//obj.parent('.device').children().eq(0).children('.state').attr('src', '../img/run.gif');
						//if(obj.parent('.device').hasClass('grayColor')) {
						obj.parent('.device').removeClass('grayColor');	
						obj.parent('.device').css('color', 'green');
						//}
						//obj.parent().removeAttr('disabled');
					} else {
						status.push(0);
						//取消选中状态
						//obj.parent('.device').children().eq(0).children('.state').attr('src', '../img/error.gif');
						obj.parent('.device').addClass('grayColor');
						obj.parent('.device').css('color', '#d11');
						//obj.parent().attr('disabled', 'disabled').children().children().attr('checked', false);						
					}
					line.children('.state').hide();
					line.children('.checkBox').attr('checked', false).show();
					line = null;
				})
			});
		}
		
		//ajax初始化
	   	function ajaxInit() {
	   		$.ajaxSetup({
	   			url:url,
	   			type:'post',
	   			cache:false,
	   			async:true
	   		})
	   	}
	})()
  	</script>
  </body>
</html>
