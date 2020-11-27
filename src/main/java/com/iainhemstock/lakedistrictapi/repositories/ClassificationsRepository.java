package com.iainhemstock.lakedistrictapi.repositories;

import com.iainhemstock.lakedistrictapi.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationsRepository extends JpaRepository<Classification, Integer> {
}
