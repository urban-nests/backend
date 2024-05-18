package com.urbannest.backend.domain.member.serive;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtToken;

public interface MemberService {
	Member login(Member member);

	JwtToken saveToken(Member member);
}
