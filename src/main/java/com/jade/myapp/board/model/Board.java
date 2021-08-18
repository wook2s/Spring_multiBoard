package com.jade.myapp.board.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Board {

	private int BoardId;
	private int categoryId;
	private String id;
	private String title;
	private String content;
	private Date writeDate;
	
	private int readCount;
	private int replyNum;
	
	private MultipartFile file;
	private int fileId;
	private String fileName;
	private long fileSize;
	private String contentType;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBoardId() {
		return BoardId;
	}
	public void setBoardId(int boardId) {
		BoardId = boardId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Override
	public String toString() {
		return "Board [BoardId=" + BoardId + ", categoryId=" + categoryId + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", writeDate=" + writeDate + ", readCount=" + readCount + ", replyNum="
				+ replyNum + ", file=" + file + ", fileId=" + fileId + ", fileName=" + fileName + ", fileSize="
				+ fileSize + ", contentType=" + contentType + "] \n";
	}
	
}
