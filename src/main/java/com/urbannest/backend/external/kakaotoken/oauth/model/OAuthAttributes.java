package com.urbannest.backend.external.kakaotoken.oauth.model;

import com.urbannest.backend.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthAttributes {

	private String name;
	private String email;
	private String profile;
	private String memberType;

	public Member toMemberEntity() {
		return Member.builder()
			.email(email)
			.build();
	}
}
