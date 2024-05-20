package com.urbannest.backend.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urbannest.backend.domain.member.dto.OAuthLoginDto;
import com.urbannest.backend.domain.member.validator.OAuthValidator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public class OAuthController {
	private final OAuthValidator oAuthValidator;

	@PostMapping("/login")
	public ResponseEntity<OAuthLoginDto.Response> oauthLogin(@RequestBody OAuthLoginDto.Request oauthLoginRequestDto,
															 HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		oAuthValidator.validateAuthorization(authorizationHeader);
		oAuthValidator.validateMemberType(oauthLoginRequestDto.getMemberType());

		return ResponseEntity.ok(OAuthLoginDto.Response.builder().build());
	}
}
