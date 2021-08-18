package com.jade.myapp.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jade.myapp.board.model.Reply;

public interface IReplyRepository {

	List<Reply> getReplyListByBoardId(@Param(value = "boardId")int boardId);

}
