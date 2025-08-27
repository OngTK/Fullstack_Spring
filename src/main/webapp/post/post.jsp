<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>게시물 조회</title>
        <link rel='stylesheet' href='/css/post/post.css'>

    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">

            <div>
                <button onclick="location.href='write.jsp'">글쓰기</button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody class="postTbody">
                    <tr>
                        <td>1</td>
                        <td>테스트 제목</td>
                        <td>유재석</td>
                        <td>2025-08-26</td>
                        <td>3</td>
                    </tr>
                </tbody>
            </table>

            <div class="pagenation">
                <ul class="pageBtnBox">
                    <li><a href="post.jsp?cno=1&page=1">1</a></li>
                    <li><a href="post.jsp?cno=1&page=2">2</a></li>
                    <li><a href="post.jsp?cno=1&page=3">3</a></li>
                    <li><a href="post.jsp?cno=1&page=4">4</a></li>
                    <li><a href="post.jsp?cno=1&page=5">5</a></li>
                </ul>
            </div>

            <div style="display:flex">
                <div>
                    <select class="key">
                        <option value="ptitle">제목</option>
                        <option value="pcontent">내용</option>
                    </select>
                </div>
                <div>
                    <input class="keyword" placeholder="검색어를 입력하세요." type="text">
                </div>
                <div>
                    <button type="button" onclick="onSearch()">검색</button>
                </div>
            </div>


        </div>


        <script src="/js/post/post.js"></script>

    </body>

    </html>