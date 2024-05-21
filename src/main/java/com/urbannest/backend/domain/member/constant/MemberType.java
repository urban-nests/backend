package com.urbannest.backend.domain.member.constant;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MemberType {
	KAKAO;

	public static MemberType from(String memberType) {
		return MemberType.valueOf(memberType.toUpperCase());
	}

	public static boolean isMemberType(String type) {
		List<MemberType> memberTypes = Arrays.stream(MemberType.values())
			.filter(memberType -> memberType.name().equals(type))
			.collect(Collectors.toList());

		return memberTypes.size() != 0;
	}
}
