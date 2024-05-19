package com.urbannest.backend.domain.member.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.urbannest.backend.domain.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtGenerator {
	private final Key key;

	public JwtGenerator(@Value("${jwt.key}") String key) {
		byte[] keyBytes = Decoders.BASE64.decode(key);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createAccessToken(Member member) {
		Claims claims = Jwts.claims();
		claims.put("email", member.getEmail());
		claims.put("nickname", member.getNickname());

		Date now = new Date();
		Date expiration = new Date(now.getTime() + (1000L*60*30));

		String token = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return token;
	}

	public String createRefreshToken(Member member) {
		Claims claims = Jwts.claims();
		claims.put("email", member.getEmail());

		Date now = new Date();
		Date expiration = new Date(now.getTime() + (1000L*60*60));

		String token = Jwts.builder()
			.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return token;
	}

}
