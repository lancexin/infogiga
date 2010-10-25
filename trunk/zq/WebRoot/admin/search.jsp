<%@ page language="java" import="data.Database" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
  	Database db = new Database();  
%>
<html>
  <head>
    <title>体验查询-政企体验馆</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="查询页面">
	
	<link rel="shortcut icon" href="../favicon.ico" />
	<link rel="stylesheet" type="text/css" href="../css/main.css">
	<link rel="stylesheet" type="text/css" href="../css/search.css">
	<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<script type="text/javascript" src="../js/search.js"></script>
  </head>
  
  <body>
  	<hr style='margin-top:-8px;'/>	
  	<div class='body' id='bd'>
		<div class='showData'>
			<iframe src='data.jsp' id='dataFrame' frameborder='0' onload='changeFrameHeight()'></iframe> 			
		</div>	
  	</div>
	
	<div class='showBar' id='showBar'>
		
	</div>
  	<div class='searchBar' id='searchBar'>
  		<div class='equipmentBlock'>
  			<span>地区：</span>
			<select id='area'></select>
			
  			<span>营业厅：</span>
			<select id='selling'></select>
			
  			<span>设备：</span>
  			<select id='region'></select>
			<select id='equipment'></select>
		</div>
		
		<div class='operationBlock'>
			<span>系统：</span>
			<select id='sys'></select>
			
			<span>体验：</span>
			<select id='operate'></select>
			
			<div id='control'>
			<input type='button' value='↑' id='up'/>
			<input type='button' value='↓' id='down'/>
			</div>
		</div>
		
		<div class='stateBlock'>
			<span>开始时间：</span>
			<input type='text' id='stime'/><img src='../img/cab.jpg' id='st'></img>
			
			<span>结束时间：</span>
			<input type='text' id='etime'/><img src='../img/cab.jpg' id='et'></img>			
			
			<span>手机号码：</span>
			<input type='text' id='mobile'/>
		</div>
		
		<div class='queryBlock'>
			<div class='reportBlog'>
				<form method='post' id='reportForm' action='/zq/door'>
					<input type='hidden' name='type' value='report'/>
					<input type='button' id='fastQuery' value='快速查询'/>
					<input type='button' id='query' value='图表查询'/>
					<input type='button' id='buildReport' value='输出报表'/>
				</form>
			</div>
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
		try{
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
	
  	(function(){//地区、营业厅、设备
  		var addrValues = <%=db.getAddressJson()%>;
		var sellingValues = <%=db.getSellingJson()%>;
		var regionValues = <%=db.getRegionJson()%>;
		var equipmentValues = <%=db.getEquipmentJson()%>;
		
		var addrSize = addrValues.addressName.length;
		var sellingSize = sellingValues.sellingName.length;
		var regionSize = regionValues.regionName.length;
		var equipmentSize = equipmentValues.equipmentName.length;//成员数
		
		var address = document.getElementById('area').options;
		var selling = document.getElementById('selling').options;
		var region = document.getElementById('region').options;
		var equipment = document.getElementById('equipment').options;//下拉框集合				
		
		var addrs = [];//地区数组
		var sellings = [];//营业厅数组
		var regions = [];//区域数组
		var equipments = [];//设备数组
		var selectedIndex = address.selectedIndex; //被选中的索引
		
		$(document).ready(function() {
			init();	
			$('#area').bind('change', changeAddr);	
			$('#selling').bind('change', changeSelling);
			$('#region').bind('change', changeRegion);
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
			
			for(var i=0,j=0; j<regionSize;j++) {//区域code和name放入数组
				regions[i] = regionValues.regionName[j];
				regions[i+1] = regionValues.regionCode[j];
				i += 2;	
			}
			
			for(var i=0,j=0; j<equipmentSize;j++) {//设备code和name放入数组
				equipments[i] = equipmentValues.equipmentName[j];
				equipments[i+1] = equipmentValues.equipmentCode[j];
				i += 2;	
			}
			
			address.length = 0;			
			selling.length = 0;
			region.length = 0;
			equipment.length = 0; //清空列表
			
			address.add(new Option('所有地区', ''));
			for(var i=0; i<addrs.length;) { //添加地区列表
				address.add(new Option(addrs[i],addrs[i+1]));
				i += 2;
			}
			
			selling.add(new Option('所有营业厅', ''));
			region.add(new Option('所有区域', ''));
			equipment.add(new Option('所有设备', ''));			
		}	
		
		function changeAddr() {
			selectedIndex = address.selectedIndex;			
			selling.length = 0;
			selling.add(new Option('所有营业厅', ''));
			for(var i=0; i<sellings.length;) {	//地区改变，则改变营业厅列表
				if(sellings[i+1].substr(0,4) == address[selectedIndex].value) {
					selling.add(new Option(sellings[i],sellings[i+1]));
				}
				i += 2;
			}
			//changeSelling(); //同时改变区域列表
		}
		
		function changeSelling() { //营业厅改变的时候,改变区域列表
			selectedIndex = selling.selectedIndex;			
			region.length = 0;
			region.add(new Option('所有区域', ''));
			for(var i=0; i<regions.length;) {	//营业厅改变，则改变区域列表
				if(regions[i+1].substr(0,8) == selling[selectedIndex].value) {
					region.add(new Option(regions[i],regions[i+1]));
				}				
				i += 2;
			}
		}
		
		function changeRegion() { //区域改变的时候,改变设备列表
			selectedIndex = region.selectedIndex;			
			equipment.length = 0;
			equipment.add(new Option('所有设备', ''));
			for(var i=0; i<equipments.length;) {	//营业厅改变，则改变设备列表
				if(equipments[i+1].substr(0,10) == region[selectedIndex].value) {
					equipment.add(new Option(equipments[i],equipments[i+1]));
				}
				i += 2;
			}
		}
  	})();
  	
  	(function() {//系统、体验
  		var systemValues = <%=db.getSystemJson()%>;
		var operateValues = <%=db.getOperateJson()%>;
		var systemSize = systemValues.systemName.length;		
		var operateSize = operateValues.operateName.length;//成员数
		
		var sys = document.getElementById('sys').options;
		var operate = document.getElementById('operate').options;//下拉框集合				
		
		var systems = [];//系统数组
		var operates = [];//体验数组
		var selectedIndex = sys.selectedIndex; //被选中的索引
		
		$(document).ready(function() {
			init();	
			$('#sys').bind('change', changeSystem);	
		});
		
		function init() {
			for(var i=0,j=0; j<systemSize;j++) {//系统code和name放入数组
				systems[i] = systemValues.systemName[j];
				systems[i+1] = systemValues.systemCode[j];		
				i += 2;	
			}
			
			for(var i=0,j=0; j<operateSize;j++) {//体验code和name放入数组
				operates[i] = operateValues.operateName[j];
				operates[i+1] = operateValues.operateCode[j];
				i += 2;	
			}
			
			sys.length = 0;	
			operate.length = 0; //清空列表
			sys.add(new Option('所有系统', ''));
			for(var i=0; i<systems.length;) { //添加系统列表
				sys.add(new Option(systems[i],systems[i+1]));
				i += 2;
			}
			
			operate.add(new Option('所有体验', ''));			
		}	
		
		function changeSystem() {
			selectedIndex = sys.selectedIndex;			
			operate.length = 0;
			operate.add(new Option('所有体验', ''));
			for(var i=0; i<operates.length;) {	//系统改变，则改变体验列表
				if(operates[i+1].substr(0,7) == sys[selectedIndex].value) {
					operate.add(new Option(operates[i],operates[i+1]));
				}
				i += 2;
			}
		}
  	})();
  	</script>
  </body>
</html>
