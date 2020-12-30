package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.config.TestApiProperties;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.GreatGableFellEntity;
import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.HelvellynFellEntity;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode
public class HelvellynSimpleFell extends SimpleFell {

    public HelvellynSimpleFell() {
        super(
            new HelvellynFell().getName(),
            new HelvellynFell().getRegionName(),
            Set.of(
                new Link(LinkRel.SELF, String.format("%s/fells%s/", TestApiProperties.API_BASE_URL, new HelvellynFellEntity().getOsMapRef()))));
    }

}
