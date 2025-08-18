<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>카카오 지도</title>
        <link rel='stylesheet' href='/css/kakao/kakao.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3> KAKAO 지도 API </h3>
            <div id="map" style="width:500px;height:400px;"></div>

            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd"></script>
        </div>

        <script src="/js/kakao/map.js"></script>
    </body>

    </html>