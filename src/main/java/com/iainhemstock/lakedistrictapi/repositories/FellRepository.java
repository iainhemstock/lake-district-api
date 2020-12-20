package com.iainhemstock.lakedistrictapi.repositories;

import com.iainhemstock.lakedistrictapi.entities.FellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FellRepository extends JpaRepository<FellEntity, String> {
    List<FellEntity> findByNameLikeIgnoreCase(String name);
}
