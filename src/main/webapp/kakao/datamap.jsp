<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>데이터 맵</title>
        <link rel='stylesheet' href='/css/kakao/datamap.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3> 데이터 지도 </h3>
            <div id="map"></div>
            <div id="sidebar"></div>
            
            <!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd"></script> -->
        </div>

        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd&libraries=clusterer"></script>
        <script src="/js/kakao/datamap.js"></script>
    </body>

    </html>