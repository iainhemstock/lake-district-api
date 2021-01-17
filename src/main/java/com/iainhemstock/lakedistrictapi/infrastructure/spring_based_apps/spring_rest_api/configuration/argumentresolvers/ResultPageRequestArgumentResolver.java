package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.argumentresolvers;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPageRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ResultPageRequestArgumentResolver implements HandlerMethodArgumentResolver {
    private ApiProperties apiProperties;

    public ResultPageRequestArgumentResolver(final ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
    }

    @Override
    public boolean supportsParameter(final MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(ResultPageRequest.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter methodParameter,
                                  final ModelAndViewContainer modelAndViewContainer,
                                  final NativeWebRequest nativeWebRequest,
                                  final WebDataBinderFactory webDataBinderFactory) {
        String offsetParam = nativeWebRequest.getParameter("offset");
        String limitParam = nativeWebRequest.getParameter("limit");
        String sortParam = nativeWebRequest.getParameter("sort");

        if (offsetParam == null) offsetParam = String.valueOf(apiProperties.getPageOffset());
        if (limitParam == null) limitParam = String.valueOf(apiProperties.getPageSize());
        if (sortParam == null) sortParam = apiProperties.getPageSort();

        return ResultPageRequest.of(Integer.parseInt(offsetParam), Integer.parseInt(limitParam), sortParam);
    }
}
