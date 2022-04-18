<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--   
====> EL / JSTL 적용
<%@ page import="com.model2.mvc.service.domain.Purchase" %>

<%
	Purchase purchase = (Purchase) request.getAttribute("purchase");
%>	
========
--%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>구매 내역 확인</title>
</head>
<body>

<form name="updatePurchase" action="/updatePurchaseView?tranNo=${purchase.tranNo}" method="post">

거래요청

<table border=1>

	<tr>
		<td>주문번호</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>거래요청 상품</td>
		<td>${purchase.purchaseProd.prodName}</td>
		<td>${purchase.tranNo}</td>
	</tr>
	<tr>
		<td>판매자 아이디</td>
		<td><a href="/getUser?userId=${purchase.purchaseProd.sellerId}">${purchase.purchaseProd.sellerId}</a></td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		
			${purchase.paymentOption}
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${purchase.divyMessage}</td>
		<td></td>
	</tr>
</table>
</form>


</body>
</html>