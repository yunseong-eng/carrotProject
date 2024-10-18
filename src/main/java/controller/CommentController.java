package controller;

import dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CommentService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    private String bucketName = "bitcamp-9th-bucket-143";

    // 댓글 작성 처리
    @PostMapping("/write")
    public String writeComment(@ModelAttribute CommentDTO commentDTO) {
        // 로그인 없이 임시 userId 설정
        commentDTO.setUserId("guestUser");

        if (commentDTO.getParentComment() != null) {
        	
            commentService.writeReply(commentDTO);  // 대댓글 작성
        } else {
            commentService.writeComment(commentDTO);  // 일반 댓글 작성
        }
        
        // 댓글 작성 후 다시 해당 게시글의 상세 페이지로 리다이렉트
        return "redirect:/board/detailForm/" + commentDTO.getBoardId();
    }

    // 댓글 수정 처리
    @PostMapping("/update")
    public String updateComment(@ModelAttribute CommentDTO commentDTO) {
        commentService.updateComment(commentDTO);
        return "redirect:/board/detailForm/" + commentDTO.getBoardId();
    }

    // 댓글 삭제 처리
    @PostMapping("/delete")
    public String deleteComment(@RequestParam int commentId, @RequestParam int boardId) {
        commentService.deleteComment(commentId);
        return "redirect:/board/detailForm/" + boardId;
    }
}
