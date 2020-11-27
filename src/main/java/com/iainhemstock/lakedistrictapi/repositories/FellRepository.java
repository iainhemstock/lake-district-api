package com.iainhemstock.lakedistrictapi.repositories;

import com.iainhemstock.lakedistrictapi.entities.Fell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FellRepository extends JpaRepository<Fell, Integer> {

    List<Fell> findByNameLikeIgnoreCase(String name);

}
