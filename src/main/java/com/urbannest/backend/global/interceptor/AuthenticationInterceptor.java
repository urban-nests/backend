package com.urbannest.backend.global.interceptor;

import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.urbannest.backend.domain.member.jwt.JwtProvider;
import com.urbannest.backend.domain.member.service.TokenService;
import com.urbannest.backend.domain.member.validator.OAuthValidator;
import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.AuthenticationException;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
	private final OAuthValidator oAuthValidator;
	private final JwtProvider jwtProvider;
	private final TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String authorizationHeader = request.getHeader("Authorization");
		oAuthValidator.validateAuthorization(authorizationHeader);
		String accessToken = authorizationHeader.split(" ")[1];
		try {
			log.info(accessToken);
			jwtProvider.validateToken(accessToken);
		}
		catch (ExpiredJwtException e) { // access token expire
			Cookie[] cookies = request.getCookies();
			if (cookies == null) { // no refresh token
				throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
			}
			String	refreshToken =  Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals("refresh-token"))
				.map(Cookie::getValue)
				.findFirst()
				.orElse(null);

			// reissue access token
			String reIssuedAccessToken = tokenService.createAccessToken(refreshToken);
			response.setHeader("Authorization", "Bearer " + reIssuedAccessToken);
			return false;
		}


		return true;
	}
}
