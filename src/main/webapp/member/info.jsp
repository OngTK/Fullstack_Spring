<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>내 정보 조회</title>
        <link rel='stylesheet' href='/css/member/info.css'>
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>마이페이지</h3>

            <div id="info">
                <div>회원번호 : <span id="mno" class="infoArea"></span> </div>
                <div>아이디 :<span id="mid" class="infoArea"></span> </div>
                <div>이름 :<span id="mname" class="infoArea"></span> </div>
                <div>전화번호 :<span id="mphone" class="infoArea"></span> </div>
                <div>가입일자 :<span id="mdate" class="infoArea"></span> </div>

                <a href="/member/update.jsp">회원정보 수정</a><br />
                <a href="/member/pwdupdate.jsp">비밀번호 수정</a><br />
            </div>
            <div id="point">
                <table>
                    <thead>
                        <tr>
                            <th>날짜·시간</th>
                            <th>포인트</th>
                            <th>이유</th>
                        </tr>
                    </thead>
                    <tbody id="pointTbody"></tbody>
                </table>
            </div>

            <a href="#" onclick="onDelete()">회원 탈퇴</a><br />

        </div>

        <script src="/js/member/info.js"></script>

    </body>

    </html>