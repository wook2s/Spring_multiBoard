package com.jade.myapp.member.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.myapp.member.model.Member;
import com.jade.myapp.member.service.IMemberService;
import com.jade.myapp.util.MemberValidator;

@Controller
public class MemberController {

	@Autowired
	IMemberService memberService;
	
	@Autowired
	private MemberValidator memberValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(memberValidator);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//----------아이디 중복확인 요청----------
	@RequestMapping(value = "/member/insertId", method = RequestMethod.GET)
	public String idDuplicateCheck() {
		return "member.memberInsertId";
	}
	//----------아이디 중복확인 요청----------
	
	//----------아이디 중복확인----------
	@RequestMapping(value = "/member/insertId", method = RequestMethod.POST)
	public String idDuplicateCheck(String id ,RedirectAttributes reattrs) {
		
		String idPattern = "[a-zA-Z0-9]{2,10}";
		boolean reg = id.matches(idPattern);
		boolean idCheck = memberService.idDuplicateCheck(id);

		if(reg == false) {
			reattrs.addFlashAttribute("idMessage", "아이디는 영문,숫자 2~10자입니다.");
			return "redirect:/member/insertId";
		}
		else if(idCheck == false) {
			reattrs.addFlashAttribute("idMessage", "아이디가 사용중입니다.");
			return "redirect:/member/insertId";
		}else {
			reattrs.addFlashAttribute("idMessage", "사용가능");
			Member member = new Member();
			member.setId(id);
			reattrs.addFlashAttribute("member", member);
			reattrs.addFlashAttribute("idCheck", "pass");
			
			return "redirect:/member/insert";
		}
	}
	//----------아이디 중복확인----------
	
	//----------회원가입 요청----------
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String memberInsert() {

		return "member.memberInsert";
	}
	//----------회원가입 요청----------

	//----------회원가입 폼 확인 후 삽입----------
	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String memberInsert(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult result, 
			Model model,
			RedirectAttributes reattrs) {
		logger.info(member.toString());
			
		if(result.hasErrors()) {
			return "member.memberInsert";
		}
		try {
			memberService.memberInsert(member);
			reattrs.addFlashAttribute("memberMessage", "회원가입 완료");
		} catch (Exception e) {
			reattrs.addFlashAttribute("memberMessage", "회원가입 실패");
		}
		
		return "redirect:/";
	}
	//----------회원가입 폼 확인 후 삽입----------

	//----------로그인 페이지 요청----------
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String memberLogin() {

		return "member.memberLogin";
	}
	//----------로그인 페이지 요청----------

	//----------로그인 처리----------
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String memberLogin(String id, String pwd, HttpSession session, Model model, RedirectAttributes reattrs) {

		String result = memberService.loginCheck(id, pwd);

		if (result.equals("success")) {
			reattrs.addFlashAttribute("loginMessage", result);
			session.setAttribute("id", id);
			//로그인 성공 시 홈으로 이동
			return "redirect:/";
		}else {
			reattrs.addFlashAttribute("loginMessage", result);
			return "redirect:/member/login";
		}

	}
	//----------로그인 처리----------

	//----------로그인아웃----------
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String memberLogout(Member member, HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}
	//----------로그인아웃----------

	//----------회원 정보 상세보기----------
	@RequestMapping(value = "/member/detail", method = RequestMethod.GET)
	public String memberDetail(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		Member member = memberService.getMemberById(id);
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	//----------회원 정보 상세보기----------

	//----------회원 정보 삭제----------
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String memberDelete(HttpSession session, RedirectAttributes reattrs) {
		String id = (String) session.getAttribute("id");
		String result = memberService.memberDelete(id);
		session.removeAttribute("id");
		reattrs.addFlashAttribute("memberDeleteMessage", result);
		return "redirect:/";
	}
	//----------회원 정보 삭제----------
	
	//----------회원 정보 수정 페이지 요청----------
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public String memberModify(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		Member member = memberService.getMemberById(id);
		model.addAttribute("member", member);
		
		return "member.memberModify";
	}
	//----------회원 정보 수정 페이지 요청----------
	
	//----------회원 정보 수정----------
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModify(
			@ModelAttribute("member") @Valid Member member, 
			BindingResult result, 
			Model model,
			RedirectAttributes reattrs) {
		
		logger.info(member.toString());
		
		if(result.hasErrors()) {
			return "member.memberModify";
		}
		
		try {
			memberService.memberModify(member);
			reattrs.addFlashAttribute("memberMessage", "회원수정 완료");
		} catch (Exception e) {
			reattrs.addFlashAttribute("memberMessage", "회원수정 실패");
		}
		return "redirect:/";
	}
	//----------회원 정보 수정----------
	
	
	
}
