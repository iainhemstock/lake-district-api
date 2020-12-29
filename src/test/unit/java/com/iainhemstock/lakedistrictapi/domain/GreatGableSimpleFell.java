package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.fells.GreatGableFellEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class GreatGableSimpleFell extends SimpleFell {

    public GreatGableSimpleFell() {
        super(
            new GreatGableFellEntity().getName(),
            new GreatGableFellEntity().getRegionEntity().getRegionName(),
            new Links(
                new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new GreatGableFellEntity().getOsMapRef().toString())));
    }

}
