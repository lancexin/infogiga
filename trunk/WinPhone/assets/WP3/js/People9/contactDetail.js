function initialize(param) {	
	var person = window.contact.getInfo(param);
//	var detail = window.contact.getDetails(param);
	
	$id('name').innerHTML = person.getName().toLocaleString();
	
	$id('callmobilenumber').innerHTML = person.getNumber().toLocaleString();
	$id('textmobilenumber').innerHTML = person.getNumber().toLocaleString();
	
	/*var phones = detail.getContactMethod().phone;
	if(phones.home) {
		$('#callhomenumber').html(phones.hone.toLocaleString());
	}
	if(phones.work) {
		$('#callworknumber').html(phones.work.toLocaleString());
	}*/
}

function $id() {
	var e = arguments[0] || '';
	return document.getElementById(e);
}

Key = {
	menu: function(){
		//$.lockScreen({background:'url(../../image/spec/wallpaper.png) no-repeat'});	
	},
	back: function(){
		window.base.deactivate('contactDetail');
		window.base.activate('people');
	}	
}
