<%@ page language = "java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>대기열 홈 페이지</title>

    
</head>
<body>
    <jsp:include page = "/waiting/header.jsp"></jsp:include>
    
    <h3>대기열 보기</h3>
    <p>대기열 자세히 보기를 원하실 경우, [ 대기번호 ]를 클릭해주세요.</p>
    <button onclick="transferWirte()">등록하기</button>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>대기번호</th>
                    <th>연락처</th>
                    <th>인원수</th>
                    <th>등록 일시</th>
                </tr>
            </thead>
            <tbody id="waitingTbody"></tbody>
        </table>
    </div>

    <script src = "/waiting/list.js"></script>    
</body>
</html>