package com.jade.myapp.board.service;

import java.util.List;

import com.jade.myapp.board.model.Reply;

public interface IReplyService {

	List<Reply> getReplyListByBoardId(int boardId);

}
