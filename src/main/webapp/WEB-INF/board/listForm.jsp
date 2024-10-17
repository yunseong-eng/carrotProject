<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>${category} 게시판 목록</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 20px;
}

/* 카테고리 선택 */
#category-section {
    margin: 20px 0;
}
#category-section a {
    margin-right: 15px;
    text-decoration: none;
    font-size: 18px;
    color: black;
}
#category-section a:hover {
    color: blue;
}

/* 테이블 스타일 */
table {
    width: 100%;
    border-collapse: collapse;
    table-layout: fixed; /* 테이블 레이아웃 고정 */
}
th, td {
    border-bottom: 1px solid #ccc;
    padding: 10px;
    text-align: center;
}
th {
    background-color: #f4f4f4;
}
th:nth-child(1) {
    width: 10%; /* 구분 컬럼 너비 */
}
th:nth-child(2) {
    width: 40%; /* 제목 컬럼 너비 (제일 길게 설정) */
}
th:nth-child(3) {
    width: 15%; /* 작성자 컬럼 너비 */
}
th:nth-child(4) {
    width: 20%; /* 작성시간 컬럼 너비 */
}
th:nth-child(5) {
    width: 5%; /* 조회수 컬럼 너비 */
}
.sale {
    color: red;
}
.purchase {
    color: blue;
}
.title-link {
    text-decoration: none;
    color: black;
    display: block;
    white-space: nowrap; /* 한 줄로 표시 */
    overflow: hidden;
    text-overflow: ellipsis; /* 길면 ...으로 표시 */
}
.title-link:hover {
    text-decoration: underline;
}
</style>
</head>
<body>

    <h3>${category} 게시글</h3>

    <!-- 게시판 테이블 -->
    <table>
        <thead>
            <tr>
                <th>구분</th> <!-- 판매/구매 구분 -->
                <th>제목</th>
                <th>작성자</th>
                <th>작성시간</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${board.postType == '판매'}">
                                <span class="sale">[판매]</span>
                            </c:when>
                            <c:otherwise>
                                <span class="purchase">[구매]</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="title-link" href="${pageContext.request.contextPath}/board/detailForm/${board.boardId}">
                            ${board.title}
                        </a>
                    </td>
                    <td>${board.userId}</td>
                    <td><fmt:formatDate value="${board.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    <td><fmt:formatNumber value="${board.views}" pattern="#,###"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <form action="${pageContext.request.contextPath}/board/writeForm" method="get">
        <button type="submit">글 작성</button>
    </form>
</body>
</html>
