<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h3>와이파이 정보 구하기</h3>

<div>
    <a href="index.html">홈</a>
    <a href="history.html">위치 히스토리 목록</a>
    <a href="importwifi.html">Open API 와이파이 정보 가져오기</a>
</div>

<div>
    <form action="data" method="post">
        LAT:<input type="text" id="LAT">
        LNT:<input type="text" id="LNT">
        <button type="button" id="location">내 위치 가져오기</button>
        <input type="submit" value="근처 WI-FI 정보 보기"/>
    </form>
</div>

<div>
    <table>
        <div>
            <tr>
                <th>거리
                </td>
                <th>관리번호</th>
                <th>자치구
                </td>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류
                </td>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>wifi접속환경</th>
                <th>X좌표
                </td>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>

        </div>
        <div>
        </div>
    </table>
</div>


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
        document.getElementById("LAT").value = x;
        document.getElementById("LNT").value = y;
    }
</script>


</body>
</html>