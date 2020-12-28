package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.assembler.DomainToEntityAssembler;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class FellRepositoryImpl implements FellRepository {
    private final FellEntityRepository fellEntityRepository;
    private final DomainToEntityAssembler domainToEntityAssembler;

    @Autowired
    public FellRepositoryImpl(final FellEntityRepository fellEntityRepository, final DomainToEntityAssembler domainToEntityAssembler) {
        this.fellEntityRepository = fellEntityRepository;
        this.domainToEntityAssembler = domainToEntityAssembler;
    }

    @Override
    public Optional<FellEntity> findById(final OsMapRef osMapRef) {
        return fellEntityRepository.findById(osMapRef);
    }

    @Override
    public Fell findFellById(final OsMapRef osMapRef) {
        Optional<FellEntity> entity = fellEntityRepository.findById(osMapRef);
        Fell domain = this.domainToEntityAssembler.toDomain(entity.get());
        return domain;
    }

    @Override
    public Page<FellEntity> findAll(final Pageable pageable) {
        return fellEntityRepository.findAll(pageable);
    }
}
