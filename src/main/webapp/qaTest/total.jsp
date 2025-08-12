<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>쇼핑몰 회원관리</title>
            <link rel='stylesheet' href='CSS/index.css'>
</head>
<body>
    <jsp:include page = "/qaTest/header.jsp"></jsp:include>

    <h3>회원매출조회</h3>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <td>회원번호</td>
                    <td>회원성명</td>
                    <td>고객등급</td>
                    <td>매출</td>
                </tr>
            </thead>
            <tbody id="totalTbody"></tbody>
        </table>
    </div>

    <jsp:include page = "/qaTest/footer.jsp"></jsp:include>

    <script src="/qaTest/JS/total.js"></script>

</body>
</html>