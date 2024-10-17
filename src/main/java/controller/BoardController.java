package controller;

import dto.BoardDTO;
import dto.CommentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.BoardService;
import service.CommentService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;

    // 전체 게시글 목록 보기
    @GetMapping("/listAll")
    public String boardListAll(Model model) {
        List<BoardDTO> boardList = boardService.getAllBoardList();
        model.addAttribute("boardList", boardList);
        return "/board/listForm"; // 전체 게시글 목록을 보여주는 페이지
    }

    // 게시글 목록 보기
    @GetMapping("/listForm")
    public String boardList(@RequestParam String category,
                            @RequestParam(defaultValue = "1") int page,
                            Model model) {
        // 전체 목록 보기
        Map<String, Object> result;
        if ("전체".equals(category)) {
            result = boardService.getBoardList("전체", page);
        } else {
            result = boardService.getBoardList(category, page);
        }
        model.addAttribute("boardList", result.get("boardList"));
        model.addAttribute("category", category);

        return "/board/listForm";
    }

    // 게시글 작성 폼 이동
    @GetMapping("/writeForm")
    public String writeForm(Model model) {
        return "/board/writeForm";
    }

    // 게시글 작성 처리 (이미지 업로드 추가)
    @PostMapping("/write")
    public String writeBoard(@ModelAttribute BoardDTO boardDTO,
                             @RequestParam("file") MultipartFile file,
                             HttpSession session) {
        // 로그인 없이 임시 userId 설정
        boardDTO.setUserId("temporaryUser");
        
        // 배송비가 없는 경우 0으로 설정
        if (boardDTO.getShippingFee() == null || boardDTO.getShippingFee().isEmpty()) {
            boardDTO.setShippingFee("0");
        }

        // 이미지 파일 처리
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String storedFileName = UUID.randomUUID().toString() + "_" + fileName;

            // 파일 저장 경로 설정 (WEB-INF/storage에 저장)
            String uploadDir = session.getServletContext().getRealPath("WEB-INF/storage");
            File dest = new File(uploadDir + "/" + storedFileName);
            try {
                file.transferTo(dest); // 파일 저장
            } catch (IOException e) {
                e.printStackTrace();
                return "error";  // 오류 발생 시 에러 페이지로 리다이렉트
            }

            // 저장된 이미지 경로를 BoardDTO에 설정
            boardDTO.setImagePath("WEB-INF/storage/" + storedFileName);
        }

        // 게시글 저장
        boardService.writeBoard(boardDTO);

        // 카테고리 값을 URL 인코딩 처리
        String encodedCategory = URLEncoder.encode(boardDTO.getCategory(), StandardCharsets.UTF_8);

        return "redirect:/board/listForm?category=" + encodedCategory;
    }

    // 게시글 상세 보기
    @GetMapping("/detailForm/{boardId}")
    public String boardDetail(@PathVariable int boardId, Model model) {
        BoardDTO boardDTO = boardService.getBoardDetail(boardId);
        model.addAttribute("board", boardDTO);

        // 댓글 목록을 조회하여 model에 추가
        List<CommentDTO> commentList = commentService.getCommentList(boardId);
        model.addAttribute("commentList", commentList);  // 댓글 목록을 모델에 추가

        return "/board/detailForm";  // 상세페이지로 이동
    }


    // 게시글 수정 폼 이동
    @GetMapping("/updateForm/{boardId}")
    public String updateForm(@PathVariable int boardId, Model model) {
        BoardDTO boardDTO = boardService.getBoardDetail(boardId);
        model.addAttribute("board", boardDTO);
        return "/board/updateForm";
    }

    // 게시글 삭제 처리
    @PostMapping("/delete")
    public String deleteBoard(@RequestParam int boardId, @RequestParam String category) {
        boardService.deleteBoard(boardId);

        // 카테고리 값을 URL 인코딩 처리
        String encodedCategory = URLEncoder.encode(category, StandardCharsets.UTF_8);

        return "redirect:/board/listForm?category=" + encodedCategory;
    }
}
