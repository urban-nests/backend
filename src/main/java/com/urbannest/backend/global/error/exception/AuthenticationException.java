package com.urbannest.backend.global.error.exception;

import com.urbannest.backend.global.error.ErrorCode;

public class AuthenticationException extends BusinessException {

	public AuthenticationException(ErrorCode errorCode) {
		super(errorCode);
	}
}
