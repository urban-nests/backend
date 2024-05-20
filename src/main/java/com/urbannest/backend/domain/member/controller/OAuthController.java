package com.urbannest.backend.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.constant.MemberType;
import com.urbannest.backend.domain.member.dto.OAuthLoginDto;
import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.service.OAuthLoginService;
import com.urbannest.backend.domain.member.validator.OAuthValidator;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/oauth")
@Slf4j
@RequiredArgsConstructor
public class OAuthController {
	private final OAuthValidator oAuthValidator;
	private final OAuthLoginService oAuthLoginService;

	@PostMapping("/login")
	public ResponseEntity<?> oauthLogin(@RequestBody OAuthLoginDto.Request oauthLoginRequestDto,
															 HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader("Authorization");
		log.warn(authorizationHeader);
		oAuthValidator.validateAuthorization(authorizationHeader);
		oAuthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

		String accessToken = authorizationHeader.split(" ")[1];
		JwtToken token = oAuthLoginService.oAuthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));

		response.addHeader(JwtProvider.Authorization, "Bearer " + token.accessToken());
		Cookie cookie = new Cookie("refresh-token", token.refreshToken());
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		return ResponseEntity.ok("소셜 로그인 성공");
	}
}
