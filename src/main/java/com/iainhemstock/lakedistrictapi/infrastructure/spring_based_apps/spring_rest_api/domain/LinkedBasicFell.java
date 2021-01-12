package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.iainhemstock.lakedistrictapi.domain.Feet;
import com.iainhemstock.lakedistrictapi.domain.FellName;
import com.iainhemstock.lakedistrictapi.domain.Meters;

import java.util.EnumMap;

public interface LinkedBasicFell {
    FellName getName();
    Meters getHeightMeters();
    Feet getHeightFeet();
    EnumMap<LinkRel, Link> getLinks();
}
