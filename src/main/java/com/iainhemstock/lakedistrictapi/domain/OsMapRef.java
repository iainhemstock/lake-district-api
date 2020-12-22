package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class OsMapRef implements Serializable {

    @NotNull
    private String value;

    public OsMapRef(final String value) {
        if (value == null) throw new NullPointerException("OsMapRef cannot be null");
        if (value.equals("")) throw new IllegalArgumentException("OsMapRef cannot be blank");
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
