package service.impl;

import dao.BoardDAO;
import dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BoardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDAO boardDAO;

    private static final int PAGE_SIZE = 10;

    @Override
    public void writeBoard(BoardDTO boardDTO) {
        boardDAO.insertBoard(boardDTO);
    }

    @Override
    public Map<String, Object> getBoardList(String category, int page) {
        int startNum = (page - 1) * PAGE_SIZE;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("category", category);
        map.put("startNum", startNum);
        map.put("pageSize", PAGE_SIZE);

        List<BoardDTO> boardList = boardDAO.getBoardListByCategory(map);
        List<BoardDTO> fixedNotices = boardDAO.getFixedNotices(category);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("boardList", boardList);
        result.put("fixedNotices", fixedNotices);

        return result;
    }

    @Override
    public BoardDTO getBoardDetail(int boardId) {
        boardDAO.increaseViews(boardId);
        return boardDAO.getBoardDetail(boardId);
    }

    @Override
    public void updateBoard(BoardDTO boardDTO) {
        boardDAO.updateBoard(boardDTO);
    }

    @Override
    public void deleteBoard(int boardId) {
        boardDAO.deleteBoard(boardId);
    }

    @Override
    public List<BoardDTO> getFixedNotices(String category) {
        return boardDAO.getFixedNotices(category);
    }
}
