package service;

import dto.UserDTO;
import dto.BoardDTO;

import java.util.List;

public interface AdminService {

    // 관리자 로그인 처리
    boolean authenticateAdmin(String username, String password);

    // 모든 회원 목록 조회
    List<UserDTO> getAllUsers();

    // 특정 회원 정보 조회
    UserDTO getUserById(String userId);

    // 회원 정보 수정
    void updateUser(UserDTO userDTO);

    // 회원 삭제 처리
    void deleteUser(String userId);

    // 공지사항 포함 게시글 목록 조회 (공지사항을 상단에 고정)
    List<BoardDTO> getAllBoardsWithNotice();

    // 게시글 정보 수정 (관리자 전용)
    void updateBoard(BoardDTO boardDTO);

    // 게시글 삭제 처리 (관리자 전용)
    void deleteBoard(int boardId);

    // 공지사항 게시글 등록 (관리자 전용)
    void addNotice(BoardDTO boardDTO);

	BoardDTO getUserById(int boardId);
}