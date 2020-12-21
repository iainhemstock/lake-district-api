package com.iainhemstock.lakedistrictapi.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "parent_fell")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParentFell implements Nullable {

    @Id
    @Column(name = "os_map_ref")
    private String osMapRef;

    @Override
    public boolean isNull() {
        return osMapRef.isEmpty();
    }

    public static ParentFell newNull() {
        return new NullFell();
    }

    @Override
    public boolean equals(Object other) {
        return osMapRef.equals(((ParentFell) other).osMapRef);
    }
}
