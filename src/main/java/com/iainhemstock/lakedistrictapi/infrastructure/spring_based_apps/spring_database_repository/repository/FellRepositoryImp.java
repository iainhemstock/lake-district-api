package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import com.iainhemstock.lakedistrictapi.repository_interfaces.FellRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class FellRepositoryImp implements FellRepository {
    @Override
    public Optional<FellEntity> findById(final OsMapRef osMapRef) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<FellEntity> findAll(final Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
