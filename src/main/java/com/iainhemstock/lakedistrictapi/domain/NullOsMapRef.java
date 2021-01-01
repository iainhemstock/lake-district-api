package com.iainhemstock.lakedistrictapi.domain;

public class NullOsMapRef extends OsMapRef {
    public NullOsMapRef() {
        super("null");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
