package controller;

import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Random;

import javax.mail.internet.MimeMessage;
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
    
	@Autowired
	JavaMailSenderImpl mailSender;

	//이메일 인증
	@PostMapping("/emailAuth")
	@ResponseBody
	public int emailAuth(String email) {
		
		System.out.println("전달 받은 이메일 주소 : " + email);
		
		//난수의 범위 111111 ~ 999999 (6자리 난수)
		Random random = new Random();
		int checkNum = random.nextInt(888888)+111111;
		
		//이메일 보낼 양식
		String setFrom = "ekqlsol13@naver.com"; //2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
		String toMail = email;
		String title = "Carrot 회원가입 인증 이메일 입니다.";
		String content = "인증 코드는 " + checkNum + " 입니다." +
						 "<br>" +
						 "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage(); //Spring에서 제공하는 mail API
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("랜덤숫자 : " + checkNum);
		return checkNum;
	}
    
    
}
