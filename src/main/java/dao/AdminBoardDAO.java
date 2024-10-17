package dao;

import dto.BoardDTO;
import dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminBoardDAO {

    // 모든 회원 목록 조회 (관리자 전용)
    List<UserDTO> getAllUsers();

    // 특정 회원 정보 조회 (관리자 전용)
    UserDTO getUserById(@Param("userId") String userId);

    // 회원 정보 수정 (관리자 전용)
    void updateUser(UserDTO userDTO);

    // 회원 삭제 (관리자 전용)
    void deleteUser(@Param("userId") String userId);

    // 모든 게시글 목록 조회 (공지사항 포함 상단 고정)
    List<BoardDTO> getAllBoardsWithNotice(Map<String, Object> params);

    // 게시글 삭제 (관리자 전용)
    void deleteBoard(@Param("boardId") int boardId);

    // 공지사항 등록 (관리자 전용)
    void insertNotice(BoardDTO boardDTO);

    // 게시글 수정 (관리자 전용)
    void updateBoard(BoardDTO boardDTO);
}