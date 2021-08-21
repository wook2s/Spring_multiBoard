package com.jade.myapp.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jade.myapp.board.dao.IBoardRepository;
import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardFile;
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

	@Override
	@Transactional
	public void insertBoard(Board board) {
		int boardMaxNum = boardRepository.getMaxBoardId();
		board.setBoardId(boardMaxNum+1);

		System.out.println(board);
		
		boardRepository.insertBoard(board);
		
	}
	
	@Override
	@Transactional
	public void insertBoard(Board board, BoardFile file) {
		int boardMaxNum = boardRepository.getMaxBoardId();
		board.setBoardId(boardMaxNum+1);
		file.setBoardId(boardMaxNum+1);
		
		int fileMaxNum = boardRepository.getMaxFileId();
		file.setFileId(fileMaxNum+1);
		
		board.setFileName(file.getFileName());
		
		boardRepository.insertBoard(board);
		boardRepository.insertFile(file);
	}

	@Override
	public BoardFile getFileByBoardId(int boardId) {
		BoardFile file =  boardRepository.getFileByBoardId(boardId);
		return file;
	}

	@Override
	@Transactional
	public void modifyBoard(Board board, BoardFile file) {
		file.setBoardId(board.getBoardId());

		if(board.getFileName() == null || board.getFileName().equals("")) {
			file.setFileId(boardRepository.getMaxFileId()+1);
			board.setFileName(file.getFileName());
			boardRepository.insertFile(file);
			System.out.println("inserting file");

		}else {
			board.setFileName(file.getFileName());
			System.out.println("updating file");
			System.out.println(file.toString());
			
			boardRepository.updateFile(file);
		}
		
		System.out.println("updating board");
		boardRepository.updateBoard(board);
	}

	@Override
	public void modifyBoard(Board board) {
		boardRepository.updateBoard(board);
		
	}

	@Override
	@Transactional
	public void deleteBoard(int boardId) {
		boardRepository.deleteFile(boardId);
		boardRepository.deleteBoard(boardId);
	}

	@Override
	public void addReplyNum(int boardId) {
		boardRepository.addReplyNum(boardId);
	}

	@Override
	public void subReplyNum(int boardId) {
		boardRepository.subReplyNum(boardId);
	}

	@Override
	public List<Board> search(int categoryId, String option, String word) {
		
		word = "%"+word+"%";
		
		String content = null;
		String title = null;
		String id = null;
		
		if(option.equals("content")) {
			content = option;
		}else if(option.equals("title")) {
			title = option;
		}else if(option.equals("id")) {
			id = option;
		}
		
		List<Board> boardList = boardRepository.search(categoryId, content, title, id, word);
		return boardList;
	}

	@Override
	public int getTotalBoardCount(int categoryId) {
		int totalBoardCount =  boardRepository.getTotalBoardCount(categoryId);
		return totalBoardCount;
	}

	@Override
	public int getSearchTotalCount(int categoryId, String option, String word) {
		word = "%"+word+"%";
		
		String content = null;
		String title = null;
		String id = null;
		
		if(option.equals("content")) {
			content = option;
		}else if(option.equals("title")) {
			title = option;
		}else if(option.equals("id")) {
			id = option;
		}
		
		int totalCount = boardRepository.getSearchTotalCount(categoryId, content, title, id, word);
		return totalCount;
	}


}
