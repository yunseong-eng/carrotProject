package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userId;          // 아이디
    private String password;        // 비밀번호
    private String name;            // 이름
    private String phoneNumber;     // 핸드폰번호
    private String gender;          // 성별 (M, F)
    private String address;         // 주소
    private String email;           // 이메일
    private boolean emailVerified;  // 이메일 인증 여부
    private String emailToken;      // 이메일 인증 토큰
    private String role;            // 권한 (USER, ADMIN)
}
