/**
 * 
 */
//로고 누르면 메인화면으로
var logo = document.getElementsByClassName('logo');
logo[0].addEventListener('click', function(event) {
	location.href = '/project/main.do'
});

//로그아웃 버튼
var button = document.getElementsByTagName('button');
var logoutButton = button[0];
logoutButton.addEventListener('click', function(event) {
	location.href = '/project/logout.do'
});
