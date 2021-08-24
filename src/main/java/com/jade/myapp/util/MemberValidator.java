package com.jade.myapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jade.myapp.member.model.Member;

@Component
public class MemberValidator implements Validator {

	static final String idPattern = "[a-zA-Z0-9]{2,10}";
	static final String pwdPattern = ".{4,10}";
	static final String namePattern = "[a-zA-Z]{2,20}";
	static final String emailPattern = "^[a-zA-Z]{1,}.{1,}@[a-zA-Z]{1,}[.](com)|(net)|(org)$";
	static final String phonePattern = "^0\\d{1,2}-\\d{3,4}-\\d{4}";

	@Override
	public boolean supports(Class<?> clazz) {

		return Member.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;

		String id = member.getId();
		if (!id.matches(idPattern)) {
			errors.rejectValue("id", "idError", "아이디는 2~10자입니다.");
		}

		String pwd = member.getPwd();
		if (!pwd.matches(pwdPattern)) {
			errors.rejectValue("pwd", "pwdError", "비밀번호는 4~10자입니다");
		}
		
		String name = member.getName();
		if(!name.matches(namePattern)) {
			errors.rejectValue("name", "nameError", "이름은 2~20자입니다");
		}

		String email = member.getEmail();
		if (!email.matches(emailPattern)) {
			errors.rejectValue("email", "emailError", "이메일 형식이 맞지 않습니다.");
		}

		String phone = member.getPhone();
		if (!phone.matches(phonePattern)) {
			errors.rejectValue("phone", "phoneError", "전화번호 형식이 맞지 않습니다.");
		}

	}

}
