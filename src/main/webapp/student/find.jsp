<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>find</title>
    <style>
        #table{
            border: 1px black solid;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <jsp:include page = "/student/header.jsp"></jsp:include>

    <div>
    <h3> 조회 페이지 </h3>
    
<table id="table">
    <thead>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>국어</th>
            <th>수학</th>
            <th>날짜</th>
        </tr>
    </thead>
    <tbody id="studentTbody">
    </tbody>
</table>

</div>

    <button onclick=find()>전체 출력</button>

    <script src='/student/find.js'></script>
</body>
</html>