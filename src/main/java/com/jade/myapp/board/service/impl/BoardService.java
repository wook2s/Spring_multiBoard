package com.jade.myapp.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jade.myapp.board.dao.IBoardRepository;
import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.service.IBoardService;

@Service
public class BoardService implements IBoardService{

	@Autowired
	IBoardRepository boardRepository;
	
	@Override
	public List<Board> getAllBoardListByCategory(int categoryId, int nowPage) {
		
		int startPage = (nowPage-1)*10 +1;
		int endPage = nowPage*10;
		List<Board> boardList = boardRepository.getAllBoardListByCategory(categoryId, startPage, endPage);
		return boardList;
	}
	
	@Override
	public Board getBoard(int boardId) {
		Board board =  boardRepository.getBoard(boardId);
		return board;
	}

	@Override
	public void addReadCount(int boardId) {
		boardRepository.addReadCount(boardId);
	}

}
