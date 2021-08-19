package com.jade.myapp.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	@Transactional
	public void replyInsert(Reply reply) {
		int maxReplyNum = replyRepository.getMaxReplyId();
		reply.setReplyId(maxReplyNum+1);
		replyRepository.replyInsert(reply);
	}

	@Override
	public void replyDeleteByBoardId(int boardId) {
		replyRepository.replyDeleteByBoardId(boardId);
	}

	@Override
	public void replyDeleteByReplyId(int replyId) {
		replyRepository.replyDeleteByReplyId(replyId);
	}

	@Override
	public void replyModify(Reply reply) {
		replyRepository.replyModify(reply);		
	}

}
