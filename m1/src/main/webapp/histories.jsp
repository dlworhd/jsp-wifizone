<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:forEach var="data" items="{histories}">
      <tr>
        <td>${data.id}</td>
        <td>${data.x}</td>
        <td>${data.y}</td>
        <td>${data.date}</td>
        <button>삭제</button>
      </tr>
    </c:forEach>
</tbody>

</body>
</html>
