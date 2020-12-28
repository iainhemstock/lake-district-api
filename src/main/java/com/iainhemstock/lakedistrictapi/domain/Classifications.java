package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ClassificationEntity;
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
    private Set<ClassificationEntity> classificationEntities;

    public Classifications(final Set<ClassificationEntity> classificationEntities) {
        this.classificationEntities = classificationEntities;
    }

    public void forEach(final Consumer<? super ClassificationEntity> consumer) {
        for (ClassificationEntity classificationEntity : this.classificationEntities) {
            consumer.accept(classificationEntity);
        }
    }
}
