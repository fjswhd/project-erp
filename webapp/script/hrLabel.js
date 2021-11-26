
	var label = document.getElementsByClassName('label');
	

	label[0].onclick = function() {
		location.href = '/project/hr/list.do?p=1';
	};
	label[1].onclick = function() {
		location.href = '/project/hr/checkForm.do';
	};
	label[2].onclick = function() {
		location.href = '/project/hr/insertForm.do';
	};