package com.jade.myapp.board.dao;

import java.util.List;

import com.jade.myapp.board.model.BoardCategory;

public interface IBoardCategoryRepository {

	List<BoardCategory> getCategoryList();

}
