package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "classifications")
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Classification {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    public Classification(final int id, @NotNull String name, final ClassificationName classificationName) {
        this.id = id;
        this.name = name;
    }

    private Classification() {
    }
}
