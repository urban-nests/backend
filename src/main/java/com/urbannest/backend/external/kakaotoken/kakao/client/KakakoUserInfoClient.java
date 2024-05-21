package com.urbannest.backend.external.kakaotoken.kakao.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.urbannest.backend.external.kakaotoken.kakao.dto.KakaoUserInfoResponseDto;

import lombok.Getter;

@FeignClient(url = "https://kapi.kakao.com", name = "kakaoUserInfoClient")
public interface KakakoUserInfoClient {

	@GetMapping(value = "/v2/user/me", consumes = "applicatio/json")
	KakaoUserInfoResponseDto getKakaoUserInfo(@RequestHeader("content-type") String contentType,
													 @RequestHeader("Authorization") String accessToken);
}
