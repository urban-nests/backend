package com.urbannest.backend.domain.member.service;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtToken;

public interface MemberService {
	void signup(Member member);

	Member login(Member member);

	JwtToken saveToken(Member member);

}
