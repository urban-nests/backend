package com.urbannest.backend.external.kakaotoken.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.urbannest.backend.domain.member.constant.MemberType;
import com.urbannest.backend.domain.member.controller.OAuthController;
import com.urbannest.backend.domain.member.dto.OAuthLoginDto;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.service.OAuthLoginService;
import com.urbannest.backend.external.kakaotoken.client.KakaoTokenClient;
import com.urbannest.backend.external.kakaotoken.dto.KakaoTokenDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class KakaoTokenController {

	private final KakaoTokenClient kakaoTokenClient;
	private final RestTemplate restTemplate;
	private final OAuthLoginService oAuthLoginService;

	@Value("${kakao.client.id}")
	private String clientId;

	@Value("${kakao.client.secret")
	private String clientSecret;

	@GetMapping("/login")
	public String login() {
		return "OAuthForm";
	}

	@GetMapping("/oauth/kakao/callback")
	public @ResponseBody ResponseEntity<?> loginCallback(String code, HttpServletResponse response) {
		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
		KakaoTokenDto.Request kakaoTokenRequestDto = KakaoTokenDto.Request.builder()
			.client_id(clientId)
			.client_secret(clientSecret)
			.grant_type("authorization_code")
			.code(code)
			.redirect_uri("http://localhost:8080/oauth/kakao/callback")
			.build();

		KakaoTokenDto.Response kakaoToken = kakaoTokenClient.requestKakaoToken(contentType, kakaoTokenRequestDto);
		log.info(kakaoToken.toString());

		JwtToken token = oAuthLoginService.oAuthLogin(kakaoToken.getId_token());
		response.setHeader("Authorization", "Bearer " + token.accessToken());
		Cookie cookie = new Cookie("refresh-token", token.refreshToken());
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		return ResponseEntity.ok("카카오 소셜 로그인 성공");
	}
}
