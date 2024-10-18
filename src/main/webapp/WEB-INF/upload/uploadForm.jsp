<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>이미지 업로드</title>
</head>
<body>
    <h1>이미지 업로드</h1>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <label>이미지 파일: <input type="file" name="file"></label><br>
        <button type="submit">업로드</button>
    </form>
</body>
</html>
