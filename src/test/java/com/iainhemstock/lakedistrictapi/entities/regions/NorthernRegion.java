package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class NorthernRegion extends Region {

    public NorthernRegion() {
        super(5, "Northern Lake District");
    }

}
