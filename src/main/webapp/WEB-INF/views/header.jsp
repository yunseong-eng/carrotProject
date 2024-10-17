<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
/* 헤더 스타일 */
#header {
    display: flex;
    align-items: center;
    padding: 10px;
    background-color: white;
}
#header img {
    height: 80px;
    margin-right: 20px;
}
#header a {
    text-decoration: none;
    color: black;
    margin-right: 30px;
    font-size: 18px;
}
#header a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
    <!-- 헤더 -->
    <div id="header">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/image/carrotLogo.png" alt="로고">
        </a>
        <a href="${pageContext.request.contextPath}/board/listForm?category=전체">전체</a>
        <a href="${pageContext.request.contextPath}/board/listForm?category=의류">의류</a>
        <a href="${pageContext.request.contextPath}/board/listForm?category=신발">신발</a>
    </div>
</body>
</html>
