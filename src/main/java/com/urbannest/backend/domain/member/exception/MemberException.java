package com.urbannest.backend.domain.member.exception;

import com.urbannest.backend.global.error.ErrorCode;
import com.urbannest.backend.global.error.exception.BusinessException;

public class MemberException extends BusinessException {
	public MemberException(ErrorCode errorCode) {

		super(errorCode);
	}
}
