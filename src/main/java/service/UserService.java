package service;

import java.util.List;

import dto.UserDTO;

public interface UserService {
    // 회원가입
    void registerUser(UserDTO userDTO);

    // 로그인
    UserDTO loginUser(String userId, String password);

    // 아이디 중복 체크
    boolean isUserIdAvailable(String userId);

    // 이메일 인증 처리
    void verifyEmail(String userId, String emailToken);

    // 회원 정보 조회
    UserDTO getUserInfo(String userId);

    // 회원 정보 수정
    void updateUserInfo(UserDTO userDTO);

    // 회원 삭제
    void deleteUser(String userId);
    
    // 모든 회원 정보 조회 (관리자)
    List<UserDTO> getAllUsers();

    // 특정 회원 정보 수정 (관리자)
    void updateUser(UserDTO userDTO);

    // 특정 회원 삭제 (관리자)
    void deleteUserByAdmin(String userId);

    // 관리자 인증
    boolean authenticateAdmin(String userId, String password);

    // 특정 회원 정보 조회
	UserDTO getUserById(String id);
}
