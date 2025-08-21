<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>포인트 충전</title>
        <link rel='stylesheet' href='/css/member/pointCharge.css'>
        
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <h3>포인트 충전하기</h3>
            <form id="pointForm">
                <label>
                    <input type="radio" name="pointCharge" value="1000" /> 1,000원
                </label>
                <label>
                    <input type="radio" name="pointCharge" value="5000" /> 5,000원
                </label>
                <label>
                    <input type="radio" name="pointCharge" value="10000" /> 10,000원
                </label>
                <br />
            </form>
            <button onclick="onCharge()"> 충전하기 </button>
        </div>

        <script src="/js/member/pointCharge.js"></script>

    </body>

    </html>