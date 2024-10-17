package service.impl;

import dao.AdminBoardDAO;
import dao.UserDAO;
import dto.BoardDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AdminService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserDAO userDAO;  // 회원 관리 DAO

    @Autowired
    private AdminBoardDAO adminBoardDAO;  // 관리자용 게시글 관리 DAO

    // 관리자 로그인 처리
    @Override
    public boolean authenticateAdmin(String username, String password) {
        UserDTO admin = userDAO.loginUser(username);
        return admin != null && admin.getPassword().equals(password) && "ADMIN".equals(admin.getRole());
    }

    // 모든 회원 목록 조회
    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // 특정 회원 정보 조회
    @Override
    public UserDTO getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    // 회원 정보 수정 (관리자 전용)
    @Override
    public void updateUser(UserDTO userDTO) {
        userDAO.updateUserInfo(userDTO);  // 회원 정보 수정 ??? 이거 updateUserInfo가 아닐 수도 있음
    }

    // 회원 삭제 처리
    @Override
    public void deleteUser(String userId) {
        userDAO.deleteUser(userId);
    }

    // 공지사항 포함 게시글 목록 조회
    @Override
    public List<BoardDTO> getAllBoardsWithNotice() {
        return adminBoardDAO.getAllBoardsWithNotice(null);
    }

    // 게시글 정보 수정 (관리자 전용)
    @Override
    public void updateBoard(BoardDTO boardDTO) {
        adminBoardDAO.updateBoard(boardDTO);
    }

    // 게시글 삭제 (관리자 전용)
    @Override
    public void deleteBoard(int boardId) {
        adminBoardDAO.deleteBoard(boardId);
    }

    // 공지사항 등록 (관리자 전용)
    @Override
    public void addNotice(BoardDTO boardDTO) {
        // 공지사항 여부는 BoardDTO의 isNotice 필드로 전달됨
        adminBoardDAO.insertNotice(boardDTO); // 공지사항 등록을 위한 메서드 사용
    }

	@Override // -------------- 수정해야 될 부분
	public BoardDTO getUserById(int boardId) {
		// TODO Auto-generated method stub
		return null;
	}

}