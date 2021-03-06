package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration;

import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.application_logic.FellServiceImpl;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Autowired private FellRepository fellRepository;

    @Bean
    public FellService fellService() {
        return new FellServiceImpl(fellRepository);
    }

}
