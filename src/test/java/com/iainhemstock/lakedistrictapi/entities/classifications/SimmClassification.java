package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class SimmClassification extends Classification {
    public SimmClassification() {
        super(13, "Simm");
    }
}
