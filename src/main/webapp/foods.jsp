<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eora21
  Date: 2023/04/04
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>foods</title>
</head>
<body>
<h1>상품목록</h1>
<form method='post' action='/cart.do'>
    <c:forEach var="food" items="${foods}">
        <p>${food} <input type="text" name="${food}"></p>
    </c:forEach>
    <input type="submit" value="전송"/>
</form>
</body>
</html>
