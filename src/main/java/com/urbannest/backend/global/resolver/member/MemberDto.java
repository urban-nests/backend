package com.urbannest.backend.global.resolver.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDto {
	private String email;
	private String nickname;
}
