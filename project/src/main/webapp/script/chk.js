/**
 * 
 */
var xhr = new XMLHttpRequest();
var msg = document.getElementsByClassName('msg');
var password = frm.password.value;

xhr.open('POST', '/project/hr/check.do');
xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

xhr.onreadystatechange = function() {
	if (xhr.readyState === XMLHttpRequest.DONE && xhr.status == 200) {
		var result = xhr.responseText;
		if (result > 0) {
			msg[0].innerHTML = '비밀번호가 일치하지 않습니다.(' + result + '회 틀림)';
		} 
	}
};

xhr.send('password=' + password);



