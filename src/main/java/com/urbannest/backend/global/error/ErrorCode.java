package com.urbannest.backend.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "001", "토큰 만료"),
	NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "002", "유효하지 않은 토큰"),
	NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, "003", "Authorization Header is Empty"),
	NOT_VALID_BEARER_GRANT_TYPE(HttpStatus.UNAUTHORIZED, "004", "인증 타입 Bearer 아님"),
	INVALID_MEMBER_TYPE(HttpStatus.BAD_REQUEST, "005", "잘못된 회원 타입");


	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;
}
