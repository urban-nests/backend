package com.urbannest.backend.domain.member.service;

import org.springframework.stereotype.Service;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtDB;
import com.urbannest.backend.domain.member.jwt.JwtGenerator;
import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.AuthenticationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtProvider jwtProvider;
	private final MemberService memberService;
	private final JwtGenerator jwtGenerator;

	public String createAccessToken(String refreshToken) {
		jwtProvider.validateToken(refreshToken);

		Member member = JwtDB.JwtDB.get(refreshToken);
		if(member == null) {
			throw new AuthenticationException(ErrorCode.REFRESH_NOT_FOUND);
		}

		String accessToken = jwtGenerator.createAccessToken(member);
		return accessToken;
	}
}
