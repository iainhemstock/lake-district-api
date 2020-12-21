package com.iainhemstock.lakedistrictapi.services;

import com.iainhemstock.lakedistrictapi.config.ApiProperties;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.dtos.PagedCollectionDTO;
import com.iainhemstock.lakedistrictapi.dtos.SummarisedFellDTO;
import com.iainhemstock.lakedistrictapi.entities.FellEntity;
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
public class FellEntityServiceImpl implements FellEntityService {
    private final FellRepository fellRepository;
    private final ApiClockService apiClockService;
    private final FellSimplifiedPagedCollectionMapper pagedCollectionMapper;
    private final ApiProperties apiProperties;

    @Autowired
    public FellEntityServiceImpl(final FellRepository fellRepository,
                                 final ApiClockService apiClockService,
                                 final FellSimplifiedPagedCollectionMapper pagedCollectionMapper,
                                 final ApiProperties apiProperties) {
        this.apiClockService = apiClockService;
        this.apiProperties = apiProperties;
        this.fellRepository = fellRepository;
        this.pagedCollectionMapper = pagedCollectionMapper;
    }

    @Override
    public FellEntity getById(final OsMapRef osMapRef) {
        Optional<FellEntity> fell = fellRepository.findById(osMapRef);
        if (fell.isEmpty()) {
            String requestUri = String.format("%s/fells/%s", apiProperties.getBaseUrl(), osMapRef.toString());
            throw new FellNotFoundException(osMapRef.toString(), apiClockService.now(), HttpMethod.GET.name(), requestUri);
        }
        return fell.get();
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
        pagedCollection.setFirstLink(new Link(LinkRel.FIRST, String.format("%s/fells?offset=%d&limit=%d", apiProperties.getBaseUrl(), offset, limit)));
        return pagedCollection;
    }


}
