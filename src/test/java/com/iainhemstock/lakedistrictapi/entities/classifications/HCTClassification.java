package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class HCTClassification extends Classification {
    public HCTClassification() {
        super(9, "Historic county top");
    }
}
