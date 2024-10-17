package service.impl;

import dao.UserDAO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    // 회원가입
    @Override
    public void registerUser(UserDTO userDTO) {
        if (isUserIdAvailable(userDTO.getUserId())) {
            userDAO.insertUser(userDTO); // 새로운 회원 정보 DB에 저장
        } else {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    // 로그인
    @Override
    public UserDTO loginUser(String userId, String password) {
        UserDTO user = userDAO.getUserById(userId); // 아이디로 회원 정보 조회
        if (user != null && user.getPassword().equals(password)) {
            return user; // 로그인 성공
        } else {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    // 아이디 중복 체크
    @Override
    public boolean isUserIdAvailable(String userId) {
        return userDAO.getUserById(userId) == null; // 아이디가 존재하지 않으면 사용 가능
    }

    // 이메일 인증 처리
    @Override
    public void verifyEmail(String userId, String emailToken) {
        UserDTO user = userDAO.getUserById(userId);
        if (user != null && user.getEmailToken().equals(emailToken)) {
            user.setEmailVerified(true); // 이메일 인증 완료 처리
            userDAO.updateUserInfo(user);
        } else {
            throw new IllegalArgumentException("잘못된 인증 토큰입니다.");
        }
    }

    // 회원 정보 조회
    @Override
    public UserDTO getUserInfo(String userId) {
        return userDAO.getUserById(userId); // 회원 정보 반환
    }

    // 회원 정보 수정
    @Override
    public void updateUserInfo(UserDTO userDTO) {
        userDAO.updateUserInfo(userDTO); // 회원 정보 업데이트
    }

    // 회원 삭제
    @Override
    public void deleteUser(String userId) {
        userDAO.deleteUser(userId); // 회원 삭제
    }

    // 모든 회원 정보 조회 (관리자 전용)
    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers(); // 모든 회원 정보 조회
    }

    // 특정 회원 정보 수정 (관리자 전용)
    @Override
    public void updateUser(UserDTO userDTO) {
        userDAO.updateUserInfo(userDTO); // 특정 회원 정보 수정
    }

    // 특정 회원 삭제 (관리자 전용)
    @Override
    public void deleteUserByAdmin(String userId) {
        userDAO.deleteUser(userId); // 관리자에 의한 회원 삭제
    }

    // 관리자 인증
    @Override
    public boolean authenticateAdmin(String userId, String password) {
        UserDTO user = userDAO.getUserById(userId);
        return user != null && user.getPassword().equals(password) && "ADMIN".equals(user.getRole());
    }

    // 특정 회원 정보 조회
    @Override
    public UserDTO getUserById(String userId) {
        return userDAO.getUserById(userId); // 특정 회원 정보 조회
    }
}