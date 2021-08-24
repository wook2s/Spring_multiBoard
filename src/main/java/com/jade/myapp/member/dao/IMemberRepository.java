package com.jade.myapp.member.dao;

import org.apache.ibatis.annotations.Param;

import com.jade.myapp.member.model.Member;

public interface IMemberRepository {

	void memberInsert(Member member);

	Member getMemberById(String id);

	void memberDelete(String id);

	void memberModify(Member member);

	String idDuplicateCheck(String id);

	
}
