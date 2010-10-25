<%@ page language="java" import="java.util.*,data.Database" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  	Database db = new Database();
%>
<html>
  <head>
    <title>系统增加-政企体验馆</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="添加页面">
	
	<link rel="shortcut icon" href="../favicon.ico" />
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/append.css">
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="../js/append.js"></script>
  </head>
  
  <body>
  	<hr style='margin-top:-8px;'/>	    
  	<div class='body'>  		
  		<div class='content'>
  		<fieldset>
  			<legend><i>添加地区</i></legend>  
  			<div id='address'>			
  				<div class='addrState'></div>
  				
	  			<div class='addrCode'>
		  			<span>地区代码：</span>
		  			<div><input id='addrCode' type='text'/></div>
	  			</div>
	  			
  				<div class='addrName'>
		  			<span>地区名称：</span>
		  			<div><input id='addrName' type='text'/></div>
	  			</div>	  			
	  			
	  			<input type='button' id='appendAddr' value='添加'/>	  		
	  		</div>  			
  		</fieldset>
  		
  		<fieldset>
  			<legend><i>添加营业厅</i></legend>
  			<div id='selling'>  				
  				<div class='sellingState'></div>
  				
	  			<span>地区：</span>
  				<select id='addr1'></select>
	  			
	  			<div class='sellingCode'>
		  			<span>营业厅代码：</span>
		  			<div><input id='sellingCode' type='text'/></div>
	  			</div>
	  			
	  			<div class='sellingName'>
		  			<span>营业厅名称：</span>
		  			<div><input id='sellingName' type='text'/></div>
	  			</div>
	  			
	  			<input type='button' id='appendSelling' value='添加'/>
  			</div>
  		</fieldset>
  		
  		<fieldset>
  			<legend><i>添加设备</i></legend>
  			<div id='equipment'>
  				<div class='equipmentState'></div>
  				
	  			<span>地区：</span>
  				<select id='addr2'></select>
  				
	  			<div class='selectSelling'>
	  				<span>营业厅：</span>
	  				<select id='selling1'></select>
	  			</div>
	  			
	  			<div class='equipmentCode'>
	  				<span>设备代码：</span>
	  				<div><input id='equipmentCode' type='text'/></div>
	  			</div>
	  			
	  			<div class='equipmentName'>
	  				<span>设备名称：</span>
	  				<div><input id='equipmentName' type='text'/></div>
	  			</div>
	  			
	  			<input type='button' id='appendEquipment' value='添加'/>
	  		</div>
  		</fieldset> 
  		</div>
  	</div>
  	
  	<script type='text/javascript'>
  	$(document).ready(function() {
  		if(!allowVisit()) void(0);
  		heightAdapter();
  		changeTitle();
  	})
  	
  	function changeTitle() {
  		parent.document.title = document.title;
  	}
   	
  	/*高度自适应*/
	function heightAdapter() {
		try {
			var height = document.body.scrollHeight;
			parent.document.getElementById('page').style.height = height;
		} catch(err) {}
	}
	
	/*限制直接访问*/
	function allowVisit() {
		if(parent.document.getElementById('page') == null) {
			document.body.innerHTML = '禁止直接访问';
			return false;
		}
		return true;
	}
  	
  	(function(){
		var addrValues = <%=db.getAddressJson()%>;
		var sellingValues = <%=db.getSellingJson()%>;//json数据
		var addrSize = addrValues.addressName.length;
		var sellingSize = sellingValues.sellingName.length;//成员数
		
		var address1 = document.getElementById('addr1').options;
		var address2 = document.getElementById('addr2').options;
		var selling1 = document.getElementById('selling1').options;//下拉框集合				
		
		var addrs = [];//地区数组
		var sellings = [];//营业厅数组
		var selectedIndex = address1.selectedIndex;
		
		$(document).ready(function() {
			init();
			$('#addr2').bind('change', changeAddr);
		});
		
		function init() {
			for(var i=0,j=0; j<addrSize;j++) {//地区code和name放入数组
				addrs[i] = addrValues.addressName[j];
				addrs[i+1] = addrValues.addressCode[j];		
				i += 2;			
			}
			
			for(var i=0,j=0; j<sellingSize;j++) {//营业厅code和name放入数组
				sellings[i] = sellingValues.sellingName[j];
				sellings[i+1] = sellingValues.sellingCode[j];
				i += 2;	
			}
			
			address1.length = 0;			
			address2.length = 0; //清空列表
			for(var i=0; i<addrs.length;) { //添加地区列表
				address1.add(new Option(addrs[i],addrs[i+1]));
				address2.add(new Option(addrs[i],addrs[i+1]));
				i += 2;
			}
			
			selling1.length = 0;
			selectedIndex = address2.selectedIndex;
			for(var i=0; i<sellings.length;) {	//添加营业厅列表
				if(sellings[i+1].substr(0,4) == address2[selectedIndex].value) {
					selling1.add(new Option(sellings[i],sellings[i+1]));
				}
				i += 2;
			}
		}
		
		function changeAddr() {//下拉框改变
			selectedIndex = address2.selectedIndex;
			selling1.length = 0;
			for(var i=0; i<sellings.length;) {	
				if(sellings[i+1].substr(0,4) == address2[selectedIndex].value) {
					selling1.add(new Option(sellings[i],sellings[i+1]));
				}
				i += 2;
			}
		}
	})();
  	</script>
  </body>
</html>
