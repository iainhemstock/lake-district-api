package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ScafellPikeFellEntity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ScafellPikeSimpleFell extends SimpleFell {

    public ScafellPikeSimpleFell() {
        super(
            new FellName(new ScafellPikeFellEntity().getName()),
            new RegionName(new ScafellPikeFellEntity().getRegionEntity().getName()),
            new Links(
                new Link(LinkRel.SELF, String.format("%s/fells%s/", TestApiProperties.API_BASE_URL, new ScafellPikeFellEntity().getOsMapRef()))));
    }

}
