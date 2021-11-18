<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/sessionChk.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 수정</title>

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="/project/css/outline.css?1" rel="stylesheet">
<link href="/project/css/hrCheck.css?8" rel="stylesheet">

<style type="text/css">
	@font-face {
		font-family: 'paybooc-Medium';
    	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Medium.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
	@font-face {
    	font-family: 'paybooc-Bold';
    	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Bold.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
</style>
<script type="text/javascript">
	window.onload = function() {
		var label = document.getElementsByClassName('label');
		label[1].setAttribute('style', 'background: #186343');
	
		var tool = document.getElementsByClassName('tool');
		tool[4].setAttribute('style', 'background: #f8f7f2; color: #000; box-shadow: 0 -0.15rem 0.15rem #505050; z-index: 1;');
		/* tool[4].style.background = '#f8f7f2';
		tool[4].style.color = '#000';
		tool[4].style['box-shadow'] = '0 -0.15rem 0.15rem #505050';
		tool[4].style['z-index'] = '1'; */
	}
	
	function chk() {
		var xhr = new XMLHttpRequest();
		var msg = document.getElementsByClassName('msg');
		var password = frm.password.value;
		
		if (password == null || password === '') {
			msg[0].style.color = '#000';
			msg[0].innerHTML = '비밀번호를 입력하세요.';
			frm.password.focus();
			return false;
		}
		
		var cnt = parseInt(frm.cnt.value);
		frm.cnt.value = cnt + 1;
		
		xhr.open('POST', '/project/hr/check.do');
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status == 200) {
				var result = xhr.responseText;
				if (result > 0) {
					if (result == 6) {
						location.href = '/project/hr/init.do';						
					} else {
						msg[0].style.color = '#ff0000';
						msg[0].innerHTML = '비밀번호가 일치하지 않습니다.(' + frm.cnt.value + '회 틀림)';						
					}
				} else if (result == 0) {
					location.href = '/project/hr/updateForm.do';
				} 
				
			}
		};
		
		xhr.send('password=' + password); 
	}
	
	
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/project/script/postAddress.js"></script>

</head>
<body>
	<div id="header"> 
		<div class="logo"></div>
		<div class="user_info">${sessionScope.Hr.dept_name}팀 ${sessionScope.Hr.emp_name}님</div>
		<div class="logout_container">
			<button>로그아웃</button>
		</div>
	</div>
	<div id="body_container">
		<div class="side_bar">
			<div>인 사</div>
			<div class="label">사원 목록</div>
			<div class="label">정보 수정</div>
			<div class="label">사원 등록</div>
		</div>
		<div class="body">
			<div class="toolbar">
				<div class="tool">구매</div>
				<div class="tool">판매</div>
				<div class="tool">재고</div>
				<div class="tool">회계</div>
				<div class="tool">인사</div>
				<div></div>
			</div>
			<div class="content">
				<div class="content_head">
					<div class="label_name">정보 수정</div>
				</div>
				<div class="content_body">
					<div class="message">
						본인 확인을 위해 한 번 더 비밀번호를 입력해주세요. <br>
						5회 이상 비밀번호를 틀릴 시 계정의 비밀번호가 초기화 되고 <br>
						초기화된 비밀번호가 계정에 입력된 이메일로 발송됩니다. <br>
					</div>
					<form action="/project/hr/updateForm.do" name="frm" method="post">
					<input type="hidden" name="cnt" value="0">
						<table>
							<tr>
								<th>비밀번호</th>
								<td><input type="password" name="password" required="required" /></td>
							</tr>
							<tr>
								<td>
									<div class="msg"></div>
									<input type="button" value="확인" onclick="chk()">
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
<!-- 	<script type="text/javascript" src="/project/script/postAddress.js"></script> -->
	<script type="text/javascript" src="/project/script/header.js"></script>
	<script type="text/javascript" src="/project/script/hrLabel.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>