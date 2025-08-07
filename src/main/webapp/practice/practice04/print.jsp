<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>Print</title>
    
</head>
<body>
    <jsp:include page = "/practice/practice04/header.jsp"></jsp:include>


    <h3>대기열 보기 화면 입니다.</h3>
    
        <button onclick="print()">대기열 보기</button>
    <table>
        <thead>
            <tr>
                <th>대기번호</th>
                <th>전화번호</th>
                <th>인원수</th>
            </tr>
        </thead>
        <tbody id="waitingTbody"></tbody>
    </table>




    <script src='print.js'></script>
</body>
</html>