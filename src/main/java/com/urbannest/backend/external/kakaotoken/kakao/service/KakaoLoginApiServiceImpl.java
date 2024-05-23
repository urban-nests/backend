package com.urbannest.backend.external.kakaotoken.kakao.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.urbannest.backend.external.kakaotoken.kakao.client.KakakoUserInfoClient;
import com.urbannest.backend.external.kakaotoken.kakao.dto.KakaoUserInfoResponseDto;
import com.urbannest.backend.external.kakaotoken.oauth.model.OAuthAttributes;
import com.urbannest.backend.external.kakaotoken.oauth.service.SocialLoginApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

	private final KakakoUserInfoClient kakaoUserInfoClient;
	private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset='utf-8";

	@Override
	public OAuthAttributes getUserInfo(String accessToken) {
		KakaoUserInfoResponseDto kakaoUserInfoResponseDto =	kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE, "Bearer " + accessToken);

		KakaoUserInfoResponseDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
		String nickname = kakaoAccount.getProfile().getNickname();
		String email = kakaoAccount.getEmail();

		return OAuthAttributes.builder()
			.nickname(nickname)
			.email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email)
			.build();
	}
}
