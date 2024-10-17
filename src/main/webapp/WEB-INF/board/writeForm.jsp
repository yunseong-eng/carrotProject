<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
</head>
<body>
    <h2>게시글 작성</h2>

    <form action="${pageContext.request.contextPath}/board/write" method="post" enctype="multipart/form-data">
    <label for="title">제목:</label>
    <input type="text" id="title" name="title" required><br>

    <label for="category">카테고리:</label>
    <select id="category" name="category" required>
        <option value="의류">의류</option>
        <option value="신발">신발</option>
    </select><br>

    <label for="postType">게시글 유형:</label>
    <select id="postType" name="postType" required>
        <option value="판매">판매</option>
        <option value="구매">구매</option>
    </select><br>

    <label for="content">내용:</label>
    <textarea id="content" name="content" rows="5" cols="50" required></textarea><br>

    <label for="shippingFee">배송비:</label>
    <input type="text" id="shippingFee" name="shippingFee" required><br>

    <label for="includes">구성품 포함 여부:</label>
    <select id="includes" name="includes">
        <option value="Y">포함</option>
        <option value="N">미포함</option>
    </select><br>

    <!-- 이미지 업로드 -->
    <label for="file">이미지 파일:</label>
    <input type="file" id="file" name="file"><br>

    <input type="submit" value="작성 완료">
</form>
</body>
</html>
