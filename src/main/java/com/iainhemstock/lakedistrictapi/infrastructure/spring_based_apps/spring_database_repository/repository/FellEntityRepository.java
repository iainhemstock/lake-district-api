package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.repository;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.FellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FellEntityRepository extends JpaRepository<FellEntity, OsMapRef> {
//    List<FellEntity> findByNameLikeIgnoreCase(String name);
}
