package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class SyngeClassificationEntity extends ClassificationEntity {
    public SyngeClassificationEntity() {
        super(14, "Synge");
    }
}
