
	var label = document.getElementsByClassName('label');
	

	label[0].onclick = function() {
		location.href = '/project/st/productList.do';
	};
	label[1].onclick = function() {
		location.href = '/project/st/inProductList.do';
	};
	label[2].onclick = function() {
		location.href = '/project/st/outProductList.do';
	};
	label[3].onclick = function() {
		location.href = '/project/st/modifyStockList.do';
	};