package com.iainhemstock.lakedistrictapi.entities;

import javax.persistence.Entity;

@Entity
public final class NullFellEntity extends ParentFellEntity {

    public NullFellEntity() {
        super(-1);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
