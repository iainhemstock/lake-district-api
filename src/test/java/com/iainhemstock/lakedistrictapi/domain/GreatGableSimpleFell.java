package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.GreatGableFellEntity;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode
public class GreatGableSimpleFell extends SimpleFell {

    public GreatGableSimpleFell() {
        super(
            new FellName(new GreatGableFellEntity().getName()),
            new RegionName(new GreatGableFellEntity().getRegionEntity().getName()),
            Set.of(
                new Link(LinkRel.SELF, String.format("%s/fells%s/", TestApiProperties.API_BASE_URL, new GreatGableFellEntity().getOsMapRef()))));
    }

}
