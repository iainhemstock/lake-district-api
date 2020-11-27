package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class WainwrightClassification extends Classification {
    public WainwrightClassification() {
        super(1, "Wainwright");
    }
}
