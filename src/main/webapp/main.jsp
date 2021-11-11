<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
	@import url('/project/css/main.css?22')
</style>
<script type="text/javascript">
	var isLoggedin = '${sessionScope.currentEmp}';
	
	if (isLoggedin == null || isLoggedin == '') {
		alert('로그인하지 않은 상태입니다. \n먼저 로그인 해 주세요.');
		location.replace('/project/loginForm.do');
	}
</script>
</head>
<body>
	<div class="container">
		<div class="logo">
			<img alt="" src="/project/images/logo.jpg" width="300">
		</div>
		<div class="dept_icon_container">
			<div class="icon_box">
				<div class="icon">
					<img alt="" src="/project/images/sell.jpg"> 
				</div>
				<div class="dept_name">
					<button>판 매</button>
				</div>
			</div>
			<div class="icon_box">
				<div class="icon">
					<img alt="" src="/project/images/buy.jpg"> 
				</div>
				<div class="dept_name">
					<button>구 매</button>
				</div>
			</div>
			<div class="icon_box">
				<div class="icon">
					<img alt="" src="/project/images/box.jpg"> 
				</div>
				<div class="dept_name">
					<button>재 고</button>
				</div>
			</div>
			<div class="icon_box">
				<div class="icon">
					<img alt="" src="/project/images/cal.jpg">	
				</div>
				<div class="dept_name">
					<button>회 계</button>
				</div>
			</div>
			<div class="icon_box">
				<div class="icon">
					<img alt="" src="/project/images/hr.jpg">
				</div>
				<div class="dept_name">
					<button onclick="location.href='/project/hr/list.do'">인 사</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>