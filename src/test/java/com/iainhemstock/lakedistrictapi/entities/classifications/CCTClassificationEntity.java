package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class CCTClassificationEntity extends ClassificationEntity {
    public CCTClassificationEntity() {
        super(7, "Current county top");
    }
}
