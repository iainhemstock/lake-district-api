package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.EndpointGeneratorService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.LatLongToDmsConversionService;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.MeterToFeetConversionService;
import com.iainhemstock.lakedistrictapi.services.mappers.FellSimplifiedPagedCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class FellService {
    private final FellRepository fellRepository;
    private final MeterToFeetConversionService m2fConversionService;
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final ApiClockService apiClockService;
    private final EndpointGeneratorService endpointGeneratorService;
    private final FellSimplifiedPagedCollectionMapper pagedCollectionMapper;
    private final ApiProperties apiProperties;

    @Autowired
    public FellService(final FellRepository fellRepository,
                       final MeterToFeetConversionService m2fConversionService,
                       final LatLongToDmsConversionService latLongToDmsConversionService,
                       final ApiClockService apiClockService,
                       final EndpointGeneratorService endpointGeneratorService,
                       final FellSimplifiedPagedCollectionMapper pagedCollectionMapper,
                       final ApiProperties apiProperties) {
        this.apiClockService = apiClockService;
        this.apiProperties = apiProperties;
        this.fellRepository = fellRepository;
        this.m2fConversionService = m2fConversionService;
        this.latLongToDmsConversionService = latLongToDmsConversionService;
        this.endpointGeneratorService = endpointGeneratorService;
        this.pagedCollectionMapper = pagedCollectionMapper;
    }

    public DetailedFell getDetailedFell(OsMapRef osMapRef) {
        FellEntity fell = fellRepository.findById(osMapRef.toString())
            .orElseThrow(() -> {
                String requestUri = String.format("%s/fells/%s", apiProperties.getBaseUrl(), osMapRef.toString());
                return new FellNotFoundException(osMapRef.toString(), apiClockService.now(), HttpMethod.GET.name(), requestUri);
            });
        return new DetailedFell(fell, m2fConversionService, latLongToDmsConversionService, endpointGeneratorService);
    }

    public PagedCollectionDTO<SummarisedFellDTO> getSummarisedFells(final Integer offset, final Integer limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Param 'offset' cannot be negative");
        }
        Page<FellEntity> fellPage = fellRepository.findAll(PageRequest.of(offset, limit));
        return pagedCollectionMapper.map(fellPage);
    }

    public PagedCollection getSummarisedFells2(final int offset, final int limit) {
        Page<FellEntity> fellPage = fellRepository.findAll(PageRequest.of(offset, limit));
        PagedCollection pagedCollection = new PagedCollection(offset, limit, fellPage.toList(), fellPage.getTotalPages(), fellPage.getTotalElements());
        pagedCollection.setFirstLink(new Link(String.format("%s/fells?offset=%d&limit=%d", apiProperties.getBaseUrl(), offset, limit)));
        return pagedCollection;
    }
}
