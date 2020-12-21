package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.OsMapRef;

import javax.persistence.Entity;

@Entity
public final class NullFell extends ParentFell {

    public NullFell() {
        super(new OsMapRef("null os map ref"));
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
