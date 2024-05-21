package com.urbannest.backend.external.kakaotoken.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.urbannest.backend.external.kakaotoken.dto.KakaoTokenDto;

@FeignClient(url = "https://kauth.kakao.com", name = "kakaoTokenClient")

public interface KakaoTokenClient {
	@PostMapping(value = "/oauth/token", consumes = "appilcation/json")
	KakaoTokenDto.Response requestKakaoToken(@RequestHeader("Content-Type") String contentType, @SpringQueryMap KakaoTokenDto.Request request);
}
