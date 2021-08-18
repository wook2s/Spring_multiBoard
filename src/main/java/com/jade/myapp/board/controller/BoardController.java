package com.jade.myapp.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardCategory;
import com.jade.myapp.board.model.Reply;
import com.jade.myapp.board.service.IBoardCategoryService;
import com.jade.myapp.board.service.IBoardService;
import com.jade.myapp.board.service.IReplyService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	IBoardService boardService;
	@Autowired
	IBoardCategoryService boardCategoryService;
	@Autowired
	IReplyService replyService;
	
	@RequestMapping(value = "/board/list/{categoryId}/{nowPage}")
	public String getAllBoardListByCategory(@PathVariable int categoryId, @PathVariable int nowPage, Model model) {
		List<Board> boardList = boardService.getAllBoardListByCategory(categoryId, nowPage);
		model.addAttribute("boardList", boardList);
		
		return "board/listPage";
	}
	
	@RequestMapping(value = "/boardList/{categoryId}")
	public String getAllBoardListByCategory(@PathVariable int categoryId, Model model) {
		
		return getAllBoardListByCategory(categoryId, 1, model);
	}
	
	@RequestMapping(value = "/board/detail/{boardId}")
	public String getBoardDetail(@PathVariable int boardId, Model model) {
		boardService.addReadCount(boardId);
		List<Reply> replyList = replyService.getReplyListByBoardId(boardId);
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("replyList",replyList);
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
	
	@RequestMapping(value = "/board/insert", method = RequestMethod.GET)
	public String insertBoard(Model model) {
		List<BoardCategory> categoryList = boardCategoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "board/insertBoard";
	}
	
	@RequestMapping(value = "/board/insert", method = RequestMethod.POST)
	public String insertBoard(Board board, RedirectAttributes reattrs) {
		
		
		
		
		
		return "redirect:/board/list/"+board.getCategoryId();
	}
	
	
}




