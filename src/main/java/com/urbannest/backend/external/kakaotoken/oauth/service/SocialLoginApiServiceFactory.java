package com.urbannest.backend.external.kakaotoken.oauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SocialLoginApiServiceFactory {

	// Factory Pattern
	private static Map<String, SocialLoginApiService> socialLoginApiServices;

	public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
		this.socialLoginApiServices = socialLoginApiServices;
	}

	public static SocialLoginApiService getSocialLoginApiService(String type) {
		String socialLoginApiServiceBeanName = "";

		if(type.equals("Kakao")) {
			socialLoginApiServiceBeanName = "KakaoLoginApiServiceImpl";
		}

		return 	socialLoginApiServices.get(socialLoginApiServiceBeanName);
	}
}
