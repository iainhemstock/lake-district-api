package com.iainhemstock.lakedistrictapi.entities;


import com.iainhemstock.lakedistrictapi.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
}
