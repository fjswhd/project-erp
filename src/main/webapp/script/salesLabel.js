
	var label = document.getElementsByClassName('label');
	
	label[0].onclick = function() {
		location.href = '/project/sales/customerList.do?p=1';
	};
	label[1].onclick = function() {
		location.href = '/project/sales/customerInsertForm.do';
	};
	label[2].onclick = function() {
		location.href = '/project/sales/orderList.do?p=1';
	};
	label[3].onclick = function() {
		location.href = '/project/sales/orderInsertForm.do';
	};