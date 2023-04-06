<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
  <a href="/init.do">식품매대 준비</a><br>
  <a href="/foods.do">상품 목록</a><br>
  <a href="/cart.do">장바구니 화면</a><br>
<c:set var="money" value="${sessionScope.get('money')}" />
<p>잔액: ${money}</p>
</body>
</html>