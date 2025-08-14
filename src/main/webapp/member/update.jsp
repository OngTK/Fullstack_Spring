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

            <div>회원번호 :  <input type="text" id = "mno" class = "infoArea" disabled/> </div>
            <div>아이디 :<input type="text" id = "mid" class = "infoArea" disabled > </div>
            <div>이름 :<input type="text" id = "mname" class = "infoArea" > </div>
            <div>전화번호 :<input  type="text" id = "mphone" class = "infoArea" onkeyup="phoneCheck()"><div class="phoneCheck"></div> </div>
            <div>가입일자 :<input  type="text" id = "mdate" class = "infoArea"  disabled> </div>
  
            <button type="button" onclick="update()">수정하기</button>
            
        </div>

        <script src="/js/member/update.js"></script>

    </body>

    </html>