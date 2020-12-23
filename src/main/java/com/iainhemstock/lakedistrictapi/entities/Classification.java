package com.iainhemstock.lakedistrictapi.entities;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "classifications")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
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
    
}
