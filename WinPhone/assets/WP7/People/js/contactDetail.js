function initialize(param) {	
//	var person = window.contact.getInfo(param);
	var people = Data_contact;
	var pid = parseInt(param);
	var index = -1;
	for(var i=0; i<people.length; i++) {
		if(people[i].pid == pid) {
			index = i;
			break;
		}
	}	
	var person = people[index];
//	var detail = windw.contact.getDetails(param);
	
	$id('name').innerHTML = person.name;
	
	$id('callmobilenumber').innerHTML = person.number;
	$id('textmobilenumber').innerHTML = person.number;
	
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
		window.base.deactivate('People.contactDetail');
		window.base.activate('People.people');
	}	
}
