package com.jade.myapp.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jade.myapp.board.dao.IBoardCategoryRepository;
import com.jade.myapp.board.dao.IReplyRepository;
import com.jade.myapp.board.model.BoardCategory;
import com.jade.myapp.board.model.Reply;
import com.jade.myapp.board.service.IBoardCategoryService;
import com.jade.myapp.board.service.IReplyService;

@Service
public class BoardCategoryService implements IBoardCategoryService{

	@Autowired
	IBoardCategoryRepository boardCategoryRepository;

	@Override
	public List<BoardCategory> getCategoryList() {
		List<BoardCategory> categoryList = boardCategoryRepository.getCategoryList();
		return categoryList;
	}
	

}
