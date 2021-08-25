package com.jade.myapp.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
	
	
	//----------메인 페이지 요청----------
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		//각 게시판 별 최대조회수 글을 뷰에 전달
		Board[] boards = new Board[3];
		for(int i=0; i<boards.length; i++) {
			boards[i] = boardService.getMaxViewByCategoryId(i+1);
			model.addAttribute("board"+(i+1), boards[i]);
		}
		
		return "board.home";
	}
	//----------메인 페이지 요청----------
	
	
	//----------카테고리 별 게시글 리스트 요청----------
	@RequestMapping(value = "/board/list/{categoryId}/{nowPage}")
	public String getAllBoardListByCategory(
			@PathVariable int categoryId, 
			@PathVariable int nowPage, 
			Model model,
			HttpServletRequest request,
			HttpSession session) {
		List<Board> boardList = boardService.getAllBoardListByCategory(categoryId, nowPage);
		int totalBoardCount = boardService.getTotalBoardCount(categoryId);
		
		//페이저 설정을 위한 페이지, 총 게시물 정보 전달
		int lastPage = 0;
		if(totalBoardCount % 10 == 0) {
			lastPage = totalBoardCount/10;
		}else if(totalBoardCount % 10 != 0) {
			lastPage = totalBoardCount/10 +1;
		}
		
		session.setAttribute("categoryId", categoryId);
		
		//페이저 재사용을 위한 페이지 uri정보 전달
		String pathTmp = request.getRequestURI();
		int tmp = pathTmp.lastIndexOf("/");
		String path = pathTmp.substring(0, tmp+1);
		
		model.addAttribute("path", path);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalBoardCount", totalBoardCount);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("lastPage", lastPage);
		
		return "board.listPage";
	}
	//----------카테고리 별 게시글 리스트 요청----------
	
	//----------카테고리 별 게시글 리스트 요청 (페이지 정보가 없을 때 기본값)----------
	@RequestMapping(value = "/board/list/{categoryId}")
	public String getAllBoardListByCategory(@PathVariable int categoryId, Model model, HttpServletRequest request) {
		
		return getAllBoardListByCategory(categoryId, 1, model, request, request.getSession());
	}
	//----------카테고리 별 게시글 리스트 요청 (페이지 정보가 없을 때 기본값)----------
	
	//----------게시글 자세히보기 ----------
	@RequestMapping(value = "/board/detail/{categoryId}/{boardId}")
	public String getBoardDetail(
			@PathVariable int boardId,
			@PathVariable int categoryId,
			Model model, 
			HttpSession session) {
		//조회수 1 추가
		boardService.addReadCount(boardId);
		//댓글 리스트 전달
		List<Reply> replyList = replyService.getReplyListByBoardId(boardId);
		Board board = boardService.getBoard(boardId);
		
		model.addAttribute("id", (String)session.getAttribute("id"));
		model.addAttribute("replyList",replyList);
		model.addAttribute("board", board);
		
		return "board.boardDetail";
	}
	//----------게시글 자세히보기 ----------
	
	//----------2번 카테고리 글 이미지 출력하기 ---------- 
	@RequestMapping(value="/board/detail/img/2/{boardId}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable int boardId){
		BoardFile file = boardService.getFileByBoardId(boardId);
		final HttpHeaders headers = new HttpHeaders();
		
		if(file != null) {
			logger.info("getFile" + file.toString());
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0],mtypes[1]));
			headers.setContentLength(file.getFileSize());
			headers.setContentDispositionFormData("attachment", file.getFileName() , Charset.forName("UTF-8"));
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	//----------2번 카테고리 글 이미지 출력하기 ---------- 

	//----------3번 카테고리 글 영상 출력하기 ---------- 
	@RequestMapping(value="/board/detail/video/3/{boardId}")
	public ResponseEntity<byte[]> getVideoFile(@PathVariable int boardId){
		BoardFile file = boardService.getFileByBoardId(boardId);
		final HttpHeaders headers = new HttpHeaders();
		
		if(file != null) {
			logger.info("getFile" + file.toString());
			String[] mtypes = file.getFileContentType().split("/");
			headers.setContentType(new MediaType(mtypes[0],mtypes[1]));
			headers.setContentLength(file.getFileSize());
			headers.setContentDispositionFormData("attachment", file.getFileName() , Charset.forName("UTF-8"));
			return new ResponseEntity<byte[]>(file.getFileData(),headers,HttpStatus.OK);
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	//----------3번 카테고리 글 영상 출력하기 ---------- 

	//----------첨부파일 다운로드 ---------- 
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
	//----------첨부파일 다운로드 ---------- 
	
	//----------글쓰기 페이지 요청 ---------- 
	@RequestMapping(value = "/board/insert", method = RequestMethod.GET)
	public String insertBoard(Model model, HttpSession session) {
		List<BoardCategory> categoryList = boardCategoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("id", (String)session.getAttribute("id"));
		model.addAttribute("categoryId", session.getAttribute("categoryId"));
		return "board.insertBoard";
	}
	//----------글쓰기 페이지 요청 ---------- 
	
	//----------게시글 업로드---------- 
	@RequestMapping(value = "/board/insert", method = RequestMethod.POST)
	public String insertBoard(Board board, RedirectAttributes reattrs) {
		logger.info("inserting new board");
		try {
			//태그 제거
			board.setTitle(Jsoup.clean(board.getTitle(), Whitelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Whitelist.basic()));
			MultipartFile mfile = board.getFile();
			logger.info(board.toString());
			//첨부파일이 있을 시 저장
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
			
			//첨부파일이 없을 시 게시글만 저장
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
	//----------게시글 업로드---------- 
	
	//----------게시글 수정 페이지 요청---------- 
	@RequestMapping(value = "/board/modify/{categoryId}/{boardId}", method = RequestMethod.GET)
	public String boardModify(Model model, @PathVariable int boardId) {
		Board board =boardService.getBoard(boardId);
		model.addAttribute("board", board);
		
		return "board.boardModify";
	}
	//----------게시글 수정 페이지 요청---------- 

	//----------게시글 수정 페이지 업로드---------- 
	@RequestMapping(value = "/board/modify/{categoryId}/{boardId}", method = RequestMethod.POST)
	public String boardModify(Board board, RedirectAttributes reattrs, String preFileName) {
		logger.info("modify board");
		try {
			//기존 파일과 비교하여 파일 교체
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
		
		return "redirect:/board/detail/"+board.getCategoryId()+"/"+board.getBoardId();
	}
	//----------게시글 수정 페이지 업로드---------- 
	
	//----------게시글 삭제 요청---------- 
	@RequestMapping(value = "/board/delete/{categoryId}/{boardId}")
	public String boardDelete(@PathVariable int categoryId, @PathVariable int boardId) {

		replyService.replyDeleteByBoardId(boardId);
		boardService.deleteBoard(boardId);
		
		return "redirect:/board/list/"+categoryId+"/"+1;
	}
	//----------게시글 삭제 요청---------- 
	
	//----------댓글 쓰기---------- 
	@RequestMapping(value = "/board/detail/replyInsert/{categoryId}/{boardId}", method = RequestMethod.POST)
	public String replyInsert(@PathVariable int categoryId, @PathVariable int boardId, Reply reply) {
		//태그 제거
		reply.setContent(Jsoup.clean(reply.getContent(), Whitelist.basic()));
		replyService.replyInsert(reply);
		boardService.addReplyNum(boardId);
		
		return "redirect:/board/detail/"+categoryId+"/"+boardId;
	}
	//----------댓글 쓰기---------- 
	
	//----------댓글 삭제---------- 
	@RequestMapping(value = "/board/detail/replyDelete/{categoryId}/{boardId}/{replyId}", method = RequestMethod.GET)
	public String replyDelete(@PathVariable int categoryId, @PathVariable int boardId, @PathVariable int replyId) {
		replyService.replyDeleteByReplyId(replyId);
		boardService.subReplyNum(boardId);
		return "redirect:/board/detail/"+categoryId+"/"+boardId;
	}
	//----------댓글 삭제---------- 
	
	//----------댓글 수정 페이지 요청---------- 
	@RequestMapping(value = "/board/detail/replyModify/{categoryId}/{boardId}/{replyId}", method = RequestMethod.GET)
	public String replyModify(@PathVariable int boardId, @PathVariable int replyId, Model model) {
		Board board = boardService.getBoard(boardId);
		List<Reply> replyList = replyService.getReplyListByBoardId(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("replyId", replyId);
		model.addAttribute("replyList", replyList);
		
		return "board.replyModify";
	}
	//----------댓글 수정 페이지 요청---------- 

	
	//----------댓글 수정---------- 
	@RequestMapping(value = "/board/detail/replyModify/{categoryId}/{boardId}/{replyId}", method = RequestMethod.POST)
	public String replyModify(Reply reply,@PathVariable int categoryId, @PathVariable int boardId, @PathVariable int replyId) {
		reply.setContent(Jsoup.clean(reply.getContent(), Whitelist.basic()));
		replyService.replyModify(reply);
		
		return "redirect:/board/detail/"+categoryId+"/"+boardId;
	}
	//----------댓글 수정---------- 

	//----------검색 요청 시 UTF-8 인코딩---------- 
	@RequestMapping(value = "/board/search", method = RequestMethod.POST)
	public String search(String categoryId, String option, String word, String nowPage) throws IOException {
		String encodedParam = URLEncoder.encode(word, "UTF-8");
		
		return "redirect:/board/search/"+categoryId+"/"+option+"/"+encodedParam+"/"+nowPage;
	}
	//----------검색 요청 시 UTF-8 인코딩---------- 
	
	//----------검색 페이지 전달---------- 
	@RequestMapping(value = "/board/search/{categoryId}/{option}/{word}/{nowPage}", method =RequestMethod.GET)
	public String search(
			@PathVariable String categoryId, 
			@PathVariable String option,
			@PathVariable String word,
			@PathVariable String nowPage,
			Model model,
			HttpServletRequest request) {
		
		int _categoryId = Integer.parseInt(categoryId);
		int _nowPage = Integer.parseInt(nowPage);
		int lastPage = 0;
		
		//페이저 재사용을 위한 uri값 전달
		String pathTmp = request.getRequestURI();
		int tmp = pathTmp.lastIndexOf("/");
		String path = pathTmp.substring(0, tmp+1);
		model.addAttribute("path", path);
		
		
		List<Board> boardList = boardService.search(_categoryId, option, word, _nowPage);
		int searchTotalCount = boardService.getSearchTotalCount(_categoryId, option, word);
		
		if(searchTotalCount % 10 == 0) {
			lastPage = searchTotalCount / 10;
		}else {
			lastPage = searchTotalCount/10 +1;
		}
		
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("nowPage", _nowPage);
		model.addAttribute("totalBoardCount", searchTotalCount);
		model.addAttribute("boardList", boardList);
		return "board.listPage";
	}
	//----------검색 페이지 전달---------- 
	
	@RequestMapping("/board/maxView/{categoryId}")
	public void getMaxView(int categoryId, Model model) {
		Board board = boardService.getMaxViewByCategoryId(categoryId);
		model.addAttribute("maxViewBoard", board);
	}
	
}
