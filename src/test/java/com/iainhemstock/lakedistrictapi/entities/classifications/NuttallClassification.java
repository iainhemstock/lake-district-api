package com.iainhemstock.lakedistrictapi.entities.classifications;

import com.iainhemstock.lakedistrictapi.entities.Classification;

import javax.persistence.Entity;

@Entity
public final class NuttallClassification extends Classification {
    public NuttallClassification() {
        super(4, "Nuttall");
    }
}
