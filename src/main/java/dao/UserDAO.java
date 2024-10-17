package dao;

import dto.UserDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    // 회원가입
    void registerUser(UserDTO userDTO);

    // 로그인
    UserDTO loginUser(String userId);

    // 아이디 중복 체크
    int checkUserId(String userId);

    // 이메일 인증 업데이트
    void updateEmailVerified(UserDTO userDTO);

    // 회원 정보 조회
    UserDTO getUserInfo(String userId);

    // 회원 정보 수정
    void updateUserInfo(UserDTO userDTO);

    // 회원 삭제
    void deleteUser(String userId);
    
    // 모든 회원 조회 (관리자 기능)
    List<UserDTO> getAllUsers();

    // 특정 회원 조회 (관리자 기능)
    UserDTO getUserById(String userId);

    // 특정 회원 정보 수정 (관리자 기능)
    void updateUserByAdmin(UserDTO userDTO);

    // 특정 회원 삭제 (관리자 기능)
    void deleteUserByAdmin(String userId);

    // 이메일 토큰으로 회원 조회 (이메일 인증 시 사용)
    UserDTO getUserByEmailToken(String emailToken);
    
    // 새로운 회원 정보 저장
	void insertUser(UserDTO userDTO); //회원가입 시 사용자 정보 DB에 저장

}
