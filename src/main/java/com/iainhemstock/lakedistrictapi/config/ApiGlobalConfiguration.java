package com.iainhemstock.lakedistrictapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.iainhemstock.lakedistrictapi.dtos.FellSimplifiedDTO;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.serializers.PagedCollectionDtoSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApiGlobalConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
//        registerPagedCollectionSerializer(objectMapper);
        return objectMapper;
    }

    // this allows a generic type serializer to be used with objectmapper
//    private void registerPagedCollectionSerializer(ObjectMapper objectMapper) {
//        CollectionLikeType type = objectMapper.getTypeFactory().constructCollectionLikeType(PagedCollectionDTO.class, FellSimplifiedDTO.class);
//        objectMapper.registerModule(new SimpleModule().addSerializer(new PagedCollectionDtoSerializer(type)));
//    }

}
