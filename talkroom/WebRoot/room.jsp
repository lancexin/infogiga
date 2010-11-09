<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Talk Room</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type='text/javascript' src='dwr/interface/RoomManager.js'></script>  
	<script type='text/javascript' src='dwr/engine.js'></script>  
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript">
	var talkTo = 'all';
	
	var userName = '<%=new String(request.getParameter("name").getBytes("iso8859-1"),"utf-8")%>';
	
	var mId = '';
	
	function login(){
		RoomManager.login(userName,function(data){
			if(data){
				mId = data;
			}else{
				document.location = "login.html";
			}
			
		});
	}
	
	function setId(id){
		mId = id;
	}
	
	function freshMenu(m){
			eval("var menu="+m);
			document.getElementById("user-list").innerHTML = '';
			for(var i=0;i<menu.length-1;i++){
				var d = document.getElementById("user-list").innerHTML;
				document.getElementById("user-list").innerHTML =d+ "<li style='cursor: pointer;'><a id='"+menu[i].id+"' href='javascript:;' onclick='callOne(this)'>"+menu[i].name+"</a></li>";
			}
	}
	
	function insertMsg(msg){
		document.getElementById("msg-container").innerHTML = document.getElementById('msg-container').innerHTML+msg+"<br/>";
	}
	
	function insertUser(id,name){
		var h = document.getElementById("user-list").innerHTML;
		document.getElementById("user-list").innerHTML = h+"<li style='cursor: pointer;'><a id='"+id+"' href='javascript:;' onclick='callOne(this)'>"+name+"</a></li>";
	}
	
	function removeUser(id){
		var el = document.getElementById(id).parentNode;
		el.parentNode.removeChild(el);
	}
	
	function callAll(){
		talkTo = 'all';
		document.getElementById('sendname').innerHTML = '所有人';
	}
	
	function callOne(t){
		talkTo = t.id;
		document.getElementById('sendname').innerHTML = t.innerHTML;
	}
	
	function sendMsg(){
		var msg = document.getElementById("msg").value;
		if(!msg){
			return;
		}
		RoomManager.sendMsg(userName,mId,talkTo,msg);
		document.getElementById("msg").value = '';
	}
	
	function clean(){
		document.getElementById('msg-container').innerHTML = "";
	}
	
	window.onbeforeunload = onbeforeunload_handler;  
    window.onunload = onunload_handler;  
    function onbeforeunload_handler(){  
        var warning="确认退出?";  
        return warning;  
    }  
	      
    function onunload_handler(){  
        var warning="谢谢光临";  
        RoomManager.layout(userName);  
    } 
	</script>
  </head>
  
  <body onload="dwr.engine.setActiveReverseAjax(true);login();">
  <div style="float:left;width: 920px">
  	<div style="border: solid 1px;width: 912px;height: 50px;float: left;margin-bottom: 5px">
  		<h2>DWR聊天室,测试</h2>
  	</div>
  	<div id="msg-container" style="border: solid 1px;width: 700px;height: 400px;overflow: auto;float: left" >
    		
    </div>
    <div id="user-container" style="border: solid 1px;margin-left: 10px;height: 400px;width: 200px;float: left;overflow: auto">
    	<ul id="user-list">
    		
    	</ul>
    </div>
    <div style="border: solid 1px;float:left;width: 902px;height:50px;margin-top: 10px;padding: 5px">
    	发送给：<span id="sendname" style="margin-right: 10px">所有人</span><input onclick="callAll()" type="button" value="所有人"/><input type="button" value="清空" onclick="clean()"/><br/>
    	<input id="msg" type="text" style=" width : 700px;"/><input type="button" value="发送" onclick="sendMsg()"/>
    </div>
  </div>
    
  </body>
</html>
