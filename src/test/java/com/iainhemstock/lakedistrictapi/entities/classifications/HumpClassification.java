package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class HumpClassification extends Classification {
    public HumpClassification() {
        super(12, "HuMP");
    }
}
