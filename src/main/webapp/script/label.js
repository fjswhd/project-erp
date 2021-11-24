
var label = document.getElementsByClassName('label');
var pathName = window.location.pathname

if(pathName.includes('hr')) {
	label[0].onclick = function() {
		location.href = '/project/hr/list.do?p=1';
	};
	label[1].onclick = function() {
		location.href = '/project/hr/checkForm.do';
	};
	label[2].onclick = function() {
		location.href = '/project/hr/insertForm.do';
	};	
} else if (pathName.includes('sales')) {
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
} else if (pathName.includes('seller')||pathName.includes('product')) {
	label[0].onclick = function() {
		location.href = '/project/seller/list.do?p=1';
	};
	label[1].onclick = function() {
		location.href = '/project/seller/insertForm.do';
	};
	label[2].onclick = function() {
		location.href = '/project/product/list.do?p=1';
	};
	label[3].onclick = function() {
		location.href = '/project/product/insertForm.do';
	};
	label[4].onclick = function() {
		location.href = '/project/purchase/orderList.do?p=1';
	};
	label[5].onclick = function() {
		location.href = '/project/purchase/orderInsertForm.do';
	};
}
