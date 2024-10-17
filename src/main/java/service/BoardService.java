package service;

import dto.BoardDTO;
import java.util.List;
import java.util.Map;

public interface BoardService {

    // 게시글 작성 (공지사항 및 일반 게시글 구분)
    void writeBoard(BoardDTO boardDTO);

    // 게시글 목록 조회 (카테고리별, 공지사항 제외)
    List<BoardDTO> getBoardListByCategory(Map<String, Object> map);

    // 게시글 상세 조회
    BoardDTO getBoardById(int boardId);

    // 게시글 수정
    void updateBoard(BoardDTO boardDTO);

    // 게시글 삭제 (공지사항 삭제도 통합)
    void deleteBoard(int boardId);

    // 조회수 증가
    void increaseViewCount(int boardId);

    // 모든 게시글 조회 (공지사항 상단 고정 포함, 카테고리 선택 가능)
    List<BoardDTO> getAllBoardsWithNotice(Map<String, Object> map);

    // 공지사항 목록 조회 (관리자 전용)
    List<BoardDTO> getAllNotices();
}