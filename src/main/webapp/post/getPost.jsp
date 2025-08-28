<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>게시물 개별조회</title>
        <link rel='stylesheet' href='/css/post/getPost.css'>

    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <div id="postArea">
                <div>
                    <h4>글번호</h4><span id="pno"></span>
                </div>
                <div>
                    <h4>작성일자</h4><span id="pdate"></span>
                </div>
                <div>
                    <h4>조회수</h4><span id="pview"></span>
                </div>
                <div>
                    <h4>제목</h4><span id="ptitle"></span>
                </div>
                <div>
                    <h4>카테고리</h4><span id="cno"></span>
                </div>
                <div>
                    <h4>작성자</h4><span id="mid"></span>
                </div>
                <div>
                    <h4>내용</h4><span id="pcontent"></span>
                </div>
            </div>
            <!-- 본인 게시물에 대해서만 버튼을 노출 -->
            <div class = "etcBtn">
            </div>
            <br>
            <hr>
            <br>


            <div>
                <h4>댓글</h4>
                <!-- 댓글 조회 영역 -->
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th> 리뷰번호 </th>
                                <th> 작성일자 </th>
                                <th> 작성내용 </th>
                                <th> 작성자 </th>
                            </tr>
                        </thead>
                        <tbody id="replyPrintArea">
                            <tr></tr>
                        </tbody>
                    </table>
                </div>
                <br>
                <hr>
                <br>
                <!-- 댓글 작성 영역 -->
                <div id="replyWriteArea">
                    <textarea id="rcontent"></textarea>
                    <button onclick="writeReply()">댓글쓰기</button>
                </div>
            </div>
        </div>
        <script src="/js/post/getPost.js"></script>

    </body>

    </html>