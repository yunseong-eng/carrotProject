CREATE TABLE post_comment (
    commentId      INT AUTO_INCREMENT PRIMARY KEY, -- 댓글 ID
    boardId        INT NOT NULL,                   -- 게시글 ID (post_board 테이블의 외래키)
    userId         VARCHAR(50) NOT NULL,           -- 댓글 작성자 ID (user 테이블의 외래키)
    parentComment  INT,                            -- 부모 댓글 ID (NULL일 경우 일반 댓글, 값을 가지면 대댓글)
    content        TEXT NOT NULL,                  -- 댓글 내용
    createTime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 댓글 작성일
    CONSTRAINT fk_board_comment FOREIGN KEY (boardId) REFERENCES post_board(boardId),
    CONSTRAINT fk_user_comment FOREIGN KEY (userId) REFERENCES user(userId),
    CONSTRAINT fk_parent_comment FOREIGN KEY (parentComment) REFERENCES post_comment(commentId)
);
