package com.iainhemstock.lakedistrictapi.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

@EqualsAndHashCode
@ToString
public class ClassificationNames {
    private Set<ClassificationName> classificationNames;

    public ClassificationNames() {
        this.classificationNames = new LinkedHashSet<>();
    }

    public ClassificationNames(final Set<ClassificationName> classificationNames) {
        this.classificationNames = classificationNames;
    }

    public void add(final ClassificationName classificationName) {
        this.classificationNames.add(classificationName);
    }

    public void forEach(final Consumer<? super ClassificationName> consumer) {
        for (ClassificationName classificationName : this.classificationNames) {
            consumer.accept(classificationName);
        }
    }
}
