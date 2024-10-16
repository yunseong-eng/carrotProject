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
    public void registerUser(UserDTO userDTO) {
        userDAO.registerUser(userDTO);
    }

    @Override
    public UserDTO loginUser(String userId, String password) {
        UserDTO user = userDAO.loginUser(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // 로그인 실패
    }

    @Override
    public boolean isUserIdAvailable(String userId) {
        return userDAO.checkUserId(userId) == 0;
    }

    @Override
    public void verifyEmail(String userId, String emailToken) {
        UserDTO userDTO = userDAO.getUserInfo(userId);
        if (userDTO != null && userDTO.getEmailToken().equals(emailToken)) {
            userDTO.setEmailVerified(true);
            userDAO.updateEmailVerified(userDTO);
        }
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return userDAO.getUserInfo(userId);
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        userDAO.updateUserInfo(userDTO);
    }

    @Override
    public void deleteUser(String userId) {
        userDAO.deleteUser(userId);
    }
}
