package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class FurthClassificationEntity extends ClassificationEntity {
    public FurthClassificationEntity() {
        super(8, "Furth");
    }
}
