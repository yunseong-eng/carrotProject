/*
package controller;


import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 회원가입 폼 이동
    @GetMapping("/register")
    public String registerForm() {
        return "/user/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO userDTO) {
        userService.registerUser(userDTO);
        // 이메일 인증 로직 추가 가능
        return "redirect:/user/login";
    }

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
            session.setAttribute("user", user); //user값이 null이 아니면 세션 생성
            return "success"; //로그인 성공시 success값 리턴, AJax로 login js에 전달
        } else {
            return "fail"; // 로그인 실패 시
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index"; // ViewResolver에 따라 /WEB-INF/index.jsp를 반환
    }

    // 내 정보 보기
    @GetMapping("/myinfo")
    public String myInfo(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("userInfo", userService.getUserInfo(user.getUserId()));
            return "user/myinfo";
        } else {
            return "redirect:/user/login";
        }
    }

    // 아이디 중복 체크 AJAX
    @RequestMapping(value = "/checkUserId", method =RequestMethod.POST)
    @ResponseBody
    public String checkUserId(@RequestParam String userId) {
        return userService.isUserIdAvailable(userId);
    }

    // 회원 정보 수정 처리
    @PostMapping("/update")
    public String updateUserInfo(@ModelAttribute UserDTO userDTO, HttpSession session) {
        userService.updateUserInfo(userDTO);
        session.setAttribute("user", userDTO);
        return "redirect:/user/myinfo";
    }

    // 회원 탈퇴 처리
    @PostMapping("/delete")
    public String deleteUser(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            userService.deleteUser(user.getUserId());
            session.invalidate();
            return "redirect:/";
        } else {
            return "redirect:/user/login";
        }
    }
}
*/