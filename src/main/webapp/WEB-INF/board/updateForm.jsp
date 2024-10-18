<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
</head>
<body>
    <h1>게시글 수정</h1>
    <form action="${pageContext.request.contextPath}/board/update" method="post">
        <input type="hidden" name="boardId" value="${board.boardId}">
        <label>제목: <input type="text" name="title" value="${board.title}"></label><br>
        <label>내용: <textarea name="content">${board.content}</textarea></label><br>
        <button type="submit">수정</button>
    </form>
</body>
</html>
