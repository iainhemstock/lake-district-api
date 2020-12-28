package com.iainhemstock.lakedistrictapi.application_logic;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.application_interfaces.FellService;
import com.iainhemstock.lakedistrictapi.application_interfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.application_interfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class FellServiceImpl implements FellService {
    private final FellRepository fellRepository;
    private final ApiClockService apiClockService;
    private final MeterToFeetConversionService meterToFeetConversionService;
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final ApiProperties apiProperties;

    public FellServiceImpl(final FellRepository fellRepository,
                           final ApiClockService apiClockService,
                           final ApiProperties apiProperties,
                           final MeterToFeetConversionService meterToFeetConversionService,
                           final LatLongToDmsConversionService latLongToDmsConversionService) {
        this.apiClockService = apiClockService;
        this.apiProperties = apiProperties;
        this.fellRepository = fellRepository;
        this.meterToFeetConversionService = meterToFeetConversionService;
        this.latLongToDmsConversionService = latLongToDmsConversionService;
    }

    @Override
    public Fell getById(final OsMapRef osMapRef) {
        return fellRepository.findFellById(osMapRef);
    }

    @Override
    public Page<FellEntity> getFells(final int offset, final int limit) {
        if (offset < 0)
            throw new IllegalArgumentException("Offset cannot be negative");

        if (limit <= 0)
            throw new IllegalArgumentException("Limit cannot be negative or zero");

        return fellRepository.findAll(PageRequest.of(offset, limit));
    }


}
