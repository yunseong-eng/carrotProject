<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>${category} 게시판 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f4f4f4;
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
        }
        .title-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>${category} 게시판 목록</h1>

    <!-- 카테고리 선택 폼 -->
    <form action="${pageContext.request.contextPath}/board/listForm" method="get">
        <label for="category">카테고리 선택:</label>
        <select name="category" id="category">
            <option value="전체">전체</option>
            <option value="의류">의류</option>
            <option value="신발">신발</option>
            <!-- 다른 카테고리를 추가할 수 있습니다 -->
        </select>
        <button type="submit">조회</button>
    </form>
    
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
                    <!-- 판매글/구매글 구분 -->
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
                    
                    <!-- 제목 (상세 페이지로 이동) -->
                    <td>
                        <a class="title-link" href="${pageContext.request.contextPath}/board/detailForm/${board.boardId}">
                            ${board.title}
                        </a>
                    </td>
                    
                    <!-- 작성자 -->
                    <td>${board.userId}</td>
                    
                    <!-- 작성시간 (포맷 적용) -->
                    <td><fmt:formatDate value="${board.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    
                    <!-- 조회수 (포맷 적용) -->
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
