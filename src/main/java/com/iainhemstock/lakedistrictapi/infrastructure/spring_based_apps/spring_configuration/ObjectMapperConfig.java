package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.Links;
import com.iainhemstock.lakedistrictapi.domain.SimpleFell;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.dtos.ItemDTO;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.serialization.*;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new SimpleModule().addSerializer(new TypeToken<ItemDTO<Fell>>() {}.getRawType(), new ItemDTOSerializer<>()));
        mapper.registerModule(new SimpleModule().addSerializer(Links.class, new LinksSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(Fell.class, new FellSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(SimpleFell.class, new SimpleFellSerializer()));
        mapper.registerModule(new SimpleModule().addSerializer(new TypeToken<RepoPage<SimpleFell>>() {}.getRawType(), new RepoResultSerializer<>()));

        return mapper;
    }

}
