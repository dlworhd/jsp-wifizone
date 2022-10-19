<%--
  Created by IntelliJ IDEA.
  User: ejay
  Date: 2022/10/15
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>X좌표</td>
        <td>Y좌표</td>
        <td>조회일자</td>
        <td>비고</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="data" items="${histories}">
        <tr>
            <td>${data.id}</td>
            <td>${data.lat1}</td>
            <td>${data.lnt1}</td>
            <td>${data.date}</td>
            <td>
                <button type="button" onclick="location.href='/remove?id=${data.id}' ">삭제</button>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
