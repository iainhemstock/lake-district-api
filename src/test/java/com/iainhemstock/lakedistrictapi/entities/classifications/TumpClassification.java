package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class TumpClassification extends Classification {
    public TumpClassification() {
        super(16, "Tump");
    }
}
