Ajax = {
	init: function() {
		var xmlHttp;	 
		 try {
		    xmlHttp=new XMLHttpRequest();
		 } catch (e) {
			try {
		      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {	
		      try {
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		      } catch (e) {		        
		         return false;
		      }
			}
		 }
		 return xmlHttp;
	},
	check: function() {
		
	},
	post: function() {
		var request = Ajax.init() || null;
		var url = arguments[0]||"";
		var data = arguments[1] || null;
		var callback = arguments[2] || {};
		
		request.onreadystatechange = function() {
			if(request.readyState==4) {
				callback(request.responseText);
			}
		}
		request.open("post", url, true);
		request.send(data);
	}
}

Action = {
	handler:null,
	send: function() {
		Ajax.post("to?v=3", null, function(msg) {
			if(msg == '1') {
				clearInterval(Action.handler);
				window.location = "to?v=3";
			}
		});
	},
	checkQrcode: function() {
		Action.handler = setInterval(Action.send, 1000);
	}
}