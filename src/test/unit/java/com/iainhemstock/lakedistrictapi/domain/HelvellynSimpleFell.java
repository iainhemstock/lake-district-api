package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class HelvellynSimpleFell extends SimpleFell {

    public HelvellynSimpleFell() {
        super(
            new HelvellynFell().getName(),
            new HelvellynFell().getRegionName(),
            new Links(
                new Link(LinkRel.SELF, "http://localhost:8080/api/v1/fells/" + new HelvellynFell().getOsMapRef().toString())));
    }

}
