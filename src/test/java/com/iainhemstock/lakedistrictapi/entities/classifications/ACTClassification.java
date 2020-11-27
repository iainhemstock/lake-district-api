package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class ACTClassification extends Classification {
    public ACTClassification() {
        super(10, "Administrative county top");
    }
}
