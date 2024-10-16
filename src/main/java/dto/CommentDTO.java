package dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CommentDTO {
    private int commentId;          // 댓글 ID
    private int boardId;            // 게시글 ID
    private String userId;          // 작성자 ID
    private Integer parentComment;  // 부모 댓글 ID (nullable)
    private String content;         // 댓글 내용
    private Timestamp createTime;   // 작성일
}
