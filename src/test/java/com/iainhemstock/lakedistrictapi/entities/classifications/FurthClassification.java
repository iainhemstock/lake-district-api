package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class FurthClassification extends Classification {
    public FurthClassification() {
        super(8, "Furth");
    }
}
