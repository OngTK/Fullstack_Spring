<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>대기열 홈 페이지</title>
</head>
<body>
    <jsp:include page = "/waiting/header.jsp"></jsp:include>

    
    <h3>대기열 등록</h3>
    <div>전화번호<input type="text" id="phone" placeholder="010-0000-0000"></div>
    <div>인원수<input type="text" id="count"></div>
    <button onclick="waitingWrite()">등록하기</button>

    <script src="/waiting/write.js"></script>
    
</body>
</html>