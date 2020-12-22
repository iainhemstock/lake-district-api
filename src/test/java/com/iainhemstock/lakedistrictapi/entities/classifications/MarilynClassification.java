package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class MarilynClassification extends Classification {
    public MarilynClassification() {
        super(3, "Marilyn", new ClassificationName("Marilyn"));
    }
}
