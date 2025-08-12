<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>대기열 홈 페이지</title>
</head>
<body>
    <jsp:include page = "/waiting/header.jsp"></jsp:include>

    <h3>대기 정보 수정하기</h3>

    <div>대기번호<input type="number" id = "wno" disabled></div>
    <div>연락처<input type="text" id = "phone"></div>
    <div>인원수<input type="number" id = "count"></div>
    <div>등록 일시<input type="text" id = "wdate" disabled></div>

    <button onclick="waitingUpdate()">수정하기</button>

    <script src = "/waiting/update.js"></script>

    
</body>
</html>