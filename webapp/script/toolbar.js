/**
 * 
 */
	var toolbar = document.getElementsByClassName('toolbar');
	var tool = document.getElementsByClassName('tool');
	tool[0].onclick = function() {
		location.href = '/project/buying/list.do'
	};
	tool[1].onclick = function() {
		location.href = '/project/selling/list.do'
	};
	tool[2].onclick = function() {
		location.href = '/project/st/productList.do'
	};
	tool[3].onclick = function() {
		location.href = '/project/accounting/list.do'
	};
	tool[4].onclick = function() {
		location.href = '/project/hr/list.do'
	};
	


