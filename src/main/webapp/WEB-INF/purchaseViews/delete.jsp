<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title></head><body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제 되었습니다");
		location.href = "sellerList.do?pageNum=${pageNum}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("삭제 실패 !!!");
		history.back();
	</script>
</c:if>
<c:if test="${result < 0 }">
	<script type="text/javascript">
		alert("화면에서 읽은 구매처 번호와 데이터베이스에서 읽은 구매처 번호가 다릅니다.");
		history.back();
	</script>
</c:if>
</body>
</html>