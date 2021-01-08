package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class OsMapRef {
    private String value;
    private boolean isNull;

    public OsMapRef(final String value) {
        if (value == null) {
            throw new NullPointerException("OsMapRef cannot be null");
        }
        else if (value.equals("")) {
            throw new IllegalArgumentException("OsMapRef cannot be blank");
        }
        else {
            this.value = value;
            this.isNull = false;
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
