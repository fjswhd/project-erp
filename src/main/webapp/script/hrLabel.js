
	var label = document.getElementsByClassName('label');
	

	label[0].onclick = function() {
		location.href = '/project/hr/list.do';
	};
	label[1].onclick = function() {
		location.href = '/project/hr/updateForm.do';
	};
	label[2].onclick = function() {
		location.href = '/project/hr/insertForm.do';
	};