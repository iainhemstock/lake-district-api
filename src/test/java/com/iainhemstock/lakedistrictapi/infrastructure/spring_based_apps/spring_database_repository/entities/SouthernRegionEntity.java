package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

public final class SouthernRegionEntity extends RegionEntity {
    public SouthernRegionEntity() {
        super(4, "Southern Lake District");
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
