package controller;

import dto.CommentDTO;
import dto.UserDTO;
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
    
    private String bucketName = "bitcamp-9th-bucket-143"; // 네이버 클라우드 관련 부분 유지

    // 댓글 작성 처리
    @PostMapping("/write")
    public String writeComment(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");

        // 세션에 로그인된 사용자가 없을 경우 로그인 페이지로 리다이렉트
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        // 로그인된 사용자의 userId 설정
        commentDTO.setUserId(loggedInUser.getUserId());

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
    public String updateComment(@ModelAttribute CommentDTO commentDTO, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");

        // 세션에 로그인된 사용자가 없을 경우 로그인 페이지로 리다이렉트
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        commentDTO.setUserId(loggedInUser.getUserId());
        commentService.updateComment(commentDTO);

        return "redirect:/board/detailForm/" + commentDTO.getBoardId();
    }

    // 댓글 삭제 처리
    @PostMapping("/delete")
    public String deleteComment(@RequestParam int commentId, @RequestParam int boardId, HttpSession session) {
        // 로그인된 사용자 정보 가져오기
        UserDTO loggedInUser = (UserDTO) session.getAttribute("user");

        // 세션에 로그인된 사용자가 없을 경우 로그인 페이지로 리다이렉트
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        commentService.deleteComment(commentId);

        return "redirect:/board/detailForm/" + boardId;
    }
}
