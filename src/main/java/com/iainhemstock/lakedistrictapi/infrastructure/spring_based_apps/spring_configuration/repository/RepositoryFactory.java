package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.FellRepositoryImpl;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryFactory {

    @Autowired private FellEntityRepository fellEntityRepository;

    @Bean
    public FellRepository fellRepository() {
        return new FellRepositoryImpl(fellEntityRepository);
    }

}
