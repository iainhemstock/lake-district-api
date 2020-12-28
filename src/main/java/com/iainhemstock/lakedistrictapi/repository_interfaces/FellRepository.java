package com.iainhemstock.lakedistrictapi.repository_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FellRepository {
    Optional<FellEntity> findById(final OsMapRef osMapRef);
    Page<FellEntity> findAll(final Pageable pageable);

    Optional<Fell> findFellById(OsMapRef osMapRef);
}
