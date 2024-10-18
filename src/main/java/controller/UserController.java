package controller;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 로그인 폼 이동
    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        UserDTO user = userService.loginUser(userId, password);
        if (user != null) {
            session.setAttribute("user", user); // 로그인 성공 시 세션에 유저 정보 저장
            return "success"; // 로그인 성공 시
        } else {
            return "fail"; // 로그인 실패 시
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화 (로그아웃)
        return "redirect:/"; // 로그아웃 후 홈으로 리다이렉트
    }
}
