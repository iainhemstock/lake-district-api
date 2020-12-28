package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class BirkettClassfication extends Classification {
    public BirkettClassfication() {
        super(11, new ClassificationName("Birkett"));
    }
}
