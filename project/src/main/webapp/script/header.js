/**
 * 
 */
var logo = document.getElementsByClassName('logo');
logo[0].addEventListener('click', function(event) {
	location.href = '/project/main.do'
});

var button = document.getElementsByTagName('button');
var logoutButton = button[0];
logoutButton.addEventListener('click', function(event) {
	location.href = '/project/logout.do'
});
