<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Find</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>

    <h3>게시물 상세 조회</h3>
    <div>번호 <input type="number" id = "bno" disabled></div>
    <div>작성자 <input type="text"  id="bwriter" disabled></div>
    <div>내용 <textarea id="bcontent" disabled></textarea></div>

    <button onclick="boardUpdateView()">수정하기</button>
    <button onclick="boardDelete()">삭제하기</button>

    <script src="./view.js"></script>
    
</body>
</html>