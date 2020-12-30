package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ScafellPikeFellEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ScafellPikeSimpleFell extends SimpleFell {

    public ScafellPikeSimpleFell() {
        super(
            new FellName(new ScafellPikeFellEntity().getName()),
            new RegionName(new ScafellPikeFellEntity().getRegionEntity().getName()),
            new Links(
                new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new ScafellPikeFellEntity().getOsMapRef().toString())));
    }

}
