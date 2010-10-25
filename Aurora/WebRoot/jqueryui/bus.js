$(function() {		
	$.ajaxSetup({
		global: false,
		type: 'post',
		cache: false,
		contentType: "application/x-www-form-urlencoded;charset=UTF-8"
	});
		
	$.getJSON('info.do?guiders', function(json) {//初始化接待人员
		Info.guiders = json;
		for(var index = 0; index < json.length; index++) {
			$('#guiderSelect').append('<option>'+json[index]+ '</option>');
		}
	});
	
	$.getJSON('info.do?technicians', function(json) {
		Info.technicians = json;
		for(var index = 0; index < json.length; index++) {
			$('#technicianSelect').append('<option>'+json[index]+ '</option>');
		}
	});
	
	$.getJSON('info.do?MMSTemplates', function(json) {
		Info.mmsTemplates = json;
		for(var index = 0; index < json.length; index++) {
			$('#mmsTemplate').append('<option>'+json[index].name[0]+ '</option>');
			$('#mmsContent').val(json[index].content[0]);
		}
	});
	
	$('#mmsTemplate').change(function() {
		var index = $(this).get(0).options.selectedIndex;
		var content = Info.mmsTemplates[index].content[0]; //内容
		$('#mmsContent').val(content);
	});
	
	//Next.getInvitationDays();
		
	$('#guiderSelect').change(function(){
		var selectVal = $('#guiderSelect').val();
		var valid = true; //有效值
		if(selectVal == '请选择') return;
		$('.guiderBlock').each(function(){
			if(selectVal == $(this).children('input[name="guider"]').val()) {
				//重复值
				valid = false;
			}
		});
		if(!valid) return;
		//复制下一个 
		if($('#guiderSelect').nextAll('.guiderBlock').length <= 1) {
			//没有选择任何接待人员
			$('.guiderBlock').eq(0).clone(true).show().children('#guider')
				.attr('name', 'guider').val(selectVal).end()
				.insertAfter($('#guiderSelect').nextAll().last());			
		} else {
			//已经选择了一个接待人员
			$('#guiderSelect').nextAll('.guiderBlock').eq(1)
				.children('input[name="guider"]').val(selectVal);			
		}
	});
	
	$('#technicianSelect').change(function(){
		var selectVal = $('#technicianSelect').val();
		var valid = true; //有效值
		if(selectVal == '请选择') return;
		$('.technicianBlock').each(function(){
			if(selectVal == $(this).children('input[name="technician"]').val()) {
				//重复值
				valid = false;
			}
		});
		if(!valid) return;
		//复制下一个 
		if($('#technicianSelect').nextAll('.technicianBlock').length <= 1) {
			//没有选择任何技术人员
			$('.technicianBlock').eq(0).clone(true).show().children('#technician')
				.attr('name', 'technician').val(selectVal).end()
				.insertAfter($('#technicianSelect').nextAll().last());			
		} else {
			//已经选择了一个技术人员
			$('#technicianSelect').nextAll('.technicianBlock').eq(1)
				.children('input[name="technician"]').val(selectVal);		
		}
	});
	   	
	$("#fast_order_dialog").dialog({
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
				var url = 'fastReservation.do';
				var data = $('form[id="fastInvitation"]').serialize();
				
				/*****还没判断输入的合法性*/
				$.post(url, data, function() {
					$("#maintabs").tabs('load',0); //刷新一下
					/*更新预约信息*/
				});	
				$('.customerLine :text').val(''); //清空输入框
				$(this).dialog('close');
			}
		}
	});
});

Info = {
	guiders: {}, 	//接待人员列表
	technicians: {}, //技术人员列表
	mmsTemplates: {}, //彩信模板列表
	invitationDays: [{time:'2010-02-25 07:00', title:'预约'}], //日为单位的预约信息
	invitationWeeks: {}, //周为单位的预约信息
	invitationMonths: {},  //月为单位的预约信息
	invitation:{}		//当前的邀请信息
}

Next = {
	//关闭快速预约	
	closeFastInvitation:function(e){
		$("#fast_order_dialog").dialog('close');
	},
	//快速预约
	showFastInvitation:function(e){	
		$('.customerList').insertAfter('#fastInvitation .stub');//客户列表移到此对话框
		var visitTime = e;
		$('#fastInvitation #visitTime-fast').attr('value', visitTime); //参观的时间设置好		
		$("#fast_order_dialog").dialog('open');
	},
	//添加一个客户
	addCustomerLine:function(e) {
		$('.customerLine').eq(0).clone(true).children().each(function(){
			$(this).children().eq(0).val('');
		}).end().appendTo($('.customerList'));
	},
	//删除一个客户
	removeCustomerLine:function(e){
		if($('.customerLine').size() > 1) {
			$(e).parent().remove();
		}		
	},
	//删除接待人员
	removeGuider:function(e) {
		$(e).parent().remove();
		$('#guiderSelect').get(0).options.selectedIndex = 0;
	},
	//删除技术人员 
	removeTechnician:function(e) {
		$(e).parent().remove();
		$('#technicianSelect').get(0).options.selectedIndex = 0;
	},
	//天为单位的预约信息
	getInvitationDays: function(date) {
		var current = date || '';		//date没指定就为空，表示当天
		$.getJSON('info.do?invitationDays&date='+current, function(json) {
			Info.invitationDays = json;
		});
	},
	//周为单位的预约信息
	getInvitationWeeks: function(e) {
		var current = date || '';
		$.getJSON('info.do?invitationWeeks&date='+current, function(json) {
			Info.invitationWeeks = json;
		});
	},
	//月为单位的预约信息
	getInvitationMonths: function(e) {
		var current = date || '';
		$.getJSON('info.do?invitationMonths&date='+current, function(json) {
			Info.invitationMonths = json;
		});
	},
	//某id的邀请
	getInvitationById: function(iid) {
		$.getJSON('info.do?invitation&invitationId='+iid, function(json) {
			Info.invitation = json;
		});
	},
	//正在上传
	uploading: function() {
		$('#uploadStatus').text('上传中...');
	},
	//上传成功
	uploadSuccess: function(txt) {
		var customers = txt.split(';');
		var count = customers.length - 1;//待添加的客户数
		var currentLineCount = $('.customerLine').size();//当前的行数
		
		if(count <= 0) {
			alert('客户列表为空，请检查后重新上传');
		} else if(count > currentLineCount) {//需要添加行
			var addLineCount = count - currentLineCount;
			var line = new Array(addLineCount);
			$(line).each(function() {
				Next.addCustomerLine();
			});
		}
		
		for(var index = 0;index < count; index++) {
			var customer = customers[index];
			var cInfoArray = customer.split('|');
			var blanks = $('.customerLine').eq(index).find(':text');
			for(var j=0;j<blanks.length;j++) {
				$(blanks[j]).val(cInfoArray[j] || '');
			}
		}
		$("#upload_flie_dialog").dialog('close');		
	},
	//上传失败
	uploadFail: function(msg) {
		$('#uploadStatus').text(msg);
	},
	today: function() {
		var now = new Date();
		var year = now.getYear();
	}
}; 