<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">@import url("common.css");</style>
<script type="text/javascript">
function chk() {
	if (frm.seller_no.value == "" || frm.seller_no.value == null ||
		frm.seller_reg_num.value == "" || frm.seller_reg_num.value == null ||
		frm.seller_name.value == "" || frm.seller_name.value == null ||
		frm.seller_tel.value == "" || frm.seller_tel.value == null ||
		frm.seller_address.value == "" || frm.seller_address.value == null ||
		frm.seller_email.value == "" || frm.seller_email.value == null ||
		frm.seller_memo.value == "" || frm.seller_memo.value == null ) 
	{alert("모든 정보를 입력해주세요.");			return false;}
	else {var returnValue = confirm("등록하시겠습니까?");		return returnValue;}
}
</script></head><body>
<form action="write.do" method="post" name="frm" onsubmit="return chk()">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="emp_no" value="${emp_no}" />
<table><caption>구매처 등록</caption>
	<tr><th>사업자 번호</th><td><input type="text" name="seller_reg_num" /></td></tr>
	<tr><th>업체명</th><td><input type="text" name="seller_name" /></td></tr>
	<tr><th>전화</th>	<td><input type="tel" name="seller_tel" /></td></tr>
	<tr><th>주소</th><td><input type="text" name="seller_address" /></td></tr>
	<tr><th>담당자</th><td>${emp_no}</td></tr>
	<tr><th>이메일</th><td><input type="email" name="seller_email" /></td></tr>
	<tr><th>참고사항</th><td><input type="text" name="seller_memo" /></td></tr>
	<tr><th colspan="2"><input type="submit" value="확인"></th></tr>
</table>
</form>
<!-- 같은 구매처, 상품만 다른 버튼 하나 더 만들면 좋을듯 -->
</body>
</html>