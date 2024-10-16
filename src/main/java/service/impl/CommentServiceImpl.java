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
        return commentDAO.getCommentList(boardId);
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
        commentDAO.insertReply(commentDTO);
    }
}
