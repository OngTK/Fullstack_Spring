<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Find</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>

    <h3>특정 게시물 조회</h3>
    <div>번호 <input type="number" id = "bno"></div>
    <button onclick="boardFind()">조회하기</button>

    <div>작성자 <input type="text" disabled id="bwriter"></div>
    <div>내용 <textarea disabled id="bcontent"></textarea></div>

    <script src="./boardFind.js"></script>
    
</body>
</html>