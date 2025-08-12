<%@ page language="java" contentType="text/html ; charset=utf-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>헤더 영역</title>
    <style>
        #headerarea {
            border-bottom: 4px solid black;
        }

        * {
            margin: 0px 0px;
        }

        a {
            text-decoration: none;
            color: black;
        }

        ul {
            display: flex;
            list-style-type: none;
        }
    </style>
</head>

<body>
    <div id ="headerarea">
        <h3>대기열 서비스</h3>
        <div>header area</div>

        <ul>
            <ol><a href="/waiting/index.jsp">Home</a></ol>
            <ol><a href="/waiting/list.jsp">대기열 보기</a></ol>
        </ul>
    </div>

</body>

</html>