package com.urbannest.backend.domain.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.serive.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberController {
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody Member member) {
		log.info("MemberController.signup() is called.");
		memberService.signup(member);

		// Response Object에 대한 고민 필요
		return ResponseEntity.ok("회원가입 성공");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
		log.info("MemberController.login() is called");
		log.warn(member.toString());
		// 로그인
		Member result = memberService.login(member);

		JwtToken token = null;
		if(result != null) { // 로그인 성공
			// 토큰 생성
			token = memberService.saveToken(member);
		}

		// token만 반환해도 되는가?
		return ResponseEntity.ok(token);
	}
}
