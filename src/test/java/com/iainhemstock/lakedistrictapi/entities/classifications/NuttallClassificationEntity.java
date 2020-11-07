package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class NuttallClassificationEntity extends ClassificationEntity {
    public NuttallClassificationEntity() {
        super(4, "Nuttall");
    }
}
