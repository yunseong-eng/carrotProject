package controller;

import dto.BoardDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    // 게시글 목록 보기
    @GetMapping("/list")
    public String boardList(@RequestParam String category,
                            @RequestParam(defaultValue = "1") int page,
                            Model model) {
        Map<String, Object> result = boardService.getBoardList(category, page);
        model.addAttribute("boardList", result.get("boardList"));
        model.addAttribute("fixedNotices", result.get("fixedNotices"));
        model.addAttribute("category", category);
        return "board/list";
    }

    // 게시글 작성 폼 이동
    @GetMapping("/write")
    public String writeForm(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            return "board/write";
        } else {
            return "redirect:/user/login";
        }
    }

    // 게시글 작성 처리
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            boardDTO.setUserId(user.getUserId());
            boardService.writeBoard(boardDTO);
            return "redirect:/board/list?category=" + boardDTO.getCategory();
        } else {
            return "redirect:/user/login";
        }
    }

    // 게시글 상세 보기
    @GetMapping("/detail/{boardId}")
    public String boardDetail(@PathVariable int boardId, Model model) {
        BoardDTO boardDTO = boardService.getBoardDetail(boardId);
        model.addAttribute("board", boardDTO);
        return "board/detail";
    }

    // 게시글 수정 폼 이동
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable int boardId, HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        BoardDTO boardDTO = boardService.getBoardDetail(boardId);
        if (user != null && user.getUserId().equals(boardDTO.getUserId())) {
            model.addAttribute("board", boardDTO);
            return "board/update";
        } else {
            return "redirect:/user/login";
        }
    }

    // 게시글 수정 처리
    @PostMapping("/update")
    public String updateBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null && user.getUserId().equals(boardDTO.getUserId())) {
            boardService.updateBoard(boardDTO);
            return "redirect:/board/detail/" + boardDTO.getBoardId();
        } else {
            return "redirect:/user/login";
        }
    }

    // 게시글 삭제 처리
    @PostMapping("/delete")
    public String deleteBoard(@RequestParam int boardId, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        BoardDTO boardDTO = boardService.getBoardDetail(boardId);
        if (user != null && user.getUserId().equals(boardDTO.getUserId())) {
            boardService.deleteBoard(boardId);
            return "redirect:/board/list?category=" + boardDTO.getCategory();
        } else {
            return "redirect:/user/login";
        }
    }
}
