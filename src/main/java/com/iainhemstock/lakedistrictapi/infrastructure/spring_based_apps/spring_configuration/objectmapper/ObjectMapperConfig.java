package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.FellSerializer;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.LinksSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule().addSerializer(Fell.class, new FellSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(Links.class, new LinksSerializer()));

        return mapper;
    }

}
