package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.GreatGableFellEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GreatGableSimpleFell extends SimpleFell {

    public GreatGableSimpleFell() {
        super(
            new FellName(new GreatGableFellEntity().getName()),
            new RegionName(new GreatGableFellEntity().getRegionEntity().getName()),
            new Links(
                new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new GreatGableFellEntity().getOsMapRef().toString())));
    }

}
