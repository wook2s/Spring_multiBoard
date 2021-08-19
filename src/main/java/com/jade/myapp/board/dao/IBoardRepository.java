package com.jade.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardFile;

public interface IBoardRepository {

	List<Board> getAllBoardListByCategory(
			@Param(value = "categoryId") int categoryId, 
			@Param(value = "startPage")int startPage, 
			@Param(value = "endPage")int endPage);

	Board getBoard(@Param(value = "boardId") int boardId);

	void addReadCount(@Param(value = "boardId") int boardId);

	void insertBoard(Board board);
	void insertFile(BoardFile file);

	int getMaxBoardId();
	int getMaxFileId();

	BoardFile getFileByBoardId(int boardId);

	void updateBoard(Board board);

	void updateFile(BoardFile file);

	void deleteBoard(int boardId);
	void deleteFile(int boardId);

	void addReplyNum(int boardId);

	void subReplyNum(int boardId);


}
