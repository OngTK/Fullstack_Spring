<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>save</title>
</head>
<body>
    <jsp:include page = "/student/header.jsp"></jsp:include>

    <h3> 저장 페이지 </h3>
    <div>
        이름 :     <input type="text" class="sname"> <br/>
        국어점수 : <input type="number" class="skor"><br/>
        수학점수 : <input type="number" class="smath"><br/> 
    </div>


    <button onclick=save()>저장</button>



    <script src='/student/save.js'></script>
</body>
</html>