package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class HewittClassificationEntity extends ClassificationEntity {
    public HewittClassificationEntity() {
        super(2, "Hewitt");
    }
}
