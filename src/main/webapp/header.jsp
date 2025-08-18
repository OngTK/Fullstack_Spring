<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>header 영역</title>

        <link rel='stylesheet' href='/css/header.css'>
    </head>

    <body>
        <div id="header">
            <ul id="sub-menu">
                <li><a href="/index.jsp"><img src="/img/tj_logo.jpg"></a></li>
                <li><a href="/datago/data.jsp">공공데이터</a></li>
                <li><a href="/kakao/map.jsp">카카오지도</a></li>
                <li><a href="/kakao/datamap.jsp">데이터맵</a></li>
                <li><a href="/practice/school.jsp"> 실습7 </a> </li>
            </ul>

            <ul id="log-menu">
                <!-- 미로그인 시 구역-->
                <li><a href="/member/login.jsp">로그인</a></li>
                <li><a href="/member/singup.jsp">회원가입</a></li>

                <!-- 로그인 시 구역 -->
                <li><span>OOO님 100 POINT </span></li>
                <li><a href="/member/info.jsp">내 정보</a></li>
                <li><a href="#" onclick="">로그아웃</a></li>
            </ul>
        </div>

        <script src="/js/header.js"></script>
    </body>

    </html>