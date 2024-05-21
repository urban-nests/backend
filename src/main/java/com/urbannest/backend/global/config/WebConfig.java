package com.urbannest.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import com.urbannest.backend.global.interceptor.AuthenticationInterceptor;
import com.urbannest.backend.global.resolver.member.MemberArgumentResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final MemberArgumentResolver memberArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
            .order(1)
            .addPathPatterns("/api/**")
            .excludePathPatterns(
                "/api/member/signup",
                "/api/member/login",
                "/api/oauth/login",
                "/api/access-token/issue",
                "/api/member/logout");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ValidatingPageableArgumentResolver());
        resolvers.add(memberArgumentResolver);
    }



}
