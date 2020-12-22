package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "classifications")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Classification {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Embedded
    @Column(name = "name")
    @NotNull
    private ClassificationName classificationName;

    public Classification(final int id, final ClassificationName classificationName) {
        this.id = id;
        this.classificationName = classificationName;
    }
}
