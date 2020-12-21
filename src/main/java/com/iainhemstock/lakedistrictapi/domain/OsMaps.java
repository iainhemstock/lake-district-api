package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.entities.OsMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OsMaps {

    @ManyToMany
    @JoinTable(
        name = "fells_osmaps",
        joinColumns = @JoinColumn(name = "os_map_ref"),
        inverseJoinColumns = @JoinColumn(name = "os_map_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "os_map_ref", "os_map_id" }))
    private Set<OsMap> osMaps;

    public OsMaps(final Set<OsMap> osMaps) {
        this.osMaps = osMaps;
    }

    public Set<OsMap> toSet() {
        return osMaps;
    }
}
