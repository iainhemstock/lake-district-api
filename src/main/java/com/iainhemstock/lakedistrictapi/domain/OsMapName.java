package com.iainhemstock.lakedistrictapi.domain;

public class OsMapName {
    private final String name;

    public OsMapName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return name.equals(((OsMapName) other).name);
    }
}
