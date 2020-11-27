package com.iainhemstock.lakedistrictapi.repositories;

import com.iainhemstock.lakedistrictapi.entities.OsMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsMapRepository extends JpaRepository<OsMap, Integer> {
}
