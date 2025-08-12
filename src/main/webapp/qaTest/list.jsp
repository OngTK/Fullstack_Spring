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

    <h3>회원목록조회/수정</h3>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <td>회원번호</td>
                    <td>회원성명</td>
                    <td>전화번호</td>
                    <td>주소</td>
                    <td>가입일자</td>
                    <td>고객등급</td>
                    <td>거주지역</td>
                </tr>
            </thead>
            <tbody id="memberTbody"></tbody>
        </table>
    </div>

    <jsp:include page = "/qaTest/footer.jsp"></jsp:include>

    <script src="/qaTest/JS/list.js"></script>

</body>
</html>