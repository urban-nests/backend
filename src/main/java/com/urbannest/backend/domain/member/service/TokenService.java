package com.urbannest.backend.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.urbannest.backend.domain.member.entity.RefreshToken;
import com.urbannest.backend.domain.member.jwt.JwtGenerator;
import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.repository.RefreshTokenRepository;
import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.AuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {
	private final JwtProvider jwtProvider;
	private final MemberService memberService;
	private final JwtGenerator jwtGenerator;
	private final RefreshTokenRepository refreshTokenRepository;

	public String createAccessToken(String refreshToken) {
		jwtProvider.validateToken(refreshToken);

//		Member member = JwtDB.JwtDB.get(refreshToken);
		Optional<RefreshToken> token = refreshTokenRepository.findById(refreshToken);

		if (token.isEmpty()) {
			throw new AuthenticationException(ErrorCode.REFRESH_NOT_FOUND);
		}

		log.warn(token.get().getMember().toString());
		String accessToken = jwtGenerator.createAccessToken(token.get().getMember());
		return accessToken;
	}
}
