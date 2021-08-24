package com.jade.myapp.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jade.myapp.member.dao.IMemberRepository;
import com.jade.myapp.member.model.Member;
import com.jade.myapp.member.service.IMemberService;

@Service
public class MemberService implements IMemberService{

	@Autowired
	IMemberRepository memberRepository;

	@Override
	public void memberInsert(Member member) {
		memberRepository.memberInsert(member);
	}

	@Override
	public String loginCheck(String id, String pwd) {
		Member member = memberRepository.getMemberById(id);
		
		if(member == null) {
			return "idFail";
		}
		else if (member != null && !member.getPwd().equals(pwd)) {
			return "pwdFail";
		}else {
			return "success";
		}
		
	}

	@Override
	public Member getMemberById(String id) {
		Member member = memberRepository.getMemberById(id);
		return member;
	}

	@Override
	@Transactional
	public String memberDelete(String id) {
		memberRepository.memberDelete(id);
		Member member = memberRepository.getMemberById(id);
		
		String message ="";
		if(member == null) {
			message = "deleteSuccess";
		}else {
			message = "deleteFail";
		}
		return message;
	}

	@Override
	public void memberModify(Member member) {
		memberRepository.memberModify(member);
	}

	@Override
	public boolean idDuplicateCheck(String id) {
		String _id = memberRepository.idDuplicateCheck(id);
		boolean result = false;
		if(_id == null || _id.equals("")) {
			result = true;
		}
		
		return result;
	}
}
