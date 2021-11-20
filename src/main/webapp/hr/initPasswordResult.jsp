<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		var result = '${result}';
		var nextURL = '${nextURL}';
		if (result == 0) {
			alert('계정 비밀번호 초기화에 실패했습니다.\r\n인사팀에 문의해주세요.');
			location.href = '/project/login/loginForm.jsp';	
		} else if (result == -1) {
			alert('잘못된 접근입니다.\r\n먼저 로그인해주세요.');
			location.href = '/project/login/loginForm.jsp';	
		} else {
			alert('계정 비밀번호가 초기화되었습니다.\r\n이메일을 확인해주세요.');
			location.href = 'http://' + nextURL;	
		}
	</script>
</body>
</html>