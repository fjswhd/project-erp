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
		var result = '${result}'
		if (result == 0) {
			alert('사원 등록 실패');
			location.href = '/project/hr/list.do';
		} else {
			alert('사원 등록 성공');
			location.href = '/project/hr/list.do';
		}
	</script>
</body>
</html>