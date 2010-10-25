<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="data.Database,bean.RegionBean,bean.ClientBean,java.util.ArrayList"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Database db = new Database();
	ArrayList<RegionBean> regions = db.getRegions();	
	ArrayList<ClientBean> clients = db.getClients();
%>
<html>
  <head>
    <title>参数设置-政企体验馆</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="设置页面">
	
	<link rel="shortcut icon" href="../favicon.ico" />
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/set.css">
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="../js/set.js"></script>
  </head>
  
  <body>
  	<hr style='margin-top:-8px;'/>
  	<div class='body'> 
  	 	<fieldset id='mail'>
  	 		<legend>邮件发送设置</legend>
  	 		<div>
  	 			<input type='checkbox' id='changeFrom'/>更改发件邮箱
  	 			<input type='checkbox' id='sendOption'/>更改发送设置
  	 			<div>
  	 				<span>SMTP服务器：</span> <input type='text' id='smtp' disabled/>
  	 			</div>
  	 			<div>
  	 				<span>发件邮箱：</span> <input type='text' id='from' disabled/>
  	 			</div>
  	 			<div>
  	 				<span>邮箱密码：</span> <input type='password' id='pwd' disabled/>
  	 			</div>
  	 			<div>
  	 				<span>确认密码：</span> <input type='password' id='repwd' disabled/>
  	 			</div>
  	 			<div>
  	 				<span>收件邮箱：</span> <input type='text' id='to' disabled/>
  	 			</div>
  	 			<div>
  	 				<span>是否发送：</span> <input type='checkbox' id='isSend' disabled/>
  	 				<span style='width:130px'></span>
  	 			</div>
  	 			<div>
  	 				<span>发送间隔：</span> <input type='text' id='cyc' disabled/><span class='suffix'>天</span>
  	 			</div>
  	 			<div>  	 				
  	 				<span>发送时间：</span> 
					<select id='hour' disabled>
						<%for(int i=0;i<24;i++) {%>
						<option value='<%=i%>'><%=i %></option>
						<%}%>
					</select><span class='suffix'>点</span>
  	 			</div>
  	 			<div>
  	 				<input type='button' value='保存' id='save'/>
  	 				<input type='button' value='重置' id='reset'/>
  	 			</div>
  	 		</div>
  	 	</fieldset>
  	 	<fieldset id='sms'>
  	 		<legend>短信发送设置</legend>
  	 		<div>
  	 			<div id='region'>
  	 				<%for(int i=1;i<5;i++) {%>
  	 				<div code='<%=regions.get(i).getRegionCode()%>'><%=regions.get(i).getRegionName() %></div>
  	 				<%} %>
  	 			</div>
  	 			<div id='messageBox'>
  	 				<textarea id='message'></textarea>
  	 			</div>
  	 			<input type='button' value='保存' id='saveSms'/>
  	 		</div>
  	 		<div id='smscontent'>
  	 			<%for(int i=1;i<5;i++) {%>
  	 			<div id='c<%=i%>'><%=db.getSms(regions.get(i).getRegionCode()) %></div>
  	 			<%} %>
  	 		</div>
  	 	</fieldset>
  	 	<fieldset id='master'>
  	 		<legend>全场控制设置</legend>
  	 		<div>
  	 			<div id='eid'>
	  	 			<span>ID:</span>
	  	 			<select id='clientId'>
	  	 				<%for(ClientBean client: clients){ %>
	  	 				<option value='<%=client.getId() %>'><%=client.getId() %></option>
	  	 				<%}%>
	  	 			</select>
	  	 			<input type='text' id='newClient'/>	
	  	 			<span class='fixer'>
	  	 				<input type='button' value='新增' id='createNew'/>
	  	 				<input type='button' value='取消' id='cancelNew'/>
	  	 			</span>
  	 			</div>
  	 			<div id='ename'>
	  	 			<span>机器名:</span>
	  	 			<input type='text' id='nameText' value='<%=clients.get(0).getName() %>'/>
  	 			</div>
  	 			<div id='subject'>
	  	 			<span>描述:</span>
	  	 			<input type='text' id='subjectText' value='<%=clients.get(0).getSubject() %>'/>
  	 			</div>
  	 			<div id='ip'>
	  	 			<span>IP:</span>
	  	 			<input type='text' id='ipText' value='<%=clients.get(0).getIp() %>'/>
  	 			</div>
  	 			<div id='btn'>
  	 				<input type='button' value='删除' id='deleteClient'/>
  	 				<input type='button' value='保存' id='saveClient'/>
  	 			</div>
  	 		</div>
  	 	</fieldset>
  	</div>
  	
  	<script type="text/javascript">
  	$(function(){
  		if(!allowVisit()) void(0);
  		changeTitle();
  		heightAdapter();
  	})
  	
  	function changeTitle() {
  		parent.document.title = document.title;
  	} 
	   	
   	/*限制直接访问*/
	function allowVisit() {
		if(parent.document.getElementById('page') == null) {
			document.body.innerHTML = '禁止直接访问';
			return false;
		}
		return true;
	}
	
	/*自适应页面高度*/
   	function heightAdapter() {
   		try{
		var height = document.body.scrollHeight;
		parent.document.getElementById('page').style.height = height;
		} catch(err) {}
	}
  	</script>
  	<script type='text/javascript'>
  	var json;
  	$(document).ready(function() {
  		ajaxInit();
  		getOption();
  		$('#changeFrom').bind('click', changeFrom);
  		$('#sendOption').bind('click', changeSend);
  		$('#save').bind('click', save);
  		$('#reset').bind('click', reset);
  	});
  	
  	function getOption() {
  		$.getJSON('/zq/door', 'type=read', function(msg) {
  			json = msg;
  			fillValue();
  		});
  	}
  	
  	function reset() {
  		fillValue();
  	}
  	
  	/*是否合法*/
  	function check() {
  		var flag = true;
  		if($('#changeFrom').attr('checked')) {
  			if(/^smtp.\w+.\w+$/.test($('#smtp').attr('value'))) {
  				$('#smtp').css('border-color', '');
  			} else {
  				flag = false;
  				$('#smtp').css('border-color', 'red');
  			}
  			if(/^\w+@\w+(.\w+)+$/.test($('#from').attr('value'))) {
  				$('#from').css('border-color', '');
  			} else {
  				flag = false;
  				$('#from').css('border-color', 'red');
  			}
  			if($('#pwd').attr('value') != $('#repwd').attr('value')) {//密码不一致
  				flag = false;
  				$('#pwd, #repwd').css('border-color', 'red');
  			} else {
  				$('#pwd, #repwd').css('border-color', '');
  			}
  		}  		
		if($('#sendOption').attr('checked')) {	
  			if(/^\w+@\w+(.\w+)+$/.test($('#to').attr('value'))) {
  				$('#to').css('border-color', '');
  			} else {
  				flag = false;
  				$('#to').css('border-color', 'red');
  			}
  			if(/^\d+$/.test($('#cyc').attr('value'))) {
  				$('#cyc').css('border-color', '');
  			} else {
  				flag = false;
  				$('#cyc').css('border-color', 'red');
  			}
  		}
  		return flag;
  	}
  	
  	/*保存数据*/
  	function save() {
  		if(!check()) return;
  		if($('#changeFrom').attr('checked') || $('#sendOption').attr('checked')) {
  			var fa = $('#changeFrom').attr('checked');
  			var fb = $('#sendOption').attr('checked');
  			if(fa) { 
	  			if(!window.confirm("发件邮箱必须支持smtp，如果确认该邮箱支持smtp，点击确认，否则建议取消")) {
	  				return;
	  			}
	  		}
	  		setOption(fa, fb);
  		} else {
  			return;
  		}
  	}
  	
  	function setOption(fa, fb) {
  		var url = '/zq/door?';
  		var param = 
  		{
  			type:'set',
  			smtp:fa?$('#smtp').attr('value'):json.smtp,
  			from:fa?$('#from').attr('value'):json.from,
  			to:fb?$('#to').attr('value'):json.to,
  			pwd:fa?$('#pwd').attr('value'):json.pwd,
  			cyc:fb?$('#cyc').attr('value'):json.cyc,
  			hour:fb?$('#hour').val():json.hour,
  			flag:fb?$('#isSend').attr('checked'):json.flag
  		}
  		url = url+ $.param(param);
  		
  		$.ajax({
  			url:url,
  			complete: function() {
  				window.location.reload();
  			}
  		});
  	}
  	
  	/*填充邮件的设置*/
  	function fillValue() {
  		$('#mail #smtp').attr('value', json.smtp);
  		$('#mail #from').attr('value', json.from);
  		$('#mail #pwd').attr('value', json.pwd);
  		$('#mail #repwd').attr('value', json.pwd);
  		$('#mail #to').attr('value', json.to);
  		$('#mail #cyc').attr('value', json.cyc);
  		$('#mail #hour').val(json.hour);
  		detectSend();
  	}  	
  	
  	/*检查发送邮件的标志位*/
  	function detectSend() {
  		if(json.flag == 'true') {
  			$('#isSend').attr('checked', 'checked');
  		} else {
  			$('#isSend').removeAttr('checked');
  		}
  	}
  	  	
  	function changeFrom() {
  		if($('#changeFrom').attr('checked')) {
  			enableFrom();
  		}else {
  			disableFrom();
  		}  		
  	}
  	
  	function changeSend() {
  		if($('#sendOption').attr('checked')) {
  			enableSend();
  		}else {
  			disableSend();
  		} 
  	}
  	
  	function disableFrom() {
  		$('#mail #smtp').attr('disabled','disabled');
  		$('#mail #from').attr('disabled','disabled');
  		$('#mail #pwd').attr('disabled','disabled');
  		$('#mail #repwd').attr('disabled','disabled');
  	}
  	
  	function enableFrom() {
  		$('#mail #smtp').removeAttr('disabled');
  		$('#mail #from').removeAttr('disabled');
  		$('#mail #pwd').removeAttr('disabled');
  		$('#mail #repwd').removeAttr('disabled');
  	}
  	
  	function disableSend() {
  		$('#mail #to').attr('disabled','disabled');
  		$('#mail #isSend').attr('disabled', 'disabled');
  		$('#mail #cyc').attr('disabled','disabled');
  		$('#mail #hour').attr('disabled','disabled');
  	}
  	
  	function enableSend() {
  		$('#mail #to').removeAttr('disabled');
  		$('#mail #isSend').removeAttr('disabled');
  		$('#mail #cyc').removeAttr('disabled');
  		$('#mail #hour').removeAttr('disabled');
  	}  	
  	
  	/*初始化ajax*/
   	function ajaxInit() {   		
   		$.ajaxSetup({
   			url:'/zq/door',
   			type:'post',
   			cache:false,
   			async:false
   		})
   	}
  	</script>
  	<script type="text/javascript">
  	$(function(){
  		//ajaxInit();
  		BindClick();
  		$('#region div').eq(0).click();
  		saveSms();
  	});
  	
  	function BindClick() {
  		$('#region div').each(function(){
  			var index = $('#region div').index($(this))+ 1;
  			$(this).data('selected', false);
  			$(this).click(function(){
  				$('#region div').css('background', '#4274BA').data('selected', false);
  				$(this).css('background', '#7DA1DB').data('selected', true);  				
  				$('#message').text($('#smscontent #c'+index).text());
  			});
  		});
  	} 
  	
  	function saveSms() {
  		$('#saveSms').click(function(){
  			var sms = $('#message').text(); 
  			var code = $('#region div').filter(
  				function(index){
  					return $('#region div').eq(index).data('selected');
  				}).attr('code');
  			var params = $.param({
  				type: 'sms',
  				code: code,
  				sms: sms
  			});
  			
  			$.ajax({
  				data:{
	  				type: 'sms',
	  				code: code,
	  				sms: sms
  				},
  				success: function(m){
  					if(/\s*true\s*/.test(m)) {  
  						$('#c'+ ($('#region div').index($("#region div[code='"+ code+ "']"))+ 1)).text(sms);						
  						alert('更新成功');
  					} else {
  						alert('更新失败');
  					}  					
  				},
  				error: function(){
  					alert('网络异常');
  				}
  			});
  		});
  	}
  	</script>
  	<script type='text/javascript'>
  	$(function(){
  		createId();
  		deleteClient();
  		saveClient();
  		changeId();
  	});
  	
  	function createId() {
  		$('#createNew').click(function(){
  			var lastId = $('#clientId').get(0).options[$('#clientId').get(0).options.length-1].text;
  			$('#clientId').hide();
  			$('#newClient').show().focus();
  			$('#newClient').val(lastId-0+1);  			
  		});
  		$('#cancelNew').click(function(){
  			$('#newClient').hide();
  			$('#clientId').show();
  		});  		
  	}
  	
  	function deleteClient() {
  		$('#deleteClient').click(function(){
  			var options = $('#clientId').get(0).options;
  			var index = options.selectedIndex;
  			var id = options[index].value;
  			
  			$.ajax({
  				data:{
  					type: 'client',
  					command: 'delete',
  					id: id
  				},
  				success: function(msg) {
  					if(/\s*true\s*/.test(msg)) {
  						alert("操作成功");
  						window.location.reload();
  					} else {
  						alert("操作失败");
  					}
  				}
  			});
  		});
  	}
  	
  	function saveClient() {
  		$('#saveClient').click(function(){
  			var isNew = $('#clientId').css('display')=='none';
  			
  			if(isNew) {  				
  				command = 'add';
  				id = $('#newClient').val();
  			} else {
  				var options = $('#clientId').get(0).options;
  				var index = options.selectedIndex;
  				id = options[index].value;
  				command = 'update';
  			}
  			
  			$.ajax({
  				data: {
  					type: 'client',
  					command: command,
  					id: id,
  					name: $('#nameText').val(),
  					subject: $('#subjectText').val(),
  					ip: $('#ipText').val()
  				},
  				success: function(msg) {
  					if(/\s*true\s*/.test(msg)) {
  						alert("操作成功");
  						window.location.reload();
  					} else {
  						alert("操作失败");
  					}
  				}
  			});
  		});
  	}
  	
  	function changeId() {
  		$('#clientId').change(function(){
  			var options = $('#clientId').get(0).options;
			var index = options.selectedIndex;
			var id = options[index].value;
			
			$.getJSON('/zq/door',
				{type: 'client', command: 'get', id: id},
				function(json){
  					$('#nameText').val(json.ename);
  					$('#subjectText').val(json.subject);
  					$('#ipText').val(json.ip);
  				});
  		});
  	}
  	</script>
  </body>
</html>
