package com.urbannest.backend.external.kakaotoken.oauth.service;

import com.urbannest.backend.external.kakaotoken.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {

	OAuthAttributes getUserInfo(String accessToken);

}
