<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Write</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>

    <h3>게시물 등록</h3>
    <div>내용 <textarea id="bcontent"></textarea></div>
    <div>작성자 <input type = text id="bwriter"></div>
    <button onclick="boardWrite()">등록하기</button>

    <script src="./boardWrite.js"></script>
    
</body>
</html>