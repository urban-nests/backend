package com.urbannest.backend.global.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.urbannest.backend.domain.member.validator.OAuthValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

	private final OAuthValidator oAuthValidator;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String authorizationHeader = request.getHeader("Authorization");
		oAuthValidator.validateAuthorization(authorizationHeader);

		return true;
	}
}
