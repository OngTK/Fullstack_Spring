<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Regi</title>
    
</head>
<body>
    <jsp:include page = "/practice/practice04/header.jsp"></jsp:include>


    <h3>대기열 등록 화면 입니다.</h3>

    <div>
        <div>
            전화번호 <input type="text" placeholder="010-0000-0000" id="phone">
        </div>
        <div>
            인원수 <input type="number" id="count">
        </div>
    </div>

    <button onclick="regi()">대기열 등록</button>

    <script src='regi.js'></script>
</body>
</html>