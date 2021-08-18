package com.jade.myapp.board.model;

import java.sql.Date;

public class Reply {

	private int replyId;
	private int boardId;
	private String id;
	private String content;
	private Date wrteDate;
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWrteDate() {
		return wrteDate;
	}
	public void setWrteDate(Date wrteDate) {
		this.wrteDate = wrteDate;
	}
	
	
}
