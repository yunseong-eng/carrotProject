package service;

import dto.CommentDTO;

import java.util.List;

public interface CommentService {
    // 댓글 작성
    void writeComment(CommentDTO commentDTO);

    // 댓글 목록 조회
    List<CommentDTO> getCommentList(int boardId);

    // 댓글 수정
    void updateComment(CommentDTO commentDTO);

    // 댓글 삭제
    void deleteComment(int commentId);

    // 대댓글 작성
    void writeReply(CommentDTO commentDTO);
}
