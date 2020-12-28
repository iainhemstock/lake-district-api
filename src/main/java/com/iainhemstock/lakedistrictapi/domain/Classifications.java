package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.Classification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Consumer;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Classifications {

    @ManyToMany
    @JoinTable(
        name = "fells_classifications",
        joinColumns = @JoinColumn(name = "os_map_ref"),
        inverseJoinColumns = @JoinColumn(name = "classification_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = { "os_map_ref", "classification_id" }))
    private Set<Classification> classifications;

    public Classifications(final Set<Classification> classifications) {
        this.classifications = classifications;
    }

    public void forEach(final Consumer<? super Classification> consumer) {
        for (Classification classification : this.classifications) {
            consumer.accept(classification);
        }
    }
}
