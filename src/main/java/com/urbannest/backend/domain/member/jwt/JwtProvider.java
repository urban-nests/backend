package com.urbannest.backend.domain.member.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	private final Key key;

	public JwtProvider(@Value("${jwt.key}") String key) {
		byte[] keyBytes = Decoders.BASE64.decode(key);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	// JWT 복호화 후, 데이터 반환
	public Authentication getAuthentication(String acessToken) {

		// JWT 복호화
		Claims claims = parseClaims(acessToken);

		if(claims.get("auth") == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}

		// claims에서 정보 추출


	}

	// Validate


}
