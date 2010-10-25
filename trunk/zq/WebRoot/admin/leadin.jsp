<%@ page language="java" import="bean.InfoBean;" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id='db' class='data.Database'/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>二维码导览-政企体验馆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="planar">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type='text/javascript' src='js/jquery-1.3.2.min.js'></script>
  </head>
  
  <body>
  	<hr style='margin-top:-8px;'/>	
    <div class='register'>
    	<span id='nameLabel'>姓名:</span>
    		<input type='text' id='name'>
    	<span id='sexLabel'>性别:</span>
    		<input type='radio' name='sex' checked="checked"/><i>男</i>
    		<input type='radio' name='sex'/><i>女</i>
    	<span id='phoneLabel'>手机号:</span>
    		<input type='text' id='phone' />
    	<span id='planarLabel'>二维码:</span>
    		<input type='text' id='planar' />
    	<span id='planarList'></span>
    	<input type='button' value='登记' id='checkin' />
    </div>
    <hr class='line'/>
    <div class='view'>
    	<table id='infoTable' border='1' cellspacing='0' cellpadding='2'>
    		<tr id='tableHead'>
    			<th>姓名</th>
    			<th>性别</th>
    			<th>手机号</th>
    			<th>二维码</th>
    			<th>操作</th>
    		</tr>
    		<% for(InfoBean info: db.getInfo()) {%>
    		<tr>
    			<td><%=info.getName() %></td>
    			<td><%=info.getGender() %></td>
    			<td><%=info.getPhone() %></td>
    			<td><%=info.getPlanar() %></td>
    			<td><input type='button' value='注销' class='checkout' 
    				data='<%=info.getId() %>' onclick='checkout(this.parentNode.parentNode)'/></td>
    		</tr>
    		<%} %>
    	</table>
    </div>

    <script type="text/javascript">
    var hide = true;
   	$(document).ready(function(){
   		if(!allowVisit()) void(0);
   		initAjax();   	
   		checkFocus();
   		changeTitle();
   		$('#name').focus();
   		$('#checkin').bind('click', checkin);//登记
   		$('#planar').bind('click, focus', showList).bind('keydown', 
   			function(event){//tab键
   				if(event.keyCode == 9) hideList();
   			});
   		heightAdapter();
   		paintLine();
   	}); 
   	
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
   	
   	/*行着色*/
   	function paintLine() {
   		$('#infoTable tr').each(function(){
   			if($('#infoTable tr').index($(this)) %2 == 0) {
   				//表标题不变色
   				if($('#infoTable tr').index($(this)) == 0) return; 
   				$(this).css('background', '#def');
   			}
   			else {
   				$(this).css('background', '#efefff');
   			}
   		});
   	}
   	
   	/*自适应页面高度*/
   	function heightAdapter() {
   		try{
		var height = document.body.scrollHeight;
		height = height < 500? 500:height;
		parent.document.getElementById('page').style.height = height;
		} catch(err) {}
	}
	
	/*焦点检测*/
   	function checkFocus() {   		
   		$(document.body).bind('click', function(e){
   			if(e.target.id != 'planar' && e.target.id != 'planarList') {
   				hideList();
   			}
   		});
   	}
   	
   	/*登记*/
   	function checkin() {
   		var gender = getGender();
   		var data = {
   			type: 'in',
   			name: $('#name').val(),
   			gender: gender,
   			phone: $('#phone').val(),
   			planar: $('#planar').val()
   		} 
   		if(!check()) return;
   		$.ajax({
   			data: data,
   			success: function(msg){
   				if(/^\s*using\s*$/.test(msg)) {
   					alert('此二维码在使用中，请选择其他二维码进行注册');
   				}
   				else if(/^\s*notin\s*$/.test(msg)) {
   					alert('此二维码不存在，请使用其他二维码进行注册');
   				}
   				else if(/^\s*fail\s*$/.test(msg)) {
   					alert('登记失败');
   				}
   				else {
   					newLine($('#name').val(), gender, $('#phone').val(), $('#planar').val(), msg);
   					heightAdapter(); //高度增加
   					paintLine(); //重新行着色
   				}
   			} 
   		});
   	}
   	
   	/*获取性别*/
   	function getGender() {
   		if(sex[0].checked) return "男";
   		else if(sex[1].checked) return "女";
   		else return "";
   	}
   	
   	/*检验数据合法性*/
   	function check() {
   		var name = '';
   		if($('#name').val() == '') { name = '名字,'; }
   		if($('#gender').val() == '') { name += '性别,'; }
   		if($('#phone').val() == '') { name += '手机号,';	}
   		if($('#planar').val() == '') { name += '二维码';	}
   		if(name != '') { 
   			alert(name+ '不能为空');
   			return false;
   		}
   		
   		if(!/^1[358]\d{9}$/.test($('#phone').val())) {
   			alert('手机号不合法');
   			return false;
   		}
   		return true;
   	}
   	
   	/*显示二维码列表*/
   	function showList() {
   		var src = $('#planar').get(0);
   		var left = getPos(src, "Left");
   		var top = getPos(src, "Top")+ src.offsetHeight;
   		
   		createList(); 
   		$('#planarList').css({
   			left: left,
   			top: top,
   			display: 'block'
   		});
   	}
   	
   	/*创建二维码列表*/
   	function createList() {
   		var list;
   		$('#planarList ul').remove();
   		$.getJSON('/zq/do', 'type=freeCode', function(json) {
			list = json.planar;
	   		$('#planarList').append('<ul id="pu"></ul>');
	   		for(i=0; i<list.length; i++) {
	   			$('#pu').append(
	   			'<li onclick="pitch(this)" onmouseover="overList(this)" onmouseout="outList(this)">'
	   			+ list[i]+ '</li>');
	   		}
	   		heightAdapter();
   		});
   	}
   	
   	/*选中*/
   	function pitch(obj) {
   		var e = obj;   		
   		$('#planar').val($(e).html());
   		hideList();
   	}
   	
   	/*滑过*/
   	function overList(obj) {
   		var e = obj;
   		$(e).css({
   			background:'#ade',
			'font-size':'1.1em',
   			color:'blue',
   			'font-weight':'bold'
   			});
   	}
   	
   	/*滑出*/
   	function outList(obj) {
   		var e = obj;
   		$(e).css({
   			background:'#eee',
   			'font-size':'1em',
   			color:'black',
   			'font-weight':'normal'
   			});
   	}	
   	
   	/*隐藏二维码列表*/
   	function hideList() {
   		$('#planarList').css('display', 'none');
   	}
   	
   	/*获取绝对位置*/
   	function getPos(el,ePro) 
	{
	    var ePos=0;
	    while(el!=null)
	    {       
	        ePos+=el["offset"+ePro];
	        el=el.offsetParent;
	    }
	    return ePos;
	}
   	
   	/*注销*/
   	function checkout(row) {
   		var data = {
   			type: 'out',
   			id: row.childNodes[4].childNodes[0].data
   		}
   		if(!confirm('确认注销这条记录？')) return;
   		$.ajax({
   			data: data,
   			success: function(msg) {
   				if(/^\s*true\s*$/.test(msg)) {
   					removeLine(row);
   					paintLine(); //重新行着色
   				}
   				else {
   					alert('注销失败');
   				}
   			} 
   		});
   	}
   	   	
   	/*初始化ajax*/
   	function initAjax() {
   		$.ajaxSetup({
   			url:'/zq/do',
   			type:'post',
   			cache:false,
   			async:true
   		})
   	}
   	
   	/*添加新行*/
   	function newLine(name, sex, phone, code, checkID) {
   		var row = $('#infoTable').get(0).insertRow();
   		row.insertCell().innerText = name;
   		row.insertCell().innerText = sex;
   		row.insertCell().innerText = phone;
   		row.insertCell().innerText = code;
   		row.insertCell().innerHTML = 
   			"<input type='button' value='注销' class='checkout' data='"+ checkID+ "'/>";
   		
   		$(row.cells[4]).bind('click', function(){ //绑定注销按钮事件
   			checkout(row);
   		});    		
   	}
   	
   	/*删除指定行*/
   	function removeLine(row) {
   		var index = row.rowIndex;
   		$('#infoTable').get(0).deleteRow(index);
   	}
    </script>
  </body>
</html>
