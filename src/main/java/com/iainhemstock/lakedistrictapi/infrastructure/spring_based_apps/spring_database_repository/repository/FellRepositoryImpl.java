package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository.jpa_repository.FellEntityRepository;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class FellRepositoryImpl implements FellRepository {
    private final FellEntityRepository fellEntityRepository;

    @Autowired
    public FellRepositoryImpl(final FellEntityRepository fellEntityRepository) {
        this.fellEntityRepository = fellEntityRepository;
    }

    @Override
    public Optional<FellEntity> findById(final OsMapRef osMapRef) {
        return fellEntityRepository.findById(osMapRef);
    }

    @Override
    public Optional<Fell> findFellById(final OsMapRef osMapRef) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<FellEntity> findAll(final Pageable pageable) {
        return fellEntityRepository.findAll(pageable);
    }
}
