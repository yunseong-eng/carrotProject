package controller;

import dto.CommentDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 댓글 작성 처리
    @PostMapping("/write")
    public String writeComment(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            commentDTO.setUserId(user.getUserId());
            commentService.writeComment(commentDTO);
            return "redirect:/board/detail/" + commentDTO.getBoardId();
        } else {
            return "redirect:/user/login";
        }
    }

    // 댓글 수정 처리
    @PostMapping("/update")
    public String updateComment(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null && user.getUserId().equals(commentDTO.getUserId())) {
            commentService.updateComment(commentDTO);
            return "redirect:/board/detail/" + commentDTO.getBoardId();
        } else {
            return "redirect:/user/login";
        }
    }

    // 댓글 삭제 처리
    @PostMapping("/delete")
    public String deleteComment(@RequestParam int commentId, @RequestParam int boardId, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        // 댓글 작성자나 관리자만 삭제 가능하도록 로직 추가
        commentService.deleteComment(commentId);
        return "redirect:/board/detail/" + boardId;
    }
}
