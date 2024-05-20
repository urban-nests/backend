package com.urbannest.backend.domain.member.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.urbannest.backend.domain.member.constant.MemberType;
import com.urbannest.backend.domain.member.dto.OAuthLoginDto;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.validator.OAuthValidator;
import com.urbannest.backend.external.kakaotoken.oauth.model.OAuthAttributes;
import com.urbannest.backend.external.kakaotoken.oauth.service.SocialLoginApiService;
import com.urbannest.backend.external.kakaotoken.oauth.service.SocialLoginApiServiceFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OAuthLoginService {

	private final MemberService memberService;
	public JwtToken oAuthLogin(String accessToken, MemberType memberType) {
		SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
		OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);

//		log.warn("OAuthLoginService.oAuthLogin : {}", userInfo.toString());

		Optional<Member> member = memberService.findMemberByEmail(userInfo.getEmail());

		JwtToken token = null;
		if(member.isEmpty()) { // 신규 회원
			Member oauthMember = userInfo.toMemberEntity();
			Member signupMember = Member.builder()
				.email(oauthMember.getEmail())
				.password(UUID.randomUUID().toString())
				.nickname(oauthMember.getNickname())
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
