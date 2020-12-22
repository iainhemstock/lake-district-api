package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class BirkettClassfication extends Classification {
    public BirkettClassfication() {
        super(11, "Birkett", new ClassificationName("Birkett"));
    }
}
