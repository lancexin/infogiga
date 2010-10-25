
$(document).ready(function(){
	$('.logo img').bind('click', goChinaMobile);
	$('#appendAddr').bind('click', appendAddr);
	$('#appendSelling').bind('click', appendSelling);
	$('#appendEquipment').bind('click', appendEquipment);
	
});

function goChinaMobile() {
	window.open('http://www.zj.chinamobile.com');
}

function checkBlank(str) {//检测是否为空
	if(/\s+/.test(str) || str == null || str == '') {
		return true;
	}
	return false;
}

function appendAddr() {
	var params = 
	{type:'append', 
	 key:'area', 
	 areaName:$('#addrName').val(), 
	 areaCode:$('#addrCode').val()
	 };
	var url = '/zq/door?'+ $.param(params);
	
	if(!/^\d{4}$/.test($('#addrCode').val())) {//判断合法性
		$('.addrState').text('地区代码必须是4位数字');
		$('#addrCode').val('').focus();
		$('.sellingState, .equipmentState').text('');
		return ;
	}
	else if(checkBlank($('#addrName').val())) {
		$('.addrState').text('地区名称不能为空或有空格');
		$('#addrName').val('').focus();
		$('.sellingState, .equipmentState').text('');
		return ;
	}
	
	$('#appendAddr').attr('disabled','true');
	$.ajax({
		type:'post',
		url:url,
		async:false, //同步
		cache:false, //不使用缓存
		success:function(msg) { 
			document.location.reload();
		},
		error:function(msg) {
		},
		complete:function() {
			$('#appendAddr').removeAttr('disabled');
		}
	});
}

function appendSelling() {
	var address1 = document.getElementById('addr1').options;
	var selectedIndex = address1.selectedIndex;
	var params = 
	{type:'append', 
	 key:'selling', 
	 sellingName:$('#sellingName').val(), 
	 sellingCode:address1[selectedIndex].value+$('#sellingCode').val()};
	var url = '/zq/door?'+ $.param(params);
	
	if(!/^[A-Z]\d{3}$/.test($('#sellingCode').val())) {//判断合法性
		$('.sellingState').text('营业厅代码必须是1个大写字母加3位数字');
		$('#sellingCode').val('').focus();
		$('.addrState, .equipmentState').text('');
		return ;
	}
	else if(checkBlank($('#sellingName').val())) {
		$('.sellingState').text('营业厅名称不能为空或者有空格');
		$('#sellingName').val('').focus();
		$('.addrState, .equipmentState').text('');
		return ;
	}
	
	$('#appendSelling').attr('disabled','true');
	$.ajax({
		type:'post',
		url:url,
		async:false, //同步
		cache:false, //不使用缓存
		success:function(msg) { 
			window.location.reload();
		},
		error:function(msg) {
		},
		complete:function() {
			$('#appendSelling').removeAttr('disabled');
		}
	});
}

function appendEquipment() {
	var address2 = document.getElementById('addr2').options;
	var selling1 = document.getElementById('selling1').options;	
	var selectedIndex1 = address2.selectedIndex;
	var selectedIndex2 = selling1.selectedIndex;
	
	if(selectedIndex2 == -1) {//第二个下拉框中没有内容的情况
		$('.equipmentState').text('请先添加属于该地区的营业厅');
		$('#equipmentCode, #equipmentName').val('');
		$('#sellingCode').focus();
		return;
	}
	
	var params =
	{type:'append',
	 key:'equipment',
	 equipmentName:$('#equipmentName').val(), 
	 equipmentCode:selling1[selectedIndex2].value+$('#equipmentCode').val()};
	var url = '/zq/door?'+ $.param(params);
	
	if(!/^[A-Z]\d[A-Z]\d{5}$/.test($('#equipmentCode').val())) { //判断合法性
		$('.equipmentState').text('设备代码格式形如A1B00001');
		$('#equipmentCode').val('').focus();
		$('.addrState, .sellingState').text('');
		return ;
	}
	else if(checkBlank($('#equipmentName').val())) {
		$('.equipmentState').text('设备名称不能为空或者有空格');
		$('#equipmentName').val('').focus();
		$('.addrState, .sellingState').text('');
		return ;
	}
	
	$('#appendEquipment').attr('disabled','true');
	$.ajax({
		type:'post',
		url:url,
		async:false, //同步
		cache:false, //不使用缓存
		success:function(msg) {  	
			window.location.reload();				
		},
		error:function(msg) {
		},
		complete:function() {
			$('#appendEquipment').removeAttr('disabled');
		}
	});
}

$(document).ready(function() {
	$('#addrCode').focus();
	$('#addrCode, #addrName').bind('keydown', function(event){
		if(event.keyCode == 13) {
			$('#appendAddr').click();
		}
	});
	$('#sellingCode, #sellingName').bind('keydown', function(event){
		if(event.keyCode == 13) {
			$('#appendSelling').click();
		}
	});
	$('#equipmentCode, #equipmentName').bind('keydown', function(event){
		if(event.keyCode == 13) {
			$('#appendEquipment').click();
		}
	});
});