<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	* {
		margin: 0;
		padding: 0;
		font-size: 10px;
	}
	div {
		display: flex;
	}@font-face {
    	font-family: 'GmarketSansMedium';
    	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
	
	.container {
		background: #f8f7f2;
		border-radius: 0.8rem;
		flex-direction: column;
		margin: auto;
		padding: 2rem;
		padding-top: 1rem;
		width: 60rem;
		justify-content: flex-start;
	}
	.logo {
		display: flex;
		background-image: url('http://localhost:8080/project/images/logo4.jpg');
		background-size: cover;
		margin-bottom: 1rem;
		width: 25rem;
		height: 12.5rem;
	}
	.msg {
		flex-direction: column;
		font-family: 'GmarketSansMedium', sans-serif;
		font-size: 2.4rem;
	}
	.password {
		background: #fff;
		border-radius: 0.8rem;
		align-self: center;
		justify-content: center;
		align-items: center;
		margin: 0 2rem;
		width: 40rem;
		height: 6rem;
		font-size: 3.2rem;
		color: #f4a261;
	}
	a {
		font-size: 1.6rem;
	}
	a:link, a:visited {
		color: blue;
	}
</style>
</head>
<body>
	<table cellpadding="0" cellspacing="0" border="0" style="font-family: 'GmarketSansMedium', sans-serif; background: #f8f7f2; border-radius: 0.8rem;" width="600">
		<tr>
			<td style="padding-left: 20px;">
				<img alt="" src="https://raw.githubusercontent.com/fjswhd/project-erp/fjswhd/src/main/webapp/images/logo4.jpg" width="200">
			</td>
		</tr>
		<tr>
			<td style="padding-left: 20px; font-size: 24px;">
				귀하의 계정 비밀번호가 초기화되었습니다.<br>
				새로운 비밀번호는
			</td>
		</tr>
		<tr>
			<td align="center">
				<table cellpadding="0" cellspacing="0" border="0" style="background: #ffffff; border-radius: 0.8rem;" width="400">
					<tr>
						<td align="center" style="font-size: 32px; color: #f4a261;">
							새로운 비밀번호
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="padding-left: 20px; font-size: 24px">
				입니다.
			</td>
		</tr>
		<tr>
			<td style="padding-left: 20px; font-size: 24px">
				<a  href="http://localhost:8080/project/loginForm.do">로그인 페이지 바로가기</a>
			</td>
		</tr>
	</table>
</body>
</html>