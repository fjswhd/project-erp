<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	/* var isChkedId = 0;	//아직 중복검사를 통과 못 함, submit하기전에 이 값을 확인해서 아이디 체크를 했는지 안했는지 확인
	var chkId = function() {
		var user_id = frm.user_id.value;
		var msg = document.getElementsByClassName('msg');
		
		if(user_id == null || user_id.trim() === '') {
			msg[0].innerHTML = '아이디를 입력하세요.';
			isChkedId = 0;
			return;
		}
		
		var xhr = new XMLHttpRequest();
		
		xhr.open('post', '/board_with_user/user/checkId', true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.send('user_id='+user_id);
		
		xhr.addEventListener('load', function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				var result = xhr.responseText;
				if (result == 0) {	//사용가능한 아이디
					msg[0].innerHTML = '사용 가능한 아이디입니다.';
					isChkedId = 1;	//아이디 확인 완료
				} else {
					msg[0].innerHTML = '중복된 아이디입니다.';
					isChkedId = 0;
				}
			}
		});
	};
	
	var isChkedPass = 0;	//비밀번호 검사 통과 못 함
	var chkPass = function() {
		var msg = document.getElementsByClassName('msg');
		if (frm.password.value == frm.password2.value) { //두 비밀번호가 일치하면
			msg[1].innerHTML = '비밀번호가 일치합니다.'; 
			isChkedPass = 1;	//비밀번호 검사 통과
		} else { 	//비밀번호가 일치하지 않으면
			msg[1].innerHTML = '비밀번호가 일치하지 않습니다.';
			frm.password.focus();
			isChkedPass = 0;
		}
	};
	
	var lastChk = function() {
		if (isChkedId == 0) {
			alert('아이디를 확인을 해주세요.');
			frm.user_id.focus();
			return false;
		} else if (isChkedPass == 0) {
			alert('비밀번호와 비밀번호 확인이 일치하지 않습니다. \r\n비밀번호를 확인해 주세요.');
			frm.password.focus();
			return false;
		}
	}
	
	var goBack = function() {
		if (confirm('회원 가입을 취소하시겠습니까?')) 
			history.go(-1);
	}; */
</script>
<style type="text/css">
	@import url('./userCss/joinForm.css?1')	
</style>
</head>
<body>
	<form name="frm" action="/board_with_user/user/join" method="post">
		<div id="main">
		<div class="title">회원 가입</div>
			<table>
				<tr>
					<td>
						<div class="head">아이디</div>
						<div class="body"><input type="text" name="user_id" required="required" autofocus="autofocus" onchange="chkId()"></div>
						<div class="msg"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">비밀번호</div>
						<div class="body"><input type="password" name="password" required="required"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">비밀번호 확인</div>
						<div class="body"><input type="password" name="password2" required="required" onchange="chkPass()"></div>
						<div class="msg"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">이름</div>
						<div class="body"><input type="text" name="name" required="required"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">주소</div>
						<div class="body"><input type="text" name="address" required="required"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">이메일</div>
						<div class="body"><input type="email" name="email" required="required"></div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="head">전화번호</div>
						<div class="body"><input type="tel" name="tel" pattern="\d{3}-\d{3,4}-\d{4}" placeholder="010-0000-0000" required="required"></div>
						<div class="msg"></div>
					</td>
				</tr>
				<tr>
					<th>
						<div class="tail">
							<input type="submit" value="가입하기" onclick="return lastChk()"/>
							<input type="button" value="취소" onclick="goBack()"/>
						</div>
					</th>
				</tr>
			</table>
		</div>	
	</form>
</body>
</html>