package com.iainhemstock.lakedistrictapi.config.modelmapper.converters;

import com.iainhemstock.lakedistrictapi.domain.ClassificationName;
import com.iainhemstock.lakedistrictapi.domain.ClassificationNames;
import com.iainhemstock.lakedistrictapi.entities.Classification;
import org.modelmapper.AbstractConverter;

import java.util.Set;
import java.util.stream.Collectors;

public class ClassificationConverter extends AbstractConverter<ClassificationNames, Set<String>> {
    @Override
    protected Set<String> convert(final ClassificationNames classificationNames) {
        return classificationNames.stream()
            .map(ClassificationName::toString)
            .collect(Collectors.toSet());
    }
}
