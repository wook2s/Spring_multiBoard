package com.jade.myapp.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.AbstractRequestAttributesScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jade.myapp.member.model.Member;
import com.jade.myapp.member.service.IMemberService;

@Controller
public class MemberController {

	@Autowired
	IMemberService memberService;

	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String memberInsert() {

		return "member/memberInsert";
	}

	@RequestMapping(value = "/member/insert", method = RequestMethod.POST)
	public String memberInsert(Member member) {
		System.out.println(member);
		memberService.memberInsert(member);

		return "redirect:/";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String memberLogin() {

		return "member/memberLogin";
	}

	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String memberLogin(String id, String pwd, HttpSession session, Model model, RedirectAttributes reattrs) {

		String result = memberService.loginCheck(id, pwd);

		if (result.equals("success")) {
			reattrs.addFlashAttribute("loginMessage", result);
			session.setAttribute("id", id);
			return "redirect:/";
		}else {
			reattrs.addFlashAttribute("loginMessage", result);
			return "redirect:/member/login";
		}

	}

	@RequestMapping(value = "/member/logout", method = RequestMethod.POST)
	public String memberLogout(Member member, HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}

	@RequestMapping(value = "/member/detail", method = RequestMethod.GET)
	public String memberDetail(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		Member member = memberService.getMemberById(id);
		model.addAttribute("member", member);
		return "member/memberDetail";
	}

	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String memberDelete(HttpSession session, RedirectAttributes reattrs) {
		String id = (String) session.getAttribute("id");
		String result = memberService.memberDelete(id);
		session.removeAttribute("id");
		reattrs.addFlashAttribute("memberDeleteMessage", result);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public String memberModify(Model model, HttpSession session) {
		String id = (String) session.getAttribute("id");
		Member member = memberService.getMemberById(id);
		model.addAttribute("member", member);
		
		return "board/memberModify";
	}
	
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModify(Member member, RedirectAttributes reattrs) {
		memberService.memberModify(member);
		return "redirect:/";
	}
	
	
	
	
	
	
}
