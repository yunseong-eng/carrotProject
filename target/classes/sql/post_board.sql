CREATE TABLE post_board (
    boardId        INT AUTO_INCREMENT PRIMARY KEY,     -- 글 ID
    userId         VARCHAR(50) NOT NULL,               -- 글 작성자 ID (user 테이블의 외래키)
    title          VARCHAR(255) NOT NULL,              -- 제목
    content        TEXT NOT NULL,                      -- 내용
    category       VARCHAR(50) NOT NULL,               -- 카테고리 (예: 의류, 신발)
    postType       VARCHAR(10) NOT NULL,               -- 게시글 유형 (예: 판매, 구매)
    createTime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,-- 작성일
    views          INT DEFAULT 0,                      -- 조회수
    shippingFee    VARCHAR(10) NOT NULL,               -- 배송비 (예: 무료, 유료)
    includes       CHAR(1) DEFAULT 'N',                -- 구성품 여부 (O, N)
    imagePath      VARCHAR(255),                       -- 이미지 경로
    isNotice       TINYINT(1) DEFAULT 0                -- 공지사항 여부 (1: 공지사항, 0: 일반 글)
);
