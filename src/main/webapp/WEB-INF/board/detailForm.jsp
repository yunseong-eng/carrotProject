<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>${board.title} - 상세페이지</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}
.container {
    display: flex;
    max-width: 1000px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 8px;
    background-color: #f9f9f9;
}
.left-section {
    width: 50%;
    padding-right: 20px;
}
.right-section {
    width: 50%;
    display: flex;
    flex-direction: column;
}
.right-section h1 {
    font-size: 24px;
    margin-bottom: 10px;
}
.details {
    margin-bottom: 20px;
}
.details p {
    margin: 5px 0;
    font-size: 16px;
}
.status {
    margin-top: 10px;
}
.info {
    margin: 20px 0;
}
.comments-section {
    margin-top: 40px;
}
.comments-section h2 {
    font-size: 1.5em;
}
.comment-form textarea {
    width: 100%;
    height: 100px;
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    resize: none;
}
.comment-form button {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
.comment-list {
    margin-top: 20px;
}
.comment-list .comment {
    border-bottom: 1px solid #ddd;
    padding: 10px 0;
}
.views {
    display: flex;
    align-items: center;
    font-size: 14px;
    margin-bottom: 10px;
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>

    <div class="container">
        <!-- 왼쪽 섹션: 이미지 -->
        <div class="left-section">
            <c:if test="${board.imagePath != null}">
                <img src="${pageContext.request.contextPath}/image?imagePath=${board.imagePath}" alt="게시글 이미지" style="max-width: 100%; border: 1px solid #ddd; padding: 10px;"/>
            </c:if>
        </div>

        <!-- 오른쪽 섹션: 게시글 정보 -->
        <div class="right-section">
            <!-- 게시글 제목 -->
            <h1>${board.title}</h1>

            <!-- 게시글 내용 -->
            <div class="details">
                <p>${board.content}</p>
            </div>

            <!-- 조회수 -->
            <div class="views">
                <i class="fas fa-eye"></i> <!-- 조회수 아이콘 -->
                <span>조회수: ${board.views}</span>
            </div>

            <!-- 판매/구매 상태 -->
            <div class="status">
                <strong>판매 상태: </strong>
                <c:choose>
                    <c:when test="${board.postType == '판매'}">
                        <span style="color: red;">판매중</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: blue;">구매 요청</span>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- 판매자 정보 및 배송비 -->
            <div class="info">
                <p><strong>판매자 아이디: </strong>${board.userId}</p>
                <p><strong>배송비: </strong>${board.shippingFee}</p>
            </div>
        </div>
    </div>

    <!-- 댓글 섹션 -->
    <div class="comments-section">
        <h2>댓글</h2>

        <!-- 댓글 목록 -->
        <div class="comment-list">
            <c:forEach var="comment" items="${commentList}">
                <div class="comment">
                    <p><strong>${comment.userId}</strong>: ${comment.content}</p>
                    <p><small><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm"/></small></p>
                </div>
            </c:forEach>
        </div>

        <!-- 댓글 작성 폼 -->
        <form class="comment-form" action="${pageContext.request.contextPath}/comment/write" method="post">
            <input type="hidden" name="boardId" value="${board.boardId}">
            <textarea name="content" placeholder="댓글을 입력하세요" style="height: 50px;"></textarea> <!-- 입력창 높이 줄임 -->
            <button type="submit">댓글 작성</button>
        </form>
    </div>
</body>
</html>
