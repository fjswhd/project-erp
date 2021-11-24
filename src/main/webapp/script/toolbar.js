/**
 * 
 */
	var toolbar = document.getElementsByClassName('toolbar');
	var tool = document.getElementsByClassName('tool');
	tool[0].onclick = function() {
		location.href = '/project/purchase/sellerList.do?p=1'
	};
	tool[1].onclick = function() {
		location.href = '/project/sales/customerList.do?p=1'
	};
	tool[2].onclick = function() {
		location.href = '/project/product/list.do'
	};
	tool[3].onclick = function() {
		location.href = '/project/accounting/list.do'
	};
	tool[4].onclick = function() {
		location.href = '/project/hr/list.do?p=1'
	};
	


