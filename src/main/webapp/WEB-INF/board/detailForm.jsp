<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detail.css">
<title>${board.title} - 상세페이지</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script>
    // 댓글 달기 폼 토글
    function toggleReplyForm(commentId) {
        var replyForm = document.getElementById("replyForm_" + commentId);
        if (replyForm.style.display === "none" || replyForm.style.display === "") {
            replyForm.style.display = "block";
        } else {
            replyForm.style.display = "none";
        }
    }
</script>
</head>
<body>

    <div class="container">
    <!-- 왼쪽 섹션: 이미지 -->
    <div class="left-section">
        <c:if test="${board.imagePath != null}">
            <img src="${pageContext.request.contextPath}/image?imagePath=${board.imagePath}" alt="게시글 이미지" />
        </c:if>
    </div>

    <!-- 오른쪽 섹션: 게시글 정보 -->
    <div class="right-section">
        <!-- 게시글 제목 -->
        <h1>${board.title}</h1>

        <!-- 조회수 -->
        <div class="views">
            <i class="fas fa-eye"></i>
            <span>${board.views}</span>
        </div>

        <!-- 게시글 내용 -->
        <div class="details">
            <p style="white-space: pre-line;">${board.content}</p>
        </div>

        <!-- 판매/구매 상태 -->
        <div class="status">
            <strong>판매 상태</strong>
            <c:choose>
                <c:when test="${board.postType == '판매'}">
                    <span>판매중</span>
                </c:when>
                <c:otherwise>
                    <span class="blue">구매 요청</span>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- 판매자 정보 및 배송비 -->
        <div class="info">
            <p><strong>판매자 아이디</strong> <span>${board.userId}</span></p>
            <p><strong>배송비</strong>
                <c:choose>
                    <c:when test="${fn:contains(board.shippingFee, '무료') == false}">
                        <span>O (₩${board.shippingFee})</span>
                    </c:when>
                    <c:otherwise>
                        <span>X (무료)</span>
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
    </div>
</div>
<hr>
<!-- 댓글 섹션 -->
<div class="comments-section">
    <h2>댓글</h2>
    <!-- 댓글 작성 폼 -->
    <form class="comment-form" action="${pageContext.request.contextPath}/comment/write" method="post">
        <input type="hidden" name="boardId" value="${board.boardId}">
        <textarea name="content" placeholder="댓글을 입력하세요" style="height: 50px;"></textarea>
        <button type="submit">댓글 작성</button>
    </form>

    <!-- 댓글 목록 -->
    <div class="comment-list">
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                <p><strong>${comment.userId}</strong>: ${comment.content}</p>
                <p><small><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm"/></small></p>

                <!-- 대댓글 작성 버튼 -->
                <a href="javascript:void(0)" class="reply-toggle" onclick="toggleReplyForm(${comment.commentId})">답글 달기</a>

                <!-- 대댓글 목록 -->
                <c:if test="${comment.replyList != null}">
                    <div class="reply-list">
                        <c:forEach var="reply" items="${comment.replyList}">
                            <div class="reply">
                                <p><strong>${reply.userId}</strong>: ${reply.content}</p>
                                <p><small><fmt:formatDate value="${reply.createTime}" pattern="yyyy-MM-dd HH:mm"/></small></p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>

                <!-- 대댓글 작성 폼 (기본적으로 숨김) -->
                <form id="replyForm_${comment.commentId}" class="reply-form" action="${pageContext.request.contextPath}/comment/write" method="post" style="display: none;">
                    <input type="hidden" name="boardId" value="${board.boardId}">
                    <input type="hidden" name="parentComment" value="${comment.commentId}">
                    <textarea name="content" placeholder="대댓글을 입력하세요" style="height: 25px;"></textarea>
                    <button type="submit">대댓글 작성</button>
                </form>
            </div>
        </c:forEach>
    </div>

</div>
</body>
</html>
