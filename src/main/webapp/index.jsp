<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>KREAM | 한정판 거래의 FLEX</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div id="wrap">
    <header>
        <div id="header">
            <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/image/carrotLogo.png" class="logo"></a>
            <dl class="topnav">
                <dt class="blind">top navigation</dt>
                <dd><a href="#">고객센터</a></dd>
                <dd><a href="#">마이페이지</a></dd>
                <dd><a href="#">관심</a></dd>
                <dd><a href="#">알림</a></dd>
                <dd><a href="${pageContext.request.contextPath}/user/login">로그인</a></dd>
            </dl>

            <dl class="topnav2">
                <dt class="blind">top navigation</dt>
                <dd><a href="#">HOME</a></dd>
                <dd><a href="#">STYLE</a></dd>
                <dd><a href="#">SHOP</a></dd>
                <dd><a href="#"><img src="${pageContext.request.contextPath}/image/d.png" width="20"></a></dd>
                <dd><a href="#"><img src="${pageContext.request.contextPath}/image/d2.png" width="20"></a></dd>
            </dl>

            <h2 class="blind">main navigation</h2>
            <ul class="mainnav">
                <li><a href="${pageContext.request.contextPath}/board/listForm?category=전체">전체</a></li>
                <li><a href="${pageContext.request.contextPath}/board/listForm?category=의류">의류</a></li>
                <li><a href="${pageContext.request.contextPath}/board/listForm?category=신발">신발</a></li>
            </ul>
        </div><!--header-->
    </header>

    <span class="slideshow-container">
        <div class="slidesbackground">
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/1.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/2.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/3.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/4.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/5.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/6.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/7.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/8.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/9.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/10.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/11.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/12.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/13.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/card0.webp" class="slideshow-image">
            </div>
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/image/welcome.webp" class="slideshow-image">
            </div>
        </div>
    </span>
    <br><br><br>

    <div>
        <!-- 추천 항목들 -->
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item1.webp" alt="item1">
            <dd>크림 드로우</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item2.webp" alt="item2">
            <dd>남성 추천</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item3.webp" alt="item3">
            <dd>여성 추천</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item4.webp" alt="item4">
            <dd>색다른 추천</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item5.webp" alt="item5">
            <dd>대표 트랙탑</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item6.webp" alt="item6">
            <dd>정가 아래</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item7.webp" alt="item7">
            <dd>24FW 신상 발매</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item8.webp" alt="item8">
            <dd>구해요</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item9.webp" alt="item9">
            <dd>20만원에 아이폰</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/image/item10.webp" alt="item10">
            <dd>러닝화 완벽 준비</dd>
        </div>
    </div>
    
    <br><br><br><br>
    <hr>
    <br>
    
    <!-- 발매 상품 섹션 -->
    <div class="logname">
        <dd>Just Dropped</dd>
        <p>발매 상품</p>
    </div>
    <div class="itemcenter">
        <!-- 발매된 상품들 -->
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped6.webp" alt="dropped6">
            <dd class="name">Jordan</dd>
            <dd>Jordan 1 Retro Low OG Mocha</dd>
            <dd class="name">185,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped2.webp" alt="dropped2">
            <dd class="name">BLR</dd>
            <dd>[KREAM 단독] BLR Embossed Wave Washing Wide Denim Pants Black</dd>
            <dd class="name">143,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped3.webp" alt="dropped3">
            <dd class="name">Uniqlo</dd>
            <dd>Uniqlo Multi Pocket Shoulder Bag Olive</dd>
            <dd class="name">45,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped4.webp" alt="dropped4">
            <dd class="name">Asics</dd>
            <dd>Asics Jog 100 S White Black - 2E Wide (Korea Exclusive)</dd>
            <dd class="name">92,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped5.webp" alt="dropped5">
            <dd class="name">Asics</dd>
            <dd>Asics Gel-Kayano 14 Earthenware Pack Fjord Grey Cement Grey</dd>
            <dd class="name">211,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/image/dropped1.webp" alt="dropped1">
            <dd class="name">Nike</dd>
            <dd>Nike x Peaceminusone Air Force 1 Low Para-Noise 3.0 Black and Multicolor</dd>
            <dd class="name">432,000원</dd>
            <dd class="mininame">즉시 구매가</dd>
        </div>
        <!-- 기타 상품들 -->
    </div>

    <div class="button-container">
        <button class="button">더보기</button>
    </div>

    <br><br><br><br>
    <hr>
    <br>

    <!-- 인기 브랜드 섹션 -->
    <div class="logname">
        <dd>Top Brand</dd>
        <p>인기 탑 브랜드</p>
    </div>
    <div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/topbrandimage/jordan.webp" alt="">
            <dd>나이키</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/topbrandimage/adidas.webp" alt="">
            <dd>아디다스</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/topbrandimage/stussy.webp" alt="">
            <dd>스투시</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/topbrandimage/chrome_hearts.webp" alt="">
            <dd>크롬하츠</dd>
        </div>
        <div class="layout">
            <img src="${pageContext.request.contextPath}/topbrandimage/iab_studio.webp" alt="">
            <dd>아이앱 스튜디오</dd>
        </div>
        <!-- 기타 브랜드들 -->
    </div>

    <br><br><br><br>
    <hr>
    <br>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>

<script type="text/javascript">
    var slideIndex = 0;
    showSlides();

    function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");

        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slideIndex++;
        if (slideIndex > slides.length) {
            slideIndex = 1;
        }
        slides[slideIndex - 1].style.display = "block";
        setTimeout(showSlides, 3000); // 3초마다 이미지가 체인지
    }
</script>
