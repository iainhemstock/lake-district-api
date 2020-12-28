package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.repository;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.FellRepositoryImpl;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryFactory {

    @Autowired private FellEntityRepository fellEntityRepository;
    @Autowired private DomainToEntityAssembler domainToEntityAssembler;

    @Bean
    public FellRepository fellRepository() {
        return new FellRepositoryImpl(fellEntityRepository, domainToEntityAssembler);
    }

}
