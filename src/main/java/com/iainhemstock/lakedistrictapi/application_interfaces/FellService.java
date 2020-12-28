package com.iainhemstock.lakedistrictapi.application_interfaces;

import com.iainhemstock.lakedistrictapi.domain.Fell;
import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import org.springframework.data.domain.Page;

public interface FellService {
    Fell getById(final OsMapRef osMapRef);
    Page<FellEntity> getFells(int offset, int limit);
}
