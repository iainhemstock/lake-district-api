package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class CCTClassification extends Classification {
    public CCTClassification() {
        super(7, "Current county top");
    }
}
