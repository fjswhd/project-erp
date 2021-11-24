<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/sessionChk.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 내역</title>

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="/project/css/common/outline.css" rel="stylesheet" type="text/css">
<link href="/project/css/sales/customerList.css?1" rel="stylesheet" type="text/css">

<script type="text/javascript">
	window.onload = function() {
		var label = document.getElementsByClassName('label');
		label[2].setAttribute('style', 'background: #186343');

		var tool = document.getElementsByClassName('tool');
		tool[1].setAttribute('style','background: #f8f7f2; color: #000; box-shadow: 0 -0.15rem 0.15rem #808080; z-index: 1;');
	}
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="/common/header.jsp" />
	</div>
	<div id="body_container">
		<div class="side_bar">
			<div>판 매</div>
			<div class="label">판매처 목록</div>
			<div class="label">판매처 등록</div>
			<div class="label">판매 내역</div>
			<div class="label">판매 등록</div>
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
					<div class="label_name">판매 내역</div>
				</div>
				<div class="content_body">
					<table>
						<tr>
							<th>판매일</th>
							<th>업체코드</th>
							<th>업체명</th>
							<th>상품코드</th>
							<th>상품명</th>
							<th>출고단가</th>
							<th>수량</th>
							<th>담당자</th>
						</tr>
						<c:if test="${empty salesList}">
							<tr>
								<th colspan="8">등록된 판매처가 없습니다</th>
							</tr>
						</c:if>

						<c:if test="${not empty salesList }">
							<c:forEach var="sales" items="${salesList }">
								<tr>
									<td>${sales.sales_order_date }</td>
									<td>${sales.customer_no }</td>
									<td>${sales.customer_name }</td>
									<td>${sales.product_no}</td>
									<td>${sales.product_name}</td>
									<td>${sales.price}</td>
									<td>${sales.sales_detail_pcount}</td>
									<td>${sales.emp_name}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					<div class="page">
						<c:if test="${startPage > PAGE_PER_BLOCK }">
							<button onclick="location.href='customerList.do?pageNum=${startPage - 1}'">이전</button>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${param.p == i}">
								<b><a href="/project/sales/orderList.do?p=${i}">${i}</a></b>
							</c:if>
							<c:if test="${param.p != i}">
								<a href="/project/sales/orderList.do?p=${i}">${i}</a>
							</c:if>
						</c:forEach>
						<!-- 	보여줄 것이 아직 남아있다 -->
						<c:if test="${endPage < totalPage}">
							<button
								onclick="location.href='customerList.do?pageNum=${endPage + 1}'">다음</button>
						</c:if>
						<!-- 해야됨!!!!!!!!!!!! 데이터에 저장된 담당자가 아니라, 현재 로그인한 세션의 아이디(emp_no)가 들어가야함.  -->
						<!-- 각 구매처 옆에 같은 구매처, 상품만 다른 버튼 하나 추가하면 좋을듯 -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="/project/script/header.js"></script>
	<script type="text/javascript" src="/project/script/label.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>