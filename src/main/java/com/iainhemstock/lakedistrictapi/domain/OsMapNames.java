package com.iainhemstock.lakedistrictapi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class OsMapNames {
    private List<OsMapName> osMapNames;

    public OsMapNames(final OsMapName... osMapNames) {
        this.osMapNames = new ArrayList<>();
        Collections.addAll(this.osMapNames, osMapNames);
    }

    public OsMapName get(final int i) {
        return this.osMapNames.get(i);
    }

    public Stream<OsMapName> stream() {
        return this.osMapNames.stream();
    }

    public void add(final OsMapName osMapName) {
        this.osMapNames.add(osMapName);
    }

    @Override
    public boolean equals(Object other) {
        List<OsMapName> otherList = ((OsMapNames) other).osMapNames;
        return this.osMapNames.containsAll(otherList) && otherList.containsAll(this.osMapNames);
    }
}
