<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Delete</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>


    <h3>게시물 삭제</h3>
    <div>번호 <input type="number"></div>
    <button onclick="boardDelete()">삭제하기</button>

</body>
</html>