package com.iainhemstock.lakedistrictapi.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class FellName {
    @Column(name = "name")
    @NotNull
    private String value;

    public FellName(final String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
