<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/write.css">
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
    <div class="container">
        <h2>게시글 작성</h2>
        <form id="postForm" action="${pageContext.request.contextPath}/board/write" method="post" enctype="multipart/form-data" novalidate>
            <table>
                <tr>
                    <th><label for="file">이미지 파일:</label></th>
                    <td><input type="file" id="file" name="file" onchange="previewImage(event)"><br>
                        <img id="imagePreview" alt="이미지 미리보기">
                    </td>
                </tr>
                <tr>
                    <th><label for="title">제목:</label></th>
                    <td><input type="text" id="title" name="title"></td>
                </tr>
                <tr>
                    <th><label for="category">카테고리:</label></th>
                    <td>
                        <select id="category" name="category">
                            <option value="의류">의류</option>
                            <option value="신발">신발</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="postType">게시글 유형:</label></th>
                    <td>
                        <select id="postType" name="postType">
                            <option value="판매">판매</option>
                            <option value="구매">구매</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="content">내용:</label></th>
                    <td><textarea id="content" name="content" rows="8" placeholder="번호를 꼭 입력하세요!"></textarea></td>
                </tr>
                <tr>
                    <th><label for="shippingFeeSelect">배송비:</label></th>
                    <td>
                        <select id="shippingFeeSelect" name="shippingFeeOption" onchange="toggleShippingFeeInput()">
                            <option value="O">O</option>
                            <option value="X">X</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><label for="shippingFee">배송비 금액:</label></th>
                    <td><input type="text" id="shippingFee" name="shippingFee" placeholder="배송비 없음" disabled></td>
                </tr>
                <tr>
                    <th><label for="includes">구성품 포함 여부:</label></th>
                    <td>
                        <select id="includes" name="includes">
                            <option value="Y">포함</option>
                            <option value="N">미포함</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="작성 완료">
        </form>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
</body>
</html>
