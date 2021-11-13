<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
	@font-face {
    	font-family: 'CookieRunOTF-Bold';
    	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/CookieRunOTF-Bold00.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
</style>
<style type="text/css">
	@import url('./css/loginForm.css?6')
</style>
</head>
<body>
	<div class="loginBox"> 
		<div class="boxTitle">만만</div>
			<form action="/project/login.do" method="post">
				<div class="input">
					<div>
						<input type="text" name="empNo" required="required" autofocus="autofocus" placeholder="사번">
					</div>
					<div>
						<input type="password" name="password" required="required" placeholder="비밀번호">
					</div>
					<div>
						<input type="submit" value="로그인">
					</div>
				</div>
			</form>
		<div class="option">
			<a href="/project/initPassword.do">비밀번호를 잊으셨나요?</a>
		</div>
	</div>
	<div class="footer">
		ⓒ 만만 by project team no.3
	</div>
</body>
</html>