package service;

import dto.UserDTO;

public interface UserService {
    // 로그인
    UserDTO loginUser(String userId, String password);
}
