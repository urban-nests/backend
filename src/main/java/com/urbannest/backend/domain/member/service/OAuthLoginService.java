package com.urbannest.backend.domain.member.service;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.urbannest.backend.domain.member.constant.MemberType;
import com.urbannest.backend.domain.member.dto.OAuthLoginDto;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.validator.OAuthValidator;
import com.urbannest.backend.external.kakaotoken.oauth.model.OAuthAttributes;
import com.urbannest.backend.external.kakaotoken.oauth.service.SocialLoginApiService;
import com.urbannest.backend.external.kakaotoken.oauth.service.SocialLoginApiServiceFactory;

import io.jsonwebtoken.Jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OAuthLoginService {

	private final MemberService memberService;
	private final JwtProvider jwtProvider;
	public JwtToken oAuthLogin(String idToken) {
//		SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
//		OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);

//		log.warn("OAuthLoginService.oAuthLogin : {}", userInfo.toString());
// 1. 검증없이 디코딩
		// JWT 토큰

		// JWT 디코딩
		DecodedJWT decodedJWT = JWT.decode(idToken);

		// email과 nickname 추출
		String email = decodedJWT.getClaim("email").asString();
		String nickname = decodedJWT.getClaim("nickname").asString();

		Optional<Member> member = memberService.findMemberByEmail(email);

		JwtToken token = null;
		if(member.isEmpty()) { // 신규 회원
			Member signupMember = Member.builder()
				.email(email)
				.password(UUID.randomUUID().toString())
				.nickname(nickname)
				.build();
			memberService.signup(signupMember);
			token = memberService.saveToken(signupMember);
		}
		else { // 신규 회원이 아님
			Member oauthMember = member.get();
			token = memberService.saveToken(oauthMember);
		}

		return token;
	}
}
