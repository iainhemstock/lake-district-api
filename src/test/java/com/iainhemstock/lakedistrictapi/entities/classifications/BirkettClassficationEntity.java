package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class BirkettClassficationEntity extends ClassificationEntity {
    public BirkettClassficationEntity() {
        super(11, "Birkett");
    }
}
