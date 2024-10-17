package dao;

import dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardDAO {

    // 게시글 작성 (공지사항 및 일반 게시글 구분)
    void insertBoard(BoardDTO boardDTO);

    // 게시글 목록 조회 (카테고리별)
    List<BoardDTO> getBoardListByCategory(Map<String, Object> map);

    // 게시글 상세 조회 (게시글 수정 페이지로 이동할 때 사용)
    BoardDTO getBoardById(int boardId); // getBoardDetail 메서드 제거

    // 게시글 수정
    void updateBoard(BoardDTO boardDTO);

    // 게시글 삭제 (공지사항 삭제도 통합)
    void deleteBoard(int boardId);

    // 조회수 증가
    void increaseViews(int boardId);
    
    // 모든 게시글 조회 (공지사항 상단 고정 포함, 카테고리 선택 가능)
    List<BoardDTO> getAllBoardsWithNotice(Map<String, Object> map); // 카테고리별 공지사항 조회 가능하도록 수정

    // 공지사항 목록 조회 (관리자용)
    List<BoardDTO> getAllNotices(); // 공지사항 전용 조회 (관리자 페이지)
}