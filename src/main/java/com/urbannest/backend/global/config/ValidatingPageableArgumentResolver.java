package com.urbannest.backend.global.config;

import com.urbannest.backend.domain.housedeal.exception.PageablePageOutOfBoundException;
import com.urbannest.backend.domain.housedeal.exception.PageableSizeOutOfBoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class ValidatingPageableArgumentResolver extends PageableHandlerMethodArgumentResolver {
    @Override
    public Pageable resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        String size = webRequest.getParameter(getParameterNameToUse(getSizeParameterName(), methodParameter));
        validateSize(size);
        return super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
    }

    private void validateSize(String size) {
        if (size==null || size.isBlank() || Integer.parseInt(size) > 10) {
            log.error("page size validate 실패");
            throw new PageableSizeOutOfBoundException();
        }
    }
}
