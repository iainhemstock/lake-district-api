package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class TumpClassificationEntity extends ClassificationEntity {
    public TumpClassificationEntity() {
        super(16, "Tump");
    }
}
