package com.iainhemstock.lakedistrictapi.entities;


import com.iainhemstock.lakedistrictapi.domain.OsMapRef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parent_fell")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParentFell implements Nullable {

    @EmbeddedId
    private OsMapRef osMapRef;

    @Override
    public boolean isNull() {
        return osMapRef.toString().isEmpty();
    }

    public static ParentFell newNull() {
        return new NullFell();
    }

    @Override
    public boolean equals(Object other) {
        return osMapRef.equals(((ParentFell) other).osMapRef);
    }
}
