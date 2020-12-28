package com.iainhemstock.lakedistrictapi.repository_interfaces;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.OsMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsMapRepository extends JpaRepository<OsMapEntity, Integer> {
}
