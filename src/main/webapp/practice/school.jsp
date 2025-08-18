<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>연수구 학교</title>
        <link rel='stylesheet' href='/css/practice/school.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <h3> 연수구 관내 학교 </h3>
        <div id="container">            
            <div id="map"></div>
            <div id="sidebar"></div>
        </div>

        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=29ac2fc1e2229c89c0cdf197740abcb5&libraries=clusterer"></script>
        <script src="/js/practice/school.js"></script>
    </body>

    </html>