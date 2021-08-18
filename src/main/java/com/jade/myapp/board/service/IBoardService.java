package com.jade.myapp.board.service;

import java.util.List;

import com.jade.myapp.board.model.Board;

public interface IBoardService {

	List<Board> getAllBoardListByCategory(int categoryId, int nowPage);
	Board getBoard(int boardId);
	void addReadCount(int boardId);

}
