CREATE TABLE post_comment (
    comment_id      INT AUTO_INCREMENT PRIMARY KEY, -- 댓글 ID
    board_id        INT NOT NULL, -- 게시글 ID (post_board 테이블의 외래키)
    user_id         VARCHAR(50) NOT NULL, -- 댓글 작성자 ID (user 테이블의 외래키)
    parent_comment  INT, -- 부모 댓글 ID (NULL일 경우 일반 댓글, 값을 가지면 대댓글)
    content         TEXT NOT NULL, -- 댓글 내용
    create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 댓글 작성일
    CONSTRAINT fk_board_comment FOREIGN KEY (board_id) REFERENCES post_board(board_id),
    CONSTRAINT fk_user_comment FOREIGN KEY (user_id) REFERENCES user(user_id),
    CONSTRAINT fk_parent_comment FOREIGN KEY (parent_comment) REFERENCES post_comment(comment_id) -- 부모 댓글과의 관계
);
