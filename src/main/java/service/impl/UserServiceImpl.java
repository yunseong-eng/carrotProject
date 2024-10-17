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
        //System.out.println("DB pwd : " + user.getPassword());
        //if (user != null && user.getPassword().equals(password)) {
        //	System.out.println("user" + user);
        //    return user;
        //}
        //return null; // 로그인 실패
        
        // user가 null인지 확인
        if (user == null) {
            System.out.println("로그인 실패: 사용자 ID가 존재하지 않음.");
            return null; // 사용자 ID가 존재하지 않음
        }
        
        // 비밀번호 비교
        if (user.getPassword().equals(password)) {
            System.out.println("로그인 성공: " + user);
            return user;
        } else {
            System.out.println("로그인 실패: 비밀번호가 일치하지 않음.");
            return null; // 비밀번호가 일치하지 않음
        }
        
    }

    @Override
    public String isUserIdAvailable(String userId) {
    	UserDTO userDTO = userDAO.checkUserId(userId);
    	
    	if(userDTO == null)
    		return "non_exist";
    	else
    		return "exist";    	
    }

    @Override
    public void verifyEmail(String userId, String emailToken) {
		/*
		 * UserDTO userDTO = userDAO.getUserInfo(userId); if (userDTO != null &&
		 * userDTO.getEmailToken().equals(emailToken)) { userDTO.setEmailVerified(true);
		 * userDAO.updateEmailVerified(userDTO); }
		 */
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
