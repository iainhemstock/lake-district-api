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
    @Column(name = "fell_id")
    private int fellId;

    @Override
    public boolean isNull() {
        return fellId < 1;
    }

    public static ParentFell newNull() {
        return new NullFell();
    }
}
