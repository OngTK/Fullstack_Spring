<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>정보 수정</title>
        <link rel='stylesheet' href='/css/member/update.css'>
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>회원 정보 수정</h3>

            <form id="memberForm">
                <div>회원번호 : <input type="text" name="mno" id="mno" class="infoArea" disabled /> </div>
                <div>아이디 :<input type="text" name="mid" id="mid" class="infoArea" disabled> </div>
                <div>이름 :<input type="text" name="mname" id="mname" class="infoArea"> </div>
                <div>전화번호 :<input type="text" name="mphone" id="mphone" class="infoArea" onkeyup="phoneCheck()">
                    <div class="phoneCheck"></div>
                </div>
                <div>가입일자 :<input type="text" name="mdate" id="mdate" class="infoArea" disabled> </div>
                <div>프로필 : <input type="file" name="upload" id="mimgname"></div>
            </form>
            <button type="button" onclick="update()">수정하기</button>

        </div>

        <script src="/js/member/update.js"></script>

    </body>

    </html>