/*
package service.impl;


import dao.CommentDAO;
import dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO; // DAO 객체 주입, DB와의 연동을 위해 사용

    // 댓글 작성 메서드
    @Override
    public void writeComment(CommentDTO commentDTO) {
        commentDAO.insertComment(commentDTO); // 댓글을 DB에 저장
    }

    // 특정 게시글에 대한 댓글 목록 조회
    @Override
    public List<CommentDTO> getCommentList(int boardId) {
        return commentDAO.getCommentList(boardId); // 게시글 ID로 댓글 목록 조회
    }

    // 댓글 수정
    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentDAO.updateComment(commentDTO); // 댓글 내용을 수정
    }

    // 댓글 삭제
    @Override
    public void deleteComment(int commentId) {
        commentDAO.deleteComment(commentId); // 댓글 ID로 댓글 삭제
    }
}
*/