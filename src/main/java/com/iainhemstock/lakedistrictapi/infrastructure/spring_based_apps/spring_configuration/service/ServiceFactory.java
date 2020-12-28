package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.service;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.application_logic.FellServiceImpl;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Autowired private FellRepository fellRepository;
    @Autowired private ApiClockService apiClockService;
    @Autowired private ApiProperties apiProperties;
    @Autowired private MeterToFeetConversionService meterToFeetConversionService;
    @Autowired private LatLongToDmsConversionService latLongToDmsConversionService;

    @Bean
    public FellService fellService() {
        return new FellServiceImpl(fellRepository,
                                   apiClockService,
                                   apiProperties,
                                   meterToFeetConversionService,
                                   latLongToDmsConversionService);
    }

}
