package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.ClassificationEntity;

import javax.persistence.Entity;

@Entity
public final class FellrangerClassificationEntity extends ClassificationEntity {
    public FellrangerClassificationEntity() {
        super(15, "Fellranger");
    }
}
