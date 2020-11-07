package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class MarilynClassificationEntity extends ClassificationEntity {
    public MarilynClassificationEntity() {
        super(3, "Marilyn");
    }
}
