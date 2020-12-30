package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.application_interfaces.ApiClockService;
import com.iainhemstock.lakedistrictapi.domain.*;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_configuration.ApiProperties;
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
import java.util.Set;
import java.util.stream.Collectors;

public class FellRepositoryImpl implements FellRepository {
    private final FellEntityRepository fellEntityRepository;
    private final DomainToEntityAssembler domainToEntityAssembler;
    private final ApiClockService apiClockService;
    private ApiProperties apiProperties;

    @Autowired
    public FellRepositoryImpl(final FellEntityRepository fellEntityRepository,
                              final DomainToEntityAssembler domainToEntityAssembler,
                              final ApiClockService apiClockService,
                              final ApiProperties apiProperties) {
        this.fellEntityRepository = fellEntityRepository;
        this.domainToEntityAssembler = domainToEntityAssembler;
        this.apiClockService = apiClockService;
        this.apiProperties = apiProperties;
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
    public RepoPage<SimpleFell> findAll(final int offset, final int limit) {
        Page<FellEntity> fellEntityPage = fellEntityRepository.findAll(PageRequest.of(offset, limit));
        if (fellEntityPage.isEmpty())
            return SpringPageRepoPage.empty();

        List<SimpleFell> simpleFells = fellEntityPage.toList().stream()
            .map(fellEntity -> {
                return new SimpleFell(
                    new FellName(fellEntity.getName()),
                    new RegionName(fellEntity.getRegionEntity().getName()),
                    Set.of(
                        new Link(LinkRel.SELF, String.format("%s/fells/%s", apiProperties.getBaseUrl(), fellEntity.getOsMapRef()))));
            }).collect(Collectors.toList());

        Page<SimpleFell> simpleFellPage = new PageImpl<>(simpleFells, PageRequest.of(offset, limit), fellEntityPage.getTotalElements());

        return SpringPageRepoPage.from(simpleFellPage);
    }
}
