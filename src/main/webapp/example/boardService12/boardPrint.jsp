<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Board Print</title>
</head>
<body>
    <jsp:include page = "/example/boardService12/header.jsp"></jsp:include>

    <h3>게시물 전체 조회</h3>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>작성자</th>
                    <th>내용</th>
                </tr>
            </thead>
            <tbody id="boardTbody">

            </tbody>
        </table>  
    </div>
    <button onclick="boardPrint()">조회하기</button>
    
</body>
</html>