package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class FellRepositoryImpl implements FellRepository {
    private static final String FELL_ENTITY_HEIGHT_METERS_FIELD = "heightMeters";

    private final FellEntityRepository fellEntityRepository;
    private final DomainToEntityAssembler domainToEntityAssembler;
    private final ApiClockService apiClockService;

    @Autowired
    public FellRepositoryImpl(final FellEntityRepository fellEntityRepository,
                              final DomainToEntityAssembler domainToEntityAssembler,
                              final ApiClockService apiClockService) {
        this.fellEntityRepository = fellEntityRepository;
        this.domainToEntityAssembler = domainToEntityAssembler;
        this.apiClockService = apiClockService;
    }

    @Override
    public Fell findById(final OsMapRef osMapRef) {
        FellEntity entity = fellEntityRepository.findById(osMapRef.toString())
            .orElseThrow(() -> new FellNotFoundException(osMapRef.toString(), apiClockService.now()));
        return this.domainToEntityAssembler.toDomain(entity);
    }

    public ResultPage<Fell> findAll(final int offset, final int limit, final String sort) {
        Page<FellEntity> fellEntityPage = fellEntityRepository.findAll(PageRequest.of(offset, limit, sortCriteria(sort)));
        List<Fell> fellList = fellEntityPage.stream()
            .map(this.domainToEntityAssembler::toDomain)
            .collect(Collectors.toList());
        return SpringPageResultPage.from(new PageImpl<>(fellList, PageRequest.of(offset, limit), fellEntityPage.getTotalElements()));
    }

    private Sort sortCriteria(final String sort) {
        if (sort.equals("height.desc")) return Sort.by(Sort.Direction.DESC, FELL_ENTITY_HEIGHT_METERS_FIELD);
        else if (sort.equals("height.asc")) return Sort.by(Sort.Direction.ASC, FELL_ENTITY_HEIGHT_METERS_FIELD);
        else if (sort.equals("name.asc")) return Sort.by(Sort.Direction.ASC, "name");
        else if (sort.equals("name.desc")) return Sort.by(Sort.Direction.DESC, "name");
        throw new IllegalArgumentException(String.format("Unrecognized sort request {sort=%s}", sort));
    }
}
