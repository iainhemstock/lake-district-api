package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;

import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode
public class OsMapNames {
    private Set<OsMapName> osMapNames;

    public OsMapNames() {
        this.osMapNames = new LinkedHashSet<>();
    }

    public OsMapNames(final Set<OsMapName> osMapNames) {
        this.osMapNames = osMapNames;
    }

    public void add(final OsMapName osMapName) {
        this.osMapNames.add(osMapName);
    }
}
