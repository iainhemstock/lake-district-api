package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class HCTClassificationEntity extends ClassificationEntity {
    public HCTClassificationEntity() {
        super(9, "Historic county top");
    }
}
