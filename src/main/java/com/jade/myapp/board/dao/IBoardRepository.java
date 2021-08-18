package com.jade.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jade.myapp.board.model.Board;

public interface IBoardRepository {

	List<Board> getAllBoardListByCategory(
			@Param(value = "categoryId") int categoryId, 
			@Param(value = "startPage")int startPage, 
			@Param(value = "endPage")int endPage);

	Board getBoard(@Param(value = "boardId") int boardId);

	void addReadCount(@Param(value = "boardId") int boardId);

}
