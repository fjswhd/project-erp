<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/sessionChk.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 현황</title>

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="/project/css/common/outline.css" rel="stylesheet" type="text/css">
<link href="/project/css/inventory/releaseList.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	window.onload = function() {
		var label = document.getElementsByClassName('label');
		label[2].setAttribute('style', 'background: #186343');

		var tool = document.getElementsByClassName('tool');
		tool[2].setAttribute('style','background: #f8f7f2; color: #000; box-shadow: 0 -0.15rem 0.15rem #808080; z-index: 1;');
	}
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="/common/header.jsp" />
	</div>
	<div id="body_container">
		<div class="side_bar">
			<div>재 고</div>
			<div class="label">재고 현황</div>
			<div class="label">입고 내역</div>
			<div class="label">출고 내역</div>
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
					<div class="label_name">출고 내역</div>
				</div>
				<div class="content_body">
					<table>
						<tr>
							<th>출고일</th>
							<th>업체코드</th>
							<th>업체명</th>
							<th>상품코드</th>
							<th>상품명</th>
							<th>판매단가</th>
							<th>판매수량</th>
							<th>담당자</th>
						</tr>
						<c:if test="${empty salesList}">
							<tr>
								<th colspan="8">출고내역이 없습니다</th>
							</tr>
						</c:if>
						<c:if test="${not empty salesList }">
							<c:forEach var="sales" items="${salesList}">
								<tr>
									<td>${sales.sales_order_date}</td>
									<td>${sales.seller_no}</td>
									<td>${sales.seller_name}</td>
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
							<a href='/project/inventory/releaseList.do?p=${startPage - 1}'>이전</a>
						</c:if>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${param.p == i}">
								<b><a href="/project/inventory/releaseList.do?p=${i}">${i}</a></b>
							</c:if>
							<c:if test="${param.p != i}">
								<a href="/project/inventory/releaseList.do?p=${i}">${i}</a>
							</c:if>
						</c:forEach>
						<!-- 	보여줄 것이 아직 남아있다 -->
						<c:if test="${endPage < totalPage}">
							<a href='/project/inventory/releaseList.do?p=${endPage + 1}'>다음</a>
						</c:if>
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