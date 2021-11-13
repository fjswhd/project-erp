<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css">
</head><body>
<table><caption>게시글 목록</caption>
	<tr><th>구매처 코드</th><th>사업자등록번호</th><th>업체명</th><th>전화</th><th>주소</th>
		<th>담당자</th><th>이메일</th><th>참고사항</th></tr>
<c:if test="${empty sellerList }">
	<tr><th colspan="8">등록된 구매처가 없습니다</th></tr>
</c:if>
<c:if test="${not empty sellerList }">
	<c:forEach var="board" items="${sellerList }">		
		<tr>
		<c:if test="${board.seller_del == 'y' }">			
		</c:if>
		<c:if test="${board.seller_del != 'y' }">
		 
			<td>
<!-- 		 기존에 있는 정보를 가지고 등록 페이지로 이동 			-->
				<a href="updateForm.do?seller_no=${board.seller_no}">
				${board.seller_no}</a>
			</td>
			<td>${board.seller_reg_num }</td>
			<td>${board.seller_name }</td>
			<td>${board.seller_tel }</td>
			<td>${board.seller_address}</td>
			<td>${board.emp_no}</td>
			<td>${board.seller_email}</td>
			<td>${board.seller_memo}</td>
			<td><a href="deleteForm.do?seller_no=${board.seller_no}">
			<input type="button" value="삭제" /></a></td>
			
		</c:if>
		</tr>
	</c:forEach>
</c:if>
</table>
<div align="center">
	<c:if test="${startPage > PAGE_PER_BLOCK }">
		<button onclick="location.href='sellerList.do?pageNum=${startPage - 1}'">이전</button>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<button onclick="location.href='sellerList.do?pageNum=${i}'">${i }</button>
	</c:forEach>
<!-- 	보여줄 것이 아직 남아있다 -->
	<c:if test="${endPage < totalPage}">
		<button onclick="location.href='sellerList.do?pageNum=${endPage + 1}'">다음</button>
	</c:if>
	<br><button onclick="location.href='writeForm.do?pageNum=1&emp_no=21-00001'">구매처 등록</button>
<!-- 해야됨!!!!!!!!!!!! 데이터에 저장된 담당자가 아니라, 현재 로그인한 세션의 아이디(emp_no)가 들어가야함.  -->
<!-- 각 구매처 옆에 같은 구매처, 상품만 다른 버튼 하나 추가하면 좋을듯 -->
</div>
</body>
</html>