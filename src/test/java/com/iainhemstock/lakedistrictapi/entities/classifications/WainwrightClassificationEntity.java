package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class WainwrightClassificationEntity extends ClassificationEntity {
    public WainwrightClassificationEntity() {
        super(1, "Wainwright");
    }
}
