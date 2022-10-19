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
    <title>History</title>
    <link rel="stylesheet" href="base.css">


</head>
<body>
<a href="index.jsp">홈</a>
<a href="history">위치 히스토리 목록</a>
<a href="load-wifi">Open API 와이파이 정보 가져오기</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>위도</th>
        <th>경도</th>
        <th>조회일자</th>
        <th>비고</th>
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
