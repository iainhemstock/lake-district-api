package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class FellrangerClassification extends Classification {
    public FellrangerClassification() {
        super(15, new ClassificationName("Fellranger"));
    }
}
