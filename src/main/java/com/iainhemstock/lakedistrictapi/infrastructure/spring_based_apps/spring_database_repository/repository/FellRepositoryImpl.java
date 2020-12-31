package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.configuration.ApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.exceptions.FellNotFoundException;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.RepoPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FellRepositoryImpl implements FellRepository {
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
        Optional<FellEntity> entity = fellEntityRepository.findById(osMapRef.toString());
        if (entity.isEmpty())
            throw new FellNotFoundException(osMapRef.toString(), apiClockService.now());
        Fell domain = this.domainToEntityAssembler.toDomain(entity.get());
        return domain;
    }

    @Override
    public RepoPage<Fell> findAll(final int offset, final int limit) {
        Page<FellEntity> fellEntities = fellEntityRepository.findAll(PageRequest.of(offset, limit));
        if (fellEntities.isEmpty())
            return SpringPageRepoPage.empty();

        List<Fell> fells = fellEntities.stream().map(domainToEntityAssembler::toDomain).collect(Collectors.toList());

        return SpringPageRepoPage.from(new PageImpl<>(
            fells,
            PageRequest.of(offset, limit),
            fellEntities.getTotalElements()));
    }
}
