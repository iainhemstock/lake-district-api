package com.iainhemstock.lakedistrictapi.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ClassificationNames {
    private final List<ClassificationName> classificationNames;

    public ClassificationNames(ClassificationName... classificationNames) {
        this.classificationNames = new ArrayList<>();
        Collections.addAll(this.classificationNames, classificationNames);
    }

    public ClassificationName get(final int i) {
        return this.classificationNames.get(i);
    }

    public Stream<ClassificationName> stream() {
        return this.classificationNames.stream();
    }

    public void add(final ClassificationName classificationName) {
        this.classificationNames.add(classificationName);
    }

    @Override
    public boolean equals(Object other) {
        List<ClassificationName> otherList = ((ClassificationNames) other).classificationNames;
        return classificationNames.containsAll(otherList) && otherList.containsAll(classificationNames);
    }
}
