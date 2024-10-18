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
        // 이메일 인증 로직 추가 가능 --해야함
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
        session.invalidate(); //로그아웃시 세션 삭제
        return "redirect:/"; //페이지 리다이렉트
    }
    
    // 내 정보 보기
    @RequestMapping(value = "/myinfo") //myinfo URL로 요청이 들어올 때 해당 메서드를 호출
    public String myInfo(HttpSession session, Model model) { //Http를 통해 세션에서 사용자 정보를 가져오고, Model을 통해 뷰에 데이터 전달.
        UserDTO user = (UserDTO) session.getAttribute("user"); //세션에서 userDTO를 가져와서 user에 저장
        if (user != null) {
            model.addAttribute("userInfo", userService.getUserInfo(user.getUserId())); //user.userID로 DB에서 조회후 model에 userInfo라는 이름으로 추가
            return "/user/myinfo"; //뷰 페이지 반환
        } else { //유저 정보가 없을경우
        	System.out.println("myInfo 정보가 없습니다.");
            return "redirect:/user/login"; //유저 정보가 없을때 로그인 페이지로 리다이렉트
        }
    }

    // 아이디 중복 체크 AJAX
    @RequestMapping(value = "/checkUserId", method =RequestMethod.POST)
    @ResponseBody
    public String checkUserId(@RequestParam String userId) {
        return userService.isUserIdAvailable(userId);
    }
    
    //비밀번호 체크 AJax
    @RequestMapping(value = "/checkUserPwd", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserPwd(@RequestParam String userId, @RequestParam String nowpwd) {
    	return userService.checkUserPwd(userId, nowpwd);
    }

    // 회원 정보 수정 처리
    @PostMapping("/update")
    public String updateUserInfo(@ModelAttribute UserDTO userDTO, HttpSession session) {
        userService.updateUserInfo(userDTO);
        session.setAttribute("user", userDTO);
        return "/user/myinfo";
    }
    
    // 회원탈퇴 폼 이동
    @GetMapping("/delete")
    public String deleteForm(HttpSession session) {
    	UserDTO user = (UserDTO) session.getAttribute("user");
    	if(user != null) {
    		return "/user/delete";
    	}
    	else {
    		 System.out.println("deleteForm 유저정보 없음");
        	 return "redirect:/user/login";
    	}
    }

    // 회원 탈퇴 처리
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String deleteUser(@RequestParam String userId, @RequestParam String nowpwd, HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            userService.deleteUser(user.getUserId());
            session.invalidate();
            System.out.println("세션 삭제, 회원탈퇴 완료");
            return "success";
        } else {
        	 System.out.println("delete 유저정보 없음");
        	 return "redirect:/user/login";
        }
    }
}
