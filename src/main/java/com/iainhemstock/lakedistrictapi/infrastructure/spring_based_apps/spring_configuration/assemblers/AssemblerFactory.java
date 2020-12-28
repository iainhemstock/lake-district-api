package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.assemblers;

import com.iainhemstock.lakedistrictapi.application_logic.LatLongToDmsConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.application_logic.MeterToFeetConversionServiceImpl;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssemblerFactory {

    @Bean
    public DomainToEntityAssembler domainToEntityAssembler() {
        return new DomainToEntityAssembler(new LatLongToDmsConversionServiceImpl(), new MeterToFeetConversionServiceImpl());
    }

}
