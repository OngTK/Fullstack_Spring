<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>로그인</title>
        <link rel='stylesheet' href='/css/member/login.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>로그인</h3>
            아이디 : <input type="text" class="idInput" id="idInput" placeholder="아이디 입력"><br />
            패스워드 : <input type="password" class="pwdInput" id="pwdInput" placeholder="패스워드 입력"><br />
            <button type="button" onclick="login()">로그인</button><br />
            <a href="/member/singup.jps">회원가입하기</a> <br />
            <a href="/member/find.jps">아이디/비밀번호찾기</a> <br />
        </div>



        <script src="/js/member/login.js"></script>
    </body>

    </html>