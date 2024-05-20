package com.urbannest.backend.external.kakaotoken.oauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.urbannest.backend.domain.member.constant.MemberType;

@Service
public class SocialLoginApiServiceFactory {

	// Factory Pattern
	private static Map<String, SocialLoginApiService> socialLoginApiServices;

	public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
		this.socialLoginApiServices = socialLoginApiServices;
	}

	public static SocialLoginApiService getSocialLoginApiService(MemberType type) {
		String socialLoginApiServiceBeanName = "";

		if(type.KAKAO.equals(type)) {
			socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl";
		}

		return socialLoginApiServices.get(socialLoginApiServiceBeanName);
	}
}
