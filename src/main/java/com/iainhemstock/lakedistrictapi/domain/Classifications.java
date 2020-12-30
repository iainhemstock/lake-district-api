package com.iainhemstock.lakedistrictapi.domain;

import com.iainhemstock.lakedistrictapi.infrastructure.spring_based_apps.spring_database_repository.entities.ClassificationEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Classifications {

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
