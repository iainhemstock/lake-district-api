package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.repository.LinkedResultPage;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.*;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper
            .registerModule(new SimpleModule().addSerializer(
                LinkedBasicFell.class,
                new LinkedBasicFellSerializer()))
            .registerModule(new SimpleModule().addSerializer(
                LinkedFell.class,
                new LinkedFellSerializer()))
            .registerModule(new SimpleModule().addSerializer(
                new TypeToken<LinkedResultPage<LinkedBasicFell>>() {}.getRawType(),
                new LinkedResultPageSerializer<>()))
            .registerModule(new SimpleModule().addSerializer(
                new TypeToken<Map<LinkRel, Link>>() {}.getRawType(),
                new LinksMapSerializer()));

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }

}
