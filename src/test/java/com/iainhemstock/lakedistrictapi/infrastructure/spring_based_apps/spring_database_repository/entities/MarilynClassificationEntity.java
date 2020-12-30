package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class MarilynClassificationEntity extends ClassificationEntity {
    public MarilynClassificationEntity() {
        super(3, "Marilyn");
    }
}
