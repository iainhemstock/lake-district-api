package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class MarilynClassificationEntity extends ClassificationEntity {
    public MarilynClassificationEntity() {
        super(3, new ClassificationName("Marilyn"));
    }
}
