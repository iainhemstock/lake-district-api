package com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities;

import com.iainhemstock.lakedistrictapi.domain.OsMapName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "os_maps")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OsMap {

    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private int id;

    @Embedded
    @Column(name = "name")
    @NotNull
    private OsMapName osMapName;

}
