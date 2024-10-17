package service.impl;

import dao.BoardDAO;
import dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BoardService;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    // 게시글 작성 (일반 게시글 및 공지사항)
    @Override
    public void writeBoard(BoardDTO boardDTO) {
        boardDAO.insertBoard(boardDTO);
    }

    // 게시글 목록 조회 (카테고리별, 공지사항 제외)
    @Override
    public List<BoardDTO> getBoardListByCategory(Map<String, Object> map) {
        return boardDAO.getBoardListByCategory(map);
    }

    // 게시글 상세 조회
    @Override
    public BoardDTO getBoardById(int boardId) {
        return boardDAO.getBoardById(boardId);
    }

    // 게시글 수정 (일반 게시글 및 공지사항)
    @Override
    public void updateBoard(BoardDTO boardDTO) {
        boardDAO.updateBoard(boardDTO);
    }

    // 게시글 삭제 (일반 게시글 및 공지사항)
    @Override
    public void deleteBoard(int boardId) {
        boardDAO.deleteBoard(boardId);
    }

    // 게시글 조회수 증가
    @Override
    public void increaseViewCount(int boardId) {
        boardDAO.increaseViews(boardId);
    }

    // 모든 게시글 조회 (공지사항 상단 고정 포함, 카테고리 선택 가능)
    @Override
    public List<BoardDTO> getAllBoardsWithNotice(Map<String, Object> map) {
        return boardDAO.getAllBoardsWithNotice(map);
    }

    // 공지사항 목록 조회 (관리자 및 일반 사용자 모두 접근 가능)
    @Override
    public List<BoardDTO> getAllNotices() {
        return boardDAO.getAllNotices();
    }
}