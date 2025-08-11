<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>Page Title</title>
    <style>
        #underarea {
            border-bottom: 4px solid black;
        }

        a{
            text-decoration: none;
            color: black;
        }
        ul{
            display: flex;
            list-style-type: none;
        }
    </style>
</head>

<body>

    <div id="underarea">
        <div><h3>메인메뉴</h3>
            헤더 영역</div>
        <ul>
            <li><a href="/example/boardService12/home.jsp">Home</a></li>
            <li><a href="/example/boardService12/boardWrite.jsp">게시물 쓰기</a></li>
            <li><a href="/example/boardService12/boardPrint.jsp">게시물 전체조회</a></li>
            <li><a href="/example/boardService12/boardFind.jsp">게시물 개별조회</a></li>
            <li><a href="/example/boardService12/boardDelete.jsp">게시물 삭제</a></li>
            <li><a href="/example/boardService12/boardUpdate.jsp">게시물 수정</a></li>
        </ul>
    </div>

</body>

</html>