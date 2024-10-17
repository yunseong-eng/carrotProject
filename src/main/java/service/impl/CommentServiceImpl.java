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
    private CommentDAO commentDAO;

    @Override
    public void writeComment(CommentDTO commentDTO) {
        commentDAO.insertComment(commentDTO);
    }

    @Override
    public List<CommentDTO> getCommentList(int boardId) {
        // 게시글에 대한 모든 댓글을 가져옴
        List<CommentDTO> commentList = commentDAO.getCommentList(boardId);
        
        // 각 댓글에 대한 대댓글을 조회하여 설정
        for (CommentDTO comment : commentList) {
            List<CommentDTO> replies = commentDAO.getRepliesForComment(comment.getCommentId());
            comment.setReplyList(replies);  // 댓글 객체에 대댓글 리스트 설정
        }
        
        return commentList;
    }

    @Override
    public void updateComment(CommentDTO commentDTO) {
        commentDAO.updateComment(commentDTO);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDAO.deleteComment(commentId);
    }
    @Override
    public void writeReply(CommentDTO commentDTO) {
        commentDAO.insertReply(commentDTO);  // 대댓글 저장
    }
    
}

