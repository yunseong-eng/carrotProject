package dao;

import dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    // 로그인
    UserDTO loginUser(String userId);
}
