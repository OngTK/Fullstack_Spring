<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>비밀번호 수정</title>
        <link rel='stylesheet' href='/css/member/update.css'>
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>비밀번호 수정 수정</h3>

            <div>기존 패스워스 : <input type="password" id="oldpwd"> </div>
            <div>신규 패스워스 : <input type="password" id="newpwd"> </div>

            <button type="button" onclick="updatePassword()">수정하기</button>

        </div>

        <script src="/js/member/pwdupdate.js"></script>

    </body>

    </html>