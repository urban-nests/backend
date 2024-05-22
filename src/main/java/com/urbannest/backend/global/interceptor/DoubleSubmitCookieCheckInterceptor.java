package com.urbannest.backend.global.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Arrays;

@Slf4j
@Component
public class DoubleSubmitCookieCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("dscc interceptor in");

        String headerToken = request.getHeader("CSRF_HEADER_TK");
        String cookieToken = null;

        Cookie[] cookies = request.getCookies();
        Cookie first = Arrays.stream(cookies)
                .filter(cookie -> "CSRF_COOKIE_TK".equals(cookie.getName()))
                .findFirst().orElse(null);

        if (first != null) {
            cookieToken = first.getValue();
            first.setValue("");
            first.setMaxAge(0);
            response.addCookie(first);
        }

        if (cookieToken == null || !cookieToken.equals(headerToken)) {
            log.info("csrf token not match");
            response.sendRedirect("/");
            return false;
        }

        log.info("cookieToken = {}", cookieToken);
        log.info("headerToken = {}", headerToken);
        return true;
    }
}
