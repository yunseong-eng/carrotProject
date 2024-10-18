package service.impl;

import dao.UserDAO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDTO loginUser(String userId, String password) {
        UserDTO user = userDAO.loginUser(userId);

        if (user == null) {
            System.out.println("로그인 실패: 입력한 ID가 없습니다.");
            return null; // 아이디가 존재하지 않음
        }

        // 비밀번호 검증
        if (user.getPassword().equals(password)) {
            System.out.println("로그인 성공: " + user.getUserId());
            return user;
        } else {
            System.out.println("로그인 실패: 비밀번호가 틀렸습니다.");
            return null;
        }
    }
}
