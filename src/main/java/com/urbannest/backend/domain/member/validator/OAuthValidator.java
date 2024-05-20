package com.urbannest.backend.domain.member.validator;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.urbannest.backend.domain.member.constant.MemberType;
import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.AuthenticationException;
import com.urbannest.backend.global.error.exception.BusinessException;


@Service
public class OAuthValidator {
	public void validateAuthorization(String authorizationHeader) {
		if (!StringUtils.hasText(authorizationHeader)) {
			throw new AuthenticationException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
		}

		String[] authorizations = authorizationHeader.split(" ");
		if (authorizations.length < 2 || "Bearer".equals(authorizations[0])) {
			throw new AuthenticationException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
		}
	}

	public void validateMemberType(String memberType) {
		if(!MemberType.isMemberType(memberType)) {
			throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
		}
	}
}
