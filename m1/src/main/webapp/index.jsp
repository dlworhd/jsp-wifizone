<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3>와이파이 정보 구하기</h3>

<a href="index.jsp">홈</a>
<a href="history">위치 히스토리 목록</a>
<a href="load-wifi">Open API 와이파이 정보 가져오기</a>

<form action="/aroundwifi" method="get">
    LAT:<input type="text" id="lat1" name="lat1">
    LNT:<input type="text" id="lnt1" name="lnt1">
    <button type="button" id="location">내 위치 가져오기</button>
    <input type="submit" value="근처 WI-FI 정보 보기"/>
</form>

<table>
    <thead>
    <tr>
        <th>거리</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>wifi접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="data" items="${datalist}">
        <tr>
            <td>${data.dist}</td>
            <td>${data.mgrNum}</td>
            <td>${data.fc}</td>
            <td>${data.mainNum}</td>
            <td>${data.add1}</td>
            <td>${data.add2}</td>
            <td>${data.instlFloor}</td>
            <td>${data.instlTy}</td>
            <td>${data.instlMby}</td>
            <td>${data.svc}</td>
            <td>${data.cmcwr}</td>
            <td>${data.cstcYear}</td>
            <td>${data.inoutDoor}</td>
            <td>${data.remarS3}</td>
            <td>${data.lat2}</td>
            <td>${data.lnt2}</td>
            <td>${data.workDttm}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<script>


    const button = document.getElementById("location");
    button.addEventListener('click', getUserLocation);

    function getUserLocation() {
        if (!navigator.geolocation) {
            throw "위치 정보가 지원되지 않습니다.";
        }
        navigator.geolocation.getCurrentPosition(success);
    }


    function success({coords, timestamp}) {
        const latitude = coords.latitude; // 위도
        const longitude = coords.longitude; // 경도

        input_text(latitude, longitude);
    }

    function input_text(x, y) {
        document.getElementById("lat1").value = x;
        document.getElementById("lnt1").value = y;
    }
</script>


</body>
</html>