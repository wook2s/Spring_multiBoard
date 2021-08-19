package com.jade.myapp.board.service;

import java.util.List;

import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardFile;

public interface IBoardService {

	List<Board> getAllBoardListByCategory(int categoryId, int nowPage);
	Board getBoard(int boardId);
	void addReadCount(int boardId);
	
	void insertBoard(Board board, BoardFile file);
	void insertBoard(Board board);
	BoardFile getFileByBoardId(int boardId);
	void modifyBoard(Board board, BoardFile file);
	void modifyBoard(Board board);
	void deleteBoard(int boardId);
	void addReplyNum(int boardId);
	void subReplyNum(int boardId);

}
