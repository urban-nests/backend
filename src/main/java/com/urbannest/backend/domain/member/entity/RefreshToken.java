package com.urbannest.backend.domain.member.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.urbannest.backend.domain.member.jwt.JwtGenerator;

import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash(value = "refresh-token", timeToLive = JwtGenerator.REFRESH_TOKEN_TTL)
@Builder
public class RefreshToken {
	@Id
	private String refreshToken;
	private Member member;
}
