package com.urbannest.backend.global.resolver.member;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.urbannest.backend.domain.member.jwt.JwtProvider;

import io.jsonwebtoken.Claims;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {
	private final JwtProvider jwtProvider;
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// true면 revolseArgument 실행
		boolean hasMemberInfoAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
		boolean hasMemberDto = MemberDto.class.isAssignableFrom(parameter.getParameterType());
		return hasMemberDto && hasMemberInfoAnnotation;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		String authorizationHeader = request.getHeader("Authorization");
		String accessToken = authorizationHeader.split(" ")[1];
		log.info(accessToken);
		Claims claims = jwtProvider.parseClaims(accessToken);
		String email = claims.get("email").toString();
		String nickname = claims.get("nickname").toString();
		log.info("email : {}", email);
		log.info("nickname : {}", nickname);



		return MemberDto.builder()
			.email(email)
			.nickname(nickname)
			.build();
	}
}
