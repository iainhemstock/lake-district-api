package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class CHPClassificationEntity extends ClassificationEntity {
    public CHPClassificationEntity() {
        super(5, "Country high point");
    }
}
