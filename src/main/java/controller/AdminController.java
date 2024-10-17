package controller;

import dto.BoardDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.AdminService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 관리자 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginForm() {
        return "admin/login";  // 로그인 페이지로 이동
    }

    // 관리자 로그인 처리
    @PostMapping("/login")
    public String loginProcess(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (adminService.authenticateAdmin(username, password)) {
            session.setAttribute("adminLogin", true);  // 세션에 관리자 로그인 상태 저장
            return "redirect:/admin/users";  // 로그인 성공 후 회원 목록 페이지로 이동
        }
        return "admin/login";  // 로그인 실패 시 로그인 페이지로 다시 이동
    }

    // 모든 회원 목록 조회 (관리자 전용)
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDTO> userList = adminService.getAllUsers();  // 모든 회원 정보 조회
        model.addAttribute("userList", userList);  // 회원 목록을 모델에 추가
        return "admin/userList";  // 회원 목록 페이지로 이동
    }

    // 특정 회원 정보 조회 및 수정 페이지로 이동
    @GetMapping("/editUser/{userId}")
    public String editUserForm(@PathVariable String userId, Model model) {
        UserDTO user = adminService.getUserById(userId);  // 특정 회원 정보 조회
        model.addAttribute("user", user);  // 회원 정보를 모델에 추가
        return "admin/editUser";  // 회원 정보 수정 페이지로 이동
    }

    // 회원 정보 수정 처리
    @PostMapping("/editUser")
    public String editUserProcess(@ModelAttribute UserDTO userDTO) {
        adminService.updateUser(userDTO);  // 회원 정보 수정 처리
        return "redirect:/admin/users";  // 수정 후 회원 목록 페이지로 이동
    }

    // 회원 삭제 처리
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") String userId) {
        adminService.deleteUser(userId);  // 회원 삭제 처리
        return "redirect:/admin/users";  // 삭제 후 회원 목록 페이지로 이동
    }

    // 공지사항 등록 페이지로 이동
    @GetMapping("/addNotice")
    public String addNoticeForm(Model model) {
        model.addAttribute("board", new BoardDTO());  // 빈 게시글 객체를 모델에 추가
        return "admin/addNotice";  // 공지사항 등록 페이지로 이동
    }

    // 공지사항 등록 처리
    @PostMapping("/addNotice")
    public String addNoticeProcess(@ModelAttribute BoardDTO boardDTO) {
        boardDTO.setNotice(true);  // 공지사항으로 설정
        adminService.addNotice(boardDTO);  // 공지사항 등록 처리
        return "redirect:/admin/boards";  // 등록 후 게시글 목록 페이지로 이동
    }

    // 게시글 목록 조회 (공지사항 상단 고정)
    @GetMapping("/boards")
    public String getBoardList(Model model) {
        List<BoardDTO> boardList = adminService.getAllBoardsWithNotice();  // 공지사항 포함 게시글 목록 조회
        model.addAttribute("boardList", boardList);  // 게시글 목록을 모델에 추가
        return "admin/boardList";  // 게시글 목록 페이지로 이동
    }

    // 게시글 수정 페이지로 이동
    @GetMapping("/editBoard/{boardId}")
    public String editBoardForm(@PathVariable int boardId, Model model) {
        BoardDTO board = adminService.getUserById(boardId);  // 특정 게시글 정보 조회
        model.addAttribute("board", board);  // 게시글 정보를 모델에 추가
        return "admin/editBoard";  // 게시글 수정 페이지로 이동
    }

    // 게시글 수정 처리
    @PostMapping("/editBoard")
    public String editBoardProcess(@ModelAttribute BoardDTO boardDTO) {
        adminService.updateBoard(boardDTO);  // 게시글 수정 처리
        return "redirect:/admin/boards";  // 수정 후 게시글 목록 페이지로 이동
    }

    // 게시글 삭제 처리
    @PostMapping("/deleteBoard")
    public String deleteBoard(@RequestParam("boardId") int boardId) {
        adminService.deleteBoard(boardId);  // 게시글 삭제 처리
        return "redirect:/admin/boards";  // 삭제 후 게시글 목록 페이지로 이동
    }
}