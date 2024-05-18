package com.urbannest.backend.domain.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.serive.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Member member) {
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
