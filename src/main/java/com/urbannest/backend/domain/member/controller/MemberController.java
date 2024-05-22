package com.urbannest.backend.domain.member.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.entity.RefreshToken;
import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.repository.RefreshTokenRepository;
import com.urbannest.backend.domain.member.service.MemberService;
import com.urbannest.backend.global.resolver.member.MemberDto;
import com.urbannest.backend.global.resolver.member.MemberInfo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final RefreshTokenRepository refreshTokenRepository;


	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Member member) {
		log.info("MemberController.signup() is called.");
		memberService.signup(member);

		return ResponseEntity.ok("회원가입 성공");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member, HttpServletResponse response) {
		log.info("MemberController.login() is called");
		log.warn(member.toString());
		// 로그인
		Member result = memberService.login(member);

		JwtToken token = null;
		if (result != null) { // 로그인 성공
			// 토큰 생성
			token = memberService.saveToken(result);
		}

		// RFC5719에 따라 Jwt는 Authorization Header에 포함하기로 합의
		response.addHeader(JwtProvider.Authorization, "Bearer " + token.accessToken());
		Cookie cookie = new Cookie("refresh-token", token.refreshToken());
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);

		return ResponseEntity.ok("로그인 성공");
	}

	@GetMapping("/memberinfo")
	public MemberDto getInfo(@MemberInfo MemberDto memberDto) {
		return memberDto;
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			refreshToken = Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals("refresh-token"))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);
		}

		if (refreshToken != null) {
//			JwtDB.JwtDB.remove(refreshToken);
			refreshTokenRepository.delete(RefreshToken.builder().refreshToken(refreshToken).build());

			// 쿠키 삭제
			Cookie cookie = new Cookie("refresh-token", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		return ResponseEntity.ok("로그아웃 성공");
	}
}
