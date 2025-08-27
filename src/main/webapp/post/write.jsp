<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>게시물 작성</title>
        <link rel='stylesheet' href='/css/post/write.css'>

        <!-- Summer Note -->
        <!-- 부트스트랩 최신버전  -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

        <!-- jquery 최신버전 -->
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>

        <!-- 썸머노트 0.9.1 최신버전 css/js , https://cdnjs.com/ -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs5.min.css" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/summernote-bs5.min.js"></script>

        <!-- 썸머노트 한글 js-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.9.1/lang/summernote-ko-KR.min.js"></script>

    </head>

    <body>
        <jsp:include page="/header.jsp"></jsp:include>

        <div id="container">
            <div>카테고리</div>
            <select class="cno">
                <option value="1">뉴스</option>
                <option value="2">이벤트</option>
                <option value="3">FAQ</option>
                <option value="4">튜토리얼</option>
                <option value="5">사용자 리뷰</option>
            </select>
            <div>제목 <input type="text" class="ptitle" /></div>
            <div>내용 <br />
                <textarea class="pcontent" id="summernote" name="editordata"></textarea>
            </div>
            <button type="button" onclick="onWrite()">등록</button>

        </div>

        <script src="/js/post/write.js"></script>

    </body>

    </html>