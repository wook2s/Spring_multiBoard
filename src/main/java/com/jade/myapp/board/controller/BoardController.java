package com.jade.myapp.board.controller;

import java.io.Reader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.myapp.board.model.Board;
import com.jade.myapp.board.model.BoardCategory;
import com.jade.myapp.board.model.BoardFile;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("id", (String)session.getAttribute("id"));
		return "board.home";
	}
	
	
	@RequestMapping(value = "/board/list/{categoryId}/{nowPage}")
	public String getAllBoardListByCategory(@PathVariable int categoryId, @PathVariable int nowPage, Model model) {
		List<Board> boardList = boardService.getAllBoardListByCategory(categoryId, nowPage);
		model.addAttribute("boardList", boardList);
		
		return "board.listPage";
	}
	
	@RequestMapping(value = "/board/list/{categoryId}")
	public String getAllBoardListByCategory(@PathVariable int categoryId, Model model) {
		
		return getAllBoardListByCategory(categoryId, 1, model);
	}
	
	@RequestMapping(value = "/board/detail/{boardId}")
	public String getBoardDetail(@PathVariable int boardId, Model model, HttpSession session) {
		boardService.addReadCount(boardId);
		List<Reply> replyList = replyService.getReplyListByBoardId(boardId);
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("id", (String)session.getAttribute("id"));
		model.addAttribute("replyList",replyList);
		model.addAttribute("board", board);
		
		return "board.boardDetail";
	}
	
	@RequestMapping(value = "/board/detail/downloadFile/{boardId}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable int boardId) {
		BoardFile file = boardService.getFileByBoardId(boardId);
		final HttpHeaders headers = new HttpHeaders();
		String[] fileTypes = file.getFileContentType().split("/");
		
		headers.setContentType(new MediaType(fileTypes[0], fileTypes[1]));
		headers.setContentDispositionFormData("attachment", file.getFileName(), Charset.forName("UTF-8"));
		headers.setContentLength(file.getFileSize());
		
		ResponseEntity<byte[]> downFile = new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
		
		return downFile;
	}
	
	@RequestMapping(value = "/board/insert", method = RequestMethod.GET)
	public String insertBoard(Model model, HttpSession session) {
		List<BoardCategory> categoryList = boardCategoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("id", (String)session.getAttribute("id"));
		return "board.insertBoard";
	}
	
	@RequestMapping(value = "/board/insert", method = RequestMethod.POST)
	public String insertBoard(Board board, RedirectAttributes reattrs) {
		logger.info("inserting new board");
		try {
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			MultipartFile mfile = board.getFile();
			logger.info(board.toString());
			if(mfile != null && !mfile.isEmpty()) {
				logger.info("file setting start");
				BoardFile file= new BoardFile(); 
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("file setting end : "+mfile.getOriginalFilename());
				logger.info(board.toString());
				logger.info(file.toString());
				boardService.insertBoard(board, file);
			}else {
				boardService.insertBoard(board);
			}
			reattrs.addFlashAttribute("okMessage","게시글 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
			reattrs.addFlashAttribute("errorMessage",e.getMessage());
		}
		
		return "redirect:/board/list/"+board.getCategoryId();
	}
	
	@RequestMapping(value = "/board/modify/{boardId}", method = RequestMethod.GET)
	public String boardModify(Model model, @PathVariable int boardId) {
		Board board =boardService.getBoard(boardId);
		model.addAttribute("board", board);
		
		return "board.boardModify";
	}
	@RequestMapping(value = "/board/modify/{boardId}", method = RequestMethod.POST)
	public String boardModify(Board board, RedirectAttributes reattrs, String preFileName) {
		logger.info("modify board");
		try {
			board.setFileName(preFileName);
			System.out.println("기존 파일 이름 : "+preFileName);
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			MultipartFile mfile = board.getFile();
			logger.info(board.toString());
			if(mfile != null && !mfile.isEmpty()) {
				logger.info("file setting start");
				BoardFile file= new BoardFile(); 
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("file setting end : "+mfile.getOriginalFilename());
				logger.info(board.toString());
				logger.info(file.toString());
				boardService.modifyBoard(board, file);
			}else {
				boardService.modifyBoard(board);
			}
			reattrs.addFlashAttribute("okMessage","게시글 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
			reattrs.addFlashAttribute("errorMessage",e.getMessage());
		}
		
		return "redirect:/board/detail/"+board.getBoardId();
	}
	
	@RequestMapping(value = "/board/delete/{boardId}")
	public String boardDelete(@PathVariable int boardId) {
		int returnCategory = boardService.getBoard(boardId).getCategoryId();

		replyService.replyDeleteByBoardId(boardId);
		boardService.deleteBoard(boardId);
		
		return "redirect:/board/list/"+returnCategory;
	}
	
	@RequestMapping(value = "/board/detail/replyInsert/{boardId}", method = RequestMethod.POST)
	public String replyInsert(@PathVariable int boardId, Reply reply) {
		
		reply.setContent(Jsoup.clean(reply.getContent(), Whitelist.basic()));
		replyService.replyInsert(reply);
		boardService.addReplyNum(boardId);
		
		return "redirect:/board/detail/"+boardId;
	}
	
	@RequestMapping(value = "/board/detail/replyDelete/{boardId}/{replyId}", method = RequestMethod.GET)
	public String replyDelete(@PathVariable int boardId, @PathVariable int replyId) {
		replyService.replyDeleteByReplyId(replyId);
		boardService.subReplyNum(boardId);
		return "redirect:/board/detail/"+boardId;
	}
	
	@RequestMapping(value = "/board/detail/replyModify/{boardId}/{replyId}", method = RequestMethod.GET)
	public String replyModify(@PathVariable int boardId, @PathVariable int replyId, Model model) {
		Board board = boardService.getBoard(boardId);
		List<Reply> replyList = replyService.getReplyListByBoardId(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("replyId", replyId);
		model.addAttribute("replyList", replyList);
		
		return "board.replyModify";
	}
	
	@RequestMapping(value = "/board/detail/replyModify/{boardId}/{replyId}", method = RequestMethod.POST)
	public String replyModify(Reply reply,@PathVariable int boardId, @PathVariable int replyId) {
		reply.setContent(Jsoup.clean(reply.getContent(), Whitelist.basic()));
		replyService.replyModify(reply);
		
		return "redirect:/board/detail/"+boardId;
	}
}




