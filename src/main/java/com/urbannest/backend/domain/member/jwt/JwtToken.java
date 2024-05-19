package com.urbannest.backend.domain.member.jwt;

public record JwtToken(String accessToken, String refreshToken) {
}
