package com.iainhemstock.lakedistrictapi.entities.regions;

import com.iainhemstock.lakedistrictapi.entities.Region;

import javax.persistence.Entity;

@Entity
public final class WesternRegion extends Region {
    public WesternRegion() {
        super(7, "Western Lake District");
    }
}
