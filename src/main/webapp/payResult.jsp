<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/06
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payResult</title>
</head>
<body>
<p>결제금액: ${requestScope.get("payCost")}</p>
<p>잔액: ${sessionScope.get('money')}</p>
<a href="/cart.do">장바구니에서 더 결제하기</a><br>
<a href='/index.jsp'>main</a>
</body>
</html>
