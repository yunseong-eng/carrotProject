package controller;

import dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BoardService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 작성 페이지 이동 (GET)
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new BoardDTO());  // 빈 BoardDTO 객체를 모델에 추가
        return "board/write";  // 게시글 작성 페이지로 이동
    }

    // 게시글 작성 처리 (POST)
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute BoardDTO boardDTO) {
        boardService.writeBoard(boardDTO);  // 게시글 작성 처리
        return "redirect:/board/list";  // 작성 후 게시글 목록으로 리다이렉트
    }

    // 게시글 목록 조회 (카테고리별, 공지사항 제외)
    @GetMapping("/list")
    public String getBoardList(@RequestParam Map<String, Object> params, Model model) {
        List<BoardDTO> boardList = boardService.getBoardListByCategory(params);  // 게시글 목록 조회
        model.addAttribute("boardList", boardList);  // 조회된 게시글 목록을 모델에 추가
        return "board/list";  // 게시글 목록 페이지로 이동
    }

    // 게시글 상세 조회 (GET)
    @GetMapping("/detail/{boardId}")
    public String getBoardDetail(@PathVariable int boardId, Model model) {
        BoardDTO board = boardService.getBoardById(boardId);  // 게시글 상세 정보 조회
        boardService.increaseViewCount(boardId);  // 조회수 증가 처리
        model.addAttribute("board", board);  // 게시글 상세 정보를 모델에 추가
        return "board/detail";  // 게시글 상세 페이지로 이동
    }

    // 게시글 수정 페이지 이동 (GET)
    @GetMapping("/edit/{boardId}")
    public String editForm(@PathVariable int boardId, Model model, HttpSession session) {
        BoardDTO board = boardService.getBoardById(boardId);

        // 공지사항이면 관리자만 수정 가능
        if (board.getPostType().equals("공지사항") && !isAdmin(session)) {
            return "redirect:/login";  // 관리자가 아니면 로그인 페이지로 리다이렉트
        }

        // 일반 게시글은 작성자 또는 관리자만 수정 가능
        if (!board.getPostType().equals("공지사항") && !isOwnerOrAdmin(session, board.getUserId())) {
            return "redirect:/login";  // 작성자 본인 또는 관리자가 아니면 로그인 페이지로 리다이렉트
        }

        model.addAttribute("board", board);
        return "board/edit";
    }

    // 게시글 수정 처리 (POST)
    @PostMapping("/edit")
    public String editBoard(@ModelAttribute BoardDTO boardDTO, HttpSession session) {
        // 공지사항이면 관리자만 수정 가능
        if (boardDTO.getPostType().equals("공지사항") && !isAdmin(session)) {
            return "redirect:/login";
        }

        // 일반 게시글은 작성자 또는 관리자만 수정 가능
        if (!boardDTO.getPostType().equals("공지사항") && !isOwnerOrAdmin(session, boardDTO.getUserId())) {
            return "redirect:/login";
        }

        boardService.updateBoard(boardDTO);  // 게시글 수정 처리
        return "redirect:/board/detail/" + boardDTO.getBoardId();
    }

    // 게시글 삭제 처리 (POST)
    @PostMapping("/delete")
    public String deleteBoard(@RequestParam int boardId, HttpSession session) {
        BoardDTO board = boardService.getBoardById(boardId);

        // 공지사항이면 관리자만 삭제 가능
        if (board.getPostType().equals("공지사항") && !isAdmin(session)) {
            return "redirect:/login";
        }

        // 일반 게시글은 작성자 또는 관리자만 삭제 가능
        if (!board.getPostType().equals("공지사항") && !isOwnerOrAdmin(session, board.getUserId())) {
            return "redirect:/login";
        }

        boardService.deleteBoard(boardId);  // 게시글 삭제 처리
        return "redirect:/board/list";
    }

    // 모든 게시글 조회 (공지사항 상단 고정 포함)
    @GetMapping("/all")
    public String getAllBoardsWithNotice(@RequestParam Map<String, Object> params, Model model) {
        List<BoardDTO> boardList = boardService.getAllBoardsWithNotice(params);  // 공지사항 상단 고정 포함한 게시글 목록 조회
        model.addAttribute("boardList", boardList);  // 조회된 게시글 목록을 모델에 추가
        return "board/all";  // 모든 게시글 조회 페이지로 이동
    }

    // 공지사항 목록 조회 (모든 사용자 접근 가능)
    @GetMapping("/notices")
    public String getNoticeList(Model model) {
        List<BoardDTO> notices = boardService.getAllNotices();  // 공지사항 목록 조회
        model.addAttribute("notices", notices);  // 공지사항 목록을 모델에 추가
        return "board/notices";  // 공지사항 목록 페이지로 이동
    }

    // 관리자 권한 체크 메서드
    private boolean isAdmin(HttpSession session) {
        Object isAdmin = session.getAttribute("adminLogin");
        return isAdmin != null && (boolean) isAdmin;
    }

    // 작성자 또는 관리자 권한 체크 메서드
    private boolean isOwnerOrAdmin(HttpSession session, String userId) {
        Object currentUserId = session.getAttribute("userId");
        return isAdmin(session) || (currentUserId != null && currentUserId.equals(userId));
    }
}