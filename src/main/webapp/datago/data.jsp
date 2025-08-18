<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>Data</title>
        <link rel='stylesheet' href='/css/datago/data.css'>
    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3> 사업자 상태 조회</h3>
            <input type="text" placeholder="-없이 사업자번호 입력" class="b_no">
            <button onclick="dataAPI2()"> 확인 </button>
            
            <h3> 인천 부평구 주유소 현황 API </h3>

            <table>
                <thead>
                    <th>연번</th>
                    <th>상호</th>
                    <th>업종</th>
                    <th>전화번호</th>
                    <th>주소</th>
                </thead>
                <tbody id="dataTbody"></tbody>
            </table>

            
        </div>

        <script src="/js/datago/data.js"></script>
    </body>

    </html>