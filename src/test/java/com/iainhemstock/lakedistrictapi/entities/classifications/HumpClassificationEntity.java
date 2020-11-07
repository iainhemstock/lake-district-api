package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class HumpClassificationEntity extends ClassificationEntity {
    public HumpClassificationEntity() {
        super(12, "HuMP");
    }
}
