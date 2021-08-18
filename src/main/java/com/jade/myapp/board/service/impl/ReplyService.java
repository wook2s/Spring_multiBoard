package com.jade.myapp.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jade.myapp.board.dao.IReplyRepository;
import com.jade.myapp.board.model.Reply;
import com.jade.myapp.board.service.IReplyService;

@Service
public class ReplyService implements IReplyService{

	@Autowired
	IReplyRepository replyRepository;
	
	@Override
	public List<Reply> getReplyListByBoardId(int boardId) {
		List<Reply> replyList = replyRepository.getReplyListByBoardId(boardId);
		return replyList;
	}

}
