package com.iainhemstock.lakedistrictapi.domain;

public class SummarisedFell {
    private final FellName fellName;
    private final Link selfLink;

    public SummarisedFell(final FellName fellName, final Link selfLink) {
        this.fellName = fellName;
        this.selfLink = selfLink;
    }
}
