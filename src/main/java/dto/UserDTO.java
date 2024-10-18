package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userId; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
}
