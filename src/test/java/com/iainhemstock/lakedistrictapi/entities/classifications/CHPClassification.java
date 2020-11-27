package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class CHPClassification extends Classification {
    public CHPClassification() {
        super(5, "Country high point");
    }
}
