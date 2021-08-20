package com.jade.myapp.member.service;

import com.jade.myapp.member.model.Member;

public interface IMemberService {

	void memberInsert(Member member);

	String loginCheck(String id, String pwd);
	Member getMemberById(String id);

	String memberDelete(String id);

	void memberModify(Member member);
	
}
