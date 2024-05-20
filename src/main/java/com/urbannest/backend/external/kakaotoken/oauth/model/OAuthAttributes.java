package com.urbannest.backend.external.kakaotoken.oauth.model;

import com.urbannest.backend.domain.member.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class OAuthAttributes {

	private String nickname;
	private String email;
	private String profile;
	private String memberType;

	public Member toMemberEntity() {
		return Member.builder()
			.nickname(nickname)
			.email(email)
			.build();
	}
}
