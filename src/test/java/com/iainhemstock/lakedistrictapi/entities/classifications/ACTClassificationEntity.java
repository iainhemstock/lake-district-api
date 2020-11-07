package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class ACTClassificationEntity extends ClassificationEntity {
    public ACTClassificationEntity() {
        super(10, "Administrative county top");
    }
}
