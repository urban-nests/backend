package com.urbannest.backend.global.config;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.urbannest.backend.global.error.FeignClientExceptionErrorDecoder;

@Configuration
@EnableFeignClients(basePackages = "com.urbannest")
@Import(FeignClientsConfiguration.class)
public class FeignConfig {
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL; // reqeust, response의 headers, body, matadata 모두 로깅
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new FeignClientExceptionErrorDecoder();
	}

	@Bean
	public Retryer retryer() {
		// 재시도를 위한 Retryer
		return new Retryer.Default(1000, 2000, 3);
	}
}
