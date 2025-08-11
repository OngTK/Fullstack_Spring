<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Update</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>

    <h3>게시물 수정</h3>
    <div>번호 <input type="number"></div>
    <div>내용 <input type="textarea"></div>
    <button onclick="boardUpdate()">수정하기</button>

    
</body>
</html>