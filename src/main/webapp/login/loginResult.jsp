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
		var name = '${sessionScope.Hr.emp_name}';
		if (result == 0) {
			alert('존재하지 않는 사번입니다.');
			history.go(-1);
		} else if (result == -1) {
			alert('잘못된 비밀번호입니다.');
			history.go(-1);			
		} else {
			alert(name + '님 환영합니다.');
			location.replace('/project/main.do');
		}
	</script>
</body>
</html>