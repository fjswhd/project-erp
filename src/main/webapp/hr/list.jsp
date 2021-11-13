<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 목록</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
	@import url('/project/css/hrList.css?23')
</style>
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
		document.getElementById('currLabel').setAttribute('style', 'background: #186343');
	}
</script>
</head>
<body>
	<div id="header"> 
		<!--========================= 추후 수정(여기부터) =========================-->
		<div class="logo"></div>
		<div class="user_info">${sessionScope.currentEmp.dept.deptName}팀 ${sessionScope.currentEmp.empName}님</div>
		<div class="logout_container">
			<button>로그아웃</button>
		</div>
		<!--========================= 추후 수정(여기까지) =========================-->
	</div>
	<div id="body_container">
		<div class="side_bar">
			<div>인 사</div>
			<div id="currLabel" class="label">사원 목록</div>
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
					<div class="label_name">사원 목록</div>
				</div>
				<div class="content_body">
					<table>
						<tr>
							<th>
								<div>사번</div>
								<div>사원명</div>								
								<div>부서명</div>								
								<div>전화번호</div>								
								<div>이메일</div>								
							</th>
						</tr>
						<c:forEach var="emp" items="${empList}" >
						<tr>
							<td>
								<div>${emp.empNo}</div>
								<div>${emp.empName}</div>
								<div>${emp.dept.deptName}</div>
								<div>${emp.empTel }</div>
								<div>${emp.empEmail }</div>
							</td>
						</tr>						
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/project/script/hrLabel.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>