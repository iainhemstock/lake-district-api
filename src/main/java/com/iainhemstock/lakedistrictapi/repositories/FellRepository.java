package com.iainhemstock.lakedistrictapi.repositories;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FellRepository extends JpaRepository<Fell, OsMapRef> {
//    List<FellEntity> findByNameLikeIgnoreCase(String name);
}
