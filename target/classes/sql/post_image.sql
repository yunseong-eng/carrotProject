CREATE TABLE post_image (
    image_id            INT AUTO_INCREMENT PRIMARY KEY, -- 이미지 ID
    board_id            INT NOT NULL, -- 해당 게시글 ID (post_board 테이블의 외래키)
    image_name          VARCHAR(50), -- 이미지 설명 또는 이름
    image_content       VARCHAR(4000), -- 이미지 내용 설명
    image_file_name     VARCHAR(100) NOT NULL, -- 저장된 파일명 (서버에서 저장된 파일명)
    image_original_name VARCHAR(100) NOT NULL, -- 원본 파일명 (사용자가 업로드한 파일명)
    CONSTRAINT fk_board_image FOREIGN KEY (board_id) REFERENCES post_board(board_id) ON DELETE CASCADE
);
