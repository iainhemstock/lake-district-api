package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import com.iainhemstock.lakedistrictapi.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repositories.FellRepository;
import com.iainhemstock.lakedistrictapi.serviceinterfaces.*;
import com.iainhemstock.lakedistrictapi.services.mappers.FellSimplifiedPagedCollectionMapper;
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
    private final FellSimplifiedPagedCollectionMapper pagedCollectionMapper;
    private final MeterToFeetConversionService meterToFeetConversionService;
    private final LatLongToDmsConversionService latLongToDmsConversionService;
    private final ApiProperties apiProperties;

    @Autowired
    public FellServiceImpl(final FellRepository fellRepository,
                           final ApiClockService apiClockService,
                           final FellSimplifiedPagedCollectionMapper pagedCollectionMapper,
                           final ApiProperties apiProperties,
                           final MeterToFeetConversionService meterToFeetConversionService,
                           final LatLongToDmsConversionService latLongToDmsConversionService) {
        this.apiClockService = apiClockService;
        this.apiProperties = apiProperties;
        this.fellRepository = fellRepository;
        this.pagedCollectionMapper = pagedCollectionMapper;
        this.meterToFeetConversionService = meterToFeetConversionService;
        this.latLongToDmsConversionService = latLongToDmsConversionService;
    }

    @Override
    public Fell getById(final OsMapRef osMapRef) {
        Optional<Fell> fell = fellRepository.findById(osMapRef);
        if (fell.isEmpty()) {
            String requestUri = String.format("%s/fells/%s", apiProperties.getBaseUrl(), osMapRef.toString());
            throw new FellNotFoundException(osMapRef.toString(), apiClockService.now(), HttpMethod.GET.name(), requestUri);
        }
        Fell fellEntity = fell.get();
        fellEntity.setMeterToFeetConversionService(meterToFeetConversionService);
        fellEntity.setLatLongToDmsConversionService(latLongToDmsConversionService);
        return fellEntity;
    }

    public PagedCollectionDTO<SummarisedFellDTO> getSummarisedFells(final Integer offset, final Integer limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Param 'offset' cannot be negative");
        }
        Page<Fell> fellPage = fellRepository.findAll(PageRequest.of(offset, limit));
        return pagedCollectionMapper.map(fellPage);
    }

    public PagedCollection getSummarisedFells2(final int offset, final int limit) {
        Page<Fell> fellPage = fellRepository.findAll(PageRequest.of(offset, limit));
        PagedCollection pagedCollection = new PagedCollection(offset, limit, fellPage.toList(), fellPage.getTotalPages(), fellPage.getTotalElements());
        pagedCollection.setFirstLink(new Link(LinkRel.FIRST, String.format("%s/fells?offset=%d&limit=%d", apiProperties.getBaseUrl(), offset, limit)));
        return pagedCollection;
    }


}
