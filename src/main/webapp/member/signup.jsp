<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>회원가입</title>
        <link rel='stylesheet' href='/css/member/signup.css'>
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>
        <div id="container">
            <h3>회원가입 페이지</h3>
            <form id="memberForm">
            아이디 : <input type="text" name="mid" class="idInput" id="idInput" onkeyup="idCheck()">
            <div class="idCheck"> </div>
            비밀번호 : <input type="password" name="mpwd" class="pwdInput" id="pwdInput"> <br />
            이름 : <input type="text" name="mname" class="nameInput" id="nameInput"> <br />
            연락처 : <input type="text" name="mphone" class="phoneInput" id="phoneInput" placeholder="010-0000-0000"
                onkeyup="phoneCheck()">
            <div class="phoneCheck"> </div>
            이미지 : <input type="file" name="upload" class="uploadFile" />
            </form>
            
            <button type="button" onclick="signup()">회원가입</button> <br />
            <a href="/member/login.jsp">로그인하기</a><br />
            <a href="/member/find.jsp">아이디/비밀번호 찾기</a><br />
        </div>

        <script src="/js/member/signup.js"></script>

    </body>

    </html>