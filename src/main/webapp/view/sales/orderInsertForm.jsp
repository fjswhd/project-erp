<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/common/sessionChk.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매 등록</title>

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
<link href="/project/css/common/outline.css" rel="stylesheet" type="text/css">
<link href="/project/css/sales/orderInsertForm.css?3" rel="stylesheet" type="text/css">

<script type="text/javascript">
	window.history.forward();
	
	window.onload = function() {
		var label = document.getElementsByClassName('label');
		label[3].setAttribute('style', 'background: #186343');

		var tool = document.getElementsByClassName('tool');
		tool[1].setAttribute('style','background: #f8f7f2; color: #000; box-shadow: 0 -0.15rem 0.15rem #808080; z-index: 1;');
		
		document.getElementById('customerSearch').onclick = function() {
			window.open('/project/sales/customerSearch.do', '업체검색', 'width=500, height=600, scrollbars=no, resizable=yes');
		}
	
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
					<div class="label_name">판매 등록</div>
				</div>
				<div class="content_body">
					<form action="/project/sales/orderInsert.do" method="post" name="frm" onsubmit="return submitChk()">
					<table>
		<!-- //판매주문일, 주문번호, 판매처코드, 판매처명, 상품코드, 상품명, 판매가, 매입가, 현재 재고량, 주문수량, 주문 등록 사원번호 -->			
						<tr>
							<th>주문번호</th>
							<td>${sales_order_no}<input type="hidden" name="sales_order_no" required="required" value="${sales_order_no}" /></td>
						</tr>
						<tr>
							<th>판매업체코드</th>
							<td>
								<input type="text" name="customer_no" required="required" />
								<input id="customerSearch" type="button" value="업체검색">
							</td>
							
						</tr>
						<tr>
							<th>판매업체명</th>
							<td><input type="text" name="customer_name" required="required" /></td>
						</tr>
						<tr>
							<th>상품코드</th>
							<td>
								<input type="text" name="product_no" required="required" readonly="readonly" />
								<input id="productSearch" type="button" value="상품검색">
							</td>
						</tr>
						<tr>
							<th>상품명</th>
							<td><input type="text" name="product_no" required="required" readonly="readonly" /></td>
						</tr>
						<tr>
							<th>판매단가</th>
							<td><input type="number" name="price" required="required" readonly="readonly" /></td>
						</tr>
						<tr>
							<th>주문수량</th>
							<td><input type="number" name="sales_order_pcount" required="required" /></td>
						</tr>
						<tr>
							<th>판매 가능 수량</th>
							<td><input type="number" name="stock" required="required" readonly="readonly" value="0" /></td>
						</tr>
						<tr>
							<th>담당자</th>
							<td>
								${Hr.emp_no} / ${Hr.dept_name}팀 ${Hr.emp_name}
								<input type="hidden" name="emp_no" required="required" value="${Hr.emp_no}" /> 
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="등록"></td>
						</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/project/script/header.js"></script>
	<script type="text/javascript" src="/project/script/label.js"></script>
	<script type="text/javascript" src="/project/script/toolbar.js"></script>
</body>
</html>