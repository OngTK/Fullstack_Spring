<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>아이디/비밀번호 찾기</title>
        <link rel='stylesheet' href='/css/member/find.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>아이디 찾기</h3>
            이름 : <input type="text" class="nameInput" id="nameInput"><br />
            연락처 : <input type="text" class="phoneInput" id="phoneInput" placeholder="010-0000-0000"><br />
            <button type="button" onclick="findID()">아이디 찾기</button><br />
            <div id="findIDOutput"></div>

            <h3>비밀번호 찾기</h3>
            아이디 : <input type="text" class="idInput" id="idInput"><br />
            연락처 : <input type="text" class="phoneInput2" id="phoneInput2" placeholder="010-0000-0000"><br />
            <button type="button" onclick="findPwd()">비밀번호</button><br />
            <div id="newpwd"></div>

        </div>



        <script src="/js/member/find.js"></script>
    </body>

    </html>