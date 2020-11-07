package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class SimmClassificationEntity extends ClassificationEntity {
    public SimmClassificationEntity() {
        super(13, "Simm");
    }
}
