package com.iainhemstock.lakedistrictapi.entities;

import javax.persistence.Entity;

@Entity
public final class NullFell extends ParentFell {

    public NullFell() {
        super("");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
