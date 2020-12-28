package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.exception_handling.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.application_interfaces.*;
//import com.iainhemstock.lakedistrictapi.services.mappers.FellSimplifiedPagedCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FellServiceImpl implements FellService {
    private final FellRepository fellRepository;
    private final ApiClockService apiClockService;
    private final MeterToFeetConversionService meterToFeetConversionService;
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final ApiProperties apiProperties;

    @Autowired
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
    public FellEntity getById(final OsMapRef osMapRef) {
        Optional<FellEntity> fell = fellRepository.findById(osMapRef);
        if (fell.isEmpty()) {
            String requestUri = String.format("%s/fells/%s", apiProperties.getBaseUrl(), osMapRef.toString());
            throw new FellNotFoundException(osMapRef.toString(), apiClockService.now(), HttpMethod.GET.name(), requestUri);
        }
        FellEntity fellEntity = fell.get();
        fellEntity.setMeterToFeetConversionService(meterToFeetConversionService);
        fellEntity.setLatLongToDmsConversionService(latLongToDmsConversionService);
        return fellEntity;
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
