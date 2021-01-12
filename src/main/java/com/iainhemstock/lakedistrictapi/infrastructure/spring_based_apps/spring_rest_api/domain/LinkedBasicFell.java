package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_rest_api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iainhemstock.lakedistrictapi.domain.FellName;

import java.util.EnumMap;

@JsonSerialize(as = LinkedBasicFell.class)
public interface LinkedBasicFell {
    FellName getName();
    EnumMap<LinkRel, Link> getLinks();
}
