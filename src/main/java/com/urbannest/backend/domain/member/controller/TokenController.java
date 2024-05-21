package com.urbannest.backend.domain.member.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.service.TokenService;
import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.AuthenticationException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/token")
@Slf4j
@RequiredArgsConstructor
public class TokenController {

	private final TokenService tokenService;

	@PostMapping("/access-token/issue")
	public ResponseEntity<?> createAccessToken(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		String refreshToken = null;
		if (cookies != null) {
			refreshToken =  Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals("refresh-token"))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);
		}

		String accessToken = tokenService.createAccessToken(refreshToken);
		log.info("access-token : {}", accessToken);

		// RFC5719에 따라 Jwt는 Authorization Header에 포함하기로 합의
		response.addHeader(JwtProvider.Authorization, "Bearer " + accessToken);
		return ResponseEntity.ok("access-token 재발급 성공");
	}
}
