CREATE TABLE post_board (
    board_id        INT AUTO_INCREMENT PRIMARY KEY, -- 글 ID
    user_id         VARCHAR(50) NOT NULL, -- 글 작성자 ID (user 테이블의 외래키)
    title           VARCHAR(255) NOT NULL, -- 제목
    content         TEXT NOT NULL, -- 내용
    category        VARCHAR(50) NOT NULL, -- 카테고리 (의류, 신발)
    post_type       VARCHAR(10) NOT NULL, -- 게시글 유형 (판매, 구매)
    create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 작성일
    views           INT DEFAULT 0, -- 조회수
    shipping_fee    VARCHAR(10) NOT NULL, -- 배송비 (무료, 유료)
    includes        CHAR(1) DEFAULT 'N' -- 구성품 여부 (O, X)
);
