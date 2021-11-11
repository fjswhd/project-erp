<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<style type="text/css">
	@import url('/project/css/hr.css?22')
</style>

<script type="text/javascript">
	window.onload = function() {
		document.getElementById('currLabel').setAttribute('style', 'background: #768B5E');
	}
</script>
</head>
<body>
	<div id="header"> 
		<!--========================= 추후 수정(여기부터) =========================-->
		<div class="logo"></div>
		<div class="user_info">${emp.d_no}팀 ${emp.e_name}님</div>
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
				<div class="tool">구 매</div>
				<div class="tool">판 매</div>
				<div class="tool">재 고</div>
				<div class="tool">회 계</div>
				<div class="tool">인 사</div>
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
						<tr>
							<td>
								<div>21-00001</div>
								<div>이종민</div>
								<div>인사</div>
								<div>010-9052-1980</div>
								<div>fjswhd93@gmail.com</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>21-00001</div>
								<div>이종민</div>
								<div>인사</div>
								<div>010-9052-1980</div>
								<div>fjswhd93@gmail.com</div>
							</td>
						</tr>
						<tr>
							<td>
								<div>21-00001</div>
								<div>이종민</div>
								<div>인사</div>
								<div>010-9052-1980</div>
								<div>fjswhd93@gmail.com</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/project/script/label.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>