<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입고 내역</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<link href="/project/css/outline.css?10" rel="stylesheet"
	type="text/css">
<link href="/project/css/inOutStockList.css?24" rel="stylesheet"
	type="text/css">


<script type="text/javascript">
	window.onload = function() {
		document.getElementById('currLabel').setAttribute('style',
				'background: #186343');
		window.onload = function() {
			var label = document.getElementsByClassName('label');
			label[0].setAttribute('style', 'background: #186343');

			var tool = document.getElementsByClassName('tool');
			tool[4]
					.setAttribute(
							'style',
							'background: #f8f7f2; color: #000; box-shadow: 0 -0.15rem 0.15rem #505050; z-index: 1;');
		}
	};
</script>
</head>
<body>
	<div id="header">
		<!--========================= 추후 수정(여기부터) =========================-->
		<div class="logo"></div>
		<div class="user_info">${sessionScope.Hr.dept_name}팀
			${sessionScope.Hr.emp_name}님</div>
		<div class="logout_container">
			<button>로그아웃</button>
		</div>
		<!--========================= 추후 수정(여기까지) =========================-->
	</div>
	<div id="body_container">
		<div class="side_bar">
			<!--========================= 이 부분이 달라짐(여기부터) =========================-->
			<div>재고</div>
			<div class="label">재고 현황</div>
			<div id="currLabel" class="label">입고 내역</div>
			<div class="label">출고 내역</div>
			<div class="label">재고 변동 내역</div>


			<!--========================= 이 부분이 달라짐(여기까지) =========================-->
		</div>
		<div class="body">
			<div class="toolbar">
				<div class="tool">구매</div>
				<div class="tool">판매</div>
				<div class="tool">재고</div>
				<div class="tool">회계</div>
				<div class="tool">인사</div>
				<div class="tool"></div>
			</div>
			<div class="content">
				<div class="content_head">
					<div class="label_name">입고 내역</div>
				</div>
				<div class="content_body">
					<!--========================= 이 부분이 달라짐(여기부터) =========================-->
					<form method="post" name="search"
						action="/project/st/inProductListSearch.do">
						<div class="search">
							<div>
								<input type="date" name="s_date" >
								<div> ~ </div>
								<input type="date" name="e_date" >
							</div>
							<div>
								<select name="searchField">
									<option value="0">선택</option>
									<option value="seller_no">업체코드</option>
									<option value="seller_name">업체명</option>
									<option value="product_no">상품코드</option>
									<option value="product_name">상품명</option>
								</select>
							</div>

							<div>
								<input type="text" name="keyword" style="width: 150px;">
							</div>

							<div>
								<button class="search_img" type="submit">
									<img src="../images/search.jpg" width="30">
								</button>

							</div>
						</div>
					</form>
					<table>
						<tr>
							<th>입고일</th>
							<th>업체코드</th>
							<th>업체명</th>
							<th>상품코드</th>
							<th>상품명</th>
							<th>입고단가</th>
							<th>수량</th>
						</tr>
						<c:if test="${empty inProductList }">
							<tr>
								<th colspan="7">입고내역이 없습니다</th>
							</tr>
						</c:if>

						<c:if test="${not empty inProductList }">
							<c:forEach var="product" items="${inProductList}">
								<tr>
									<td>${product.purchase_order_date}</td>
									<td>${product.seller_no }</td>
									<td>${product.seller_name }</td>
									<td>${product.product_no }</td>
									<td>${product.product_name }</td>
									<td>${product.cost }</td>
									<td>${product.purchase_detail_pcount}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>

					<div class="page">
						<a href="/project/st/inProductList.do?pageNum=${startPage - 1}">&lt;</a>
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${pageNum == i}">
								<b><a href="/project/st/inProductList.do?pageNum=${i}'">${i }</a></b>
							</c:if>
							<c:if test="${pageNum != i}">
								<a href="/project/st/inProductList.do?pageNum=${i}">${i}</a>
							</c:if>
						</c:forEach>
						<a href="/project/st/inProductList.do?pageNum=${endPage + 1}">&gt;</a>
					</div>


					<!--========================= 이 부분이 달라짐(여기까지) =========================-->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/project/script/header.js"></script>
	<script type="text/javascript" src="/project/script/stockLabel.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>