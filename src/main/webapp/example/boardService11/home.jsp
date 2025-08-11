<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>Home</title>
</head>

<body>
    <jsp:include page = "/example/header.jsp"></jsp:include>
        
    http://localhost:8080/example/home.jsp


    <h4> JSP 파일 내에 JSP 파일 가져오기 </h4>

    <h3> 홈 jsp 파일 입니다. </h3>

    <!-- <a href="/example/board.jsp">게시판으로</a> -->

    <jsp:include page = "/example/footer.jsp"></jsp:include>

</body>

</html>