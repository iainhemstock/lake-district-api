package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Allows FellController::getFell() to receive an OsMapRef path variable instead of a string
     */
    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(new Converter<String, OsMapRef>() {
            @Override
            public OsMapRef convert(final String s) {
                return new OsMapRef(s);
            }
        });
    }
}
