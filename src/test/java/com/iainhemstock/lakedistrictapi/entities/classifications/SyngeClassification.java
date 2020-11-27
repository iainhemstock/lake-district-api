package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class SyngeClassification extends Classification {
    public SyngeClassification() {
        super(14, "Synge");
    }
}
