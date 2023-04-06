<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/04
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cart</title>
</head>
<body>
<h1>장바구니 목록</h1>
<c:forEach var="food" items="${sessionScope.get('cart').entrySet()}">
    <c:set var="cost" value="${food.getKey().getCost() * food.getValue()}"/>
    <form action="/pay.do" method="post">
        <span>${food.getKey().getName()} ${food.getValue()} 금액: ${cost}</span>
        <input type="hidden" name="foodName" value="${food.getKey().getName()}">
        <input type="hidden" name="cost" value="${cost}">
        <button type="submit">결제</button>
    </form>
</c:forEach>
<p>전체 금액: ${sessionScope.get('sum')}</p>
<p>잔액: ${sessionScope.get('money')}</p>
<a href="index.jsp">메인화면으로</a>
</body>
</html>
