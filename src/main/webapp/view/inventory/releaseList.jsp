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
<link href="/project/css/inventory/releaseList.css?1" rel="stylesheet" type="text/css">

<script type="text/javascript">
	window.onload = function() {
		var label = document.getElementsByClassName('label');
		label[3].setAttribute('style', 'background: #186343');

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
			<div class="label">재고 변동 내역</div>
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
					<form method="post" name="search"
						action="/project/st/outProductListSearch.do">
						<div class="search">
							<div>
								<input type="date" name="s_date" >
								<div> ~ </div>
								<input type="date" name="e_date" >
							</div>
							<div>
								<select name="searchField">
									<option value="0">선택</option>
									<option value="customer_no">업체코드</option>
									<option value="customer_name">업체명</option>
									<option value="product_no">상품코드</option>
									<option value="product_name">상품명</option>
								</select>
							</div>
							<div>
								<input type="text" name="keyword" style="width: 150px;">
							</div>
							<div>
								<button class="search_img" type="submit">
									<img src="/project/images/search.jpg" width="30">
								</button>
							</div>
						</div>
					</form>
					<table>
						<tr>
							<th>출고일</th>
							<th>업체코드</th>
							<th>업체명</th>
							<th>상품코드</th>
							<th>상품명</th>
							<th>출고단가</th>
							<th>수량</th>
						</tr>
						<c:if test="${empty outProductList }">
							<tr>
								<td>출고내역이 없습니다</td>
							</tr>
						</c:if>

						<c:if test="${not empty outProductList }">
							<c:forEach var="product" items="${outProductList}">
								<tr>
									<td>${product.sales_order_date}</td>
									<td>${product.customer_no }</td>
									<td>${product.customer_name }</td>
									<td>${product.product_no }</td>
									<td>${product.product_name }</td>
									<td>${product.price }</td>
									<td>${product.sales_detail_pcount }</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
	
					<div class="page">
						<a href="/project/inventory/releaseList.do?p=${p-5}">&lt;</a>
						<c:forEach begin="${firstPage}" end="${lastPage}" varStatus="vs">
							<c:if test="${p == vs.index}">
								<b><a href="/project/inventory/releaseList.do?p=${vs.index}">${vs.index}</a></b>
							</c:if>
							<c:if test="${p != vs.index}">
								<a href="/project/inventory/releaseList.do?p=${vs.index}">${vs.index}</a>
							</c:if>
						</c:forEach>
						<a href="/project/inventory/releaseList.do?p=${p+5}">&gt;</a>
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