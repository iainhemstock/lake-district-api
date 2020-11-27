package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class HewittClassification extends Classification {
    public HewittClassification() {
        super(2, "Hewitt");
    }
}
